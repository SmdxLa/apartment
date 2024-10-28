package top.crcbest.web.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.crcbest.web.admin.converter.StringToBaseEnumConverterFactory;
import top.crcbest.web.admin.converter.StringToItemTypeConverter;

/**
 * @author crc
 * @date 2024/10/28
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

//    @Autowired
//    private StringToItemTypeConverter stringToItemTypeConverter;

    @Autowired
    private StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;
    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(stringToItemTypeConverter);
        registry.addConverterFactory(stringToBaseEnumConverterFactory);

    }
}
