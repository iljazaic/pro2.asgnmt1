package a1.impl.Model;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

import a1.impl.Model.ModelInterfaces.ObservingUser;
import a1.impl.Model.StateModel.StateInterface;

public class User implements ObservingUser {
    private String userId;
    private String name;

    private List<Vinyl> loadedVinylList = new ArrayList<Vinyl>();


    public User(String name){
        this.name=name;
        this.userId = UUID.randomUUID().toString();
    }

    //essential stuff
    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.userId;
    }

    //mandatory stuff
    @Override
    public void update(String changedId, StateInterface newState) {
        for (Vinyl vinyl : loadedVinylList) {
            if (vinyl.getId().equals(changedId)) {
                vinyl.setState(newState);
                //update fr
                //update vm
            }
        }
    }
}
