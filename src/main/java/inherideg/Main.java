package inherideg;

import db.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        try {
            game();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void game() throws SQLException {
        JFrame f = new JFrame();
        Deg deg = new Deg("dog-123", null, 0, 0);

        deg.retrieve();
        Canvas c = new Canvas(){
            @Override
            public void paint(Graphics g) {
                setBackground(Color.WHITE);
                g.fillRect((int)deg.getX(), (int)deg.getY(), 20, 10);
                g.drawString(deg.getName(), (int)deg.getX(), (int)deg.getY());
            }
        };

        c.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 38:
                        deg.moveUp(2);
                        break;
                    case 40:
                        deg.moveDown(2);
                        break;
                    case 37:
                        deg.moveLeft(2);
                        break;
                    case 39:
                        deg.moveRight(2);
                        break;
                }
                c.repaint();
            }
        });

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    deg.save();
                    Database.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });

        f.add(c);
        f.setSize(800,600);
        f.setVisible(true);
    }
}
