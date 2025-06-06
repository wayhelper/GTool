package com.way.gtool.domain;

import com.way.gtool.domain.type.Operate;
import com.way.gtool.domain.vo.Op;
import com.way.gtool.common.utils.Result;

import java.util.List;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:21
 * @desciption:
 */
public interface IStrategy {
    /**
     * 获取操作列表
     * @return
     */
    List<Op> getOps();
    /**
     * 策略执行方法
     * @param op
     * @param data
     * @return
     */
     Result execute(Operate op, String data);
}
