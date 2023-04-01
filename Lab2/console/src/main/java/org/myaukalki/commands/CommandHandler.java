package org.myaukalki.commands;

import org.jetbrains.annotations.Nullable;

import java.util.Scanner;

public abstract class CommandHandler {

    @Nullable
    private CommandHandler handler;
    public final Scanner scanner;

    public CommandHandler(){
        scanner = new Scanner(System.in);
    }

    public CommandHandler setNext(CommandHandler handler){
        this.handler = handler;
        return handler;
    }

    public void handle(String command){
        if (handler!= null) {
            handler.handle(command);
        }
        else throw new RuntimeException("Unable to handle command");
    }
}
