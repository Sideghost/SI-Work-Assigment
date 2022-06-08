package isel.sisinf.grp3.ui;

import java.util.Map;

public class CommandsOO implements IComandsOO{
    @Override
    public void action() {

    }

    @Override
    public String getSintax() {
        return null;
    }

    public Map<String, CommandsOO> getCommandsOO() {
        return Map.ofEntries(Map.entry("EXIT" , this.action() {
            String something = "2";
        };),);
    }

}


