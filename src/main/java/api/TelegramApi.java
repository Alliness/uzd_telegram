package api;

import core.ConfigLoader;
import core.HttpClient;
import core.HttpClientHeader;
import core.Serializable;
import dto.GetUpdateDTO;
import dto.SendMessageDTO;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TelegramApi {

    private static final Logger log = Logger.getLogger(TelegramApi.class);

    private static final String TOKEN   = ConfigLoader.getInstance().getProperty("telegram.token");
    private static final String API_URL = ConfigLoader.getInstance().getProperty("telegram.endpoint");

    private static final String GET_UPDATES  = "/getUpdates";
    private static final String SEND_MESSAGE = "/sendMessage";

    private HttpClient http;
    private String     urlRequest;

    private GetUpdateDTO cache;
    private int lastMessageId = 0;


    public TelegramApi() {

        urlRequest = API_URL + TOKEN;
        http = new HttpClient();
    }

    public void getUpdates() {
        try {

            http.addHeader(HttpClientHeader.CONTENT_TYPE_APPLICATION_JSON)
                .get(urlRequest + GET_UPDATES)
                .execute();
            cache = Serializable.deserialize(http.toJSON(), GetUpdateDTO.class);
        } catch (Exception e) {
            log.error(e);
        }
    }


    public GetUpdateDTO.Result getLastMessage() {
        if (cache != null) {
            List<GetUpdateDTO.Result>     messages = cache.getResult();
            Optional<GetUpdateDTO.Result> msg      = messages.stream().max(Comparator.comparingInt(o -> o.getMessage().getMessageId()));
            if (msg.isPresent()) {
                return msg.get();
            }
        }
        return null;
    }

    public int getLastMessageId() {
        return lastMessageId;
    }

    public void messageNotify() {
        try {
            if (getLastMessage() != null) {
                if (getLastMessage().getMessage().getMessageId() != lastMessageId) {
                    log.info(getLastMessage().getMessage().getText());
                    lastMessageId = getLastMessage().getMessage().getMessageId();
                    if (getLastMessage().getMessage().getText().equals("Gavno")) {
                        sendMessage(getLastMessage().getMessage().getFrom().getId(), "Sam Gavno");
                    }
                }
            }
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }
    }

    private void sendMessage(int chatId, String message) {
        SendMessageDTO data = new SendMessageDTO(chatId, message);
        System.out.println(Serializable.serialize(data));

        http.addHeader(HttpClientHeader.CONTENT_TYPE_APPLICATION_JSON)
            .post(urlRequest + SEND_MESSAGE, Serializable.serialize(data))
            .execute();

        System.out.println(http.toString());
    }
}
