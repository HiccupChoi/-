package com.vs.enums;

public enum AuthorityEnum {
        STUDENT("1","学生"),
        TEACHER("2","老师"),
        MANAGER("3","管理员"),
        PARENTS("4","家长")
    ;
    private String Authority_code;
    private String Authority_txt;

    AuthorityEnum(String authority_code, String authority_txt) {
        Authority_code = authority_code;
        Authority_txt = authority_txt;
    }

    public String getAuthority_code() {
        return Authority_code;
    }

    public String getAuthority_txt() {
        return Authority_txt;
    }
}
