import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class MainGUI {
    private JFrame frame;
    private JTextField playerNameField;
    private JTextField yearField;
    private JTextArea resultArea;
    private NBADataBase data = new NBADataBase();

    public MainGUI() throws IOException {
        frame = new JFrame("NBA Player Stats");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 600);
        setupMainWindow();
        frame.setVisible(true);
    }

    private void setupMainWindow() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add a placeholder panel with fixed size
        JPanel placeholderPanel = new JPanel();
        placeholderPanel.setPreferredSize(new Dimension(650, 200));
        panel.add(placeholderPanel, BorderLayout.NORTH);

        // Use the custom ButtonPanel
        ButtonPanel buttonPanel = new ButtonPanel(new GridLayout(3, 2));

        JButton playerFinderButton = new JButton("Player Finder");
        JButton getPlayerStatsButton = new JButton("Get Player Stats");
        JButton getLeagueLeadersButton = new JButton("Get League Leaders");
        JButton getAllTimeLeadersButton = new JButton("Get All Time Leaders");
        JButton seasonalRecordsButton = new JButton("Seasonal Records");
        JButton anotherButton = new JButton("Another Button");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        buttonPanel.add(playerFinderButton);
        buttonPanel.add(getPlayerStatsButton);
        buttonPanel.add(getLeagueLeadersButton);
        buttonPanel.add(getAllTimeLeadersButton);
        buttonPanel.add(seasonalRecordsButton);
        buttonPanel.add(anotherButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.getContentPane().add(BorderLayout.SOUTH, new JScrollPane(resultArea));
        frame.revalidate();
        frame.repaint();

        playerFinderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupPlayerFinderWindow();
            }
        });

        getPlayerStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupPlayerStatsWindow();
            }
        });

        getLeagueLeadersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupLeagueLeadersWindow();
            }
        });

        getAllTimeLeadersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupAllTimeLeadersWindow();
            }
        });

        seasonalRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupSeasonalRecordsWindow();
            }
        });

        anotherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement another window setup method
            }
        });
    }

    public static void main(String[] args) throws IOException {
        new MainGUI();
    }

    private void setupPlayerFinderWindow() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel playerNameLabel = new JLabel("Player Name:");
        playerNameField = new JTextField();
        JLabel yearLabel = new JLabel("Year:");
        yearField = new JTextField();
        JButton getPlayerDescButton = new JButton("Get Player Description");
        JButton backButton = new JButton("Back");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        panel.add(playerNameLabel);
        panel.add(playerNameField);
        panel.add(yearLabel);
        panel.add(yearField);
        panel.add(getPlayerDescButton);
        panel.add(backButton);

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(resultArea));
        frame.revalidate();
        frame.repaint();

        getPlayerDescButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameField.getText().toUpperCase();
                int year = Integer.parseInt(yearField.getText());
                String desc = data.playerDesc(playerName, year);
                resultArea.setText(desc);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupMainWindow();
            }
        });
    }

    private void setupPlayerStatsWindow() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel playerNameLabel = new JLabel("Player Name:");
        playerNameField = new JTextField();
        JLabel yearLabel = new JLabel("Year (Leave blank for career stats):");
        yearField = new JTextField();
        JButton getPlayerStatsButton = new JButton("Get Player Stats");
        JButton backButton = new JButton("Back");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        panel.add(playerNameLabel);
        panel.add(playerNameField);
        panel.add(yearLabel);
        panel.add(yearField);
        panel.add(getPlayerStatsButton);
        panel.add(backButton);


        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(resultArea));
        frame.revalidate();
        frame.repaint();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupMainWindow();
            }
        });

        getPlayerStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameField.getText().toUpperCase();
                String yearString = yearField.getText();
                int year = yearString.isEmpty() ? 0 : Integer.parseInt(yearString);
                double[] stats = data.playerStats(playerName, year);
                resultArea.setText("PPG: " + stats[0] + "\nAPG: " + stats[1] + "\nRPG: " + stats[2] + "\nSPG: " + stats[3] + "\nBPG: " + stats[4]);
            }
        });
    }

    private void setupLeagueLeadersWindow() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel yearLabel = new JLabel("Year:");
        yearField = new JTextField();
        JButton ptsButton = new JButton("PTS");
        JButton astButton = new JButton("AST");
        JButton trbButton = new JButton("TRB");
        JButton stlButton = new JButton("STL");
        JButton blkButton = new JButton("BLK");
        JButton backButton = new JButton("Back");

        panel.add(yearLabel);
        panel.add(yearField);
        panel.add(ptsButton);
        panel.add(astButton);
        panel.add(trbButton);
        panel.add(stlButton);
        panel.add(blkButton);
        panel.add(backButton);

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(resultArea));
        frame.revalidate();
        frame.repaint();

        ActionListener leagueLeadersListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int year = Integer.parseInt(yearField.getText());
                String stat = e.getActionCommand();
                Leader[] leaders = data.leagueLeaders(year, stat, true, 10);
                StringBuilder result = new StringBuilder("League Leaders:\n");
                for (Leader leader : leaders) {
                    result.append(leader.toString()).append("\n");
                }
                resultArea.setText(result.toString());
            }
        };

        ptsButton.addActionListener(leagueLeadersListener);
        astButton.addActionListener(leagueLeadersListener);
        trbButton.addActionListener(leagueLeadersListener);
        stlButton.addActionListener(leagueLeadersListener);
        blkButton.addActionListener(leagueLeadersListener);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupMainWindow();
            }
        });
    }

    private void setupAllTimeLeadersWindow() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JButton ptsButton = new JButton("PTS");
        JButton astButton = new JButton("AST");
        JButton trbButton = new JButton("TRB");
        JButton stlButton = new JButton("STL");
        JButton blkButton = new JButton("BLK");
        JButton backButton = new JButton("Back");

        panel.add(ptsButton);
        panel.add(astButton);
        panel.add(trbButton);
        panel.add(stlButton);
        panel.add(blkButton);
        panel.add(backButton);

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(resultArea));
        frame.revalidate();
        frame.repaint();

        ActionListener allTimeLeadersListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stat = e.getActionCommand();
                Leader[] leaders = data.allTimeLeaders(stat, true, 10);
                StringBuilder result = new StringBuilder("All Time Leaders:\n");
                for (Leader leader : leaders) {
                    result.append(leader.toString()).append("\n");
                }
                resultArea.setText(result.toString());
            }
        };

        ptsButton.addActionListener(allTimeLeadersListener);
        astButton.addActionListener(allTimeLeadersListener);
        trbButton.addActionListener(allTimeLeadersListener);
        stlButton.addActionListener(allTimeLeadersListener);
        blkButton.addActionListener(allTimeLeadersListener);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupMainWindow();
            }
        });
    }

    private void setupSeasonalRecordsWindow() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JButton ptsButton = new JButton("PTS");
        JButton astButton = new JButton("AST");
        JButton trbButton = new JButton("TRB");
        JButton stlButton = new JButton("STL");
        JButton blkButton = new JButton("BLK");
        JButton backButton = new JButton("Back");

        panel.add(ptsButton);
        panel.add(astButton);
        panel.add(trbButton);
        panel.add(stlButton);
        panel.add(blkButton);
        panel.add(backButton);

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(resultArea));
        frame.revalidate();
        frame.repaint();

        ActionListener seasonalRecordsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stat = e.getActionCommand();
                Leader[] leaders = data.seasonalRecords(stat, true, 10);
                StringBuilder result = new StringBuilder("Seasonal Records:\n");
                for (Leader leader : leaders) {
                    result.append(leader.toString()).append("\n");
                }
                resultArea.setText(result.toString());
            }
        };

        ptsButton.addActionListener(seasonalRecordsListener);
        astButton.addActionListener(seasonalRecordsListener);
        trbButton.addActionListener(seasonalRecordsListener);
        stlButton.addActionListener(seasonalRecordsListener);
        blkButton.addActionListener(seasonalRecordsListener);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupMainWindow();
            }
        });
    }

}