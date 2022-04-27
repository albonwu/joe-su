import javax.swing.JPanel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.MouseInfo;
import java.awt.PointerInfo;

import java.util.Timer;
import java.util.TimerTask;

import java.util.LinkedList;
import java.util.ListIterator;

public class screen extends JPanel implements ActionListener, KeyListener, MouseListener {
    private int stage; // 0 is for song selection, 1 is for song. 2 is for results screen
    private int pstage; // -1 is for on start up
    private long timeSinceStageChange;
    private sfx soundEffects;
    private song[] SongMaps;
    private Timer timer;
    private LinkedList<Rectangle> updateBlocks;
    private ListIterator<note> notelist;

    static {
        // BufferedImage hitCircle = ImageIO.read(new File(""));

    }

    public screen() {
        setSize(1920, 1080);
        setVisible(true);

        stage = 0;
        timeSinceStageChange = System.currentTimeMillis();
        pstage = -1;
        SongMaps = new song[4];
        SongMaps[0] = new song(
                "file:///Users/albonwu/Documents/cs%203%20project/local%20files/cs3%20game%20thing/songs/Bad%20Apple");
        timer = new Timer();
        updateBlocks = new LinkedList<Rectangle>();
        TimerTask t = new TimerTask() {
            public void run() {
                paintNecessary();
            }
        };
        timer.scheduleAtFixedRate(t, 0, 100);
    }

    public void paintNecessary() {
        while (!updateBlocks.isEmpty())
            repaint(updateBlocks.removeFirst());
    }

    public void paintComponent(Graphics g) {
        // staging(g);
        updateBlocks.add(new Rectangle(0, 0, 1920, 1030));
        long time = System.currentTimeMillis();
        boolean isdone = false;
        /*
         * while(notelist.hasNext() && !isdone)
         * {
         * note n = notelist.next();
         * if(n.time < time)
         * g.fillOval(n.x,n.y,50,50);
         * else
         * {
         * notelist.previous();
         * isdone = true;
         * }
         * }
         */
    }

    public void staging(Graphics g) {
        if (stage != pstage) {
            pstage = stage;
            timeSinceStageChange = System.currentTimeMillis();
            if (stage == 0) // selection
            {

            } else if (stage == 1) // loading
            {

            } else if (stage == 2) // actual thing
            {

            } else if (stage == 3) // pause
            {

            } else if (stage == 4) // failed song
            {

            } else if (stage == 5) // results screen
            {

            }
        } else {

        }
    }

    public void background(Graphics g) {
    }

    public void hitCircle(Graphics g, int x, int y) {
    }

    public void slider(Graphics g) {
    }

    public void spinner(Graphics g) {
    }

    // ActionListener Method
    public void actionPerformed(ActionEvent e) {

    }

    // KeyListener Methods
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && stage != 0) // purely for testing purposes, will be removed in implementation.
        {
            pstage = stage;
            stage--;
        } else if (key == KeyEvent.VK_RIGHT && stage != 5) {
            pstage = stage;
            stage++;
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    // MouseListener Methods
    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }
}
