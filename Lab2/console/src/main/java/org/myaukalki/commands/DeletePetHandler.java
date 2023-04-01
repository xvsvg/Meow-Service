package org.myaukalki.commands;

import org.myaukalki.PetService;
import org.myaukalki.dto.PetRequestDto;

public class DeletePetHandler extends CommandHandler {

    private final PetService service;

    public DeletePetHandler(PetService service) {
        this.service = service;
    }

    @Override
    public void handle(String command) {

        command = command.toLowerCase();

        if (!(command.contains("delete") && command.contains("pet"))){
            super.handle(command);
            return;
        }

        if (command.split(" ").length != 3){
            System.out.println("Usage: delete pet <id>");
        }


        var dto = new PetRequestDto();
        dto.setId(Long.parseLong(command.split(" ")[2]));

        try{
            var answer = service.delete(dto);
            System.out.println("deleted: " + answer);
        }
        catch (Exception e){
            System.out.println("error: " + e.getMessage());
        }
    }
}
