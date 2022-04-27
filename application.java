import javax.swing.JFrame;
import java.io.IOException;

public class application {
    public static void main(String...args) throws IOException
    {
        JFrame j = new JFrame();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(1920,1080);
        screen s = new screen();
        j.addKeyListener(s);
        j.addMouseListener(s);
        j.setContentPane(s);
        j.setVisible(true);
    }
}