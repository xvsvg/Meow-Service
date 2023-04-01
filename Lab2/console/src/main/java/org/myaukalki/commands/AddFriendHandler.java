package org.myaukalki.commands;

import org.myaukalki.PetService;
import org.myaukalki.dto.*;

public class AddFriendHandler extends CommandHandler {

    private final PetService service;

    public AddFriendHandler(PetService service) {
        this.service = service;
    }

    @Override
    public void handle(String command) {

        command = command.toLowerCase();

        if (!(command.contains("add") && command.contains("friend"))) {
            super.handle(command);
            return;
        }

        System.out.println("provide target pet id");
        var targetPetId = Long.parseLong(scanner.nextLine());

        System.out.println("provide target pet name");
        var targetPetName = scanner.nextLine();

        System.out.println("provide target pet owner id");
        var targetPetOwnerId = Long.parseLong(scanner.nextLine());

        System.out.println("provide target pet owner name");
        var targetPetOwnerName = scanner.nextLine();

        System.out.println("provide friend pet id");
        var friendPetId = Long.parseLong(scanner.nextLine());

        System.out.println("provide friend pet name");
        var friendPetName = scanner.nextLine();

        System.out.println("provide friend pet owner id");
        var friendPetOwnerId = Long.parseLong(scanner.nextLine());

        System.out.println("provide friend pet owner name");
        var friendPetOwnerName = scanner.nextLine();

        var targetOwnerDto = new OwnerAnswerDto();
        var friendOwnerDto = new OwnerAnswerDto();

        targetOwnerDto.setId(targetPetOwnerId);
        targetOwnerDto.setName(targetPetOwnerName);

        friendOwnerDto.setId(friendPetOwnerId);
        friendOwnerDto.setName(friendPetOwnerName);

        var pet = new PetRequestDto();
        var friend = new PetRequestDto();

        pet.setId(targetPetId);
        pet.setName(targetPetName);

        friend.setId(friendPetId);
        friend.setName(friendPetName);

        try{
            var answer = service.addFriend(pet, friend);
            System.out.println("added: " + answer);
        }
        catch(Exception e){
            System.out.println("error: " + e.getMessage());
        }
    }
}
