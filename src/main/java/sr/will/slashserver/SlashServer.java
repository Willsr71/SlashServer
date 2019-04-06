package sr.will.slashserver;


import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import java.util.ArrayList;
import java.util.List;

@Plugin(id = PluginInfo.ID, name = PluginInfo.NAME, version = PluginInfo.VERSION, description = PluginInfo.DESCRIPTION)
public class SlashServer {
    @Inject
    private ProxyServer proxy;
    private List<String> registeredCommands = new ArrayList<>();

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        proxy.getCommandManager().register(new CommandReload(this), "ssreload");

        reload();
    }

    public void reload() {
        registeredCommands.forEach(name -> proxy.getCommandManager().unregister(name));
        registeredCommands.clear();

        for (RegisteredServer server : proxy.getAllServers()) {
            String name = server.getServerInfo().getName().toLowerCase();
            proxy.getCommandManager().register(new CommandServer(server), name);
            registeredCommands.add(name);
        }
    }
}
