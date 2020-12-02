package Components.Command.Commands;

import BotTasks.BotKernel;
import Components.Command.CommandMapper;
import Components.Command.StringProcessor;
import Components.test.TestObject;
import Utilities.Entities.WUser;
import Utilities.RankingSnL;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.io.IOException;

public class SaveRanking extends CommandMapper {
    public SaveRanking(){
        this.initializeCmd("saveranking","sr");
    }

    @Override
    public void runCommand(StringProcessor str, MessageReceivedEvent event){
        try {
            write();
            event.getChannel().sendMessage("Successfully saved ranking data.").queue();
        }catch (NullPointerException e){
            if(str.getFullCmd().contains("-de")) BotKernel.SendLogMessage(event.getChannel(),
                    "Something went wrong and the command threw a NullPointerException!");
        }catch (Exception e){
            if(str.getFullCmd().contains("-de")) BotKernel.SendLogMessage(event.getChannel(),
                    e.getMessage());
        }

    }

    public static void write() throws IOException{
        RankingSnL.writeToFile(RankingSnL.getStr(BotKernel.getUserManagement().getClassified()),
                new File("rankingTMP.dat"));
    }
}
