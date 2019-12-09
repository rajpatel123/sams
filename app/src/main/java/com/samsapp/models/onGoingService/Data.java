
package com.samsapp.models.onGoingService;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("Ongoing_Service_List")
    @Expose
    private List<OngoingServiceList> ongoingServiceList = null;

    public List<OngoingServiceList> getOngoingServiceList() {
        return ongoingServiceList;
    }

    public void setOngoingServiceList(List<OngoingServiceList> ongoingServiceList) {
        this.ongoingServiceList = ongoingServiceList;
    }

}
