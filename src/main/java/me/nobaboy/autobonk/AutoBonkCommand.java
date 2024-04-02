package me.nobaboy.autobonk;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class AutoBonkCommand extends CommandBase {

    public static boolean mainToggle = true;
    public static boolean debugMode = false;

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public String getCommandName() {
        return "autobonk";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Usage: /autobonk <toggle, debug>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(args.length < 1) {
            sender.addChatMessage(new ChatComponentText(AutoBonk.MOD_PREFIX + EnumChatFormatting.RED + getCommandUsage(sender)));
        } else {
            switch(args[0].toLowerCase()) {
                case "toggle":
                    mainToggle = !mainToggle;
                    sender.addChatMessage(new ChatComponentText(AutoBonk.MOD_PREFIX + (mainToggle ? EnumChatFormatting.GREEN + "enabled." : EnumChatFormatting.RED + "disabled.")));
                    break;
                case "debug":
                    debugMode = !debugMode;
                    sender.addChatMessage(new ChatComponentText(AutoBonk.MOD_PREFIX + (debugMode ? EnumChatFormatting.GREEN + "Debug Mode enabled." : EnumChatFormatting.RED + "Debug Mode disabled.")));
                    break;
            }
        }
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, "toggle", "debug") : null;
    }
}
