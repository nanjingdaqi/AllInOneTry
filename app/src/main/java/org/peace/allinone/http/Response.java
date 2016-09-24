package org.peace.allinone.http;


public class Response {
    private int code;
    private byte[] body;

    public Response(int code, byte[] body) {
        this.code = code;
        this.body = body;
    }
}
