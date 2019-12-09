
package com.samsapp.models.paymentTransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComAndroidOrgConscrypt {

    @SerializedName("implTitle")
    @Expose
    private String implTitle;
    @SerializedName("implVendor")
    @Expose
    private String implVendor;
    @SerializedName("implVersion")
    @Expose
    private String implVersion;
    @SerializedName("pkgName")
    @Expose
    private String pkgName;
    @SerializedName("specTitle")
    @Expose
    private String specTitle;
    @SerializedName("specVendor")
    @Expose
    private String specVendor;
    @SerializedName("specVersion")
    @Expose
    private String specVersion;

    public String getImplTitle() {
        return implTitle;
    }

    public void setImplTitle(String implTitle) {
        this.implTitle = implTitle;
    }

    public String getImplVendor() {
        return implVendor;
    }

    public void setImplVendor(String implVendor) {
        this.implVendor = implVendor;
    }

    public String getImplVersion() {
        return implVersion;
    }

    public void setImplVersion(String implVersion) {
        this.implVersion = implVersion;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getSpecTitle() {
        return specTitle;
    }

    public void setSpecTitle(String specTitle) {
        this.specTitle = specTitle;
    }

    public String getSpecVendor() {
        return specVendor;
    }

    public void setSpecVendor(String specVendor) {
        this.specVendor = specVendor;
    }

    public String getSpecVersion() {
        return specVersion;
    }

    public void setSpecVersion(String specVersion) {
        this.specVersion = specVersion;
    }

}
