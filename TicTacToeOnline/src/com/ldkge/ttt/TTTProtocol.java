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
	
	public TTTProtocol() {
		game = null;
		score = new int[] {0, 0};
	}
	
	public String processInput(String input) {
		String output = null;
		String outcome = null;
		
		switch (state) {
		case NEWGAME:
			game = new TicTacToe();
			
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
			//Calculate AI move
			
			outcome = game.makeMove(0, 0, 0);
			if (outcome == "Continue") {
				state = PLAYERONE;
				output = "0,0";
			}
			else {
				state = END;
				
				if (outcome == "WIN0") {
					score[0]++;
				}
				else if(outcome == "WIN1") {
					score[1]++;
				}
				else {
					score[0]++;
					score[1]++;
				}
				
				output = outcome + "|" + score[0] + "," + score[1];
			}	
			break;
		case PLAYERONE:
			String[] move = input.split(",");
			
			outcome = game.makeMove(Integer.parseInt(move[0]), Integer.parseInt(move[1]), 1);
			if (outcome == "Continue") {
				state = PLAYERZERO;
				output = "OK";
			}
			else {
				state = END;
				
				if (outcome == "WIN0") {
					score[0]++;
				}
				else if(outcome == "WIN1") {
					score[1]++;
				}
				else {
					score[0]++;
					score[1]++;
				}
				
				output = outcome + "|" + score[0] + "," + score[1];
			}
			break;
		case END:
			if (input == "NEWGAME") {
				state = NEWGAME;
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