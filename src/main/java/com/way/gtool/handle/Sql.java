package com.way.gtool.handle;

import com.way.gtool.common.utils.SpringContextUtil;
import com.way.gtool.domain.IStrategy;
import com.way.gtool.domain.type.Operate;
import com.way.gtool.domain.vo.Op;
import com.way.gtool.common.utils.Result;
import org.springframework.ai.chat.client.ChatClient;

import java.util.List;

/**
 * @author JingWei Guo
 * @date 2024/12/30 17:16
 * @desciption: sql工具
 */
public class Sql implements IStrategy {
    private String prompt="你是一个SQL美化工具，按照要求美化SQL语句，不要有多余的回答。";
    private Result mysql(String data) {
        SQL beauty = SpringContextUtil.getBean(ChatClient.Builder.class)
            .defaultSystem(prompt).build()
            .prompt()
            .user("请将下面的mysql语句美化：" + data)
            .call().entity(SQL.class);
        return Result.get(200, "美化成功", beauty.sql);
    }

    private Result oracle(String data) {
        SQL beauty = SpringContextUtil.getBean(ChatClient.Builder.class)
            .defaultSystem(prompt).build()
            .prompt()
            .user("请将下面的oracle语句美化：" + data)
            .call().entity(SQL.class);
        return Result.get(200, "美化成功", beauty.sql);
    }

    private Result pgsql(String data) {
        SQL beauty = SpringContextUtil.getBean(ChatClient.Builder.class)
            .defaultSystem(prompt).build()
            .prompt()
            .user("请将下面的postgresSQL语句美化：" + data)
            .call().entity(SQL.class);
        return Result.get(200, "美化成功", beauty.sql);
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
        } else if (Operate.ORACLE==op) {
            return this.oracle(data);
        } else if (Operate.PGSQL==op) {
            return this.pgsql(data);
        } else {
            return Result.get(500, "操作失败! 未实现的方法",null);
        }
    }

    public record SQL(String sql){};
}
