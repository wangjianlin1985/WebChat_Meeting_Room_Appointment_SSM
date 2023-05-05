package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.PlaceType;
import com.chengxusheji.po.Place;

import com.chengxusheji.mapper.PlaceMapper;
@Service
public class PlaceService {

	@Resource PlaceMapper placeMapper;
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

    /*添加会议室记录*/
    public void addPlace(Place place) throws Exception {
    	placeMapper.addPlace(place);
    }

    /*按照查询条件分页查询会议室记录*/
    public ArrayList<Place> queryPlace(PlaceType placeTypeObj,String placeName,String placeLocation,String addTime,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(null != placeTypeObj && placeTypeObj.getPlaceTypeId()!= null && placeTypeObj.getPlaceTypeId()!= 0)  where += " and t_place.placeTypeObj=" + placeTypeObj.getPlaceTypeId();
    	if(!placeName.equals("")) where = where + " and t_place.placeName like '%" + placeName + "%'";
    	if(!placeLocation.equals("")) where = where + " and t_place.placeLocation like '%" + placeLocation + "%'";
    	if(!addTime.equals("")) where = where + " and t_place.addTime like '%" + addTime + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return placeMapper.queryPlace(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<Place> queryPlace(PlaceType placeTypeObj,String placeName,String placeLocation,String addTime) throws Exception  { 
     	String where = "where 1=1";
    	if(null != placeTypeObj && placeTypeObj.getPlaceTypeId()!= null && placeTypeObj.getPlaceTypeId()!= 0)  where += " and t_place.placeTypeObj=" + placeTypeObj.getPlaceTypeId();
    	if(!placeName.equals("")) where = where + " and t_place.placeName like '%" + placeName + "%'";
    	if(!placeLocation.equals("")) where = where + " and t_place.placeLocation like '%" + placeLocation + "%'";
    	if(!addTime.equals("")) where = where + " and t_place.addTime like '%" + addTime + "%'";
    	return placeMapper.queryPlaceList(where);
    }

    /*查询所有会议室记录*/
    public ArrayList<Place> queryAllPlace()  throws Exception {
        return placeMapper.queryPlaceList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(PlaceType placeTypeObj,String placeName,String placeLocation,String addTime) throws Exception {
     	String where = "where 1=1";
    	if(null != placeTypeObj && placeTypeObj.getPlaceTypeId()!= null && placeTypeObj.getPlaceTypeId()!= 0)  where += " and t_place.placeTypeObj=" + placeTypeObj.getPlaceTypeId();
    	if(!placeName.equals("")) where = where + " and t_place.placeName like '%" + placeName + "%'";
    	if(!placeLocation.equals("")) where = where + " and t_place.placeLocation like '%" + placeLocation + "%'";
    	if(!addTime.equals("")) where = where + " and t_place.addTime like '%" + addTime + "%'";
        recordNumber = placeMapper.queryPlaceCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取会议室记录*/
    public Place getPlace(int placeId) throws Exception  {
        Place place = placeMapper.getPlace(placeId);
        return place;
    }

    /*更新会议室记录*/
    public void updatePlace(Place place) throws Exception {
        placeMapper.updatePlace(place);
    }

    /*删除一条会议室记录*/
    public void deletePlace (int placeId) throws Exception {
        placeMapper.deletePlace(placeId);
    }

    /*删除多条会议室信息*/
    public int deletePlaces (String placeIds) throws Exception {
    	String _placeIds[] = placeIds.split(",");
    	for(String _placeId: _placeIds) {
    		placeMapper.deletePlace(Integer.parseInt(_placeId));
    	}
    	return _placeIds.length;
    }
}
