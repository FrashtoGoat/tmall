package com.xiaoluban.demo.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.xiaoluban.demo.excel.vo.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: TXB
 * @Date: 2021/4/22 10:31
 * @Description: *
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @RequestMapping("/exportTest")
    public void excelExport(){

        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");

        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);


        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/Test/writeTest.xlsx");
        //通过构造方法创建writer
//        ExcelWriter writer = new ExcelWriter("d:/Test/writeTest.xls");

        //跳过当前行，既第一行，非必须，在此演示用
        writer.passCurrentRow();

        //合并单元格后的标题行，使用默认标题样式
        writer.merge(row1.size() - 1, "测试标题");
        //一次性写出内容，强制输出标题
        writer.write(rows, true);
        //关闭writer，释放内存
        writer.close();

    }


    @GetMapping("/downloadExcel")
    public void downLoadFile(HttpServletResponse response) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            employees.add(new Employee(i + 18, "a" + i));
        }

        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.merge(1, "员工信息表");
        writer.write(employees, true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");

        String name = "XXX国际贸易公司";

        response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        IoUtil.close(out);
    }


}
