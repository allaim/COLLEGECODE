package com.manager.command.impl;

import com.manager.command.Command;

public class QuitCommand implements Command {

    public Object doCommand() {
        System.exit(0);
        return null;
    }

}
