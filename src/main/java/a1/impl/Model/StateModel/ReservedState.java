package a1.impl.Model.StateModel;

public class ReservedState implements StateInterface {
    public ReservedState(){};



    @Override
    public boolean onBorrowAttempt() {
        return false;//error
    }


    @Override
    public boolean onReservationAttempt(){
        return true;
    }
}
