package Components.Command.Commands;

import BotTasks.BotKernel;
import Components.Command.CommandMapper;
import Components.Command.StringProcessor;
import Utilities.RankingSnL;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;

public class LoadRanking extends CommandMapper {
    public LoadRanking(){ this.initializeCmd("loadranking","lr");}

    @Override
    public void runCommand(StringProcessor str, MessageReceivedEvent event){
        try {
            //System.out.println(RankingSnL.loadFromFile(new File("rankingTMP.dat")) ==null);
            BotKernel.getUserManagement().replaceRanking(RankingSnL.loadFromFile(new File("rankingTMP.dat")));
            event.getChannel().sendMessage("Successfully loaded ranking data.").queue();
        }catch (NullPointerException e){
            if(str.getFullCmd().contains("-de")) BotKernel.SendLogMessage(event.getChannel(),
                    "Something went wrong and the command threw a NullPointerException!");
        }catch (Exception e){
            if(str.getFullCmd().contains("-de")) BotKernel.SendLogMessage(event.getChannel(),
                    e.getMessage());
        }
    }
}
