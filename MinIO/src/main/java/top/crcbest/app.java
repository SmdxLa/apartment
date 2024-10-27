package top.crcbest;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author crc
 * @date 2024/10/27
 */
public class app {
    public static void main(String[] args) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        //先创建一个MinioClient对象，输入账号密码和地址
        MinioClient minioClient = MinioClient.builder()
                .credentials("admin", "crcisbest")
                .endpoint("https://s3.stulove.tech")
                .build();

        //所有参数都自带，直接输入大写字母，IDEA提示
        //判断桶是否存在，需抛出异常
        boolean flag = minioClient.bucketExists(BucketExistsArgs.builder().bucket("b002").build());

        if (flag) {
            System.out.println("桶存在");
        } else {
            //创建桶
            minioClient.makeBucket(MakeBucketArgs.builder()
                            .bucket("b002")
                            .build());
            //设置桶的访问策略
            String policy = """
                    {
                        "Version": "2012-10-17",
                        "Statement": [
                            {
                                "Effect": "Allow",
                                "Principal": {
                                    "AWS": [
                                        "*"
                                    ]
                                },
                                "Action": [
                                    "s3:GetObject"
                                ],
                                "Resource": [
                                    "arn:aws:s3:::b002/*"
                                ]
                            }
                        ]
                    }
                                                    """;
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                             .bucket("b002")
                             .config(policy)
                             .build());
        }

        //上传
        minioClient.uploadObject(UploadObjectArgs.builder()
                        .bucket("b002")
                        .filename("E:\\h\\Baidu\\2688103771_Steve__1\\尚庭公寓\\图片资源\\公寓-健身房.jpg")
                        .object("公寓-健身房.jpg")
                .build());

    }
}
