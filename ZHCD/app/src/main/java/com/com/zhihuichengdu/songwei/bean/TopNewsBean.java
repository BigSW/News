package com.com.zhihuichengdu.songwei.bean;

import java.util.ArrayList;

/**
 * Created by songwei on 2016/8/26.
 */
public class TopNewsBean {

    public  TopNewsData data;

     public  class TopNewsData {
         public  String more;
         public  String countcommenturl;
         public ArrayList<TopNewsnews> topnews;
         public  ArrayList<NewsMessage>  news;
    }

    public  class  TopNewsnews{
        public boolean comment;
        public  int id;
        public String topimage,pubdate,title,type,url,commentlist,commenturl;
    }
    /*"comment": true,
            "commentlist": "http://10.0.2.2:8080/zhbj/10006/comment_1.json",
            "commenturl": "http://zhbj.qianlong.com/client/user/newComment/35319",
            "id": 35311,
            "listimage": "http://10.0.2.2:8080/zhbj/10006/1452327318UU91.jpg",
            "pubdate": "2014-04-08 14:58",
            "title": "中国",
            "type": "news",
            "url": "http://10.0.2.2:8080/zhbj/10006/724D6A55496A11726628.html"*/


    public class NewsMessage{
          public  String commentlist,commenturl,listimage,title,type,url,pubdate;
           public int id;

    }
}
