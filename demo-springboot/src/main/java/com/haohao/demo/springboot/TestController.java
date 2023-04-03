package com.haohao.demo.springboot;

import com.haohao.demo.date.DateYear;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class TestController {

    @GetMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().setAttribute("name", "张三");
        request.getRequestDispatcher("index").forward(request, response);
    }

    @GetMapping("/index")
    public String index() {
        return DateYear.beginDateStr();
    }
}
