package com.xiaoluban.tmallprotal.exception;

import com.xiaoluban.tmallcommon.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: txb
 * @Date: 20210210
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHander {

    @ExceptionHandler(Exception.class)
    public CommonResult excptionHandle(Throwable e){
        log.error("未知异常",e);
        return CommonResult.syserror(e.getMessage());
    }


}
