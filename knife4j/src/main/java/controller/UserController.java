package controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author crc
 * @date 2024/10/27
 */
@Tag(name = "用户接口", description = "用户信息管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Operation(summary = "查询用户信息")
    @GetMapping("/get")
    public String get() {
        return "get";
    }

}
