<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/placeType.css" />
<div id="placeTypeAddDiv">
	<form id="placeTypeAddForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">会议室类型名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="placeType_placeTypeName" name="placeType.placeTypeName" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">会议室类型说明:</span>
			<span class="inputControl">
				<textarea id="placeType_placeTypeDesc" name="placeType.placeTypeDesc" rows="6" cols="80"></textarea>

			</span>

		</div>
		<div class="operation">
			<a id="placeTypeAddButton" class="easyui-linkbutton">添加</a>
			<a id="placeTypeClearButton" class="easyui-linkbutton">重填</a>
		</div> 
	</form>
</div>
<script src="${pageContext.request.contextPath}/PlaceType/js/placeType_add.js"></script> 
