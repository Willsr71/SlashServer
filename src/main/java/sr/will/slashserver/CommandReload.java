package sr.will.slashserver;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import net.kyori.text.TextComponent;
import net.kyori.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.optional.qual.MaybePresent;

public class CommandReload implements Command {
    private SlashServer plugin;

    public CommandReload(SlashServer plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(@MaybePresent CommandSource cs, @NonNull @MaybePresent String[] args) {
        plugin.reload();
        cs.sendMessage(TextComponent.of("Plugin reloaded!", TextColor.GREEN));
    }

    @Override
    public boolean hasPermission(@MaybePresent CommandSource cs, @NonNull @MaybePresent String[] args) {
        return cs.hasPermission("slashserver.reload");
    }
}
