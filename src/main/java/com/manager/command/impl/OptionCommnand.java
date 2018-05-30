package com.manager.command.impl;

import com.manager.command.Command;
import com.manager.ui.UITemplate;

public class OptionCommnand implements Command{
    
    private UITemplate uiTemplate;
            
    public OptionCommnand(UITemplate uiTemplate){
        this.uiTemplate = uiTemplate;
    }

    public Object doCommand() {
        uiTemplate.doOptions();
        return null;
    }
    
}
