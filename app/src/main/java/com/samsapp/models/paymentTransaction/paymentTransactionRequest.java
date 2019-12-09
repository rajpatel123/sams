package com.samsapp.models.paymentTransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class paymentTransactionRequest {

    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("txnid")
    @Expose
    String txnid;
    @SerializedName("orderId")
    @Expose
    String orderId;
    @SerializedName("banktxnid")
    @Expose
    String banktxnid;
    @SerializedName("txnAmount")
    @Expose
    String txnAmount;
    @SerializedName("currency")
    @Expose
    String currency;
    @SerializedName("status")
    @Expose
    String status;
    @SerializedName("response")
    @Expose
    String response;
    @SerializedName("resMSG")
    @Expose
    String resMSG;
    @SerializedName("date")
    @Expose
    String date;
    @SerializedName("gatewayName")
    @Expose
    String gatewayName;
    @SerializedName("bankName")
    @Expose
    String bankName;
    @SerializedName("checksum")
    @Expose
    String checksum;
    @SerializedName("payMode")
    @Expose
    String payMode;
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("orderNo")
    @Expose
    String orderNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTxnid() {
        return txnid;
    }

    public void setTxnid(String txnid) {
        this.txnid = txnid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBanktxnid() {
        return banktxnid;
    }

    public void setBanktxnid(String banktxnid) {
        this.banktxnid = banktxnid;
    }

    public String getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(String txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResMSG() {
        return resMSG;
    }

    public void setResMSG(String resMSG) {
        this.resMSG = resMSG;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


}
