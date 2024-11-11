package com.example.fitnessclub.shell;

import com.example.fitnessclub.service.UserRegistrationService;
import com.example.fitnessclub.service.UserService;
import com.example.fitnessclub.model.*;
import com.example.fitnessclub.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SeedData {
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;
    private final ClassRepository classRepository;
    private final ClassDateRepository classDateRepository;
    private final ShiftRepository shiftRepository;
    private final UserRegistrationService userRegistrationService;
    private final UserService userService;

    public SeedData(UserRepository userRepository, AttendanceRepository attendanceRepository, ClassRepository classRepository, ClassDateRepository classDateRepository, ShiftRepository shiftRepository, UserRegistrationService userRegistrationService, UserService userService) {
        this.userRepository = userRepository;
        this.attendanceRepository = attendanceRepository;
        this.classRepository = classRepository;
        this.classDateRepository = classDateRepository;
        this.shiftRepository = shiftRepository;
        this.userRegistrationService = userRegistrationService;
        this.userService = userService;
    }

    public void insert(){
        User admin = new User("Admin", "User","admin@fitnessclub.com", "admin123");
        User trainer = new User( "John", "Doe","trainer@fitnessclub.com", "trainer123");
        User member = new User( "Jane", "Smith","member@fitnessclub.com", "member123");
        User member1 = new User( "Jane", "Smith","member1@fitnessclub.com", "member123");
        User member2 = new User( "John", "Doe", "member2@fitnessclub.com","member234");
        User member3 = new User( "Alice", "Johnson","member3@fitnessclub.com", "member345");
        User member4 = new User( "Bob", "Brown","member4@fitnessclub.com", "member456");
        User member5 = new User( "Charlie", "Davis","member5@fitnessclub.com", "member567");
        User all = new User( "Jane", "Smith","all@all.com", "secret");

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

        ClassDate yogaDate =classDateRepository.save(new ClassDate(yogaClass, LocalDate.of(2024, 11, 15), LocalTime.of(8, 0), LocalTime.of(9, 0)));
        classDateRepository.save(new ClassDate(yogaClass, LocalDate.of(2024, 11, 16), LocalTime.of(8, 0), LocalTime.of(9, 0)));
        classDateRepository.save(new ClassDate(yogaClass, LocalDate.of(2024, 11, 17), LocalTime.of(8, 0), LocalTime.of(9, 0)));

        ClassDate pilatesDate = classDateRepository.save(new ClassDate(pilatesClass, LocalDate.of(2024, 11, 15), LocalTime.of(9, 0), LocalTime.of(10, 0)));
        classDateRepository.save(new ClassDate(pilatesClass, LocalDate.of(2024, 11, 16), LocalTime.of(9, 0), LocalTime.of(10, 0)));
        classDateRepository.save(new ClassDate(pilatesClass, LocalDate.of(2024, 11, 17), LocalTime.of(9, 0), LocalTime.of(10, 0)));

        ClassDate spinDate = classDateRepository.save(new ClassDate(spinClass, LocalDate.of(2024, 11, 15), LocalTime.of(10, 0), LocalTime.of(11, 0)));
        classDateRepository.save(new ClassDate(spinClass, LocalDate.of(2024, 11, 16), LocalTime.of(10, 0), LocalTime.of(11, 0)));
        classDateRepository.save(new ClassDate(spinClass, LocalDate.of(2024, 11, 17), LocalTime.of(10, 0), LocalTime.of(11, 0)));

        List<ClassDate> dates =classDateRepository.findAll();
        List<User> memebersList = userService.findMembers();
        Set<User> usersSet = new HashSet<>(memebersList);

        Attendance attendance1 = new Attendance(yogaDate, usersSet);

        attendanceRepository.save(attendance1);

        Shift shift1 = new Shift(yogaClass, userService.findTrainers().getFirst(), dates.getFirst());

        shiftRepository.save(shift1);

    }



}
