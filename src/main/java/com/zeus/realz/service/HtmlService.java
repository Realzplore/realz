package com.zeus.realz.service;

import com.zeus.realz.domain.RealzBank;
import com.zeus.realz.persistence.service.RealzBankService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;

/**
 * @Author: liping.zheng
 * @Date: 2019/3/11
 */
@Service
public class HtmlService {
    private static final String LIANHANGHAO_URL = "http://www.lianhanghao.com/index.php/Index/index/p/{0}.html";

    @Autowired
    private RealzBankService realzBankService;

    public void lianhanghaoGet(final Integer startPage, final Integer endPage) throws InterruptedException {
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("联行号");
        //标题
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("开户行联行行号");
        cell = row.createCell(1);
        cell.setCellValue("开户行名称");
        int rowNum = 1;
        try (OutputStream fileOut = new FileOutputStream("D:/联行号.xlsx")) {
            int page = startPage;
            while (page <= endPage && page > 0) {
                String tempUrl = MessageFormat.format(LIANHANGHAO_URL, page);
                try {
                    Document doc = Jsoup.connect(tempUrl).get();
                    Elements table = doc.getElementsByClass("t2");
                    Element data = table.first();
                    for (Element tableChild : data.children()) {
                        if (!tableChild.tag().toString().equals("tbody")) {
                            continue;
                        }
                        for (Element tr : tableChild.children()) {
                            Row childRow = sheet.createRow(rowNum++);
                            Cell childCell = childRow.createCell(0);
                            childCell.setCellValue(tr.child(0).text());
                            childCell = childRow.createCell(1);
                            childCell.setCellValue(tr.child(1).text());
                        }
//                    System.out.println(child);
                    }

                } catch (IOException e) {
                    System.out.println("联行号第" + page + "页数据爬取失败-------");
                }

                page++;
                Thread.sleep(1000);
            }
            workbook.write(fileOut);
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (IOException e) {
            System.out.println("文件I/O错误");
        }
    }

    public void lianhanghaoGetForDB(Integer startPage, Integer endPage) throws InterruptedException {
        int page = startPage;
        while (page <= endPage && page > 0) {
            String tempUrl = MessageFormat.format(LIANHANGHAO_URL, page);
            try {
                Document doc = Jsoup.connect(tempUrl).get();
                Elements table = doc.getElementsByClass("t2");
                Element data = table.first();
                for (Element tableChild : data.children()) {
                    if (!tableChild.tag().toString().equals("tbody")) {
                        continue;
                    }
                    for (Element tr : tableChild.children()) {
                        RealzBank realzBank = realzBankService.selectByBankId(tr.child(0).text());
                        if (realzBank == null) {
                            realzBank = new RealzBank();
                            realzBank.setBankId(tr.child(0).text());
                            realzBank.setBankName(tr.child(1).text());
                            realzBankService.insert(realzBank);
                        } else {
                            realzBank.setBankName(tr.child(1).text());
                            realzBankService.updateById(realzBank);
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("联行号第" + page + "页数据爬取失败-------");
            }

            page++;
            Thread.sleep(1000);
        }
    }
}
