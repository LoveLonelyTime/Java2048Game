package com.super2048;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 游戏窗口
 * 
 * @author 爱寂寞的时光
 *
 */
@SuppressWarnings("serial")
public class GUI extends JFrame {
	private static final String TITLE = "超级2048";
	private static final Color TEXT_COLOR = new Color(119, 110, 101);
	private static final Font BIG_FONT = new Font(Font.DIALOG, Font.BOLD, 52);
	private static final Font SMALL_FONT = new Font(Font.DIALOG, Font.PLAIN, 15);

	private JPanel headPanel = new JPanel();
	private DisplayValueBoard scoreBoard = new DisplayValueBoard("得分", "0");
	private DisplayValueBoard stepBoard = new DisplayValueBoard("步数", "0");
	private JButton startButton = new JButton("开始游戏");

	private GameController gameController = new GameController(scoreBoard, stepBoard);
	private JPanel gamePanel = new GameContainer(gameController);

	/**
	 * 运行游戏的方法
	 */
	public void run() {
		// 设置标题
		this.setTitle(TITLE);
		// 加载图标
		try {
			this.setIconImage(ResourcesLoader.loadImage("images/icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "貌似出错了呢！", "出错了！", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		headPanel.setLayout(new GridBagLayout());
		// 填写标题
		JLabel title = new JLabel(TITLE);
		title.setFont(BIG_FONT);
		title.setForeground(TEXT_COLOR);
		headPanel.add(title, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		// 填写计分板
		headPanel.add(scoreBoard, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		// 填写计步板
		headPanel.add(stepBoard, new GridBagConstraints(3, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		// 填写不同说明
		JLabel differenceText = new JLabel("这里和其他2048不同的是这里有256个格！");
		differenceText.setFont(SMALL_FONT);
		differenceText.setForeground(TEXT_COLOR);
		headPanel.add(differenceText, new GridBagConstraints(0, 1, 4, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		// 填写游戏说明
		JLabel instructionText = new JLabel("使用鼠标进行拖动，合并数字直到2048");
		instructionText.setFont(SMALL_FONT);
		instructionText.setForeground(TEXT_COLOR);
		headPanel.add(instructionText, new GridBagConstraints(0, 2, 4, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		// 填写作者
		JLabel authorText = new JLabel("by：@爱寂寞的时光");
		authorText.setFont(SMALL_FONT);
		authorText.setForeground(TEXT_COLOR);
		headPanel.add(authorText, new GridBagConstraints(0, 3, 2, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		// 填写开始按钮
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUI.this.gameController.newGame();
				GUI.this.gamePanel.repaint();
			}
		});
		headPanel.add(startButton, new GridBagConstraints(1, 3, 2, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		// 添加头部面板
		this.getContentPane().add(headPanel, BorderLayout.NORTH);
		// 添加游戏主体面板
		this.getContentPane().add(gamePanel, BorderLayout.CENTER);
		// 窗口设置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dimension = this.getPreferredSize();
		this.setSize(dimension.width + 50, dimension.height + 50);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		// 显示窗口
		this.setVisible(true);
		// 新的游戏
		gameController.newGame();
	}
}
