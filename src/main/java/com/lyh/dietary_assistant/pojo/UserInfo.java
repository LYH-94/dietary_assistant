package com.lyh.dietary_assistant.pojo;

public class UserInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.id
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.owner
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private Integer owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.nickName
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private String nickname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.email
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.targetCalories
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private Short targetcalories;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.carbohydrateRatio
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private Byte carbohydrateratio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.fatRatio
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private Byte fatratio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.proteinRatio
     *
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    private Byte proteinratio;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.id
     *
     * @return the value of t_user_info.id
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.id
     *
     * @param id the value for t_user_info.id
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.owner
     *
     * @return the value of t_user_info.owner
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public Integer getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.owner
     *
     * @param owner the value for t_user_info.owner
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.nickName
     *
     * @return the value of t_user_info.nickName
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.nickName
     *
     * @param nickname the value for t_user_info.nickName
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.email
     *
     * @return the value of t_user_info.email
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.email
     *
     * @param email the value for t_user_info.email
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.targetCalories
     *
     * @return the value of t_user_info.targetCalories
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public Short getTargetcalories() {
        return targetcalories;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.targetCalories
     *
     * @param targetcalories the value for t_user_info.targetCalories
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setTargetcalories(Short targetcalories) {
        this.targetcalories = targetcalories;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.carbohydrateRatio
     *
     * @return the value of t_user_info.carbohydrateRatio
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public Byte getCarbohydrateratio() {
        return carbohydrateratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.carbohydrateRatio
     *
     * @param carbohydrateratio the value for t_user_info.carbohydrateRatio
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setCarbohydrateratio(Byte carbohydrateratio) {
        this.carbohydrateratio = carbohydrateratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.fatRatio
     *
     * @return the value of t_user_info.fatRatio
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public Byte getFatratio() {
        return fatratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.fatRatio
     *
     * @param fatratio the value for t_user_info.fatRatio
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setFatratio(Byte fatratio) {
        this.fatratio = fatratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.proteinRatio
     *
     * @return the value of t_user_info.proteinRatio
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public Byte getProteinratio() {
        return proteinratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.proteinRatio
     *
     * @param proteinratio the value for t_user_info.proteinRatio
     * @mbggenerated Tue Jul 30 20:26:00 CST 2024
     */
    public void setProteinratio(Byte proteinratio) {
        this.proteinratio = proteinratio;
    }

    // 以下是自己新增的程式碼。
    public UserInfo() {
    }

    public UserInfo(Integer id, Integer owner, String nickname, String email, Short targetcalories, Byte carbohydrateratio, Byte fatratio, Byte proteinratio) {
        this.id = id;
        this.owner = owner;
        this.nickname = nickname;
        this.email = email;
        this.targetcalories = targetcalories;
        this.carbohydrateratio = carbohydrateratio;
        this.fatratio = fatratio;
        this.proteinratio = proteinratio;
    }
}