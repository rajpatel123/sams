
package com.e.driver.models.paymentTransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MMap {

    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("CHECKSUMHASH")
    @Expose
    private String cHECKSUMHASH;
    @SerializedName("BANKNAME")
    @Expose
    private String bANKNAME;
    @SerializedName("ORDERID")
    @Expose
    private String oRDERID;
    @SerializedName("TXNAMOUNT")
    @Expose
    private String tXNAMOUNT;
    @SerializedName("TXNDATE")
    @Expose
    private String tXNDATE;
    @SerializedName("MID")
    @Expose
    private String mID;
    @SerializedName("TXNID")
    @Expose
    private String tXNID;
    @SerializedName("RESPCODE")
    @Expose
    private String rESPCODE;
    @SerializedName("PAYMENTMODE")
    @Expose
    private String pAYMENTMODE;
    @SerializedName("BANKTXNID")
    @Expose
    private String bANKTXNID;
    @SerializedName("CURRENCY")
    @Expose
    private String cURRENCY;
    @SerializedName("GATEWAYNAME")
    @Expose
    private String gATEWAYNAME;
    @SerializedName("RESPMSG")
    @Expose
    private String rESPMSG;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getCHECKSUMHASH() {
        return cHECKSUMHASH;
    }

    public void setCHECKSUMHASH(String cHECKSUMHASH) {
        this.cHECKSUMHASH = cHECKSUMHASH;
    }

    public String getBANKNAME() {
        return bANKNAME;
    }

    public void setBANKNAME(String bANKNAME) {
        this.bANKNAME = bANKNAME;
    }

    public String getORDERID() {
        return oRDERID;
    }

    public void setORDERID(String oRDERID) {
        this.oRDERID = oRDERID;
    }

    public String getTXNAMOUNT() {
        return tXNAMOUNT;
    }

    public void setTXNAMOUNT(String tXNAMOUNT) {
        this.tXNAMOUNT = tXNAMOUNT;
    }

    public String getTXNDATE() {
        return tXNDATE;
    }

    public void setTXNDATE(String tXNDATE) {
        this.tXNDATE = tXNDATE;
    }

    public String getMID() {
        return mID;
    }

    public void setMID(String mID) {
        this.mID = mID;
    }

    public String getTXNID() {
        return tXNID;
    }

    public void setTXNID(String tXNID) {
        this.tXNID = tXNID;
    }

    public String getRESPCODE() {
        return rESPCODE;
    }

    public void setRESPCODE(String rESPCODE) {
        this.rESPCODE = rESPCODE;
    }

    public String getPAYMENTMODE() {
        return pAYMENTMODE;
    }

    public void setPAYMENTMODE(String pAYMENTMODE) {
        this.pAYMENTMODE = pAYMENTMODE;
    }

    public String getBANKTXNID() {
        return bANKTXNID;
    }

    public void setBANKTXNID(String bANKTXNID) {
        this.bANKTXNID = bANKTXNID;
    }

    public String getCURRENCY() {
        return cURRENCY;
    }

    public void setCURRENCY(String cURRENCY) {
        this.cURRENCY = cURRENCY;
    }

    public String getGATEWAYNAME() {
        return gATEWAYNAME;
    }

    public void setGATEWAYNAME(String gATEWAYNAME) {
        this.gATEWAYNAME = gATEWAYNAME;
    }

    public String getRESPMSG() {
        return rESPMSG;
    }

    public void setRESPMSG(String rESPMSG) {
        this.rESPMSG = rESPMSG;
    }

}
