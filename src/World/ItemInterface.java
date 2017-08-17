package World;

public interface ItemInterface {
    
    //--------------------------------------------------------------------------
    
    //raw materials - ore
    String IRON_ORE = "iron ore";                 // mine: iron cluster
    String COPPER_ORE = "copper ore";             // mine: copper cluster
    String TIN_ORE = "tin ore";                   // drop: tin elemental

    String GOLD_ORE = "gold ore";                 // mine: lava rocks
    String TITANIUM_ORE = "titanium ore";         // drop: titanium elemental
    String METEORITE_ORE = "meteorite ore";       // mine: meteorite rocks
    String ADAMANTIUM_ORE = "adamantium ore";     // drop: robot direwolf
    
    //raw materials - wood
    String BIRCH_LOG = "birch log";               // chop: birch tree
    String PINE_LOG = "pine log";                 // chop: pine tree
    String OAK_LOG = "oak log";                   // chop: crates/houses
    String MAPLE_LOG = "maple log";               // chop: maple tree
    String WILLOW_LOG = "willow log";             // chop: willow tree
    String WHITEWOOD_LOG = "white wood log";      // chop: tree spirit
            
    //raw materials - hide
    String RABBIT_HIDE = "rabbit hide";           // skin: rabbits
    String COW_HIDE = "cow hide";                 // skin: cows
    String FOX_HIDE = "fox hide";                 // skin: foxes
    String WOLF_HIDE = "wolf hide";               // skin: wolves
    String BEAR_HIDE = "bear hide";               // skin: bears
    String DRAGON_HIDE = "dragon hide";           // skin: dragons
    
    //--------------------------------------------------------------------------
    
    //processed materials - ingot
    String IRON_INGOT = "iron ingot";                 // level: 1 || tool: cheap        || weapon: sharp
    String COPPER_INGOT = "copper ingot";             // level: 2 || tool: experienced  || weapon: experienced
    String TIN_INGOT = "tin ingot";                   // level: 3 || tool: dense        || weapon: 
    String BRONZE_INGOT = "bronze ingot";             // level: 4 || tool: momentum     || weapon: shocking
    String GOLD_INGOT = "gold ingot";                 // level: 5 || tool: luck         || weapon: luck
    String TITANIUM_INGOT = "titanium ingot";         // level: 6 || tool: magnetic     || weapon: beheading
    String METEORITE_INGOT = "meteorite ingot";       // level: 7 || tool: unbreakable  || weapon: unbreakable
    String ADAMANTIUM_INGOT = "adamantium ingot";     // level: 8 || tool: unnatural    || weapon: 
    
    //processed materials - plank
    String BIRCH_PLANK = "birch plank";               // level: 1 || tool: cheap        || weapon: fractured
    String PINE_PLANK = "pine plank";                 // level: 2 || tool: experienced  || weapon: experienced
    String OAK_PLANK = "oak plank";                   // level: 3 || tool: ecological   || weapon: ecological
    String MAPLE_PLANK = "maple plank";               // level: 4 || tool: writable     || weapon: writable
    String WILLOW_PLANK = "willow plank";             // level: 5 || tool: luck         || weapon: luck
    String WHITEWOOD_PLANK = "white wood plank";      // level: 6 || tool: ghost        || weapon: 
            
    //processed materials - leather
    String STIFF_LEATHER = "stiff leather";                 // level: 1 || tool: cheap          || weapon: 
    String THICK_LEATHER = "thick leather";                 // level: 2 || tool: experienced    || weapon: experienced
    String WORKED_LEATHER = "worked leather";               // level: 3 || tool: magical        || weapon: magical
    String CURED_LEATHER = "cured leather";                 // level: 4 || tool: momentum       || weapon: flammable
    String HARDENED_LEATHER = "hardened leather";           // level: 5 || tool: aquadynamic    || weapon: 
    String REINFORCED_LEATHER = "reinforced leather";       // level: 6 || tool: luck           || weapon: luck
    
    //--------------------------------------------------------------------------
    
    //abilities description - tool
        //metal
    String CHEAP = "Gain extra durability when repairing";
    String EXPERIENCED = "Grants bonus experience when used";
    String DENSE = "Grants a chance to use less durability when used";
    String MOMENTUM = "Increases tools efficiency";
    String LUCK = "Grants a chances for higher drops";
    String MAGNETIC = "Pulls items towards you";
    String UNBREAKABLE = "Doesn't lose durability";
    String UNNATURAL = "Tool is semi-instant";
        //wood
    String ECOLOGICAL = "Restores durability over time";
    String WRITABLE = "Adds an extra modifier";
    String GHOST = "Prevents monsters from seeing you while using the tool";
        //leather
    String MAGICAL = "Gives random stat bonuses over time";
    String AQUADYNAMIC = "Operates at same speed under water and fasster under the rain";
    
    //--------------------------------------------------------------------------
    
    //abilities description - weapon
        //metal
    String SHARP = "Causes enemy to bleed for a bit";
    String SHOCKING = "builds electric charge that will deal bonus damage to enemies when discharged";
    String BEHEADING = "Grants a chance to drop enemys' head when it dies";
        //wood
    String FRACTURED = "Grants bonus damage to weapon";
        //leather
    String FLAMMABLE = "Sets enemies on fire";
}
