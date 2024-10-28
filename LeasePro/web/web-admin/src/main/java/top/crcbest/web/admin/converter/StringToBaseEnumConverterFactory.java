package top.crcbest.web.admin.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import top.crcbest.model.enums.BaseEnum;

import java.util.Arrays;

/**
 * @author crc
 * @date 2024/10/28
 */
@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum>{

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new Converter<String, T>() {
            @Override
            public T convert(String source) {
                return Arrays.stream(targetType.getEnumConstants())
                        .filter(t -> source.equals(t.getCode()+""))//这里的equals是String的equals，code是Integer，所以要转成String
                        .findFirst()
                        .orElseThrow(RuntimeException::new);
            }
        };
    }
}
