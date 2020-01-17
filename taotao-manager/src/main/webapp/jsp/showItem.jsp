<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>商品展示页面</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/showItem.js"></script>
<style>
body {
	margin: 10px;
}

.demo-carousel {
	height: 200px;
	line-height: 200px;
	text-align: center;
}
</style>
</head>
<body>
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">商品名称</label>
			<div class="layui-input-inline">
				<input id="searchTitile" type="text" name="title"
					placeholder="请输入商品名称" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">范围</label>
			<div class="layui-input-inline" style="width: 100px;">
				<input type="text" id="priceMin" placeholder="￥:0"
					autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid">-</div>
			<div class="layui-input-inline" style="width: 100px;">
				<input type="text" id="priceMax" placeholder="￥:10000000"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">商品分类</label>
			<div class="layui-input-inline">
				<input id="selectCid" type="text"  placeholder="请选择商品分类"
					class="layui-input">
			</div>
			<input type="hidden" id="cidContent" name="cId" />
		</div>
		<button id="search" type="button" class="layui-btn layui-btn-radius">点击搜索</button>
	</div>
	
		
		
	
	<table class="layui-hide" id="demo" lay-filter="iteToolBar"></table>
	<div id="toolbarDemo" style="display: none;" class="layui-btn-container">
	    <button class="layui-btn layui-btn-sm" lay-event="deleteItem">选中删除</button>
	    <button class="layui-btn layui-btn-sm" lay-event="addItemItem">新增商品</button>
	    <button class="layui-btn layui-btn-sm" lay-event="updateItem">修改商品</button>
	    <button class="layui-btn layui-btn-sm" lay-event="upItem">上架</button>
	    <button class="layui-btn layui-btn-sm" lay-event="downItem">下架</button>
  	</div>
	<div style="display: none;" id="barDemo">
	  
	  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</div>

	

	

		
</body>
</html>
