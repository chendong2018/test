package com.jianwu.dao;

import com.jianwu.domain.ZipTemplate;
import com.jianwu.example.ZipTemplateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZipTemplateDao {
    int countByExample(ZipTemplateExample example);

    int deleteByExample(ZipTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZipTemplate record);

    int insertSelective(ZipTemplate record);

    List<ZipTemplate> selectByExample(ZipTemplateExample example);

    ZipTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZipTemplate record, @Param("example") ZipTemplateExample example);

    int updateByExample(@Param("record") ZipTemplate record, @Param("example") ZipTemplateExample example);

    int updateByPrimaryKeySelective(ZipTemplate record);

    int updateByPrimaryKey(ZipTemplate record);

    /**
     * @param templateOrder 模版编号
     * @param templateName 模板名称
     * @param module 模板面
     * @param status 状态
     * @return
     */
    Integer countTemplate(@Param("templateOrder") String templateOrder, @Param("templateName") String templateName,
                          @Param("module") Integer module, @Param("status") Integer status);

    /**
     * @param templateOrder
     * @param templateName
     * @param module
     * @param status
     * @param start
     * @param end
     * @return
     */
    List<ZipTemplate> searchZipTempLateByPage(@Param("templateOrder") String templateOrder, @Param("templateName") String templateName,
                                              @Param("module") Integer module, @Param("status") Integer status, @Param("start") Integer start,
                                              @Param("end") Integer end);
}