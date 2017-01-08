package com.web.controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 验证码的控制器
 * 
 * @author Administrator
 *
 */
@Controller
@Scope(value = "prototype")
public class ValidateCodeController extends ActionSupport {
	/**
	 * 生成随机颜色
	 * 
	 * @return Color对象，此Color对象是RGB形式的。
	 */
	private Color getRandomColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 200;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private static final String CONTENT_TYPE = "image/jpeg";

	// 生成验证码(流)
	public void create() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		// 设定编码格式
		response.setContentType(CONTENT_TYPE);
		// 以下3句话 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 1、生成字符串
		HttpSession session = request.getSession();
		// 产生四位随机码，写入session
		// String chose =
		// "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		// 去掉容易混淆的字母：0,1,o,I,l,O
		String chose = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";

		char display[] = new char[4];
		Random rand = new Random();
		for (int i = 0; i < display.length; i++) {

			char c = chose.charAt(rand.nextInt(chose.length()));

			display[i] = c;
		}

		// 2、把随机的字符串存放到session中，为了做登录的验证码的判断
		String str = new String(display);
		// 存放验证码到session中
		session.setAttribute("rnCode", str);

		// 2、创建一张图片 生成图像，返回到页面上
		int width = 70, height = 26;

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 创建一个画布
		Graphics g = image.getGraphics();
		// 以下填充背景颜色
		g.setColor(Color.white);
		g.setColor(getRandomColor(200, 250)); // 随机生成背景色(灰色调)

		// 设置边框 (画一矩形,大小)
		g.fillRect(0, 0, width, height);

		// 设置字体颜色、字体
		g.setColor(Color.black);

		g.drawRect(0, 0, width - 1, height - 1); // 字要比图像边框小一点

		// 3、输入图片流

		// 将认证码写入图像
		g.setColor(Color.black);
		g.setFont(new Font("黑体", Font.PLAIN, 20));

		g.drawString(str, 5, 20);

		// 图像生效
		g.dispose();

		try {
			// 输出图像
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
