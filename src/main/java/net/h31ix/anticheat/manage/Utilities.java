/*
 * AntiCheat for Bukkit.
 * Copyright (C) 2012 H31IX http://h31ix.net
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package net.h31ix.anticheat.manage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.h31ix.anticheat.Anticheat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public final class Utilities 
{
    private static final List<Material> INSTANT_BREAK = new ArrayList<Material>();
    private static final List<Material> FOOD = new ArrayList<Material>();
    
    private Utilities()
    {
        
    }    
    public static void alert(String [] message)
    {
        for(Player p : Bukkit.getServer().getOnlinePlayers())
        {
            if(p.hasPermission("anticheat.alert") || p.hasPermission("anticheat.admin") || p.hasPermission("anticheat.mod") || p.isOp())
            {
               for(String msg : message)
               {
                   p.sendMessage(msg);
               }
            }
        } 
        if(Anticheat.getManager().getConfiguration().logConsole())
        {
           Logger logger = Logger.getLogger("Minecraft");
           for(String msg : message)
           {
               logger.log(Level.INFO,ChatColor.stripColor(msg));
           }            
        }
        if(Anticheat.getManager().getConfiguration().getFileLogLevel() > 0)
        {
           for(String msg : message)
           {
               Anticheat.getManager().fileLog(ChatColor.stripColor(msg));
           }             
        }
    }
    public static boolean cantStandAt(Block block)
    {
        return !canStand(block) && cantStandClose(block) && cantStandFar(block);
    }
    private static boolean cantStandClose(Block block)
    {
        return !canStand(block.getRelative(BlockFace.NORTH)) && !canStand(block.getRelative(BlockFace.EAST)) && !canStand(block.getRelative(BlockFace.SOUTH)) && !canStand(block.getRelative(BlockFace.WEST));
    }
    private static boolean cantStandFar(Block block)
    {
        return !canStand(block.getRelative(BlockFace.NORTH_WEST)) && !canStand(block.getRelative(BlockFace.NORTH_EAST)) && !canStand(block.getRelative(BlockFace.SOUTH_WEST)) && !canStand(block.getRelative(BlockFace.SOUTH_EAST));
    }
    public static boolean canStand(Block block)
    {
        return !(block.isLiquid() || block.getType() == Material.AIR);
    }
    public static boolean isInstantBreak(Material m)
    {
        return INSTANT_BREAK.contains(m);
    }
    public static boolean isFood(Material m)
    {
        return FOOD.contains(m);
    }   
    public static boolean sprintFly(Player player)
    {
        return player.isSprinting() || player.isFlying();       
    }
    public static boolean isOnLilyPad(Player player)
    {
        Block block = player.getLocation().getBlock();
        Material lily = Material.WATER_LILY;
        return block.getType() == lily || block.getRelative(BlockFace.NORTH).getType() == lily || block.getRelative(BlockFace.SOUTH).getType() == lily || block.getRelative(BlockFace.EAST).getType() == lily || block.getRelative(BlockFace.WEST).getType() == lily;
    }
    public static boolean isInt(String string)
    {
        boolean x = false;
        try {
            Integer.parseInt(string);
            x = true;
        }
        catch(Exception ex)
        {
        }
        return x;
    }
    static 
    {
        INSTANT_BREAK.add(Material.RED_MUSHROOM);
        INSTANT_BREAK.add(Material.RED_ROSE);
        INSTANT_BREAK.add(Material.BROWN_MUSHROOM); 
        INSTANT_BREAK.add(Material.YELLOW_FLOWER); 
        INSTANT_BREAK.add(Material.REDSTONE); 
        INSTANT_BREAK.add(Material.REDSTONE_TORCH_OFF); 
        INSTANT_BREAK.add(Material.REDSTONE_TORCH_ON); 
        INSTANT_BREAK.add(Material.REDSTONE_WIRE);  
        INSTANT_BREAK.add(Material.LONG_GRASS);
        INSTANT_BREAK.add(Material.PAINTING); 
        INSTANT_BREAK.add(Material.WHEAT); 
        INSTANT_BREAK.add(Material.SUGAR_CANE); 
        INSTANT_BREAK.add(Material.SUGAR_CANE_BLOCK); 
        INSTANT_BREAK.add(Material.DIODE); 
        INSTANT_BREAK.add(Material.DIODE_BLOCK_OFF); 
        INSTANT_BREAK.add(Material.DIODE_BLOCK_ON);
        INSTANT_BREAK.add(Material.SAPLING);
        INSTANT_BREAK.add(Material.TORCH);
        INSTANT_BREAK.add(Material.CROPS);
        INSTANT_BREAK.add(Material.SNOW);   
        FOOD.add(Material.COOKED_BEEF);
        FOOD.add(Material.COOKED_CHICKEN);
        FOOD.add(Material.COOKED_FISH);
        FOOD.add(Material.GRILLED_PORK);
        FOOD.add(Material.PORK);
        FOOD.add(Material.MUSHROOM_SOUP);
        FOOD.add(Material.RAW_BEEF);
        FOOD.add(Material.RAW_CHICKEN);
        FOOD.add(Material.RAW_FISH);
        FOOD.add(Material.APPLE);
        FOOD.add(Material.GOLDEN_APPLE);
        FOOD.add(Material.MELON);
        FOOD.add(Material.COOKIE);
        FOOD.add(Material.BREAD);
        FOOD.add(Material.SPIDER_EYE);
        FOOD.add(Material.ROTTEN_FLESH);           
    }
}
