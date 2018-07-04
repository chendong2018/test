package com.jianwu.dao;

import com.jianwu.domain.ZipTemplateCustom;
import com.jianwu.example.ZipTemplateCustomExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZipTemplateCustomDao {
    int countByExample(ZipTemplateCustomExample example);

    int deleteByExample(ZipTemplateCustomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZipTemplateCustom record);

    int insertSelective(ZipTemplateCustom record);

    List<ZipTemplateCustom> selectByExample(ZipTemplateCustomExample example);

    ZipTemplateCustom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZipTemplateCustom record, @Param("example") ZipTemplateCustomExample example);

    int updateByExample(@Param("record") ZipTemplateCustom record, @Param("example") ZipTemplateCustomExample example);

    int updateByPrimaryKeySelective(ZipTemplateCustom record);

    int updateByPrimaryKey(ZipTemplateCustom record);

    int updateBatchByPrimaryKey(List<ZipTemplateCustom> list);

    int insertBatch(List<ZipTemplateCustom> list);

    int updateBatchByPrimaryKeySelective(List<ZipTemplateCustom> list);
}