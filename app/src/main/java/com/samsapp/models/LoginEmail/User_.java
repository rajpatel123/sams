package com.samsapp.models.LoginEmail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User_ {

@SerializedName("User_Name")
@Expose
private String userName;
@SerializedName("User_Email")
@Expose
private String userEmail;
@SerializedName("Role_ID")
@Expose
private String roleID;
@SerializedName("Login_ID")
@Expose
private String loginID;
@SerializedName("Customer_Type ID")
@Expose
private String customerTypeID;
@SerializedName("Prime_End_Date")
@Expose
private String primeEndDate;
@SerializedName("Total_Count")
@Expose
private String totalCount;
@SerializedName("Mobile_No")
@Expose
private String mobileNo;
@SerializedName("Address")
@Expose
private String address;
@SerializedName("Landmark")
@Expose
private String landmark;
@SerializedName("Pincode")
@Expose
private String pincode;
@SerializedName("Prime")
@Expose
private String prime;
@SerializedName("Prime_Membership_Price")
@Expose
private String primeMembershipPrice;
@SerializedName("Prime_Membership_Discount")
@Expose
private String primeMembershipDiscount;
@SerializedName("Role_Name")
@Expose
private String roleName;

public String getUserName() {
return userName;
}

public void setUserName(String userName) {
this.userName = userName;
}

public String getUserEmail() {
return userEmail;
}

public void setUserEmail(String userEmail) {
this.userEmail = userEmail;
}

public String getRoleID() {
return roleID;
}

public void setRoleID(String roleID) {
this.roleID = roleID;
}

public String getLoginID() {
return loginID;
}

public void setLoginID(String loginID) {
this.loginID = loginID;
}

public String getCustomerTypeID() {
return customerTypeID;
}

public void setCustomerTypeID(String customerTypeID) {
this.customerTypeID = customerTypeID;
}

public String getPrimeEndDate() {
return primeEndDate;
}

public void setPrimeEndDate(String primeEndDate) {
this.primeEndDate = primeEndDate;
}

public String getTotalCount() {
return totalCount;
}

public void setTotalCount(String totalCount) {
this.totalCount = totalCount;
}

public String getMobileNo() {
return mobileNo;
}

public void setMobileNo(String mobileNo) {
this.mobileNo = mobileNo;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getLandmark() {
return landmark;
}

public void setLandmark(String landmark) {
this.landmark = landmark;
}

public String getPincode() {
return pincode;
}

public void setPincode(String pincode) {
this.pincode = pincode;
}

public String getPrime() {
return prime;
}

public void setPrime(String prime) {
this.prime = prime;
}

public String getPrimeMembershipPrice() {
return primeMembershipPrice;
}

public void setPrimeMembershipPrice(String primeMembershipPrice) {
this.primeMembershipPrice = primeMembershipPrice;
}

public String getPrimeMembershipDiscount() {
return primeMembershipDiscount;
}

public void setPrimeMembershipDiscount(String primeMembershipDiscount) {
this.primeMembershipDiscount = primeMembershipDiscount;
}

public String getRoleName() {
return roleName;
}

public void setRoleName(String roleName) {
this.roleName = roleName;
}

}