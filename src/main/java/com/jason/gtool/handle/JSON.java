package com.jason.gtool.handle;

import cn.hutool.json.JSONUtil;
import com.jason.gtool.domain.IStrategy;
import com.jason.gtool.domain.type.Operate;
import com.jason.gtool.domain.vo.Op;
import com.jason.gtool.utils.EscapeUtils;
import com.jason.gtool.utils.Result;

import java.util.Arrays;
import java.util.List;

/**
 * @author JingWei Guo
 * @date 2024/12/27 09:47
 * @desciption:
 */
public class JSON implements IStrategy {

    /**
     * 格式化JSON
     * @param data
     * @return
     */
    private Result format(String data) {
        return Result.get(200, "老铁这个JSON没毛病!", JSONUtil.toJsonPrettyStr(data));
    }

    /**
     * 压缩JSON
     * @param data
     * @return
     */
    private Result density(String data) {
        return Result.get(200, "压缩成功!", JSONUtil.parse(data).toString());
    }

    /**
     * 转义JSON
     * @return
     */
    private Result escape(String data){
        return Result.get(200, "转义成功!", EscapeUtils.escape(data));
    }

    /**
     * 去转义
     * @return
     */
    private Result Unescape(String data){
        return Result.get(200, "去除转义!", EscapeUtils.unescape(data));
    }

    @Override
    public List<Op> getOps() {
        return Arrays.asList(
            new Op("格式化JSON", Operate.FORMAT),
            new Op("压缩JSON", Operate.DENSITY),
            new Op("转义JSON", Operate.ESCAPE),
            new Op("去除转义", Operate.UNESCAPE)
        );
    }

    @Override
    public Result execute(Operate op, String data) {
        if (Operate.FORMAT ==op) {
            return this.format(data);
        } else if (Operate.DENSITY ==op){
            return this.density(data);
        } else if (Operate.ESCAPE ==op) {
            return this.escape(data);
        } else if (Operate.UNESCAPE ==op) {
            return this.Unescape(data);
        } else {
            return Result.get(500, "操作失败! 未实现的方法",null);
        }
    }
}
