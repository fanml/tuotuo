package com.fml.learn.basiclearn.syslog;

import java.util.Date;

/**
 * 存储操作日志信息类
 */
public class OperateLog {

    private Long id;

    private String recordId; // 操作数据id


    private String module;// 模块名称

    private String business;// 业务方法描述

    private String opType;// 操作类型

    private Long userId;// 操作人

    private String userName;// 操作人姓名

    private String params;// 操作数据

    private Date createTime; // 时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OperateLog{" +
                "id=" + id +
                ", recordId='" + recordId + '\'' +
                ", module='" + module + '\'' +
                ", business='" + business + '\'' +
                ", opType='" + opType + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", params='" + params + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
