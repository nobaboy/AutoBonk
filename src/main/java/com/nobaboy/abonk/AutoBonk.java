package com.nobaboy.abonk;

import com.nobaboy.abonk.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptedMinecraftVersions = "[1.8.9]")
public class AutoBonk
{
    private int boopNumber = 0;
    private final String playerIGN = Minecraft.getMinecraft().getSession().getUsername();

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new AutoBonkToggle());
    }

    @SubscribeEvent
    public void onChatReceived(final ClientChatReceivedEvent e) {
        String message = EnumChatFormatting.getTextWithoutFormattingCodes(e.message.getUnformattedText());

        Pattern chatPattern = Pattern.compile("(?<type>(Guild|Party)) > (?:\\[(?<rank>.+)\\] |)(?<username>.+): (?<message>.+)");
        Matcher chatMatcher = chatPattern.matcher(message);

        if (!chatMatcher.find()) return;
        String type = chatMatcher.group("type");
        String realMessage = chatMatcher.group("message");
        String realIGN = chatMatcher.group("username").toLowerCase();

        final boolean checkForMessage = realMessage.toLowerCase().contains("boop!") || realMessage.toLowerCase().contains("boop");

        if (type.startsWith("Guild") && checkForMessage) {
            if(!AutoBonkToggle.isToggled) return;
            if(realIGN.equalsIgnoreCase(playerIGN)) return;
            if (boopNumber == 0) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/gc Bonk!");
                Minecraft.getMinecraft().thePlayer.playSound("abonk:bonk", 1.0F, 1.0F);
                boopNumber = 1;
            } else {
                boopNumber++;
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/gc Bonk! x" + boopNumber);
                Minecraft.getMinecraft().thePlayer.playSound("abonk:bonk", 1.0F, 1.0F);
            }
        }

        if (type.startsWith("Party") && checkForMessage) {
            if(!AutoBonkToggle.isToggled) return;
            if(realIGN.equalsIgnoreCase(playerIGN)) return;
            if (boopNumber == 0) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/pc Bonk!");
                Minecraft.getMinecraft().thePlayer.playSound("abonk:bonk", 1.0F, 1.0F);
                boopNumber = 1;
            } else {
                boopNumber++;
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/pc Bonk! x" + boopNumber);
                Minecraft.getMinecraft().thePlayer.playSound("abonk:bonk", 1.0F, 1.0F);
            }
        }
    }

    @SubscribeEvent
    public void onChatReceivedDirect(final ClientChatReceivedEvent e) {
        String message = EnumChatFormatting.getTextWithoutFormattingCodes(e.message.getUnformattedText());

        Pattern dmPattern = Pattern.compile("(?<type>(From|To)) (?:\\[(?<rank>.+)\\] |)(?<username>.+): (?<message>.+)");
        Matcher dmMatcher = dmPattern.matcher(message);

        if (!dmMatcher.find()) return;
        String type = dmMatcher.group("type");
        String realMessage = dmMatcher.group("message");
        String realIGN = dmMatcher.group("username").toLowerCase();

        final boolean checkForDMMessage = realMessage.toLowerCase().contains("boop!") || realMessage.toLowerCase().contains("boop");

        if (type.startsWith("From") && checkForDMMessage) {
            if(!AutoBonkToggle.isToggled) return;
            if(realIGN.equalsIgnoreCase(playerIGN)) return;
            if (boopNumber == 0) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/r Bonk!");
                Minecraft.getMinecraft().thePlayer.playSound("abonk:bonk", 1.0F, 1.0F);
                boopNumber = 1;
            } else {
                boopNumber++;
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/r Bonk! x" + boopNumber);
                Minecraft.getMinecraft().thePlayer.playSound("abonk:bonk", 1.0F, 1.0F);
            }
        }
    }
}