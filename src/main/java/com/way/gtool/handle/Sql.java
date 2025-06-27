package com.way.gtool.handle;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.way.gtool.domain.IStrategy;
import com.way.gtool.domain.type.Operate;
import com.way.gtool.domain.vo.Op;
import com.way.gtool.common.utils.Result;

import java.util.Arrays;
import java.util.List;

/**
 * @author JingWei Guo
 * @date 2024/12/30 17:16
 * @desciption: sql工具
 */
public class Sql implements IStrategy {
    private Result mysql(String data) {
        return Result.get(200, "美化成功", SQLUtils.formatMySql(data));
    }

    private Result oracle(String data) {
        return Result.get(200, "美化成功", SQLUtils.formatOracle(data));
    }

    private Result pgsql(String data) {
        return Result.get(200, "美化成功", SQLUtils.format(data, DbType.postgresql));
    }
    @Override
    public List<Op> getOps() {
        return List.of(
            new Op("Mysql美化", Operate.MYSQL),
            new Op("Oracle美化", Operate.ORACLE),
            new Op("Pgsql美化", Operate.PGSQL)
        );
    }

    @Override
    public Result execute(Operate op, String data) {
        if (Operate.MYSQL ==op) {
            return this.mysql(data);
        } else if (Operate.ORACLE==op){
            return this.oracle(data);
        } else if (Operate.PGSQL==op){
            return this.pgsql(data);
        }else {
            return Result.get(500, "操作失败! 未实现的方法",null);
        }
    }
}
