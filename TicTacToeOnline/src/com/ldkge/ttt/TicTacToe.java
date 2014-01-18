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
	
	public String makeMove(int x, int y, int player) {
		if (board[x][y] == -3 && (player == 0 || player == 1)) {
			board[x][y] = player;
		}
		else {
			System.out.println("Invalid move");
		}
		
		return checkWin();
	}
	
	private String checkWin() {
		int c0 = board[0][0] + board[1][0] + board[2][0],
				c1 = board[0][1] + board[1][1] + board[2][1],
				c2 = board[0][2] + board[1][2] + board[2][2],
				r0 = board[0][0] + board[0][1] + board[0][2],
				r1 = board[1][0] + board[1][1] + board[1][2],
				r2 = board[2][0] + board[2][1] + board[2][2],
				d0 = board[0][0] + board[1][1] + board[2][2],
				d1 = board[0][2] + board[1][1] + board[2][0], 
				k = 0;
		
		for (int[] c : board) {
			for (int i : c) {
				if (i != -3) {
					k++;
				}
			}
		}
		
		if (c0 == 0 || c1 == 0 || c2 == 0 || r0 == 0 || r1 == 0 || r2 == 0 || d0 == 0 || d1 == 0) {
			System.out.println("WIN0");
			return "Player 0 wins";
		}
		else if (c0 == 3 || c1 == 3 || c2 == 3 || r0 == 3 || r1 == 3 || r2 == 3 || d0 == 3 || d1 == 3) {
			System.out.println("WIN1");
			return "Player 1 wins";
		}
		else if (k == 9) {
			System.out.println("DRAW");
			return "Draw";
		}
		
		return "Continue";
	}

}
