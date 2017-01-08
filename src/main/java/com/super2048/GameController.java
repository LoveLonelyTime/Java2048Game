package com.super2048;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * 游戏控制器
 * 
 * @author 爱寂寞的时光
 *
 */
public class GameController {
	private DisplayValueBoard scoreBoard;
	private DisplayValueBoard stepBoard;
	private BlockGenerator.Level[][] board = new BlockGenerator.Level[16][16];

	/**
	 * 创建一个游戏控制器
	 * 
	 * @param scoreBoard
	 *            计分板
	 * @param stepBoard
	 *            计步板
	 */
	public GameController(DisplayValueBoard scoreBoard, DisplayValueBoard stepBoard) {
		this.scoreBoard = scoreBoard;
		this.stepBoard = stepBoard;
	}

	/**
	 * 开始一个新游戏
	 */
	public void newGame() {
		// 清空数组
		for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				this.board[x][y] = BlockGenerator.Level.NULL;
			}
		}
		// 清空计分板和计步板
		scoreBoard.setContent("0");
		stepBoard.setContent("0");
		// 随机生成两个数（2或4）
		for (int i = 0; i < 2; i++) {
			Point point = getRandomFreeLocation();
			this.board[point.x][point.y] = generateRandom2or4();
		}
	}

	/**
	 * 上移
	 */
	public void up() {
		if (canUp()) {
			removeUpBlank();
			for (int y = 0; y < 16; y++) {
				for (int x = 0; x < 16; x++) {
					if (y - 1 >= 0 && board[x][y] != BlockGenerator.Level.NULL && board[x][y] == board[x][y - 1]) {
						board[x][y - 1] = BlockGenerator.Level.toEnum(BlockGenerator.Level.getValue(board[x][y])
								+ BlockGenerator.Level.getValue(board[x][y - 1]));
						board[x][y] = BlockGenerator.Level.NULL;
						// 有效的合并
						scoreBoard.setContent(String.valueOf(Integer.valueOf(scoreBoard.getContent()) + 1));
					}
				}
			}
			removeUpBlank();
			stepBoard.setContent(String.valueOf(Integer.valueOf(stepBoard.getContent()) + 1));
			Point point = getRandomFreeLocation();
			this.board[point.x][point.y] = generateRandom2or4();
		}
	}

	/**
	 * 下移
	 */
	public void down() {
		if (canDonw()) {
			removeDownBlank();
			for (int y = 15; y >= 0; y--) {
				for (int x = 0; x < 16; x++) {
					if (y + 1 <= 15 && board[x][y] != BlockGenerator.Level.NULL && board[x][y] == board[x][y + 1]) {
						board[x][y + 1] = BlockGenerator.Level.toEnum(BlockGenerator.Level.getValue(board[x][y])
								+ BlockGenerator.Level.getValue(board[x][y + 1]));
						board[x][y] = BlockGenerator.Level.NULL;
						// 有效的合并
						scoreBoard.setContent(String.valueOf(Integer.valueOf(scoreBoard.getContent()) + 1));
					}
				}
			}
			removeDownBlank();
			stepBoard.setContent(String.valueOf(Integer.valueOf(stepBoard.getContent()) + 1));
			Point point = getRandomFreeLocation();
			this.board[point.x][point.y] = generateRandom2or4();
		}
	}

	/**
	 * 左移
	 */
	public void left() {
		if (canLeft()) {
			removeLeftBlank();
			for (int x = 0; x < 16; x++) {
				for (int y = 0; y < 16; y++) {
					if (x - 1 >= 0 && board[x][y] != BlockGenerator.Level.NULL && board[x][y] == board[x - 1][y]) {
						board[x - 1][y] = BlockGenerator.Level.toEnum(BlockGenerator.Level.getValue(board[x][y])
								+ BlockGenerator.Level.getValue(board[x - 1][y]));
						board[x][y] = BlockGenerator.Level.NULL;
						// 有效的合并
						scoreBoard.setContent(String.valueOf(Integer.valueOf(scoreBoard.getContent()) + 1));
					}
				}
			}
			removeLeftBlank();
			stepBoard.setContent(String.valueOf(Integer.valueOf(stepBoard.getContent()) + 1));
			Point point = getRandomFreeLocation();
			this.board[point.x][point.y] = generateRandom2or4();
		}
	}

	/**
	 * 右移
	 */
	public void right() {
		if (canRight()) {
			removeRightBlank();
			for (int x = 15; x >= 0; x--) {
				for (int y = 0; y < 16; y++) {
					if (x + 1 <= 15 && board[x][y] != BlockGenerator.Level.NULL && board[x][y] == board[x + 1][y]) {
						board[x + 1][y] = BlockGenerator.Level.toEnum(BlockGenerator.Level.getValue(board[x][y])
								+ BlockGenerator.Level.getValue(board[x + 1][y]));
						board[x][y] = BlockGenerator.Level.NULL;
						// 有效的合并
						scoreBoard.setContent(String.valueOf(Integer.valueOf(scoreBoard.getContent()) + 1));
					}
				}
			}
			removeRightBlank();
			stepBoard.setContent(String.valueOf(Integer.valueOf(stepBoard.getContent()) + 1));
			Point point = getRandomFreeLocation();
			this.board[point.x][point.y] = generateRandom2or4();
		}
	}

	/**
	 * 向上移空格
	 */
	private void removeUpBlank() {
		int removeUp = 1;
		for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				if (board[x][y] != BlockGenerator.Level.NULL) {
					while (y - removeUp >= 0 && board[x][y - removeUp] == BlockGenerator.Level.NULL) {
						removeUp++;
					}
					if (removeUp - 1 != 0) {
						board[x][y - (removeUp - 1)] = board[x][y];
						board[x][y] = BlockGenerator.Level.NULL;
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
		for (int y = 15; y >= 0; y--) {
			for (int x = 0; x < 16; x++) {
				if (board[x][y] != BlockGenerator.Level.NULL) {
					while (y + removeDonw <= 15 && board[x][y + removeDonw] == BlockGenerator.Level.NULL) {
						removeDonw++;
					}
					if (removeDonw - 1 != 0) {
						board[x][y + (removeDonw - 1)] = board[x][y];
						board[x][y] = BlockGenerator.Level.NULL;
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
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				if (board[x][y] != BlockGenerator.Level.NULL) {
					while (x - removeLeft >= 0 && board[x - removeLeft][y] == BlockGenerator.Level.NULL) {
						removeLeft++;
					}
					if (removeLeft - 1 != 0) {
						board[x - (removeLeft - 1)][y] = board[x][y];
						board[x][y] = BlockGenerator.Level.NULL;
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
		for (int x = 15; x >= 0; x--) {
			for (int y = 0; y < 16; y++) {
				if (board[x][y] != BlockGenerator.Level.NULL) {
					while (x + removeRight <= 15 && board[x + removeRight][y] == BlockGenerator.Level.NULL) {
						removeRight++;
					}
					if (removeRight - 1 != 0) {
						board[x + (removeRight - 1)][y] = board[x][y];
						board[x][y] = BlockGenerator.Level.NULL;
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
		canMerge: for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				if (y - 1 >= 0 && board[x][y] != BlockGenerator.Level.NULL && board[x][y] == board[x][y - 1]) {
					canMerge = true;
					break canMerge;
				}
			}
		}
		int removeUp = 1;
		canMove: for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				if (board[x][y] != BlockGenerator.Level.NULL) {
					while (y - removeUp >= 0 && board[x][y - removeUp] == BlockGenerator.Level.NULL) {
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
		canMerge: for (int y = 15; y >= 0; y--) {
			for (int x = 0; x < 16; x++) {
				if (y + 1 <= 15 && board[x][y] != BlockGenerator.Level.NULL && board[x][y] == board[x][y + 1]) {
					canMerge = true;
					break canMerge;
				}
			}
		}
		int removeDonw = 1;
		canMove: for (int y = 15; y >= 0; y--) {
			for (int x = 0; x < 16; x++) {
				if (board[x][y] != BlockGenerator.Level.NULL) {
					while (y + removeDonw <= 15 && board[x][y + removeDonw] == BlockGenerator.Level.NULL) {
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
		canMerge: for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				if (x - 1 >= 0 && board[x][y] != BlockGenerator.Level.NULL && board[x][y] == board[x - 1][y]) {
					canMerge = true;
					break canMerge;
				}
			}
		}
		int removeLeft = 1;
		canMove: for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				if (board[x][y] != BlockGenerator.Level.NULL) {
					while (x - removeLeft >= 0 && board[x - removeLeft][y] == BlockGenerator.Level.NULL) {
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
		canMerge: for (int x = 15; x >= 0; x--) {
			for (int y = 0; y < 16; y++) {
				if (x + 1 <= 15 && board[x][y] != BlockGenerator.Level.NULL && board[x][y] == board[x + 1][y]) {
					canMerge = true;
					break canMerge;
				}
			}
		}
		int removeRight = 1;
		canMove: for (int x = 15; x >= 0; x--) {
			for (int y = 0; y < 16; y++) {
				if (board[x][y] != BlockGenerator.Level.NULL) {
					while (x + removeRight <= 15 && board[x + removeRight][y] == BlockGenerator.Level.NULL) {
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
					"你失败了，共计得分：" + scoreBoard.getContent() + "，共计移动步数：" + stepBoard.getContent(), "结束",
					JOptionPane.PLAIN_MESSAGE);
			newGame();
		}
		if (have2048()) {
			// 胜利
			JOptionPane.showMessageDialog(null,
					"你胜利了，共计得分：" + scoreBoard.getContent() + "，共计移动步数：" + stepBoard.getContent(), "结束",
					JOptionPane.PLAIN_MESSAGE);
			newGame();
		}
	}

	/**
	 * 有没有2048
	 * 
	 * @return 有没有2048
	 */
	private boolean have2048() {
		for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				if (board[x][y] == BlockGenerator.Level.$2048) {
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
	public BlockGenerator.Level[][] getBoard() {
		return this.board;
	}

	/**
	 * 随机取一个空闲的位置
	 * 
	 * @return 一个空闲的位置
	 */
	private Point getRandomFreeLocation() {
		// 取所有空闲的位置
		List<Point> freeLocationList = new ArrayList<>();
		for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				BlockGenerator.Level level = this.board[x][y];
				if (level == BlockGenerator.Level.NULL) {
					freeLocationList.add(new Point(x, y));
				}
			}
		}
		// 随机取一个空闲的位置
		return freeLocationList.get((int) (Math.random() * (freeLocationList.size() - 1)));
	}

	/**
	 * 随机生成2或4，2的几率为90%，4的几率为10%
	 * 
	 * @return 2或4
	 */
	private BlockGenerator.Level generateRandom2or4() {
		int value = (int) (Math.random() * 100);
		if (value < 90) {
			return BlockGenerator.Level.$2;
		} else {
			return BlockGenerator.Level.$4;
		}
	}
}
