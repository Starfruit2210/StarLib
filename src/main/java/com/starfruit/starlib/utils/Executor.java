package com.starfruit.starlib.utils;

import com.starfruit.starlib.StarLib;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class Executor {

    public static BukkitTask sync(Runnable runnable) {
        return Bukkit.getScheduler().runTask(StarLib.instance(), runnable);
    }

    public static BukkitTask syncLater(long delay, Runnable runnable) {
        return Bukkit.getScheduler().runTaskLater(StarLib.instance(), runnable, delay);
    }

    public static BukkitTask syncTimer(long delay, long runEvery, Runnable runnable) {
        return Bukkit.getScheduler().runTaskTimer(StarLib.instance(), runnable, delay, runEvery);
    }

    public static BukkitTask async(Runnable runnable) {
        return Bukkit.getScheduler().runTaskAsynchronously(StarLib.instance(), runnable);
    }

    public static BukkitTask asyncLater(long delay, Runnable runnable) {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(StarLib.instance(), runnable, delay);
    }

    public static BukkitTask asyncTimer(long delay, long runEvery, Runnable runnable) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(StarLib.instance(), runnable, delay, runEvery);
    }
    
}
