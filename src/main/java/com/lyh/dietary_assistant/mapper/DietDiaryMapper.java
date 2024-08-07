package com.lyh.dietary_assistant.mapper;

import com.lyh.dietary_assistant.pojo.DietDiary;
import com.lyh.dietary_assistant.pojo.DietDiaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DietDiaryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_diet_diary
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    int countByExample(DietDiaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_diet_diary
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    int deleteByExample(DietDiaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_diet_diary
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_diet_diary
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    int insert(DietDiary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_diet_diary
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    int insertSelective(DietDiary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_diet_diary
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    List<DietDiary> selectByExample(DietDiaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_diet_diary
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    DietDiary selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_diet_diary
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    int updateByExampleSelective(@Param("record") DietDiary record, @Param("example") DietDiaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_diet_diary
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    int updateByExample(@Param("record") DietDiary record, @Param("example") DietDiaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_diet_diary
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    int updateByPrimaryKeySelective(DietDiary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_diet_diary
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    int updateByPrimaryKey(DietDiary record);
}