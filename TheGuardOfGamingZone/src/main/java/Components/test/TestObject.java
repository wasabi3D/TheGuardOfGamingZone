package Components.test;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.RestAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class TestObject implements User {

    private final String name,discriminator,avatarId,defaultAvatarId,tag;
    private final boolean hasPrivateChannel_;
    private final RestAction<PrivateChannel> privateChannels;
    private final List<Guild> guildList;
    private final JDA jda;
    private final String mention;

    public TestObject(User user){
        name = user.getName();
        discriminator = user.getDiscriminator();
        avatarId = user.getAvatarId();
        defaultAvatarId = user.getDefaultAvatarId();
        tag = user.getAsTag();

        hasPrivateChannel_ = user.hasPrivateChannel();
        privateChannels = user.openPrivateChannel();
        guildList = user.getMutualGuilds();
        jda = user.getJDA();
        mention = user.getAsMention();

    }


    @NotNull
    @Override
    public String getName() {
        return name;
    }

    @NotNull
    @Override
    public String getDiscriminator() {
        return discriminator;
    }

    @Nullable
    @Override
    public String getAvatarId() {
        return avatarId;
    }

    @NotNull
    @Override
    public String getDefaultAvatarId() {
        return defaultAvatarId;
    }

    @NotNull
    @Override
    public String getAsTag() {
        return tag;
    }

    @Override
    public boolean hasPrivateChannel() {
        return false;
    }

    @NotNull
    @Override
    public RestAction<PrivateChannel> openPrivateChannel() {
        return privateChannels;
    }

    @NotNull
    @Override
    public List<Guild> getMutualGuilds() {
        return guildList;
    }

    @Override
    public boolean isBot() {
        return false;
    }

    @NotNull
    @Override
    public JDA getJDA() {
        return jda;
    }

    @NotNull
    @Override
    public EnumSet<UserFlag> getFlags() {
        return null;
    }

    @Override
    public int getFlagsRaw() {
        return 0;
    }

    @Override
    public boolean isFake() {
        return false;
    }

    @NotNull
    @Override
    public String getAsMention() {
        return mention;
    }

    @Override
    public long getIdLong() {
        return 0;
    }
}
