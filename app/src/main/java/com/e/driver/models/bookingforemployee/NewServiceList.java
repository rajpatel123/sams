package com.e.driver.models.bookingforemployee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewServiceList {

@SerializedName("order_no")
@Expose
private String orderNo;
@SerializedName("service_name")
@Expose
private String serviceName;
@SerializedName("cust_name")
@Expose
private String custName;
@SerializedName("cust_address")
@Expose
private String custAddress;
@SerializedName("cust_landmark")
@Expose
private String custLandmark;

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

public String getCustName() {
return custName;
}

public void setCustName(String custName) {
this.custName = custName;
}

public String getCustAddress() {
return custAddress;
}

public void setCustAddress(String custAddress) {
this.custAddress = custAddress;
}

public String getCustLandmark() {
return custLandmark;
}

public void setCustLandmark(String custLandmark) {
this.custLandmark = custLandmark;
}

}