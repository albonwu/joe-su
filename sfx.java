import javafx.embed.swing.JFXPanel;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class sfx
{
    static{
        JFXPanel fxpanel = new JFXPanel();
    }
    static String filepath;
    String status;
    /*
    static AudioClip drumHit = new AudioClip("");
    static AudioClip slider = new AudioClip("");
    static AudioClip buttonClick = new AudioClip("");
    static AudioClip missSound = new AudioClip("");
    */


    static Media song = new Media("file:///C:/Users/achau/Desktop/Code%20Projects/cs3%20game%20thing/sound%20files/snow.mp3");
    static MediaPlayer music = new MediaPlayer(song);
    static Long overallVolume;
    static Long songVolume;
    static Long sfxvolume;

    public sfx()
    {
//        playSong();
    }
    public static void playSong(){music.play();}
/*    public void pauseSong(){music.pause();}
    public void resumeSong(){music.play();}
    
    public void onHit(){drumHit.play();}
    public void onSlider(){slider.play();}
    public void onMiss(){missSound.play();}*/
}