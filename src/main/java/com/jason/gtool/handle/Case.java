package com.jason.gtool.handle;


import com.jason.gtool.domain.IStrategy;
import com.jason.gtool.domain.type.Operate;
import com.jason.gtool.domain.vo.Op;
import com.jason.gtool.utils.Result;

import java.util.Arrays;
import java.util.List;

/**
 * @author JingWei Guo
 * @date 2024/12/30 17:04
 * @desciption: 字母大小写转换
 */
public class Case implements IStrategy {
    /**
     * 字母转大写
     * @param data
     * @return
     */
    private Result capital(String data) {
        return Result.get(200, "操作成功", data.toUpperCase());
    }

    /**
     * 字母转小写
     * @param data
     * @return
     */
    private Result lower(String data) {
        return Result.data(data.toLowerCase());
    }


    @Override
    public List<Op> getOps() {
        return Arrays.asList(
            new Op("字母转大写", Operate.CAPITAL),
            new Op("字母转小写", Operate.LOWER)
        );
    }

    @Override
    public Result execute(Operate op, String data) {
        if (Operate.CAPITAL ==op) {
            return this.capital(data);
        } else if (Operate.LOWER==op){
            return this.lower(data);
        } else {
            return Result.get(500, "操作失败! 未实现的方法",null);
        }
    }
}
