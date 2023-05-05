$(function () {
	$.ajax({
		url : "PlaceType/" + $("#placeType_placeTypeId_edit").val() + "/update",
		type : "get",
		data : {
			//placeTypeId : $("#placeType_placeTypeId_edit").val(),
		},
		beforeSend : function () {
			$.messager.progress({
				text : "正在获取中...",
			});
		},
		success : function (placeType, response, status) {
			$.messager.progress("close");
			if (placeType) { 
				$("#placeType_placeTypeId_edit").val(placeType.placeTypeId);
				$("#placeType_placeTypeId_edit").validatebox({
					required : true,
					missingMessage : "请输入会议室类型id",
					editable: false
				});
				$("#placeType_placeTypeName_edit").val(placeType.placeTypeName);
				$("#placeType_placeTypeName_edit").validatebox({
					required : true,
					missingMessage : "请输入会议室类型名称",
				});
				$("#placeType_placeTypeDesc_edit").val(placeType.placeTypeDesc);
				$("#placeType_placeTypeDesc_edit").validatebox({
					required : true,
					missingMessage : "请输入会议室类型说明",
				});
			} else {
				$.messager.alert("获取失败！", "未知错误导致失败，请重试！", "warning");
				$(".messager-window").css("z-index",10000);
			}
		}
	});

	$("#placeTypeModifyButton").click(function(){ 
		if ($("#placeTypeEditForm").form("validate")) {
			$("#placeTypeEditForm").form({
			    url:"PlaceType/" +  $("#placeType_placeTypeId_edit").val() + "/update",
			    onSubmit: function(){
					if($("#placeTypeEditForm").form("validate"))  {
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
			$("#placeTypeEditForm").submit();
		} else {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
			$(".messager-window").css("z-index",10000);
		}
	});
});
