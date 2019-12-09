
package com.samsapp.models.completedService;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("Complete_Service_List")
    @Expose
    private List<CompleteServiceList> completeServiceList = null;

    public List<CompleteServiceList> getCompleteServiceList() {
        return completeServiceList;
    }

    public void setCompleteServiceList(List<CompleteServiceList> completeServiceList) {
        this.completeServiceList = completeServiceList;
    }

}
