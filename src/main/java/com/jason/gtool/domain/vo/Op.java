package com.jason.gtool.domain.vo;

import com.jason.gtool.domain.type.Operate;
import com.jason.gtool.domain.type.RouteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @author JingWei Guo
 * @date 2024/12/28 13:34
 * @desciption:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Op {
    private String name;
    private Operate op;

    /**
     * 根据路由获取操作列表
     */
    public static List<Op> getOpsByRoute(RouteEnum route) {
        if (route == RouteEnum.BASE64) {
            return Arrays.asList(
                new Op("Base64加密", Operate.ENCRYPT),
                new Op("Base64解密", Operate.DECRYPT)
            );
        } else if (route == RouteEnum.JSON) {
            return Arrays.asList(
                new Op("格式化JSON", Operate.FORMAT),
                new Op("压缩JSON", Operate.DENSITY)
            );
        } else if (route == RouteEnum.UNICODE) {
            return Arrays.asList(
                new Op("Unicode加密", Operate.ENCRYPT),
                new Op("Unicode解密", Operate.DECRYPT)
            );
        }else { //默认json
            return Arrays.asList(
                new Op("格式化JSON", Operate.FORMAT),
                new Op("压缩JSON", Operate.DENSITY)
            );
        }
    }
}
