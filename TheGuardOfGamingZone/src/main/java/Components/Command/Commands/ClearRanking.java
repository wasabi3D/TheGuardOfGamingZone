package Components.Command.Commands;

import BotTasks.BotKernel;
import Components.Command.CommandMapper;
import Components.Command.StringProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ClearRanking extends CommandMapper {
    public ClearRanking(){
        this.initializeCmd("clearranking","clearranking");
        this.addOptions("-CONFIRM");
    }

    @Override
    public void runCommand(StringProcessor str, MessageReceivedEvent event){
        try {
            if(str.getFullCmd().contains("-CONFIRM")) {
                BotKernel.getUserManagement().clearRanking();
                BotKernel.SendLogMessage(event.getChannel(),
                        "Successfully cleared current ranking data.");
            }
        }catch(NullPointerException e){
            if(str.getFullCmd().contains("-de")) BotKernel.SendLogMessage(event.getChannel(),
                    "Something went wrong and the command threw a NullPointerException!");
        }catch(Exception e){
            if(str.getFullCmd().contains("-de")) BotKernel.SendLogMessage(event.getChannel(),
                    e.getMessage());
        }
    }
}
