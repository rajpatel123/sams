
package com.e.driver.models.paymentTransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Packages {

    @SerializedName("com.android.org.conscrypt")
    @Expose
    private ComAndroidOrgConscrypt comAndroidOrgConscrypt;
    @SerializedName("android.security.net.config")
    @Expose
    private AndroidSecurityNetConfig androidSecurityNetConfig;

    public ComAndroidOrgConscrypt getComAndroidOrgConscrypt() {
        return comAndroidOrgConscrypt;
    }

    public void setComAndroidOrgConscrypt(ComAndroidOrgConscrypt comAndroidOrgConscrypt) {
        this.comAndroidOrgConscrypt = comAndroidOrgConscrypt;
    }

    public AndroidSecurityNetConfig getAndroidSecurityNetConfig() {
        return androidSecurityNetConfig;
    }

    public void setAndroidSecurityNetConfig(AndroidSecurityNetConfig androidSecurityNetConfig) {
        this.androidSecurityNetConfig = androidSecurityNetConfig;
    }

}
