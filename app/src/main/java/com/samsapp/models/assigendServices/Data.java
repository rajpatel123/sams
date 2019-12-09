
package com.samsapp.models.assigendServices;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("New_Service_List")
    @Expose
    private List<NewServiceList> newServiceList = null;

    public List<NewServiceList> getNewServiceList() {
        return newServiceList;
    }

    public void setNewServiceList(List<NewServiceList> newServiceList) {
        this.newServiceList = newServiceList;
    }

}
