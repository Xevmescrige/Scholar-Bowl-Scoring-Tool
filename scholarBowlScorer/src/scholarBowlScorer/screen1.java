package scholarBowlScorer;

//import java.awt.BorderLayout;
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
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
//import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;
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

	static int scoresA = 0;
	static int scoresB = 0;
	public static String teamAName = "TEAM A";
	public static String teamBName = "TEAM B";
	public int questionNum = 0;
	public int playerNum = 0;
	public boolean currentTeam = true; // true is A, false is B
	public static double[][] scores = new double[20][12]; // 12x20(+) array, arrays 1-6 are team A (4+2subs), 7-12 are
															// for B.
	public static String[] Players = new String[12];
	public static double[][] bonusArray = new double[20][4]; // column 0-1 for team A, 2-3 for B -- 0, 2 are bonus, 1,3
																// are
	// rebound
	public boolean Ap5 = false;
	public boolean Bp5 = false;
	public boolean Ap6 = false;
	public boolean Bp6 = false;
	public boolean timeout = false;
	public boolean answered = false;

	public static void printArray(double mat[][]) {
		for (double[] row : mat)
			System.out.println(Arrays.toString(row));
	}

	public void add_score(double value) {
		for (int i = 0; i < 12; i++) {
			if (scores[questionNum][i] != 0.01 && i != playerNum) {
				scores[questionNum][i] = 0;
			}
		}
		scores[questionNum][playerNum] = value;
	}

	public void update_score() {
		int subTotalA = 0;
		int subTotalB = 0;
		for (int i = 0; i < 12; i++) {
			if ((i >= 0) && (i <= 5)) {
				for (int j = 0; j <= questionNum; j++) {
					subTotalA += scores[j][i];
				}
			} else if ((i >= 6) && (i <= 11)) {
				for (int j = 0; j <= questionNum; j++) {
					subTotalB += scores[j][i];
				}
			}
		}
		for (int j = 0; j <= questionNum; j++) {
			subTotalA += bonusArray[j][0];
			subTotalA += bonusArray[j][1];
			scoresA = subTotalA;
		}
		for (int j = 0; j <= questionNum; j++) {
			subTotalB += bonusArray[j][2];
			subTotalB += bonusArray[j][3];
			scoresB = subTotalB;
		}
	}

	public void replace_sub(JButton thisBtn, JButton button1, JButton button2, JButton button3, JButton button4,
			JButton button5, JButton button6, String string, JPanel panel, JPanel panel2, JButton ethisBtn,
			JButton ebutton1, JButton ebutton2, JButton ebutton3, JButton ebutton4, JButton ebutton5,
			JButton ebutton6) {
		if (ebutton1.getText() == string) {
			panel.remove(thisBtn);
			panel.add(button1);
			panel.validate();
			panel2.remove(ethisBtn);
			panel2.add(ebutton1);
			panel2.validate();
		} else if (ebutton2.getText() == string) {
			panel.remove(thisBtn);
			panel.add(button2);
			panel.validate();
			panel2.remove(ethisBtn);
			panel2.add(ebutton2);
			panel2.validate();
		} else if (ebutton3.getText() == string) {
			panel.remove(thisBtn);
			panel.add(button3);
			panel.validate();
			panel2.remove(ethisBtn);
			panel2.add(ebutton3);
			panel2.validate();
		} else if (ebutton4.getText() == string) {
			panel.remove(thisBtn);
			panel.add(button4);
			panel.validate();
			panel2.remove(ethisBtn);
			panel2.add(ebutton4);
			panel2.validate();
		} else if (ebutton5.getText() == string) {
			panel.remove(thisBtn);
			panel.add(button5);
			panel.validate();
			panel2.remove(ethisBtn);
			panel2.add(ebutton5);
			panel2.validate();
		} else if (ebutton6.getText() == string) {
			panel.remove(thisBtn);
			panel.add(button6);
			panel.validate();
			panel2.remove(ethisBtn);
			panel2.add(ebutton6);
			panel2.validate();
		}
	}

	public void playerAnswered(JLabel label, String player) {
		CardLayout c = (CardLayout) (contentPane.getLayout());
		c.show(contentPane, "ap1");
		JMenuBar Menu = getJMenuBar();
		Menu.setVisible(false);
		label.setText(player);
	}

	public void answeredBonus(JLabel thisTeam, JLabel thatTeam) {
		CardLayout c = (CardLayout) (contentPane.getLayout());
		c.show(contentPane, "bP");
		if ((playerNum >= 0) && (playerNum <= 5)) {
			currentTeam = true;
			thisTeam.setText(teamAName);
			thatTeam.setText(teamBName);
		}

		else if ((playerNum >= 6) && (playerNum <= 11)) {
			currentTeam = false;
			thisTeam.setText(teamBName);
			thatTeam.setText(teamAName);
		}

	}

	public void answeredNext() {
		CardLayout c = (CardLayout) (contentPane.getLayout());
		c.show(contentPane, "qP");
		JMenuBar Menu = getJMenuBar();
		Menu.setVisible(true);
	}

	// nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore,
	// lblTBScore);
	@SuppressWarnings("deprecation")
	public void nextQuestion(JLabel label, JMenuBar menubar, JButton button, JLabel label2, JLabel label3) {
		CardLayout c = (CardLayout) (contentPane.getLayout());
		c.show(contentPane, "qP");
		if (questionNum <= 18) {
			if (questionNum == 9) {
				c.show(contentPane, "htp1");
				menubar.hide();
			} else if (questionNum == 18) {
				menubar.add(button);
				questionNum += 1;
			} else {
				questionNum += 1;
			}
		} else if (questionNum > 18) {
			if (JOptionPane.showConfirmDialog(contentPane,
					"Are you sure you want to go past question " + (questionNum + 1)
							+ "?  If this is not your intention, click the \"END ROUND\" button",
					"Question", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				questionNum += 1;
			}

		}
		label.setText("QUESTION: " + (Integer.toString(questionNum + 1)));
		label2.setText(teamAName.toUpperCase() + ": " + scoresA);
		label3.setText(teamBName.toUpperCase() + ": " + scoresB);
	}

	public void bonusDone(JLabel ttBP, JLabel thtBP) {
		if (currentTeam) {
			double thisScore = Double.parseDouble(ttBP.getText());
			// scoresA += thisScore;
			bonusArray[questionNum][0] = thisScore;
			double thatScore = Double.parseDouble(thtBP.getText());
			// scoresB += thatScore;
			bonusArray[questionNum][3] = thatScore;
			update_score();
		} else {
			double thatScore = Double.parseDouble(ttBP.getText());
			double thisScore = Double.parseDouble(thtBP.getText());
			// scoresA += thisScore;
			bonusArray[questionNum][2] = thatScore;
			// scoresB += thatScore;
			bonusArray[questionNum][1] = thisScore;
			update_score();
		}
	}

	/*
	 * public void showGUI(){ JLabel[] labels=createLabels(); for (int
	 * i=0;i<labels.length;i++){ this.add(labels[i]); } }
	 */

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
		menuBar_scoreScreen.setLayout(new GridLayout(1, 5));
		JMenuBar menuBar_setup = new JMenuBar();
		menuBar_setup.setLayout(new GridLayout(1, 4));
		contentPane.setLayout(new CardLayout(0, 0));
		JLabel lblPlayName = new JLabel("");
		lblPlayName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPlayName.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel matchDetailsPanel = new JPanel(new GridLayout(5, 2, 0, 0));
		contentPane.add(matchDetailsPanel, "mdp1");

		JPanel teamAPanel = new JPanel(new GridLayout(2, 1));
		contentPane.add(teamAPanel, "tAP");

		JPanel tAPTP = new JPanel(new GridLayout(0, 1, 0, 0));
		teamAPanel.add(tAPTP);

		JPanel tAPBP = new JPanel(new GridLayout(0, 4, 0, 0));
		teamAPanel.add(tAPBP);

		JPanel teamBPanel = new JPanel(new GridLayout(0, 1, 0, 0));
		contentPane.add(teamBPanel, "tAB");

		JPanel halftimePanel = new JPanel();
		contentPane.add(halftimePanel, "htp1");
		halftimePanel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel htPanelMain = new JPanel(new GridLayout(2, 0, 0, 0));
		halftimePanel.add(htPanelMain);
		JPanel htPanelTop = new JPanel(new GridLayout(3, 0, 0, 0));
		JPanel htPanelBot = new JPanel(new GridLayout(3, 0, 0, 0));
		htPanelMain.add(htPanelTop);
		htPanelMain.add(htPanelBot);
		JLabel subs = new JLabel("SUBSTITUTIONS");
		subs.setFont(new Font("Tahoma", Font.PLAIN, 28));
		subs.setHorizontalAlignment(SwingConstants.CENTER);
		htPanelBot.add(subs);
		JPanel htTeamAS = new JPanel(new GridLayout(0, 5, 0, 0));
		JPanel htTeamBS = new JPanel(new GridLayout(0, 5, 0, 0));
		htPanelBot.add(htTeamAS);
		JButton htSubAbtn1 = new JButton("P1");
		JButton htSubAbtn2 = new JButton("P2");
		JButton htSubAbtn3 = new JButton("P3");
		JButton htSubAbtn4 = new JButton("P4");
		JButton htSubAbtn5 = new JButton("P5");
		JButton htSubAbtn6 = new JButton("P6");
		JButton htSubBbtn5 = new JButton("P5");
		JButton htSubBbtn6 = new JButton("P6");
		JLabel htTAlbl = new JLabel("TEAM A:");
		htTAlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		htTAlbl.setHorizontalAlignment(SwingConstants.CENTER);
		htTeamAS.add(htTAlbl);
		htTeamAS.add(htSubAbtn1);
		htTeamAS.add(htSubAbtn2);
		htTeamAS.add(htSubAbtn3);
		htTeamAS.add(htSubAbtn4);

		for (int i = 0; i < 20; i++) {
			scores[i][4] = 0.01;
			scores[i][5] = 0.01;
			scores[i][10] = 0.01;
			scores[i][11] = 0.01;
		}

		for (int i = 0; i < 12; i++) {
			Players[i] = "P" + i;
		}

		htPanelBot.add(htTeamBS);
		JButton htSubBbtn1 = new JButton("P1");
		JButton htSubBbtn2 = new JButton("P2");
		JButton htSubBbtn3 = new JButton("P3");
		JButton htSubBbtn4 = new JButton("P4");
		JLabel htTBlbl = new JLabel("TEAM B:");
		htTBlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		htTBlbl.setHorizontalAlignment(SwingConstants.CENTER);
		htTeamBS.add(htTBlbl);
		htTeamBS.add(htSubBbtn1);
		htTeamBS.add(htSubBbtn2);
		htTeamBS.add(htSubBbtn3);
		htTeamBS.add(htSubBbtn4);
		JLabel htlbl = new JLabel("HALFTIME");
		htlbl.setHorizontalAlignment(SwingConstants.CENTER);
		htlbl.setFont(new Font("Tahoma", Font.PLAIN, 28));
		htPanelTop.add(htlbl);
		JPanel htSC = new JPanel(new GridLayout(0, 3, 0, 0));
		htPanelTop.add(htSC);
		JLabel htTAScorelbl = new JLabel("TEAM A SCORE:");
		htTAScorelbl.setHorizontalAlignment(SwingConstants.CENTER);
		htTAScorelbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel htTBScorelbl = new JLabel("TEAM B SCORE:");
		htTBScorelbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		htTBScorelbl.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel htSCBot = new JPanel(new GridLayout(0, 2, 0, 0));
		htPanelTop.add(htSCBot);
		JButton htCont = new JButton("CONTINUE ->");
		JButton htBack = new JButton("<- BACK");
		htSCBot.add(htBack);
		htSCBot.add(htCont);

		JButton htSCbtn = new JButton("SCOREBOARD");
		htSCbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen2 screen2 = new screen2();
				screen2.pack();
				screen2.setVisible(true);
			}
		});
		htSC.add(htTAScorelbl);
		htSC.add(htSCbtn);
		htSC.add(htTBScorelbl);
		JPanel scorePanel = new JPanel(new GridLayout(0, 1, 0, 0));
		contentPane.add(scorePanel, "sP");

		JPanel bonusPanel = new JPanel(new GridLayout(0, 3, 0, 0));
		contentPane.add(bonusPanel, "bP");

		JButton bonusConfirm = new JButton();
		bonusConfirm.setText("CONFIRM");

		JButton bonusBack = new JButton();
		bonusBack.setText("BACK");
		JLabel thisTeamN = new JLabel("ANSWERING TEAM");
		JLabel thatTeamN = new JLabel("REBOUNDING TEAM");
		thisTeamN.setHorizontalAlignment(SwingConstants.CENTER);
		thatTeamN.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel thisTeamBP = new JLabel();
		JLabel thatTeamBP = new JLabel();
		thisTeamBP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		thisTeamBP.setHorizontalAlignment(SwingConstants.CENTER);
		thatTeamBP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		thatTeamBP.setHorizontalAlignment(SwingConstants.CENTER);
		thisTeamBP.setText("0");
		thatTeamBP.setText("0");

		JButton btnRP = new JButton();
		btnRP.setText("+10");
		btnRP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Integer.parseInt(thatTeamBP.getText()) <= 20)
						&& (Integer.parseInt(thatTeamBP.getText()) + Integer.parseInt(thisTeamBP.getText()) < 30)) {
					thatTeamBP.setText(Integer.toString((Integer.parseInt(thatTeamBP.getText()) + 10)));
				}
			}
		});

		JButton btnRM = new JButton();
		btnRM.setText("-10");
		btnRM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Integer.parseInt(thatTeamBP.getText()) >= 10)) {
					thatTeamBP.setText(Integer.toString((Integer.parseInt(thatTeamBP.getText()) - 10)));
				}
			}
		});
		JButton btnLP = new JButton();
		btnLP.setText("+10");
		btnLP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Integer.parseInt(thisTeamBP.getText()) <= 20)
						&& (Integer.parseInt(thatTeamBP.getText()) + Integer.parseInt(thisTeamBP.getText()) < 30)) {
					thisTeamBP.setText(Integer.toString((Integer.parseInt(thisTeamBP.getText()) + 10)));
				}
			}
		});

		JButton btnLM = new JButton();
		btnLM.setText("-10");
		btnLM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(thisTeamBP.getText()) >= 10) {
					thisTeamBP.setText(Integer.toString((Integer.parseInt(thisTeamBP.getText()) - 10)));
				}
			}
		});

		JPanel bleft = new JPanel(new GridLayout(3, 1, 0, 0));
		JPanel bmiddle = new JPanel(new GridLayout(3, 1, 0, 0));
		JPanel bright = new JPanel(new GridLayout(3, 1, 0, 0));
		bonusPanel.add(bleft);
		bonusPanel.add(bmiddle);
		bonusPanel.add(bright);

		String text = "<html>This is a bonus.  Use the plus and minus score buttons to keep track of how many points each team scores on the bonus, then confirm.</html>";
		JLabel bonusInfo = new JLabel("<html><div style='text-align: center;'>" + text + "</div></html>");
		bonusInfo.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel blBot = new JPanel(new GridLayout(0, 2, 0, 0));
		JPanel brBot = new JPanel(new GridLayout(0, 2, 0, 0));

		bleft.add(thisTeamN);
		bleft.add(blBot);
		bleft.add(thisTeamBP);

		bright.add(thatTeamN);
		bright.add(brBot);
		bright.add(thatTeamBP);

		bmiddle.add(bonusConfirm);
		bmiddle.add(bonusInfo);
		bmiddle.add(bonusBack);

		blBot.add(btnLM);
		blBot.add(btnLP);
		brBot.add(btnRM);
		brBot.add(btnRP);

		JPanel answerPanel = new JPanel(new GridLayout(0, 3, 0, 0));
		JPanel aPane1 = new JPanel(new GridLayout(2, 0, 0, 0));
		JPanel aPane2 = new JPanel(new GridLayout(2, 1, 0, 0));
		JPanel aPane3 = new JPanel(new GridLayout(2, 0, 0, 0));
		answerPanel.add(aPane1);
		answerPanel.add(aPane2);
		answerPanel.add(aPane3);
		JButton btnAnsC = new JButton("Correct");
		JButton btnAnsI = new JButton("Incorrect - No Neg");
		JButton btnAnsN = new JButton("Neg");
		JButton btnAnsP = new JButton("Power");
		JButton btnAnsCancel = new JButton("Cancel");
		contentPane.add(answerPanel, "ap1");
		aPane1.add(btnAnsC);
		aPane2.add(btnAnsCancel);
		aPane3.add(btnAnsI);
		aPane3.add(btnAnsN);
		aPane1.add(btnAnsP);
		aPane2.add(lblPlayName);

		btnAnsCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printArray(scores);
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "qP");
				getJMenuBar().setVisible(true);
			}
		});

		JButton btnPPA1 = new JButton("P1");
		JButton btnPPA2 = new JButton("P2");
		JButton btnPPA3 = new JButton("P3");
		JButton btnPPA4 = new JButton("P4");
		JButton btnPPA5 = new JButton("P5");
		JButton btnPPA6 = new JButton("P6");
		JButton btnPPB1 = new JButton("P1");
		JButton btnPPB2 = new JButton("P2");
		JButton btnPPB3 = new JButton("P3");
		JButton btnPPB4 = new JButton("P4");
		JButton btnPPB5 = new JButton("P5");
		JButton btnPPB6 = new JButton("P6");

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
		// JButton btnAAp5 = new JButton("PLAYER 5");
		// JButton btnAP6 = new JButton("PLAYER 6");

		JButton btnSetTeamB = new JButton("SET TEAM B NAME");
		JButton btnBP1 = new JButton("PLAYER 1");
		JButton btnBP2 = new JButton("PLAYER 2");
		JButton btnBP3 = new JButton("PLAYER 3");
		JButton btnBP4 = new JButton("PLAYER 4");
		// JButton btnBAp5 = new JButton("PLAYER 5");
		// JButton btnBP6 = new JButton("PLAYER 6");

		JLabel lblRoom = new JLabel("ROOM:");
		tfRoom = new JTextField();
		JLabel lblModerator = new JLabel("MODERATOR:");
		tfModerator = new JTextField();
		JLabel lblRound = new JLabel("ROUND:");
		tfRound = new JTextField();
		JLabel lblPacket = new JLabel("PACKET:");
		tfPacket = new JTextField();
		JLabel lblScorekeeper = new JLabel("SCOREKEEPER:");
		tfScoreKeeper = new JTextField();

		JButton btnSaveCurrentSettings = new JButton("SAVE CURRENT INPUT");
		JButton btnLoadSettings = new JButton("LOAD INPUT");
		JButton btnNext = new JButton("NEXT -->");
		JButton btnSBack = new JButton("<-- BACK TO SETUP");
		JButton btnPQ = new JButton("<-- PREVIOUS QUESTION");
		JButton btnTO = new JButton("TIMEOUT");
		JButton btnViewScoreboard = new JButton("VIEW SCOREBOARD");
		JButton btnNextQuestion = new JButton("NEXT QUESTION -->");
		JButton btnFinish = new JButton("END ROUND");

		JLabel lblQNumber = new JLabel("QUESTION: 1");
		JLabel lblTAScore = new JLabel("TEAM A SCORE:");
		lblTAScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblTAScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		JLabel lblTBScore = new JLabel("TEAM B SCORE:");
		lblTBScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblTBScore.setFont(new Font("Tahoma", Font.PLAIN, 16));

		RoundID round = new RoundID("", "", "", "", "");
		JButton buttonTNBack = new JButton("<-- BACK");
		buttonTNBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "mdp1");
				setJMenuBar(menuBar_setup);
			}
		});
		
		btnViewScoreboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen2 frame = new screen2();
				frame.setVisible(true);
			}
		});
		menuBar_setname.add(buttonTNBack);

		bonusConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "qP");
				bonusDone(thisTeamBP, thatTeamBP);
				System.out.println("---------");
				printArray(bonusArray);
				nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore, lblTBScore);
				getJMenuBar().setVisible(true);
				if (questionNum <= 10) {
					htTAScorelbl.setText(lblTAScore.getText());
					htTBScorelbl.setText(lblTBScore.getText());
				}
				thisTeamBP.setText("0");
				thatTeamBP.setText("0");
				/*
				 * JLabel[][] labels=createLabels(); for (int i=0;i<labels.length;i++){ for (int
				 * j=0;j<labels.length;j++) answerPanel.add(labels[i][j]); }
				 */
			}
		});

		btnAnsC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_score(10);
				update_score();
				playerAnswered(lblPlayName, btnPPA1.getText());
				// answeredNext();
				answeredBonus(thisTeamN, thatTeamN);
				// nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore,
				// lblTBScore);
			}
		});
		btnAnsI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_score(0.001);
				update_score();
				if (!answered) {
					answered = true;
				} else if (answered) {
					answered = false;
					nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore, lblTBScore);
				}
				answeredNext();
			}
		});
		btnAnsN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_score(-5);
				update_score();
				if (!answered) {
					answered = true;
				} else if (answered) {
					answered = false;
					nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore, lblTBScore);
				}
				answeredNext();
			}
		});
		btnAnsP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_score(15);
				update_score();
				playerAnswered(lblPlayName, btnPPA1.getText());
				// answeredNext();
				answeredBonus(thisTeamN, thatTeamN);
				// nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore,
				// lblTBScore);
			}
		});
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
				lblTAScore.setText(teamAName.toUpperCase() + ": " + scoresA);
				lblTBScore.setText(teamBName.toUpperCase() + ": " + scoresB);
				if (btnStartMatch.getText() == "START MATCH") {
					btnStartMatch.setText("RETURN TO MATCH");
				}
			}
		});
		menuBar_setname.add(btnStartMatch);

		btnSetTeamA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamAName = (String) JOptionPane.showInputDialog(contentPane, "ENTER TEAM A'S NAME HERE");
				btnTeamA.setText(teamAName);
				btnSetTeamA.setText(teamAName);
				lblTeamA.setText(teamAName);
			}
		});
		tAPTP.add(btnSetTeamA);

		btnAP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AP1name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnAP1.setText(AP1name);
				btnPPA1.setText(AP1name);
				htSubAbtn1.setText(AP1name);
				Players[0] = AP1name;
			}
		});
		tAPBP.add(btnAP1);

		btnAP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AP2name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnAP2.setText(AP2name);
				btnPPA2.setText(AP2name);
				htSubAbtn2.setText(AP2name);
				Players[1] = AP2name;
			}
		});
		tAPBP.add(btnAP2);

		btnAP3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AP3name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnAP3.setText(AP3name);
				btnPPA3.setText(AP3name);
				htSubAbtn3.setText(AP3name);
				Players[2] = AP3name;
			}
		});
		tAPBP.add(btnAP3);

		btnAP4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AP4name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnAP4.setText(AP4name);
				btnPPA4.setText(AP4name);
				htSubAbtn4.setText(AP4name);
				Players[3] = AP4name;
			}
		});
		tAPBP.add(btnAP4);

		JPanel tBPTP = new JPanel();
		teamBPanel.add(tBPTP);
		tBPTP.setLayout(new GridLayout(0, 1, 0, 0));

		btnSetTeamB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamBName = (String) JOptionPane.showInputDialog(contentPane, "ENTER TEAM B'S NAME");
				btnTeamB.setText(teamBName);
				btnSetTeamB.setText(teamBName);
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
				htSubBbtn1.setText(BP1name);
				Players[6] = BP1name;
			}
		});
		tBPBP.add(btnBP1);

		btnBP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String BP2name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnBP2.setText(BP2name);
				btnPPB2.setText(BP2name);
				htSubBbtn2.setText(BP2name);
				Players[7] = BP2name;
			}
		});
		tBPBP.add(btnBP2);

		btnBP3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String BP3name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnBP3.setText(BP3name);
				btnPPB3.setText(BP3name);
				htSubBbtn3.setText(BP3name);
				Players[8] = BP3name;
			}
		});
		tBPBP.add(btnBP3);

		btnBP4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String BP4name = (String) JOptionPane.showInputDialog(contentPane, "ENTER PLAYER'S NAME");
				btnBP4.setText(BP4name);
				btnPPB4.setText(BP4name);
				htSubBbtn4.setText(BP4name);
				Players[9] = BP4name;
			}
		});
		tBPBP.add(btnBP4);

		// setJMenuBar(menuBar_setname);

		setJMenuBar(menuBar_setup);
		// setJMenuBar(menuBar_scoreScreen);

		JPanel questionPanel = new JPanel(new GridLayout(3, 1, 0, 0));
		contentPane.add(questionPanel, "qP");

		JPanel qInfoPanel = new JPanel(new GridLayout(0, 3, 0, 0));
		questionPanel.add(qInfoPanel);

		qInfoPanel.add(lblTAScore);

		qInfoPanel.add(lblQNumber);
		lblQNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblQNumber.setFont(new Font("Tahoma", Font.PLAIN, 26));

		qInfoPanel.add(lblTBScore);

		JPanel tAPlayers = new JPanel(new GridLayout(0, 1, 0, 0));
		questionPanel.add(tAPlayers);

		lblTeamA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTeamA.setHorizontalAlignment(SwingConstants.CENTER);
		tAPlayers.add(lblTeamA);

		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Code to export to xls?
			}
		});

		JPanel tAPBtns = new JPanel(new GridLayout(0, 4, 0, 0));
		tAPlayers.add(tAPBtns);

		tAPBtns.add(btnPPA1);
		btnPPA1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNum = 0;
				playerAnswered(lblPlayName, btnPPA1.getText());
			}
		});

		tAPBtns.add(btnPPA2);
		btnPPA2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNum = 1;
				playerAnswered(lblPlayName, btnPPA2.getText());
			}
		});

		tAPBtns.add(btnPPA3);
		btnPPA3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNum = 2;
				playerAnswered(lblPlayName, btnPPA3.getText());
			}
		});

		tAPBtns.add(btnPPA4);
		btnPPA4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNum = 3;
				playerAnswered(lblPlayName, btnPPA4.getText());
			}
		});

		btnPPA5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNum = 4;
				playerAnswered(lblPlayName, btnPPA5.getText());
			}
		});

		btnPPA6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNum = 5;
				playerAnswered(lblPlayName, btnPPA6.getText());
			}
		});

		JPanel tBPlayers = new JPanel(new GridLayout(0, 1, 0, 0));
		questionPanel.add(tBPlayers);

		lblTeamB.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tBPlayers.add(lblTeamB);

		JPanel tBPBtns = new JPanel();
		tBPlayers.add(tBPBtns);
		tBPBtns.setLayout(new GridLayout(1, 0, 0, 0));

		tBPBtns.add(btnPPB1);
		btnPPB1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNum = 6;
				playerAnswered(lblPlayName, btnPPB1.getText());
			}
		});

		tBPBtns.add(btnPPB2);
		btnPPB2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNum = 7;
				playerAnswered(lblPlayName, btnPPB2.getText());
			}
		});

		tBPBtns.add(btnPPB3);
		btnPPB3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNum = 8;
				playerAnswered(lblPlayName, btnPPB3.getText());
			}
		});

		tBPBtns.add(btnPPB4);
		btnPPB4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNum = 9;
				playerAnswered(lblPlayName, btnPPB4.getText());
			}
		});

		btnPPB5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNum = 10;
				playerAnswered(lblPlayName, btnPPB5.getText());
			}
		});

		btnPPB6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNum = 11;
				playerAnswered(lblPlayName, btnPPB6.getText());
			}
		});

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

		btnLoadSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfRoom.setText(round.rroom);
				tfPacket.setText(round.rpacket);
				tfRound.setText(round.rrnumber);
				tfModerator.setText(round.rmod);
				tfScoreKeeper.setText(round.rskeep);
			}
		});

		htCont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "qP");
				getJMenuBar().setVisible(true);
				if (!timeout) {
					questionNum += 1;
					lblQNumber.setText("QUESTION: " + (Integer.toString(questionNum + 1)));
				} else if (timeout) {
					timeout = false;
					htlbl.setText("HALFTIME");
				}
			}
		});
		menuBar_setup.add(btnLoadSettings);

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "tAP");
				setJMenuBar(menuBar_setname);
				htTAScorelbl.setText(lblTAScore.getText());
				htTBScorelbl.setText(lblTBScore.getText());
			}
		});
		menuBar_setup.add(btnNext);
		// setJMenuBar(menuBar_scoreScreen);

		btnSBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "tAP");
				setJMenuBar(menuBar_setname);
			}
		});
		menuBar_scoreScreen.add(btnSBack);
		menuBar_scoreScreen.add(btnTO);
		btnPQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (questionNum > 0) {
					questionNum -= 1;
					lblQNumber.setText("QUESTION: " + (Integer.toString(questionNum + 1)));
					update_score();
					lblTAScore.setText(teamAName.toUpperCase() + ": " + scoresA);
					lblTBScore.setText(teamBName.toUpperCase() + ": " + scoresB);
				} else {
					questionNum = 0;
					lblQNumber.setText("QUESTION: " + (Integer.toString(questionNum + 1)));
				}

			}
		});
		btnTO.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				timeout = true;
				htlbl.setText("TIMEOUT");
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "htp1");
				menuBar_scoreScreen.hide();
			}
		});
		menuBar_scoreScreen.add(btnPQ);

		menuBar_scoreScreen.add(btnViewScoreboard);

		btnNextQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore, lblTBScore);
				update_score();
				lblTAScore.setText(teamAName.toUpperCase() + ": " + scoresA);
				lblTBScore.setText(teamBName.toUpperCase() + ": " + scoresB);
				if (!timeout) {
					htlbl.setText("HALFTIME");
				}
				answered = false;
			}
		});
		menuBar_scoreScreen.add(btnNextQuestion);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		matchDetailsPanel.add(lblRoom);

		matchDetailsPanel.add(tfRoom);
		tfRoom.setColumns(10);

		matchDetailsPanel.add(lblModerator);

		matchDetailsPanel.add(tfModerator);
		tfModerator.setColumns(10);

		matchDetailsPanel.add(lblRound);

		matchDetailsPanel.add(tfRound);
		tfRound.setColumns(10);

		matchDetailsPanel.add(lblPacket);

		matchDetailsPanel.add(tfPacket);
		tfPacket.setColumns(10);

		matchDetailsPanel.add(lblScorekeeper);

		matchDetailsPanel.add(tfScoreKeeper);
		tfScoreKeeper.setColumns(10);

		htSubAbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Ap5 || !Ap6) {
					int value = (Integer) JOptionPane.showConfirmDialog(contentPane,
							"If you are adding Player 5 or Player 6, select yes, if not, select no.", "Question",
							JOptionPane.YES_NO_OPTION);
					if (value == JOptionPane.YES_OPTION) {
						String APname = (String) JOptionPane.showInputDialog(contentPane, "ENTER SUB'S NAME");
						if (APname != null) {
							if (!Ap5) {
								htSubAbtn5.setText(APname);
								htTeamAS.remove(htSubAbtn1);
								htTeamAS.add(htSubAbtn5);
								htTeamAS.validate();
								tAPBtns.remove(btnPPA1);
								btnPPA5.setText(APname);
								tAPBtns.add(btnPPA5);
								Players[4] = APname;
								Ap5 = true;
								tAPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][0] = 0.01;
									scores[i+1][4] = 0;
								}
							} else {
								htSubAbtn6.setText(APname);
								htTeamAS.remove(htSubAbtn1);
								htTeamAS.add(htSubAbtn6);
								htTeamAS.validate();
								tAPBtns.remove(btnPPA1);
								btnPPA6.setText(APname);
								tAPBtns.add(btnPPA6);
								Players[5] = APname;
								Ap6 = true;
								tAPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][0] = 0.01;
									scores[i+1][5] = 0;
								}
							}
						}
					} else if (value == JOptionPane.NO_OPTION) {
						Object[] possibilities = { btnPPA1.getText(), btnPPA2.getText(), btnPPA3.getText(),
								btnPPA4.getText(), btnPPA5.getText() };
						String APName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
								"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPA1.getText());
						replace_sub(htSubAbtn1, htSubAbtn1, htSubAbtn2, htSubAbtn3, htSubAbtn4, htSubAbtn5, htSubAbtn5,
								APName, htTeamAS, tAPBtns, btnPPA1, btnPPA1, btnPPA2, btnPPA3, btnPPA4, btnPPA5,
								btnPPA5);
						if (APName == btnPPA1.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][0] = 0.01;
								scores[i+1][0] = 0;
							}
						} else if (APName == btnPPA2.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][0] = 0.01;
								scores[i+1][1] = 0;
							}
						} else if (APName == btnPPA3.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][0] = 0.01;
								scores[i+1][2] = 0;
							}
						} else if (APName == btnPPA4.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][0] = 0.01;
								scores[i+1][3] = 0;
							}
						} else if (APName == btnPPA5.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][0] = 0.01;
								scores[i+1][4] = 0;
							}
						} else if (APName == btnPPA6.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][0] = 0.01;
								scores[i+1][5] = 0;
							}
						}
					}
				} else {
					Object[] possibilities = { btnPPA1.getText(), btnPPA2.getText(), btnPPA3.getText(),
							btnPPA4.getText(), btnPPA5.getText(), btnPPA6.getText() };
					String APName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
							"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPA1.getText());
					replace_sub(htSubAbtn1, htSubAbtn1, htSubAbtn2, htSubAbtn3, htSubAbtn4, htSubAbtn5, htSubAbtn6,
							APName, htTeamAS, tAPBtns, btnPPA1, btnPPA1, btnPPA2, btnPPA3, btnPPA4, btnPPA5, btnPPA6);
					if (APName == btnPPA1.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][0] = 0.01;
							scores[i+1][0] = 0;
						}
					} else if (APName == btnPPA2.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][0] = 0.01;
							scores[i+1][1] = 0;
						}
					} else if (APName == btnPPA3.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][0] = 0.01;
							scores[i+1][2] = 0;
						}
					} else if (APName == btnPPA4.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][0] = 0.01;
							scores[i+1][3] = 0;
						}
					} else if (APName == btnPPA5.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][0] = 0.01;
							scores[i+1][4] = 0;
						}
					} else if (APName == btnPPA6.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][0] = 0.01;
							scores[i+1][5] = 0;
						}
					}
				}

			}
		});

		htSubAbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Ap5 || !Ap6) {
					int value = (Integer) JOptionPane.showConfirmDialog(contentPane,
							"If you are adding Player 5 or Player 6, select yes, if not, select no.", "Question",
							JOptionPane.YES_NO_OPTION);
					if (value == JOptionPane.YES_OPTION) {
						String APname = (String) JOptionPane.showInputDialog(contentPane, "ENTER SUB'S NAME");
						if (APname != null) {
							if (!Ap5) {
								htSubAbtn5.setText(APname);
								htTeamAS.remove(htSubAbtn2);
								htTeamAS.add(htSubAbtn5);
								htTeamAS.validate();
								tAPBtns.remove(btnPPA2);
								btnPPA5.setText(APname);
								tAPBtns.add(btnPPA5);
								Players[4] = APname;
								Ap5 = true;
								tAPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][1] = 0.01;
									scores[i+1][4] = 0;
								}
							} else {
								htSubAbtn6.setText(APname);
								htTeamAS.remove(htSubAbtn2);
								htTeamAS.add(htSubAbtn6);
								htTeamAS.validate();
								tAPBtns.remove(btnPPA2);
								btnPPA6.setText(APname);
								tAPBtns.add(btnPPA6);
								Players[5] = APname;
								Ap6 = true;
								tAPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][1] = 0.01;
									scores[i+1][5] = 0;
								}
							}
						}
					} else if (value == JOptionPane.NO_OPTION) {
						Object[] possibilities = { btnPPA1.getText(), btnPPA2.getText(), btnPPA3.getText(),
								btnPPA4.getText(), btnPPA5.getText() };
						String APName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
								"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPA1.getText());
						replace_sub(htSubAbtn2, htSubAbtn1, htSubAbtn2, htSubAbtn3, htSubAbtn4, htSubAbtn5, htSubAbtn5,
								APName, htTeamAS, tAPBtns, btnPPA2, btnPPA1, btnPPA2, btnPPA3, btnPPA4, btnPPA5,
								btnPPA5);
						if (APName == btnPPA1.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][1] = 0.01;
								scores[i+1][0] = 0;
							}
						} else if (APName == btnPPA2.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][1] = 0.01;
								scores[i+1][1] = 0;
							}
						} else if (APName == btnPPA3.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][1] = 0.01;
								scores[i+1][2] = 0;
							}
						} else if (APName == btnPPA4.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][1] = 0.01;
								scores[i+1][3] = 0;
							}
						} else if (APName == btnPPA5.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][1] = 0.01;
								scores[i+1][4] = 0;
							}
						} else if (APName == btnPPA6.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][1] = 0.01;
								scores[i+1][5] = 0;
							}
						}
					}
				} else {
					Object[] possibilities = { btnPPA1.getText(), btnPPA2.getText(), btnPPA3.getText(),
							btnPPA4.getText(), btnPPA5.getText(), btnPPA6.getText() };
					String APName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
							"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPA1.getText());
					replace_sub(htSubAbtn2, htSubAbtn1, htSubAbtn2, htSubAbtn3, htSubAbtn4, htSubAbtn5, htSubAbtn6,
							APName, htTeamAS, tAPBtns, btnPPA2, btnPPA1, btnPPA2, btnPPA3, btnPPA4, btnPPA5, btnPPA6);
					if (APName == btnPPA1.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][1] = 0.01;
							scores[i+1][0] = 0;
						}
					} else if (APName == btnPPA2.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][1] = 0.01;
							scores[i+1][1] = 0;
						}
					} else if (APName == btnPPA3.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][1] = 0.01;
							scores[i+1][2] = 0;
						}
					} else if (APName == btnPPA4.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][1] = 0.01;
							scores[i+1][3] = 0;
						}
					} else if (APName == btnPPA5.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][1] = 0.01;
							scores[i+1][4] = 0;
						}
					} else if (APName == btnPPA6.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][1] = 0.01;
							scores[i+1][5] = 0;
						}
					}
				}

			}
		});

		htSubAbtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Ap5 || !Ap6) {
					int value = (Integer) JOptionPane.showConfirmDialog(contentPane,
							"If you are adding Player 5 or Player 6, select yes, if not, select no.", "Question",
							JOptionPane.YES_NO_OPTION);
					if (value == JOptionPane.YES_OPTION) {
						String APname = (String) JOptionPane.showInputDialog(contentPane, "ENTER SUB'S NAME");
						if (APname != null) {
							if (!Ap5) {
								htSubAbtn5.setText(APname);
								htTeamAS.remove(htSubAbtn3);
								htTeamAS.add(htSubAbtn5);
								htTeamAS.validate();
								tAPBtns.remove(btnPPA3);
								btnPPA5.setText(APname);
								tAPBtns.add(btnPPA5);
								Players[4] = APname;
								Ap5 = true;
								tAPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][2] = 0.01;
									scores[i+1][4] = 0;
								}
							} else {
								htSubAbtn6.setText(APname);
								htTeamAS.remove(htSubAbtn3);
								htTeamAS.add(htSubAbtn6);
								htTeamAS.validate();
								tAPBtns.remove(btnPPA3);
								btnPPA6.setText(APname);
								tAPBtns.add(btnPPA6);
								Players[5] = APname;
								Ap6 = true;
								tAPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][2] = 0.01;
									scores[i+1][5] = 0;
								}
							}
						}
					} else if (value == JOptionPane.NO_OPTION) {
						Object[] possibilities = { btnPPA1.getText(), btnPPA2.getText(), btnPPA3.getText(),
								btnPPA4.getText(), btnPPA5.getText() };
						String APName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
								"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPA1.getText());
						replace_sub(htSubAbtn3, htSubAbtn1, htSubAbtn2, htSubAbtn3, htSubAbtn4, htSubAbtn5, htSubAbtn5,
								APName, htTeamAS, tAPBtns, btnPPA3, btnPPA1, btnPPA2, btnPPA3, btnPPA4, btnPPA5,
								btnPPA5);
						if (APName == btnPPA1.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][2] = 0.01;
								scores[i+1][0] = 0;
							}
						} else if (APName == btnPPA2.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][2] = 0.01;
								scores[i+1][1] = 0;
							}
						} else if (APName == btnPPA3.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][2] = 0.01;
								scores[i+1][2] = 0;
							}
						} else if (APName == btnPPA4.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][2] = 0.01;
								scores[i+1][3] = 0;
							}
						} else if (APName == btnPPA5.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][2] = 0.01;
								scores[i+1][4] = 0;
							}
						} else if (APName == btnPPA6.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][2] = 0.01;
								scores[i+1][5] = 0;
							}
						}
					}
				} else {
					Object[] possibilities = { btnPPA1.getText(), btnPPA2.getText(), btnPPA3.getText(),
							btnPPA4.getText(), btnPPA5.getText(), btnPPA6.getText() };
					String APName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
							"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPA1.getText());
					replace_sub(htSubAbtn3, htSubAbtn1, htSubAbtn2, htSubAbtn3, htSubAbtn4, htSubAbtn5, htSubAbtn6,
							APName, htTeamAS, tAPBtns, btnPPA3, btnPPA1, btnPPA2, btnPPA3, btnPPA4, btnPPA5, btnPPA6);
					if (APName == btnPPA1.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][2] = 0.01;
							scores[i+1][0] = 0;
						}
					} else if (APName == btnPPA2.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][2] = 0.01;
							scores[i+1][1] = 0;
						}
					} else if (APName == btnPPA3.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][2] = 0.01;
							scores[i+1][2] = 0;
						}
					} else if (APName == btnPPA4.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][2] = 0.01;
							scores[i+1][3] = 0;
						}
					} else if (APName == btnPPA5.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][2] = 0.01;
							scores[i+1][4] = 0;
						}
					} else if (APName == btnPPA6.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][2] = 0.01;
							scores[i+1][5] = 0;
						}
					}
				}

			}
		});

		htSubAbtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Ap5 || !Ap6) {
					int value = (Integer) JOptionPane.showConfirmDialog(contentPane,
							"If you are adding Player 5 or Player 6, select yes, if not, select no.", "Question",
							JOptionPane.YES_NO_OPTION);
					if (value == JOptionPane.YES_OPTION) {
						String APname = (String) JOptionPane.showInputDialog(contentPane, "ENTER SUB'S NAME");
						if (APname != null) {
							if (!Ap5) {
								htSubAbtn5.setText(APname);
								htTeamAS.remove(htSubAbtn4);
								htTeamAS.add(htSubAbtn5);
								htTeamAS.validate();
								tAPBtns.remove(btnPPA4);
								btnPPA5.setText(APname);
								tAPBtns.add(btnPPA5);
								Players[4] = APname;
								Ap5 = true;
								tAPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][3] = 0.01;
									scores[i+1][4] = 0;
								}
							} else {
								htSubAbtn6.setText(APname);
								htTeamAS.remove(htSubAbtn4);
								htTeamAS.add(htSubAbtn6);
								htTeamAS.validate();
								tAPBtns.remove(btnPPA4);
								btnPPA6.setText(APname);
								tAPBtns.add(btnPPA6);
								Players[5] = APname;
								Ap6 = true;
								tAPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][3] = 0.01;
									scores[i+1][5] = 0;
								}
							}
						}
					} else if (value == JOptionPane.NO_OPTION) {
						Object[] possibilities = { btnPPA1.getText(), btnPPA2.getText(), btnPPA3.getText(),
								btnPPA4.getText(), btnPPA5.getText() };
						String APName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
								"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPA1.getText());
						replace_sub(htSubAbtn4, htSubAbtn1, htSubAbtn2, htSubAbtn3, htSubAbtn4, htSubAbtn5, htSubAbtn5,
								APName, htTeamAS, tAPBtns, btnPPA4, btnPPA1, btnPPA2, btnPPA3, btnPPA4, btnPPA5,
								btnPPA5);
						if (APName == btnPPA1.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][3] = 0.01;
								scores[i+1][0] = 0;
							}
						} else if (APName == btnPPA2.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][3] = 0.01;
								scores[i+1][1] = 0;
							}
						} else if (APName == btnPPA3.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][3] = 0.01;
								scores[i+1][2] = 0;
							}
						} else if (APName == btnPPA4.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][3] = 0.01;
								scores[i+1][3] = 0;
							}
						} else if (APName == btnPPA5.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][3] = 0.01;
								scores[i+1][4] = 0;
							}
						} else if (APName == btnPPA6.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][3] = 0.01;
								scores[i+1][5] = 0;
							}
						}
					}
				} else {
					Object[] possibilities = { btnPPA1.getText(), btnPPA2.getText(), btnPPA3.getText(),
							btnPPA4.getText(), btnPPA5.getText(), btnPPA6.getText() };
					String APName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
							"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPA1.getText());
					replace_sub(htSubAbtn4, htSubAbtn1, htSubAbtn2, htSubAbtn3, htSubAbtn4, htSubAbtn5, htSubAbtn6,
							APName, htTeamAS, tAPBtns, btnPPA4, btnPPA1, btnPPA2, btnPPA3, btnPPA4, btnPPA5, btnPPA6);
					if (APName == btnPPA1.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][3] = 0.01;
							scores[i+1][0] = 0;
						}
					} else if (APName == btnPPA2.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][3] = 0.01;
							scores[i+1][1] = 0;
						}
					} else if (APName == btnPPA3.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][3] = 0.01;
							scores[i+1][2] = 0;
						}
					} else if (APName == btnPPA4.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][3] = 0.01;
							scores[i+1][3] = 0;
						}
					} else if (APName == btnPPA5.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][3] = 0.01;
							scores[i+1][4] = 0;
						}
					} else if (APName == btnPPA6.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][3] = 0.01;
							scores[i+1][5] = 0;
						}
					}
				}

			}
		});

		htSubAbtn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Ap6) {
					int value = (Integer) JOptionPane.showConfirmDialog(contentPane,
							"If you are adding Player 6, select yes, if not, select no.", "Question",
							JOptionPane.YES_NO_OPTION);
					if (value == JOptionPane.YES_OPTION) {
						String APname = (String) JOptionPane.showInputDialog(contentPane, "ENTER SUB'S NAME");
						if (APname != null) {
							htSubAbtn6.setText(APname);
							htTeamAS.remove(htSubAbtn5);
							htTeamAS.add(htSubAbtn6);
							htTeamAS.validate();
							tAPBtns.remove(btnPPA5);
							btnPPA6.setText(APname);
							tAPBtns.add(btnPPA6);
							Players[5] = APname;
							Ap6 = true;
							tAPBtns.validate();
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][4] = 0.01;
								scores[i+1][5] = 0;
							}
						}
					} else if (value == JOptionPane.NO_OPTION) {
						Object[] possibilities = { btnPPA1.getText(), btnPPA2.getText(), btnPPA3.getText(),
								btnPPA4.getText(), btnPPA5.getText() };
						String APName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
								"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPA1.getText());
						replace_sub(htSubAbtn5, htSubAbtn1, htSubAbtn2, htSubAbtn3, htSubAbtn4, htSubAbtn5, htSubAbtn5,
								APName, htTeamAS, tAPBtns, btnPPA5, btnPPA1, btnPPA2, btnPPA3, btnPPA4, btnPPA5,
								btnPPA6);
						if (APName == btnPPA1.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][4] = 0.01;
								scores[i+1][0] = 0;
							}
						} else if (APName == btnPPA2.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][4] = 0.01;
								scores[i+1][1] = 0;
							}
						} else if (APName == btnPPA3.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][4] = 0.01;
								scores[i+1][2] = 0;
							}
						} else if (APName == btnPPA4.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][4] = 0.01;
								scores[i+1][3] = 0;
							}
						} else if (APName == btnPPA5.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][4] = 0.01;
								scores[i+1][4] = 0;
							}
						} else if (APName == btnPPA6.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][4] = 0.01;
								scores[i+1][5] = 0;
							}
						}
					}
				} else {
					Object[] possibilities = { btnPPA1.getText(), btnPPA2.getText(), btnPPA3.getText(),
							btnPPA4.getText(), btnPPA5.getText(), btnPPA6.getText() };
					String APName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
							"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPA1.getText());
					replace_sub(htSubAbtn5, htSubAbtn1, htSubAbtn2, htSubAbtn3, htSubAbtn4, htSubAbtn5, htSubAbtn6,
							APName, htTeamAS, tAPBtns, btnPPA5, btnPPA1, btnPPA2, btnPPA3, btnPPA4, btnPPA5, btnPPA6);
					if (APName == btnPPA1.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][4] = 0.01;
							scores[i+1][0] = 0;
						}
					} else if (APName == btnPPA2.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][4] = 0.01;
							scores[i+1][1] = 0;
						}
					} else if (APName == btnPPA3.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][4] = 0.01;
							scores[i+1][2] = 0;
						}
					} else if (APName == btnPPA4.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][4] = 0.01;
							scores[i+1][3] = 0;
						}
					} else if (APName == btnPPA5.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][4] = 0.01;
							scores[i+1][4] = 0;
						}
					} else if (APName == btnPPA6.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][4] = 0.01;
							scores[i+1][5] = 0;
						}
					}
				}

			}
		});

		htSubAbtn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] possibilities = { btnPPA1.getText(), btnPPA2.getText(), btnPPA3.getText(), btnPPA4.getText(),
						btnPPA5.getText(), btnPPA6.getText() };
				String APName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?", "SUBSTITUTION",
						JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPA1.getText());
				replace_sub(htSubAbtn6, htSubAbtn1, htSubAbtn2, htSubAbtn3, htSubAbtn4, htSubAbtn5, htSubAbtn6, APName,
						htTeamAS, tAPBtns, btnPPA6, btnPPA1, btnPPA2, btnPPA3, btnPPA4, btnPPA5, btnPPA6);
				if (APName == btnPPA1.getText()) {
					for (int i = questionNum; i < 19; i++) {
						scores[i+1][5] = 0.01;
						scores[i+1][0] = 0;
					}
				} else if (APName == btnPPA2.getText()) {
					for (int i = questionNum; i < 19; i++) {
						scores[i+1][5] = 0.01;
						scores[i+1][1] = 0;
					}
				} else if (APName == btnPPA3.getText()) {
					for (int i = questionNum; i < 19; i++) {
						scores[i+1][5] = 0.01;
						scores[i+1][2] = 0;
					}
				} else if (APName == btnPPA4.getText()) {
					for (int i = questionNum; i < 19; i++) {
						scores[i+1][5] = 0.01;
						scores[i+1][3] = 0;
					}
				} else if (APName == btnPPA5.getText()) {
					for (int i = questionNum; i < 19; i++) {
						scores[i+1][5] = 0.01;
						scores[i+1][4] = 0;
					}
				} else if (APName == btnPPA6.getText()) {
					for (int i = questionNum; i < 19; i++) {
						scores[i+1][5] = 0.01;
						scores[i+1][5] = 0;
					}
				}
			}
		});

		htSubBbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Bp5 || !Bp6) {
					int value = (Integer) JOptionPane.showConfirmDialog(contentPane,
							"If you are adding Player 5 or Player 6, select yes, if not, select no.", "Question",
							JOptionPane.YES_NO_OPTION);
					if (value == JOptionPane.YES_OPTION) {
						String BPname = (String) JOptionPane.showInputDialog(contentPane, "ENTER SUB'S NAME");
						if (BPname != null) {
							if (!Bp5) {
								htSubBbtn5.setText(BPname);
								htTeamBS.remove(htSubBbtn1);
								htTeamBS.add(htSubBbtn5);
								htTeamBS.validate();
								tBPBtns.remove(btnPPB1);
								btnPPB5.setText(BPname);
								tBPBtns.add(btnPPB5);
								Players[10] = BPname;
								Bp5 = true;
								tBPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][6] = 0.01;
									scores[i+1][10] = 0;
								}
							} else {
								htSubBbtn6.setText(BPname);
								htTeamBS.remove(htSubBbtn1);
								htTeamBS.add(htSubBbtn6);
								htTeamBS.validate();
								tBPBtns.remove(btnPPB1);
								btnPPB6.setText(BPname);
								tBPBtns.add(btnPPB6);
								Players[11] = BPname;
								Bp6 = true;
								tBPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][6] = 0.01;
									scores[i+1][11] = 0;
								}
							}
						}
					} else if (value == JOptionPane.NO_OPTION) {
						Object[] possibilities = { btnPPB1.getText(), btnPPB2.getText(), btnPPB3.getText(),
								btnPPB4.getText(), btnPPB5.getText() };
						String BPName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
								"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPB1.getText());
						replace_sub(htSubBbtn1, htSubBbtn1, htSubBbtn2, htSubBbtn3, htSubBbtn4, htSubBbtn5, htSubBbtn5,
								BPName, htTeamBS, tBPBtns, btnPPB1, btnPPB1, btnPPB2, btnPPB3, btnPPB4, btnPPB5,
								btnPPB5);
						if (BPName == btnPPB1.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][6] = 0.01;
								scores[i+1][6] = 0;
							}
						} else if (BPName == btnPPB2.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][6] = 0.01;
								scores[i+1][7] = 0;
							}
						} else if (BPName == btnPPB3.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][6] = 0.01;
								scores[i+1][8] = 0;
							}
						} else if (BPName == btnPPB4.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][6] = 0.01;
								scores[i+1][9] = 0;
							}
						} else if (BPName == btnPPB5.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][6] = 0.01;
								scores[i+1][10] = 0;
							}
						} else if (BPName == btnPPB6.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][6] = 0.01;
								scores[i+1][11] = 0;
							}
						}
					}
				} else {
					Object[] possibilities = { btnPPB1.getText(), btnPPB2.getText(), btnPPB3.getText(),
							btnPPB4.getText(), btnPPB5.getText(), btnPPB6.getText() };
					String BPName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
							"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPB1.getText());
					replace_sub(htSubBbtn1, htSubBbtn1, htSubBbtn2, htSubBbtn3, htSubBbtn4, htSubBbtn5, htSubBbtn6,
							BPName, htTeamBS, tBPBtns, btnPPB1, btnPPB1, btnPPB2, btnPPB3, btnPPB4, btnPPB5, btnPPB6);
					if (BPName == btnPPB1.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][6] = 0.01;
							scores[i+1][6] = 0;
						}
					} else if (BPName == btnPPB2.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][6] = 0.01;
							scores[i+1][7] = 0;
						}
					} else if (BPName == btnPPB3.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][6] = 0.01;
							scores[i+1][8] = 0;
						}
					} else if (BPName == btnPPB4.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][6] = 0.01;
							scores[i+1][9] = 0;
						}
					} else if (BPName == btnPPB5.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][6] = 0.01;
							scores[i+1][10] = 0;
						}
					} else if (BPName == btnPPB6.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][6] = 0.01;
							scores[i+1][11] = 0;
						}
					}
				}

			}
		});

		htSubBbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Bp5 || !Bp6) {
					int value = (Integer) JOptionPane.showConfirmDialog(contentPane,
							"If you are adding Player 5 or Player 6, select yes, if not, select no.", "Question",
							JOptionPane.YES_NO_OPTION);
					if (value == JOptionPane.YES_OPTION) {
						String BPname = (String) JOptionPane.showInputDialog(contentPane, "ENTER SUB'S NAME");
						if (BPname != null) {
							if (!Bp5) {
								htSubBbtn5.setText(BPname);
								htTeamBS.remove(htSubBbtn2);
								htTeamBS.add(htSubBbtn5);
								htTeamBS.validate();
								tBPBtns.remove(btnPPB2);
								btnPPB5.setText(BPname);
								tBPBtns.add(btnPPB5);
								Players[10] = BPname;
								Bp5 = true;
								tBPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][7] = 0.01;
									scores[i+1][10] = 0;
								}
							} else {
								htSubBbtn6.setText(BPname);
								htTeamBS.remove(htSubBbtn2);
								htTeamBS.add(htSubBbtn6);
								htTeamBS.validate();
								tBPBtns.remove(btnPPB2);
								btnPPB6.setText(BPname);
								tBPBtns.add(btnPPB6);
								Players[11] = BPname;
								Bp6 = true;
								tBPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][7] = 0.01;
									scores[i+1][11] = 0;
								}
							}
						}
					} else if (value == JOptionPane.NO_OPTION) {
						Object[] possibilities = { btnPPB1.getText(), btnPPB2.getText(), btnPPB3.getText(),
								btnPPB4.getText(), btnPPB5.getText() };
						String BPName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
								"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPB1.getText());
						replace_sub(htSubBbtn2, htSubBbtn1, htSubBbtn2, htSubBbtn3, htSubBbtn4, htSubBbtn5, htSubBbtn5,
								BPName, htTeamBS, tBPBtns, btnPPB2, btnPPB1, btnPPB2, btnPPB3, btnPPB4, btnPPB5,
								btnPPB5);
						if (BPName == btnPPB1.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][7] = 0.01;
								scores[i+1][6] = 0;
							}
						} else if (BPName == btnPPB2.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][7] = 0.01;
								scores[i+1][7] = 0;
							}
						} else if (BPName == btnPPB3.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][7] = 0.01;
								scores[i+1][8] = 0;
							}
						} else if (BPName == btnPPB4.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][7] = 0.01;
								scores[i+1][9] = 0;
							}
						} else if (BPName == btnPPB5.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][7] = 0.01;
								scores[i+1][10] = 0;
							}
						} else if (BPName == btnPPB6.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][7] = 0.01;
								scores[i+1][11] = 0;
							}
						}
					}
				} else {
					Object[] possibilities = { btnPPB1.getText(), btnPPB2.getText(), btnPPB3.getText(),
							btnPPB4.getText(), btnPPB5.getText(), btnPPB6.getText() };
					String BPName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
							"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPB1.getText());
					replace_sub(htSubBbtn2, htSubBbtn1, htSubBbtn2, htSubBbtn3, htSubBbtn4, htSubBbtn5, htSubBbtn6,
							BPName, htTeamBS, tBPBtns, btnPPB2, btnPPB1, btnPPB2, btnPPB3, btnPPB4, btnPPB5, btnPPB6);
					if (BPName == btnPPB1.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][7] = 0.01;
							scores[i+1][6] = 0;
						}
					} else if (BPName == btnPPB2.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][7] = 0.01;
							scores[i+1][7] = 0;
						}
					} else if (BPName == btnPPB3.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][7] = 0.01;
							scores[i+1][8] = 0;
						}
					} else if (BPName == btnPPB4.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][7] = 0.01;
							scores[i+1][9] = 0;
						}
					} else if (BPName == btnPPB5.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][7] = 0.01;
							scores[i+1][10] = 0;
						}
					} else if (BPName == btnPPB6.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][7] = 0.01;
							scores[i+1][11] = 0;
						}
					}
				}

			}
		});

		htSubBbtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Bp5 || !Bp6) {
					int value = (Integer) JOptionPane.showConfirmDialog(contentPane,
							"If you are adding Player 5 or Player 6, select yes, if not, select no.", "Question",
							JOptionPane.YES_NO_OPTION);
					if (value == JOptionPane.YES_OPTION) {
						String BPname = (String) JOptionPane.showInputDialog(contentPane, "ENTER SUB'S NAME");
						if (BPname != null) {
							if (!Bp5) {
								htSubBbtn5.setText(BPname);
								htTeamBS.remove(htSubBbtn3);
								htTeamBS.add(htSubBbtn5);
								htTeamBS.validate();
								tBPBtns.remove(btnPPB3);
								btnPPB5.setText(BPname);
								tBPBtns.add(btnPPB5);
								Players[10] = BPname;
								Bp5 = true;
								tBPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][8] = 0.01;
									scores[i+1][10] = 0;
								}
							} else {
								htSubBbtn6.setText(BPname);
								htTeamBS.remove(htSubBbtn3);
								htTeamBS.add(htSubBbtn6);
								htTeamBS.validate();
								tBPBtns.remove(btnPPB3);
								btnPPB6.setText(BPname);
								tBPBtns.add(btnPPB6);
								Players[11] = BPname;
								Bp6 = true;
								tBPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][8] = 0.01;
									scores[i+1][11] = 0;
								}
							}
						}
					} else if (value == JOptionPane.NO_OPTION) {
						Object[] possibilities = { btnPPB1.getText(), btnPPB2.getText(), btnPPB3.getText(),
								btnPPB4.getText(), btnPPB5.getText() };
						String BPName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
								"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPB1.getText());
						replace_sub(htSubBbtn3, htSubBbtn1, htSubBbtn2, htSubBbtn3, htSubBbtn4, htSubBbtn5, htSubBbtn5,
								BPName, htTeamBS, tBPBtns, btnPPB3, btnPPB1, btnPPB2, btnPPB3, btnPPB4, btnPPB5,
								btnPPB5);
						if (BPName == btnPPB1.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][8] = 0.01;
								scores[i+1][6] = 0;
							}
						} else if (BPName == btnPPB2.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][8] = 0.01;
								scores[i+1][7] = 0;
							}
						} else if (BPName == btnPPB3.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][8] = 0.01;
								scores[i+1][8] = 0;
							}
						} else if (BPName == btnPPB4.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][8] = 0.01;
								scores[i+1][9] = 0;
							}
						} else if (BPName == btnPPB5.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][8] = 0.01;
								scores[i+1][10] = 0;
							}
						} else if (BPName == btnPPB6.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][8] = 0.01;
								scores[i+1][11] = 0;
							}
						}
					}
				} else {
					Object[] possibilities = { btnPPB1.getText(), btnPPB2.getText(), btnPPB3.getText(),
							btnPPB4.getText(), btnPPB5.getText(), btnPPB6.getText() };
					String BPName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
							"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPB1.getText());
					replace_sub(htSubBbtn3, htSubBbtn1, htSubBbtn2, htSubBbtn3, htSubBbtn4, htSubBbtn5, htSubBbtn6,
							BPName, htTeamBS, tBPBtns, btnPPB3, btnPPB1, btnPPB2, btnPPB3, btnPPB4, btnPPB5, btnPPB6);
					if (BPName == btnPPB1.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][8] = 0.01;
							scores[i+1][6] = 0;
						}
					} else if (BPName == btnPPB2.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][8] = 0.01;
							scores[i+1][7] = 0;
						}
					} else if (BPName == btnPPB3.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][8] = 0.01;
							scores[i+1][8] = 0;
						}
					} else if (BPName == btnPPB4.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][8] = 0.01;
							scores[i+1][9] = 0;
						}
					} else if (BPName == btnPPB5.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][8] = 0.01;
							scores[i+1][10] = 0;
						}
					} else if (BPName == btnPPB6.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][8] = 0.01;
							scores[i+1][11] = 0;
						}
					}
				}

			}
		});

		htSubBbtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Bp5 || !Bp6) {
					int value = (Integer) JOptionPane.showConfirmDialog(contentPane,
							"If you are adding Player 5 or Player 6, select yes, if not, select no.", "Question",
							JOptionPane.YES_NO_OPTION);
					if (value == JOptionPane.YES_OPTION) {
						String BPname = (String) JOptionPane.showInputDialog(contentPane, "ENTER SUB'S NAME");
						if (BPname != null) {
							if (!Bp5) {
								htSubBbtn5.setText(BPname);
								htTeamBS.remove(htSubBbtn4);
								htTeamBS.add(htSubBbtn5);
								htTeamBS.validate();
								tBPBtns.remove(btnPPB4);
								btnPPB5.setText(BPname);
								tBPBtns.add(btnPPB5);
								Players[10] = BPname;
								Bp5 = true;
								tBPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][9] = 0.01;
									scores[i+1][10] = 0;
								}
							} else {
								htSubBbtn6.setText(BPname);
								htTeamBS.remove(htSubBbtn4);
								htTeamBS.add(htSubBbtn6);
								htTeamBS.validate();
								tBPBtns.remove(btnPPB4);
								btnPPB6.setText(BPname);
								tBPBtns.add(btnPPB6);
								Players[11] = BPname;
								Bp6 = true;
								tBPBtns.validate();
								for (int i = questionNum; i < 19; i++) {
									scores[i+1][9] = 0.01;
									scores[i+1][11] = 0;
								}
							}
						}
					} else if (value == JOptionPane.NO_OPTION) {
						Object[] possibilities = { btnPPB1.getText(), btnPPB2.getText(), btnPPB3.getText(),
								btnPPB4.getText(), btnPPB5.getText() };
						String BPName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
								"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPB1.getText());
						replace_sub(htSubBbtn4, htSubBbtn1, htSubBbtn2, htSubBbtn3, htSubBbtn4, htSubBbtn5, htSubBbtn5,
								BPName, htTeamBS, tBPBtns, btnPPB4, btnPPB1, btnPPB2, btnPPB3, btnPPB4, btnPPB5,
								btnPPB5);
						if (BPName == btnPPB1.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][9] = 0.01;
								scores[i+1][6] = 0;
							}
						} else if (BPName == btnPPB2.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][9] = 0.01;
								scores[i+1][7] = 0;
							}
						} else if (BPName == btnPPB3.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][9] = 0.01;
								scores[i+1][8] = 0;
							}
						} else if (BPName == btnPPB4.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][9] = 0.01;
								scores[i+1][9] = 0;
							}
						} else if (BPName == btnPPB5.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][9] = 0.01;
								scores[i+1][10] = 0;
							}
						} else if (BPName == btnPPB6.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][9] = 0.01;
								scores[i+1][11] = 0;
							}
						}
					}
				} else {
					Object[] possibilities = { btnPPB1.getText(), btnPPB2.getText(), btnPPB3.getText(),
							btnPPB4.getText(), btnPPB5.getText(), btnPPB6.getText() };
					String BPName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
							"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPB1.getText());
					replace_sub(htSubBbtn4, htSubBbtn1, htSubBbtn2, htSubBbtn3, htSubBbtn4, htSubBbtn5, htSubBbtn6,
							BPName, htTeamBS, tBPBtns, btnPPB4, btnPPB1, btnPPB2, btnPPB3, btnPPB4, btnPPB5, btnPPB6);
					if (BPName == btnPPB1.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][9] = 0.01;
							scores[i+1][6] = 0;
						}
					} else if (BPName == btnPPB2.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][9] = 0.01;
							scores[i+1][7] = 0;
						}
					} else if (BPName == btnPPB3.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][9] = 0.01;
							scores[i+1][8] = 0;
						}
					} else if (BPName == btnPPB4.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][9] = 0.01;
							scores[i+1][9] = 0;
						}
					} else if (BPName == btnPPB5.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][9] = 0.01;
							scores[i+1][10] = 0;
						}
					} else if (BPName == btnPPB6.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][9] = 0.01;
							scores[i+1][11] = 0;
						}
					}
				}

			}
		});

		htSubBbtn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Bp6) {
					int value = (Integer) JOptionPane.showConfirmDialog(contentPane,
							"If you are adding Player 6, select yes, if not, select no.", "Question",
							JOptionPane.YES_NO_OPTION);
					if (value == JOptionPane.YES_OPTION) {
						String BPname = (String) JOptionPane.showInputDialog(contentPane, "ENTER SUB'S NAME");
						if (BPname != null) {
							htSubBbtn6.setText(BPname);
							htTeamBS.remove(htSubBbtn5);
							htTeamBS.add(htSubBbtn6);
							htTeamBS.validate();
							tBPBtns.remove(btnPPB5);
							btnPPB6.setText(BPname);
							tBPBtns.add(btnPPB6);
							Players[11] = BPname;
							Bp6 = true;
							tBPBtns.validate();
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][10] = 0.01;
								scores[i+1][11] = 0;
							}
						}
					} else if (value == JOptionPane.NO_OPTION) {
						Object[] possibilities = { btnPPB1.getText(), btnPPB2.getText(), btnPPB3.getText(),
								btnPPB4.getText(), btnPPB5.getText() };
						String BPName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
								"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPB1.getText());
						replace_sub(htSubBbtn5, htSubBbtn1, htSubBbtn2, htSubBbtn3, htSubBbtn4, htSubBbtn5, htSubBbtn5,
								BPName, htTeamBS, tBPBtns, btnPPB5, btnPPB1, btnPPB2, btnPPB3, btnPPB4, btnPPB5,
								btnPPB6);
						if (BPName == btnPPB1.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][10] = 0.01;
								scores[i+1][6] = 0;
							}
						} else if (BPName == btnPPB2.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][10] = 0.01;
								scores[i+1][7] = 0;
							}
						} else if (BPName == btnPPB3.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][10] = 0.01;
								scores[i+1][8] = 0;
							}
						} else if (BPName == btnPPB4.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][10] = 0.01;
								scores[i+1][9] = 0;
							}
						} else if (BPName == btnPPB5.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][10] = 0.01;
								scores[i+1][10] = 0;
							}
						} else if (BPName == btnPPB6.getText()) {
							for (int i = questionNum; i < 19; i++) {
								scores[i+1][10] = 0.01;
								scores[i+1][11] = 0;
							}
						}
					}
				} else {
					Object[] possibilities = { btnPPB1.getText(), btnPPB2.getText(), btnPPB3.getText(),
							btnPPB4.getText(), btnPPB5.getText(), btnPPB6.getText() };
					String BPName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?",
							"SUBSTITUTION", JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPB1.getText());
					replace_sub(htSubBbtn5, htSubBbtn1, htSubBbtn2, htSubBbtn3, htSubBbtn4, htSubBbtn5, htSubBbtn6,
							BPName, htTeamBS, tBPBtns, btnPPB5, btnPPB1, btnPPB2, btnPPB3, btnPPB4, btnPPB5, btnPPB6);
					if (BPName == btnPPB1.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][10] = 0.01;
							scores[i+1][6] = 0;
						}
					} else if (BPName == btnPPB2.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][10] = 0.01;
							scores[i+1][7] = 0;
						}
					} else if (BPName == btnPPB3.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][10] = 0.01;
							scores[i+1][8] = 0;
						}
					} else if (BPName == btnPPB4.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][10] = 0.01;
							scores[i+1][9] = 0;
						}
					} else if (BPName == btnPPB5.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][10] = 0.01;
							scores[i+1][10] = 0;
						}
					} else if (BPName == btnPPB6.getText()) {
						for (int i = questionNum; i < 19; i++) {
							scores[i+1][10] = 0.01;
							scores[i+1][11] = 0;
						}
					}
				}

			}
		});

		htSubBbtn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] possibilities = { btnPPB1.getText(), btnPPB2.getText(), btnPPB3.getText(), btnPPB4.getText(),
						btnPPB5.getText(), btnPPB6.getText() };
				String BPName = (String) JOptionPane.showInputDialog(contentPane, "Who's subbing in?", "SUBSTITUTION",
						JOptionPane.PLAIN_MESSAGE, null, possibilities, btnPPB1.getText());
				replace_sub(htSubBbtn6, htSubBbtn1, htSubBbtn2, htSubBbtn3, htSubBbtn4, htSubBbtn5, htSubBbtn6, BPName,
						htTeamBS, tBPBtns, btnPPB6, btnPPB1, btnPPB2, btnPPB3, btnPPB4, btnPPB5, btnPPB6);
				if (BPName == btnPPB1.getText()) {
					for (int i = questionNum; i < 19; i++) {
						scores[i+1][11] = 0.01;
						scores[i+1][6] = 0;
					}
				} else if (BPName == btnPPB2.getText()) {
					for (int i = questionNum; i < 19; i++) {
						scores[i+1][11] = 0.01;
						scores[i+1][7] = 0;
					}
				} else if (BPName == btnPPB3.getText()) {
					for (int i = questionNum; i < 19; i++) {
						scores[i+1][11] = 0.01;
						scores[i+1][8] = 0;
					}
				} else if (BPName == btnPPB4.getText()) {
					for (int i = questionNum; i < 19; i++) {
						scores[i+1][11] = 0.01;
						scores[i+1][9] = 0;
					}
				} else if (BPName == btnPPB5.getText()) {
					for (int i = questionNum; i < 19; i++) {
						scores[i+1][11] = 0.01;
						scores[i+1][10] = 0;
					}
				} else if (BPName == btnPPB6.getText()) {
					for (int i = questionNum; i < 19; i++) {
						scores[i+1][11] = 0.01;
						scores[i+1][11] = 0;
					}
				}
			}
		});

		htBack.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "qP");
				menuBar_scoreScreen.show();
			}
		});

	}

}
