package com.starfruit.starlib.worldguards.events;

import com.starfruit.starlib.worldguards.MovementWay;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

/**
 * event that is triggered after a player left a WorldGuard region
 */
public class RegionLeftEvent extends RegionEvent {
    /**
     * creates a new RegionLeftEvent
     *
     * @param region   the region the player has left
     * @param player   the player who triggered the event
     * @param movement the type of movement how the player left the region
     */
    public RegionLeftEvent(ProtectedRegion region, Player player, MovementWay movement, PlayerEvent parent) {
        super(region, player, movement, parent);
    }

}
