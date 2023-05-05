package com.chengxusheji.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.chengxusheji.utils.ExportExcelUtil;
import com.chengxusheji.utils.UserException;
import com.chengxusheji.service.PlaceService;
import com.chengxusheji.po.Place;
import com.chengxusheji.service.PlaceTypeService;
import com.chengxusheji.po.PlaceType;

//Place管理控制层
@Controller
@RequestMapping("/Place")
public class PlaceController extends BaseController {

    /*业务层对象*/
    @Resource PlaceService placeService;

    @Resource PlaceTypeService placeTypeService;
	@InitBinder("placeTypeObj")
	public void initBinderplaceTypeObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("placeTypeObj.");
	}
	@InitBinder("place")
	public void initBinderPlace(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("place.");
	}
	/*跳转到添加Place视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new Place());
		/*查询所有的PlaceType信息*/
		List<PlaceType> placeTypeList = placeTypeService.queryAllPlaceType();
		request.setAttribute("placeTypeList", placeTypeList);
		return "Place_add";
	}

	/*客户端ajax方式提交添加会议室信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated Place place, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
		try {
			place.setPlacePhoto(this.handlePhotoUpload(request, "placePhotoFile"));
		} catch(UserException ex) {
			message = "图片格式不正确！";
			writeJsonResponse(response, success, message);
			return ;
		}
        placeService.addPlace(place);
        message = "会议室添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	/*ajax方式按照查询条件分页查询会议室信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute("placeTypeObj") PlaceType placeTypeObj,String placeName,String placeLocation,String addTime,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if (placeName == null) placeName = "";
		if (placeLocation == null) placeLocation = "";
		if (addTime == null) addTime = "";
		if(rows != 0)placeService.setRows(rows);
		List<Place> placeList = placeService.queryPlace(placeTypeObj, placeName, placeLocation, addTime, page);
	    /*计算总的页数和总的记录数*/
	    placeService.queryTotalPageAndRecordNumber(placeTypeObj, placeName, placeLocation, addTime);
	    /*获取到总的页码数目*/
	    int totalPage = placeService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = placeService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(Place place:placeList) {
			JSONObject jsonPlace = place.getJsonObject();
			jsonArray.put(jsonPlace);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询会议室信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<Place> placeList = placeService.queryAllPlace();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(Place place:placeList) {
			JSONObject jsonPlace = new JSONObject();
			jsonPlace.accumulate("placeId", place.getPlaceId());
			jsonPlace.accumulate("placeName", place.getPlaceName());
			jsonArray.put(jsonPlace);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询会议室信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(@ModelAttribute("placeTypeObj") PlaceType placeTypeObj,String placeName,String placeLocation,String addTime,Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (placeName == null) placeName = "";
		if (placeLocation == null) placeLocation = "";
		if (addTime == null) addTime = "";
		List<Place> placeList = placeService.queryPlace(placeTypeObj, placeName, placeLocation, addTime, currentPage);
	    /*计算总的页数和总的记录数*/
	    placeService.queryTotalPageAndRecordNumber(placeTypeObj, placeName, placeLocation, addTime);
	    /*获取到总的页码数目*/
	    int totalPage = placeService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = placeService.getRecordNumber();
	    request.setAttribute("placeList",  placeList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("placeTypeObj", placeTypeObj);
	    request.setAttribute("placeName", placeName);
	    request.setAttribute("placeLocation", placeLocation);
	    request.setAttribute("addTime", addTime);
	    List<PlaceType> placeTypeList = placeTypeService.queryAllPlaceType();
	    request.setAttribute("placeTypeList", placeTypeList);
		return "Place/place_frontquery_result"; 
	}

     /*前台查询Place信息*/
	@RequestMapping(value="/{placeId}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable Integer placeId,Model model,HttpServletRequest request) throws Exception {
		/*根据主键placeId获取Place对象*/
        Place place = placeService.getPlace(placeId);

        List<PlaceType> placeTypeList = placeTypeService.queryAllPlaceType();
        request.setAttribute("placeTypeList", placeTypeList);
        request.setAttribute("place",  place);
        return "Place/place_frontshow";
	}

	/*ajax方式显示会议室修改jsp视图页*/
	@RequestMapping(value="/{placeId}/update",method=RequestMethod.GET)
	public void update(@PathVariable Integer placeId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键placeId获取Place对象*/
        Place place = placeService.getPlace(placeId);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonPlace = place.getJsonObject();
		out.println(jsonPlace.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新会议室信息*/
	@RequestMapping(value = "/{placeId}/update", method = RequestMethod.POST)
	public void update(@Validated Place place, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		String placePhotoFileName = this.handlePhotoUpload(request, "placePhotoFile");
		if(!placePhotoFileName.equals("upload/NoImage.jpg"))place.setPlacePhoto(placePhotoFileName); 


		try {
			placeService.updatePlace(place);
			message = "会议室更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "会议室更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除会议室信息*/
	@RequestMapping(value="/{placeId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer placeId,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  placeService.deletePlace(placeId);
	            request.setAttribute("message", "会议室删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "会议室删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条会议室记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String placeIds,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = placeService.deletePlaces(placeIds);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出会议室信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel(@ModelAttribute("placeTypeObj") PlaceType placeTypeObj,String placeName,String placeLocation,String addTime, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        if(placeName == null) placeName = "";
        if(placeLocation == null) placeLocation = "";
        if(addTime == null) addTime = "";
        List<Place> placeList = placeService.queryPlace(placeTypeObj,placeName,placeLocation,addTime);
        ExportExcelUtil ex = new ExportExcelUtil();
        String _title = "Place信息记录"; 
        String[] headers = { "会议室id","会议室类型","会议室名称","会议室照片","容纳人数","会议室位置","会议室单价","发布时间"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<placeList.size();i++) {
        	Place place = placeList.get(i); 
        	dataset.add(new String[]{place.getPlaceId() + "",place.getPlaceTypeObj().getPlaceTypeName(),place.getPlaceName(),place.getPlacePhoto(),place.getPersonNum() + "",place.getPlaceLocation(),place.getPrice() + "",place.getAddTime()});
        }
        /*
        OutputStream out = null;
		try {
			out = new FileOutputStream("C://output.xls");
			ex.exportExcel(title,headers, dataset, out);
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		OutputStream out = null;//创建一个输出流对象 
		try { 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"Place.xls");//filename是下载的xls的名，建议最好用英文 
			response.setContentType("application/msexcel;charset=UTF-8");//设置类型 
			response.setHeader("Pragma","No-cache");//设置头 
			response.setHeader("Cache-Control","no-cache");//设置头 
			response.setDateHeader("Expires", 0);//设置日期头  
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			ex.exportExcel(rootPath,_title,headers, dataset, out);
			out.flush();
		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{
			try{
				if(out!=null){ 
					out.close(); 
				}
			}catch(IOException e){ 
				e.printStackTrace(); 
			} 
		}
    }
}
