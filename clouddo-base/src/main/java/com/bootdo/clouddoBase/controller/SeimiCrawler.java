package com.bootdo.clouddoBase.controller;


import cn.wanghaomiao.seimi.core.Seimi;
import cn.wanghaomiao.seimi.spring.common.CrawlerCache;
import cn.wanghaomiao.seimi.struct.Request;

import com.bootdo.clouddoBase.domain.SeimicrawlerDO;
import com.bootdo.clouddoBase.screw.myscrew;
import com.bootdo.clouddoBase.seleniumhq.myselenium;
import com.bootdo.clouddoBase.service.SeimicrawlerService;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping("/SeimiCrawler")
@RestController
public class SeimiCrawler {
    @Autowired
    SeimicrawlerService seimicrawlerService;
    @GetMapping
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<SeimicrawlerDO> personList = seimicrawlerService.list(query);
        int total = seimicrawlerService.count(query);
        PageUtils pageUtils = new PageUtils(personList, total);
        return R.ok().put("page",pageUtils);
    }
    @GetMapping("/basic")
    R basic() {
        Seimi s = new Seimi();
        s.start("data");

        return R.ok();


    }
    @RequestMapping("/send_req")
    public R sendRequest(Request request){
        CrawlerCache.consumeRequest(request);
        return R.ok();
    }
    @GetMapping("/seleniumhq")
    R seleniumhq() throws InterruptedException, IOException {
        myselenium.京东秒杀();
        return R.ok();


    }
    @GetMapping("/screw")
    R screw() {
        myscrew.screw();
        return R.ok();
    }
}
