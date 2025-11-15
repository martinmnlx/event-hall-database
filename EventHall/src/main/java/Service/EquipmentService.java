package com.sam.eventhall.Service;

import com.sam.eventhall.Model.Equipment;
import com.sam.eventhall.Repository.EquipmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Equipment saveEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public void deleteEquipment(Equipment equipment) {
        equipmentRepository.delete(equipment);
    }
}
