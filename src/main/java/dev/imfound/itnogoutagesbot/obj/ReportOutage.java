package dev.imfound.itnogoutagesbot.obj;

import dev.imfound.itnogoutagesbot.enums.ReportOutagePhase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReportOutage {

    private String service;
    private String time;
    private String description;
    private ReportOutagePhase reportOutagePhase;


}
