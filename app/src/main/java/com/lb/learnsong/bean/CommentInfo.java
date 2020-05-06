package com.lb.learnsong.bean;

public class CommentInfo {
//    "headimg": "http://120.24.185.40:8088/image/music/201908271610477849.jpg",用户头像 <string>
//"commentUpId": 0,评论上级id <number>
//"ismyself": 0,是否是自己评论（0：不是 1：是） <boolean>
//            "nickName": "大洼渴泽",昵称 <string>
//"addtime": "2019-09-04 09:33:33",添加时间 <string>
//"commentid": 9,评论id <string>
//"praisecount": 0,点赞总条数 <number>
//"type": 0,类型（0：文字 1：图片 2：语音 3:视频） <number>
//"ispraise": 0,是否已点赞（0：否 1：是） <boolean>
//            "content": "   不跟共同语言了吧！你是个男人吗！"评论类型 <string>
    private String headimg;
    private String commentUpId;
    private int ismyself;
    private String nickName;
    private String addtime;
    private String commentid;
    private int praisecount;
    private int type;
    private int ispraise;
    private String content;

    public String getHeadimg() {
        return headimg;
    }

    public String getCommentUpId() {
        return commentUpId;
    }

    public int isIsmyself() {
        return ismyself;
    }

    public String getNickname() {
        return nickName;
    }

    public String getAddtime() {
        return addtime;
    }

    public String getCommentid() {
        return commentid;
    }

    public int getPraisecount() {
        return praisecount;
    }

    public int getType() {
        return type;
    }

    public int isIspraise() {
        return ispraise;
    }

    public String getContent() {
        return content;
    }
}
