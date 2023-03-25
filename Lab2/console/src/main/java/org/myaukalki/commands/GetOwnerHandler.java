package org.myaukalki.commands;

import org.myaukalki.OwnerService;

public class GetOwnerHandler extends CommandHandler {

    private final OwnerService ownerService;

    public GetOwnerHandler(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public void handle(String command) {
        command = command.toLowerCase();

        if (!(command.contains("get") && command.contains("owner"))) {
            super.handle(command);
            return;
        }

        if (command.split(" ").length != 3) {
            System.out.println("Usage: get owner <id>");
            return;
        }

        try{
        var answer = ownerService.find(Long.parseLong(command.split(" ")[2]));
        System.out.println(answer);
        }
        catch (Exception e){
            System.out.println("error: " + e.getMessage());
        }
    }
}
