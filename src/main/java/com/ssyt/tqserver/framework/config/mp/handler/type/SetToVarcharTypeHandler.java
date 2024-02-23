package com.ssyt.tqserver.framework.config.mp.handler.type;

import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Set.class)
public class SetToVarcharTypeHandler implements TypeHandler<Set<Long>> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Set<Long> strings, JdbcType jdbcType) throws SQLException {
        // 遍历List类型的入参，拼装为String类型，使用Statement对象插入数据库
        preparedStatement.setString(i, StringUtils.join(strings, ","));
    }

    @Override
    public Set<Long> getResult(ResultSet resultSet, String s) throws SQLException {
        // 获取String类型的结果，使用","分割为List后返回
        String resultString = resultSet.getString(s);
        if (StringUtils.isNotEmpty(resultString)) {
            return new HashSet<>(Arrays.asList(Convert.toLongArray(resultString.split(","))));
        }
        return null;
    }

    @Override
    public Set<Long> getResult(ResultSet resultSet, int i) throws SQLException {
        // 获取String类型的结果，使用","分割为List后返回
        String resultString = resultSet.getString(i);
        if (StringUtils.isNotEmpty(resultString)) {
            return new HashSet<>(Arrays.asList(Convert.toLongArray(resultString.split(","))));
        }
        return null;
    }

    @Override
    public Set<Long> getResult(CallableStatement callableStatement, int i) throws SQLException {
        // 获取String类型的结果，使用","分割为List后返回
        String resultString = callableStatement.getString(i);
        if (StringUtils.isNotEmpty(resultString)) {
            return new HashSet<>(Arrays.asList(Convert.toLongArray(resultString.split(","))));
        }
        return null;
    }


}
