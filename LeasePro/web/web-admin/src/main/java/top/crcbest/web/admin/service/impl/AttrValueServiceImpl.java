package top.crcbest.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.crcbest.model.entity.AttrValue;
import top.crcbest.web.admin.service.AttrValueService;
import top.crcbest.web.admin.mapper.AttrValueMapper;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【attr_value(房间基本属性值表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper, AttrValue>
    implements AttrValueService{

}




