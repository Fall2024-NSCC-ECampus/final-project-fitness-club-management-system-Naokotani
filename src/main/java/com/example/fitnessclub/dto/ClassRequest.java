package com.example.fitnessclub.dto;

import com.example.fitnessclub.model.ClassDate;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
public class ClassRequest {
    private String name;
    private Set<ClassDate> dates = new HashSet<>();
}