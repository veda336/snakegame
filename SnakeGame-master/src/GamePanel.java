import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GamePanel extends JPanel implements  KeyListener,ActionListener {

    static final int width = 30;
    static final int height = 30;
    static final int tile = 20;

    ArrayList<Point> snakeBody;
    Random random;
    Point head;
    int size;
    int dir;
    private Point food;
    boolean space;
    boolean running;
    private Timer looper = new Timer(1000/5, (ActionListener) this);
    GamePanel(){
        this.setPreferredSize(new Dimension(width*tile,height*tile));
        this.addKeyListener(this);
        snakeBody = new ArrayList<>();
        random = new Random();
        start();
        looper.start();
        space=false;
        running=true;
    }

    private void start() {
        snakeBody.clear();
        size=1;
        dir  = 0;
        snakeBody.add(new Point(random.nextInt((int)(width*tile)),
                random.nextInt((int)(height*tile))));
        food = setFood();
    }
    private Point setFood()
    {
        Point f = null;
        while(f == null)
        {
            int x = (int)(Math.random()*width);
            int y = (int)(Math.random()*height);

            for(int i = 0; i < size; i++)
            {
                if(snakeBody.get(i).x == x && snakeBody.get(i).y == y)
                {
                    setFood();

                }
                else{
                    f = new Point(x, y);

                }

            }
        }
        return f;

    }

    private void move() {

        for(int i=size-1;i>0;i--){
            snakeBody.get(i).x = snakeBody.get(i -1).x; // dahhhhhh
            snakeBody.get(i).y = snakeBody.get(i -1).y;
        }

        if(dir == 0)
            snakeBody.get(0).y --;
        else if(dir == 1)
            snakeBody.get(0).y ++;
        else if(dir == 2)
            snakeBody.get(0).x ++;
        else if(dir == 3)
            snakeBody.get(0).x --;





        if (snakeBody.get(0).x > width - 1)
            snakeBody.get(0).x = 0;
        if (snakeBody.get(0).x < 0)
            snakeBody.get(0).x = width - 1;
        if (snakeBody.get(0).y > height - 1)
            snakeBody.get(0).y = 0;
        if (snakeBody.get(0).y < 0)
            snakeBody.get(0).y = height - 1;

        if(snakeBody.get(0).x == food.x && snakeBody.get(0).y == food.y) {
            snakeBody.add(new Point(snakeBody.get(size - 1).x, snakeBody.get(size - 1).y));
            size++;

            food = setFood();
        }


        for (int i = 2; i < size; i++) {
            if (snakeBody.get(0).x == snakeBody.get(i).x && snakeBody.get(0).y == snakeBody.get(i).y) {
                running=false;
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }


    private void draw(Graphics g) {
        g.setColor(new Color(245, 135, 0));
        g.fillRect(0,0,width*tile,height*tile);
        g.setColor(new Color(254,179,0));
        g.fillRect(20,20,width*tile-2*tile,width*tile-2*tile);
        g.setColor(Color.WHITE);

        /*for(int i = 0; i < width; i++)
            for(int j = 0; j < height; j++)
                g.drawRect(i*tile, j*tile, tile, tile);*/
        g.setColor(Color.GREEN);
        for(int i = 0; i < size; i++)
            g.fillRect(snakeBody.get(i).x*tile, snakeBody.get(i).y*tile, tile, tile);

        g.setColor(Color.RED);

        g.fillRect(food.x*tile, food.y*tile, tile, tile);

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && dir != 1)
            dir = 0;
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && dir != 0)
            dir = 1;
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && dir != 3)
            dir = 2;
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && dir != 2)
            dir = 3;
        else if(e.getKeyCode()==KeyEvent.VK_SPACE)
            if(space){
                space=false;
            }else {
                space = true;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!space && running){
            move();
            repaint();
        }
        if(space==true && running){

            Graphics g = getGraphics();
            g.setColor(Color.WHITE);
            g.setFont( new Font("Ink Free",Font.BOLD, 40));
            g.drawString("PAUSED",getWidth()/2-width,getHeight()/2-height);
        }
        if(!running){
            Graphics g = getGraphics();
            g.setColor(Color.WHITE);
            g.setFont( new Font("Ink Free",Font.BOLD, 40));
            g.drawString("GAMEOVER",getWidth()/2-3*width,getHeight()/2-3*height);
        }

    }
}