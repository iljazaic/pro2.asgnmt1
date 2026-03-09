package a1.impl.View.Controllers;

import java.util.List;

import a1.impl.Model.Vinyl;
import a1.impl.Model.ModelInterfaces.ObservingUser;
import a1.impl.Model.StateModel.StateInterface;
import a1.impl.View.ClientManager;

public class UpdatingController implements ObservingUser {

    @Override
    public void update(String changedId, StateInterface newState) {
        String data = "{\"id\":\"%s\",\"state\":\"%s\"}".formatted(changedId, newState);
        ClientManager.broadcast(data, "updateState");
    }

    public void setUserId(String userId) {
        ClientManager.broadcast("\"userId\":\"%s\"".formatted(userId), "setId");
    }

    public void sendWhole(List<Vinyl> wholeList) {
        String jsonString = "[";

        for (Vinyl vinyl : wholeList) {
            jsonString += "{\"name\":\"%s\", \"id\":\"%s\",\"state\":\"%s\"},".formatted(vinyl.getName(), vinyl.getId(),
                    vinyl.getState().getClass().getSimpleName().toString());
        }
        jsonString = jsonString.substring(0,jsonString.length()-1) + "]";

        ClientManager.broadcast(jsonString, "updateList");
    }

}