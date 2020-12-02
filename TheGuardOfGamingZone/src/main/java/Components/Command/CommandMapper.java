package Components.Command;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandMapper {
    private String head;
    private String head2;
    protected ArrayList<String> options;

    public void initializeCmd(String h,String alt){
        options = new ArrayList<>();
        head = h;
        head2 = alt;
        options.add("-de");
    }

    public boolean ifCmdMatches(String top){
        //System.out.println(top);
        return top.equals(head) || top.equals(head2);
    }

    public void addOptions(String... args){
        options.addAll(Arrays.asList(args));
    }

    public void runCommand(StringProcessor str, MessageReceivedEvent event){
    }
}
