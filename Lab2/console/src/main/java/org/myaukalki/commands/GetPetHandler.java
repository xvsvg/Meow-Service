package org.myaukalki.commands;

import org.myaukalki.PetService;
import org.myaukalki.dto.CreateOwnerDto;

import java.time.LocalDate;

public class GetPetHandler extends CommandHandler {

    private final PetService service;

    public GetPetHandler(PetService service) {
        this.service = service;
    }

    @Override
    public void handle(String command) {
        command = command.toLowerCase();

        if (!(command.contains("get") && command.contains("pet"))) {
            super.handle(command);
            return;
        }

        if (command.split(" ").length != 3) {
            System.out.println("Usage: get pet <id>");
            return;
        }

        try {
            var answer = service.find(Long.parseLong(command.split(" ")[2]));
            System.out.println(answer);
        }
        catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
