package com.epam.osipchik.gym.command;

import com.epam.osipchik.gym.command.impl.AuthorizationUserCommand;
import com.epam.osipchik.gym.command.impl.CommandNotFound;
import com.epam.osipchik.gym.command.impl.MainPage;
import com.epam.osipchik.gym.command.impl.RegistrationUserCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private Map<CommandType,Command> commands = new HashMap<>();
    public CommandProvider(){
        commands.put(CommandType.AUTHORIZATION, new AuthorizationUserCommand());
        commands.put(CommandType.REGISTRATION, new RegistrationUserCommand());
        commands.put(CommandType.COMMAND_NOT_FOUND,new CommandNotFound());
        commands.put(CommandType.MAIN_PAGE, new MainPage());
    }
    public static CommandProvider getInstance(){
        return instance;
    }

    public Command getCommand(String nameOfCommand){
        Command command;
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(nameOfCommand.toUpperCase());
            command = commands.get(commandType);
        } catch (IllegalThreadStateException | NullPointerException e) {
            command = commands.get(CommandType.COMMAND_NOT_FOUND);
        }
        return command;
    }
}
