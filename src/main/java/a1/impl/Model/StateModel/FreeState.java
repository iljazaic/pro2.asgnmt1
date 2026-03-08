package a1.impl.Model.StateModel;

public class FreeState implements StateInterface {

    public FreeState() {
    }

    @Override
    public boolean onBorrowAttempt() {
        return true;
    }

    @Override
    public boolean onReservationAttempt() {
        return true;
    }

}
