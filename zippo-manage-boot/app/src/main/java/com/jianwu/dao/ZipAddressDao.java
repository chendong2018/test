package com.jianwu.dao;

import com.jianwu.domain.ZipAddress;
import com.jianwu.example.ZipAddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZipAddressDao {
    int countByExample(ZipAddressExample example);

    int deleteByExample(ZipAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZipAddress record);

    int insertSelective(ZipAddress record);

    List<ZipAddress> selectByExample(ZipAddressExample example);

    ZipAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZipAddress record, @Param("example") ZipAddressExample example);

    int updateByExample(@Param("record") ZipAddress record, @Param("example") ZipAddressExample example);

    int updateByPrimaryKeySelective(ZipAddress record);

    int updateByPrimaryKey(ZipAddress record);

    int updateBatchByPrimaryKey(List<ZipAddress> list);

    int insertBatch(List<ZipAddress> list);

    int updateBatchByPrimaryKeySelective(List<ZipAddress> list);
}