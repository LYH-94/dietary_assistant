package com.lyh.dietary_assistant.pojo;

import java.util.Date;

public class BodyCompositionData {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_body_composition_data.id
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_body_composition_data.owner
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private Integer owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_body_composition_data.date
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private Date date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_body_composition_data.weight
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private Float weight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_body_composition_data.bodyFat
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private Float bodyfat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_body_composition_data.skeletalMuscleMass
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private Float skeletalmusclemass;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_body_composition_data.id
     *
     * @return the value of t_body_composition_data.id
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_body_composition_data.id
     *
     * @param id the value for t_body_composition_data.id
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_body_composition_data.owner
     *
     * @return the value of t_body_composition_data.owner
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public Integer getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_body_composition_data.owner
     *
     * @param owner the value for t_body_composition_data.owner
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_body_composition_data.date
     *
     * @return the value of t_body_composition_data.date
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_body_composition_data.date
     *
     * @param date the value for t_body_composition_data.date
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_body_composition_data.weight
     *
     * @return the value of t_body_composition_data.weight
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public Float getWeight() {
        return weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_body_composition_data.weight
     *
     * @param weight the value for t_body_composition_data.weight
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setWeight(Float weight) {
        this.weight = weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_body_composition_data.bodyFat
     *
     * @return the value of t_body_composition_data.bodyFat
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public Float getBodyfat() {
        return bodyfat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_body_composition_data.bodyFat
     *
     * @param bodyfat the value for t_body_composition_data.bodyFat
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setBodyfat(Float bodyfat) {
        this.bodyfat = bodyfat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_body_composition_data.skeletalMuscleMass
     *
     * @return the value of t_body_composition_data.skeletalMuscleMass
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public Float getSkeletalmusclemass() {
        return skeletalmusclemass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_body_composition_data.skeletalMuscleMass
     *
     * @param skeletalmusclemass the value for t_body_composition_data.skeletalMuscleMass
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setSkeletalmusclemass(Float skeletalmusclemass) {
        this.skeletalmusclemass = skeletalmusclemass;
    }
}