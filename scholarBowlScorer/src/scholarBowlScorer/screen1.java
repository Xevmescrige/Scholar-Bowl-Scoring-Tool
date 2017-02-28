package scholarBowlScorer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class screen1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					screen1 frame = new screen1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*public void setName(String name) {
		this.setText(name);
	}*/

	/**
	 * Create the frame.
	 */
	public screen1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.setLayout(new GridLayout(1,4));
		
		JButton btnTeamA = new JButton("TEAM A");
		btnTeamA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c = (CardLayout)(contentPane.getLayout());
				c.show(contentPane, "tAP");
			}
		});
		menuBar.add(btnTeamA);
		JLabel lblSetNames = new JLabel("SET NAMES");
		lblSetNames.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetNames.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		menuBar.add(lblSetNames);
		
		JButton btnTeamB = new JButton("TEAM B");
		btnTeamB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout)(contentPane.getLayout());
				c.show(contentPane, "tAB");
			}
		});
		menuBar.add(btnTeamB);
		
		JButton btnStartMatch = new JButton("START MATCH");
		menuBar.add(btnStartMatch);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		Panel teamAPanel = new Panel();
		contentPane.add(teamAPanel, "tAP");
		teamAPanel.setLayout(new GridLayout(2,1));
		
		Panel tAPTP = new Panel();
		teamAPanel.add(tAPTP);
		tAPTP.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnSetTeamA = new JButton("SET TEAM A NAME");
		btnSetTeamA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teamAName = (String)JOptionPane.showInputDialog(contentPane, "ENTER TEAM A'S NAME HERE");
				btnTeamA.setText(teamAName);
			}
		});
		tAPTP.add(btnSetTeamA);
		
		JPanel tAPBP = new JPanel();
		teamAPanel.add(tAPBP);
		tAPBP.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton btnAP1 = new JButton("PLAYER 1");
		btnAP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String tAPl1 = (String)JOptionPane.showInputDialog(contentPane, "TEAM A - PLAYER 1'S NAME");
				btnAP1.setName((String)JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME"));
			}
		});
		tAPBP.add(btnAP1);
		
		JButton btnAP2 = new JButton("PLAYER 2");
		btnAP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tAPl2 = (String)JOptionPane.showInputDialog(contentPane, "TEAM A - PLAYER 2'S NAME");
				btnAP2.setText(tAPl2);
			}
		});
		tAPBP.add(btnAP2);
		
		JButton btnAP3 = new JButton("PLAYER 3");
		btnAP3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tAPBP.add(btnAP3);
		
		JButton btnAP4 = new JButton("PLAYER 4");
		btnAP4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tAPBP.add(btnAP4);
		
		Panel teamBPanel = new Panel();
		contentPane.add(teamBPanel, "tAB");
		teamBPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		Panel tBPTP = new Panel();
		teamBPanel.add(tBPTP);
		tBPTP.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnSetTeamB = new JButton("SET TEAM B NAME");
		btnSetTeamB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teamBName = (String)JOptionPane.showInputDialog(contentPane, "infoMessage");
				btnTeamB.setText(teamBName);
			}
		});
		tBPTP.add(btnSetTeamB);
		
		JPanel tBPBP = new JPanel();
		teamBPanel.add(tBPBP);
		tBPBP.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton button_1 = new JButton("PLAYER 1");
		tBPBP.add(button_1);
		
		JButton button_2 = new JButton("PLAYER 2");
		tBPBP.add(button_2);
		
		JButton button_3 = new JButton("PLAYER 3");
		tBPBP.add(button_3);
		
		JButton button_4 = new JButton("PLAYER 4");
		tBPBP.add(button_4);
	}

}
