package pl.gr.manager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gr.manager.Entity.POJO.ReservationModel;
import pl.gr.manager.Entity.Reservation;
import pl.gr.manager.Entity.Room;
import pl.gr.manager.Repository.ClientRepository;
import pl.gr.manager.Repository.ReservationRepository;
import pl.gr.manager.Repository.RoomRepository;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    final ReservationRepository reservationRepository;
    final ClientRepository clientRepository;
    final RoomRepository roomRepository;

    public ReservationController(ReservationRepository reservationRepository
            , ClientRepository clientRepository
            , RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public String getReservationIndex(Model model){
        model.addAttribute("reservationModel", new ReservationModel());
        model.addAttribute("rooms", roomRepository.findAll());
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservation";
    }

    @PostMapping
    public String addReservation(@ModelAttribute ReservationModel reservationModel){
        Reservation reservation = new Reservation();
        Room room = roomRepository.findById(reservationModel.getRoomID())
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation client id "));
        reservation.setClient(clientRepository.findById(reservationModel.getClientID())
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation client id "
                        + reservationModel.getClientID())));
        reservation.setRoom(room);
        reservation.setReservationStartDate(reservationModel.getReservationStartDate());
        reservationRepository.save(reservation);

        room.setOccupied(true);
        roomRepository.save(room);
        return "redirect:/reservation";
    }

    @GetMapping("/delete/{id}")
    public String deleteReservation(@ModelAttribute("id") Long id){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id:" + id));
        Room room = reservation.getRoom();
        room.setOccupied(false);
        roomRepository.save(room);
        reservationRepository.delete(reservation);
        return "redirect:/reservation";
    }

    @GetMapping("/edit/{id}")
    public String editReservation(@ModelAttribute("id") Long id, Model model){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id:" + id));
        ReservationModel reservationModel = new ReservationModel(reservation.getClient().getId()
                , reservation.getRoom().getId(), reservation.getReservationStartDate());
        model.addAttribute("reservation", reservation);
        model.addAttribute("reservationModel", reservationModel);
        model.addAttribute("rooms", roomRepository.findAll());
        Room room = reservation.getRoom();
        room.setOccupied(false);
        roomRepository.save(room);
        return "editReservation";
    }

    @PostMapping("/edit/{id}")
    public String updateRoom(@ModelAttribute("id") Long id, @ModelAttribute ReservationModel reservationModel){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation Id:" + id));
        Room room = roomRepository.findById(reservationModel.getRoomID())
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation client id "));
        reservation.setClient(clientRepository.findById(reservationModel.getClientID())
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation client id "
                        + reservationModel.getClientID())));
        room.setOccupied(true);
        reservation.setRoom(room);
        reservation.setReservationStartDate(reservationModel.getReservationStartDate());
        reservationRepository.save(reservation);
        return "redirect:/reservation";
    }

}
