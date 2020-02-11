package com.azhar.adscriptparser.tools;

/**
 * Created by Azhar Rivaldi on 11/02/2020.
 */

public class AdsCodeParser {

    private String code;

    public AdsCodeParser() {

    }

    public void doParser(String code) {
        this.code = code;
    }

    public String getParsedCode() {
        return code
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("\'", "&#39;");
    }
}
