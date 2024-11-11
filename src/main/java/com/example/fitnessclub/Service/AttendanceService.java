package com.example.fitnessclub.Service;

import java.util.List;

public interface AttendanceService {
    void createAttendance(List<Long> memberIds, Long classDateId);
}
