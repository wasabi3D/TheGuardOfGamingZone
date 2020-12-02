package Utilities;


import net.dv8tion.jda.api.entities.Message;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MessageWithLifetime extends Thread{

    private Queue<Delete> queue = new ArrayDeque<>();

    @Override
    public void start(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for(int i = 0; i<queue.size();i++) {
                    if (queue.peek().getIfDone()) {
                        queue.poll();
                    } else {
                        break;
                    }
                }

            }}, 400, 400);
    }

    public void addMsgQueue(Message msg){
        queue.add(new Delete(msg));
    }

}

class Delete extends Thread{
    Message msg;
    private boolean isDone = false;

    public Delete(Message message){
        msg = message;
        this.start();
    }

    @Override
    public void start(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch(Exception e){}
        msg.delete().queue();
        isDone = true;
    }

    public boolean getIfDone(){
        return isDone;
    }

}
