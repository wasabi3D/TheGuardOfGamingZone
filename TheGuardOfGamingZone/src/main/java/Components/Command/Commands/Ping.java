package Components.Command.Commands;

import BotTasks.BotKernel;
import Components.Command.CommandMapper;
import Components.Command.StringProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Ping extends CommandMapper{
    public Ping(){
        this.initializeCmd("ping","ping");
        this.addOptions();
    }

    @Override
    public void runCommand(StringProcessor command, MessageReceivedEvent event) {
        try {
            event.getChannel().sendMessage("Pong!").queue();
        }catch(NullPointerException e){
            if(command.getFullCmd().contains("-de")) BotKernel.SendLogMessage(event.getChannel(),
                    "Something went wrong and the command threw a NullPointerException!");
        }catch(Exception e){
            if(command.getFullCmd().contains("-de")){
                event.getChannel().sendMessage(e.getMessage()).queue();
            }
        }
    }
}
