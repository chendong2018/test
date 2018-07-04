package com.jianwu.dao;

import com.jianwu.domain.ZipOrderForm;
import com.jianwu.domain.result.ZipOrderFormResponse;
import com.jianwu.example.ZipOrderFormExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ZipOrderFormDao {
    int countByExample(ZipOrderFormExample example);

    int deleteByExample(ZipOrderFormExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZipOrderForm record);

    int insertSelective(ZipOrderForm record);

    List<ZipOrderForm> selectByExample(ZipOrderFormExample example);

    ZipOrderForm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZipOrderForm record, @Param("example") ZipOrderFormExample example);

    int updateByExample(@Param("record") ZipOrderForm record, @Param("example") ZipOrderFormExample example);

    int updateByPrimaryKeySelective(ZipOrderForm record);

    int updateByPrimaryKey(ZipOrderForm record);

    int updateBatchByPrimaryKey(List<ZipOrderForm> list);

    int insertBatch(List<ZipOrderForm> list);

    int updateBatchByPrimaryKeySelective(List<ZipOrderForm> list);

    /*
    分页模糊查询订单列表
     */
    List<ZipOrderFormResponse> queryLike(@Param("start") Integer start, @Param("end") Integer end, @Param("sTime") Date sTime, @Param("eTime") Date eTime, @Param("status") Integer status, @Param("wechatUserId") Integer wechatUserId, @Param("wechatUserName") String wechatUserName, @Param("orderNumber") String orderNumber);

    /*
    模糊查询订单条数
     */
    int queryLikeCount(@Param("sTime") Date sTime, @Param("eTime") Date eTime, @Param("status") Integer status, @Param("wechatUserId") Integer wechatUserId, @Param("wechatUserName") String wechatUserName, @Param("orderNumber") String orderNumber);

}