package com.lb.learnsong.bean;

public class LyricsInfo {
//    "aname": "明天过后",专辑名称 <string>
//"collectCount": 1,歌曲收藏总数 <number>
//"isCollect": 1,是否收藏（0：未收藏 1：已收藏） <number>
//"synopsis": "没有星星的夜空问明天过后 山明,歌词 <string>
//"type": 0,类型（0：专辑 1：单曲） <number>
//"translate": "翻译1",翻译 <string>
//"isMyself": 1,是否是自己发布 <number>
//"sid": 1,歌手id <number>
//"cover": "https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike60%2C5%2C5%2C60%2C20/sign=e9f37320ccbf6c81e33a24badd57da50/a08b87d6277f9e2f72798a401f30e924b899f32e.jpg",封面 <string>
//"lname": "明天过后",歌曲名称 <string>
//"sname": "张杰",歌手名称 <string>
//"isConcern": 0,是否关注（0：未关注 1：已关注） <number>
//"isPraise": 1,是否点赞（0：未点赞 1：已点赞） <number>
//"id": 1歌曲id <number>
    private String aname;
    private int collectCount;
    private int isCollcet;
    private String synopsis;
    private String translate;
    private String cover;
    private String lname;
    private String sname;
    private int type;
    private int isMyself;
    private int sid;
    private int isConcern;
    private int isPraise;
    private int id;

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public int getIsCollcet() {
        return isCollcet;
    }

    public void setIsCollcet(int isCollcet) {
        this.isCollcet = isCollcet;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsMyself() {
        return isMyself;
    }

    public void setIsMyself(int isMyself) {
        this.isMyself = isMyself;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getIsConcern() {
        return isConcern;
    }

    public void setIsConcern(int isConcern) {
        this.isConcern = isConcern;
    }

    public int getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(int isPraise) {
        this.isPraise = isPraise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
