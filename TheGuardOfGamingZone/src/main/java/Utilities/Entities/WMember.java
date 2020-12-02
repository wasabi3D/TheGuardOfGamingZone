package Utilities.Entities;

import net.dv8tion.jda.api.entities.Member;

public class WMember{

    private final String nickname,effectiveName,mention;
    private final long id;

    public WMember(Member member){
        if(member.getNickname() != null) {
            nickname = member.getNickname();
        }else{
            nickname = member.getEffectiveName();
        }
        effectiveName = member.getEffectiveName();
        mention = member.getAsMention();
        id = member.getIdLong();

    }


    public String getNickname() {
        return nickname;
    }

    public String getEffectiveName() {
        return effectiveName;
    }

    public String getAsMention() {
        return mention;
    }

    public long getIdLong() {
        return id;
    }
}
