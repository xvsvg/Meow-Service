package org.myaukalki.commands;

import org.myaukalki.OwnerService;
import org.myaukalki.dto.CreateOwnerDto;

import java.time.LocalDate;

public class CreateOwnerHandler extends CommandHandler {

    private final OwnerService service;

    public CreateOwnerHandler(OwnerService service) {
        this.service = service;
    }

    @Override
    public void handle(String command) {
        command = command.toLowerCase();

        if (!(command.contains("create") && command.contains("owner"))) {
            super.handle(command);
            return;
        }

        System.out.println("provide owner name");
        var name = scanner.nextLine();

        System.out.println("provide owner birthdate");
        var birthDate = LocalDate.parse(scanner.nextLine());

        var ownerDto = new CreateOwnerDto();

        ownerDto.setName(name);
        ownerDto.setBirthDate(birthDate);

        try {
            var answer = service.createOwner(ownerDto);
            System.out.println("owner created " + answer);
        }
        catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

    }
}
