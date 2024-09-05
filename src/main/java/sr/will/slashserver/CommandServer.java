package sr.will.slashserver;

import com.mojang.brigadier.Command;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import java.util.Optional;

public class CommandServer {
    public static BrigadierCommand getCommand(RegisteredServer server) {
        String name = server.getServerInfo().getName().toLowerCase();
        return new BrigadierCommand(BrigadierCommand.literalArgumentBuilder(name)
                .requires(cs -> cs.hasPermission("slashserver." + name))
                .requires(cs -> cs instanceof Player)
                .executes(ctx -> {
                    Player player = (Player) ctx.getSource();
                    Optional<ServerConnection> connection = player.getCurrentServer();

                    player.createConnectionRequest(server).fireAndForget();
                    return Command.SINGLE_SUCCESS;
                })
                .build());
    }
}
