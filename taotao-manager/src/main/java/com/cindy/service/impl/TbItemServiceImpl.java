package com.cindy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cindy.bean.ItemJs;
import com.cindy.bean.TbItem;
import com.cindy.common.TaoTaoResult;
import com.cindy.mapper.TbItemMapper;
import com.cindy.service.TbItemService;
@Service
public class TbItemServiceImpl implements TbItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Override
	public TbItem findTbItemById(Long tbItemId) {
		TbItem tbItem=tbItemMapper.findTbItemById(tbItemId);
		return tbItem;
	}
	@Override
	public List<TbItem> findTbItem(Integer page, Integer limit) {
		List<TbItem> tbItems=tbItemMapper.findTbItem(page,limit);
		return tbItems;
	}
	@Override
	public Integer findCount() {
		Integer i=tbItemMapper.findCount();
		return i;
	}

	@Override
	public TaoTaoResult updateItems(List<TbItem> items, Integer type,Date date) {
		List<Long> ids = new ArrayList<Long>();
		for (TbItem tbItem : items) {
			ids.add(tbItem.getId());//把选中的商品添加到集合里
		}
		int count = tbItemMapper.updateItemByIds(ids, type);
		if(count>0&&type==0){//count是选中的商品，type是状态
			return TaoTaoResult.build(200, "商品下架成功");
		}else if(count>0&&type==1){
			return TaoTaoResult.build(200, "商品上架成功");
		}else if(count>0&&type==2){
			return TaoTaoResult.build(200, "商品删除成功");
		}
		return TaoTaoResult.build(500, "操作有误");
	}
@Override
//多条件搜索
public ItemJs findTbItemBy(Integer page, Integer limit, String title, Integer priceMin, Integer priceMax, Long cId) {
	if(priceMin==null){//按价格范围搜，如果用户没有输入最低价就默认为0
		priceMin = 0;
	}
	if(priceMax==null){//如果用户没有输入最高价就默认为10000000
		priceMax = 10000000; 
	}
	ItemJs itemJs = new ItemJs();
	itemJs.setCode(0);
	int count=tbItemMapper.findTbItemCountBySearch(title,priceMin,priceMax,cId);
	if(count<=0){//如果查询结果的总记录条数为0则提示：
		itemJs.setMsg("没有商品信息");
		return itemJs;//并返回成json格式传给页面 
	}
	itemJs.setMsg("");
	itemJs.setCount(count);
	
	/**
	 * 以上是总记录条数
	 * 下面的方法是分页返回的是个集合的json格式
	 */
	List<TbItem> data = tbItemMapper.findTbItemBySearchPage((page-1)*limit, limit,title,priceMin,priceMax,cId);
	itemJs.setData(data);
	return itemJs;
}
	
	
	
}
	

	
	
	

	
	
	


