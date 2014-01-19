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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
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
		
		JButton[] b = new JButton[9];
		
		for (int i = 0; i < b.length; i++) {
			b[i] = new JButton();
			b[i].setBounds(25 + 150 * (i % 3), 25 + 150 * (i / 3), 150, 150);
			b[i].addActionListener(new BtnPressedHandler(b, i));
			boardPane.add(b[i]);
		}
		
	}
	
	public void setLblScore(JLabel lblScore) {
		this.lblScore = lblScore;
	}
	
	private class BtnPressedHandler implements ActionListener {

		private JButton[] b;
		private int i;
		
		public BtnPressedHandler(JButton[] b, int i) {
			this.b = b;
			this.i = i;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {                
				BufferedImage image = ImageIO.read(new File("X.png"));
				b[i].setIcon(new ImageIcon(image));
				b[i].setEnabled(false);
			} 
			catch (IOException ex) {

			}
			
		}
		
	}

}
