package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.OrderInfo;

public interface OrderInfoMapper {
	/*添加预约订单信息*/
	public void addOrderInfo(OrderInfo orderInfo) throws Exception;

	/*按照查询条件分页查询预约订单记录*/
	public ArrayList<OrderInfo> queryOrderInfo(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有预约订单记录*/
	public ArrayList<OrderInfo> queryOrderInfoList(@Param("where") String where) throws Exception;

	/*按照查询条件的预约订单记录数*/
	public int queryOrderInfoCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条预约订单记录*/
	public OrderInfo getOrderInfo(int orderNo) throws Exception;

	/*更新预约订单记录*/
	public void updateOrderInfo(OrderInfo orderInfo) throws Exception;

	/*删除预约订单记录*/
	public void deleteOrderInfo(int orderNo) throws Exception;

}
