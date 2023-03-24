package org.myaukalki.commands;

import org.myaukalki.OwnerService;
import org.myaukalki.PetService;
import org.myaukalki.domain.utils.Color;
import org.myaukalki.dto.*;
import org.myaukalki.implementations.OwnerDaoImpl;
import org.myaukalki.mapper.OwnerMapper;

import java.time.LocalDate;

public class CreatePetHandler extends CommandHandler {
    private final PetService service;
    private final OwnerService ownerService;

    public CreatePetHandler(PetService service) {
        this.service = service;
        ownerService = new OwnerService(new OwnerDaoImpl());
    }

    @Override
    public void handle(String command) {
        command = command.toLowerCase();

        if (!(command.contains("create") && command.contains("pet"))) {
            super.handle(command);
            return;
        }

        System.out.println("provide pet name");
        var name = scanner.nextLine();

        System.out.println("provide pet birthdate");
        var petBirthDate = LocalDate.parse(scanner.nextLine());

        System.out.println("provide owner id");
        var ownerId = Long.parseLong(scanner.nextLine());

        System.out.println("provide pet color");
        var color = Color.valueOf(scanner.nextLine());

        System.out.println("provide pet breed");
        var breed = scanner.nextLine();


        var petDto = new CreatePetDto();
        var ownerDto = ownerService.find(ownerId);

        petDto.setName(name);
        petDto.setOwner(ownerDto);
        petDto.setBirthDate(petBirthDate);
        petDto.setColor(color);
        petDto.setBreed(breed);

        try {
            var answer = service.createPet(petDto);
            System.out.println("pet created " + answer);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
