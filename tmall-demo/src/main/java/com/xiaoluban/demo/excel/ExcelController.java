package com.xiaoluban.demo.excel;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.xiaoluban.demo.excel.vo.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@Controller
@RequestMapping("/excel")
public class ExcelController {

    @GetMapping("/downloadExcel")
    @ResponseBody
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


    @GetMapping("/test")
    public String toTest(){
        return "test";
    }

}
