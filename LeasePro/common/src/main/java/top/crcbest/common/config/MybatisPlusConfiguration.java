package top.crcbest.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author crc
 * @date 2024/10/27
 */
@Configuration
@MapperScan("top.crcbest.web.*.mapper")
public class MybatisPlusConfiguration {
}
