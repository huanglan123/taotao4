package com.cindy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cindy.bean.TbItemCat;

public interface TbItemCatMapper {

	List<TbItemCat> findTbItemCatById(Long parentId);
	//根据echarts里的parentId属性查找分类

	TbItemCat getTbItemCatById(@Param("id")Long id);//根据分类id找分类
	
}
