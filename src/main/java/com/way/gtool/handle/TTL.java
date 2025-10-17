package com.way.gtool.handle;

import com.way.gtool.common.utils.Result;
import com.way.gtool.common.utils.SpringContextUtil;
import com.way.gtool.domain.IStrategy;
import com.way.gtool.domain.type.Operate;
import com.way.gtool.domain.vo.Op;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;

import java.util.Base64;
import java.util.List;

/**
 * @author Jingway
 * @date 2025/7/8 17:04
 * @description: 音频
 */
public class TTL implements IStrategy {

    private Result toVoice(String data){
        SpeechPrompt speechPrompt = new SpeechPrompt(data);
        SpeechResponse response = SpringContextUtil.getBean(OpenAiAudioSpeechModel.class).call(speechPrompt);
        return Result.get(200, "转换成功", Base64.getEncoder().encodeToString(response.getResult().getOutput()));
    }

    @Override
    public List<Op> getOps() {
        return List.of(
            new Op("文字转语音", Operate.TEXTTOVOICE)
        );
    }

    @Override
    public Result execute(Operate op, String data) {
        if (Operate.TEXTTOVOICE ==op) {
            return this.toVoice(data);
        } else {
            return Result.get(500, "操作失败! 未实现的方法",null);
        }
    }
}
