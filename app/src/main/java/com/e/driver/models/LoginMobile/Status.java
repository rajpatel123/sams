package com.e.driver.models.LoginMobile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

@SerializedName("StatusType")
@Expose
private String statusType;

public String getStatusType() {
return statusType;
}

public void setStatusType(String statusType) {
this.statusType = statusType;
}

}