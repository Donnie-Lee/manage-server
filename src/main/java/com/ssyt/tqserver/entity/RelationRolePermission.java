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
@TableName("relation_role_permission")
public class RelationRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "relation_role_permission_id", type = IdType.AUTO)
    private Long relationRolePermissionId;

    private Long permissionId;

    private Long roleId;

    public Long getRelationRolePermissionId() {
        return relationRolePermissionId;
    }

    public void setRelationRolePermissionId(Long relationRolePermissionId) {
        this.relationRolePermissionId = relationRolePermissionId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RelationRolePermission{" +
            "relationRolePermissionId = " + relationRolePermissionId +
            ", permissionId = " + permissionId +
            ", roleId = " + roleId +
        "}";
    }
}
