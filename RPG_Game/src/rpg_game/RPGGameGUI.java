package rpg_game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RPGGameGUI {
    private JFrame frame;
    private JPanel panel;
    private JButton newGameButton;
    private JButton loadGameButton;
    private GameEngine gameEngine; // Reference to your game engine
    private String playerName;

    private JMenuBar menuBar; // Menu bar reference
    
    private JPanel gifPanel;
    
    private JLabel playerAttackLabel;
    private JLabel playerDefenseLabel;
    private JProgressBar playerHPBar;

    public RPGGameGUI() {
        frame = new JFrame("RPG Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        panel = new JPanel();
        frame.add(panel);

        newGameButton = new JButton("Start New Game");
        loadGameButton = new JButton("Load Saved Game");

        panel.add(newGameButton);
        panel.add(loadGameButton);



        gameEngine = new GameEngine(this); // Initialize your game engine with a reference to this GUI
        
        gifPanel = new JPanel();
        panel.add(gifPanel); // Add the GIF panel to the main panel

        addGifImageToGUI(); // Add the GIF image to the GIF panel

        // ActionListener for Start New Game button
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (true) {
                    playerName = promptForPlayerName(); // Use a custom input dialog
                    if (playerName == null) {
                        // Player clicked cancel or closed the dialog
                        return; // Exit this method, going back to the main page
                    } else if (!playerName.trim().isEmpty()) {
                        break; // Break the loop if a valid name is entered
                    } else {
                        displayMessage("Please enter a valid player name.");
                    }
                }

                gameEngine.startNewGame(playerName); // Pass the player's name to the game engine
                setMenuBarVisible(true); // Show the menu bar after starting the game
                addGifImageToGUI();
            }
        });

        // ActionListener for Load Saved Game button
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	gameEngine.loadGame();
                setMenuBarVisible(true); // Show the menu bar after loading the game
                addGifImageToGUI();
            }
        });

        // Initially, hide the menu bar
        setMenuBarVisible(false);
    }
    

    // Method to create the menu bar and menu items
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");

        // Add menu items for in-game actions
        JMenuItem viewCharacterStatsMenuItem = new JMenuItem("View Character Stats");
        JMenuItem viewInventoryMenuItem = new JMenuItem("View Inventory");
        JMenuItem doQuestMenuItem = new JMenuItem("Do Quest");
        JMenuItem saveGameMenuItem = new JMenuItem("Save Game");
        JMenuItem loadGameMenuItem = new JMenuItem("Load Game");
        JMenuItem quitMenuItem = new JMenuItem("Quit");

        // Add action listeners to menu items
        viewCharacterStatsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.viewCharacterStats();
            }
        });

        viewInventoryMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.viewInventory();
            }
        });

        doQuestMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.chooseQuestAndStartBattle(frame);
            }
        });

        saveGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.saveGame();
            }
        });

        loadGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.loadGame();
            }
        });

        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(frame, "Thank you for playing");
                frame.dispose();
            }
        });

        // Add menu items to the menu
        menu.add(viewCharacterStatsMenuItem);
        menu.add(viewInventoryMenuItem);
        menu.add(doQuestMenuItem);
        menu.add(saveGameMenuItem);
        menu.add(loadGameMenuItem);
        menu.add(quitMenuItem);

        // Add the menu to the menu bar
        menuBar.add(menu);

        return menuBar;
    }
    
    private void addGifImageToGUI() {

        // Create a JLabel to display the GIF
        JLabel gifLabel = new JLabel();
        gifLabel.setIcon(new javax.swing.ImageIcon("./resources/RPG.gif"));
        

        // Add the JLabel to your GUI panel
        panel.add(gifLabel);

        // Refresh the GUI
        panel.revalidate();
        panel.repaint();
    }

    // Method to set the visibility of the menu bar
    private void setMenuBarVisible(boolean visible) {
        if (visible) {
            panel.removeAll();
            panel.setLayout(new FlowLayout());
            playerAttackLabel = new JLabel("Attack: ");
            playerDefenseLabel = new JLabel("Defense: ");
            playerHPBar = new JProgressBar(0, gameEngine.player.getMaxHealth()); // Initialize with initial HP
            playerHPBar.setStringPainted(true); // Display the value as text
            playerHPBar.setForeground(Color.blue);
            panel.add(playerHPBar);
            panel.add(playerAttackLabel);
            panel.add(playerDefenseLabel);

            updatePlayerInfo(gameEngine.player.getHealth(), gameEngine.player.getAttack(), gameEngine.player.getDefense());
            if (menuBar == null) {
                menuBar = createMenuBar();
            }
            frame.setJMenuBar(menuBar);
        } else {
            frame.setJMenuBar(null);
        }
        frame.revalidate();
    }


    // Method to display a message with options
    public void displayMessageWithOptions(String message, List<String> options) {

        // Create option buttons and add them to the panel
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout());

        for (String option : options) {
            JButton optionButton = new JButton(option);
            optionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Send the chosen option to the GameEngine
                    gameEngine.handleOptionChoice(option);
                }
            });
            optionPanel.add(optionButton);
        }

        // Add the option panel to the main panel
        panel.add(optionPanel);

        // Refresh the GUI
        panel.revalidate();
        panel.repaint();
    }

    // Method to display a message
    public void displayMessage(String message) {
    	JOptionPane.showMessageDialog(frame, message);
    }

    // Method to get the player's name
    public String promptForPlayerName() {
    	return JOptionPane.showInputDialog(frame, "Enter your name:");
    }

    public void show() {
        frame.setVisible(true);
    }
    
    public void updatePlayerInfo(int hp, int attack, int defense) {

    	playerHPBar.setMaximum(gameEngine.player.getMaxHealth());
        playerHPBar.setValue(hp);
        playerHPBar.setString("HP: " + hp);
        playerAttackLabel.setText("Attack: " + attack);
        playerDefenseLabel.setText("Defense: " + defense);
     // Calculate the threshold value for 50% HP
        int thresholdValue = gameEngine.player.getMaxHealth() / 2;


        // Update the HP bar color based on the current HP value
        if (gameEngine.player.getHealth() < thresholdValue) {
            playerHPBar.setForeground(Color.YELLOW);
        } else {
            playerHPBar.setForeground(Color.blue); // Or any other color you want for HP >= 50%
        }
    }

	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}
	
	public int showDefeatDialog() {
	    Object[] options = {"Start New Game", "Load Saved Game"};
	    int choice = JOptionPane.showOptionDialog(frame, "You have been defeated. What would you like to do?", "Defeated",
	            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	    return choice; // Return the player's choice (0 for New Game, 1 for Load Saved Game)
	}
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RPGGameGUI gui = new RPGGameGUI();
                gui.addGifImageToGUI();
                gui.show();
            }
        });
    }
}
