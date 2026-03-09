package a1.impl.Model.StateModel;

public class ReservedState implements StateInterface {

    private String owner;

    public ReservedState(String ownerId) {
        this.owner = ownerId;
    };

    @Override
    public String getOwner() {
        return this.owner;
    };

    @Override
    public boolean onBorrowAttempt(String userId) {
        System.out.println(userId);
        System.out.println(getOwner());

        return userId.equals(getOwner());// to make sure the user who reserved it rn CAN borrow it
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
