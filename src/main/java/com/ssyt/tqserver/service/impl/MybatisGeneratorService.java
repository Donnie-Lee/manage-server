package com.ssyt.codegenerator.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MybatisGeneratorService {
    public void generate(String module, String url, String username, String password, List<String> tables, List<String> tablePrefixs) {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(getEnv("user.name")) // 设置作者
                            .outputDir(getEnv("user.dir") + File.separator + module + File.separator + "src/main/java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.ssyt") // 设置父包名
                            .moduleName(module) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, getEnv("user.dir") + File.separator + module + File.separator + "src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    if (CollectionUtil.isNotEmpty(tables)) {
                        builder.addInclude(tables);
                    }
                    if (CollectionUtil.isNotEmpty(tablePrefixs)) {
                        builder.addTablePrefix(tablePrefixs);
                    }
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    // 读取环境变量
    public String getEnv(String name) {
        return System.getProperty(name);
    }


    public static void main(String[] args) {
        MybatisGeneratorService mybatisGeneratorService = new MybatisGeneratorService();
        mybatisGeneratorService.generate(null, "jdbc:mysql://vm.local:3306/auth_system?serverTimezone=UTC", "root", "root", Arrays.asList("contact_info"),null);
    }
}
