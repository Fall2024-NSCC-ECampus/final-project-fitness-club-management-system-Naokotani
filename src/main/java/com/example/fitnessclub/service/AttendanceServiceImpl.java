package com.example.fitnessclub.service;

import com.example.fitnessclub.model.Attendance;
import com.example.fitnessclub.model.ClassDate;
import com.example.fitnessclub.model.Shift;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.repository.AttendanceRepository;
import com.example.fitnessclub.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final UserService userService;
    private final ClassService classService;
    private final ShiftService shiftService;


    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, UserService userService, ClassService classService, ShiftRepository shiftRepository, ShiftService shiftService) {
        this.attendanceRepository = attendanceRepository;
        this.classService = classService;
        this.userService = userService;
        this.shiftService = shiftService;
    }

    //TODO improve error handling of unfound IDs

    /**
     * Creates an attendance record
     * @param memberIds The {@link User} to be added to the attendance record
     * @param classDateId The {@link ClassDate} to be added to the attendance record.
     * @return The saved attendance record.
     */
    @Override
    public Attendance createAttendance(List<Long> memberIds, Long classDateId) {
        Set<User> members = memberIds.stream().map(userService::findUserById).collect(Collectors.toSet());
        ClassDate classDate = classService.findClassDateById(classDateId);
        Attendance attendance = attendanceRepository.save(new Attendance(classDate, members));
        Shift shift = shiftService.updateShift(classDate);
        return attendanceRepository.save(attendance);
    }
}
