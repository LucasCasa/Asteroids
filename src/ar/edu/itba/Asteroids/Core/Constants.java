package ar.edu.itba.Asteroids.Core;

public class Constants {

	//Valores de las naves
		public static final int[] SHIPS_RADIUS = {25,20,10,30,20};
		public static final int[] SHIPS_MASS = {5,3,1,2,3};
		public static final int[] SHIPS_MAX_VEL = {300,325,500,400,350};
		public static final int[] SHIPS_ACCEL = {300,325,1200,400,350};
		public static final int[] SHIPS_LIVES = {6,5,3,4,5};
		
		public static final int VIRTUAL_WIDTH = 800;
		public static final int VIRTUAL_HEIGHT = 600;
		public static final float ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
		
		//MEJORADORES DE INTERFAZ
		public static final int HORIZONTAL_OFFSET = 25;
		public static final int VERTICAL_OFFSET = 15;
		public static final int ICON_SIZE = 35;
		public static final int TEXT_SEPARATOR = 50;
}
