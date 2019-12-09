package com.samsapp.models.paymentTransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class primePaymentTransaction {
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
    @SerializedName("cust_name")
    @Expose
    String cust_name;
    @SerializedName("cust_login_mob")
    @Expose
    String cust_login_mob;
    @SerializedName("cust_alter_mob")
    @Expose
    String cust_alter_mob;
    @SerializedName("cust_address")
    @Expose
    String cust_address;
    @SerializedName("cust_landmark")
    @Expose
    String cust_landmark;
    @SerializedName("cust_pincode")
    @Expose
    String cust_pincode;
    @SerializedName("city_id")
    @Expose
    String city_id;
    @SerializedName("state_id")
    @Expose
    String state_id;

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

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_login_mob() {
        return cust_login_mob;
    }

    public void setCust_login_mob(String cust_login_mob) {
        this.cust_login_mob = cust_login_mob;
    }

    public String getCust_alter_mob() {
        return cust_alter_mob;
    }

    public void setCust_alter_mob(String cust_alter_mob) {
        this.cust_alter_mob = cust_alter_mob;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public String getCust_landmark() {
        return cust_landmark;
    }

    public void setCust_landmark(String cust_landmark) {
        this.cust_landmark = cust_landmark;
    }

    public String getCust_pincode() {
        return cust_pincode;
    }

    public void setCust_pincode(String cust_pincode) {
        this.cust_pincode = cust_pincode;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }





}
