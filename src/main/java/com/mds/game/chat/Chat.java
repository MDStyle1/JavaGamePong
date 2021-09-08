package com.mds.game.chat;

import com.mds.game.Main;
import com.mds.game.client.Request;

import java.util.ArrayList;
import java.util.List;

public class Chat implements Runnable{
    private List<String> list = new ArrayList<String>();
    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
    private EventChat eventChat;
    private Main main;
    private boolean stop=false;
    private Request request;
    public Chat(Main main){
        this.main = main;
        request = main.getRequest();
    }

    public void setEventChat(EventChat eventChat) {
        this.eventChat = eventChat;
    }

    public List<String> getList() {
        return new ArrayList<String>(list);
    }

    private void listenMessage(){
        while(!stop){
            RequestChat requestChat = new RequestChat();
            if(chatMessageList.size()==0){
                requestChat.setId(0);
            } else {
                requestChat.setId(chatMessageList.get(chatMessageList.size()-1).getId()+1);
            }

            if(request.<RequestChatMessage,RequestChat>postRequest(main.getHost()+"/chat/listen",RequestChatMessage.class,requestChat)){
                RequestChatMessage requestChatMessage = (RequestChatMessage)request.answer;
                if(requestChatMessage!=null){
                    List<String> list1 = new ArrayList<String>();
                    requestChatMessage.getChatMessageList().stream().forEach(item->{chatMessageList.add(item);list.add(item.getMessage());list1.add(item.getMessage());});
                    if(eventChat!=null)eventChat.addMessage(list1);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        listenMessage();
    }
    public void stopChat(){
        stop=true;
    }
    public void sendMessage(String message){
        ChatSendMessage chatSendMessage = new ChatSendMessage();
        chatSendMessage.setMessage(message);
        request.postRequest(main.getHost()+"/chat/send",String.class,chatSendMessage);
    }

    public interface EventChat{
        void addMessage(List<String> list);
    }
}
