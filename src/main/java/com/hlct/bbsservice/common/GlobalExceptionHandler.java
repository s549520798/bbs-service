package com.hlct.bbsservice.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
@ControllerAdvice
public class GlobalExceptionHandler {
    public static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    public static final String DEFAULT_ERROR_VIEW = "error";
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView HtmlErrorHandler(HttpServletRequest request, Exception e){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("exception", e.getMessage());
//        log.info(e.getMessage());
//        modelAndView.addObject("url", request.getRequestURL());
//        log.info(request.getRequestURL().toString());
//        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
//        return modelAndView;
//    }
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultInfo<String> JsonErrorHandler(HttpServletRequest request, Exception e){

        ResultInfo<String> resultInfo = new ResultInfo<>();
        HttpStatus status = getStatus(request);
        resultInfo.setCode(status.value());
        resultInfo.setMessage(e.getMessage());
        resultInfo.setData("error url : " + request.getRequestURI() + ", status : " + status.name());
        log.error(resultInfo.toString());
        return resultInfo;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
