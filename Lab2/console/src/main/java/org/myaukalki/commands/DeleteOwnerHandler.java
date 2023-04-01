package org.myaukalki.commands;

import org.myaukalki.OwnerService;
import org.myaukalki.dto.OwnerRequestDto;
import org.myaukalki.dto.PetRequestDto;

public class DeleteOwnerHandler extends CommandHandler {

    private final OwnerService service;

    public DeleteOwnerHandler(OwnerService service) {
        this.service = service;
    }

    @Override
    public void handle(String command) {

        command = command.toLowerCase();

        if (!(command.contains("delete") && command.contains("owner"))){
            super.handle(command);
            return;
        }

        if (command.split(" ").length != 3){
            System.out.println("Usage: delete owner <id>");
        }


        var dto = new OwnerRequestDto();
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
