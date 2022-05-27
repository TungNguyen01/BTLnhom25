package pl.gr.manager.Entity;

import pl.gr.manager.Entity.POJO.RoomType;

import javax.persistence.*;

@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    @GeneratedValue
    @Column(name = "ROOM_ID")
    private Long id;
    @Column(name = "ROOM_NUMBER")
    private Integer roomNumber;
    @Column(name = "ROOM_TYPE")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    @Column(name = "OCCUPIED")
    private boolean isOccupied;

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
