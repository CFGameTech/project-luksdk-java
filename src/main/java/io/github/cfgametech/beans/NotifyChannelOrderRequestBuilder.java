package io.github.cfgametech.beans;

import java.util.ArrayList;
import java.util.List;

public class NotifyChannelOrderRequestBuilder {
    private final List<NotifyChannelOrderResponseEntry> response;

    public NotifyChannelOrderRequestBuilder()
    {
        this.response = new ArrayList<NotifyChannelOrderResponseEntry>();
    }

    public NotifyChannelOrderRequestBuilder addEntry(NotifyChannelOrderResponseEntry entry)
    {
        this.response.add(entry);
        return this;
    }

    public List<NotifyChannelOrderResponseEntry> build()
    {
        return this.response;
    }
}
