package com.jtj.jwtm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.web.reactive.function.BodyInserter;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/1/22.
 */
@Data
@NoArgsConstructor
public class ErrorResult {

    private String code;
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time = LocalDateTime.now();

    public enum Code{
        OK("0","成功"),
        NO_NEED_PARAMS("10000","必填参数未传"),
        NO_USER_AND_REGISTE("20001","不存在该用户，但该用户名可注册"),
        NO_USER_NOT_REGISTE("20002","不存在该用户，该用户名不可注册"),
        UN_SUPPORT_THIRD_SERVER("30001","不支持的第三方服务");

        private String code;
        private String message;

        Code(String code,String message){
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public static ErrorResult of(Code code) {
        return of(code.getCode(),code.getMessage());
    }

    public static ErrorResult of(String code, String message) {
        ErrorResult result = new ErrorResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public Mono<ErrorResult> toMono() {
        return Mono.just(this);
    }

    public BodyInserter<ErrorResult, ReactiveHttpOutputMessage> toBody() {
        return fromObject(this);
    }

}
