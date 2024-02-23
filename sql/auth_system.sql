create table sys_user
(
    revision    int          null comment '乐观锁',
    create_by   varchar(32)  not null comment '创建人',
    create_time datetime     not null comment '创建时间',
    update_by   varchar(32)  null comment '更新人',
    update_time datetime     null comment '更新时间',
    deleted     bit          null comment '逻辑删除',
    dept_ids    text         null comment '部门id',
    username    varchar(64)  not null comment '用户名',
    real_name   varchar(64)  not null comment '真实姓名',
    password    varchar(255) not null comment '密码',
    gender      varchar(255) null comment '性别',
    email       varchar(100) null comment '邮箱',
    mobile      varchar(20)  not null comment '手机号',
    work_no     varchar(64)  null comment '工号',
    avatar      varchar(255) null comment '头像',
    status      bit          null comment '状态',
    user_id     bigint auto_increment comment '用户id'
        primary key
)
    comment '用户信息表' row_format = DYNAMIC;

create table sys_role
(
    revision       int          null comment '乐观锁',
    create_by      varchar(32)  not null comment '创建人',
    create_time    datetime     not null comment '创建时间',
    update_by      varchar(32)  null comment '更新人',
    update_time    datetime     null comment '更新时间',
    role_name      varchar(255) not null comment '角色名称',
    role_code      varchar(255) not null comment '角色编码',
    role_desc      varchar(255) null comment '角色描述',
    role_id        bigint auto_increment comment '主键id'
        primary key,
    status         bit          not null comment '状态',
    deleted        bit          not null comment '逻辑删除',
    permission_ids varchar(255) null comment '权限id',
    half_check_ids varchar(255) null comment '半选中id'
)
    comment '角色信息表' row_format = DYNAMIC;

create table sys_permission
(
    revision        int    default 0 not null comment '乐观锁',
    create_by       varchar(32)      not null comment '创建人',
    create_time     datetime         not null comment '创建时间',
    update_by       varchar(32)      null comment '更新人',
    update_time     datetime         null comment '更新时间',
    deleted         bit              not null comment '逻辑删除',
    permission_code varchar(255)     not null comment '权限编码',
    permission_name varchar(255)     not null comment '权限名称',
    icon            varchar(255)     null comment '图标',
    type            varchar(255)     not null comment '权限类型',
    path            varchar(255)     null comment '路径',
    redirect        varchar(255)     null comment '重定向路径',
    component       varchar(255)     null comment '组件',
    sort_no         int    default 0 null comment '排序号',
    hidden          bit              null comment '是否隐藏',
    parent_id       bigint default 0 not null comment '父级权限',
    permission_id   bigint auto_increment comment '权限id'
        primary key,
    status          bit              not null comment '状态',
    focus_menu      bigint           null comment '聚焦菜单',
    constraint unique_code
        unique (permission_code)
)
    comment '权限信息表' row_format = DYNAMIC;

create table relation_role_permission
(
    relation_role_permission_id bigint auto_increment comment '主键id'
        primary key,
    permission_id               bigint not null comment '权限id',
    role_id                     bigint not null comment '角色id'
)
    comment '用户角色关联信息表' row_format = DYNAMIC;

create table relation_user_role
(
    relation_user_role_id bigint auto_increment comment '主键id'
        primary key,
    user_id               bigint not null comment '用户id',
    role_id               bigint not null comment '角色id'
)
    comment '用户角色关联信息表' row_format = DYNAMIC;





