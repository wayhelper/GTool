package com.way.gtool.handle;

import com.way.gtool.common.utils.Result;
import com.way.gtool.common.utils.SpringContextUtil;
import com.way.gtool.domain.IStrategy;
import com.way.gtool.domain.type.Operate;
import com.way.gtool.domain.vo.Op;
import org.springframework.ai.chat.client.ChatClient;

import java.util.Arrays;
import java.util.List;

/**
*   @author JingWei Guo
*   @date 2024/12/26 15:15
*   @desciption: 借助大模型完成翻译操作
*/

public class Translate implements IStrategy {
    private final String prompt = "你是一个翻译工具，按照要求翻译不要有多余的回答。";

    private Result toEN(String data) {
        data = SpringContextUtil.getBean(ChatClient.Builder.class).defaultSystem(prompt).build().prompt().user("请将下面的内容翻译成英文：" + data).call().content();
        return Result.get(200, "执行成功", data);
    }


    private Result toCN(String data) {
        data = SpringContextUtil.getBean(ChatClient.Builder.class).defaultSystem(prompt).build().prompt().user("请将下面的内容翻译成中文：" + data).call().content();
        return Result.get(200, "执行成功", data);
    }

    @Override
    public List<Op> getOps() {
        return List.of(
            new Op("转英文", Operate.TOEN),
            new Op("转中文", Operate.TOCN)
        );
    }

    @Override
    public Result execute(Operate op, String data) {
        if (Operate.TOEN ==op) {
            return this.toEN(data);
        } else if (Operate.TOCN==op){
            return this.toCN(data);
        } else {
            return Result.get(500, "操作失败! 未实现的方法",null);
        }
    }
}
