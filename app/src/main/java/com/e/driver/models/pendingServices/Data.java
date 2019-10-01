
package com.e.driver.models.pendingServices;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("Pending Serivce List")
    @Expose
    private List<PendingSerivceList> pendingSerivceList = null;

    public List<PendingSerivceList> getPendingSerivceList() {
        return pendingSerivceList;
    }

    public void setPendingSerivceList(List<PendingSerivceList> pendingSerivceList) {
        this.pendingSerivceList = pendingSerivceList;
    }

}
