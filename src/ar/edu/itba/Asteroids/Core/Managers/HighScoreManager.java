package ar.edu.itba.Asteroids.Core.Managers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeMap;

import org.parse4j.Parse;
import org.parse4j.ParseObject;

import ar.edu.itba.Asteroids.Core.Player;


public class HighScoreManager implements Serializable{
	private static HighScoreManager self;
	
	private TreeMap<Float, String> highscores;
	
	
	private HighScoreManager(){
		highscores = new TreeMap<Float, String>(new ScoreComparator());

	}
	
	public static HighScoreManager getInstance(){
		if(self == null){
			self = new HighScoreManager();
		}
		return self;
	}
	
	public void add(float score, String name){
		highscores.put(score, name);
	}
	
	public void add(Player player){
		highscores.put(player.getScore(), player.getName());
		Parse.initialize("IRqu530fZMPOFpV2QxtWzCfUBouOKbuYivIDHGbc", "tQU5oYuTNi9Eh3dkG0GOBUZIAMvcRe4OvHypZT4n");
	    ParseObject gameScore = new ParseObject("HighScores");
	    gameScore.put("score",player.getScore());
	    gameScore.put("playerName", player.getName());
		gameScore.saveInBackground();

	}
	
	public TreeMap<Float, String> getHighScores(){
		return highscores;
	}
    
    public static HighScoreManager deserialize() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("Score.out");
		ObjectInputStream ois = new ObjectInputStream(fis);
		HighScoreManager s = (HighScoreManager) ois.readObject();
		ois.close();
		return s;
    }
    
    public static void serialize(HighScoreManager s) throws IOException {
        FileOutputStream fos = new FileOutputStream("Score.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s);
        fos.close();
    }
	
	public void writeScores(){
	    try {
	        serialize(this);
	    } catch (IOException e) {
	        return;
	    }
	}
	
	public void loadScores(){
		HighScoreManager s = null;
		try {
		    s = (HighScoreManager) deserialize();
		} catch (ClassNotFoundException | IOException e) {

		} finally{
			if(s != (HighScoreManager)null){
				highscores = new TreeMap<Float, String>(s.getHighScores());
			}
		}
	}
	
	public void update(){
		
	}
	
	
	private class ScoreComparator implements Comparator<Float>, Serializable{

		public int compare(Float o1, Float o2) {
			int diff = (int)(o2.floatValue() - o1.floatValue());
			if (diff == 0) {
				return 1;
			}
			return diff > 0 ? 1 : -1;
		}
	}
	
	private static final long serialVersionUID = 9203780305782683437L;
}