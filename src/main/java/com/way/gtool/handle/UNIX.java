package com.way.gtool.handle;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.way.gtool.domain.IStrategy;
import com.way.gtool.domain.type.Operate;
import com.way.gtool.domain.vo.Op;
import com.way.gtool.common.utils.Result;

import java.util.Arrays;
import java.util.List;

/**
 *时间戳
 */
public class UNIX implements IStrategy {
    /**
     * 时间戳
     * @param data
     * @return
     */
    private Result timestamp(String data) {
        long timestamp = DateUtil.parse(data).getTime() / 1000;
        return Result.get(200, "转换成功", StrUtil.toString(timestamp));
    }
    private Result date(String data) {
        long timestamp = Long.parseLong(data);
        // 如果时间戳长度为秒级 (10位)，乘以1000转换为毫秒级
        if (data.length() == 10) {
            timestamp *= 1000;
        }else if (data.length() == 13) {
            // 如果时间戳长度为毫秒级 (13位)，直接使用
        } else {
            return Result.get(500, "时间戳格式错误",null);
        }
        return Result.get(200, "转换成功", DateUtil.date(timestamp).toString());
    }
    @Override
    public List<Op> getOps() {
        return List.of(
            new Op("转时间戳", Operate.TIMESTAMP),
            new Op("转日期", Operate.DATE)
        );
    }

    @Override
    public Result execute(Operate op, String data) {
        if (Operate.TIMESTAMP ==op) {
            return this.timestamp(data);
        } else if (Operate.DATE==op){
            return this.date(data);
        } else {
            return Result.get(500, "操作失败! 未实现的方法",null);
        }
    }
}
