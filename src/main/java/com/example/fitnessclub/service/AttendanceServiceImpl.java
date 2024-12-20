package com.example.fitnessclub.service;

import com.example.fitnessclub.model.Attendance;
import com.example.fitnessclub.model.ClassDate;
import com.example.fitnessclub.model.Shift;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.repository.AttendanceRepository;
import com.example.fitnessclub.repository.ShiftRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final UserService userService;
    private final ClassService classService;
    private final ShiftRepository shiftRepository;


    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, UserService userService,
                                 ClassService classService, ShiftRepository shiftRepository,
                                 ShiftRepository shiftRepository1) {
        this.attendanceRepository = attendanceRepository;
        this.classService = classService;
        this.userService = userService;
             this.shiftRepository = shiftRepository1;
    }

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
                if(classDate == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Attendance attendance = new Attendance();
        attendanceRepository.save(new Attendance(classDate, members));
        Shift shift = shiftRepository.findShiftByClassDate(classDate);
        shift.setAttendance(true);
        shiftRepository.save(shift);
        return attendanceRepository.save(attendance);
    }
}
