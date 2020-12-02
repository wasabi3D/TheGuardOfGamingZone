package Components;

import Utilities.Entities.WUser;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.io.Serializable;
import java.util.ArrayList;

@JsonSerialize
public class SortedRanking implements Serializable {
    public ArrayList<WUser> members;
    public ArrayList<Integer> scores;
    public SortedRanking(ArrayList<User> m,ArrayList<Integer> s){
        scores = s;
        members = new ArrayList<>();
        for (User tmpUser: m
             ) {
            members.add(new WUser(tmpUser));
        }
    }
}
