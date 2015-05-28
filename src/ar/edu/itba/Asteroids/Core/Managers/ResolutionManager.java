package ar.edu.itba.Asteroids.Core.Managers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;

public class ResolutionManager {
	
	private static ResolutionManager self;
	private static final int wideScreenWidth = 16;
	private static final int wideScreenHeight = 9;
	private static final int normalScreenWidth = 4;
	private static final int normalScreenHeight = 3;
	private static final int macScreenWidth = 16;
	private static final int macScreenHeight = 10;
	private ArrayList<DisplayMode> wideScreen; // 16x9
	private ArrayList<DisplayMode> normalScreen; // 4x3
	private ArrayList<DisplayMode> macScreen; //16x10
	
	private ResolutionManager(){
		DisplayMode[] dm = Gdx.graphics.getDisplayModes();
		wideScreen = generateScreenRes(dm, wideScreenWidth, wideScreenHeight);
		normalScreen = generateScreenRes(dm, normalScreenWidth, normalScreenHeight);
		macScreen = generateScreenRes(dm, macScreenWidth, macScreenHeight);
	}
	
	public static ResolutionManager getInstance(){
		if(self == null){
			self = new ResolutionManager();
		}
		return self;
	}
	private ArrayList<DisplayMode> generateScreenRes(DisplayMode[] dm,float w, float h) {
		TreeSet<DisplayMode> tree = new TreeSet<DisplayMode>(new Comparator<DisplayMode>() {
			public int compare( DisplayMode a, DisplayMode b){
			 return  a.width * a.height - b.width * b.height;
			}
		}); 
		for(DisplayMode disp: dm){
			if((float)disp.width / (float)disp.height == w / h)
				tree.add(disp);
		}
		return new ArrayList<DisplayMode>(tree);
	}
	public ArrayList<DisplayMode> getWideScreenRes(){
		return wideScreen;
	}
	public ArrayList<DisplayMode> getNormalScreenRes(){
		return normalScreen;
	}
	public ArrayList<DisplayMode> getMacScreenRes(){
		return macScreen;
	}
	
	public void changeWideResolution(int keyCode, boolean fullscreen){
		if(keyCode >= 0 && keyCode < wideScreen.size()){
			Gdx.graphics.setDisplayMode(wideScreen.get(keyCode).width, wideScreen.get(keyCode).height, fullscreen);
		}
	}
	public void changeNormalResolution(int keyCode, boolean fullscreen){
		if(keyCode >= 0 && keyCode < normalScreen.size()){
			Gdx.graphics.setDisplayMode(normalScreen.get(keyCode).width, normalScreen.get(keyCode).height, fullscreen);
		}
	}

	public void changeMacResolution(int keyCode, boolean fullscreen) {
		if(keyCode >= 0 && keyCode < macScreen.size()){
			Gdx.graphics.setDisplayMode(macScreen.get(keyCode).width, macScreen.get(keyCode).height, fullscreen);
		}
		
	}
}
