package com.pubnub.api.endpoints;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.callbacks.TimeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.PNTimeResult;
import org.awaitility.Awaitility;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TimeEndpointTest extends TestHarness {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(this.PORT), false);

    private Time partialTime;
    private PubNub pubnub;

    @Before
    public void beforeEach() throws IOException {
        pubnub = this.createPubNubInstance();
        partialTime = pubnub.time();
        wireMockRule.start();
    }

    @After
    public void afterEach() {
        pubnub.destroy();
        pubnub = null;
        wireMockRule.stop();
    }

    @Test
    public void testSyncSuccess() throws IOException, PubNubException, InterruptedException {
        stubFor(get(urlPathEqualTo("/time/0"))
                .willReturn(aResponse().withBody("[14593046077243110]")));

        PNTimeResult response = partialTime.sync();
        assertTrue(response.getTimetoken().equals(14593046077243110L));

        List<LoggedRequest> requests = findAll(getRequestedFor(urlMatching("/.*")));
        assertEquals(1, requests.size());

    }

    @Test(expected = PubNubException.class)
    public void testSyncBrokenWithString() throws IOException, PubNubException {
        stubFor(get(urlPathEqualTo("/time/0"))
                .willReturn(aResponse().withBody("[abc]")));
        partialTime.sync();
    }

    @Test(expected = PubNubException.class)
    public void testSyncBrokenWithoutJSON() throws IOException, PubNubException {
        stubFor(get(urlPathEqualTo("/time/0"))
                .willReturn(aResponse().withBody("zimp")));
        partialTime.sync();
    }

    @Test(expected = PubNubException.class)
    public void testSyncBrokenWithout200() throws IOException, PubNubException {
        stubFor(get(urlPathEqualTo("/time/0"))
                .willReturn(aResponse().withBody("[14593046077243110]").withStatus(404)));
        PNTimeResult response = partialTime.sync();
        assertTrue(response.getTimetoken().equals(14593046077243110L));
    }

    @Test
    public void testAsyncSuccess() throws IOException, PubNubException {
        stubFor(get(urlPathEqualTo("/time/0"))
                .willReturn(aResponse().withBody("[14593046077243110]")));
        final AtomicInteger atomic = new AtomicInteger(0);
        partialTime.async(new TimeCallback() {

            @Override
            public void onResponse(PNTimeResult result, PNStatus status) {
                assertTrue(result.getTimetoken().equals(14593046077243110L));
                atomic.incrementAndGet();
            }
        });

        Awaitility.await().atMost(5, TimeUnit.SECONDS)
                .untilAtomic(atomic, org.hamcrest.core.IsEqual.equalTo(1));
    }

    @Test
    public void testAsyncRetrySuccess() throws IOException, PubNubException {
        stubFor(get(urlPathEqualTo("/time/0"))
                .willReturn(aResponse().withBody("[14593046077243110]")));
        final AtomicInteger atomic = new AtomicInteger(0);
        partialTime.async(new TimeCallback() {

            @Override
            public void onResponse(PNTimeResult result, PNStatus status) {


                assertTrue(result.getTimetoken().equals(14593046077243110L));
                atomic.incrementAndGet();

                if (atomic.get() == 1) {
                    status.retry();
                }

            }
        });

        Awaitility.await().atMost(5, TimeUnit.SECONDS)
                .untilAtomic(atomic, org.hamcrest.core.IsEqual.equalTo(2));
    }

    @Test
    public void testAsyncBrokenWithString() throws IOException, PubNubException {
        stubFor(get(urlPathEqualTo("/time/0"))
                .willReturn(aResponse().withBody("[abc]")));
        final AtomicInteger atomic = new AtomicInteger(0);
        partialTime.async(new TimeCallback() {

            @Override
            public void onResponse(PNTimeResult result, PNStatus status) {
                if (status != null) {
                    atomic.incrementAndGet();
                }
            }
        });

        Awaitility.await().atMost(5, TimeUnit.SECONDS)
                .untilAtomic(atomic, org.hamcrest.core.IsEqual.equalTo(1));

    }

    @Test
    public void testAsyncBrokenWithoutJSON() throws IOException, PubNubException {
        stubFor(get(urlPathEqualTo("/time/0"))
                .willReturn(aResponse().withBody("zimp")));
        final AtomicInteger atomic = new AtomicInteger(0);
        partialTime.async(new TimeCallback() {

            @Override
            public void onResponse(PNTimeResult result, PNStatus status) {
                if (status != null) {
                    atomic.incrementAndGet();
                }
            }
        });

        Awaitility.await().atMost(5, TimeUnit.SECONDS)
                .untilAtomic(atomic, org.hamcrest.core.IsEqual.equalTo(1));

    }

    @Test
    public void testAsyncBrokenWithout200() throws IOException, PubNubException {
        stubFor(get(urlPathEqualTo("/time/0"))
                .willReturn(aResponse().withBody("[14593046077243110]").withStatus(404)));
        final AtomicInteger atomic = new AtomicInteger(0);
        partialTime.async(new TimeCallback() {

            @Override
            public void onResponse(PNTimeResult result, PNStatus status) {
                if (status != null) {
                    atomic.incrementAndGet();
                }
            }

        });

        Awaitility.await().atMost(5, TimeUnit.SECONDS)
                .untilAtomic(atomic, org.hamcrest.core.IsEqual.equalTo(1));

    }

    @Test
    public void testIsAuthRequiredSuccessSync() throws IOException, PubNubException, InterruptedException {
        stubFor(get(urlPathEqualTo("/time/0"))
                .willReturn(aResponse().withBody("[14593046077243110]")));

        pubnub.getConfiguration().setAuthKey("myKey");
        PNTimeResult response = partialTime.sync();
        assertTrue(response.getTimetoken().equals(14593046077243110L));

        List<LoggedRequest> requests = findAll(getRequestedFor(urlMatching("/.*")));
        assertEquals(1, requests.size());
    }

}
