package com.nobaboy.autobonk;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class AutoBonkCommands extends CommandBase {

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
        return "Usage: AutoBonk configs";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(args.length > 0) {
            switch(args[0]) {
                case "toggle":
                    if(!mainToggle) {
                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "[AutoBonk] " + EnumChatFormatting.GREEN + "enabled."));
                        mainToggle = true;
                    } else {
                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "[AutoBonk] " + EnumChatFormatting.RED + "disabled."));
                        mainToggle = false;
                    }
                    break;
                case "debug":
                    if(!debugMode) {
                        sender.addChatMessage(new ChatComponentText(AutoBonk.MOD_PREFIX + EnumChatFormatting.GREEN + "Debug Mode on."));
                        debugMode = true;
                    } else {
                        sender.addChatMessage(new ChatComponentText(AutoBonk.MOD_PREFIX + EnumChatFormatting.RED + "Debug Mode off."));
                        debugMode = false;
                    }
                    break;
            }
        } else {
            sender.addChatMessage(new ChatComponentText(AutoBonk.MOD_PREFIX + EnumChatFormatting.RED + "Usage: /autobonk <toggle, debug>"));
        }
    }
}
