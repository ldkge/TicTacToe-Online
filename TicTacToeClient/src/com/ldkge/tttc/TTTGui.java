package com.ldkge.tttc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TTTGui extends JFrame {

	private JPanel contentPane;
	private JLabel lblScore;
	private TTTClient tttc;
	private JButton[] b;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TTTGui frame = new TTTGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TTTGui() {
		tttc = new TTTClient(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewDialog nd = new NewDialog(tttc);
				nd.setVisible(true);
			}
		});
		mnFile.add(mntmNew);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblScore = new JLabel("0 : 0");
		lblScore.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		panel.add(lblScore);

		JPanel boardPane = new JPanel();
		contentPane.add(boardPane, BorderLayout.CENTER);
		boardPane.setLayout(null);

		GraphicBoard board = new GraphicBoard();
		board.setBounds(0, 0, 500, 500);
		boardPane.add(board);

		b = new JButton[9];

		for (int i = 0; i < b.length; i++) {
			b[i] = new JButton();
			b[i].setName((i / 3) + "," + (i % 3));
			b[i].setBounds(25 + 150 * (i % 3), 25 + 150 * (i / 3), 150, 150);
			b[i].addActionListener(tttc);
			boardPane.add(b[i]);
		}

	}
	
	public JLabel getLblScore() {
		return lblScore;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton[] getB() {
		return b;
	}
}
