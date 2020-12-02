package Components.Command.Commands;

import BotTasks.BotKernel;
import Components.Command.CommandMapper;
import Components.Command.StringProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Ranking extends CommandMapper{
    public Ranking(){
        this.initializeCmd("ranking","r");
        this.addOptions("-w1","-w2","-w3","-w4","-w5");
    }

    @Override
    public void runCommand(StringProcessor command, MessageReceivedEvent event) {
        try {
            BotKernel.sendRankingEmbed(event);
        }catch(NullPointerException e){
            if(command.getFullCmd().contains("-de")) BotKernel.SendLogMessage(event.getChannel(),
                    "Something went wrong and the command threw a NullPointerException!");
        }catch(Exception e){
            if(command.getFullCmd().contains("-de")) BotKernel.SendLogMessage(event.getChannel(),
                    e.getMessage());
        }

    }
}
