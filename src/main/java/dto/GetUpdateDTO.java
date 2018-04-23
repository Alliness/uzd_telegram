package dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUpdateDTO {
    private boolean ok;

    private List<Result> result;

    public boolean isOk() {
        return ok;
    }

    public List<Result> getResult() {
        return result;
    }

    public static class Result {
        private Message message;

        private int updateId;


        public Message getMessage() {
            return message;
        }

        public int getUpdateId() {
            return updateId;
        }

        public static class Message {
            private Chat chat;

            private int date;

            private List<Entities> entities;

            private From from;

            @SerializedName("message_id")
            private int messageId;

            private String text;


            public Chat getChat() {
                return chat;
            }

            public int getDate() {
                return date;
            }

            public List<Entities> getEntities() {
                return entities;
            }

            public From getFrom() {
                return from;
            }

            public int getMessageId() {
                return messageId;
            }

            public String getText() {
                return text;
            }

            public static class Chat {
                @SerializedName("first_name")
                private String firstName;

                private int id;

                @SerializedName("last_name")
                private String lastName;

                private String type;


                public String getFirstName() {
                    return firstName;
                }

                public int getId() {
                    return id;
                }

                public String getLastName() {
                    return lastName;
                }

                public String getType() {
                    return type;
                }
            }

            public static class Entities {
                private int length;

                private int offset;

                private String type;


                public int getLength() {
                    return length;
                }

                public int getOffset() {
                    return offset;
                }

                public String getType() {
                    return type;
                }
            }

            public static class From {

                @SerializedName("first_name")
                private String firstName;

                private int id;

                @SerializedName("is_bot")
                private boolean isBot;

                @SerializedName("language_code")
                private String languageCode;

                private String lastName;

                public String getFirstName() {
                    return firstName;
                }

                public int getId() {
                    return id;
                }

                public boolean isBot() {
                    return isBot;
                }

                public String getLanguageCode() {
                    return languageCode;
                }

                public String getLastName() {
                    return lastName;
                }
            }
        }
    }
}
