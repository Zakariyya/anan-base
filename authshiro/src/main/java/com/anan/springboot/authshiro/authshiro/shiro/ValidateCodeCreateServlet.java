package com.anan.springboot.authshiro.authshiro.shiro;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@WebServlet(urlPatterns = "/auth/validatecode")
public class ValidateCodeCreateServlet extends HttpServlet {
	/**  
	*   
	*/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 定义长宽
		int width = 60;
		int height = 32;

		// create the image
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// set the background color
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);
		// draw the border
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);
		// create a random instance to generate the codes
		Random rdm = new Random();
		String hash1 = Integer.toHexString(rdm.nextInt());
		// make some confusion
		for (int i = 0; i < 50; i++) {
			int x = rdm.nextInt(width);
			int y = rdm.nextInt(height);
			g.drawOval(x, y, 2, 2);
		}

		// generate a random code
		String capstr = hash1.substring(0, 4);
		g.setColor(new Color(0, 100, 0));
		g.setFont(new Font("Bold Italic", Font.BOLD, 21));
		g.drawString(capstr, 6, 20);
		g.dispose();

		// 将生成的验证码存入session
		HttpSession session = req.getSession(true);
		session.setAttribute("validateCode", capstr);
		// 输出图片
		resp.setContentType("image/jpeg");
		OutputStream strm = resp.getOutputStream();
		ImageIO.write(image, "jpeg", strm);
		strm.close();

	}
}
