package Components.Command;

import Components.SplitCommandString;

import java.util.ArrayList;

public class StringProcessor {
    //Field
    private String command;
    private ArrayList<String> commandSp;
    private boolean isCommand;
    public static final char splitter = ' ';

    public StringProcessor(String i){ //Constructor
        command = i;
        commandSp = SplitCommandString.split(command,splitter);
        if(commandSp.get(0).charAt(0) == '$'){
            isCommand = true;
            commandSp.set(0,new StringBuilder(commandSp.get(0)).delete(0,1).toString().toLowerCase());
        }else{
            isCommand = false;
        }
    }

    public static void main(String[] args){ //test method

        /*
        String test = "test hello \"Yay hello \"w\"orld\" bonsoir";
        String[] test2 = SplitCommandString.split(test,splitter);
        for(int i = 0;i<test2.length;i++){
            System.out.println(test2[i]);
        }

         */


    }

    // ^^Main methods
    //---------

    public String getCmdType(){
        return commandSp.get(0);
    }

    public ArrayList<String> getFullCmd(){return commandSp;}

    public boolean isCommand(){return isCommand;}


}
