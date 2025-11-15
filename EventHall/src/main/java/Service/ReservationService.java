package Service;

import Model.*;
import Repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationService {


    private final ReservationRepository reservationRepository;
    private final EquipmentRepository equipmentRepository;
    private final EquipmentAllocationRepository equipmentAllocationRepository;
    private final EventHallRepository eventHallRepository;
    private final HallService hallService;

    public ReservationService(ReservationRepository reservationRepository, EquipmentRepository equipmentRepository, EquipmentAllocationRepository equipmentAllocationRepository, EventHallRepository eventHallRepository, HallService hallService) {
        this.reservationRepository = reservationRepository;
        this.equipmentRepository = equipmentRepository;
        this.equipmentAllocationRepository = equipmentAllocationRepository;
        this.eventHallRepository = eventHallRepository;
        this.hallService = hallService;
    }

    // this is for creating a new booking for reservation, including the hall and the equipment
    public Reservation createReservation(Reservation reservation, List<EquipmentAllocation> equipmentAllocations) {

        // gets the list of available halls
        List<EventHall> availableEventHalls = hallService.findAvailableHalls(reservation.getStartsOn(), reservation.getEndsOn());

        // checks if the requested hallId is available for booking
        boolean isAvailable = false;
        for (EventHall eventHall : availableEventHalls) {
            // checks if current hallID matches reservation hallID
            if (eventHall.getHallId().equals(reservation.getHallId())) {
                isAvailable = true;
                break;
            }
        }
        if (!isAvailable) {
            // basically immediately stops the method
            throw new IllegalArgumentException("Hall is not available at the requested time.");
        }

        // saves the reservation record to get its new ID
        Reservation savedReservation = reservationRepository.save(reservation);

        for (EquipmentAllocation equipmentAllocation : equipmentAllocations) {

            // checks for equipment, however if not found it stops
            Equipment equipment = equipmentRepository.findById(equipmentAllocation.getEquipmentId()).orElseThrow(() -> new IllegalArgumentException("Equipment not found"));

            // calculates the quantity being reserved
            Integer quantityReserved = equipmentRepository.getTotalReservedQuantityForEquipmentId(equipment.getEquipmentId());

            //gets available stock
            Integer availableStock = equipment.getTotalQuantity() - quantityReserved;

            // when there is a lack of available stock
            if (equipmentAllocation.getQuantity() > availableStock) {
                throw new IllegalStateException("Inventory Error: Unable to book " + equipmentAllocation.getQuantity() + " stock of " + equipment.getEquipmentName() + ". Only " + availableStock + " available.");
            }
            equipmentAllocation.setReservationId(savedReservation.getReservationId());
            equipmentAllocationRepository.save(equipmentAllocation);
        }
        return savedReservation;
    }
}