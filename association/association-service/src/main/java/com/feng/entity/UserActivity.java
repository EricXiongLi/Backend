package com.feng.entity;

import com.baomidou.mybatisplus.enums.IdType;

import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 活动名称
     */
    @NotNull(message = "用户账号不能为空")
//    private String account;
    public String account;

    /**
     * 活动名称
     */
    @NotNull(message = "活动名称不能为空")
    private String activityName;
    /**
     * 活动地点
     */
    @NotNull(message = "活动地点不能为空")
    private String site;
    /**
     * 活动地点
     */
    @NotNull(message = "活动id不能为空")
    private Integer activityId;
    /**
     * 活动举行时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd")
    @DateTimeFormat(pattern = "yyyy年MM月dd")
    private String holdTime;
    /**
     * 活动介绍
     */
    @NotNull(message = "活动简介不能为空")
    private String introduce;

    public Integer getId() {
        return id;
    }


}
