package pl.gr.manager.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gr.manager.Entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
