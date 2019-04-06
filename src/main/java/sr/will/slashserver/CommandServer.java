package sr.will.slashserver;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.text.TextComponent;
import net.kyori.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.optional.qual.MaybePresent;

import java.util.Optional;

public class CommandServer implements Command {
    private RegisteredServer server;

    public CommandServer(RegisteredServer server) {
        this.server = server;
    }

    @Override
    public void execute(@MaybePresent CommandSource cs, @NonNull @MaybePresent String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage(TextComponent.of("Player only command!", TextColor.RED));
            return;
        }

        Player player = (Player) cs;
        Optional<ServerConnection> connection = player.getCurrentServer();
        if (connection.isPresent() && connection.get().getServer() == server) {
            cs.sendMessage(TextComponent.of("You are already connected to this server!", TextColor.RED));
            return;
        }

        player.createConnectionRequest(server).fireAndForget();
        cs.sendMessage(TextComponent.of("You have been sent to " + server.getServerInfo().getName(), TextColor.GREEN));
    }

    @Override
    public boolean hasPermission(@MaybePresent CommandSource cs, @NonNull @MaybePresent String[] args) {
        return cs.hasPermission("slashserver." + server.getServerInfo().getName().toLowerCase());
    }
}
