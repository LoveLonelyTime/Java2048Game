package com.super2048;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

/**
 * 计分板组件
 * 
 * @author 爱寂寞的时光
 *
 */
@SuppressWarnings("serial")
public class DisplayValueBoard extends JComponent {
	private static final Font FONT = new Font(Font.DIALOG, Font.PLAIN, 15);
	private static final Color TEXT_COLOR = new Color(119, 110, 101);
	private static final Color BACKGROUND_COLOR = new Color(205, 179, 139);
	private final String title;
	private String content;

	/**
	 * 构造方法，进行设置
	 * 
	 * @param title
	 *            标题
	 * @param defaultContent
	 *            缺省内容
	 */
	public DisplayValueBoard(String title, String defaultContent) {
		super();
		this.title = title;
		this.content = defaultContent;
		this.setPreferredSize(new Dimension(60, 60));
		this.setBackground(Color.WHITE);
	}

	/**
	 * 取内容
	 * 
	 * @return 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
		repaint();
	}

	/**
	 * 绘制组件
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setColor(BACKGROUND_COLOR);
		graphics2d.fillRoundRect(0, 0, 60, 60, 10, 10);
		graphics2d.setColor(TEXT_COLOR);

		FontMetrics titleFontMetrics = graphics2d.getFontMetrics(FONT);
		int titleX = (60 - titleFontMetrics.stringWidth(this.title)) / 2;
		int titleY = titleFontMetrics.getAscent() + titleFontMetrics.getDescent();
		graphics2d.setFont(FONT);
		graphics2d.drawString(this.title, titleX, titleY);

		FontMetrics contentFontMetrics = graphics2d.getFontMetrics(FONT);
		int contentX = (60 - contentFontMetrics.stringWidth(this.content)) / 2;
		int contentY = (60 - (titleFontMetrics.getAscent() + titleFontMetrics.getDescent())) / 2
				+ titleFontMetrics.getAscent();
		graphics2d.setFont(FONT.deriveFont(Font.BOLD));
		graphics2d.drawString(this.content, contentX, contentY);
	}

}
