package com.jason.gtool.handle;

import com.jason.gtool.common.utils.DESPlus;
import com.jason.gtool.common.utils.Result;
import com.jason.gtool.common.utils.StringUtils;
import com.jason.gtool.domain.IStrategy;
import com.jason.gtool.domain.type.Operate;
import com.jason.gtool.domain.vo.Op;

import java.util.Arrays;
import java.util.List;

public class Cryption implements IStrategy {
    /**
     * 加密md5
     * @param data
     * @return
     */
    private Result emd5(String data) {
        return Result.get(200, "加密成功", StringUtils.encryptMD5(data));
    }

    /**
     * 加密des
     * @param data
     * @return
     */
    private Result edes(String data) {
        String[] dataArray = StringUtils.splitByLastComma(data);
        try {
            return Result.get(200, "加密成功", new DESPlus(dataArray[1]).encrypt(dataArray[0]));
        } catch (Exception e) {
            return Result.get(200, "加密失败"+e.getMessage(), null);
        }
    }

    /**
     * 解密des
     * @param data
     * @return
     */
    private Result ddes(String data) {
        String[] dataArray = StringUtils.splitByLastComma(data);
        try {
            return Result.get(200, "解密成功", new DESPlus(dataArray[1]).decrypt(dataArray[0]));
        } catch (Exception e) {
            return Result.get(200, "解密失败"+e.getMessage(), null);
        }
    }
    @Override
    public List<Op> getOps() {
        return Arrays.asList(
            new Op("加密md5", Operate.EMD5),
            new Op("加密des", Operate.EDES),
            new Op("解密des", Operate.DDES)
        );
    }

    @Override
    public Result execute(Operate op, String data) {
        if (Operate.EMD5 ==op) {
            return this.emd5(data);
        } else if (Operate.EDES == op) {
            return this.edes(data);
        } else if (Operate.DDES == op) {
            return this.ddes(data);
        } else {
            return Result.get(500, "操作失败! 未实现的方法",null);
        }
    }
}
