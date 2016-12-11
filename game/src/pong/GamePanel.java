package pong;

import java.awt.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener 
{
Player1 player1 = new Player1();
Ball ball = new Ball();
Player2 player2 = new Player2();
public GamePanel() 
{
  Timer time = new Timer(40, this);
  time.start();
  //this.addKeyListener(this);
  Server2 server = new Server2(5321);
  server.start();
  
  this.setFocusable(true);
}
private void update() 
{
        player1.update();
        ball.update();
        player2.update();
        ball.chkCollision(player1);
        ball.chkCollision(player2);
        ball.hitWall();
        Toolkit.getDefaultToolkit().sync();
}
public void paint(Graphics g) 
{
g.setColor(Color.green);
g.fillRect(0, 0, Game.window_width, Game.window_height);

        player1.paint(g);
        ball.paint(g);
        player2.paint(g);

        g.setColor(Color.black);
        g.drawLine(0, 30, Game.window_width, 30);
        g.drawLine(Game.window_width / 2, 30, Game.window_width / 2, Game.window_height);
        g.drawOval((Game.window_width / 2) - 30, Game.window_height / 2 - 30, 60, 60);
}
   public Ball getBall() {
        return ball;
    }

    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    public void keyPressed1(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player1.setYVel(-10);
            if (player1.getY() < 30) {
                player1.setYVel(0);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player1.setYVel(10);
            if (player1.getY() + 40 > Game.window_height) {
                player1.setYVel(0);
            }
        }
    }

    public void keyReleased1(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S) {
            player1.setYVel(0);

        }
    }
   public void keyPressed2(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player2.setYVel(-10);
            if (player2.getY() < 30) {
                player2.setYVel(0);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player2.setYVel(10);
            if (player2.getY() + 40 > Game.window_height) {
                player2.setYVel(0);
            }
        }
    }

    public void keyReleased2(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
            player2.setYVel(0);

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
         
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed1(e);
        keyPressed2(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
         keyReleased1(e);
         keyReleased2(e);
    }
   
    // ========================================================================
    
    private class Server2 
    {
        private final int port;
        private boolean running;
        private final ExecutorService threadPool;
        private Thread serverThread;
        
        public Server2(int port)
        {
            this.port = port;
            threadPool = Executors.newFixedThreadPool(10,new ThreadFactory()
            {
                private final AtomicInteger instanceCount = new AtomicInteger();
                @Override
                public Thread newThread(Runnable r)
                {
                    Thread t = Executors.defaultThreadFactory().newThread(r);
                    t.setDaemon(true);
                    t.setName("HANDLER_" + instanceCount.getAndIncrement());
                    return t;
                }
            });        
        }
        
        public void start()
        {
            running = true;
            System.out.println("------------- Starting Server Up -------------");
            serverThread = new Thread(() ->
            {
                try
                {
                    ServerSocket server = new ServerSocket(port);
                    while ( running)
                    {
                        final Socket client = server.accept();
                        threadPool.submit(() ->
                        {
                            try
                            {
                                try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream())))
                                {
                                    String line;
                                    while((line = in.readLine()) != null)
                                    {
                                        //System.out.println(Thread.currentThread().getName() + ": " + line);
                                        double _y = Double.parseDouble(line);
                                        int y = (int) _y;
                                        if (y < 0) {
                                          player1.setYVel(10);
                                          if (player1.getY() == Game.window_height) {
                                              player1.setYVel(0);
                                          }
                                        } else if (y > 0) {
                                            player1.setYVel(-10);
                                              if (player1.getY() == 0) {
                                                  player1.setYVel(0);
                                              }
                                        }
                                        System.out.println(y);
                                    }
                                }
                                client.close();
                            }
                            catch (IOException ex)
                            {
                                Logger.getLogger(Server2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
                    server.close();
                }
                catch (IOException ex)
                {
                    Logger.getLogger(Server2.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            serverThread.setName("LISTENER");
            serverThread.start();
        }

        public void stop()
        {
            running = false;
            if ( serverThread != null)
            {
                serverThread.interrupt();
            }
            threadPool.shutdown();
            serverThread = null;
            
        } 
    }
}
