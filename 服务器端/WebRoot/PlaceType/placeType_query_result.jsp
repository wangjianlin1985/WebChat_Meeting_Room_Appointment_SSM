<%@ page language="java"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/placeType.css" /> 

<div id="placeType_manage"></div>
<div id="placeType_manage_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="placeType_manage_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="placeType_manage_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="placeType_manage_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="placeType_manage_tool.redo();">取消选择</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="placeType_manage_tool.exportExcel();">导出到excel</a>
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
		<form id="placeTypeQueryForm" method="post">
		</form>	
	</div>
</div>

<div id="placeTypeEditDiv">
	<form id="placeTypeEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">会议室类型id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="placeType_placeTypeId_edit" name="placeType.placeTypeId" style="width:200px" />
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
	</form>
</div>
<script type="text/javascript" src="PlaceType/js/placeType_manage.js"></script> 
