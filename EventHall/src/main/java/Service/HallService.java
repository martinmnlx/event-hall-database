package Service;

import Model.EventHall;
import Repository.EventHallRepository;
import Repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

@Service
public class HallService {

    private final EventHallRepository eventHallRepository;
    private final ReservationRepository reservationRepository;

    public HallService(EventHallRepository eventHallRepository, ReservationRepository reservationRepository) {
        this.eventHallRepository = eventHallRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<EventHall> findAvailableHalls(LocalDate date, LocalTime start, LocalTime end) {
        // Makes a list of all the halls that are booked
        List<Integer> conflictHallIds = reservationRepository.findConflictingHallIds(date, start, end);
        // makes a list of all the halls that are available
        List<EventHall> availableHalls = eventHallRepository.findByStatus(EventHall.HallStatus.Available);
        // make new list to store the available halls
        List<EventHall> unusedHalls = new ArrayList<>();

        // Loop through each available hall
        for (EventHall eventHall : availableHalls) {
            if (!conflictHallIds.contains(eventHall.getHallId())) {
                unusedHalls.add(eventHall);
            }
        }
        return unusedHalls;
    }
    // to have access to all halls (for admin or staff only)
    public List<EventHall> getAllHalls() {
        return eventHallRepository.findAll();
    }
}
