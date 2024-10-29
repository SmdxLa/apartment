package top.crcbest.web.admin.service.impl;

import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import top.crcbest.common.config.properties.MinioProperties;
import top.crcbest.web.admin.service.FileService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private MinioProperties properties;

    @Autowired
    private MinioClient client;
    @Override
    public String upload(MultipartFile file) {
        try {
            //使用SimpleDateFormat对日期进行格式化
            String filename = new SimpleDateFormat("yyyyMMdd").format(new Date())
                    + "/" + UUID.randomUUID()
                    + "-" + file.getOriginalFilename();


            client.putObject(PutObjectArgs.builder().
//                    bucket(properties.getBucketName()).
                    bucket("lease").
                    object(filename).
                    stream(file.getInputStream(), file.getSize(), -1).
                    contentType(file.getContentType()).build());

            //“/”是分隔符，将endpoint、bucketName、filename拼接成一个字符串
            return String.join("/", properties.getEndpoint(), properties.getBucketName(), filename);

//            return filename;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
