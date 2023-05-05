<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/place.css" />
<div id="placeAddDiv">
	<form id="placeAddForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">会议室类型:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="place_placeTypeObj_placeTypeId" name="place.placeTypeObj.placeTypeId" style="width: auto"/>
			</span>
		</div>
		<div>
			<span class="label">会议室名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="place_placeName" name="place.placeName" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">会议室照片:</span>
			<span class="inputControl">
				<input id="placePhotoFile" name="placePhotoFile" type="file" size="50" />
			</span>
		</div>
		<div>
			<span class="label">容纳人数:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="place_personNum" name="place.personNum" style="width:80px" />

			</span>

		</div>
		<div>
			<span class="label">会议室位置:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="place_placeLocation" name="place.placeLocation" style="width:200px" />

			</span>

		</div>
		<div>
			<span class="label">会议室单价:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="place_price" name="place.price" style="width:80px" />

			</span>

		</div>
		<div>
			<span class="label">详细介绍:</span>
			<span class="inputControl">
				<textarea id="place_placeDesc" name="place.placeDesc" rows="6" cols="80"></textarea>

			</span>

		</div>
		<div>
			<span class="label">发布时间:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="place_addTime" name="place.addTime" />

			</span>

		</div>
		<div class="operation">
			<a id="placeAddButton" class="easyui-linkbutton">添加</a>
			<a id="placeClearButton" class="easyui-linkbutton">重填</a>
		</div> 
	</form>
</div>
<script src="${pageContext.request.contextPath}/Place/js/place_add.js"></script> 
