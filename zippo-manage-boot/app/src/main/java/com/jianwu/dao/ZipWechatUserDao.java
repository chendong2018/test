package com.jianwu.dao;

import com.jianwu.domain.ZipWechatUser;
import com.jianwu.domain.result.ZipWechatUserResponse;
import com.jianwu.example.ZipWechatUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZipWechatUserDao {
    int countByExample(ZipWechatUserExample example);

    int deleteByExample(ZipWechatUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZipWechatUser record);

    int insertSelective(ZipWechatUser record);

    List<ZipWechatUser> selectByExample(ZipWechatUserExample example);

    ZipWechatUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZipWechatUser record, @Param("example") ZipWechatUserExample example);

    int updateByExample(@Param("record") ZipWechatUser record, @Param("example") ZipWechatUserExample example);

    int updateByPrimaryKeySelective(ZipWechatUser record);

    int updateByPrimaryKey(ZipWechatUser record);

    int updateBatchByPrimaryKey(List<ZipWechatUser> list);

    int insertBatch(List<ZipWechatUser> list);

    int updateBatchByPrimaryKeySelective(List<ZipWechatUser> list);

    /*
    分页模糊查询客户信息
     */
    List<ZipWechatUserResponse> queryLike(@Param("start") Integer start, @Param("end") Integer end, @Param("nickName") String nickName);

    /*
    模糊查询客户信息条数
     */
    int queryLikeCount(@Param("nickName") String nickName);
}