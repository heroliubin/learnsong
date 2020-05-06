package com.lb.learnsong.bean;

public class WordInfo {

//    "id": 1,id <number>
//"targetword": "word",源单词 <string>
//"addtime": "2019-10-29 05:17:28",添加时间 <string>
//"addip": "127.0.0.1",添加ip <string>
//"phonetic": "123",发音音标，除了英语，别的语言可能没有 <string>
//"chineseword": "单词",翻译后的中文单词 <string>
//"voicefileurl": "123",外语单词的发音音频文件的保存地址，可能是本地，可能是网络 <string>
//"file": "123",文件 <string>
//"isCollect": 1是否收藏 （1：表示已收藏 其余表示未收藏） <number>
    private int id;
    private String targetWord;
    private String targetword;
    private String addtime;
    private String addip;
    private String phonetic;
    private String chineseWord;
    private String chineseword;
    private String voicefileurl;
    private String file;
    private int  isCollect;

    public int getId() {
        return id;
    }

    public String getTargetword() {
        return targetword;
    }

    public String getAddtime() {
        return addtime;
    }

    public String getAddip() {
        return addip;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public String getChineseword() {
        return chineseword;
    }

    public String getVoicefileurl() {
        return voicefileurl;
    }

    public String getFile() {
        return file;
    }

    public int getIsCollect() {
        return isCollect;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public String getChineseWord() {
        return chineseWord;
    }

    public WordInfo(String targetword,
                    String phonetic, String chineseword, String voicefileurl, String file) {

        this.targetword = targetword;
        this.phonetic = phonetic;
        this.chineseword = chineseword;
        this.voicefileurl = voicefileurl;
        this.file = file;
    }
}
