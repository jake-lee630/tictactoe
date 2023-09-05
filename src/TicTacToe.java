import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe{
    private JFrame frame = new JFrame("Tic-Tac-Toe");
    private JLabel textLabel = new JLabel();
    private JPanel textPanel = new JPanel();
    private JPanel boardPanel = new JPanel();

    private JButton[][] board = new JButton[3][3];
    private String playerX = "X";
    private String playerO = "O";
    private String currentPlayer = playerX;

    private boolean gameOver = false;
    private int turns = 0;

    private JButton restart = new JButton();
    
    // Constructor that sets up game
    public TicTacToe(){
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.setSize(600, 700);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.black);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Monospaced", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.black);
        frame.add(boardPanel);

        restart.setText("Restart");
        restart.setSize(50, 50);
        restart.setVisible(false);
        restart.setFocusable(false);
        restart.setBackground(Color.black);
        restart.setForeground(Color.white);
        restart.setFont(new Font("Monospaced", Font.BOLD, 50));
        frame.add(restart, BorderLayout.SOUTH);
        

        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                JButton square = new JButton();
                board[r][c] = square;
                boardPanel.add(square);

                square.setBackground(Color.black);
                square.setForeground(Color.white);
                square.setFont(new Font("Monospaced", Font.BOLD, 120));
                square.setFocusable(false);

                square.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton square = (JButton) e.getSource();
                        if (square.getText() == "") {
                            square.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }
    }

    private void checkWinner() {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                endGame();
                return;
            }
        }

        //vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;
            
            if (board[0][c].getText() == board[1][c].getText() &&
                board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                endGame();
                return;
            }
        }

        //diagonally
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            endGame();
            return;
        }

        //anti-diagonally
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            endGame();
            return;
        }

        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            endGame();
        }
    }

    private void endGame() {
        gameOver = true;
        restart.setVisible(true);
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
                restart();
            }
        });
    }

    private void setWinner(JButton square) {
        square.setForeground(Color.green);
        square.setBackground(Color.black);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    private void setTie(JButton square) {
        square.setForeground(Color.orange);
        square.setBackground(Color.black);
        textLabel.setText("Tie!");
    }

    private void restart(){
        frame.dispose();
        new TicTacToe();
    }
}