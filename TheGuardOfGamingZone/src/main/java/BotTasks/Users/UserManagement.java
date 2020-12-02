package BotTasks.Users;

import Components.RankingData;
import Components.SortedRanking;
import Utilities.Entities.WMember;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.internal.requests.restaction.PermissionOverrideActionImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class UserManagement extends Thread{

    private ArrayList<MuteObject> mutedUsers;
    private RankingData rankingData;

    @Override
    public void start(){

        mutedUsers = new ArrayList<>();
        rankingData = new RankingData();


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //mute-------------------------------
                for(int i = 0; i< mutedUsers.size();i++){
                    mutedUsers.get(i).subtraction(0.5f);
                    if(mutedUsers.get(i).getTimeRemaining() <= 0f){
                        unmuteMember(mutedUsers.get(i).getUser().getGuild(),mutedUsers.get(i).getUser());
                        mutedUsers.remove(i);
                    }
                }
                ///mute=======================================

            }
        },500,500);


    }

    //MUTE
    public void muteUser(Guild g, Member member, int seconds){
        if(member != null) {
            mutedUsers.add(new MuteObject(member, seconds));
            muteMember(g, member);
        }

    }

    private void muteMember(Guild g, Member m){

        g.addRoleToMember(m,g.getRolesByName("Muted",true).get(0)).queue();
    }

    private void unmuteMember(Guild g, Member m){
        g.removeRoleFromMember(m,g.getRolesByName("Muted",true).get(0)).queue();
    }


    //GUILD COMPETITION
    public void addPointsToMember(Member m,int points){
        if(m != null) {
            if(rankingData.containsKey(new WMember(m))) {
                rankingData.put(new WMember(m), rankingData.get(new WMember(m)) + points);
            }else{
                rankingData.put(new WMember(m),points);
            }
        }
    }

    public String removePointsFromMember(Member m, int points){
        WMember tmp = new WMember(m);
        if(rankingData.containsKey(tmp)){
            rankingData.put(tmp,rankingData.get(tmp)-points);
            return "success";
        }else{
            return "nuf";
        }
    }

    public RankingData getClassified(){
        /*
        ArrayList<User> membersList = new ArrayList<User>(guildPoints.keySet());
        ArrayList<Integer> pointsList = new ArrayList<Integer>(guildPoints.values());

        User tmpM; int tmpF;
        //Sort
        for(int i = 0;i<guildPoints.size();i++){
            for(int o = 0; o<guildPoints.size()-1;o++){

                if(pointsList.get(o) < pointsList.get(o+1)){
                    tmpF = pointsList.get(o);
                    tmpM = membersList.get(o);

                    pointsList.set(o,pointsList.get(o+1));
                    pointsList.set(o+1,tmpF);
                    membersList.set(o,membersList.get(o+1));
                    membersList.set(o+1,tmpM);
                }
            }
        }
        return new SortedRanking(membersList,pointsList);

         */

        return rankingData.getSorted();

    }

    public void replaceRanking(RankingData data){ //Make Null exception
        rankingData = data;
    }

    public void clearRanking(){rankingData.clear();}


}

class MuteObject{
    private Member mutedUser;
    private float timeRemaining;

    public MuteObject(Member user, float time){
        mutedUser = user;
        timeRemaining = time;
    }

    public float getTimeRemaining(){
        return timeRemaining;
    }

    public Member getUser(){
        return mutedUser;
    }

    public void subtraction(float sec){
        timeRemaining -= sec;
    }


}

