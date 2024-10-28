package top.crcbest.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author crc
 * @date 2024/10/27
 */

public class MybatisMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createDate", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateDate", LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateDate", LocalDateTime::now, LocalDateTime.class);
    }
}
