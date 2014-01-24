package com.ldkge.ttt;

import java.util.Random;

public class TTTProtocol {
	private static final int NEWGAME = 0;
	private static final int PLAYERZERO = 1;
	private static final int PLAYERONE = 2;
	private static final int END = 3;

	private int state = NEWGAME;
	private int[] score;
	private TicTacToe game;
	private AIPlayer ai;

	public TTTProtocol() {
		game = null;
		ai = null;
		score = new int[] {0, 0};
	}

	public String processInput(String input) {
		String output = null;
		String outcome = null;

		switch (state) {
		case NEWGAME:
			game = new TicTacToe();
			ai = new AIPlayer(game.getBoard());

			Random ran = new Random();
			if (ran.nextInt() % 2 == 0) {
				state = PLAYERZERO;
				output = "AI";
			}
			else {
				state = PLAYERONE;
				output = "PLAYER";
			}
			break;
		case PLAYERZERO:
			int[] aiMove = ai.makeMove();

			outcome = game.makeMove(aiMove[0], aiMove[1], 0);
			if (outcome.compareTo("Continue") == 0) {
				state = PLAYERONE;
				output = "MOVE\t" + aiMove[0] + "," + aiMove[1];
			}
			else {
				state = END;

				if (outcome.compareTo("WIN0") == 0) {
					score[0]++;
				}
				else if(outcome.compareTo("WIN1") == 0) {
					score[1]++;
				}
				else {
					score[0]++;
					score[1]++;
				}

				output = "MOVE\t" + aiMove[0] + "," + aiMove[1] + "\t" + outcome + "|" + score[0] + ":" + score[1];
			}	
			break;
		case PLAYERONE:
			String[] in = input.split("\t");
			if (in[0].compareTo("MOVE") == 0) {
				String[] move = in[1].split(",");

				outcome = game.makeMove(Integer.parseInt(move[0]), Integer.parseInt(move[1]), 1);
				if (outcome == "Continue") {
					state = PLAYERZERO;
					output = "OK";
				}
				else {
					state = END;

					if (outcome.compareTo("WIN0") == 0) {
						score[0]++;
					}
					else if(outcome.compareTo("WIN1") == 0) {
						score[1]++;
					}
					else {
						score[0]++;
						score[1]++;
					}

					output = outcome + "|" + score[0] + ":" + score[1];
				}
			}
			break;
		case END:
			if (input.compareTo("NEWGAME") == 0) {
				state = NEWGAME;
				output = "OK";
			}
			else {
				output = "BYE";
			}
			break;

		default:
			break;
		}

		return output;
	}
}
