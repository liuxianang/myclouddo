package com.bootdo.clouddoBase.configConst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class configConstHelper {
    public static String test;
    @Autowired
    public void setTest(configConst ConfigConst){
        this.test=ConfigConst.getTest();
    }
}
