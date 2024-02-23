package com.ssyt.tqserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 用户角色关联信息表
 * </p>
 *
 * @author Lzhen
 * @since 2024-01-18
 */
@TableName("relation_user_role")
public class RelationUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "relation_user_role_id", type = IdType.AUTO)
    private Long relationUserRoleId;

    private Long userId;

    private Long roleId;

    public Long getRelationUserRoleId() {
        return relationUserRoleId;
    }

    public void setRelationUserRoleId(Long relationUserRoleId) {
        this.relationUserRoleId = relationUserRoleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RelationUserRole{" +
            "relationUserRoleId = " + relationUserRoleId +
            ", userId = " + userId +
            ", roleId = " + roleId +
        "}";
    }
}
