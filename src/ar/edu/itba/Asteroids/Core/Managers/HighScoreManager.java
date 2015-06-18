package ar.edu.itba.Asteroids.Core.Managers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeMap;

import ar.edu.itba.Asteroids.Core.Player;


public class HighScoreManager{
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
	}
	
	public TreeMap<Float, String> getHighScores(){
		return highscores;
	}
    
    public static TreeMap<Float, String> deserialize() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("Score.out");
		ObjectInputStream ois = new ObjectInputStream(fis);
		@SuppressWarnings("unchecked")
		TreeMap<Float, String> s = (TreeMap<Float, String>) ois.readObject();
		ois.close();
		return s;
    }
    
    public static void serialize(TreeMap<Float, String> s) throws IOException {
        FileOutputStream fos = new FileOutputStream("Score.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s);
        fos.close();
    }
	
	public void writeScores(){
	    try {
	        serialize(this.highscores);
	    } catch (IOException e) {
	        return;
	    }
	}
	
	public void loadScores(){
		TreeMap<Float, String> s = null;
		try {
		    s = (TreeMap<Float, String>) deserialize();
		} catch (ClassNotFoundException | IOException e) {

		} finally{
			if(s != (TreeMap<Float, String>)null){
				highscores = new TreeMap<Float, String>(s);
			}
		}
	}
	
	public void update(){
		
	}
	
	
	private class ScoreComparator implements Comparator<Float>, Serializable{

		private static final long serialVersionUID = 1L;

		public int compare(Float o1, Float o2) {
			int diff = (int)(o2.floatValue() - o1.floatValue());
			if (diff == 0) {
				return 1;
			}
			return diff > 0 ? 1 : -1;
		}
	}
}