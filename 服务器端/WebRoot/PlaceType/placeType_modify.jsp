<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/placeType.css" />
<div id="placeType_editDiv">
	<form id="placeTypeEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">会议室类型id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="placeType_placeTypeId_edit" name="placeType.placeTypeId" value="<%=request.getParameter("placeTypeId") %>" style="width:200px" />
			</span>
		</div>

		<div>
			<span class="label">会议室类型名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="placeType_placeTypeName_edit" name="placeType.placeTypeName" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">会议室类型说明:</span>
			<span class="inputControl">
				<textarea id="placeType_placeTypeDesc_edit" name="placeType.placeTypeDesc" rows="8" cols="60"></textarea>

			</span>

		</div>
		<div class="operation">
			<a id="placeTypeModifyButton" class="easyui-linkbutton">更新</a> 
		</div>
	</form>
</div>
<script src="${pageContext.request.contextPath}/PlaceType/js/placeType_modify.js"></script> 
