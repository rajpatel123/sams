package com.samsapp.models.cities;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityList {

@SerializedName("city_id")
@Expose
private String cityId;
@SerializedName("city_name")
@Expose
private String cityName;

public String getCityId() {
return cityId;
}

public void setCityId(String cityId) {
this.cityId = cityId;
}

public String getCityName() {
return cityName;
}

public void setCityName(String cityName) {
this.cityName = cityName;
}

}


