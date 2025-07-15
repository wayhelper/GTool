package com.way.gtool.handle;

import com.way.gtool.common.utils.StringUtils;
import com.way.gtool.domain.IStrategy;
import com.way.gtool.domain.type.Operate;
import com.way.gtool.domain.vo.Op;
import com.way.gtool.common.utils.Result;

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

    /**
     * 将下划线或空格转为驼峰命名法
     * @param data
     * @return
     */
    private Result camel(String data) {
        return Result.get(200, "转换成功!", StringUtils.toCamelCase(data));
    }

    /**
     * 将驼峰转为空格
     * @param data
     * @return
     */
    private Result space(String data) {
        return Result.get(200, "转换成功!", StringUtils.camelToSpace(data));
    }

    /**
     * 将驼峰转为下划线
     * @param data
     * @return
     */
    private Result underline(String data) {
        return Result.get(200, "转换成功!", StringUtils.camelToUnderscore(data));
    }

    @Override
    public List<Op> getOps() {
        return List.of(
            new Op("字母转大写", Operate.CAPITAL),
            new Op("字母转小写", Operate.LOWER),
            new Op("下划线&空格转驼峰", Operate.CAMEL),
            new Op("驼峰转空格", Operate.SPACE),
            new Op("驼峰转下划线", Operate.UNDERLINE)
        );
    }

    @Override
    public Result execute(Operate op, String data) {
        if (Operate.CAPITAL ==op) {
            return this.capital(data);
        } else if (Operate.LOWER==op) {
            return this.lower(data);
        } else if (Operate.CAMEL==op) {
            return this.camel(data);
        } else if (Operate.SPACE==op) {
            return this.space(data);
        } else if (Operate.UNDERLINE==op) {
            return this.underline(data);
        } else {
            return Result.get(500, "操作失败! 未实现的方法",null);
        }
    }
}
