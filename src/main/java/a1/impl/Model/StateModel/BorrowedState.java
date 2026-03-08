package a1.impl.Model.StateModel;

public class BorrowedState implements StateInterface {
    
    public BorrowedState(){}


    @Override
    public boolean onBorrowAttempt(){
        return false;
    }

    public boolean onReservationAttempt(){
        return true;
    }


}
