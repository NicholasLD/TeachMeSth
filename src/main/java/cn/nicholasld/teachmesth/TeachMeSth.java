package cn.nicholasld.teachmesth;

import cn.nicholasld.teachmesth.implement.TeachMeImpl;
import kotlin.Lazy;
import kotlin.LazyKt;
import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.permission.*;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.FileNotFoundException;

/**
 * TeachMeSth
 * @author NicholasLD
 * @createTime 2023/5/29 20:42
 */
public final class TeachMeSth extends JavaPlugin {
    public static final TeachMeSth INSTANCE = new TeachMeSth();

    private TeachMeSth() {
        super(new JvmPluginDescriptionBuilder("cn.nicholasld.teachmesth", "1.0.0")
                .info("NicholasLD")
                .build());
    }

    @Override
    public void onEnable() {
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
        eventChannel.subscribeAlways(GroupMessageEvent.class, g -> {
            //监听群消息
            if (g.getMessage().contentToString().startsWith("教我")) {
                //截取"教我"后面的内容
                String teachContent = g.getMessage().contentToString().substring(2);
                ExternalResource replyMsg = null;
                try {
                    replyMsg = TeachMeImpl.getReplyImage(teachContent);
                }catch (FileNotFoundException e){
                    g.getGroup().sendMessage(new At(g.getSender().getId()).plus("教不会"));
                }

                Image image = null;
                if (replyMsg != null) {
                    image = g.getSender().uploadImage(replyMsg);
                }
                if (image != null) {
                    g.getGroup().sendMessage(new At(g.getSender().getId()).plus(image));
                }
            }

        });
        eventChannel.subscribeAlways(FriendMessageEvent.class, f -> {
            //监听好友消息
            if (f.getMessage().contentToString().startsWith("教我")) {
                //截取"教我"后面的内容
                String teachContent = f.getMessage().contentToString().substring(2);
                ExternalResource replyMsg = null;
                try {
                    replyMsg = TeachMeImpl.getReplyImage(teachContent);
                }catch (FileNotFoundException e){
                    f.getSender().sendMessage("教不会");
                }
                Image image = null;
                if (replyMsg != null) {
                    image = f.getSender().uploadImage(replyMsg);
                }
                if (image != null) {
                    f.getSender().sendMessage(image);
                }
            }
        });
    }
}
