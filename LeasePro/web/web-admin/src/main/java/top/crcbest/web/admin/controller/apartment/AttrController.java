package top.crcbest.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.crcbest.common.result.Result;
import top.crcbest.model.entity.AttrKey;
import top.crcbest.model.entity.AttrValue;
import top.crcbest.web.admin.service.AttrKeyService;
import top.crcbest.web.admin.service.AttrValueService;
import top.crcbest.web.admin.vo.attr.AttrKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间属性管理
 * 之所以那么麻烦，和标签不一样，是因为它多了增加属性的功能
 * 标签只有两种类型（itemType），在枚举写死了，而这个能动态调整
 * 所以两个表，一个表放名字（即种类），一个表放值（每个种类的值）
 */

@Tag(name = "房间属性管理")
@RestController
@RequestMapping("/admin/attr")
public class AttrController {

    @Autowired
    private AttrKeyService attrKeyService;

    @Autowired
    private AttrValueService attrValueService;

    @Operation(summary = "新增或更新属性名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateAttrKey(@RequestBody AttrKey attrKey) {
        attrKeyService.saveOrUpdate(attrKey);
        return Result.ok();
    }

    @Operation(summary = "新增或更新属性值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateAttrValue(@RequestBody AttrValue attrValue) {
        attrValueService.saveOrUpdate(attrValue);
        return Result.ok();
    }


    @Operation(summary = "查询全部属性名称和属性值列表")
    @GetMapping("list")
    public Result<List<AttrKeyVo>> listAttrInfo() {
        //因为两个表，所以可以先查出所有的属性名称，再根据属性名称查出属性值
        List<AttrKeyVo> attrKeyVoList = new ArrayList<>();//最终返回的结果
        List<AttrKey> attrKeys = attrKeyService.list();//查出所有的属性名称
        attrKeys.forEach(attrKey -> {
            System.out.println(attrKey.getId());
            System.out.println(attrKey.toString());
            List<AttrValue> attrValuesList =//查出当前属性的值
                    attrValueService.list(new LambdaQueryWrapper<AttrValue>().eq(AttrValue::getAttrKeyId, attrKey.getId()));
            System.out.println(attrValuesList.toString());
            AttrKeyVo attrKeyVo = new AttrKeyVo();
            BeanUtils.copyProperties(attrKey, attrKeyVo);
            attrKeyVo.setAttrValueList(attrValuesList);
            attrKeyVoList.add(attrKeyVo);
        });
        return Result.ok(attrKeyVoList);
    }

    @Operation(summary = "根据id删除属性名称")
    @DeleteMapping("key/deleteById")
    public Result removeAttrKeyById(@RequestParam Long attrKeyId) {
        attrKeyService.removeById(attrKeyId);
        attrValueService.remove(new LambdaQueryWrapper<AttrValue>().eq(AttrValue::getAttrKeyId, attrKeyId));
        return Result.ok();
    }

    @Operation(summary = "根据id删除属性值")
    @DeleteMapping("value/deleteById")
    public Result removeAttrValueById(@RequestParam Long id) {
        attrValueService.removeById(id);
        return Result.ok();
    }

}
