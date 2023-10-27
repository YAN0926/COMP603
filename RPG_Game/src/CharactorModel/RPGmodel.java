/** *
 * This RPGmodel class provides a general blueprint for all RPG characters.
 * It contains common properties like name, gender, health, etc., and defines an abstract attack method that must be implemented by its subclasses.
 *
 * Name: Chi Yan Cheung
 * SID: 15950216
 * Ranger Ng
 * SID: 20124370
 *
 * COMP603
 * Project 2 - RPG_Game
 *
 * */
package CharactorModel;

// Define the abstract RPGmodel class. Being abstract means it cannot be instantiated directly
public abstract class RPGmodel {

    // Protected member variables: accessible by subclasses and classes in the same package
    protected String name;
    protected String gender;
    protected int health;
    protected int attackPower;
    protected int defence;

    // Constructor for the RPGmodel class with parameters for name, gender, health, attack power, and defence
    public RPGmodel(String name, String gender, int health, int attackPower, int defence) {
        this.name = name;
        this.gender = gender;
        this.health = health;
        this.attackPower = attackPower;
        this.defence = defence;
    }

    // Getter method to retrieve the name
    public String getName() {
        return name;
    }

    // Setter method to set the name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method to retrieve the gender
    public String getGender() {
        return gender;
    }

    // Setter method to set the gender
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter method to retrieve the health
    public int getHealth() {
        return health;
    }

    // Setter method to set the health
    public void setHealth(int health) {
        this.health = health;
    }

    // Getter method to retrieve the attack power
    public int getAttackPower() {
        return attackPower;
    }

    // Setter method to set the attack power
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    // Getter method to retrieve the defence value
    public int getDefence() {
        return defence;
    }

    // Setter method to set the defence value
    public void setDefence(int defence) {
        this.defence = defence;
    }

    // Abstract attack method. It must be implemented by any non-abstract subclass of RPGmodel
    public abstract void attack(RPGmodel target);

    // Method to receive damage, which considers the defence value
    public void receiveDamage(int damage) {
        int netDamage = Math.max(damage - defence, 0); // Ensure damage doesn't go negative after considering defence
        this.health -= netDamage; // Subtract the net damage from health
        // Print a message to the console indicating the received damage and updated health
        System.out.println(name + " received " + netDamage + " damage. Health is now " + health);
    }

    // Static factory method to create characters of a specific type
    public static RPGmodel createCharacter(String type, String name, String gender) {
        switch (type) {
            case "Archer":
                return new Archer(name, gender);
            case "Mage":
                return new Mage(name, gender);
            case "Warrior":
                return new Warrior(name, gender);
            default:
                return null; // Return null if the type doesn't match any known character type
        }
    }
}
