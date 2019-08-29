package com.zhangjh.minormargin.utils;

import com.zhangjh.minormargin.constant.ResultCode;
import com.zhangjh.minormargin.vo.Result;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Random;

/**
 * @ClassName VerifyCode
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/29
 * @Version 小缘 1.0
 **/
public class VerifyCodeUtil {

    private static String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
    private static String codes = "abcdefghjkmnopqrstuvwxyz123456789ABCDEFGHJKMNPQRSTUVWXYZ";
    private Color bgColor = new Color(255, 255, 255);
    private int base = 30;
    private int width = base * 4;
    private int height = base;
    private int len = 4;
    private int fontSize = 22;
    private String text;

    private BufferedImage img = null;
    private Graphics2D g2 = null;

    /**
     * 生成验证码图片
     */
    public String drawImage() {
        // 1.创建图片缓冲区对象, 并设置宽高和图像类型
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 2.得到绘制环境
        g2 = (Graphics2D) img.getGraphics();
        // 3.开始画图
        // 设置背景色
        g2.setColor(bgColor);
        g2.fillRect(0, 0, width, height);
        // 用来装载验证码上的文本
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < len; i++) {
            // 设置画笔颜色 -- 随机
            g2.setColor(new Color(getRandom(0, 150), getRandom(0, 150), getRandom(0, 150)));

            // 设置字体
            g2.setFont(new Font(fontNames[getRandom(0, fontNames.length)], Font.BOLD, fontSize));

            // 旋转文字(-45~+45)
            int theta = getRandom(-45, 45);
            g2.rotate(theta * Math.PI / 180, 7 + i * base, height - 8);

            // 写字
            String code = codes.charAt(getRandom(0, codes.length())) + "";
            g2.drawString(code, 7 + i * base, height - 8);
            sb.append(code);
            g2.rotate(-theta * Math.PI / 180, 7 + i * base, height - 8);
        }

        this.text = sb.toString();

        // 画干扰线
        for (int i = 0; i < len + 2; i++) {
            // 设置画笔颜色 -- 随机
            g2.setColor(new Color(getRandom(0, 150), getRandom(0, 150),
                    getRandom(0, 150)));
            g2.drawLine(getRandom(0, 120), getRandom(0, 30), getRandom(0, 120),
                    getRandom(0, 30));
        }
        // 4.保存图片到指定的输出流
        try {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ImageIO.write(this.img, "JPEG", bs);
            Base64.Encoder base64 = Base64.getEncoder();
            return base64.encodeToString(bs.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 5.释放资源
            g2.dispose();
        }
    }

    /**
     * 获取验证码字符串
     *
     * @return
     */
    public String getCode() {
        return this.text;
    }

    /**
     * 生成随机数
     *
     * @param start
     * @param end
     * @return
     */
    private static int getRandom(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start) + start;
    }

    /**
     * 验证验证码是否正确
     *
     * @param code
     * @param codeKey
     * @return
     */
    public static Result verificationCode(String code, String codeKey) {
        Object verificationCode = RedisUtil.get(codeKey);
        // 判断图形验证码是否为空
        if (verificationCode == null) {
            return new Result(ResultCode.RESULT_FAIL, "图形验证码过期.");
        }
        // 判断手机验证码是否正确
        if (!String.valueOf(verificationCode).equalsIgnoreCase(code)) {
            return new Result(ResultCode.RESULT_FAIL, "图形验证码不正确.");
        }
        return null;
    }
}
