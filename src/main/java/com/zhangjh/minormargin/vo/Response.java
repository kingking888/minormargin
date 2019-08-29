package com.zhangjh.minormargin.vo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName Response
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/27
 * @Version 小缘 1.0
 **/
public class Response {
    public Response(HttpServletResponse response, String result) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(result);
    }
}
