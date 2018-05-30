package main;

import com.manager.command.Command;
import com.manager.util.Printable;
import com.manager.ui.UITemplate;
import com.manager.command.impl.LoginCommand; 

import java.util.Scanner;

public class Application extends Printable {

    private Scanner lineReader;

    private Command loginCommand;

    private UITemplate uiTemplate;

    public Application(){
        lineReader = new Scanner(System.in);

        loginCommand = new LoginCommand(lineReader);
    }

    public void run() {

        uiTemplate = (UITemplate)loginCommand.doCommand();
        if(uiTemplate!=null)uiTemplate.run();

    }

    public static void main(String args[]) {

        Application app = new Application();
        app.run();

    }

}