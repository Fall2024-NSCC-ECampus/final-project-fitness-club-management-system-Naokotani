package com.example.fitnessclub.shell;

import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;
import org.jline.utils.AttributedString;

@Configuration
public class ShellConfiguration implements PromptProvider {

    @Override
    public final AttributedString getPrompt() {
        return new AttributedString("fitness-club:>");
    }
}