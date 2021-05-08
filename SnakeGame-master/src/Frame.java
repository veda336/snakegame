import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {


    Frame(){
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(GamePanel.width,GamePanel.height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        GamePanel d = new GamePanel();
        this.add(d);
        this.addKeyListener(d);
        this.pack();


    }
    public static void main(String args[]){
        new Frame();
    }
}