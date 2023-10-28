package rpg_game;

public class GameData {
    private Character player;
    private Inventory inventory;

    public GameData(Character player, Inventory inventory) {
        this.player = player;
        this.inventory = inventory;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
