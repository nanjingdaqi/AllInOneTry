package org.peace.allinone.http;


public class Error extends Response {

    public Error(int code, byte[] body) {
        super(code, body);
    }
}
