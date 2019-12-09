package com.samsapp.models.bookings;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingHistory {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("order_no")
    @Expose
    private String orderNo;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("status_name")
    @Expose
    private String statusName;
    @SerializedName("booking_date")
    @Expose
    private String bookingDate;
    @SerializedName("time_slot_name")
    @Expose
    private String timeSlotName;
    @SerializedName("cust_email")
    @Expose
    private String custEmail;
    @SerializedName("mob_no")
    @Expose
    private String mobNo;
    @SerializedName("balance")
    @Expose
    private String balance;

    @SerializedName("order_transaction_id")
    @Expose
    private String order_transaction_id;

    public String getOrder_transaction_id() {
        return order_transaction_id;
    }

    public void setOrder_transaction_id(String order_transaction_id) {
        this.order_transaction_id = order_transaction_id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTimeSlotName() {
        return timeSlotName;
    }

    public void setTimeSlotName(String timeSlotName) {
        this.timeSlotName = timeSlotName;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

}


