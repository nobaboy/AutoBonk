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

@Mod(modid = "autobonk", name = "AutoBonk", version = "1.3.6", acceptedMinecraftVersions = "[1.8.9]")
public class AutoBonk
{
    public static final String MOD_ID = "autobonk";
    public static final String MOD_NAME = "AutoBonk";
    public static final String MOD_VERSION = "1.3.6";
    public static final String MOD_PREFIX = EnumChatFormatting.GRAY + "[AutoBonk] ";

    Pattern chatPattern = Pattern.compile("^(?<type>Party|Guild|From|Co-op)(?: >)? (?:\\[[A-Z+]+\\] )?(?<username>[A-z0-9_]+)(?: \\[[A-z0-9 ]+\\])?: (?<message>.+)");

    private int boopNumber = 0;
    private final String playerIGN = Minecraft.getMinecraft().getSession().getUsername();

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new AutoBonkCommands());
    }

    @SubscribeEvent
    public void onChatReceived(final ClientChatReceivedEvent e) {
        if(!AutoBonkCommands.mainToggle) return;
        String receivedMessage = EnumChatFormatting.getTextWithoutFormattingCodes(e.message.getUnformattedText());
        Matcher chatMatcher = chatPattern.matcher(receivedMessage);
        if(!chatMatcher.find()) return;

        String type = chatMatcher.group("type");
        String message = chatMatcher.group("message");
        String sender = chatMatcher.group("username");
        if(!message.toLowerCase().contains("boop") || sender.equals(playerIGN)) return;

        String alphanumericString = RandomStringUtils.randomAlphanumeric(6);
        if(AutoBonkCommands.debugMode) System.out.println("[" + MOD_NAME + "] Matched in [" + type + "], boop sent by " + sender);

        String command = type.equals("Guild") ? "/gc"
                : type.equals("Party") ? "/pc"
                : type.equals("From") ? "/r"
                : type.equals("Co-op") ? "/cc"
                : null;

        if(command == null) {
            System.out.println("[" + MOD_NAME + "] Matched message in unknown channel: " + type);
            return;
        }

        boopNumber++;
        Minecraft.getMinecraft().thePlayer.sendChatMessage(command + " Bonk!" + (boopNumber == 0 ? "" : " x" + boopNumber + " - @" + alphanumericString));
        Minecraft.getMinecraft().thePlayer.playSound(MOD_ID + ":bonk", 1.0F, 1.0F);
    }
}