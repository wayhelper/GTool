package com.jason.gtool.domain;

import com.jason.gtool.domain.type.Operate;
import com.jason.gtool.domain.type.RouteEnum;
import com.jason.gtool.domain.vo.Op;
import com.jason.gtool.utils.Result;

import java.util.List;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:21
 * @desciption:
 */
public interface IStrategy {
    List<Op> getOps();
    /**
     * 策略执行方法
     * @param op
     * @param data
     * @return
     */
     Result execute(Operate op, String data);
}
