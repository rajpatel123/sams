package com.e.driver.models.LoginMobile;



import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpLoginResponse {

@SerializedName("Status")
@Expose
private List<Status> status = null;

public List<Status> getStatus() {
return status;
}

public void setStatus(List<Status> status) {
this.status = status;
}

}



