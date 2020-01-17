$(function(){
	layui.use('table',function() {
		table = layui.table;
		 //table 实例
							table.render({
								elem : '#demo',
								height : 420,
								url : '/item/itemJson' ,//数据接口
								title : '商品表',
								page : true ,//开启分页
								toolbar : '#toolbarDemo' ,//开启工具栏，此处显示默认图标，可以自定义模板，详见文档
								id : "reloadTable",
								defaultToolbar : [ 'filter', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
									layEvent : 'LAYTABLE_TIPS',
									icon : 'layui-icon-tips'
								} ],
								
								cols : [ [ //表头
								           {type: 'checkbox', fixed: 'left'}
								           ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'}
								           ,{field: 'title', title: '商品名称', width:80}
								           ,{field: 'sellPoint', title: '描述', width: 90,  totalRow: true}
								           ,{field: 'price', title: '价格', width:80}
								           ,{field: 'num', title: '数量', width: 80,  totalRow: true}
								           ,{field: 'barcode', title: 'barcode', width:150} 
								           ,{field: 'image', title: '图片路径', width: 200}
								           ,{field: 'cid', title: 'cid', width: 100}
								           ,{field: 'status', title: '状态', width: 100 
								        	   ,templet:function(item){
								        	    	  if(item.status=='1'){
								        	    		  return "上架";
								        	    	  }else if(item.status=='2'){
								        	    		  return "下架";
								        	    	  }
								           		}
								           }		
								           ,{field: 'created', title: '创建时间', templet:"<div>{{layui.util.toDateString(d.created,'yyyy-MM-dd HH:mm:ss')}}</div>" , width: 135, totalRow: true}
								           ,{field: 'updated', title: '修改时间', templet:"<div>{{layui.util.toDateString(d.updated,'yyyy-MM-dd HH:mm:ss')}}</div>" , width: 100}
								           ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
								 ] ]
							});

							//监听头工具栏事件
							table.on('toolbar(iteToolBar)',
											function(obj) {
												var checkStatus = table
														.checkStatus(obj.config.id); //获取选中的数据
												switch (obj.event) {
												
												case 'findTbItem':
													var data = checkStatus.data;
											    	
											    	$.ajax({
											    		   type: "post",
											    		   url: "/item/findTbItem",
											    		   contentType: "application/json;charset=utf-8",
											    		   data:JSON.stringify(data),
											    		   dataType: "json",
											    		   success: function(message){
											    			   if(message.status==200){
												            	   
												            	   table.reload('demo',{  });
												               }else{
												            	   layer.alert(message.msg);
												               }
											    		   }
											    		});
												      break;
												
												case 'deleteItem':
													var data = checkStatus.data;
											    	
											    	$.ajax({
											    		   type: "post",
											    		   url: "/item/deleteItem",
											    		   contentType: "application/json;charset=utf-8",
											    		   data:JSON.stringify(data),
											    		   dataType: "json",
											    		   success: function(message){
											    			   if(message.status==200){
												            	   layer.alert('删除商品成功');
												            	   table.reload('demo',{  });
												               }else{
												            	   layer.alert(message.msg);
												               }
											    		   }
											    		});
												      break;
												    case 'upItem':
													        var data = checkStatus.data;
													             $.ajax({
														            type: "POST",
														            url: "/item/upItem",
														            contentType: "application/json;charset=utf-8",
														            data:JSON.stringify(data),
														            dataType: "json",
														            success:function (message) {
														               if(message.status==200){
														            	   layer.alert(message.msg);
														            	   table.reload('demo',{  });
														               }else{
														            	   layer.alert(message.msg);
														               }
														            }
														        });
													      break;
												    case 'downItem':
												        var data = checkStatus.data;
												             $.ajax({
													            type: "POST",
													            url: "/item/downItem",
													            contentType: "application/json;charset=utf-8",
													            data:JSON.stringify(data),
													            dataType: "json",
													            success:function (message) {
													               if(message.status==200){
													            	   layer.alert(message.msg);
													            	   table.reload('demo',{  });
													               }else{
													            	   layer.alert(message.msg);
													               }
													            }
													        });
												      break;
												    };
												  });
											});
									})
											
	
	$("#search").click(function() {
	var titleVal = $("#searchTitile").val();
	var priceMinVal = $("#priceMin").val();
	var priceMaxVal = $("#priceMax").val();
	var cidContentVal = $("#cidContent").val();
	table.reload('reloadTable', {
		url : '/item/findTbItem',
		method : "post",
		where : {
			title : titleVal,
			priceMin : priceMinVal,
			priceMax : priceMaxVal,
			cId : cidContentVal
		},
		page : {
			curr : 1
		}
	});
	$("#searchTitile").val("");
	$("#priceMin").val("");
	$("#priceMax").val("");
	$("#cidContent").val("");
	$("#selectCid").val("");
})

$("#selectCid").click(function() {
	layer.open({
		type: 2,
		title: '商品分类选择',
		shadeClose: true,
		shade: 0.8,
		area: ['380px', '90%'],
	    content: '/jsp/showZtree.jsp', 
		btn: ['确定','取消'], //按钮
		success:function (layero,index) {
		     var iframe = window['layui-layer-iframe' + index];
		     iframe.child('showItem.jsp');
		}
	}); 
})
