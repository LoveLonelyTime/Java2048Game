package com.github.lovelonelytime.java2048game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * 游戏窗口
 * 
 * @author JiaHonghao
 */
public class GameGUI extends JFrame {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1943404543087498404L;

    /**
     * 日志记录器
     */
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    /**
     * 计分板
     */
    private ScoreBoardComponent scoreBoardComponent;

    /**
     * 启动窗口
     */
    public void start() {
        // 设置标题
        this.setTitle(LanguageLoader.getString("game.ui.title"));
        // 设置图标
        try {
            this.setIconImage(ImageIO.read(GameGUI.class.getResource("/images/icon.png")));
        } catch (IOException e) {
            GameGUI.LOGGER.warn(LanguageLoader.getString("game.log.loadIconError"), e);
        }
        // 设置界面
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new FlowLayout());

        JPanel textPanel = new JPanel(new BorderLayout());
        JPanel leftTextPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(LanguageLoader.getString("game.ui.2048"));
        titleLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        titleLabel.setForeground(new Color(119, 110, 101));
        leftTextPanel.add(titleLabel, BorderLayout.NORTH);

        JLabel descriptionLabel = new JLabel(LanguageLoader.getString("game.ui.description"));
        descriptionLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
        descriptionLabel.setForeground(new Color(109, 100, 91));
        leftTextPanel.add(descriptionLabel, BorderLayout.SOUTH);
        textPanel.add(leftTextPanel, BorderLayout.WEST);

        JPanel rightTextPanel = new JPanel(new BorderLayout());
        scoreBoardComponent = new ScoreBoardComponent();
        rightTextPanel.add(scoreBoardComponent, BorderLayout.NORTH);
        JButton startButton = new JButton(LanguageLoader.getString("game.ui.startGame"));
        rightTextPanel.add(startButton, BorderLayout.SOUTH);
        textPanel.add(rightTextPanel, BorderLayout.EAST);

        contentPane.add(textPanel);
        // 设置窗口尺寸和位置
        this.pack();
        this.setResizable(false);
        // 窗口居中
        this.setLocationRelativeTo(null);
        // 窗口退出时销毁
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // 显示窗口
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                GameGUI.LOGGER.info(LanguageLoader.getString("game.log.windowExited"));
            }
        });
        GameGUI.LOGGER.info(LanguageLoader.getString("game.log.windowLoaded"));
    }
}
