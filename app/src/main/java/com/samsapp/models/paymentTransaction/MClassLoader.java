
package com.samsapp.models.paymentTransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MClassLoader {

    @SerializedName("packages")
    @Expose
    private Packages packages;
    @SerializedName("proxyCache")
    @Expose
    private ProxyCache proxyCache;

    public Packages getPackages() {
        return packages;
    }

    public void setPackages(Packages packages) {
        this.packages = packages;
    }

    public ProxyCache getProxyCache() {
        return proxyCache;
    }

    public void setProxyCache(ProxyCache proxyCache) {
        this.proxyCache = proxyCache;
    }

}
