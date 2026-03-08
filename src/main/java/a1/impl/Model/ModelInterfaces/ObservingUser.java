package a1.impl.Model.ModelInterfaces;

import a1.impl.Model.StateModel.StateInterface;

public interface ObservingUser {
    public void update(String changedId, StateInterface newState);
}
