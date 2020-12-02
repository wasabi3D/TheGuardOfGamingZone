package BotTasks;

import javax.jws.soap.SOAPBinding;
import javax.security.auth.login.LoginException;

import BotTasks.Users.UserManagement;
import Components.Command.CommandMapper;
import Components.Command.CommandProcessor;
import Components.Command.StringProcessor;
import Components.RankingData;
import Components.SortedRanking;
import Utilities.BotFileIOSystem;
import Utilities.Entities.WUser;
import Utilities.MessageWithLifetime;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed.*;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import BotTasks.Users.Antispam;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class BotKernel extends ListenerAdapter {
    static Antispam antispam;
    // MessageWithLifetime mwlt;
    static UserManagement userManagement;
    static CommandProcessor commandProcessor;
    static BotFileIOSystem botFileIOSystem;

    String antiSpamReturnVal;

    public static void main(String[] args) throws LoginException {

        JDA jda = JDABuilder.createDefault("...").addEventListeners(new BotKernel()).build();

        //antispam
        antispam = new Antispam();
        antispam.start();

        //msgLifetime
        //mwlt = new MessageWithLifetime();
        //mwlt.start();

        //UserManagement
        userManagement = new UserManagement();
        userManagement.start();

        commandProcessor = new CommandProcessor();

        //NOTE: BACKSLASHES IN THE STRING HAVE TO BE REPLACED WITH SLASHES WHEN BUILD
        botFileIOSystem = new BotFileIOSystem(System.getProperty("user.dir")+"\\botDatas\\");

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){

        if(event.getAuthor().isBot()){
            return;
        }

        System.out.println(event.getMember().getEffectiveName()+ ": " + event.getMessage().getContentRaw());
        //Antispam feature
        antiSpamReturnVal = antispam.onUserMessageReceived(event.getAuthor());
        switch (antiSpamReturnVal) {
            case "warning":
                MessageAction tmpMsg = event.getChannel().sendMessage("Hey, " + event.getAuthor().getName() + ", don't send too many messages!");
                tmpMsg.queue();
                event.getMessage().delete().queue();
                return;
            case "delete":
                event.getMessage().delete().queue();
                return;
            case "muted":
                userManagement.muteUser(event.getGuild(), event.getMember(), 10);
                event.getChannel().sendMessage(event.getAuthor().getName() + ", you have been muted for 10 seconds since you did too much spam!").queue();
                return;
        }

        StringProcessor processor = new StringProcessor(event.getMessage().getContentRaw());
        CommandMapper tmp = commandProcessor.returnMatchesData(processor);
        if(tmp != null){
            tmp.runCommand(processor,event);
        }

        //Guild Competition
        userManagement.addPointsToMember(event.getMember(),1); //Check if valid msg


    }


    //Used by other classes
    public static void sendRankingEmbed(MessageReceivedEvent event){

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Ranking");
        embed.setColor(Color.RED);
        embed.setDescription("Ranking of the Gaming Zone. Reset every week.");
        embed.addBlankField(false);

        RankingData rankingData = userManagement.getClassified(); //data check
        for(int i = 0; i< rankingData.size();i++){
            Field tmp = new Field((i+1)+". " , rankingData.getKey(i).getNickname() + ": " + rankingData.get(i) + "pt",false);
            embed.addField(tmp);
        }

        event.getChannel().sendMessage(embed.build()).queue();
    }

    public static UserManagement getUserManagement(){
        return userManagement;
    }
    public static BotFileIOSystem getBotFileIOSystem(){return botFileIOSystem;}

    public static void SendLogMessage(MessageChannel channel, String str){
        //Channel system
        channel.sendMessage(str).queue();
    }
}
