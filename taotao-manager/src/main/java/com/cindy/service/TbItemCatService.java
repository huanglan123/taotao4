package com.cindy.service;

import java.util.List;

import com.cindy.common.EchartsResult;
import com.cindy.common.ZTreeNodeResult;

public interface TbItemCatService {
	//根据分类id查，返回ztree节点数据集合，因为有id，name,isparent
	List<ZTreeNodeResult> findTbItemCatById(Long parentId);

	List<EchartsResult> statisticsItem();//分类统计
	
}
