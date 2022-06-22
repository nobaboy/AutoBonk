package com.nobaboy.abonk;

import net.minecraft.client.Minecraft;
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
        return "abonktoggle";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(!isToggled) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "AutoBonk " + EnumChatFormatting.DARK_GRAY + "> " + EnumChatFormatting.GREEN + "AutoBonk is now enabled."));
            isToggled = true;
        } else {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "AutoBonk " + EnumChatFormatting.DARK_GRAY + "> " + EnumChatFormatting.RED + "AutoBonk is now disabled."));
            isToggled = false;
        }
    }
}
