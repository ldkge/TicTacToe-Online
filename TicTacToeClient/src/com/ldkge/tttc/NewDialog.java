package com.ldkge.tttc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.UnknownHostException;

public class NewDialog extends JFrame {

	private JPanel contentPane;
	private JTextField lblHostName;

	/**
	 * Create the frame.
	 */
	public NewDialog(final TTTClient tttc) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblConnectToServer = new JLabel("Connect to server");
		contentPane.add(lblConnectToServer, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblServerIp = new JLabel("Server IP");
		lblServerIp.setBounds(30, 50, 61, 16);
		panel.add(lblServerIp);
		
		lblHostName = new JTextField();
		lblHostName.setText("127.0.0.1");
		lblHostName.setBounds(30, 78, 134, 28);
		panel.add(lblHostName);
		lblHostName.setColumns(10);
		
		final JLabel lblError = new JLabel("");
		lblError.setBounds(206, 50, 170, 107);
		panel.add(lblError);
		
		final JButton btnOk = new JButton("OK");
		btnOk.setEnabled(false);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		final JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tttc.setHostName(lblHostName.getText());
				try {
					tttc.connect();
					lblError.setText("Connected successfully");
					btnOk.setEnabled(true);
					btnConnect.setEnabled(false);
				} 
				catch (UnknownHostException e0) {
					lblError.setText("Don't know about host " + lblHostName.getText());
				}
				catch (IOException e1) {
					lblError.setText("Couldn't get I/O for the connection to " + lblHostName.getText());
				}
				
				
			}
		});
		btnConnect.setBounds(30, 128, 117, 29);
		panel.add(btnConnect);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		panel_1.add(btnOk);
	}
}
