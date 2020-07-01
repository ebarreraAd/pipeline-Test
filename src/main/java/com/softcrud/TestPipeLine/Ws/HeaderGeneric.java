package com.softcrud.TestPipeLine.Ws;

public class HeaderGeneric {
    public HeaderGeneric(String severity, int code, String message) {
        this.severity = severity;
        this.code = code;
        this.message = message;
    }

    public String severity;
    public int code;
    public String message;
}
