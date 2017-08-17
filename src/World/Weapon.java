package World;

public class Weapon extends Item {
    
    //weapon description
    protected String abilityDescription;    //describes weapons main ability
    protected String abilityAddon;          //describes weapons passive addon ability
    
    //weapon stats
    protected int damage;                   //weapons damage
    protected int damageModifier;           //weapons extra damage
    protected int durability;               //weapons dirability
    
    //basic constructor
    protected Weapon() {
        durability = 100;
        damageModifier = 0;
    }
    
    // Applies the ability of the weapon
    protected boolean ability() {
        return true;
    }
}
