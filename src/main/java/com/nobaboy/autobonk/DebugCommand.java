package com.nobaboy.autobonk;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class DebugCommand extends CommandBase {
    public static boolean isDebugToggled = false;

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public String getCommandName() {
        return "autobonkdebug";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Usage: Debug toggle for AutoBonk.";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(!isDebugToggled) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "[AutoBonk] " + EnumChatFormatting.GREEN + "Debug Mode on."));
            isDebugToggled = true;
        } else {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "[AutoBonk] " + EnumChatFormatting.RED + "Debug Mode off."));
            isDebugToggled = false;
        }
    }
}
