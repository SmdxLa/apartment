package top.crcbest.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MenuType implements BaseEnum{

    CATALOG(0, "目录"),
    MENU(1, "菜单"),
    BUTTON(2, "按钮");

    @EnumValue
    @JsonValue
    private Integer code;

    private String name;

    MenuType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
