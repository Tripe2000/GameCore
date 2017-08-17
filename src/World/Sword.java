package World;

public class Sword extends Weapon {
    
    //primary parts
    protected String hilt;          //
    protected String guard;         //
    protected String blade;         //
    
    //secondary parts
    protected String pommel;        //
    protected String grip;          //
    protected String edge;          //
    
    protected Sword() {
        abilityDescription = "Makes target bleed";
        damage = 10;
    }
}
