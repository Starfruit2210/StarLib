package com.starfruit.starlib.utils;

import com.cryptomorin.xseries.XSound;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Common {

    private static final Pattern HEX_PATTERN = Pattern.compile("(?:&#|#)([A-Fa-f0-9]{6})");
    private static final DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###,###.##");


    public static String formatTime(long number) {
        long day = number / 86400;
        long hour = (number % 86400) / 3600;
        long minute = (number % 3600) / 60;
        long second = number % 60;

        StringBuilder builder = new StringBuilder();
        if (day > 0) builder.append(day).append("d ");
        if (hour > 0 || day > 0) builder.append(hour).append("h ");
        if (minute > 0 || hour > 0 || day > 0) builder.append(minute).append("m ");
        builder.append(second).append("s");

        return builder.toString();
    }

    public static String formatTime(int number) {
        int day = number / 86400;
        int hour = number / 3600;
        int minute = (number % 3600) / 60;
        int second = number % 60;

        // build
        StringBuilder builder = new StringBuilder();
        if (day > 0) builder.append(day).append("d ");
        if (hour > 0 || day > 0) builder.append(hour).append("h ");
        if (minute > 0 || hour > 0) builder.append(minute).append("m ");
        builder.append(second).append("s");

        return builder.toString();
    }

    public static double getRandomNumberBetween(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(max - min) + min;
    }

    public static int getRandomNumberBetween(int min, int max) {
        return ThreadLocalRandom.current().nextInt(max - min) + min;
    }

    public static boolean isValid(List<?> list, int index) {
        try {
            list.get(index);
            return true;
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public static void playSound(Player player, Sound sound) {
        playSound(player, XSound.of(sound));
    }

    public static void playSound(Player player, String sound) {
        Optional<XSound> optional = XSound.of(sound);
        optional.ifPresent(xSound -> playSound(player, xSound));
    }

    public static void playSound(Player player, XSound sound) {
        sound.play(player, 1.0f, 1.0f);
    }

    public static void broadcast(String message) {
        broadcast(message, null);
    }

    public static void broadcast(String message, @Nullable Placeholder placeholder) {
        if (placeholder != null) {
            message = placeholder.translate(message);
        }
        Bukkit.broadcastMessage(color(message));
    }

    public static void actionBar(Player player, String message) {
        actionBar(player, message, null);
    }

    public static void actionBar(Player player, String message, @Nullable Placeholder placeholder) {
        if (placeholder != null) {
            message = placeholder.translate(message);
        }
        // send the action bar message
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Common.color(message)));
    }

    public static void sendTitle(Player player, String title, String subTitle) {
        sendTitle(player, title, subTitle, null);
    }

    public static void sendTitle(Player player, String title, String subTitle, @Nullable Placeholder placeholder) {
        sendTitle(player, title, subTitle, 20, 40, 20, placeholder);
    }

    public static void sendTitle(Player player, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        sendTitle(player, title, subTitle, fadeIn, stay, fadeOut, null);
    }

    public static void sendTitle(Player player, String title, String subTitle, int fadeIn, int stay, int fadeOut, @Nullable Placeholder placeholder) {
        if (placeholder != null) {
            title = placeholder.translate(title);
            subTitle = placeholder.translate(subTitle);
        }
        player.sendTitle(Common.color(title), Common.color(subTitle), fadeIn, stay, fadeOut);
    }

    public static String digits(Object o) {
        return decimalFormat.format(o);
    }

    public static String format(FileConfiguration config, double number) {
        if (number >= 1_000_000_000_000_000_000_000_000.0) {
            return decimalFormat.format(number / 1_000_000_000_000_000_000_000_000.0) + config.getString("currency-notation.septillion", "Sp");
        } else if (number >= 1_000_000_000_000_000_000_000.0) {
            return decimalFormat.format(number / 1_000_000_000_000_000_000_000.0) + config.getString("currency-notation.sextillion", "Sx");
        } else if (number >= 1_000_000_000_000_000_000.0) {
            return decimalFormat.format(number / 1_000_000_000_000_000_000.0) + config.getString("currency-notation.quintillion", "Qi");
        } else if (number >= 1_000_000_000_000_000.0) {
            return decimalFormat.format(number / 1_000_000_000_000_000.0) + config.getString("currency-notation.quadrillion", "Qa");
        } else if (number >= 1_000_000_000_000.0) {
            return decimalFormat.format(number / 1_000_000_000_000.0) + config.getString("currency-notation.trillion", "T");
        } else if (number >= 1_000_000_000.0) {
            return decimalFormat.format(number / 1_000_000_000.0) + config.getString("currency-notation.billion", "B");
        } else if (number >= 1_000_000.0) {
            return decimalFormat.format(number / 1_000_000.0) + config.getString("currency-notation.million", "M");
        } else if (number >= 1_000.0) {
            return decimalFormat.format(number / 1_000.0) + config.getString("currency-notation.thousand", "K");
        } else {
            return decimalFormat.format(number);
        }
    }

    public static String format(double number) {
        if (number >= 1_000_000_000_000_000_000_000_000.0) {
            return decimalFormat.format(number / 1_000_000_000_000_000_000_000_000.0) + "Sp";
        } else if (number >= 1_000_000_000_000_000_000_000.0) {
            return decimalFormat.format(number / 1_000_000_000_000_000_000_000.0) + "Sx";
        } else if (number >= 1_000_000_000_000_000_000.0) {
            return decimalFormat.format(number / 1_000_000_000_000_000_000.0) + "Qi";
        } else if (number >= 1_000_000_000_000_000.0) {
            return decimalFormat.format(number / 1_000_000_000_000_000.0) + "Qa";
        } else if (number >= 1_000_000_000_000.0) {
            return decimalFormat.format(number / 1_000_000_000_000.0) + "T";
        } else if (number >= 1_000_000_000.0) {
            return decimalFormat.format(number / 1_000_000_000.0) + "B";
        } else if (number >= 1_000_000.0) {
            return decimalFormat.format(number / 1_000_000.0) + "M";
        } else if (number >= 1_000.0) {
            return decimalFormat.format(number / 1_000.0) + "K";
        } else {
            return decimalFormat.format(number);
        }
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static void addInventoryItem(Player player, ItemStack stack) {
        int amount = stack.getAmount();
        Material material = stack.getType();
        int maxStackSize = material.getMaxStackSize();

        // Clone the original stack to retain ItemMeta and other properties
        ItemStack originalStack = stack.clone();

        Map<Integer, ItemStack> leftovers = new HashMap<>();

        while (amount > 0) {
            // Determine the number of items for this part of the stack
            int stackAmount = Math.min(amount, maxStackSize);
            originalStack.setAmount(stackAmount);

            // Try to add the items to the player's inventory
            Map<Integer, ItemStack> left = player.getInventory().addItem(originalStack);
            if (!left.isEmpty()) {
                leftovers.putAll(left);
            }

            amount -= stackAmount;
        }

        // Drop any leftovers
        leftovers.values().forEach(item -> {
            player.getWorld().dropItemNaturally(player.getLocation(), item);
        });
    }

    public static void addInventoryItem(Player player, List<ItemStack> items) {
        for (ItemStack stack : items) {
            addInventoryItem(player, stack);
        }
    }

    public static void sendMessage(CommandSender sender, List<String> messages) {
        sendMessage(sender, messages, null);
    }

    public static void sendMessage(CommandSender sender, List<String> messages, Placeholder placeholder) {
        messages.forEach(message -> sendMessage(sender, message, placeholder));
    }

    public static void sendMessage(CommandSender sender, String message) {
        sendMessage(sender, message, null);
    }

    public static void sendMessage(CommandSender sender, String message, Placeholder placeholder) {
        if (message == null || message.isEmpty()) {
            return;
        }
        if (sender instanceof Player player) {
            message = papi(player, message);
        }
        if (placeholder != null) {
            message = placeholder.translate(message);
        }
        // Check if message starts with 'actionbar;'
        if (sender instanceof Player player) {
            if (message.startsWith("actionbar;")) {
                Common.actionBar(player, color(message.replace("actionbar;", "")));
            } else {
                sender.sendMessage(color(message));
            }
        } else {
            sender.sendMessage(color(message.replace("actionbar;", "")));
        }
    }

    public static List<String> color(List<String> messages) {
        return messages.stream().map(Common::color).collect(Collectors.toList());
    }

    public static String color(final String message) {
        final char colorChar = ChatColor.COLOR_CHAR;

        final Matcher matcher = HEX_PATTERN.matcher(message);
        final StringBuilder buffer = new StringBuilder(message.length() + 4 * 8);

        while (matcher.find()) {
            final String group = matcher.group(1);

            matcher.appendReplacement(buffer, colorChar + "x"
                    + colorChar + group.charAt(0) + colorChar + group.charAt(1)
                    + colorChar + group.charAt(2) + colorChar + group.charAt(3)
                    + colorChar + group.charAt(4) + colorChar + group.charAt(5));
        }

        // Step 3: Apply legacy formatting codes
        String legacyFormatted = matcher.appendTail(buffer).toString();
        return ChatColor.translateAlternateColorCodes('&', legacyFormatted);
    }

    public static String papi(Player player, String message) {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            return message;
        }
        return PlaceholderAPI.setPlaceholders(player, message);
    }

}
