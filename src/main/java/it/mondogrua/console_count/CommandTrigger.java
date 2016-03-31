package it.mondogrua.console_count;

import it.mondogrua.utils.PluggableAdaptor;

public class CommandTrigger implements StringListener {

    private PluggableAdaptor command;

    public CommandTrigger(PluggableAdaptor command) {
        super();
        this.command = command;
    }

    @Override
    public void update(String string) {
        command.execute();
    }

}
