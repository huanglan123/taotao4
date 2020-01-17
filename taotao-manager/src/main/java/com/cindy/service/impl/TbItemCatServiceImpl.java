package com.cindy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cindy.bean.TbItem;
import com.cindy.bean.TbItemCat;
import com.cindy.common.EchartsResult;
import com.cindy.common.ZTreeNodeResult;
import com.cindy.mapper.TbItemCatMapper;
import com.cindy.mapper.TbItemMapper;
import com.cindy.service.TbItemCatService;


@Service
public class TbItemCatServiceImpl implements TbItemCatService {
	private String name;
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Autowired
	private TbItemMapper tbItemMapper;
	@Override
	public List<ZTreeNodeResult> findTbItemCatById(Long parentId) {
		List<TbItemCat> tbItemCats = tbItemCatMapper.findTbItemCatById(parentId);
		//根据分类id搜索，返回分类集合
		List<ZTreeNodeResult> result = new ArrayList<ZTreeNodeResult>();
		for (TbItemCat tbItemCat : tbItemCats) {
			ZTreeNodeResult node = new ZTreeNodeResult();
			node.setId(tbItemCat.getId());
			node.setName(tbItemCat.getName());
			node.setIsParent(tbItemCat.getIsParent());
			result.add(node);//把返回的分类集合装进ztree对象
		}
		return result;//返回成Ztree节点集合
	}
	@Override
	public List<EchartsResult> statisticsItem() {
		List<EchartsResult> results = new ArrayList<EchartsResult>();
		//扇形图
		List<TbItem> tbItems = tbItemMapper.statisticsItemCId();
		//根据商品分类id查找
		for (TbItem tbItem : tbItems) {
			EchartsResult result = new EchartsResult();
			TbItemCat tbItemCat = tbItemCatMapper.getTbItemCatById(tbItem.getCId());
			getFirstItemCat(tbItemCat);//调用方法
			//result.setName(name+"类");
			result.setName(name+"类");//把得到的分类名称设在扇形图对象中
			int value = tbItemMapper.findTbItemCountByCId(tbItem.getCId());
			//统计分类下的商品数量
			result.setValue(value);
			results.add(result);
		}
		return results;
		
	}
	private String getFirstItemCat(TbItemCat tbItemCat) {
		//把分类封装一个方法，在统计时调用这个方法就可以得到分类的name属性
		TbItemCat cat = tbItemCatMapper.getTbItemCatById(tbItemCat.getParentId());
		if(cat!=null){
			name = cat.getName();
			getFirstItemCat(cat);
		}
		return null;
	}

}
