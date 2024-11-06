package com.example.fitnessclub.request;

import com.example.fitnessclub.model.ClassDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassRequest {
    private String name;
    private Set<ClassDate> dates = new HashSet<>();
}