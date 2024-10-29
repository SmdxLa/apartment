package top.crcbest.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.crcbest.common.result.Result;
import top.crcbest.model.entity.FeeKey;
import top.crcbest.model.entity.FeeValue;
import top.crcbest.web.admin.service.FeeKeyService;
import top.crcbest.web.admin.service.FeeValueService;
import top.crcbest.web.admin.vo.fee.FeeKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Tag(name = "房间杂费管理")
@RestController
@RequestMapping("/admin/fee")
public class FeeController {

    @Autowired
    private FeeKeyService feeKeyService;

    @Autowired
    private FeeValueService feeValueService;
    @Operation(summary = "保存或更新杂费名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateFeeKey(@RequestBody FeeKey feeKey) {
        feeKeyService.saveOrUpdate(feeKey);
        return Result.ok();
    }

    @Operation(summary = "保存或更新杂费值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateFeeValue(@RequestBody FeeValue feeValue) {
        feeValueService.saveOrUpdate(feeValue);
        return Result.ok();
    }


    @Operation(summary = "查询全部杂费名称和杂费值列表")
    @GetMapping("list")
    public Result<List<FeeKeyVo>> feeInfoList() {
        List<FeeKeyVo> feeKeyVoList =  new ArrayList<>();//最终返回的结果
        feeKeyService.list().forEach(feeKey -> {
            FeeKeyVo feeKeyVo = new FeeKeyVo();
            List<FeeValue> feeValueList =
                    feeValueService.list(new LambdaQueryWrapper<FeeValue>().eq(FeeValue::getFeeKeyId, feeKey.getId()));
            BeanUtils.copyProperties(feeKey, feeKeyVo);
            feeKeyVo.setFeeValueList(feeValueList);
            feeKeyVoList.add(feeKeyVo);
        });
        return Result.ok(feeKeyVoList);
    }

    @Operation(summary = "根据id删除杂费名称")
    @DeleteMapping("key/deleteById")
    public Result deleteFeeKeyById(@RequestParam Long feeKeyId) {
        feeKeyService.removeById(feeKeyId);
        feeValueService.remove(new LambdaQueryWrapper<FeeValue>().eq(FeeValue::getFeeKeyId, feeKeyId));
        return Result.ok();
    }

    @Operation(summary = "根据id删除杂费值")
    @DeleteMapping("value/deleteById")
    public Result deleteFeeValueById(@RequestParam Long id) {
        feeValueService.removeById(id);
        return Result.ok();
    }
}
