package a1.impl.Model.StateModel;

public class BorrowedState implements StateInterface {

    private String owner;

    public BorrowedState(String ownerId) {
        this.owner = ownerId;
    }

    @Override
    public String getOwner() {
        return this.owner;
    };

    @Override
    public boolean onBorrowAttempt(String userId) {
        return false;
    }


    public boolean onReservationAttempt() {
        return false;
    }

}
