package a1.impl.Model.StateModel;

public class FreeState implements StateInterface {

    public FreeState() {
    }

    @Override
    public String getOwner() {
        return null;
    }

    @Override
    public boolean onBorrowAttempt(String userId) {
        return true;
    }

    @Override
    public boolean onRemoveAttempt() {
        return true;
    }

    @Override
    public boolean onReservationAttempt() {
        return true;
    }

}
