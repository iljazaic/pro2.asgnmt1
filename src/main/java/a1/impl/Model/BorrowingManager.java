package a1.impl.Model;

import java.util.ArrayList;
import java.util.List;

import a1.impl.Model.ModelInterfaces.BorrowingManagerInterface;
import a1.impl.Model.ModelInterfaces.ObservingUser;
import a1.impl.Model.StateModel.BorrowedState;
import a1.impl.Model.StateModel.FreeState;
import a1.impl.Model.StateModel.ReservedState;
import a1.impl.Model.StateModel.StateInterface;
import a1.impl.View.Controllers.UpdatingController;

public class BorrowingManager implements BorrowingManagerInterface {
    private List<Vinyl> vinylList = new ArrayList<Vinyl>();

    private List<ObservingUser> observingUserList = new ArrayList<ObservingUser>();

    public BorrowingManager(UpdatingController updatingController) {
        observingUserList.add(updatingController);// controller always in the user list. ive seen worse imlementations
                                                  // in enterprise code so dont call it 'messy'
    };

    // // // // // // essential // // // // // // //

    public void addVinyl(Vinyl v) {
        this.vinylList.add(v);
    }

    //public List<Vinyl> getVinylListByState(StateInterface state) {
    //    List<Vinyl> toReturn = new ArrayList<Vinyl>();
    //    for (Vinyl vinyl : vinylList) {
    //        if (vinyl.getState().equals(state)) {
    //            toReturn.add(vinyl);
    //        }
    //    }
    //    return toReturn;
    //}
    public List<Vinyl> getVinylList(){
        return this.vinylList;
    }

    public void updateVinylState(String vinylId, StateInterface newState) {
        for (Vinyl v : vinylList) {
            if (v.getId().equals(vinylId)) {
                v.setState(newState);
                notifyAllObservingUsers(vinylId, newState);
                break;
            }
        }
    };

    // this method releases all borrows and reserves if a user disconnects
    public void disconnectUser(String userId) {
        for (Vinyl v : vinylList) {
            if (v.getState().getOwner().equals(userId)) {
                updateVinylState(v.getId(), new FreeState());
            }
        }
    }

    // these 2 methods:
    // find the correct vinyl
    // check if it can be borrowed/reserved based on prebuilt states
    // if true
    // updates - which then notifies observers - the state of the vinyl to
    // borrowed/reserved by the user requesting
    public void attemptBorrowing(String vinylId, String userId) {
        for (Vinyl vinyl : vinylList) {
            if (vinyl.getId().equals(vinylId)) {
                boolean success = vinyl.getState().onBorrowAttempt(userId);
                if (success) {
                    updateVinylState(vinylId, new BorrowedState(userId));
                }
                break;
            }

        }
    }

    public void attemptReservation(String vinylId, String userId) {
        for (Vinyl vinyl : vinylList) {
            if (vinyl.getId().equals(vinylId)) {
                boolean success = vinyl.getState().onReservationAttempt();
                if (success) {
                    updateVinylState(vinylId, new ReservedState(userId));
                }
                break;
            }
        }
    }

    // // // // // // mandatory // // // // // // //

    @Override
    public void addObservngUser(User user) {
        if (observingUserList.contains(user))
            return;// prevent duplication jic
        this.observingUserList.add(user);
    }

    @Override
    public void removeObservingUser(User user) {
        if (observingUserList.contains((ObservingUser)user))
            observingUserList.remove(user);
    }

    @Override
    public void notifyAllObservingUsers(String changedID, StateInterface newState) {
        for (ObservingUser user : observingUserList) {
            user.update(changedID, newState);
        }
    }

}
