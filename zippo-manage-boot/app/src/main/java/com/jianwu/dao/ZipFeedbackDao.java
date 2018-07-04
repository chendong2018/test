package com.jianwu.dao;

import com.jianwu.domain.ZipFeedback;
import com.jianwu.example.ZipFeedbackExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZipFeedbackDao {
    int countByExample(ZipFeedbackExample example);

    int deleteByExample(ZipFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZipFeedback record);

    int insertSelective(ZipFeedback record);

    List<ZipFeedback> selectByExample(ZipFeedbackExample example);

    ZipFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZipFeedback record, @Param("example") ZipFeedbackExample example);

    int updateByExample(@Param("record") ZipFeedback record, @Param("example") ZipFeedbackExample example);

    int updateByPrimaryKeySelective(ZipFeedback record);

    int updateByPrimaryKey(ZipFeedback record);

    int updateBatchByPrimaryKey(List<ZipFeedback> list);

    int insertBatch(List<ZipFeedback> list);

    int updateBatchByPrimaryKeySelective(List<ZipFeedback> list);
}