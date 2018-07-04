package com.jianwu.dao;

import com.jianwu.domain.ZipCustomization;
import com.jianwu.example.ZipCustomizationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZipCustomizationDao {
    int countByExample(ZipCustomizationExample example);

    int deleteByExample(ZipCustomizationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZipCustomization record);

    int insertSelective(ZipCustomization record);

    List<ZipCustomization> selectByExample(ZipCustomizationExample example);

    ZipCustomization selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZipCustomization record, @Param("example") ZipCustomizationExample example);

    int updateByExample(@Param("record") ZipCustomization record, @Param("example") ZipCustomizationExample example);

    int updateByPrimaryKeySelective(ZipCustomization record);

    int updateByPrimaryKey(ZipCustomization record);

    int updateBatchByPrimaryKey(List<ZipCustomization> list);

    int insertBatch(List<ZipCustomization> list);

    int updateBatchByPrimaryKeySelective(List<ZipCustomization> list);
}