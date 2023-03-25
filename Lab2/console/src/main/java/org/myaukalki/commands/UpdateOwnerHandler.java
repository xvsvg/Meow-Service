package org.myaukalki.commands;

import org.myaukalki.OwnerService;
import org.myaukalki.dto.OwnerRequestDto;

public class UpdateOwnerHandler extends CommandHandler {

    private final OwnerService service;

    public UpdateOwnerHandler(OwnerService service) {
        this.service = service;
    }

    @Override
    public void handle(String command) {
        command = command.toLowerCase();

        if (!(command.contains("update") && command.contains("owner"))) {
            super.handle(command);
            return;
        }

        System.out.println("provide owner id");
        var id = Long.parseLong(scanner.nextLine());

        System.out.println("provide owner name");
        var name = scanner.nextLine();


        var dto = new OwnerRequestDto();
        dto.setId(id);
        dto.setName(name);

        try {
            var answer = service.update(dto);
            System.out.println("updated: " + answer);
        }
        catch(Exception ex){
            System.out.println("error: " + ex.getMessage());
        }

    }
}
