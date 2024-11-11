package com.example.fitnessclub.service;

import com.example.fitnessclub.model.Attendance;

import java.util.List;

public interface AttendanceService {
    Attendance createAttendance(List<Long> memberIds, Long classDateId);
}
