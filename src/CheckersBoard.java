import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CheckersBoard extends JFrame implements ActionListener, MouseListener {
    private Handler handler;
    private JLabel currentStatus;
    private Field [][] fieldArray;
    private JPanel board;
    public static void main(String[] args) {
        CheckersBoard chBrd = new CheckersBoard();
        chBrd.setVisible(true);
        chBrd.setTitle("ШАШКИ");
        chBrd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public CheckersBoard() {
        Container cp = getContentPane();
        JPanel boardParent = new JPanel();
        boardParent.setLayout(new GridBagLayout());
        board = new JPanel();
        board.addMouseListener(this);
        board.setPreferredSize(new Dimension(400, 400));
        board.setMinimumSize(new Dimension(400, 400));
        board.setMaximumSize(new Dimension(400, 400));

        boardParent.setPreferredSize(new Dimension(400, 400));
        boardParent.setMinimumSize(new Dimension(400, 400));
        boardParent.setMaximumSize(new Dimension(400, 400));

        board.setLayout(new GridLayout(8,8));


        fieldArray = new Field[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++ ){
                fieldArray[i][j] = new Field();
                board.add(fieldArray[i][j]);
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        if (i < 3) {
                            fieldArray[i][j].Black = true;
                            fieldArray[i][j].Empty = false;
                        }
                        if (i > 4) {
                            fieldArray[i][j].White = true;
                            fieldArray[i][j].Empty = false;
                        }
                        fieldArray[i][j].setBackground(new Color(107, 73, 73));
                    }
                    else {
                        fieldArray[i][j].setBackground(new Color(169, 166, 160));
                    }
                }
                else {
                    if (j % 2 == 0) {
                        fieldArray[i][j].setBackground(new Color(169, 166, 160));
                    }
                    else {
                        if (i < 3) {
                            fieldArray[i][j].Black = true;
                            fieldArray[i][j].Empty = false;
                        }
                        if (i > 4) {
                            fieldArray[i][j].White = true;
                            fieldArray[i][j].Empty = false;
                        }
                        fieldArray[i][j].setBackground(new Color(107, 73, 73));
                    }
                }
            }
        }

        boardParent.add(board);

        JPanel top = new JPanel();
        currentStatus = new JLabel("ПРИЯТНОЙ ИГРЫ!");
        top.add(currentStatus);
        JPanel down = new JPanel();
        JButton start = new JButton("НОВАЯ ИГРА");
        down.add(start);

        cp.add(top, BorderLayout.NORTH);
        cp.add(boardParent, BorderLayout.CENTER);
        cp.add(down, BorderLayout.SOUTH);
        setBounds(50, 50, 500, 600);

        handler = new Handler(fieldArray);
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Пока не поддерживается");
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        int y = e.getX() / 50;
        int x = e.getY() / 50;
        currentStatus.setText(x + " " + y);

        handler.clickHandler(x, y);
        repaint();
    }

    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
}
