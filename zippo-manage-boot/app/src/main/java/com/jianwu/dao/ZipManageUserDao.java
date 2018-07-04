package com.jianwu.dao;

import com.jianwu.domain.ZipManageUser;
import com.jianwu.domain.result.ZipSysUserResponse;
import com.jianwu.example.ZipManageUserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ZipManageUserDao {
    int countByExample(ZipManageUserExample example);

    int deleteByExample(ZipManageUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZipManageUser record);

    int insertSelective(ZipManageUser record);

    List<ZipManageUser> selectByExample(ZipManageUserExample example);

    ZipManageUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZipManageUser record, @Param("example") ZipManageUserExample example);

    int updateByExample(@Param("record") ZipManageUser record, @Param("example") ZipManageUserExample example);

    int updateByPrimaryKeySelective(ZipManageUser record);

    int updateByPrimaryKey(ZipManageUser record);

    int updateBatchByPrimaryKey(List<ZipManageUser> list);

    int insertBatch(List<ZipManageUser> list);

    int updateBatchByPrimaryKeySelective(List<ZipManageUser> list);

    /*
     分页获取后台登录用户信息
      */
    @Select("select * from zip_manage_user where status=0 order by create_time desc limit #{start},#{end}")
    List<ZipManageUser> findUserByPage(@Param("start") Integer start,@Param("end") Integer end);

    /*
    按条件查询后台登陆用户信息
     */
    List<ZipSysUserResponse> queryLike(@Param("start") Integer start,@Param("end") Integer end,@Param("name") String name,@Param("phone") String phone);

    /*
    按条件查询后台登陆用户信息条数
     */
    int queryLikeCount(@Param("name") String name,@Param("phone") String phone);
}