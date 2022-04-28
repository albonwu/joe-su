import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class song {
    public Media song;
    public beatmap map;
    public MediaPlayer mp;
    public song(String filepath)
    {
        song = new Media(filepath + "/song.mp3");
        map = new beatmap((filepath + "/map.bm").substring(8));
        mp = new MediaPlayer(song);
    }
    public void playSong()
    {
        mp.play();
    }
    public song()
    {
        
    }
}

class beatmap {
    private BufferedReader reader;
    public LinkedList<note> notes;
    public beatmap(String path){
        notes = new LinkedList<note>();
        try{
            reader = new BufferedReader(new FileReader(path.replaceAll("/","\\\\").replaceAll("%20"," ")));
            while(!reader.readLine().equals("[HitObjects]")){}
            String line = reader.readLine();
            do
            {
                System.out.println(line);
                String[] ins = line.split(",");
                note n;
                if(ins[3].equals("1") || ins[3].equals("5"))
                    n = new note(Integer.parseInt(ins[0]),Integer.parseInt(ins[1]),Long.parseLong(ins[2]),Integer.parseInt(ins[3]),Integer.parseInt(ins[4]));
                else
                {
                    if(ins[3].equals("8")||ins[3].equals("12"))
                    {
                        n = new spinner(Integer.parseInt(ins[0]),Integer.parseInt(ins[1]),Long.parseLong(ins[2]),Integer.parseInt(ins[3]),Integer.parseInt(ins[4]),Long.parseLong(ins[5]));
                    }
                    else
                    {
                        char cType = ins[5].charAt(0);
                        String[] splits = ins[5].split("\\|");
                        for(String s : splits)
                            System.out.println(s);
                        int[][] cords = new int[splits.length-1][2];
                        for(int i = 0; i < cords.length; ++i)
                        {
                            String[] cds = splits[i+1].split(":");
                            System.out.println(cds[0]);
                            cords[i][0] = Integer.parseInt(cds[0]);
                            cords[i][1] = Integer.parseInt(cds[1]);
                        }
                        n = new curve(Integer.parseInt(ins[0]),Integer.parseInt(ins[1]),Long.parseLong(ins[2]),Integer.parseInt(ins[3]),Integer.parseInt(ins[4]),cType,cords);
                    }
                }
                line = reader.readLine();
                notes.add(n);
            } while(reader.ready());
        } catch (IOException e){System.out.println("asldhfgiawptogah");}
    }
}

class note {
    public int type;
    public int x;
    public int y;
    public long time;
    public int soundType;
    public boolean newCombo;
    
    public note(int xcord, int ycord, long time, int beattype, int sound)
    {
        type = beattype % 4;
        this.time = time;
        x = xcord;
        y = ycord;
        newCombo = beattype == 12 || beattype == 5 || beattype == 6;
        soundType = sound;
    }
}

class curve extends note {
    public char curveType;
    public int[][] cords;
    public curve(int xcord, int ycord, long time, int beattype, int sound, char curve, int[][] cords)
    {
        super(xcord, ycord, time, beattype, sound);
        curveType = curve;
        this.cords = cords;
    }
}
class spinner extends note {
    public long endTime;
    public spinner(int xcord, int ycord, long time, int beattype, int sound, Long end)
    {
        super(xcord, ycord, time, beattype, sound);
        endTime = end;
    }
}