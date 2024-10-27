package top.crcbest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author crc
 * @date 2024/10/27
 */

@SpringBootConfiguration
public class Knife4jConfiguration {
    @Bean
    public OpenAPI openAPI() {
        Contact crc = new Contact()
                .name("crc")
                .email("21rc")
                .url("https://www.stulove.tech");
        return new OpenAPI()
                .info(new Info()
                        .title("hello-knife4j项目API")
                        .contact(crc)
                        .version("1.0")
                        .summary("项目的接口文档")
                        .description("hello-knife4j项目的接口文档"));
    }

    /**
     * API分组
     * @return
     */
    @Bean
    public GroupedOpenApi userAPI() {
        return GroupedOpenApi.builder().group("用户信息管理").
                pathsToMatch("/user/**").
                build();
    }
    @Bean
    public GroupedOpenApi systemAPI() {
        return GroupedOpenApi.builder().group("产品信息管理").
                pathsToMatch("/product/**").
                build();
    }
}
