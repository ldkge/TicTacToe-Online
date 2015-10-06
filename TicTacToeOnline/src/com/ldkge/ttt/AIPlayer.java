package com.ldkge.ttt;
/*thrambooooooooooo */
import java.util.ArrayList;
import java.util.List;

public class AIPlayer {
	int[][] board;
	int[][] oboard;

	public AIPlayer(int[][] board) {
		this.oboard = board;
	}

	public int[] makeMove() {
		board = oboard.clone();
		int[] result = minimax(2, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		// depth, max-turn, alpha, beta
		return new int[] {result[1], result[2]};   // row, col
	}

	private int[] minimax(int depth, int player, int alpha, int beta) {
		// Generate possible next moves in a list of int[2] of {row, col}.
		List<int[]> nextMoves = generateMoves();

		// mySeed is maximizing; while oppSeed is minimizing
		int score;
		int bestRow = -1;
		int bestCol = -1;

		if (nextMoves.isEmpty() || depth == 0) {
			// Gameover or depth reached, evaluate score
			score = evaluate();
			return new int[] {score, bestRow, bestCol};
		} else {
			for (int[] move : nextMoves) {
				// try this move for the current "player"
				board[move[0]][move[1]] = player;
				if (player == 0) {  // mySeed (computer) is maximizing player
					score = minimax(depth - 1, 1, alpha, beta)[0];
					if (score > alpha) {
						alpha = score;
						bestRow = move[0];
						bestCol = move[1];
					}
				} else {  // oppSeed is minimizing player
					score = minimax(depth - 1, 0, alpha, beta)[0];
					if (score < beta) {
						beta = score;
						bestRow = move[0];
						bestCol = move[1];
					}
				}
				// undo move
				board[move[0]][move[1]] = -3;
				// cut-off
				if (alpha >= beta) break;
			}
			return new int[] {(player == 0) ? alpha : beta, bestRow, bestCol};
		}
	}

	private List<int[]> generateMoves() {
		List<int[]> nextMoves = new ArrayList<int[]>(); // allocate List

		// If gameover, i.e., no next move
		if (hasWon(0) || hasWon(1)) {
			return nextMoves;   // return empty list
		}

		// Search for empty cells and add to the List
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 3; ++col) {
				if (board[row][col] == -3) {
					nextMoves.add(new int[] {row, col});
				}
			}
		}
		return nextMoves;
	}

	private int evaluate() {
		int score = 0;

		score += evaluateLine(0, 0, 0, 1, 0, 2);  
		score += evaluateLine(1, 0, 1, 1, 1, 2); 
		score += evaluateLine(2, 0, 2, 1, 2, 2);  
		score += evaluateLine(0, 0, 1, 0, 2, 0); 
		score += evaluateLine(0, 1, 1, 1, 2, 1);  
		score += evaluateLine(0, 2, 1, 2, 2, 2); 
		score += evaluateLine(0, 0, 1, 1, 2, 2);
		score += evaluateLine(0, 2, 1, 1, 2, 0);

		return score;
	}

	private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
		int score = 0;

		// First cell
		if (board[row1][col1] == 0) {
			score = 1;
		} 
		else if (board[row1][col1] == 1) {
			score = -1;
		}

		// Second cell
		if (board[row2][col2] == 0) {
			if (score == 1) {   // cell1 is mySeed
				score = 10;
			} else if (score == -1) {  // cell1 is oppSeed
				return 0;
			} else {  // cell1 is empty
				score = 1;
			}
		} 
		else if (board[row2][col2] == 1) {
			if (score == -1) { // cell1 is oppSeed
				score = -10;
			} 
			else if (score == 1) { // cell1 is mySeed
				return 0;
			} 
			else {  // cell1 is empty
				score = -1;
			}
		}

		// Third cell
		if (board[row3][col3] == 0) {
			if (score > 0) {  // cell1 and/or cell2 is mySeed
				score *= 10;
			} 
			else if (score < 0) {  // cell1 and/or cell2 is oppSeed
				return 0;
			} 
			else {  // cell1 and cell2 are empty
				score = 1;
			}
		} 
		else if (board[row3][col3] == 1) {
			if (score < 0) {  // cell1 and/or cell2 is oppSeed
				score *= 10;
			} 
			else if (score > 1) {  // cell1 and/or cell2 is mySeed
				return 0;
			} 
			else {  // cell1 and cell2 are empty
				score = -1;
			}
		}
		return score;
	}

	private boolean hasWon(int thePlayer) {
		int c0 = board[0][0] + board[1][0] + board[2][0],
				c1 = board[0][1] + board[1][1] + board[2][1],
				c2 = board[0][2] + board[1][2] + board[2][2],
				r0 = board[0][0] + board[0][1] + board[0][2],
				r1 = board[1][0] + board[1][1] + board[1][2],
				r2 = board[2][0] + board[2][1] + board[2][2],
				d0 = board[0][0] + board[1][1] + board[2][2],
				d1 = board[0][2] + board[1][1] + board[2][0];
		
		if (c0 == 0 || c1 == 0 || c2 == 0 || r0 == 0 || r1 == 0 || r2 == 0 || d0 == 0 || d1 == 0) {
			return thePlayer == 0;
		}
		else if (c0 == 3 || c1 == 3 || c2 == 3 || r0 == 3 || r1 == 3 || r2 == 3 || d0 == 3 || d1 == 3) {
			return thePlayer == 1;
		}
		
		return false;
	}
}
