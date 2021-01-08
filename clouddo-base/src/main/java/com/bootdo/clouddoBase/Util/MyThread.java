package com.bootdo.clouddoBase.Util;

import cn.wanghaomiao.seimi.core.Seimi;

public class MyThread extends Thread{//继承Thread类

public void run(){

//重写run方法
    Seimi s = new Seimi();

    s.goRun("basic");
}

}