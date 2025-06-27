package com.way.gtool.handle;

import com.way.gtool.domain.IStrategy;
import com.way.gtool.domain.type.Operate;
import com.way.gtool.domain.vo.Op;
import com.way.gtool.common.utils.Result;

import java.util.Arrays;
import java.util.List;

/**
*   @author JingWei Guo
*   @date 2024/12/26 15:15
*   @desciption: base64操作
*/
public class Base64 implements IStrategy {

    private Result encrypt(String data) {
        return Result.get(200, "加密成功", cn.hutool.core.codec.Base64.encode(data));
    }


    private Result decrypt(String data) {
        return Result.get(200, "解密成功", cn.hutool.core.codec.Base64.decodeStr(data));
    }

    @Override
    public List<Op> getOps() {
        return List.of(
            new Op("Base64加密", Operate.ENCRYPT),
            new Op("Base64解密", Operate.DECRYPT)
        );
    }

    @Override
    public Result execute(Operate op, String data) {
        if (Operate.DECRYPT ==op) {
            return this.decrypt(data);
        } else if (Operate.ENCRYPT==op){
            return this.encrypt(data);
        } else {
            return Result.get(500, "操作失败! 未实现的方法",null);
        }
    }
}
