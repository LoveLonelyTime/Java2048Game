package com.github.lovelonelytime.java2048game;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 游戏启动主类
 * 
 * @author LoveLonelyTime
 */
public class Main {
    /**
     * 日志记录器
     */
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    /**
     * 启动主方法
     * 
     * @param args
     *            启动参数
     */
    public static void main(String[] args) {
        // 加载日志配置文件
        PropertyConfigurator.configure(Main.class.getResource("/configs/log4j.properties"));
        // 加入Swing事件线程
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 启动游戏
                GameGUI gameGUI = new GameGUI();
                gameGUI.start();
                Main.LOGGER.info(LanguageLoader.getString("game.log.startGame"));
            }
        });
    }
}
