package Model.ModelInterfaces;

import Model.StateModel.StateInterface;

public interface ObservingUser {
    public void update(String changedId, StateInterface newState);
}
