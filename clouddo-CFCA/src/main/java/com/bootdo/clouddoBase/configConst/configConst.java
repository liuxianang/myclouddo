package com.bootdo.clouddoBase.configConst;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class configConst {
    @Value("${Const}")
    private  String test;


    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
