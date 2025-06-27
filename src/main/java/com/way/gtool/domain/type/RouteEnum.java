package com.way.gtool.domain.type;

import com.way.gtool.handle.*;
import com.way.gtool.domain.IStrategy;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:08
 * @desciption: 类型枚举类
 */

public enum RouteEnum {
    QRCODE(new QrCode()),
    TRANSLATE(new Translate()),
    CRYPTION(new Cryption()),
    UNIX(new UNIX()),
    SQL(new Sql()),
    CASE(new Case()),
    UNICODE(new Unicode()),
    BASE64(new Base64()),
    JSON(new JSON());

    private final IStrategy strategy;

    RouteEnum(IStrategy strategy) {
        this.strategy = strategy;
    }

    public IStrategy getStrategy() {
        return strategy;
    }
}
