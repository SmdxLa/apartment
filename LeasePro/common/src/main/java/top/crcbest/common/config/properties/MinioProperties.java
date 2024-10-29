package top.crcbest.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author crc
 * @date 2024/10/29
 */
@ConfigurationProperties(prefix = "minio")//在使用时Import，或者@EnableConfigurationProperties(MinioProperties.class)
@Data
public class MinioProperties {

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String bucketName;
}
