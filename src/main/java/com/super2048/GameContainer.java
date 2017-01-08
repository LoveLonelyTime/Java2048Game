package com.super2048;

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

/**
 * 游戏主体面板
 * 
 * @author 爱寂寞的时光
 *
 */
@SuppressWarnings("serial")
public class GameContainer extends JPanel {
	private static final Color BACKGROUND_COLOR = new Color(187, 173, 160);
	private static final int MARGIN = 10;
	private static final int WIDTH = 810;
	private static final int HEIGHT = 810;

	private static final int MOUSE_OffSET = 70;

	private GameController gameController;

	private Point lastPressed = new Point(0, 0);

	/**
	 * 构造方法，可以设置最佳尺寸
	 * 
	 * @param gameController
	 *            游戏控制器
	 */
	public GameContainer(GameController gameController) {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.gameController = gameController;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GameContainer.this.lastPressed.setLocation(e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (Math.abs(GameContainer.this.lastPressed.getX() - e.getX()) < MOUSE_OffSET
						&& lastPressed.getY() > e.getY()) {
					gameController.up();
					repaint();
					gameController.judgeResult();
				} else if (Math.abs(GameContainer.this.lastPressed.getX() - e.getX()) < MOUSE_OffSET
						&& lastPressed.getY() < e.getY()) {
					gameController.down();
					repaint();
					gameController.judgeResult();
				} else if (Math.abs(GameContainer.this.lastPressed.getY() - e.getY()) < MOUSE_OffSET
						&& lastPressed.getX() > e.getX()) {
					gameController.left();
					repaint();
					gameController.judgeResult();
				} else if (Math.abs(GameContainer.this.lastPressed.getY() - e.getY()) < MOUSE_OffSET
						&& lastPressed.getX() < e.getX()) {
					gameController.right();
					repaint();
					gameController.judgeResult();
				}
			}
		});
	}

	/**
	 * 绘制面板
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int gx = (this.getWidth() - WIDTH) / 2;
		int gy = (this.getHeight() - HEIGHT) / 2;
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setColor(BACKGROUND_COLOR);
		graphics2d.fillRoundRect(gx, gy, WIDTH, HEIGHT, 10, 10);
		BlockGenerator.Level[][] board = gameController.getBoard();
		for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				Image image = BlockGenerator.generateBlockImage(board[x][y]);
				graphics2d.drawImage(image, gx + MARGIN + x * BlockGenerator.WIDTH + x * MARGIN,
						gy + MARGIN + y * BlockGenerator.HEIGHT + y * MARGIN, this);
			}
		}
	}
}
