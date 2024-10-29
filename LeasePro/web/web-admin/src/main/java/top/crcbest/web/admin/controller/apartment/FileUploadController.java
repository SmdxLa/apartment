package top.crcbest.web.admin.controller.apartment;


import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import top.crcbest.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.crcbest.web.admin.service.FileService;


@Tag(name = "文件管理")
@RequestMapping("/admin/file")
@RestController
public class FileUploadController {
    @Autowired
    private FileService service;
    @Operation(summary = "上传文件")
    @PostMapping("upload")
    public Result<String> upload(@RequestParam MultipartFile file) {
        String url = service.upload(file);
        return Result.ok(url);
    }

}
