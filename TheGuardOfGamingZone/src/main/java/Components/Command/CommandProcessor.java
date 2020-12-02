package Components.Command;
import java.util.ArrayList;
import Components.Command.Commands.*;

public class CommandProcessor {

    private ArrayList<CommandMapper> registeredCommands = new ArrayList<>();

    public CommandProcessor(){
        //Commands to register here
        registerCmd(new Ranking());
        registerCmd(new Ping());
        registerCmd(new SaveRanking());
        registerCmd(new LoadRanking());
        registerCmd(new Stop());
        registerCmd(new ClearRanking());

    }

    public CommandMapper returnMatchesData(StringProcessor command){
        if(!command.isCommand()){
            return null;
        }
        for (CommandMapper data:registeredCommands
             ) {

            if(data.ifCmdMatches(command.getCmdType())){
                return data;
            }
        }
        return null;
    }

    private void registerCmd(CommandMapper cmd){
        registeredCommands.add(cmd);
    }
}
