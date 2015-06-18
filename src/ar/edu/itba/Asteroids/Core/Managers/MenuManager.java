package ar.edu.itba.Asteroids.Core.Managers;

import java.util.HashMap;
import java.util.Map;

import ar.edu.itba.Asteroids.Core.Constants;
import ar.edu.itba.Asteroids.Core.SpaceShips.SpaceShipCreator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

public class MenuManager {
    private static MenuManager self;
    private MenuTypes state=MenuTypes.Main;
    private GameModeTypes mode;
    private Map<Integer,String> playerNames=new HashMap<Integer,String>();
    private String name="";
    private boolean[] selected = {false, false, false, false, false};
    private int numberOfShipsSelected = 0;
    private int numberofShips = 0;
    private int numberofNamesSaved = 0;
    private boolean fullscreen = false;
    private final int MAXNAMELENGTH = 7;
    
    private MenuManager(){
        
    }
    
    public static MenuManager getInstance(){
        if(self == null){
            self = new MenuManager();
        }
        return self;
    }
    
    /**
     * Resents the menu to the original state which is the Main
     */
    public void reset(){
        this.resetNames();
        this.resetSelected();
        this.state=MenuTypes.Main;
    }
    
    public void keyDown(int keyCode) {
        if(state == MenuTypes.GetPlayerName){
            if(keyCode == Keys.ESCAPE){ //if the user wants to go back to another in the menu
                pressedEscape();
                resetNames();
            }
            if(Generate(keyCode)){
                state=MenuTypes.ChooseSpaceShip;}
        }else if(state == MenuTypes.NormalScreen && keyCode!=Keys.ESCAPE){
            ResolutionManager.getInstance().changeNormalResolution(keyCode - 8, fullscreen);
        }else if(state == MenuTypes.WideScreen && keyCode!=Keys.ESCAPE){
            ResolutionManager.getInstance().changeWideResolution(keyCode - 8, fullscreen);
        }else if(state == MenuTypes.MacScreen && keyCode!=Keys.ESCAPE){
            ResolutionManager.getInstance().changeMacResolution(keyCode - 8, fullscreen);
        }else{
            switch(keyCode){
                case Keys.ESCAPE:
                    pressedEscape();
                    break;
                case Keys.NUM_1:
                    pressed1();
                    break;
                case Keys.NUM_2:
                    pressed2();
                    break;
                case Keys.NUM_3:
                    pressed3();
                    break;
                case Keys.NUM_4:
                    pressed4();
                    break;
                case Keys.NUM_5:
                    pressed5();
                    break;
                default:
                    break;
            }
        }
    }
    
    /**
     * Pressing escape at any given time in the menu will mean a "back". so what this method does is that if the escape is pressed
     * then depending on the state of the menu the player is in, it goes back to the previous state
     */
    private void pressedEscape() {
        switch(state){
            case Main:
                Gdx.app.exit(); //the game ends
            case HighScore:
            case ChangeResolution:
            case Help:
            case NumberOfPlayers:
                this.state=MenuTypes.Main;
                break;
            case WideScreen:
            case NormalScreen:
            case MacScreen:
                this.state=MenuTypes.ChangeResolution;
                break;
            case GameMode2Players:
                this.state=MenuTypes.NumberOfPlayers;
                break;
            case GameMode3Players:
                this.state=MenuTypes.NumberOfPlayers;
                break;
            case GetPlayerName:
                if(mode == GameModeTypes.TwoPlayersA || mode == GameModeTypes.TwoPlayersB){
                    this.state=MenuTypes.GameMode2Players;
                }else if(mode ==GameModeTypes.ThreePlayersA || mode==GameModeTypes.ThreePlayersB){
                    this.state=MenuTypes.GameMode3Players;
                }else
                    this.state=MenuTypes.NumberOfPlayers;
                break;
            case ChooseSpaceShip:
                resetSelected();
                resetNames();
                this.state=MenuTypes.GetPlayerName;
            default:break;
        }
    }
    
    
    private void pressed1() {
        switch(state){
            case Main:
                this.state=MenuTypes.NumberOfPlayers;
                break;
            case ChangeResolution:
                this.state=MenuTypes.WideScreen;
                break;
            case NumberOfPlayers:
                this.state=MenuTypes.GetPlayerName;
                this.mode=GameModeTypes.OnePlayer;
                this.numberofShips=1;
                break;
            case GameMode2Players:
                this.state=MenuTypes.GetPlayerName;
                this.mode=GameModeTypes.TwoPlayersA;
                break;
            case GameMode3Players:
                this.state=MenuTypes.GetPlayerName;
                this.mode=GameModeTypes.ThreePlayersA;
                break;
            case ChooseSpaceShip:
                shipSelected(0);
                break;
            default:break;
        }
    }
    
    private void pressed2() {
        switch(state){
            case Main:
                this.state=MenuTypes.ChangeResolution;
                break;
            case ChangeResolution:
                this.state=MenuTypes.MacScreen;
                break;
            case NumberOfPlayers:
                this.state=MenuTypes.GameMode2Players;
                this.numberofShips=2;
                break;
            case GameMode2Players:
                this.state=MenuTypes.GetPlayerName;
                this.mode=GameModeTypes.TwoPlayersB;
                break;
            case GameMode3Players:
                this.state=MenuTypes.GetPlayerName;
                this.mode=GameModeTypes.ThreePlayersB;
                break;
            case ChooseSpaceShip:
                shipSelected(1);
                break;
            default:break;
        }
    }
    
    private void pressed3() {
        switch(state){
            case Main:
                this.state=MenuTypes.Help;
                break;
            case ChangeResolution:
                this.state=MenuTypes.NormalScreen;
                break;
            case NumberOfPlayers:
                this.state=MenuTypes.GameMode3Players;
                this.numberofShips=3;
                break;
            case ChooseSpaceShip:
                shipSelected(2);
                break;
            default:break;
        }
    }
    
    private void pressed4() {
        switch(state){
            case Main:
                this.state=MenuTypes.HighScore;
                break;
            case ChangeResolution:
                fullscreen=!fullscreen;
                ResolutionManager.getInstance().fullScreen(fullscreen);
                break;
            case ChooseSpaceShip:
                shipSelected(3);
                break;
            default:break;
        }
    }
    
    private void pressed5(){
        switch(state){
            case ChooseSpaceShip:
                shipSelected(4);
                break;
            default:break;
        }
    }
    
    /**
     * This method generates the name of the player whilst the player types in their name.
     * @param KeyCode; the key code pressed by the user
     * @return true if all the names have been saved
     */
    private boolean Generate(int KeyCode){
        String key = Input.Keys.toString(KeyCode);
        if(key.length()==1 && name.length()<MAXNAMELENGTH)
            this.name=name.concat(key);
        else if(KeyCode == Keys.ENTER){
            return setplayerName();
        }else if(KeyCode == Keys.DEL){
            if(!(name.length()==0))
                this.name=name.substring(0, name.length()-1);
        }
        return false;
    }
    
    /**
     * This method sets the name of the player
     * @return if all the names have been saved
     */
    private boolean setplayerName(){
        playerNames.put(numberofNamesSaved, name);
        numberofNamesSaved++;
        name="";
        if(this.numberofShips <= this.numberofNamesSaved)
            return true;
        else
            return false;
        
    }
    
    /**
     * @param player; the player who is typing their name
     * @return the name of the player
     */
    public String getName(int player){
        if(playerNames.get(player) == null){
            return name;
        }
        return playerNames.get(player);
    }
    
    /**
     * @return the number of names that have already been saved
     */
    public int getCompleteName(){
        return this.numberofNamesSaved;
    }
    
    /**
     * This method resets all the names that have been saved
     */
    private void resetNames(){
        for(int i=0;i<this.numberofNamesSaved;i++)
            this.playerNames.put(i, null);
        this.numberofNamesSaved=0;
        this.name="";
    }
    
    /**
     * This method resets all the spaceShips that have been selected
     */
    private void resetSelected() {
        for(int i=0;i<Constants.NUMBER_OF_SHIPS;i++){
            selected[i]=false;		
        }
        this.numberOfShipsSelected=0;
    }
    
    /**
     * Given the spaceShip i this method removes it from the possible spaceShips to choose if it wasn't already selected.
     * If the spaceShip was already selected the method does nothing.
     * If the number of spaceShips is equal to the number of spaceShips that have been selected, the game is ready to begin and the game 
     * is created
     * @param i; the index of the spaceShip in the array of spaceShips in assets
     */
    private void shipSelected(int i) {
        if(this.numberOfShipsSelected<this.numberofShips && !spaceShipSelected(i)){
            selected[i]=true;
            this.numberOfShipsSelected++;
            boolean startsAsteroid = false;
            boolean createAsteroidPlayer = false;
            if(mode == GameModeTypes.ThreePlayersB || mode == GameModeTypes.TwoPlayersB){
                createAsteroidPlayer = true;
                if((GameModeTypes.ThreePlayersB == mode && numberOfShipsSelected == 3 )||( mode == GameModeTypes.TwoPlayersB && numberOfShipsSelected == 2)){
                    startsAsteroid = true;
                }
                
            }
            GameManager.getInstance().addSpaceShip(numberOfShipsSelected, SpaceShipCreator.create(i, numberOfShipsSelected),createAsteroidPlayer,startsAsteroid,getName(numberOfShipsSelected - 1));
            if(this.numberOfShipsSelected==this.numberofShips) //if all the spaceShips have been selected the game is created
                generateGame(this.mode);
        }
    }
    
    /**
     * Creates the game by generating a new Game in the gameManager and gameManagerUI
     * @param mode; the game mode
     */
    private void generateGame(GameModeTypes mode) {
        
        GameManager.getInstance().newGame(mode);
        GameManagerUI.getInstance().newGame(mode);
    }
    
    /**
     * @return the state the menu is in
     */
    public MenuTypes getState(){
        return this.state;
    }
    
    /**
     * 
     * @return the amount of Ships that are going to be used, the number of players in the game
     */
    public int getPlayers() {
        return this.numberofShips;
    }
    
    /**
     * @return the amount of ships that have already been selected
     */
    public int getSpaceShipsSelected(){
        return this.numberOfShipsSelected;
    }
    
    /**
     * 
     * @param s; the index of the spaceShip in the menu
     * @return if the spaceShip s has been selected or not by the user
     */
    public boolean spaceShipSelected(int s) {
        return selected[s];
    }
    
    public void update(float deltaTime) {	
    }
}
