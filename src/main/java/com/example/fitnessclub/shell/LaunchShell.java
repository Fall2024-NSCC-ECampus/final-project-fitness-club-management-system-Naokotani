package com.example.fitnessclub.shell;

import com.example.fitnessclub.service.UserRegistrationService;
import com.example.fitnessclub.service.UserRegistrationServiceImpl;
import com.example.fitnessclub.service.UserService;
import com.example.fitnessclub.service.UserServiceImpl;
import com.example.fitnessclub.exceptions.UserExists;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.model.UserRoles;
import com.example.fitnessclub.repository.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import java.util.*;

@Slf4j
@ShellComponent
public class LaunchShell {
    private final UserRepository userRepository;
    private final UserRegistrationService userRegistrationService;
    private final AttendanceRepository attendanceRepository;
    private final ClassRepository classRepository;
    private final ClassDateRepository classDateRepository;
    private final ShiftRepository shiftRepository;
    private final UserService userService;

    public LaunchShell(UserRepository userRepository, AttendanceRepository attendanceRepository, ClassRepository classRepository, ClassDateRepository classDateRepository, ShiftRepository shiftRepository) {
        this.userRepository = userRepository;
        this.userRegistrationService = new UserRegistrationServiceImpl(userRepository);
        this.attendanceRepository = attendanceRepository;
        this.classRepository = classRepository;
        this.classDateRepository = classDateRepository;
        this.shiftRepository = shiftRepository;
        this.userService = new UserServiceImpl(userRepository);
    }

    @ShellMethod("List registered admins")
    public void listAdmins() {
        Optional<List<User>> admins = userRepository.findAdmins();
        if(admins.isEmpty()) {
            System.out.println("No Admins currently registered");
        } else {
            admins.get().forEach(u -> System.out.println(u.toString()));
        }
    }

    @ShellMethod("Delete user by id")
    public String deleteUser(long id) {
        userRepository.deleteById(id);
        return String.format("Deleted user with id %d", id);
    }

    @ShellMethod("Seed the database with starter data")
    public String seedData(){
        SeedData seedData = new SeedData(userRepository, attendanceRepository, classRepository, classDateRepository,
                shiftRepository, userRegistrationService, userService);
        seedData.insert();
        return "Data seed successfully";
    }

    @ShellMethod("Register new Admin" )
    public void registerAdmin(
            @ShellOption(value={"--first-name", "-f"})
            @NotBlank
            @Pattern(regexp = "^\\S+$", message="Name cannot include spaces")
            String firstName,

            @NotBlank
            @Pattern(regexp = "^\\S+$", message="Name cannot include spaces")
            @ShellOption(value={"--last-name", "-l"})
            String lastName,

            @ShellOption(value={"--password", "-p"})
            String password,

            @Email
            @ShellOption(value={"--email", "-e"})
            String email,

            @ShellOption(value={"--trainer", "-t"})
            boolean trainer,
            @ShellOption(value={"--user", "-u"})
            boolean user
    ) {
        User adminUser = new User(firstName, lastName, email, password);
        adminUser.addRole(UserRoles.ADMIN);
        if(trainer) {adminUser.addRole(UserRoles.TRAINER);}
        if(user) {adminUser.addRole(UserRoles.MEMBER);}

        try {
            userRegistrationService.registerUser(adminUser);
            System.out.printf("First Name: %s, Last Name: %s, Email: %s has been registered successfully%n"
                    , firstName, lastName, email);
        } catch (UserExists e) {
            log.error("e: ", e);
        }
    }
}
