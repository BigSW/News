package com.com.zhihuichengdu.songwei.bean;

import java.util.ArrayList;

/**
 * Created by songwei on 2016/8/23.
 */
public class GetData {
    public int retcode;
    public ArrayList<Integer> extend ;
    public ArrayList<NewsTabData> data;



    public class NewsTabData {
        public int id;
        public int type;
        public String title;
        public ArrayList<Left_Menu> children;

    }
    public class Left_Menu{
        public  int id,type;
       public String title,url;
    }
}
