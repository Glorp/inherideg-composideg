package composideg;

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

        SqlHalp<DegDTO> sql = new SqlHalp<>(new DegTable());
        DegDTO dto = new DegDTO();
        dto.setDogId("dog-123");
        sql.retrieve(dto);
        Deg deg = DegMapper.degFrom(dto);

        Canvas c = new Canvas(){
            @Override
            public void paint(Graphics g) {
                setBackground(Color.WHITE);
                g.fillRect(deg.getPosition().getX(), deg.getPosition().getY(), 20, 10);
                g.drawString(deg.getName(), deg.getPosition().getX(), deg.getPosition().getY());
            }
        };

        c.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 38:
                        deg.move(Vector.up(2));
                        break;
                    case 40:
                        deg.move(Vector.down(2));
                        break;
                    case 37:
                        deg.move(Vector.left(2));
                        break;
                    case 39:
                        deg.move(Vector.right(2));
                        break;
                }
                c.repaint();
            }
        });

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    sql.save(DegMapper.dtoFrom(deg));
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
