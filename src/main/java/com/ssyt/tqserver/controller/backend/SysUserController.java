package com.ssyt.tqserver.controller.backend;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyt.tqserver.pojo.response.ResponseResult;
import com.ssyt.tqserver.entity.SysUser;
import com.ssyt.tqserver.service.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Lzhen
 * @since 2024-01-18
 */
@Tag(name = "管理后台：用户管理")
@RestController
@RequestMapping("/sysUser")
public record SysUserController(ISysUserService sysUserService) {

    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    @Operation(summary = "分页条件查询")
    @GetMapping("list")
    public ResponseResult<Page<SysUser>> list(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "name", required = false) String name) {
        return ResponseResult.success(sysUserService.list(pageNo, pageSize, name));
    }

}
