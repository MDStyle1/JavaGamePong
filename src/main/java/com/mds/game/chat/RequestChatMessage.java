package com.mds.game.chat;

import java.util.List;

public class RequestChatMessage {
    private List<ChatMessage> chatMessageList;

    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }
}
