package sr.will.slashserver;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.text.TextComponent;
import net.kyori.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.optional.qual.MaybePresent;

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

        ((Player) cs).createConnectionRequest(server).fireAndForget();
        cs.sendMessage(TextComponent.of("You have been sent to " + server.getServerInfo().getName(), TextColor.RED));
    }
}
