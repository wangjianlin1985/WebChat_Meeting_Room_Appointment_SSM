package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;
import com.client.utils.JsonUtils;
import com.client.utils.SessionConsts;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Place {
    /*会议室id*/
    private Integer placeId;
    public Integer getPlaceId(){
        return placeId;
    }
    public void setPlaceId(Integer placeId){
        this.placeId = placeId;
    }

    /*会议室类型*/
    private PlaceType placeTypeObj;
    public PlaceType getPlaceTypeObj() {
        return placeTypeObj;
    }
    public void setPlaceTypeObj(PlaceType placeTypeObj) {
        this.placeTypeObj = placeTypeObj;
    }

    /*会议室名称*/
    @NotEmpty(message="会议室名称不能为空")
    private String placeName;
    public String getPlaceName() {
        return placeName;
    }
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    /*会议室照片*/
    private String placePhoto;
    public String getPlacePhoto() {
        return placePhoto;
    }
    public void setPlacePhoto(String placePhoto) {
        this.placePhoto = placePhoto;
    }

    private String placePhotoUrl;
    public void setPlacePhotoUrl(String placePhotoUrl) {
		this.placePhotoUrl = placePhotoUrl;
	}
	public String getPlacePhotoUrl() {
		return  SessionConsts.BASE_URL + placePhoto;
	}
    /*容纳人数*/
    @NotNull(message="必须输入容纳人数")
    private Integer personNum;
    public Integer getPersonNum() {
        return personNum;
    }
    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    /*会议室位置*/
    @NotEmpty(message="会议室位置不能为空")
    private String placeLocation;
    public String getPlaceLocation() {
        return placeLocation;
    }
    public void setPlaceLocation(String placeLocation) {
        this.placeLocation = placeLocation;
    }

    /*会议室单价*/
    @NotNull(message="必须输入会议室单价")
    private Float price;
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    /*详细介绍*/
    @NotEmpty(message="详细介绍不能为空")
    private String placeDesc;
    public String getPlaceDesc() {
        return placeDesc;
    }
    public void setPlaceDesc(String placeDesc) {
        this.placeDesc = placeDesc;
    }

    /*发布时间*/
    @NotEmpty(message="发布时间不能为空")
    private String addTime;
    public String getAddTime() {
        return addTime;
    }
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @JsonIgnore
    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonPlace=new JSONObject(); 
		jsonPlace.accumulate("placeId", this.getPlaceId());
		jsonPlace.accumulate("placeTypeObj", this.getPlaceTypeObj().getPlaceTypeName());
		jsonPlace.accumulate("placeTypeObjPri", this.getPlaceTypeObj().getPlaceTypeId());
		jsonPlace.accumulate("placeName", this.getPlaceName());
		jsonPlace.accumulate("placePhoto", this.getPlacePhoto());
		jsonPlace.accumulate("personNum", this.getPersonNum());
		jsonPlace.accumulate("placeLocation", this.getPlaceLocation());
		jsonPlace.accumulate("price", this.getPrice());
		jsonPlace.accumulate("placeDesc", this.getPlaceDesc());
		jsonPlace.accumulate("addTime", this.getAddTime().length()>19?this.getAddTime().substring(0,19):this.getAddTime());
		return jsonPlace;
    }

    @Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}