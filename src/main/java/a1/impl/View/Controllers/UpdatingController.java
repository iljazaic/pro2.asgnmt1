package a1.impl.View.Controllers;

import a1.impl.Model.ModelInterfaces.ObservingUser;
import a1.impl.Model.StateModel.StateInterface;
import a1.impl.View.ClientManager;

public class UpdatingController implements ObservingUser {

    @Override
    public void update(String changedId, StateInterface newState) {
        String data = "{\"id\":\"%s\",\"state\":\"%s\"}".formatted(changedId, newState);
        ClientManager.broadcast(data);
    }
}