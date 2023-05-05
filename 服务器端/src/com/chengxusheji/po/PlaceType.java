package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;
import com.client.utils.JsonUtils;
import com.client.utils.SessionConsts;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PlaceType {
    /*会议室类型id*/
    private Integer placeTypeId;
    public Integer getPlaceTypeId(){
        return placeTypeId;
    }
    public void setPlaceTypeId(Integer placeTypeId){
        this.placeTypeId = placeTypeId;
    }

    /*会议室类型名称*/
    @NotEmpty(message="会议室类型名称不能为空")
    private String placeTypeName;
    public String getPlaceTypeName() {
        return placeTypeName;
    }
    public void setPlaceTypeName(String placeTypeName) {
        this.placeTypeName = placeTypeName;
    }

    /*会议室类型说明*/
    @NotEmpty(message="会议室类型说明不能为空")
    private String placeTypeDesc;
    public String getPlaceTypeDesc() {
        return placeTypeDesc;
    }
    public void setPlaceTypeDesc(String placeTypeDesc) {
        this.placeTypeDesc = placeTypeDesc;
    }

    @JsonIgnore
    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonPlaceType=new JSONObject(); 
		jsonPlaceType.accumulate("placeTypeId", this.getPlaceTypeId());
		jsonPlaceType.accumulate("placeTypeName", this.getPlaceTypeName());
		jsonPlaceType.accumulate("placeTypeDesc", this.getPlaceTypeDesc());
		return jsonPlaceType;
    }

    @Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}