package org.myaukalki.commands;

import org.myaukalki.PetService;
import org.myaukalki.dto.OwnerAnswerDto;
import org.myaukalki.dto.PetRequestDto;

import java.time.LocalDate;

public class UpdatePetHandler extends CommandHandler {

    private final PetService service;

    public UpdatePetHandler(PetService service) {
        this.service = service;
    }

    @Override
    public void handle(String command) {

        command = command.toLowerCase();

        if (!(command.contains("update") && command.contains("pet"))) {
            super.handle(command);
            return;
        }

        System.out.println("provide pet id");
        var petId = Long.parseLong(scanner.nextLine());

        System.out.println("provide pet name");
        var petName = scanner.nextLine();

        System.out.println("provide owner id");
        var ownerId = Long.parseLong(scanner.nextLine());

        System.out.println("provide owner name");
        var ownerName = scanner.nextLine();

        System.out.println("provide owner birth date");
        var birhdate = LocalDate.parse(scanner.nextLine());

        var petDto = new PetRequestDto();
        var ownerDto = new OwnerAnswerDto();
        ownerDto.setId(ownerId);
        ownerDto.setName(ownerName);
        ownerDto.setBirthDate(birhdate);

        petDto.setId(petId);
        petDto.setName(petName);
        petDto.setOwner(ownerDto);

        try {
            var answer = service.update(petDto);
            System.out.println("updated: " + answer);
        }
        catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
