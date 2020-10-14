package com.fml.learn.basiclearn.syslog;

public enum OperateModule {
    PATTERNS(1L, "patterns"),
    REFLECTION(2L, "reflection"),
    ANNOTATION(3L, "annotation"),
    SPRINGBOOT(4L, "springboot");


    private Long value;

    private String text;

    OperateModule(Long value, String text) {
        this.value = value;
        this.text = text;
    }

    public static OperateModule getOperateModule(Long value) {
        if (value == null) {
            return null;
        }
        for (OperateModule operateType : OperateModule.values()) {
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
