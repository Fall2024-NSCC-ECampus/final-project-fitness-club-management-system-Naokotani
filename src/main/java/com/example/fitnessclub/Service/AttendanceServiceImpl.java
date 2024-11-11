package com.example.fitnessclub.Service;

import com.example.fitnessclub.model.Attendance;
import com.example.fitnessclub.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final UserService userService;
    private final ClassService classService;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, UserService userService, ClassService classService) {
        this.attendanceRepository = attendanceRepository;
        this.classService = classService;
        this.userService = userService;
    }

    @Override
    public void createAttendance(List<Long> memberIds, Long classDateId) {
        attendanceRepository.save(new Attendance(classService.findClassDateById(classDateId),
                memberIds.stream().map(userService::findUserById).collect(Collectors.toSet())));
    }
}
