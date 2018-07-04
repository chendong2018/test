package com.jianwu.dao;

import com.jianwu.domain.ZipCustomerServiceUser;
import com.jianwu.domain.result.ZipCustomerServiceUserResponse;
import com.jianwu.example.ZipCustomerServiceUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZipCustomerServiceUserDao {
    int countByExample(ZipCustomerServiceUserExample example);

    int deleteByExample(ZipCustomerServiceUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZipCustomerServiceUser record);

    int insertSelective(ZipCustomerServiceUser record);

    List<ZipCustomerServiceUser> selectByExample(ZipCustomerServiceUserExample example);

    ZipCustomerServiceUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZipCustomerServiceUser record, @Param("example") ZipCustomerServiceUserExample example);

    int updateByExample(@Param("record") ZipCustomerServiceUser record, @Param("example") ZipCustomerServiceUserExample example);

    int updateByPrimaryKeySelective(ZipCustomerServiceUser record);

    int updateByPrimaryKey(ZipCustomerServiceUser record);

    int updateBatchByPrimaryKey(List<ZipCustomerServiceUser> list);

    int insertBatch(List<ZipCustomerServiceUser> list);

    int updateBatchByPrimaryKeySelective(List<ZipCustomerServiceUser> list);

    /*
    分页模糊查询客服列表
     */
    List<ZipCustomerServiceUserResponse> queryLike(@Param("start") Integer start, @Param("end") Integer end, @Param("customerServiceName") String customerServiceName, @Param("wechatNumber") String wechatNumber, @Param("status") Integer status);

    /*
    模糊查询客服条数
     */
    int queryLikeCount(@Param("customerServiceName") String customerServiceName, @Param("wechatNumber") String wechatNumber, @Param("status") Integer status);
}