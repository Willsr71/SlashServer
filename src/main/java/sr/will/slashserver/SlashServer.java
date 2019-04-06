package sr.will.slashserver;


import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;

@Plugin(id = PluginInfo.ID, name = PluginInfo.NAME, version = PluginInfo.VERSION, description = PluginInfo.DESCRIPTION)
public class SlashServer {
    @Inject
    private ProxyServer proxy;

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        // The problem with this method is that it doesn't account for servers added after proxy initialization
        // We can either create a task to check every so often or listen to a command event and check there
        // No command event exists yet, and not that many plugins exist that edit servers after initialization
        // So this is fine for now
        for (RegisteredServer server : proxy.getAllServers()) {
            proxy.getCommandManager().register(new CommandServer(server), server.getServerInfo().getName().toLowerCase());
        }
    }
}
