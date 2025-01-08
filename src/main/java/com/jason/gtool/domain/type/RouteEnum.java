package com.jason.gtool.domain.type;

import com.jason.gtool.handle.*;
import com.jason.gtool.domain.IStrategy;
import lombok.Getter;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:08
 * @desciption: 类型枚举类
 */

@Getter
public enum RouteEnum {
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

}
