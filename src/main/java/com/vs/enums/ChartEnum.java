package com.vs.enums;

public enum ChartEnum {
        LINECHART(1,"折线图"),
        ROSECHART(2,"玫瑰图")
    ;
    private int type;
    private String typeName;

    ChartEnum(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public int getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }
}
