$(function () {
	$("#place_placeTypeObj_placeTypeId").combobox({
	    url:'PlaceType/listAll',
	    valueField: "placeTypeId",
	    textField: "placeTypeName",
	    panelHeight: "auto",
        editable: false, //不允许手动输入
        required : true,
        onLoadSuccess: function () { //数据加载完毕事件
            var data = $("#place_placeTypeObj_placeTypeId").combobox("getData"); 
            if (data.length > 0) {
                $("#place_placeTypeObj_placeTypeId").combobox("select", data[0].placeTypeId);
            }
        }
	});
	$("#place_placeName").validatebox({
		required : true, 
		missingMessage : '请输入会议室名称',
	});

	$("#place_personNum").validatebox({
		required : true,
		validType : "integer",
		missingMessage : '请输入容纳人数',
		invalidMessage : '容纳人数输入不对',
	});

	$("#place_placeLocation").validatebox({
		required : true, 
		missingMessage : '请输入会议室位置',
	});

	$("#place_price").validatebox({
		required : true,
		validType : "number",
		missingMessage : '请输入会议室单价',
		invalidMessage : '会议室单价输入不对',
	});

	$("#place_placeDesc").validatebox({
		required : true, 
		missingMessage : '请输入详细介绍',
	});

	$("#place_addTime").datetimebox({
	    required : true, 
	    showSeconds: true,
	    editable: false
	});

	//单击添加按钮
	$("#placeAddButton").click(function () {
		//验证表单 
		if(!$("#placeAddForm").form("validate")) {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
			$(".messager-window").css("z-index",10000);
		} else {
			$("#placeAddForm").form({
			    url:"Place/add",
			    onSubmit: function(){
					if($("#placeAddForm").form("validate"))  { 
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
                    //此处data={"Success":true}是字符串
                	var obj = jQuery.parseJSON(data); 
                    if(obj.success){ 
                        $.messager.alert("消息","保存成功！");
                        $(".messager-window").css("z-index",10000);
                        $("#placeAddForm").form("clear");
                    }else{
                        $.messager.alert("消息",obj.message);
                        $(".messager-window").css("z-index",10000);
                    }
			    }
			});
			//提交表单
			$("#placeAddForm").submit();
		}
	});

	//单击清空按钮
	$("#placeClearButton").click(function () { 
		$("#placeAddForm").form("clear"); 
	});
});
