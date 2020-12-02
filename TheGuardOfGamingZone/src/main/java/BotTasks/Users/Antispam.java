package BotTasks.Users;

import net.dv8tion.jda.api.entities.User;

import java.util.*;



public class Antispam extends Thread{

    private HashMap<User,Integer> usersMsgCount = new HashMap<User,Integer>();
    UserSpamMute spamMute;

    @Override
    public void start(){
        Timer timer = new Timer();

        //SpamMute
        spamMute = new UserSpamMute();
        spamMute.start();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                usersMsgCount.clear();
            }}, 2400, 2400);
    }

    public String onUserMessageReceived(User user){
        String m;
        if(usersMsgCount.containsKey(user)){
            usersMsgCount.replace(user,usersMsgCount.get(user)+1);
            System.out.println(usersMsgCount.get(user));
            if(usersMsgCount.get(user) > 1){
                m = spamMute.onUserSpammed(user);
                if(m.equals("muted")){
                    return "muted";
                }
                if(usersMsgCount.get(user) > 2){
                    return "delete";
                }
                return "warning";
            }
        }else{
            usersMsgCount.put(user,1);
            return "none";
        }
        return "";
    }
}

class UserSpamMute extends Thread{
    private HashMap<User,Integer> usersSpamCount = new HashMap<>();

    @Override
    public void start(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                usersSpamCount.clear();
            }
        },10000,10000);
    }

    public String onUserSpammed(User user){
        if(usersSpamCount.containsKey(user)){
            usersSpamCount.replace(user,usersSpamCount.get(user)+1);
            if(usersSpamCount.get(user)>2){
                usersSpamCount.replace(user,0);
                return "muted";
            }
        }else{
            usersSpamCount.put(user,1);
            return "";
        }
        return "";
    }


}


