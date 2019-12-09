
package com.samsapp.models.paymentTransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionResponse {

    @SerializedName("mClassLoader")
    @Expose
    private MClassLoader mClassLoader;
    @SerializedName("mFlags")
    @Expose
    private Integer mFlags;
    @SerializedName("mMap")
    @Expose
    private MMap mMap;

    public MClassLoader getMClassLoader() {
        return mClassLoader;
    }

    public void setMClassLoader(MClassLoader mClassLoader) {
        this.mClassLoader = mClassLoader;
    }

    public Integer getMFlags() {
        return mFlags;
    }

    public void setMFlags(Integer mFlags) {
        this.mFlags = mFlags;
    }

    public MMap getMMap() {
        return mMap;
    }

    public void setMMap(MMap mMap) {
        this.mMap = mMap;
    }

}
