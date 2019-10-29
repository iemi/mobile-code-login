package com.lingyumin.mobilecodelogin.sms;

/**
 * [简要描述]简单返回体
 * [详细描述]:
 *
 * @author:
 * @date: 11:29 AM 2019/10/12
 * @since: JDK 1.8
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
