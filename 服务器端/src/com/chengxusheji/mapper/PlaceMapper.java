package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.Place;

public interface PlaceMapper {
	/*添加会议室信息*/
	public void addPlace(Place place) throws Exception;

	/*按照查询条件分页查询会议室记录*/
	public ArrayList<Place> queryPlace(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有会议室记录*/
	public ArrayList<Place> queryPlaceList(@Param("where") String where) throws Exception;

	/*按照查询条件的会议室记录数*/
	public int queryPlaceCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条会议室记录*/
	public Place getPlace(int placeId) throws Exception;

	/*更新会议室记录*/
	public void updatePlace(Place place) throws Exception;

	/*删除会议室记录*/
	public void deletePlace(int placeId) throws Exception;

}
