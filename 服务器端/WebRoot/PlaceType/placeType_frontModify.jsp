<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="com.chengxusheji.po.PlaceType" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    PlaceType placeType = (PlaceType)request.getAttribute("placeType");

%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
  <TITLE>修改会议室类型信息</TITLE>
  <link href="<%=basePath %>plugins/bootstrap.css" rel="stylesheet">
  <link href="<%=basePath %>plugins/bootstrap-dashen.css" rel="stylesheet">
  <link href="<%=basePath %>plugins/font-awesome.css" rel="stylesheet">
  <link href="<%=basePath %>plugins/animate.css" rel="stylesheet"> 
</head>
<body style="margin-top:70px;"> 
<div class="container">
<jsp:include page="../header.jsp"></jsp:include>
	<div class="col-md-9 wow fadeInLeft">
	<ul class="breadcrumb">
  		<li><a href="<%=basePath %>index.jsp">首页</a></li>
  		<li class="active">会议室类型信息修改</li>
	</ul>
		<div class="row"> 
      	<form class="form-horizontal" name="placeTypeEditForm" id="placeTypeEditForm" enctype="multipart/form-data" method="post"  class="mar_t15">
		  <div class="form-group">
			 <label for="placeType_placeTypeId_edit" class="col-md-3 text-right">会议室类型id:</label>
			 <div class="col-md-9"> 
			 	<input type="text" id="placeType_placeTypeId_edit" name="placeType.placeTypeId" class="form-control" placeholder="请输入会议室类型id" readOnly>
			 </div>
		  </div> 
		  <div class="form-group">
		  	 <label for="placeType_placeTypeName_edit" class="col-md-3 text-right">会议室类型名称:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="placeType_placeTypeName_edit" name="placeType.placeTypeName" class="form-control" placeholder="请输入会议室类型名称">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="placeType_placeTypeDesc_edit" class="col-md-3 text-right">会议室类型说明:</label>
		  	 <div class="col-md-9">
			    <textarea id="placeType_placeTypeDesc_edit" name="placeType.placeTypeDesc" rows="8" class="form-control" placeholder="请输入会议室类型说明"></textarea>
			 </div>
		  </div>
			  <div class="form-group">
			  	<span class="col-md-3""></span>
			  	<span onclick="ajaxPlaceTypeModify();" class="btn btn-primary bottom5 top5">修改</span>
			  </div>
		</form> 
	    <style>#placeTypeEditForm .form-group {margin-bottom:5px;}  </style>
      </div>
   </div>
</div>


<jsp:include page="../footer.jsp"></jsp:include>
<script src="<%=basePath %>plugins/jquery.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap.js"></script>
<script src="<%=basePath %>plugins/wow.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>plugins/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jsdate.js"></script>
<script>
var basePath = "<%=basePath%>";
/*弹出修改会议室类型界面并初始化数据*/
function placeTypeEdit(placeTypeId) {
	$.ajax({
		url :  basePath + "PlaceType/" + placeTypeId + "/update",
		type : "get",
		dataType: "json",
		success : function (placeType, response, status) {
			if (placeType) {
				$("#placeType_placeTypeId_edit").val(placeType.placeTypeId);
				$("#placeType_placeTypeName_edit").val(placeType.placeTypeName);
				$("#placeType_placeTypeDesc_edit").val(placeType.placeTypeDesc);
			} else {
				alert("获取信息失败！");
			}
		}
	});
}

/*ajax方式提交会议室类型信息表单给服务器端修改*/
function ajaxPlaceTypeModify() {
	$.ajax({
		url :  basePath + "PlaceType/" + $("#placeType_placeTypeId_edit").val() + "/update",
		type : "post",
		dataType: "json",
		data: new FormData($("#placeTypeEditForm")[0]),
		success : function (obj, response, status) {
            if(obj.success){
                alert("信息修改成功！");
                location.reload(true);
                location.href= basePath + "PlaceType/frontlist";
            }else{
                alert(obj.message);
            } 
		},
		processData: false,
		contentType: false,
	});
}

$(function(){
        /*小屏幕导航点击关闭菜单*/
        $('.navbar-collapse > a').click(function(){
            $('.navbar-collapse').collapse('hide');
        });
        new WOW().init();
    placeTypeEdit("<%=request.getParameter("placeTypeId")%>");
 })
 </script> 
</body>
</html>

