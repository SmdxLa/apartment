package top.crcbest.web.admin.controller.apartment;


import org.springframework.beans.factory.annotation.Autowired;
import top.crcbest.common.result.Result;
import top.crcbest.model.entity.PaymentType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import top.crcbest.web.admin.service.PaymentTypeService;

import java.util.List;


@Tag(name = "支付方式管理")
@RequestMapping("/admin/payment")
@RestController
public class PaymentTypeController {

    @Autowired
    private PaymentTypeService paymentTypeService;

    @Operation(summary = "查询全部支付方式列表")
    @GetMapping("list")
    public Result<List<PaymentType>> listPaymentType() {
        return Result.ok(paymentTypeService.list());
    }

    @Operation(summary = "保存或更新支付方式")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdatePaymentType(@RequestBody PaymentType paymentType) {
        paymentTypeService.saveOrUpdate(paymentType);
        return Result.ok();
    }

    @Operation(summary = "根据ID删除支付方式")
    @DeleteMapping("deleteById")
    public Result deletePaymentById(@RequestParam Long id) {
        paymentTypeService.removeById(id);
        return Result.ok();
    }

}















