package pl.gr.manager.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gr.manager.Entity.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
