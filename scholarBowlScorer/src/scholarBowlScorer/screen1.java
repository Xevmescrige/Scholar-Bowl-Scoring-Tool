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
import javax.swing.JTextField;

public class screen1 extends JFrame {

	private static final long serialVersionUID = 6753098757993050116L;
	private JPanel contentPane;
	private JTextField tfRoom;
	private JTextField tfModerator;
	private JTextField tfRound;
	private JTextField tfPacket;
	private JTextField tfScoreKeeper;

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

	public int questionNum = 1;

	/**
	 * Create the frame.
	 */
	public screen1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);

		contentPane = new JPanel();

		JMenuBar menuBar_setname = new JMenuBar();
		menuBar_setname.setLayout(new GridLayout(1, 5));
		JMenuBar menuBar_scoreScreen = new JMenuBar();
		menuBar_scoreScreen.setLayout(new GridLayout(1, 4));
		JMenuBar menuBar_setup = new JMenuBar();
		menuBar_setup.setLayout(new GridLayout(1, 4));
		contentPane.setLayout(new CardLayout(0, 0));

		JPanel matchDetailsPanel = new JPanel();
		contentPane.add(matchDetailsPanel, "mdp1");
		matchDetailsPanel.setLayout(new GridLayout(5, 2, 0, 0));

		Panel teamAPanel = new Panel();
		contentPane.add(teamAPanel, "tAP");
		teamAPanel.setLayout(new GridLayout(2, 1));

		Panel tAPTP = new Panel();
		teamAPanel.add(tAPTP);
		tAPTP.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel tAPBP = new JPanel();
		teamAPanel.add(tAPBP);
		tAPBP.setLayout(new GridLayout(0, 4, 0, 0));

		Panel teamBPanel = new Panel();
		contentPane.add(teamBPanel, "tAB");
		teamBPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnPPA1 = new JButton("P1");
		JButton btnPPA2 = new JButton("P2");
		JButton btnPPA3 = new JButton("P3");
		JButton btnPPA4 = new JButton("P4");
		JButton btnPPB1 = new JButton("P1");
		JButton btnPPB2 = new JButton("P2");
		JButton btnPPB3 = new JButton("P3");
		JButton btnPPB4 = new JButton("P4");
		
		JLabel lblTeamA = new JLabel("TEAM A");
		JLabel lblTeamB = new JLabel("TEAM B");
		
		JButton btnTeamA = new JButton("TEAM A");
		JButton btnTeamB = new JButton("TEAM B");
		
		JButton btnStartMatch = new JButton("START MATCH");
		JButton btnSetTeamA = new JButton("SET TEAM A NAME");
		JButton btnAP1 = new JButton("PLAYER 1");
		JButton btnAP2 = new JButton("PLAYER 2");
		JButton btnAP3 = new JButton("PLAYER 3");
		JButton btnAP4 = new JButton("PLAYER 4");
		JButton btnSetTeamB = new JButton("SET TEAM B NAME");
		JButton btnBP1 = new JButton("PLAYER 1");
		JButton btnBP2 = new JButton("PLAYER 2");
		JButton btnBP3 = new JButton("PLAYER 3");
		JButton btnBP4 = new JButton("PLAYER 4");

		RoundID round = new RoundID("", "", "", "", "");
		JButton buttonTNBack = new JButton("<-- BACK");
		buttonTNBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "mdp1");
				setJMenuBar(menuBar_setup);
			}
		});
		menuBar_setname.add(buttonTNBack);

		
		btnTeamA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "tAP");
			}
		});

		menuBar_setname.add(btnTeamA);

		
		btnTeamB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "tAB");
			}
		});
		JLabel lblSetNames = new JLabel("SET NAMES");
		lblSetNames.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetNames.setFont(new Font("Tahoma", Font.PLAIN, 16));

		menuBar_setname.add(lblSetNames);
		menuBar_setname.add(btnTeamB);

		
		btnStartMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "qP");
				setJMenuBar(menuBar_scoreScreen);
				if (btnStartMatch.getText() == "START MATCH") {
					btnStartMatch.setText("RETURN TO MATCH");
				}
			}
		});
		menuBar_setname.add(btnStartMatch);
		
		btnSetTeamA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teamAName = (String) JOptionPane.showInputDialog(contentPane, "ENTER TEAM A'S NAME HERE");
				btnTeamA.setText(teamAName);
				lblTeamA.setText(teamAName);
			}
		});
		tAPTP.add(btnSetTeamA);
		
		btnAP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AP1name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnAP1.setText(AP1name);
				btnPPA1.setText(AP1name);
			}
		});
		tAPBP.add(btnAP1);
		
		btnAP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AP2name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnAP2.setText(AP2name);
				btnPPA2.setText(AP2name);
			}
		});
		tAPBP.add(btnAP2);
		
		btnAP3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AP3name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnAP3.setText(AP3name);
				btnPPA3.setText(AP3name);
			}
		});
		tAPBP.add(btnAP3);
		
		btnAP4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AP4name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnAP4.setText(AP4name);
				btnPPA4.setText(AP4name);
			}
		});
		tAPBP.add(btnAP4);

		Panel tBPTP = new Panel();
		teamBPanel.add(tBPTP);
		tBPTP.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnSetTeamB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teamBName = (String) JOptionPane.showInputDialog(contentPane, "ENTER TEAM B'S NAME");
				btnTeamB.setText(teamBName);
				lblTeamB.setText(teamBName);
			}
		});
		tBPTP.add(btnSetTeamB);

		JPanel tBPBP = new JPanel();
		teamBPanel.add(tBPBP);
		tBPBP.setLayout(new GridLayout(0, 4, 0, 0));
		
		btnBP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String BP1name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnBP1.setText(BP1name);
				btnPPB1.setText(BP1name);
			}
		});
		tBPBP.add(btnBP1);
		
		btnBP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String BP2name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnBP2.setText(BP2name);
				btnPPB2.setText(BP2name);
			}
		});
		tBPBP.add(btnBP2);
		
		btnBP3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String BP3name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnBP3.setText(BP3name);
				btnPPB3.setText(BP3name);
			}
		});
		tBPBP.add(btnBP3);
		
		btnBP4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String BP4name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnBP4.setText(BP4name);
				btnPPB4.setText(BP4name);
			}
		});
		tBPBP.add(btnBP4);

		// setJMenuBar(menuBar_setname);

		setJMenuBar(menuBar_setup);
		// setJMenuBar(menuBar_scoreScreen);

		JPanel questionPanel = new JPanel();
		contentPane.add(questionPanel, "qP");
		questionPanel.setLayout(new GridLayout(3, 1, 0, 0));

		JLabel lblQNumber = new JLabel("QUESTION: " + questionNum);
		questionPanel.add(lblQNumber);
		lblQNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblQNumber.setFont(new Font("Tahoma", Font.PLAIN, 26));

		JPanel tAPlayers = new JPanel();
		questionPanel.add(tAPlayers);
		tAPlayers.setLayout(new BoxLayout(tAPlayers, BoxLayout.Y_AXIS));

		
		lblTeamA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTeamA.setHorizontalAlignment(SwingConstants.CENTER);
		tAPlayers.add(lblTeamA);
		
		JButton btnFinish = new JButton("END ROUND");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Code to export to xls?
			}
		});

		JPanel tAPBtns = new JPanel();
		tAPlayers.add(tAPBtns);
		tAPBtns.setLayout(new GridLayout(0, 4, 0, 0));

		
		tAPBtns.add(btnPPA1);

		
		tAPBtns.add(btnPPA2);

		
		tAPBtns.add(btnPPA3);

		
		tAPBtns.add(btnPPA4);

		JPanel tBPlayers = new JPanel();
		questionPanel.add(tBPlayers);
		tBPlayers.setLayout(new BoxLayout(tBPlayers, BoxLayout.Y_AXIS));
		
		lblTeamB.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tBPlayers.add(lblTeamB);

		JPanel tBPBtns = new JPanel();
		tBPlayers.add(tBPBtns);
		tBPBtns.setLayout(new GridLayout(1, 0, 0, 0));

		
		tBPBtns.add(btnPPB1);

		
		tBPBtns.add(btnPPB2);

		
		tBPBtns.add(btnPPB3);

		
		tBPBtns.add(btnPPB4);

		JButton btnSaveCurrentSettings = new JButton("SAVE CURRENT INPUT");
		btnSaveCurrentSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				round.rroom = tfRoom.getText();
				round.rpacket = tfPacket.getText();
				round.rrnumber = tfRound.getText();
				round.rmod = tfModerator.getText();
				round.rskeep = tfScoreKeeper.getText();
			}
		});
		menuBar_setup.add(btnSaveCurrentSettings);

		JButton btnLoadSettings = new JButton("LOAD INPUT");
		btnLoadSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfRoom.setText(round.rroom); 
				tfPacket.setText(round.rpacket); 
				tfRound.setText(round.rrnumber); 
				tfModerator.setText(round.rmod); 
				tfScoreKeeper.setText(round.rskeep); 
			}
		});
		menuBar_setup.add(btnLoadSettings);

		JButton btnNext = new JButton("NEXT -->");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "tAP");
				setJMenuBar(menuBar_setname);
			}
		});
		menuBar_setup.add(btnNext);
		// setJMenuBar(menuBar_scoreScreen);

		JButton btnSBack = new JButton("<-- BACK TO SETUP");
		btnSBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "tAP");
				setJMenuBar(menuBar_setname);
			}
		});
		menuBar_scoreScreen.add(btnSBack);

		JButton btnPQ = new JButton("<-- PREVIOUS QUESTION");
		btnPQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (questionNum > 1) {
					questionNum -= 1;
					lblQNumber.setText("QUESTION: " + (Integer.toString(questionNum)));
				} else {
					questionNum = 1;
					lblQNumber.setText("QUESTION: " + (Integer.toString(questionNum)));
				}
			}
		});
		menuBar_scoreScreen.add(btnPQ);

		JButton btnViewScoreboard = new JButton("VIEW SCOREBOARD");
		menuBar_scoreScreen.add(btnViewScoreboard);

		JButton btnNextQuestion = new JButton("NEXT QUESTION -->");
		btnNextQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "qP");
				if (questionNum <= 19) {
					questionNum += 1;
					if (questionNum == 20) {
						menuBar_scoreScreen.add(btnFinish);
					}
				} 
				else if (questionNum >= 20) {
					
					if (JOptionPane.showConfirmDialog(contentPane,
							"Are you sure you want to go past question " + questionNum
									+ "?  If this is not your intention, click the \"FINISH\" button",
							"Question", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						questionNum += 1;
					}
					
				}
				lblQNumber.setText("QUESTION: " + (Integer.toString(questionNum)));
			}
		});
		menuBar_scoreScreen.add(btnNextQuestion);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblRoom = new JLabel("ROOM:");
		matchDetailsPanel.add(lblRoom);

		tfRoom = new JTextField();
		matchDetailsPanel.add(tfRoom);
		tfRoom.setColumns(10);

		JLabel lblModerator = new JLabel("MODERATOR:");
		matchDetailsPanel.add(lblModerator);

		tfModerator = new JTextField();
		matchDetailsPanel.add(tfModerator);
		tfModerator.setColumns(10);

		JLabel lblRound = new JLabel("ROUND:");
		matchDetailsPanel.add(lblRound);

		tfRound = new JTextField();
		matchDetailsPanel.add(tfRound);
		tfRound.setColumns(10);

		JLabel lblPacket = new JLabel("PACKET:");
		matchDetailsPanel.add(lblPacket);

		tfPacket = new JTextField();
		matchDetailsPanel.add(tfPacket);
		tfPacket.setColumns(10);

		JLabel lblScorekeeper = new JLabel("SCOREKEEPER:");
		matchDetailsPanel.add(lblScorekeeper);

		tfScoreKeeper = new JTextField();
		matchDetailsPanel.add(tfScoreKeeper);
		tfScoreKeeper.setColumns(10);

	}

}
