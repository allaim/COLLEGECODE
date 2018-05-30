package com.manager.command;

import com.manager.util.Printable;

import java.util.Scanner;

public abstract class GenericCommand extends Printable implements Command{

    protected Scanner lineReader;

    public GenericCommand(Scanner lineReader){
        this.lineReader = lineReader;
    }

}
