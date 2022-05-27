package pl.gr.manager.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @GeneratedValue
    @Column(name = "RESERVATION_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @OneToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @Column(name = "RESERVATION_TIME")
    private LocalDate reservationStartDate;

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getReservationStartDate() {
        return reservationStartDate;
    }

    public void setReservationStartDate(LocalDate reservationTime) {
        this.reservationStartDate = reservationTime;
    }
}
