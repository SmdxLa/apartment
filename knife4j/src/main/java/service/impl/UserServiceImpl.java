package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.User;
import service.UserService;
import mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author SmdxLa
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-10-27 16:15:20
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




