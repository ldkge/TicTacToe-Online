package com.ldkge.tttc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class TTTClient implements ActionListener {
	private String fromGui;
	private TTTGui tttg;
	private Socket tttSocket;
	private PrintWriter out;
	private BufferedReader in;
	private Boolean iPlay;
	private int[] score;
	private String hostName;

	public TTTClient(TTTGui tttg) {
		this.tttg = tttg;
		fromGui = "WAIT";
		iPlay = false;
		score = new int[] {0, 0};
		hostName = new String();
	}

	public void connect() throws UnknownHostException, IOException {
		int portNumber = 8081;


		tttSocket = new Socket(hostName, portNumber);
		out = new PrintWriter(tttSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(tttSocket.getInputStream()));

		gameInit(false);



	}

	public void gameInit(Boolean again) throws IOException {
		if (again) {
			out.println("NEWGAME");
			System.out.println("Client: NEWGAME");

			String input = in.readLine();
			System.out.println("Server: " + input);

			out.println("GO");
			System.out.println("Client: GO");
		}

		String input = in.readLine();
		System.out.println("Server: " + input);
		if (input.compareTo("PLAYER") == 0) {
			iPlay = true;
		}
		else {
			iPlay = false;
			listenMove();
		}
	}

	public void makeMove(int x, int y) {
		try {
			int m = x * 3 + y;
			BufferedImage image = ImageIO.read(new File("O.png"));
			tttg.getB()[m].setIcon(new ImageIcon(image));
			tttg.getB()[m].setEnabled(false);
		} 
		catch (IOException e) {

		}

	}

	private String listenMove() {
		String input;
		String output = null;
		try {
			out.println("GO");
			System.out.println("Client: GO");

			input = in.readLine();
			System.out.println("Server: " + input);

			String[] ins = input.split("\t");
			if (ins[0].compareTo("MOVE") == 0) {
				String[] move = ins[1].split(",");


				makeMove(Integer.parseInt(move[0]), Integer.parseInt(move[1]));

				if (ins.length > 2) {
					checkWin(ins[2]);
				}

			}
			else {
				checkWin(ins[0]);
			}

			iPlay = true;

		} 
		catch (IOException e) {

		}

		return output;
	}

	private Boolean checkWin(String in) {
		String[] input = in.split("\\|");
		if (input[0].compareTo("WIN0") == 0) {
			iPlay = false;
			tttg.getLblScore().setText(input[1]);
			AgainDialog ad = new AgainDialog(tttg, this);
			ad.setVisible(true);
			return false;

		}
		else if (input[0].compareTo("WIN1") == 0) {
			iPlay = false;
			tttg.getLblScore().setText(input[1]);
			AgainDialog ad = new AgainDialog(tttg, this);
			ad.setVisible(true);
			return false;
		}
		else if (input[0].compareTo("DRAW") == 0) {
			iPlay = false;
			tttg.getLblScore().setText(input[1]);
			AgainDialog ad = new AgainDialog(tttg, this);
			ad.setVisible(true);
			return false;
		}
		return true;
	}

	public void endGame() {
		out.println("BYE");
		System.out.println("Client: BYE");

		try {
			System.out.println("Server: " + in.readLine());
			tttSocket.close();
		} 
		catch (IOException e) {

		}
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (iPlay) {
			try {    
				BufferedImage image = ImageIO.read(new File("X.png"));
				((JButton) arg0.getSource()).setIcon(new ImageIcon(image));
				((JButton) arg0.getSource()).setEnabled(false);
				fromGui = "MOVE\t" + ((JButton) arg0.getSource()).getName();
				System.out.println("Client: " + fromGui);
				out.println(fromGui);
				String input = in.readLine();
				System.out.println("Server: " + input);
				if (checkWin(input)) {
					listenMove();
				}

			} 
			catch (IOException ex) {

			}
		}

	}

}
