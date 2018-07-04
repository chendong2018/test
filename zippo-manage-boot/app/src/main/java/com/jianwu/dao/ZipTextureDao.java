package com.jianwu.dao;

import com.jianwu.domain.ZipTexture;
import com.jianwu.domain.result.ZipTextureResponse;
import com.jianwu.example.ZipTextureExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZipTextureDao {
    int countByExample(ZipTextureExample example);

    int deleteByExample(ZipTextureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZipTexture record);

    int insertSelective(ZipTexture record);

    List<ZipTexture> selectByExample(ZipTextureExample example);

    ZipTexture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZipTexture record, @Param("example") ZipTextureExample example);

    int updateByExample(@Param("record") ZipTexture record, @Param("example") ZipTextureExample example);

    int updateByPrimaryKeySelective(ZipTexture record);

    int updateByPrimaryKey(ZipTexture record);

    int updateBatchByPrimaryKey(List<ZipTexture> list);

    int insertBatch(List<ZipTexture> list);

    int updateBatchByPrimaryKeySelective(List<ZipTexture> list);

    /*
    分页模糊查询材质列表
     */
    List<ZipTextureResponse> queryLike(@Param("start") Integer start, @Param("end") Integer end, @Param("textureOrder") String textureOrder, @Param("textureName") String textureName, @Param("status") Integer status);

    /*
    模糊查询材质条数
     */
    int queryLikeCount(@Param("textureOrder") String textureOrder, @Param("textureName") String textureName, @Param("status") Integer status);
}