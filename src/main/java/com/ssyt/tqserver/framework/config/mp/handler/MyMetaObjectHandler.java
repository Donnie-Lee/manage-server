package com.ssyt.tqserver.framework.config.mp.handler;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "createBy", () -> {
            try {
                return StpUtil.getLoginId().toString();
            } catch (Exception ignored) {
            }
            return "admin";
        }, String.class);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateBy", () -> {
            try {
                return StpUtil.getLoginId().toString();
            } catch (Exception ignored) {
            }
            return "admin";
        }, String.class);
        this.strictInsertFill(metaObject, "revision", () -> 0L, Long.class);
        this.strictInsertFill(metaObject, "deleted", () -> Boolean.FALSE, Boolean.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, "updateBy", () -> {
            try {
                return StpUtil.getLoginId().toString();
            } catch (Exception ignored) {
            }
            return "admin";
        }, String.class);
    }
}
