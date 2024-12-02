package com.example.fitnessclub;

import com.example.fitnessclub.model.*;
import com.example.fitnessclub.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@Profile("test")
class DatabaseInsertTest {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private ClassDateRepository classDateRepository;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void insert(){
        insertUsers();
        insertClasses();
    }

    private void insertUsers() {
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

        userRepository.save(admin);
        userRepository.save(member1);
        userRepository.save(member2);
        userRepository.save(member3);
        userRepository.save(member4);
        userRepository.save(member5);
        userRepository.save(all);
        userRepository.save(trainer);
    }

    private void insertClasses() {
        ClassDetails yogaClass = new ClassDetails("Yoga");
        ClassDetails pilatesClass = new ClassDetails("Pilates");
        ClassDetails spinClass = new ClassDetails("Spin");

        yogaClass=classRepository.save(yogaClass);
        pilatesClass=classRepository.save(pilatesClass);
        spinClass=classRepository.save(spinClass);

        ClassDate yogaDate = classDateRepository.save(new ClassDate(yogaClass, LocalDate.of(2024, 11, 15), LocalTime.of(8, 0), LocalTime.of(9, 0)));
        ClassDate yogaDate2 = classDateRepository.save(new ClassDate(yogaClass, LocalDate.of(2024, 11, 16), LocalTime.of(8, 0), LocalTime.of(9, 0)));
        classDateRepository.save(new ClassDate(yogaClass, LocalDate.of(2024, 11, 17), LocalTime.of(8, 0), LocalTime.of(9, 0)));

        ClassDate pilatesDate = classDateRepository.save(new ClassDate(pilatesClass, LocalDate.of(2024, 11, 15), LocalTime.of(9, 0), LocalTime.of(10, 0)));
        ClassDate pilatesDate2 = classDateRepository.save(new ClassDate(pilatesClass, LocalDate.of(2024, 11, 16), LocalTime.of(9, 0), LocalTime.of(10, 0)));
        classDateRepository.save(new ClassDate(pilatesClass, LocalDate.of(2024, 11, 17), LocalTime.of(9, 0), LocalTime.of(10, 0)));

        ClassDate spinDate = classDateRepository.save(new ClassDate(spinClass, LocalDate.of(2024, 11, 15), LocalTime.of(10, 0), LocalTime.of(11, 0)));
        ClassDate spinDate2 = classDateRepository.save(new ClassDate(spinClass, LocalDate.of(2024, 11, 16), LocalTime.of(10, 0), LocalTime.of(11, 0)));
        classDateRepository.save(new ClassDate(spinClass, LocalDate.of(2024, 11, 17), LocalTime.of(10, 0), LocalTime.of(11, 0)));

        List<ClassDate> dates =classDateRepository.findAll();
        List<User> memebersList = userRepository.findUsersByRole(UserRoles.MEMBER).orElseThrow();
        Set<User> usersSet = new HashSet<>(memebersList);

        Attendance attendance1 = new Attendance(yogaDate, usersSet);
        Attendance attendance2 = new Attendance(pilatesDate, usersSet);
        Attendance attendance3 = new Attendance(spinDate, usersSet);

        Attendance attendance4 = new Attendance(yogaDate2, usersSet);
        Attendance attendance5 = new Attendance(pilatesDate2, usersSet);
        Attendance attendance6 = new Attendance(spinDate2, usersSet);

        attendanceRepository.save(attendance1);
        attendanceRepository.save(attendance2);
        attendanceRepository.save(attendance3);
        attendanceRepository.save(attendance4);
        attendanceRepository.save(attendance5);
        attendanceRepository.save(attendance6);

        Shift shift1 = new Shift(yogaClass, userRepository.findUsersByRole(UserRoles.TRAINER).orElseThrow().getLast(), dates.getFirst());
        Shift shift2 = new Shift(pilatesClass, userRepository.findUsersByRole(UserRoles.TRAINER).orElseThrow().getFirst(), dates.getLast());
        Shift shift3 = new Shift(spinClass, userRepository.findUsersByRole(UserRoles.TRAINER).orElseThrow().getLast(), dates.get(1));

        shiftRepository.save(shift1);
        shiftRepository.save(shift2);
        shiftRepository.save(shift3);
    }


}
