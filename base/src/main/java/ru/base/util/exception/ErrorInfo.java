package ru.base.util.exception;

import org.springframework.core.NestedExceptionUtils;

public class ErrorInfo {
    public final String url;
    public final String cause;
    public final ErrorType  type;
    public final String detail;


    public ErrorInfo(CharSequence url, Throwable ex) {
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        this.detail = ex.getLocalizedMessage();
        this.type = null;
        //this.detail = NestedExceptionUtils.buildMessage("",ex);
    }
    public ErrorInfo(CharSequence url, ErrorType type, String detail) {
        this.url = url.toString();
        this.type = type;
        this.cause = "";
        this.detail = detail;
    }


}
