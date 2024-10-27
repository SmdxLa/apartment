package top.crcbest.web.admin.vo.attr;

import top.crcbest.model.entity.AttrValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "属性值")
@Data
public class AttrValueVo extends AttrValue {

    @Schema(description = "对应的属性key_name")
    private String attrKeyName;
}
