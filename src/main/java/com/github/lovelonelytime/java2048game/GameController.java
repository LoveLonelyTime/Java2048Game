package com.github.lovelonelytime.java2048game;

import java.awt.Point;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class GameController {
    private ValueBoardComponent scoreBoardComponent;
    private ValueBoardComponent stepBoardComponent;
    private Block[][] board = new Block[4][4];

    /**
     * 创建一个游戏控制器
     * 
     * @param scoreBoardComponent
     *            计分板
     * @param stepBoardComponent
     *            计步板
     */
    public GameController(ValueBoardComponent scoreBoardComponent, ValueBoardComponent stepBoardComponent) {
        this.scoreBoardComponent = scoreBoardComponent;
        this.stepBoardComponent = stepBoardComponent;
    }

    /**
     * 开始一个新游戏
     */
    public void newGame() {
        // 清空数组
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                board[x][y] = Block.BLOCK_NULL;
            }
        }
        // 清空计分板和计步板
        scoreBoardComponent.setValue(0);
        stepBoardComponent.setValue(0);
        // 随机生成两个数（2或4）
        for (int i = 0; i < 2; i++) {
            Point point = getRandomFreeLocation();
            board[point.x][point.y] = generateRandom2or4();
        }
    }

    /**
     * 上移
     */
    public void up() {
        if (canUp()) {
            removeUpBlank();
            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board[y].length; x++) {
                    if (y - 1 >= 0 && board[x][y] != Block.BLOCK_NULL && board[x][y] == board[x][y - 1]) {
                        board[x][y - 1] = Block.getBlock(board[x][y].getValue() + board[x][y - 1].getValue());
                        board[x][y] = Block.BLOCK_NULL;
                        // 有效的合并
                        scoreBoardComponent.setValue(scoreBoardComponent.getValue() + 1);
                    }
                }
            }
            removeUpBlank();
            stepBoardComponent.setValue(stepBoardComponent.getValue() + 1);
            Point point = getRandomFreeLocation();
            if (point != null) {
                board[point.x][point.y] = generateRandom2or4();
            }
        }
    }

    /**
     * 下移
     */
    public void down() {
        if (canDonw()) {
            removeDownBlank();
            for (int y = board.length - 1; y >= 0; y--) {
                for (int x = 0; x < board[y].length; x++) {
                    if (y + 1 <= board.length - 1 && board[x][y] != Block.BLOCK_NULL
                            && board[x][y] == board[x][y + 1]) {
                        board[x][y + 1] = Block.getBlock(board[x][y].getValue() + board[x][y + 1].getValue());
                        board[x][y] = Block.BLOCK_NULL;
                        // 有效的合并
                        scoreBoardComponent.setValue(scoreBoardComponent.getValue() + 1);
                    }
                }
            }
            removeDownBlank();
            stepBoardComponent.setValue(stepBoardComponent.getValue() + 1);
            Point point = getRandomFreeLocation();
            if (point != null) {
                board[point.x][point.y] = generateRandom2or4();
            }
        }
    }

    /**
     * 左移
     */
    public void left() {
        if (canLeft()) {
            removeLeftBlank();
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[x].length; y++) {
                    if (x - 1 >= 0 && board[x][y] != Block.BLOCK_NULL && board[x][y] == board[x - 1][y]) {
                        board[x - 1][y] = Block.getBlock(board[x][y].getValue() + board[x - 1][y].getValue());
                        board[x][y] = Block.BLOCK_NULL;
                        // 有效的合并
                        scoreBoardComponent.setValue(scoreBoardComponent.getValue() + 1);
                    }
                }
            }
            removeLeftBlank();
            stepBoardComponent.setValue(stepBoardComponent.getValue() + 1);
            Point point = getRandomFreeLocation();
            if (point != null) {
                board[point.x][point.y] = generateRandom2or4();
            }
        }
    }

    /**
     * 右移
     */
    public void right() {
        if (canRight()) {
            removeRightBlank();
            for (int x = board.length - 1; x >= 0; x--) {
                for (int y = 0; y < board[x].length; y++) {
                    if (x + 1 <= board.length - 1 && board[x][y] != Block.BLOCK_NULL
                            && board[x][y] == board[x + 1][y]) {
                        board[x + 1][y] = Block.getBlock(board[x][y].getValue() + board[x + 1][y].getValue());
                        board[x][y] = Block.BLOCK_NULL;
                        // 有效的合并
                        scoreBoardComponent.setValue(scoreBoardComponent.getValue() + 1);
                    }
                }
            }
            removeRightBlank();
            stepBoardComponent.setValue(stepBoardComponent.getValue() + 1);
            Point point = getRandomFreeLocation();
            if (point != null) {
                board[point.x][point.y] = generateRandom2or4();
            }
        }
    }

    /**
     * 向上移空格
     */
    private void removeUpBlank() {
        int removeUp = 1;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[x][y] != Block.BLOCK_NULL) {
                    while (y - removeUp >= 0 && board[x][y - removeUp] == Block.BLOCK_NULL) {
                        removeUp++;
                    }
                    if (removeUp - 1 != 0) {
                        board[x][y - (removeUp - 1)] = board[x][y];
                        board[x][y] = Block.BLOCK_NULL;
                    }
                    removeUp = 1;
                }
            }
        }
    }

    /**
     * 向下移空格
     */
    private void removeDownBlank() {
        int removeDonw = 1;
        for (int y = board.length - 1; y >= 0; y--) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[x][y] != Block.BLOCK_NULL) {
                    while (y + removeDonw <= board.length - 1 && board[x][y + removeDonw] == Block.BLOCK_NULL) {
                        removeDonw++;
                    }
                    if (removeDonw - 1 != 0) {
                        board[x][y + (removeDonw - 1)] = board[x][y];
                        board[x][y] = Block.BLOCK_NULL;
                    }
                    removeDonw = 1;
                }
            }
        }
    }

    /**
     * 向左移空格
     */
    private void removeLeftBlank() {
        int removeLeft = 1;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] != Block.BLOCK_NULL) {
                    while (x - removeLeft >= 0 && board[x - removeLeft][y] == Block.BLOCK_NULL) {
                        removeLeft++;
                    }
                    if (removeLeft - 1 != 0) {
                        board[x - (removeLeft - 1)][y] = board[x][y];
                        board[x][y] = Block.BLOCK_NULL;
                    }
                    removeLeft = 1;
                }
            }
        }
    }

    /**
     * 向右移空格
     */
    private void removeRightBlank() {
        int removeRight = 1;
        for (int x = board.length - 1; x >= 0; x--) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] != Block.BLOCK_NULL) {
                    while (x + removeRight <= board.length - 1 && board[x + removeRight][y] == Block.BLOCK_NULL) {
                        removeRight++;
                    }
                    if (removeRight - 1 != 0) {
                        board[x + (removeRight - 1)][y] = board[x][y];
                        board[x][y] = Block.BLOCK_NULL;
                    }
                    removeRight = 1;
                }
            }
        }
    }

    /**
     * 能不能上移
     * 
     * @return 能不能
     */
    private boolean canUp() {
        boolean canMerge = false;
        boolean canMove = false;
        canMerge: for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (y - 1 >= 0 && board[x][y] != Block.BLOCK_NULL && board[x][y] == board[x][y - 1]) {
                    canMerge = true;
                    break canMerge;
                }
            }
        }
        int removeUp = 1;
        canMove: for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[x][y] != Block.BLOCK_NULL) {
                    while (y - removeUp >= 0 && board[x][y - removeUp] == Block.BLOCK_NULL) {
                        removeUp++;
                    }
                    if (removeUp - 1 != 0) {
                        canMove = true;
                        break canMove;
                    }
                    removeUp = 1;
                }
            }
        }
        return canMerge || canMove;
    }

    /**
     * 能不能下移
     * 
     * @return 能不能
     */
    private boolean canDonw() {
        boolean canMerge = false;
        boolean canMove = false;
        canMerge: for (int y = board.length - 1; y >= 0; y--) {
            for (int x = 0; x < board[y].length; x++) {
                if (y + 1 <= board.length - 1 && board[x][y] != Block.BLOCK_NULL && board[x][y] == board[x][y + 1]) {
                    canMerge = true;
                    break canMerge;
                }
            }
        }
        int removeDonw = 1;
        canMove: for (int y = board.length - 1; y >= 0; y--) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[x][y] != Block.BLOCK_NULL) {
                    while (y + removeDonw <= board.length - 1 && board[x][y + removeDonw] == Block.BLOCK_NULL) {
                        removeDonw++;
                    }
                    if (removeDonw - 1 != 0) {
                        canMove = true;
                        break canMove;
                    }
                    removeDonw = 1;
                }
            }
        }
        return canMerge || canMove;
    }

    /**
     * 能不能左移
     * 
     * @return 能不能
     */
    private boolean canLeft() {
        boolean canMerge = false;
        boolean canMove = false;
        canMerge: for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (x - 1 >= 0 && board[x][y] != Block.BLOCK_NULL && board[x][y] == board[x - 1][y]) {
                    canMerge = true;
                    break canMerge;
                }
            }
        }
        int removeLeft = 1;
        canMove: for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] != Block.BLOCK_NULL) {
                    while (x - removeLeft >= 0 && board[x - removeLeft][y] == Block.BLOCK_NULL) {
                        removeLeft++;
                    }
                    if (removeLeft - 1 != 0) {
                        canMove = true;
                        break canMove;
                    }
                    removeLeft = 1;
                }
            }
        }
        return canMerge || canMove;
    }

    /**
     * 能不能右移
     * 
     * @return 能不能
     */
    private boolean canRight() {
        boolean canMerge = false;
        boolean canMove = false;
        canMerge: for (int x = board.length - 1; x >= 0; x--) {
            for (int y = 0; y < board[x].length; y++) {
                if (x + 1 <= board.length - 1 && board[x][y] != Block.BLOCK_NULL && board[x][y] == board[x + 1][y]) {
                    canMerge = true;
                    break canMerge;
                }
            }
        }
        int removeRight = 1;
        canMove: for (int x = board.length - 1; x >= 0; x--) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] != Block.BLOCK_NULL) {
                    while (x + removeRight <= board.length - 1 && board[x + removeRight][y] == Block.BLOCK_NULL) {
                        removeRight++;
                    }
                    if (removeRight - 1 != 0) {
                        canMove = true;
                        break canMove;
                    }
                    removeRight = 1;
                }
            }
        }
        return canMerge || canMove;
    }

    /**
     * 判断游戏结果
     */
    public void judgeResult() {
        if (!canUp() && !canDonw() && !canLeft() && !canRight()) {
            // 失败
            JOptionPane.showMessageDialog(null,
                    MessageFormat.format(LanguageLoader.getString("game.ui.fail"), scoreBoardComponent.getValue(),
                            scoreBoardComponent.getValue()),
                    LanguageLoader.getString("game.ui.end"), JOptionPane.PLAIN_MESSAGE);
            newGame();
        }
        if (have2048()) {
            // 胜利
            JOptionPane.showMessageDialog(null,
                    MessageFormat.format(LanguageLoader.getString("game.ui.win"), scoreBoardComponent.getValue(),
                            scoreBoardComponent.getValue()),
                    LanguageLoader.getString("game.ui.end"), JOptionPane.PLAIN_MESSAGE);
            newGame();
        }
    }

    /**
     * 有没有2048
     * 
     * @return 有没有2048
     */
    private boolean have2048() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[x][y] == Block.BLOCK_2048) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 取游戏方块数组
     * 
     * @return 数组
     */
    public Block[][] getBoard() {
        return board;
    }

    /**
     * 随机取一个空闲的位置
     * 
     * @return 一个空闲的位置
     */
    private Point getRandomFreeLocation() {
        // 取所有空闲的位置
        List<Point> freeLocationList = new ArrayList<>();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                Block block = this.board[x][y];
                if (block == Block.BLOCK_NULL) {
                    freeLocationList.add(new Point(x, y));
                }
            }
        }
        // 随机取一个空闲的位置
        return freeLocationList.isEmpty() ? null
                : freeLocationList.get((int) (Math.random() * (freeLocationList.size() - 1)));
    }

    /**
     * 随机生成2或4，2的几率为90%，4的几率为10%
     * 
     * @return 2或4
     */
    private Block generateRandom2or4() {
        int value = (int) (Math.random() * 100);
        if (value < 90) {
            return Block.BLOCK_2;
        } else {
            return Block.BLOCK_4;
        }
    }
}
