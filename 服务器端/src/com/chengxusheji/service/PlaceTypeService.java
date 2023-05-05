package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.PlaceType;

import com.chengxusheji.mapper.PlaceTypeMapper;
@Service
public class PlaceTypeService {

	@Resource PlaceTypeMapper placeTypeMapper;
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

    /*添加会议室类型记录*/
    public void addPlaceType(PlaceType placeType) throws Exception {
    	placeTypeMapper.addPlaceType(placeType);
    }

    /*按照查询条件分页查询会议室类型记录*/
    public ArrayList<PlaceType> queryPlaceType(int currentPage) throws Exception { 
     	String where = "where 1=1";
    	int startIndex = (currentPage-1) * this.rows;
    	return placeTypeMapper.queryPlaceType(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<PlaceType> queryPlaceType() throws Exception  { 
     	String where = "where 1=1";
    	return placeTypeMapper.queryPlaceTypeList(where);
    }

    /*查询所有会议室类型记录*/
    public ArrayList<PlaceType> queryAllPlaceType()  throws Exception {
        return placeTypeMapper.queryPlaceTypeList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber() throws Exception {
     	String where = "where 1=1";
        recordNumber = placeTypeMapper.queryPlaceTypeCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取会议室类型记录*/
    public PlaceType getPlaceType(int placeTypeId) throws Exception  {
        PlaceType placeType = placeTypeMapper.getPlaceType(placeTypeId);
        return placeType;
    }

    /*更新会议室类型记录*/
    public void updatePlaceType(PlaceType placeType) throws Exception {
        placeTypeMapper.updatePlaceType(placeType);
    }

    /*删除一条会议室类型记录*/
    public void deletePlaceType (int placeTypeId) throws Exception {
        placeTypeMapper.deletePlaceType(placeTypeId);
    }

    /*删除多条会议室类型信息*/
    public int deletePlaceTypes (String placeTypeIds) throws Exception {
    	String _placeTypeIds[] = placeTypeIds.split(",");
    	for(String _placeTypeId: _placeTypeIds) {
    		placeTypeMapper.deletePlaceType(Integer.parseInt(_placeTypeId));
    	}
    	return _placeTypeIds.length;
    }
}
