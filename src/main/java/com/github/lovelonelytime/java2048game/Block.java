package com.github.lovelonelytime.java2048game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public enum Block {
    BLOCK_NULL(new Color(205, 193, 180), new Color(205, 193, 180), 0), BLOCK_2(new Color(238, 228, 218),
            new Color(119, 110, 101),
            2), BLOCK_4(new Color(237, 224, 200), new Color(119, 110, 101), 4), BLOCK_8(new Color(242, 177, 121),
                    new Color(249, 246, 242), 8), BLOCK_16(new Color(245, 149, 99), new Color(249, 246, 242),
                            16), BLOCK_32(new Color(246, 124, 95), new Color(249, 246, 242), 32), BLOCK_64(
                                    new Color(246, 94, 59), new Color(249, 246, 242),
                                    64), BLOCK_128(new Color(237, 207, 114), new Color(249, 246, 242), 128), BLOCK_256(
                                            new Color(237, 204, 97), new Color(249, 246, 242),
                                            256), BLOCK_512(new Color(237, 200, 80), new Color(249, 246, 242),
                                                    512), BLOCK_1024(new Color(237, 197, 63), new Color(249, 246, 242),
                                                            1024), BLOCK_2048(new Color(237, 194, 46),
                                                                    new Color(249, 246, 242), 2048);

    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;
    private static final Font FONT = new Font(Font.DIALOG, Font.BOLD, 30);
    
    /**
     * 背景色
     */
    private Color backgroundColor;

    /**
     * 文字颜色
     */
    private Color textColor;

    /**
     * 值
     */
    private int value;

    /**
     * 构造器
     * 
     * @param backgroundColor
     *            背景色
     * @param textColor
     *            文字颜色
     * @param value
     *            值
     */
    private Block(Color backgroundColor, Color textColor, int value) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.value = value;
    }

    /**
     * 取背景色
     * 
     * @return 背景色
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * 取文字颜色
     * 
     * @return 文字颜色
     */
    public Color getTextColor() {
        return textColor;
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
     * 通过值取方块
     * 
     * @param value
     *            值
     * @return 方块
     */
    public static Block getBlock(int value) {
        for (Block block : Block.values()) {
            if (block.getValue() == value) {
                return block;
            }
        }
        return Block.BLOCK_NULL;
    }

    /**
     * 生成方块图像
     * 
     * @param block
     *            方块
     * @return 方块图像
     */
    public static Image generateBlockImage(Block block) {
        Color backgroundColor = block.getBackgroundColor();
        Color textColor = block.getTextColor();
        String text = String.valueOf(block.getValue());
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2d = (Graphics2D) image.getGraphics();
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setColor(backgroundColor);
        graphics2d.fillRoundRect(0, 0, WIDTH, WIDTH, 5, 5);
        if (block != Block.BLOCK_NULL) {
            FontMetrics fontMetrics = graphics2d.getFontMetrics(FONT);
            int x = (WIDTH - fontMetrics.stringWidth(text)) / 2;
            int y = (HEIGHT - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2 + fontMetrics.getAscent();
            graphics2d.setFont(FONT);
            graphics2d.setColor(textColor);
            graphics2d.drawString(text, x, y);
        }
        graphics2d.dispose();
        return image;
    }
}
