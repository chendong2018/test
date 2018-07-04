package com.jianwu.dao;

import com.jianwu.domain.ZipCommodity;
import com.jianwu.domain.result.ZipCommodityResponse;
import com.jianwu.example.ZipCommodityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZipCommodityDao {
    int countByExample(ZipCommodityExample example);

    int deleteByExample(ZipCommodityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZipCommodity record);

    int insertSelective(ZipCommodity record);

    List<ZipCommodity> selectByExample(ZipCommodityExample example);

    ZipCommodity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZipCommodity record, @Param("example") ZipCommodityExample example);

    int updateByExample(@Param("record") ZipCommodity record, @Param("example") ZipCommodityExample example);

    int updateByPrimaryKeySelective(ZipCommodity record);

    int updateByPrimaryKey(ZipCommodity record);

    int updateBatchByPrimaryKey(List<ZipCommodity> list);

    int insertBatch(List<ZipCommodity> list);

    int updateBatchByPrimaryKeySelective(List<ZipCommodity> list);

    /*
    分页模糊查询商品列表
     */
    List<ZipCommodityResponse> queryLike(@Param("start") Integer start, @Param("end") Integer end, @Param("commodityOrder") String commodityOrder, @Param("commodityName") String commodityName, @Param("status") Integer status, @Param("commodityStatus") Integer commodityStatus);

    /*
    模糊查询商品条数
     */
    int queryLikeCount(@Param("commodityOrder") String commodityOrder, @Param("commodityName") String commodityName, @Param("status") Integer status, @Param("commodityStatus") Integer commodityStatus);
}