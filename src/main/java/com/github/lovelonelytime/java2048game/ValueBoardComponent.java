package com.github.lovelonelytime.java2048game;

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
 * @author LoveLonelyTime
 */
public class ValueBoardComponent extends JComponent {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1282689972267292485L;
    /**
     * 字体
     */
    private static final Font FONT = new Font(Font.DIALOG, Font.PLAIN, 18);
    /**
     * 背景颜色
     */
    private static final Color BACKGROUND_COLOR = new Color(119, 110, 101);
    /**
     * 组件宽度
     */
    private static final int PREFERRED_WIDTH = 120;
    /**
     * 组件高度
     */
    private static final int PREFERRED_HEIGHT = 50;
    /**
     * 圆角矩形的弧度
     */
    private static final int ARC = 10;

    /**
     * 值
     */
    private int value;

    /**
     * 标题
     */
    private String title;

    /**
     * 构造器设置标题
     * 
     * @param title
     *            标题
     */
    public ValueBoardComponent(String title) {
        this.title = title;
        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        // 清除组件
        super.paintComponent(g);
        // 转换为Graphics2D
        Graphics2D graphics2d = (Graphics2D) g;
        // 设置抗锯齿
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 绘制圆角矩形
        graphics2d.setColor(ValueBoardComponent.BACKGROUND_COLOR);
        graphics2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), ValueBoardComponent.ARC, ValueBoardComponent.ARC);
        // 绘制文字
        graphics2d.setColor(Color.WHITE);
        graphics2d.setFont(ValueBoardComponent.FONT);
        FontMetrics fontMetrics = graphics2d.getFontMetrics();
        // 绘制标题
        graphics2d.drawString(title, (this.getWidth() - fontMetrics.stringWidth(title)) / 2,
                (this.getHeight() - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2 + fontMetrics.getAscent()
                        - 15);
        // 绘制值
        graphics2d.drawString(String.valueOf(value),
                (this.getWidth() - fontMetrics.stringWidth(String.valueOf(value))) / 2,
                (this.getHeight() - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2 + fontMetrics.getAscent()
                        + 10);
    }

    /**
     * 取值
     * 
     * @return 值
     */
    public int getValue() {
        return value;
    }

    /**
     * 设置值
     * 
     * @param value
     *            值
     */
    public void setValue(int value) {
        this.value = value;
        repaint();
    }
}
