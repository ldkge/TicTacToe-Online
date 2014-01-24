package com.ldkge.tttc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class AgainDialog extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AgainDialog(final TTTGui tttg, final TTTClient tttc) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblWannaPlayAgain = new JLabel("Do you want to play again?");
		contentPane.add(lblWannaPlayAgain, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < tttg.getB().length; i++) {
					tttg.getB()[i].setIcon(null);
					tttg.getB()[i].setEnabled(true);
				}
				
				try {
					tttc.gameInit(true);
				} 
				catch (IOException e) {
					
				}
				
				dispose();
			}
		});
		panel.add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tttc.endGame();
				dispose();
				System.exit(0);
			}
		});
		panel.add(btnNo);
	}

}
