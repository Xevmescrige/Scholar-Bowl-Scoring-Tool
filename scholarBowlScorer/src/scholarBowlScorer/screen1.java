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
	int scoresA = 0;
	int scoresB = 0;
	public String teamAName = "TEAM A";
	public String teamBName = "TEAM B";
	public int questionNum = 0;
	public int playerNum = 0;
	public boolean currentTeam = true; //true is A, false is B
	public double[][] scores = new double[20][12]; //12x20(+) array, arrays 1-6 are team A (4+2subs), 7-12 are for B.
	
	public static void printArray(double mat[][])
    {
        for (double[] row : mat)
            System.out.println(Arrays.toString(row));
    }
	
	public void add_score(double value) 
	{
		scores[questionNum][playerNum] += value; 
		if((playerNum >= 0)&&(playerNum <=5)) {
			scoresA += value;
		}
		else if((playerNum >= 6)&&(playerNum <=11)) {
			scoresB += value;
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
		if((playerNum >= 0)&&(playerNum <=5)) {
			currentTeam = true;
			thisTeam.setText(teamAName);
			thatTeam.setText(teamBName);
		}
			
		else if((playerNum >= 6)&&(playerNum <=11)) {
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
	//nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore, lblTBScore);
	public void nextQuestion(JLabel label, JMenuBar menubar, JButton button, JLabel label2, JLabel label3) {
		CardLayout c = (CardLayout) (contentPane.getLayout());
		c.show(contentPane, "qP");
		if (questionNum <= 18) {
			if (questionNum == 9) {
				c.show(contentPane, "htp1");
			}
			else if (questionNum == 19) {
				menubar.add(button);
			}
			else {
				questionNum += 1;
			}
		} 
		else if (questionNum >= 19) {
			
			if (JOptionPane.showConfirmDialog(contentPane,
					"Are you sure you want to go past question " + (questionNum+1)
							+ "?  If this is not your intention, click the \"END ROUND\" button",
					"Question", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				questionNum += 1;
			}
			
		}
		label.setText("QUESTION: " + (Integer.toString(questionNum+1)));
		label2.setText(teamAName.toUpperCase() + ": " + scoresA);
		label3.setText(teamBName.toUpperCase() + ": " + scoresB);
	}
	
	public double[][] bonusArray = new double[20][4]; //column 0-1 for team A, 2-3 for B -- 0, 2 are bonus, 1,3 are rebound
	public void bonusDone(JLabel ttBP, JLabel thtBP) {
		if(currentTeam) {
			double thisScore = Double.parseDouble(ttBP.getText());
			scoresA += thisScore;
			bonusArray[questionNum][0] = thisScore;
			double thatScore = Double.parseDouble(thtBP.getText());
			scoresB += thatScore;
			bonusArray[questionNum][3] = thatScore;
		}
		else {
			double thisScore = Double.parseDouble(thtBP.getText());
			scoresA += thisScore;
			bonusArray[questionNum][2] = thisScore;
			double thatScore = Double.parseDouble(ttBP.getText());
			scoresB += thatScore;
			bonusArray[questionNum][1] = thatScore;
		}
	}
	
	public JLabel[][] createLabels(){
        JLabel[][] labels=new JLabel[10][10];
        for (int i=0;i<10;i++){
        	for(int j=0; j<10; j++) {
        		labels[i][j]=new JLabel("message" + i);
        	}
        }
        return labels;
    }
	
	/*public void showGUI(){
        JLabel[] labels=createLabels();
        for (int i=0;i<labels.length;i++){
            this.add(labels[i]);
        }
    }*/
	
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
		JButton btnRM = new JButton();
		btnRM.setText("-10");
		JButton btnLP = new JButton();
		btnLP.setText("+10");
		JButton btnLM = new JButton();
		btnLM.setText("-10");
		
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
		halftimePanel.setLayout(new GridLayout(0, 4, 0, 0));
		
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
		JButton btnAP5 = new JButton("PLAYER 5");
		JButton btnAP6 = new JButton("PLAYER 6");

		JButton btnSetTeamB = new JButton("SET TEAM B NAME");
		JButton btnBP1 = new JButton("PLAYER 1");
		JButton btnBP2 = new JButton("PLAYER 2");
		JButton btnBP3 = new JButton("PLAYER 3");
		JButton btnBP4 = new JButton("PLAYER 4");
		JButton btnBP5 = new JButton("PLAYER 5");
		JButton btnBP6 = new JButton("PLAYER 6");
		
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
		menuBar_setname.add(buttonTNBack);

		bonusConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "qP");
				bonusDone(thisTeamBP, thatTeamBP);
				printArray(bonusArray);
				nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore, lblTBScore);	
				getJMenuBar().setVisible(true);
				JLabel[][] labels=createLabels();
		        /*for (int i=0;i<labels.length;i++){
		        	for (int j=0;j<labels.length;j++)
		        		answerPanel.add(labels[i][j]);
		        }*/
			}
		});
		
		btnAnsC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_score(10);
				playerAnswered(lblPlayName, btnPPA1.getText());
				//answeredNext();
				answeredBonus(thisTeamN, thatTeamN);
				//nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore, lblTBScore);	
			}
		});
		btnAnsI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_score(0.001);
				answeredNext();
				nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore, lblTBScore);
			}
		});
		btnAnsN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_score(-5);
				answeredNext();
				nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore, lblTBScore);
			}
		});
		btnAnsP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_score(15);
				playerAnswered(lblPlayName, btnPPA1.getText());
				//answeredNext();
				answeredBonus(thisTeamN, thatTeamN);
				//nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore, lblTBScore);
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
				//Code to export to xls?
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
		menuBar_setup.add(btnLoadSettings);

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "tAP");
				setJMenuBar(menuBar_setname);
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

		btnPQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (questionNum > 0) {
					questionNum -= 1;
					lblQNumber.setText("QUESTION: " + (Integer.toString(questionNum+1)));
				} else {
					questionNum = 0;
					lblQNumber.setText("QUESTION: " + (Integer.toString(questionNum+1)));
				}
			}
		});
		menuBar_scoreScreen.add(btnPQ);

		menuBar_scoreScreen.add(btnViewScoreboard);

		btnNextQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextQuestion(lblQNumber, menuBar_scoreScreen, btnFinish, lblTAScore, lblTBScore);
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

	}

}
