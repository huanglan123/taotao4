package com.cindy.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cindy.bean.ItemJs;

import com.cindy.bean.TbItem;
import com.cindy.common.TaoTaoResult;
import com.cindy.service.TbItemService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/item")
public class TbItemController {
	@Autowired
	private TbItemService tbItemService;
	@RequestMapping("/{itemId}")//接受参数
	@ResponseBody
	public TbItem findTbItemById(@PathVariable Long itemId){
		TbItem tbItem = tbItemService.findTbItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/itemJson")
    @ResponseBody
    public ItemJs itemJson(Integer page,Integer limit) {
		Integer i=tbItemService.findCount();
		List<TbItem> items = tbItemService.findTbItem(page,limit);
        System.out.println(items);
        //返回用户信息，要使用@ResponseBody将返回值转换为JSON
        ItemJs itemJson=new ItemJs(); 
        itemJson.setCode(0);
        itemJson.setMsg("");
        itemJson.setCount(i);
        itemJson.setData(items);
        return itemJson;
    }
	

	
		
	
	@RequestMapping("/deleteItem")
	@ResponseBody
	public TaoTaoResult deleteItemById(@RequestBody List<TbItem> items){
		Date date=new Date();
		TaoTaoResult result = tbItemService.updateItems(items, 2,date);
		return result;
	}//删除
	
	@RequestMapping("/upItem")
	@ResponseBody
	public TaoTaoResult upItem(@RequestBody List<TbItem> items){
		Date date=new Date();
		TaoTaoResult result = tbItemService.updateItems(items,1,date);
		return result;
	}//商品上架
	
	@RequestMapping("/downItem")
	@ResponseBody
	public TaoTaoResult downItem(@RequestBody List<TbItem> items){
		Date date=new Date();
		TaoTaoResult result = tbItemService.updateItems(items,0,date);
		return result;
	}//商品下架
	

	@RequestMapping("/findTbItem")//多条件搜索并分页显示
	@ResponseBody
	public ItemJs findTbItem(Integer page,Integer limit,String title,Integer priceMin,Integer priceMax,Long cId){
		
		ItemJs itemJs = tbItemService.findTbItemBy(page,limit,title, priceMin,priceMax,cId);
		
		return itemJs;//结果集返回json格式
	}
}
