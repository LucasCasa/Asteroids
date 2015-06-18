package ar.edu.itba.Asteroids.Core;

/**
 * a Class with all the Logical constants
 *
 */
public class Constants {

		//Values of the spaceShips
		public static final int[] SHIPS_RADIUS = {25,20,10,30,20};
		public static final int[] SHIPS_MASS = {5,3,1,2,3};
		public static final int[] SHIPS_MAX_VEL = {300,325,500,400,350};
		public static final int[] SHIPS_ACCEL = {450,500,1200,800,600};
		public static final int[] SHIPS_LIVES = {6,5,3,4,5};
		
		public static final int MAX_RADIUS = SHIPS_RADIUS[3];
		public static final int MAX_MASS = SHIPS_MASS[0];
		public static final int MAX_MAX_VEL = SHIPS_MAX_VEL[2];
		public static final int MAX_ACCEL = SHIPS_ACCEL[2];
		public static final int MAX_LIVES = SHIPS_LIVES[0];

		
		public static final int VIRTUAL_WIDTH = 800;
		public static final int VIRTUAL_HEIGHT = 600;
		public static final float ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
		
		//INTERFACE VALUES
		public static final int HORIZONTAL_OFFSET = 25;
		public static final int VERTICAL_OFFSET = 15;
		public static final int ICON_SIZE = 35;
		public static final int TEXT_SEPARATOR = 50;
}
