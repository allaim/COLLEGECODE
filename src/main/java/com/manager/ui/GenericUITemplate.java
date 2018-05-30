package com.manager.ui;

import com.manager.command.Command;
import com.manager.command.impl.OptionCommnand;
import com.manager.command.impl.QuitCommand;
import com.manager.util.Printable;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class GenericUITemplate extends Printable implements UITemplate{

    protected String permName;

    protected Scanner lineReader;

    protected Map<String,Command> commands;

    public GenericUITemplate(Scanner lineReader, String permName){

        this.lineReader = lineReader;
        this.permName = permName;
        this.commands = new HashMap<String, Command>();
        this.commands.put("O",new OptionCommnand(this));
        this.commands.put("Q",new QuitCommand());

    }

    public void run(){

        println(">> "+permName);
        doOptions();
        while(true) {

            print(">> ");
            String commandString = lineReader.nextLine();

            Command command = commands.get(commandString);
            if (command != null) command.doCommand();
            else doErrorCommand();

        }

    }

    protected void doErrorCommand() {
        println("Command not found! Please try again");
        doOptions();
    }

}
