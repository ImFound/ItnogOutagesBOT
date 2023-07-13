package dev.imfound.itnogoutagesbot.obj.settings;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BOTSettings {

    private String username;
    private String token;
    private long chatId;
    private int threadId;

}
