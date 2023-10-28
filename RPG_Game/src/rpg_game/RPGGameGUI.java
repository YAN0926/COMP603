/**
 *
 * The RPGGameGUI class provides a graphical user interface for the RPG game.
 * The GUI is built using Java's Swing framework and includes components like a main window (JFrame), panels (JPanel), buttons (JButton), and a menu bar (JMenuBar).
 * The main window displays the game title "ELDEN SKY" and offers buttons to start a new game or load a saved one.
 * When the game starts, the GUI displays the player's stats and provides a menu with game options.
 * The GUI communicates with the game's logic via the GameEngine class.
 * User actions, like button clicks, trigger corresponding methods in the game engine to process and update the game state.
 * Additionally, the GUI has methods for displaying messages, prompting for player input, and updating player stats visually.
 * The class concludes with a main method, launching the GUI when the program starts.
 *
 * Name: Chi Yan CHEUNG SID: 15905216
 * Name: Renger NG SID: 20124370
 *
 * COMP603 Software Development Project 2
 *
 */
package rpg_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// This class serves as the primary GUI interface for the RPG game, facilitating user interaction and game visualization
public class RPGGameGUI {

    // Member variables for GUI components and game state
    private JFrame frame; // Main game window
    private JPanel panel; // Primary content panel
    private JButton newGameButton; // Button to start a new game
    private JButton loadGameButton; // Button to load a saved game
    private GameEngine gameEngine; // Reference to your game engine
    private String playerName; // Name of the player

    private JMenuBar menuBar; // Menu bar reference

    private JPanel gifPanel; // Panel to display game-related GIF

    private JLabel playerAttackLabel; // Label displaying player's attack stat
    private JLabel playerDefenseLabel; // Label displaying player's defense stat
    private JProgressBar playerHPBar; // Progress bar showing player's health

    // Constructor to initialize and set up the main GUI components
    public RPGGameGUI() {

        frame = new JFrame("Your RPG Game Name");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);

        panel = new JPanel(new BorderLayout()); // Use BorderLayout for the panel

        // Add a JLabel for the game name at the top
        JLabel gameNameLabel = new JLabel("         ELDEN SKY");
        gameNameLabel.setFont(new Font("Arial", Font.BOLD, 90)); // Customize font and size
        panel.add(gameNameLabel, BorderLayout.NORTH); // Add the label to the top (NORTH) of the panel

        // Create a panel for the buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout());

        newGameButton = new JButton("Start New Game");
        loadGameButton = new JButton("Load Saved Game");

        buttonPanel.add(newGameButton); // Add the "Start New Game" button
        buttonPanel.add(Box.createGlue()); // Add some space to push the "Load Saved Game" button to the right
        buttonPanel.add(loadGameButton); // Add the "Load Saved Game" button to the right

        panel.add(buttonPanel, BorderLayout.SOUTH); // Add the buttonPanel to the bottom (SOUTH) of the main panel

        frame.add(panel);

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
        ImageIcon icon = new ImageIcon("./resources/RPG.gif");
        Image img = icon.getImage().getScaledInstance(1000, 540, Image.SCALE_DEFAULT);
        icon = new ImageIcon(img);
        JLabel gifLabel = new JLabel(icon);

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

    // Method to display a dialog when the player is defeated, offering restart or load options
    public int showDefeatDialog() {
        Object[] options = {"Start New Game", "Load Saved Game"};
        int choice = JOptionPane.showOptionDialog(frame, "You have been defeated. What would you like to do?", "Defeated",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return choice; // Return the player's choice (0 for New Game, 1 for Load Saved Game)
    }

    // Main method to initialize and launch the game's GUI
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
