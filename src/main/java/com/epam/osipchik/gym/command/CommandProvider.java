package com.epam.osipchik.gym.command;

import com.epam.osipchik.gym.command.impl.*;

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
        commands.put(CommandType.SHOW_ABONEMENTS, new ShowAbonements());
        commands.put(CommandType.REGISTRATION_PAGE, new RegistrationPage());
        commands.put(CommandType.AUTHORIZATION_PAGE, new AuthorizationPage());
        commands.put(CommandType.BUY_ABONEMENT,new BuyAbonement());
        commands.put(CommandType.ENGLISH_LANG, new EnglishLang());
        commands.put(CommandType.RUSSIAN_LANG,new RussianLang());
        commands.put(CommandType.LOG_OUT, new LogOut());
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
