package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.PlaceType;

public interface PlaceTypeMapper {
	/*添加会议室类型信息*/
	public void addPlaceType(PlaceType placeType) throws Exception;

	/*按照查询条件分页查询会议室类型记录*/
	public ArrayList<PlaceType> queryPlaceType(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有会议室类型记录*/
	public ArrayList<PlaceType> queryPlaceTypeList(@Param("where") String where) throws Exception;

	/*按照查询条件的会议室类型记录数*/
	public int queryPlaceTypeCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条会议室类型记录*/
	public PlaceType getPlaceType(int placeTypeId) throws Exception;

	/*更新会议室类型记录*/
	public void updatePlaceType(PlaceType placeType) throws Exception;

	/*删除会议室类型记录*/
	public void deletePlaceType(int placeTypeId) throws Exception;

}
