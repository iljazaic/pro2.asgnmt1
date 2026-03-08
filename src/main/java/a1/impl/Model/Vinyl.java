package Model;

import java.util.Queue;
import java.util.LinkedList;
import java.util.UUID;

import Model.ModelInterfaces.BorrowableMaterialInterface;
import Model.StateModel.FreeState;
import Model.StateModel.StateInterface;

public class Vinyl implements BorrowableMaterialInterface {
    private String name;
    private String id;

    private String borrower_id = null;
    private String reserver_id = null;

    private StateInterface currentState = new FreeState();

    public Vinyl(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }



    public boolean attemptBorrow(String userId){
        return this.getState().onBorrowAttempt();
    }

    public boolean attemptReservation(String userId){
        return this.getState().onReservationAttempt();
    }


    


    @Override
    public void updateState(StateInterface newState) {
        this.currentState = newState;
    }

    public StateInterface getState() {
        return currentState;
    }

    public void setState(StateInterface state) {
        this.currentState = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBorrower_id() {
        return borrower_id;
    }

    public void setBorrower_id(String borrower_id) {
        this.borrower_id = borrower_id;
    }

    public String getReserver_id() {
        return reserver_id;
    }

    public void setReserver_id(String reserver_id) {
        this.reserver_id = reserver_id;
    }

}