package com.example.fitnessclub.fitnessclub;

import com.example.fitnessclub.service.UserRegistrationService;
import com.example.fitnessclub.service.UserService;
import com.example.fitnessclub.model.*;
import com.example.fitnessclub.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class DatabaseInsertTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private ClassDateRepository classDateRepository;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private UserRegistrationService userRegistrationService;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {}

    @Test
    public void insert(){
        User admin = new User("admin@fitnessclub.com", "Admin", "User", "admin123");
        User trainer = new User("trainer@fitnessclub.com", "John", "Doe", "trainer123");
        User member = new User("member@fitnessclub.com", "Jane", "Smith", "member123");
        User member1 = new User("member1@fitnessclub.com", "Jane", "Smith", "member123");
        User member2 = new User("member2@fitnessclub.com", "John", "Doe", "member234");
        User member3 = new User("member3@fitnessclub.com", "Alice", "Johnson", "member345");
        User member4 = new User("member4@fitnessclub.com", "Bob", "Brown", "member456");
        User member5 = new User("member5@fitnessclub.com", "Charlie", "Davis", "member567");
        User all = new User("all@fitnessclub.com", "Jane", "Smith", "member123");

        admin.addRole(UserRoles.ADMIN);
        trainer.addRole(UserRoles.TRAINER);
        member.addRole(UserRoles.MEMBER);
        member1.addRole(UserRoles.MEMBER);
        member2.addRole(UserRoles.MEMBER);
        member3.addRole(UserRoles.MEMBER);
        member4.addRole(UserRoles.MEMBER);
        member5.addRole(UserRoles.MEMBER);
        all.addRole(UserRoles.MEMBER);
        all.addRole(UserRoles.TRAINER);
        all.addRole(UserRoles.ADMIN );

        userRegistrationService.registerUser(admin);
        userRegistrationService.registerUser(member1);
        userRegistrationService.registerUser(member2);
        userRegistrationService.registerUser(member3);
        userRegistrationService.registerUser(member4);
        userRegistrationService.registerUser(member5);
        userRegistrationService.registerUser(all);
        userRegistrationService.registerUser(trainer);

        ClassDetails yogaClass = new ClassDetails("Yoga");
        ClassDetails pilatesClass = new ClassDetails("Pilates");
        ClassDetails spinClass = new ClassDetails("Spin");

        yogaClass=classRepository.save(yogaClass);
        pilatesClass=classRepository.save(pilatesClass);
        spinClass=classRepository.save(spinClass);

        // Create 3 ClassDate entities for each ClassDetails and link them
        Set<ClassDate> yogaDates = new HashSet<>();
        yogaDates.add(new ClassDate(yogaClass, LocalDate.of(2024, 11, 15), LocalTime.of(8, 0), LocalTime.of(9, 0)));
        yogaDates.add(new ClassDate(yogaClass, LocalDate.of(2024, 11, 16), LocalTime.of(8, 0), LocalTime.of(9, 0)));
        yogaDates.add(new ClassDate(yogaClass, LocalDate.of(2024, 11, 17), LocalTime.of(8, 0), LocalTime.of(9, 0)));

        Set<ClassDate> pilatesDates = new HashSet<>();
        pilatesDates.add(new ClassDate(pilatesClass, LocalDate.of(2024, 11, 15), LocalTime.of(9, 0), LocalTime.of(10, 0)));
        pilatesDates.add(new ClassDate(pilatesClass, LocalDate.of(2024, 11, 16), LocalTime.of(9, 0), LocalTime.of(10, 0)));
        pilatesDates.add(new ClassDate(pilatesClass, LocalDate.of(2024, 11, 17), LocalTime.of(9, 0), LocalTime.of(10, 0)));

        Set<ClassDate> spinDates = new HashSet<>();
        spinDates.add(new ClassDate(spinClass, LocalDate.of(2024, 11, 15), LocalTime.of(10, 0), LocalTime.of(11, 0)));
        spinDates.add(new ClassDate(spinClass, LocalDate.of(2024, 11, 16), LocalTime.of(10, 0), LocalTime.of(11, 0)));
        spinDates.add(new ClassDate(spinClass, LocalDate.of(2024, 11, 17), LocalTime.of(10, 0), LocalTime.of(11, 0)));

        classDateRepository.saveAll(yogaDates);
        classDateRepository.saveAll(pilatesDates);
        classDateRepository.saveAll(spinDates);

        List<ClassDate> dates =classDateRepository.findAll();

        Attendance attendance1 = new Attendance(dates.getFirst(), (Set<User>) userRepository.findAll());
        Attendance attendance2 = new Attendance(dates.get(1), (Set<User>) userRepository.findAll());
        Attendance attendance3 = new Attendance(dates.get(2), (Set<User>) userRepository.findAll());

        attendanceRepository.save(attendance1);
        attendanceRepository.save(attendance2);
        attendanceRepository.save(attendance3);

        Shift shift1 = new Shift(yogaClass, userService.findTrainers().getFirst(), dates.getFirst());
        Shift shift2 = new Shift(pilatesClass, userService.findTrainers().getFirst(), dates.get(1));
        Shift shift3 = new Shift(spinClass, userService.findTrainers().getFirst(), dates.get(2));

        shiftRepository.save(shift1);
        shiftRepository.save(shift2);
        shiftRepository.save(shift3);

    }
}
