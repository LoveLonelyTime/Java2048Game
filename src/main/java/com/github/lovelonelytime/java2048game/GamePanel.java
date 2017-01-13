package com.github.lovelonelytime.java2048game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2349745500491904868L;

    private static final Color BACKGROUND_COLOR = new Color(187, 173, 160);

    private static final int MARGIN = 10;

    private static final int MOUSE_OffSET = 70;

    /**
     * 组件宽度
     */
    private static final int PREFERRED_WIDTH = 330;
    /**
     * 组件高度
     */
    private static final int PREFERRED_HEIGHT = 330;
    private Point lastPressed = new Point(0, 0);

    private GameController gameController;

    public GamePanel(ValueBoardComponent scoreBoardComponent, ValueBoardComponent stepBoardComponent) {
        gameController = new GameController(scoreBoardComponent, stepBoardComponent);
        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                GamePanel.this.lastPressed.setLocation(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (Math.abs(GamePanel.this.lastPressed.getX() - e.getX()) < MOUSE_OffSET
                        && lastPressed.getY() > e.getY()) {
                    gameController.up();
                    repaint();
                    gameController.judgeResult();
                } else if (Math.abs(GamePanel.this.lastPressed.getX() - e.getX()) < MOUSE_OffSET
                        && lastPressed.getY() < e.getY()) {
                    gameController.down();
                    repaint();
                    gameController.judgeResult();
                } else if (Math.abs(GamePanel.this.lastPressed.getY() - e.getY()) < MOUSE_OffSET
                        && lastPressed.getX() > e.getX()) {
                    gameController.left();
                    repaint();
                    gameController.judgeResult();
                } else if (Math.abs(GamePanel.this.lastPressed.getY() - e.getY()) < MOUSE_OffSET
                        && lastPressed.getX() < e.getX()) {
                    gameController.right();
                    repaint();
                    gameController.judgeResult();
                }
            }
        });
    }

    public void newGame() {
        gameController.newGame();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        // 清空屏幕
        super.paint(g);
        int gx = (this.getWidth() - PREFERRED_WIDTH) / 2;
        int gy = (this.getHeight() - PREFERRED_HEIGHT) / 2;
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setColor(BACKGROUND_COLOR);
        graphics2d.fillRoundRect(gx, gy, WIDTH, HEIGHT, 10, 10);
        Block[][] board = gameController.getBoard();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                Image image = Block.generateBlockImage(board[x][y]);
                graphics2d.drawImage(image, gx + MARGIN + x * Block.WIDTH + x * MARGIN,
                        gy + MARGIN + y * Block.HEIGHT + y * MARGIN, this);
            }
        }
    }
}
