package org.myaukalki;

import org.myaukalki.commands.*;
import org.myaukalki.implementations.OwnerDaoImpl;
import org.myaukalki.implementations.PetDaoImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MyaukalkiCLI {

    private static CommandHandler handler;
    private static PetService catService;
    private static OwnerService ownerService;
    private final Scanner reader;

    public MyaukalkiCLI() {
        catService = new PetService(new PetDaoImpl());
        ownerService = new OwnerService(new OwnerDaoImpl());
        reader = new Scanner(System.in);;
    }

    public void startInteractiveMode() {

        handler = new CreatePetHandler(catService);
        handler
                .setNext(new CreateOwnerHandler(ownerService))
                .setNext(new GetOwnerHandler(ownerService))
                .setNext(new GetPetHandler(catService))
                .setNext(new UpdatePetHandler(catService))
                .setNext(new UpdateOwnerHandler(ownerService))
                .setNext(new DeletePetHandler(catService))
                .setNext(new DeleteOwnerHandler(ownerService))
                .setNext(new AddFriendHandler(catService));


        while (true) {
            var command = reader.nextLine();

            if (command.equals("exit")) {
                return;
            } else {
                handler.handle(command);
            }
        }
    }
}
