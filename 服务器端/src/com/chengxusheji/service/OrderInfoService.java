package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.UserInfo;
import com.chengxusheji.po.Place;
import com.chengxusheji.po.TimeInterval;
import com.chengxusheji.po.PayWay;
import com.chengxusheji.po.OrderInfo;

import com.chengxusheji.mapper.OrderInfoMapper;
@Service
public class OrderInfoService {

	@Resource OrderInfoMapper orderInfoMapper;
    /*每页显示记录数目*/
    private int rows = 10;;
    public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

    /*保存查询后总的页数*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*保存查询到的总记录数*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*添加预约订单记录*/
    public void addOrderInfo(OrderInfo orderInfo) throws Exception {
    	orderInfoMapper.addOrderInfo(orderInfo);
    }

    /*按照查询条件分页查询预约订单记录*/
    public ArrayList<OrderInfo> queryOrderInfo(UserInfo userObj,Place placeObj,String orderDate,TimeInterval intervalObj,PayWay payWayObj,String payAccount,String orderStateObj,String orderTime,String receiveName,String telephone,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(null != userObj &&  userObj.getUser_name() != null  && !userObj.getUser_name().equals(""))  where += " and t_orderInfo.userObj='" + userObj.getUser_name() + "'";
    	if(null != placeObj && placeObj.getPlaceId()!= null && placeObj.getPlaceId()!= 0)  where += " and t_orderInfo.placeObj=" + placeObj.getPlaceId();
    	if(!orderDate.equals("")) where = where + " and t_orderInfo.orderDate like '%" + orderDate + "%'";
    	if(null != intervalObj && intervalObj.getIntervalId()!= null && intervalObj.getIntervalId()!= 0)  where += " and t_orderInfo.intervalObj=" + intervalObj.getIntervalId();
    	if(null != payWayObj && payWayObj.getPayWayId()!= null && payWayObj.getPayWayId()!= 0)  where += " and t_orderInfo.payWayObj=" + payWayObj.getPayWayId();
    	if(!payAccount.equals("")) where = where + " and t_orderInfo.payAccount like '%" + payAccount + "%'";
    	if(!orderStateObj.equals("")) where = where + " and t_orderInfo.orderStateObj like '%" + orderStateObj + "%'";
    	if(!orderTime.equals("")) where = where + " and t_orderInfo.orderTime like '%" + orderTime + "%'";
    	if(!receiveName.equals("")) where = where + " and t_orderInfo.receiveName like '%" + receiveName + "%'";
    	if(!telephone.equals("")) where = where + " and t_orderInfo.telephone like '%" + telephone + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return orderInfoMapper.queryOrderInfo(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<OrderInfo> queryOrderInfo(UserInfo userObj,Place placeObj,String orderDate,TimeInterval intervalObj,PayWay payWayObj,String payAccount,String orderStateObj,String orderTime,String receiveName,String telephone) throws Exception  { 
     	String where = "where 1=1";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_orderInfo.userObj='" + userObj.getUser_name() + "'";
    	if(null != placeObj && placeObj.getPlaceId()!= null && placeObj.getPlaceId()!= 0)  where += " and t_orderInfo.placeObj=" + placeObj.getPlaceId();
    	if(!orderDate.equals("")) where = where + " and t_orderInfo.orderDate like '%" + orderDate + "%'";
    	if(null != intervalObj && intervalObj.getIntervalId()!= null && intervalObj.getIntervalId()!= 0)  where += " and t_orderInfo.intervalObj=" + intervalObj.getIntervalId();
    	if(null != payWayObj && payWayObj.getPayWayId()!= null && payWayObj.getPayWayId()!= 0)  where += " and t_orderInfo.payWayObj=" + payWayObj.getPayWayId();
    	if(!payAccount.equals("")) where = where + " and t_orderInfo.payAccount like '%" + payAccount + "%'";
    	if(!orderStateObj.equals("")) where = where + " and t_orderInfo.orderStateObj like '%" + orderStateObj + "%'";
    	if(!orderTime.equals("")) where = where + " and t_orderInfo.orderTime like '%" + orderTime + "%'";
    	if(!receiveName.equals("")) where = where + " and t_orderInfo.receiveName like '%" + receiveName + "%'";
    	if(!telephone.equals("")) where = where + " and t_orderInfo.telephone like '%" + telephone + "%'";
    	return orderInfoMapper.queryOrderInfoList(where);
    }

    /*查询所有预约订单记录*/
    public ArrayList<OrderInfo> queryAllOrderInfo()  throws Exception {
        return orderInfoMapper.queryOrderInfoList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(UserInfo userObj,Place placeObj,String orderDate,TimeInterval intervalObj,PayWay payWayObj,String payAccount,String orderStateObj,String orderTime,String receiveName,String telephone) throws Exception {
     	String where = "where 1=1";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_orderInfo.userObj='" + userObj.getUser_name() + "'";
    	if(null != placeObj && placeObj.getPlaceId()!= null && placeObj.getPlaceId()!= 0)  where += " and t_orderInfo.placeObj=" + placeObj.getPlaceId();
    	if(!orderDate.equals("")) where = where + " and t_orderInfo.orderDate like '%" + orderDate + "%'";
    	if(null != intervalObj && intervalObj.getIntervalId()!= null && intervalObj.getIntervalId()!= 0)  where += " and t_orderInfo.intervalObj=" + intervalObj.getIntervalId();
    	if(null != payWayObj && payWayObj.getPayWayId()!= null && payWayObj.getPayWayId()!= 0)  where += " and t_orderInfo.payWayObj=" + payWayObj.getPayWayId();
    	if(!payAccount.equals("")) where = where + " and t_orderInfo.payAccount like '%" + payAccount + "%'";
    	if(!orderStateObj.equals("")) where = where + " and t_orderInfo.orderStateObj like '%" + orderStateObj + "%'";
    	if(!orderTime.equals("")) where = where + " and t_orderInfo.orderTime like '%" + orderTime + "%'";
    	if(!receiveName.equals("")) where = where + " and t_orderInfo.receiveName like '%" + receiveName + "%'";
    	if(!telephone.equals("")) where = where + " and t_orderInfo.telephone like '%" + telephone + "%'";
        recordNumber = orderInfoMapper.queryOrderInfoCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取预约订单记录*/
    public OrderInfo getOrderInfo(int orderNo) throws Exception  {
        OrderInfo orderInfo = orderInfoMapper.getOrderInfo(orderNo);
        return orderInfo;
    }

    /*更新预约订单记录*/
    public void updateOrderInfo(OrderInfo orderInfo) throws Exception {
        orderInfoMapper.updateOrderInfo(orderInfo);
    }

    /*删除一条预约订单记录*/
    public void deleteOrderInfo (int orderNo) throws Exception {
        orderInfoMapper.deleteOrderInfo(orderNo);
    }

    /*删除多条预约订单信息*/
    public int deleteOrderInfos (String orderNos) throws Exception {
    	String _orderNos[] = orderNos.split(",");
    	for(String _orderNo: _orderNos) {
    		orderInfoMapper.deleteOrderInfo(Integer.parseInt(_orderNo));
    	}
    	return _orderNos.length;
    }
}
