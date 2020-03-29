
package com.samsapp.models.primePayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("newLoginId")
    @Expose
    private String newLoginId;
    @SerializedName("newCType")
    @Expose
    private String newCType;


    public String getTotcount() {
        return totcount;
    }

    public void setTotcount(String totcount) {
        this.totcount = totcount;
    }

    @SerializedName("totcount")
    @Expose
    private String totcount;
    @SerializedName("Prime")
    @Expose
    private String prime;
    @SerializedName("Prime_End_Date")
    @Expose
    private String primeEndDate;

    public String getNewLoginId() {
        return newLoginId;
    }

    public void setNewLoginId(String newLoginId) {
        this.newLoginId = newLoginId;
    }

    public String getNewCType() {
        return newCType;
    }

    public void setNewCType(String newCType) {
        this.newCType = newCType;
    }

    public String getPrime() {
        return prime;
    }

    public void setPrime(String prime) {
        this.prime = prime;
    }

    public String getPrimeEndDate() {
        return primeEndDate;
    }

    public void setPrimeEndDate(String primeEndDate) {
        this.primeEndDate = primeEndDate;
    }

}
