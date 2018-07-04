package com.jianwu.dao;

import com.jianwu.domain.ZipTemplateDetails;
import com.jianwu.example.ZipTemplateDetailsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZipTemplateDetailsDao {
    int countByExample(ZipTemplateDetailsExample example);

    int deleteByExample(ZipTemplateDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZipTemplateDetails record);

    int insertSelective(ZipTemplateDetails record);

    List<ZipTemplateDetails> selectByExample(ZipTemplateDetailsExample example);

    ZipTemplateDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZipTemplateDetails record, @Param("example") ZipTemplateDetailsExample example);

    int updateByExample(@Param("record") ZipTemplateDetails record, @Param("example") ZipTemplateDetailsExample example);

    int updateByPrimaryKeySelective(ZipTemplateDetails record);

    int updateByPrimaryKey(ZipTemplateDetails record);
}