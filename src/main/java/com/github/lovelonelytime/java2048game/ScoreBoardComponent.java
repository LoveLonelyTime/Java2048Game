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
public class ScoreBoardComponent extends JComponent {
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
     * 分数
     */
    private int score;

    @Override
    protected void paintComponent(Graphics g) {
        // 清除组件
        super.paintComponent(g);
        // 转换为Graphics2D
        Graphics2D graphics2d = (Graphics2D) g;
        // 设置抗锯齿
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 绘制圆角矩形
        graphics2d.setColor(ScoreBoardComponent.BACKGROUND_COLOR);
        graphics2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), ScoreBoardComponent.ARC,
                ScoreBoardComponent.ARC);
        // 绘制文字
        graphics2d.setColor(Color.WHITE);
        graphics2d.setFont(ScoreBoardComponent.FONT);
        FontMetrics fontMetrics = graphics2d.getFontMetrics();
        graphics2d.drawString(LanguageLoader.getString("game.ui.currentScore"),
                (this.getWidth() - fontMetrics.stringWidth(LanguageLoader.getString("game.ui.currentScore"))) / 2,
                (this.getHeight() - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2 + fontMetrics.getAscent()
                        - 15);
        graphics2d.drawString(String.valueOf(this.getScore()),
                (this.getWidth() - fontMetrics.stringWidth(String.valueOf(this.getScore()))) / 2,
                (this.getHeight() - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2 + fontMetrics.getAscent()
                        + 10);
    }

    @Override
    public Dimension getPreferredSize() {
        // 返回最佳尺寸
        return new Dimension(ScoreBoardComponent.PREFERRED_WIDTH, ScoreBoardComponent.PREFERRED_HEIGHT);
    }

    /**
     * 取得分
     * 
     * @return 得分
     */
    public int getScore() {
        return score;
    }

    /**
     * 设置得分
     * 
     * @param score
     *            得分
     */
    public void setScore(int score) {
        this.score = score;
    }
}
