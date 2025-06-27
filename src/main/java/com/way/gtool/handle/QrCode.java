package com.way.gtool.handle;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.way.gtool.common.utils.Result;
import com.way.gtool.domain.IStrategy;
import com.way.gtool.domain.type.Operate;
import com.way.gtool.domain.vo.Op;
import cn.hutool.core.codec.Base64;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

/**
 * @author Jingway
 * @date 2025/6/27 14:49
 * @description:
 */
public class QrCode implements IStrategy {
    /**
     * 生成二维码
     * @param data
     * @return
     */
    private Result en(String data) {
        try {
            QrConfig config = new QrConfig(300, 300);
            return Result.get(200, "二维码生成成功!", QrCodeUtil.generateAsBase64(data, config, ""));
        } catch (Exception e) {
            return Result.get(500, "二维码生成失败", null);
        }
    }

    /**
     * 解析base64二维码
     * @param data
     * @return
     */
    private Result de(String data) {
        byte[] decodedBytes = Base64.decode(data);
        return Result.get(200, "二维码解析成功!", QrCodeUtil.decode(new ByteArrayInputStream(decodedBytes)));
    }

    @Override
    public List<Op> getOps() {
        return List.of(
            new Op("生成二维码", Operate.ENQRCODE),
            new Op("解析二维码", Operate.DEQRCODE)
        );
    }

    @Override
    public Result execute(Operate op, String data) {
        if (Operate.ENQRCODE == op) {
            // 生成二维码逻辑
            return this.en(data);
        } else if (Operate.DEQRCODE == op) {
            // 解析二维码逻辑
            return this.de(data);
        } else {
            return Result.get(500, "操作失败! 未实现的方法", null);
        }
    }
}
