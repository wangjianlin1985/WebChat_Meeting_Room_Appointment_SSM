package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;
import com.client.utils.JsonUtils;
import com.client.utils.SessionConsts;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderInfo {
    /*订单编号*/
    private Integer orderNo;
    public Integer getOrderNo(){
        return orderNo;
    }
    public void setOrderNo(Integer orderNo){
        this.orderNo = orderNo;
    }

    /*下单用户*/
    private UserInfo userObj;
    public UserInfo getUserObj() {
        return userObj;
    }
    public void setUserObj(UserInfo userObj) {
        this.userObj = userObj;
    }

    /*预约会议室*/
    private Place placeObj;
    public Place getPlaceObj() {
        return placeObj;
    }
    public void setPlaceObj(Place placeObj) {
        this.placeObj = placeObj;
    }

    /*预定日期*/
    @NotEmpty(message="预定日期不能为空")
    private String orderDate;
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /*预约时段*/
    private TimeInterval intervalObj;
    public TimeInterval getIntervalObj() {
        return intervalObj;
    }
    public void setIntervalObj(TimeInterval intervalObj) {
        this.intervalObj = intervalObj;
    }

    /*订单总金额*/
    @NotNull(message="必须输入订单总金额")
    private Float totalMoney;
    public Float getTotalMoney() {
        return totalMoney;
    }
    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }

    /*支付方式*/
    private PayWay payWayObj;
    public PayWay getPayWayObj() {
        return payWayObj;
    }
    public void setPayWayObj(PayWay payWayObj) {
        this.payWayObj = payWayObj;
    }

    /*支付账号*/
    @NotEmpty(message="支付账号不能为空")
    private String payAccount;
    public String getPayAccount() {
        return payAccount;
    }
    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    /*订单状态*/
    @NotEmpty(message="订单状态不能为空")
    private String orderStateObj;
    public String getOrderStateObj() {
        return orderStateObj;
    }
    public void setOrderStateObj(String orderStateObj) {
        this.orderStateObj = orderStateObj;
    }

    /*下单时间*/
    private String orderTime;
    public String getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    /*收货人*/
    @NotEmpty(message="收货人不能为空")
    private String receiveName;
    public String getReceiveName() {
        return receiveName;
    }
    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    /*收货人电话*/
    @NotEmpty(message="收货人电话不能为空")
    private String telephone;
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /*收货人地址*/
    @NotEmpty(message="收货人地址不能为空")
    private String address;
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    /*订单备注*/
    private String orderMemo;
    public String getOrderMemo() {
        return orderMemo;
    }
    public void setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo;
    }

    @JsonIgnore
    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonOrderInfo=new JSONObject(); 
		jsonOrderInfo.accumulate("orderNo", this.getOrderNo());
		jsonOrderInfo.accumulate("userObj", this.getUserObj().getName());
		jsonOrderInfo.accumulate("userObjPri", this.getUserObj().getUser_name());
		jsonOrderInfo.accumulate("placeObj", this.getPlaceObj().getPlaceName());
		jsonOrderInfo.accumulate("placeObjPri", this.getPlaceObj().getPlaceId());
		jsonOrderInfo.accumulate("orderDate", this.getOrderDate().length()>19?this.getOrderDate().substring(0,19):this.getOrderDate());
		jsonOrderInfo.accumulate("intervalObj", this.getIntervalObj().getIntervalName());
		jsonOrderInfo.accumulate("intervalObjPri", this.getIntervalObj().getIntervalId());
		jsonOrderInfo.accumulate("totalMoney", this.getTotalMoney());
		jsonOrderInfo.accumulate("payWayObj", this.getPayWayObj().getPayWayName());
		jsonOrderInfo.accumulate("payWayObjPri", this.getPayWayObj().getPayWayId());
		jsonOrderInfo.accumulate("payAccount", this.getPayAccount());
		jsonOrderInfo.accumulate("orderStateObj", this.getOrderStateObj());
		jsonOrderInfo.accumulate("orderTime", this.getOrderTime());
		jsonOrderInfo.accumulate("receiveName", this.getReceiveName());
		jsonOrderInfo.accumulate("telephone", this.getTelephone());
		jsonOrderInfo.accumulate("address", this.getAddress());
		jsonOrderInfo.accumulate("orderMemo", this.getOrderMemo());
		return jsonOrderInfo;
    }

    @Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}