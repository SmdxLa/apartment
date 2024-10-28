package top.crcbest.web.admin.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import top.crcbest.model.enums.ItemType;

import java.util.Arrays;

/**
 * @author crc
 * @date 2024/10/28
 */
//@Component
public class StringToItemTypeConverter implements Converter<String, ItemType> {
    @Override
    public ItemType convert(String source) {
        return Arrays.stream(ItemType.values())
                .filter(itemType -> source.equals(itemType.getCode()))
                .findFirst()//找到第一个（Optional），简化了写法
                .orElseThrow(RuntimeException::new);//如果找不到就抛异常
        //如果lambda的签名和接口的签名一样，可以直接用方法引用
    }
}
