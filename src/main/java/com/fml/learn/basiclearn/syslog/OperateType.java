package com.fml.learn.basiclearn.syslog;

public enum OperateType {
    ADD(1L, "增加"),
    UPDATE(2L, "修改"),
    DELETE(3L, "删除"),
    QUERY(4L, "查询");


    private Long value;

    private String text;

    OperateType(Long value, String text) {
        this.value = value;
        this.text = text;
    }

    public static OperateType getOperateType(Long value) {
        if (value == null) {
            return null;
        }
        for (OperateType operateType : OperateType.values()) {
            if (operateType.getValue().equals(value)) {
                return operateType;
            }
        }
        return null;
    }

    public Long getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
