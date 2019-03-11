package com.zeus.realz.web;

import com.zeus.realz.service.HtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liping.zheng
 * @Date: 2019/3/11
 */
@RestController
@RequestMapping("/public")
public class IntegrationResource {
    @Autowired
    private HtmlService htmlService;

    @GetMapping("/lianhanghao")
    public void lianhanghao(@RequestParam Integer startPage, @RequestParam Integer endPage) throws InterruptedException {
        htmlService.lianhanghaoGet(startPage, endPage);
    }

    @GetMapping("/lianhanghao/db")
    public void lianhanghaoForDB(@RequestParam Integer startPage, @RequestParam Integer endPage) throws InterruptedException {
        htmlService.lianhanghaoGetForDB(startPage, endPage);
    }
}
