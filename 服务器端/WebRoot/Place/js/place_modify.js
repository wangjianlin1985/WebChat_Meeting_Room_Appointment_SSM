$(function () {
	$.ajax({
		url : "Place/" + $("#place_placeId_edit").val() + "/update",
		type : "get",
		data : {
			//placeId : $("#place_placeId_edit").val(),
		},
		beforeSend : function () {
			$.messager.progress({
				text : "正在获取中...",
			});
		},
		success : function (place, response, status) {
			$.messager.progress("close");
			if (place) { 
				$("#place_placeId_edit").val(place.placeId);
				$("#place_placeId_edit").validatebox({
					required : true,
					missingMessage : "请输入会议室id",
					editable: false
				});
				$("#place_placeTypeObj_placeTypeId_edit").combobox({
					url:"PlaceType/listAll",
					valueField:"placeTypeId",
					textField:"placeTypeName",
					panelHeight: "auto",
					editable: false, //不允许手动输入 
					onLoadSuccess: function () { //数据加载完毕事件
						$("#place_placeTypeObj_placeTypeId_edit").combobox("select", place.placeTypeObjPri);
						//var data = $("#place_placeTypeObj_placeTypeId_edit").combobox("getData"); 
						//if (data.length > 0) {
							//$("#place_placeTypeObj_placeTypeId_edit").combobox("select", data[0].placeTypeId);
						//}
					}
				});
				$("#place_placeName_edit").val(place.placeName);
				$("#place_placeName_edit").validatebox({
					required : true,
					missingMessage : "请输入会议室名称",
				});
				$("#place_placePhoto").val(place.placePhoto);
				$("#place_placePhotoImg").attr("src", place.placePhoto);
				$("#place_personNum_edit").val(place.personNum);
				$("#place_personNum_edit").validatebox({
					required : true,
					validType : "integer",
					missingMessage : "请输入容纳人数",
					invalidMessage : "容纳人数输入不对",
				});
				$("#place_placeLocation_edit").val(place.placeLocation);
				$("#place_placeLocation_edit").validatebox({
					required : true,
					missingMessage : "请输入会议室位置",
				});
				$("#place_price_edit").val(place.price);
				$("#place_price_edit").validatebox({
					required : true,
					validType : "number",
					missingMessage : "请输入会议室单价",
					invalidMessage : "会议室单价输入不对",
				});
				$("#place_placeDesc_edit").val(place.placeDesc);
				$("#place_placeDesc_edit").validatebox({
					required : true,
					missingMessage : "请输入详细介绍",
				});
				$("#place_addTime_edit").datetimebox({
					value: place.addTime,
					required: true,
					showSeconds: true,
				});
			} else {
				$.messager.alert("获取失败！", "未知错误导致失败，请重试！", "warning");
				$(".messager-window").css("z-index",10000);
			}
		}
	});

	$("#placeModifyButton").click(function(){ 
		if ($("#placeEditForm").form("validate")) {
			$("#placeEditForm").form({
			    url:"Place/" +  $("#place_placeId_edit").val() + "/update",
			    onSubmit: function(){
					if($("#placeEditForm").form("validate"))  {
	                	$.messager.progress({
							text : "正在提交数据中...",
						});
	                	return true;
	                } else {
	                    return false;
	                }
			    },
			    success:function(data){
			    	$.messager.progress("close");
                	var obj = jQuery.parseJSON(data);
                    if(obj.success){
                        $.messager.alert("消息","信息修改成功！");
                        $(".messager-window").css("z-index",10000);
                        //location.href="frontlist";
                    }else{
                        $.messager.alert("消息",obj.message);
                        $(".messager-window").css("z-index",10000);
                    } 
			    }
			});
			//提交表单
			$("#placeEditForm").submit();
		} else {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
			$(".messager-window").css("z-index",10000);
		}
	});
});
