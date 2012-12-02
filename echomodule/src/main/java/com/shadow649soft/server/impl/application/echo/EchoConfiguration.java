package com.shadow649soft.server.impl.application.echo;

import com.shadow649soft.server.api.configuration.Configuration;

public class EchoConfiguration implements Configuration {
    
    private String endWord;
    
    public void setEndWord(String endWord) {
        this.endWord = endWord;
    }
    
    public String getEndRequestWord() {
        return endWord;
    }

}
