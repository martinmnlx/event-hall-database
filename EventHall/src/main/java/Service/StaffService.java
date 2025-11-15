package com.sam.eventhall.Service;

import com.sam.eventhall.Model.Staff;
import com.sam.eventhall.Repository.StaffRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public void deleteStaff(Staff staff) {
        staffRepository.delete(staff);
    }
}
