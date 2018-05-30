package com.manager.util;

public class Printable {

    /**
     * wrapper to System.out.print in the console, and maybe in a future change for a logger
     */
    protected void print(String str) {
        System.out.print(str);
    }

    /**
     * wrapper to System.out.println in the console, and maybe in a future change for a logger
     */
    protected void println(String str) {
        System.out.println(str);
    }

}
