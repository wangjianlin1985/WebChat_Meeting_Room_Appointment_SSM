package com.client.controller;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.chengxusheji.po.PlaceType;
import com.chengxusheji.service.PlaceTypeService;
import com.client.service.AuthService;
import com.client.utils.JsonResult;
import com.client.utils.JsonResultBuilder;
import com.client.utils.ReturnCode;

@RestController
@RequestMapping("/api/placeType") 
public class ApiPlaceTypeController {
	@Resource PlaceTypeService placeTypeService;
	@Resource AuthService authService;

	@InitBinder("placeType")
	public void initBinderPlaceType(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("placeType.");
	}

	/*客户端ajax方式添加会议室类型信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(@Validated PlaceType placeType, BindingResult br, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		if (br.hasErrors()) //验证输入参数
			return JsonResultBuilder.error(ReturnCode.INPUT_PARAM_ERROR);
        placeTypeService.addPlaceType(placeType); //添加到数据库
        return JsonResultBuilder.ok();
	}

	/*客户端ajax更新会议室类型信息*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(@Validated PlaceType placeType, BindingResult br, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		if (br.hasErrors())  //验证输入参数
			return JsonResultBuilder.error(ReturnCode.INPUT_PARAM_ERROR); 
        placeTypeService.updatePlaceType(placeType);  //更新记录到数据库
        return JsonResultBuilder.ok(placeTypeService.getPlaceType(placeType.getPlaceTypeId()));
	}

	/*ajax方式显示获取会议室类型详细信息*/
	@RequestMapping(value="/get/{placeTypeId}",method=RequestMethod.POST)
	public JsonResult getPlaceType(@PathVariable int placeTypeId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键placeTypeId获取PlaceType对象*/
        PlaceType placeType = placeTypeService.getPlaceType(placeTypeId); 
        return JsonResultBuilder.ok(placeType);
	}

	/*ajax方式删除会议室类型记录*/
	@RequestMapping(value="/delete/{placeTypeId}",method=RequestMethod.POST)
	public JsonResult deletePlaceType(@PathVariable int placeTypeId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		try {
			placeTypeService.deletePlaceType(placeTypeId);
			return JsonResultBuilder.ok();
		} catch (Exception ex) {
			return JsonResultBuilder.error(ReturnCode.FOREIGN_KEY_CONSTRAINT_ERROR);
		}
	}

	//客户端查询会议室类型信息
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public JsonResult list(Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if(rows != 0)placeTypeService.setRows(rows);
		List<PlaceType> placeTypeList = placeTypeService.queryPlaceType(page);
	    /*计算总的页数和总的记录数*/
	    placeTypeService.queryTotalPageAndRecordNumber();
	    /*获取到总的页码数目*/
	    int totalPage = placeTypeService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = placeTypeService.getRecordNumber();
	    HashMap<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap.put("totalPage", totalPage);
	    resultMap.put("list", placeTypeList);
	    return JsonResultBuilder.ok(resultMap);
	}

	//客户端ajax获取所有会议室类型
	@RequestMapping(value="/listAll",method=RequestMethod.POST)
	public JsonResult listAll() throws Exception{
		List<PlaceType> placeTypeList = placeTypeService.queryAllPlaceType(); 
		return JsonResultBuilder.ok(placeTypeList);
	}
}

