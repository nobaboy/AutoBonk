package com.nobaboy.autobonk;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mod(modid = "autobonk", name = "AutoBonk", version = "1.3.3", acceptedMinecraftVersions = "[1.8.9]")
public class AutoBonk
{
    public static final String MOD_ID = "autobonk";
    public static final String MOD_NAME = "AutoBonk";
    public static final String MOD_VERSION = "1.3.3";

    private int boopNumber = 1;
    private final String playerIGN = Minecraft.getMinecraft().getSession().getUsername();

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new AutoBonkToggle());
    }

    @SubscribeEvent
    public void onChatReceived(final ClientChatReceivedEvent e) {
        String message = EnumChatFormatting.getTextWithoutFormattingCodes(e.message.getUnformattedText());

        Pattern chatPattern = Pattern.compile("^(?<type>Party|Guild|From|Co-op)(?: >)? (?<rank>\\[[A-Z+]+\\] )?(?<username>[A-z0-9_]+)(?<grank> \\[[A-z0-9 ]+\\])?: (?<message>.+)");
        Matcher chatMatcher = chatPattern.matcher(message);

        if (!chatMatcher.find()) return;
        String type = chatMatcher.group("type");
        String realMessage = chatMatcher.group("message");
        String realIGN = chatMatcher.group("username");

        final boolean checkForMessage = realMessage.toLowerCase().contains("boop");

        String command;

        if(!AutoBonkToggle.isToggled) return;
        if(!checkForMessage) return;
        switch(type) {
            case "Guild":
                command = "/gc";
                break;
            case "Party":
                command = "/pc";
                break;
            case "From":
                command = "/r";
                break;
            case "Co-op":
                command = "/cc";
                break;
            default:
                System.out.println("[" + MOD_NAME + "] Chat matched without detecting type.");
                return;
        }
        if(realIGN.equals(playerIGN)) return;
        if (boopNumber == 1) {
            boopNumber++;
            Minecraft.getMinecraft().thePlayer.sendChatMessage(command + " Bonk!");
            Minecraft.getMinecraft().thePlayer.playSound(MOD_ID + ":bonk", 1.0F, 1.0F);
        } else {
            String alphanumericString = RandomStringUtils.randomAlphanumeric(6);
            boopNumber++;
            Minecraft.getMinecraft().thePlayer.sendChatMessage(command + " Bonk! x" + boopNumber + " @" + alphanumericString);
            Minecraft.getMinecraft().thePlayer.playSound(MOD_ID + ":bonk", 10.0F, 1.0F);
        }
    }
}