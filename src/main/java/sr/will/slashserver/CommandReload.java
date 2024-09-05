package sr.will.slashserver;

import com.mojang.brigadier.Command;
import com.velocitypowered.api.command.BrigadierCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class CommandReload {
    public static BrigadierCommand getCommand(SlashServer plugin) {
        return new BrigadierCommand(BrigadierCommand.literalArgumentBuilder("ssreload")
                .requires(cs -> cs.hasPermission("slashserver.reload"))
                .executes(ctx -> {
                    plugin.reload();
                    ctx.getSource().sendMessage(Component.text("Plugin reloaded!", NamedTextColor.GREEN));
                    return Command.SINGLE_SUCCESS;
                })
                .build());
    }
}
