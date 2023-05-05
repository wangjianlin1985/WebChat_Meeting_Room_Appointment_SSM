<%@ page language="java"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/place.css" /> 

<div id="place_manage"></div>
<div id="place_manage_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="place_manage_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="place_manage_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="place_manage_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="place_manage_tool.redo();">取消选择</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="place_manage_tool.exportExcel();">导出到excel</a>
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
		<form id="placeQueryForm" method="post">
			会议室类型：<input class="textbox" type="text" id="placeTypeObj_placeTypeId_query" name="placeTypeObj.placeTypeId" style="width: auto"/>
			会议室名称：<input type="text" class="textbox" id="placeName" name="placeName" style="width:110px" />
			会议室位置：<input type="text" class="textbox" id="placeLocation" name="placeLocation" style="width:110px" />
			发布时间：<input type="text" id="addTime" name="addTime" class="easyui-datebox" editable="false" style="width:100px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="place_manage_tool.search();">查询</a>
		</form>	
	</div>
</div>

<div id="placeEditDiv">
	<form id="placeEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">会议室id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="place_placeId_edit" name="place.placeId" style="width:200px" />
			</span>
		</div>
		<div>
			<span class="label">会议室类型:</span>
			<span class="inputControl">
				<input class="textbox"  id="place_placeTypeObj_placeTypeId_edit" name="place.placeTypeObj.placeTypeId" style="width: auto"/>
			</span>
		</div>
		<div>
			<span class="label">会议室名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="place_placeName_edit" name="place.placeName" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">会议室照片:</span>
			<span class="inputControl">
				<img id="place_placePhotoImg" width="200px" border="0px"/><br/>
    			<input type="hidden" id="place_placePhoto" name="place.placePhoto"/>
				<input id="placePhotoFile" name="placePhotoFile" type="file" size="50" />
			</span>
		</div>
		<div>
			<span class="label">容纳人数:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="place_personNum_edit" name="place.personNum" style="width:80px" />

			</span>

		</div>
		<div>
			<span class="label">会议室位置:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="place_placeLocation_edit" name="place.placeLocation" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">会议室单价:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="place_price_edit" name="place.price" style="width:80px" />

			</span>

		</div>
		<div>
			<span class="label">详细介绍:</span>
			<span class="inputControl">
				<textarea id="place_placeDesc_edit" name="place.placeDesc" rows="8" cols="60"></textarea>

			</span>

		</div>
		<div>
			<span class="label">发布时间:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="place_addTime_edit" name="place.addTime" />

			</span>

		</div>
	</form>
</div>
<script type="text/javascript" src="Place/js/place_manage.js"></script> 
