package IGDB.stores;

import java.util.UUID;

public class GameStore {
	
	String gameName;
    UUID gameUUID;
    
    public String getGameName(){
    return gameName;
    }
    public UUID getGameUUID(){
    return gameUUID;
    }
    
    public void setGameName(String gameName){
    this.gameName=gameName;
    }
    public void setGameUUID(UUID gameUUID){
    this.gameUUID=gameUUID;
    }
    
}
