package ga.uniquecoding.uniquelevels.files;

import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataFile extends ga.uniquecoding.uniquecore.manager.DataFile {
    public PlayerDataFile(File directory) {
        super(directory, "");
    }

    private PlayerDataFile dataFile = this;
    private Player player;

    public void createPlayerData(Player player) throws IOException {
        String playerId = player.getUniqueId().toString();
        data.createSection(playerId);
        data.createSection(playerId + ".xp");
        data.createSection(playerId + ".xp.level");
        data.createSection(playerId + "xp.level.prestige");
        dataFile.save();
    }

    public Object getXp(UUID uuid) {
        return data.get(uuid + ".xp");
    }

    public void giveXp(UUID uuid, int amount) throws IOException {
        data.set(uuid + ".xp", +amount);
        dataFile.save();
    }

    public void setXp(UUID uuid, int amount) throws IOException {
        data.set(uuid + ".xp", amount);
        dataFile.save();
    }

    public void setLevel(UUID uuid, int amount) throws IOException {
        data.set(uuid + ".xp.level", amount);
        dataFile.save();
    }

    public void giveLevel(UUID uuid, int amount) throws IOException {
        data.set(uuid + ".xp.level", +amount);
        dataFile.save();
    }

    public void setPrestige(UUID uuid, String prestige) throws IOException {
        data.set(uuid + ".xp.level.prestige", prestige.toUpperCase());
        dataFile.save();
    }

    public boolean containsPlayerData(UUID id) {
        return data.contains(id.toString());
    }
}
