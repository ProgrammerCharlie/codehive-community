package com.feirui.subject.infra.basic.dao;

import com.feirui.subject.infra.basic.entity.SubjectCategory;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 题目分类(SubjectCategory)表数据库访问层
 *
 * @author charliefei
 * @since 2023-12-04 22:56:30
 */
public interface SubjectCategoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectCategory queryById(Long id);

    /**
     * 统计总行数
     *
     * @param subjectCategory 查询条件
     * @return 总行数
     */
    long count(SubjectCategory subjectCategory);

    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 影响行数
     */
    int insert(SubjectCategory subjectCategory);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectCategory> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubjectCategory> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectCategory> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubjectCategory> entities);

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 影响行数
     */
    int update(SubjectCategory subjectCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 查询分类
     */
    List<SubjectCategory> queryCategoryList(SubjectCategory subjectCategory);

    /**
     * 查询分类下题目数量
     * @param categoryId 分类id
     * @return 分类下题数
     */
    Integer querySubjectCount(Long categoryId);
}

