package scholarBowlScorer;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.BoxLayout;

public class screen2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8548508922121393516L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					screen2 frame = new screen2();
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
	// public JLabel[][] createLabels() {
	public JLabel[][] labels = new JLabel[32][21];

	// return labels;
	// }
	public void drawLabels(JLabel[][] l) {
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 21; j++) {
				labels[i][j] = new JLabel(" ");
			}
		}
		l[1][0].setText("Players");
		for (int i = 3; i < 23; i++) {
			l[i][0].setText("Q" + (i - 2));
		}
		l[1][1].setText(screen1.teamAName);
		l[1][11].setText(screen1.teamBName);
		l[2][1].setText(screen1.Players[0]);
		l[2][2].setText(screen1.Players[1]);
		l[2][3].setText(screen1.Players[2]);
		l[2][4].setText(screen1.Players[3]);
		l[2][5].setText(screen1.Players[4]);
		l[2][6].setText(screen1.Players[5]);
		l[2][7].setText("|A BONUS|");
		l[2][8].setText("|A RBND|");
		l[2][9].setText("|A B+R|");
		l[2][10].setText("|SUBTOTAL|");
		l[2][11].setText(screen1.Players[6]);
		l[2][12].setText(screen1.Players[7]);
		l[2][13].setText(screen1.Players[8]);
		l[2][14].setText(screen1.Players[9]);
		l[2][15].setText(screen1.Players[10]);
		l[2][16].setText(screen1.Players[11]);
		l[2][17].setText("|B BONUS|");
		l[2][18].setText("|B RBND|");
		l[2][19].setText("|B B+R|");
		l[2][20].setText("|SUBTOTAL|");
		/*
		 * l[24][1].setText("QUESTIONS"); l[24][2].setText(" ANSWERED");
		 * l[24][10].setText("QUESTIONS"); l[24][11].setText(" ANSWERED");
		 * l[25][0].setText("10"); l[26][0].setText("15"); l[27][0].setText("-5");
		 * l[28][0].setText("0");
		 */
		// l[29][0].setText("SUBTOTALS");
		l[27][0].setText("TOTALS");

	}

	public void updateLabels(JLabel[][] l, double[][] s) {
		int subA = 0;
		int subB = 0;
		for (int i = 3; i < 24; i++) {
			for (int j = 1; j < 7; j++) {
				if (s[i - 3][j - 1] == 0.001) {
					l[i][j].setText("SUB");
				} else if (s[i - 3][j - 1] == 0.0001) {
					l[i][j].setText("INC");
				} else {
					l[i][j].setText(Double.toString(s[i - 3][j - 1]));
					subA += s[i-3][j-1];
					
				}

			}
			
			for (int j = 11; j < 17; j++) {
				if (s[i - 3][j - 5] == 0.001) {
					l[i][j].setText("SUB");
				} else if (s[i - 3][j - 5] == 0.0001) {
					l[i][j].setText("INC");
				} else {
					l[i][j].setText(Double.toString(s[i - 3][j - 5]));
					subB += s[i-3][j-5];
					
				}
			}
			
			for (int j = 0; j < 2; j++) {
				l[i][j + 7].setText(Double.toString(screen1.bonusArray[i - 3][j]));
				l[i][j + 17].setText(Double.toString(screen1.bonusArray[i - 3][j + 2]));
			}


			l[i][9].setText(Double.toString(screen1.bonusArray[i - 3][0] + screen1.bonusArray[i - 3][1]));
			l[i][19].setText(Double.toString(screen1.bonusArray[i - 3][2] + screen1.bonusArray[i - 3][3]));
			l[27][10].setText(Double.toString(screen1.scoresA));
			l[27][20].setText(Double.toString(screen1.scoresB));
			l[23][0].setText("TIEBREAKER");
			subA += screen1.bonusArray[i-3][0];
			subA += screen1.bonusArray[i-3][1];
			subB += screen1.bonusArray[i-3][2];
			subB += screen1.bonusArray[i-3][3];
			l[i][10].setText(Double.toString(subA));
			l[i][20].setText(Double.toString(subB));

		}

	}

	public void showGUI(JLabel[][] l, JPanel p) {
		for (int i = 0; i < l.length; i++) {
			for (int j = 0; j < l[i].length; j++) {
				p.add(l[i][j]);
			}
		}
	}

	public screen2() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		JPanel scorePane = new JPanel(new GridLayout(33,19));
		JButton refreshBtn = new JButton("REFRESH");
		refreshBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawLabels(labels);
				updateLabels(labels, screen1.scores);
			}
		});
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(refreshBtn);
		contentPane.add(scorePane);
		setContentPane(contentPane);
		drawLabels(labels);
		updateLabels(labels, screen1.scores);
		// showGUI(labels, contentPane);
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				scorePane.add(labels[i][j]);
			}
		}
		
		pack();

	}

}
