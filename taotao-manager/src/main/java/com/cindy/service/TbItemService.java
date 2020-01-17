package com.cindy.service;

import java.util.Date;
import java.util.List;

import com.cindy.bean.ItemJs;
import com.cindy.bean.TbItem;
import com.cindy.common.TaoTaoResult;


public interface TbItemService {
	TbItem findTbItemById(Long tbItemId);//根据id查询指定商品

	List<TbItem> findTbItem(Integer page, Integer limit);//分页

	Integer findCount();//总记录条数

	//TaoTaoResult deletemByIds(List<TbItem> items);

	/**
	 * 把删除和商品上架和下架封在一个方法里，更智能
	 * @param items 批量删除的集合
	 * @param type 点击删除后的状态
	 * @return 返回TaoTaoResult结果集
	 */
	TaoTaoResult updateItems(List<TbItem> items, Integer type,Date date);

	

	//List<TbItem> findTbItemByName(String title);

	

	ItemJs findTbItemBy(Integer page, Integer limit, String title, Integer priceMin, Integer priceMax, Long cId);

	

	

	

	
	

	

	
}
