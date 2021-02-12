package com.xiaoluban.tmallcommon.util;

import com.github.pagehelper.PageHelper;

/**
 * @Author: txb
 * @Date: 20210210
 */
public class PageUtil {

    private PageUtil(){

    }

    public static void startPage(int pageNum,int pageSize){
        if(pageNum<=0){
            pageNum=1;
        }
        if(pageSize<=0){
            pageSize=10;
        }
        PageHelper.startPage(pageNum,pageSize);
    }
}
