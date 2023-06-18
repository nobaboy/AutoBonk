package com.nobaboy.autobonk;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class AutoBonkToggle extends CommandBase {

    public static boolean isToggled = true;

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public String getCommandName() {
        return "autobonktoggle";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Usage: On or Off toggle for AutoBonk.";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(!isToggled) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "[AutoBonk] " + EnumChatFormatting.GREEN + "enabled."));
            isToggled = true;
        } else {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "[AutoBonk] " + EnumChatFormatting.RED + "disabled."));
            isToggled = false;
        }
    }
}
