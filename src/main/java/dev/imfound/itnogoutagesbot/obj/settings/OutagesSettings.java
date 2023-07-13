package dev.imfound.itnogoutagesbot.obj.settings;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OutagesSettings {

    private MySQLSettings mySQLSettings;
    private BOTSettings botSettings;

}
