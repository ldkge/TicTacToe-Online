package com.ldkge.ttt;

public class TicTacToe {
	private int[][] board;
	
	public TicTacToe() {
		boardInit();
		
	}
	
	private void boardInit() {
		board = new int[3][3];
		
		for (int[] c : board) {
			for (int i = 0; i < c.length; i++) {
				c[i] = -3;
			}
		}
	}
	
	public void makeMove(int x, int y, int player) {
		if (board[x][y] != -3) {
			board[x][y] = player;
		}
		else {
			System.out.println("Invalid move");
		}
	}
	
	private void checkWin() {
		int c0 = board[0][0] + board[1][0] + board[2][0],
				c1 = board[0][1] + board[1][1] + board[2][1],
				c2 = board[0][2] + board[1][2] + board[2][2],
				r0 = board[0][0] + board[0][1] + board[0][2],
				r1 = board[1][0] + board[1][1] + board[1][2],
				r2 = board[2][0] + board[2][1] + board[2][2],
				d0 = board[0][0] + board[1][1] + board[2][2],
				d1 = board[0][2] + board[1][1] + board[2][0];
		
		if (c0 == 0 || c1 == 0 || c2 == 0 || r0 == 0 || r1 == 0 || r2 == 0 || d0 == 0 || d1 == 0) {
			System.out.println();
		}
	}

}
