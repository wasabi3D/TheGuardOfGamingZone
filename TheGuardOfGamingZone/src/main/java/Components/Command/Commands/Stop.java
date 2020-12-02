package Components.Command.Commands;

import BotTasks.BotKernel;
import Components.Command.CommandMapper;
import Components.Command.StringProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Stop extends CommandMapper {
    public Stop(){
        this.initializeCmd("stopbot","stopbot");
        this.addOptions("-CONFIRM","-FORCE");
    }

    @Override
    public void runCommand(StringProcessor str, MessageReceivedEvent event){
        //Stop things
        try {
            if(str.getFullCmd().contains("-CONFIRM")) {
                BotKernel.SendLogMessage(event.getChannel(),
                        "Stopping bot...");
                SaveRanking.write();
                Thread.sleep(1000);
                System.exit(0);
            }

        }catch (NullPointerException e){
            if(str.getFullCmd().contains("-de")) BotKernel.SendLogMessage(event.getChannel(),
                    "Something went wrong and the command threw a NullPointerException!");
        }catch (Exception e){
            if(str.getFullCmd().contains("-de")) BotKernel.SendLogMessage(event.getChannel(),
                    e.getMessage());
        }finally{
            if(str.getFullCmd().contains("-FORCE")) {
                BotKernel.SendLogMessage(event.getChannel(),
                        "Forcing to stop the bot...");
                System.exit(256000);
            }
        }

    }
}
