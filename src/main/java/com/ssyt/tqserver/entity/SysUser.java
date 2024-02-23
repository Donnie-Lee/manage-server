package com.ssyt.tqserver.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssyt.tqserver.framework.config.mp.handler.type.ArrayToVarcharTypeHandler;
import com.ssyt.tqserver.framework.config.mp.handler.type.SetToVarcharTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author Lzhen
 * @since 2024-01-18
 */
@Getter
@Setter
@TableName("sys_user")
@Schema( description = "用户信息表")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description ="乐观锁")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Version
    private Long revision;

    @Schema(description ="创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description ="创建时间")
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description ="更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @Schema(description ="更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description ="逻辑删除")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;

    @Schema(description ="部门id集合")
    @TableField(typeHandler = ArrayToVarcharTypeHandler.class)
    private List<List<Long>> deptIds;

    @Schema(description ="角色id集合")
    @TableField(exist = false,typeHandler = SetToVarcharTypeHandler.class)
    private Set<Long> roleIds;

    @Schema(description ="用户名")
    private String username;

    @Schema(description ="真实姓名")
    private String realName;

    @Schema(description ="密码")
    private String password;

    @Schema(description ="性别")
    private String gender;

    @Schema(description ="邮箱")
    private String email;

    @Schema(description ="手机号")
    private String mobile;

    @Schema(description ="工号")
    private String workNo;

    @Schema(description ="头像")
    private String avatar;

    @Schema(description ="状态")
    private Boolean status;

    @Schema(description ="用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
}
