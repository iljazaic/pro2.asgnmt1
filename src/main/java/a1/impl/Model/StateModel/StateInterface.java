package a1.impl.Model.StateModel;

public interface StateInterface {
    public boolean onBorrowAttempt(String userId);
    public boolean onReservationAttempt();
    public String getOwner();
    
}
