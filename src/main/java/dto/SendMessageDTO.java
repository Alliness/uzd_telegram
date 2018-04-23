package dto;

import com.google.gson.annotations.SerializedName;

public class SendMessageDTO {

    @SerializedName("chat_id")
    private int    chatId;
    @SerializedName("text")
    private String text;

    public SendMessageDTO(int chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
