package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String command = Command.help();
        while (!command.equals("exit")) {
            switch (command) {
                case "help" -> command = Command.outputManual();
                case "memLocal" -> command = Command.createMemLocal();
                case "memURL" -> command = Command.createMemURL();
            }
        }
    }
}