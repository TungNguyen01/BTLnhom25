package pl.gr.manager.Entity.POJO;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ReservationModel {

    private Long clientID;
    private Long roomID;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationStartDate;

    public ReservationModel() {
    }

    public ReservationModel(Long clientID, Long roomID, LocalDate reservationStartDate) {
        this.clientID = clientID;
        this.roomID = roomID;
        this.reservationStartDate = reservationStartDate;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public LocalDate getReservationStartDate() {
        return reservationStartDate;
    }

    public void setReservationStartDate(LocalDate reservationStartDate) {
        this.reservationStartDate = reservationStartDate;
    }
}
