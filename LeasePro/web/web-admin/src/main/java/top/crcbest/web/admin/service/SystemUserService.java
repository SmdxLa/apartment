package top.crcbest.web.admin.service;

import top.crcbest.model.entity.SystemUser;
import top.crcbest.web.admin.vo.system.user.SystemUserItemVo;
import top.crcbest.web.admin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liubo
* @description 针对表【system_user(员工信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface SystemUserService extends IService<SystemUser> {

}
