package io.github.cfgametech.beans;

import java.util.ArrayList;
import java.util.List;

public class CreateChannelOrderResponseBuilder {
    private final List<CreateChannelOrderResponseEntry> response;
    
    public CreateChannelOrderResponseBuilder()
    {
        this.response = new ArrayList<CreateChannelOrderResponseEntry>();
    }
    
    public CreateChannelOrderResponseBuilder addEntry(CreateChannelOrderResponseEntry entry)
    {
        this.response.add(entry);
        return this;
    }
    
    public List<CreateChannelOrderResponseEntry> build()
    {
        return this.response;
    }
}
