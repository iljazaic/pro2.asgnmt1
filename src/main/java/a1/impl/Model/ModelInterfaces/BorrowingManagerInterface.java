package a1.impl.Model.ModelInterfaces;

import a1.impl.Model.User;
import a1.impl.Model.StateModel.StateInterface;

public interface BorrowingManagerInterface {
    public void addObservngUser(User user);
    public void removeObservingUser(User user);
    public void notifyAllObservingUsers(String changedId, StateInterface newState);
}
