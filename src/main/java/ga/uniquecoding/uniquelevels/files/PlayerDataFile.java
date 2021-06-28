package ga.uniquecoding.uniquelevels.files;

import ga.uniquecoding.uniquelevels.UniqueLevels;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataFile {

    UniqueLevels uniqueLevels;

    FileConfiguration data = YamlConfiguration.loadConfiguration(
            new File(uniqueLevels.getDataFolder(), "/data/playerdata.yml"));

    public PlayerDataFile(File dataFolder) {
    }

    public void createPlayerData(UUID uuid, Player player) {
        data.createSection(uuid.toString());
        data.createSection(uuid.toString() + "XP:" + player.getTotalExperience());

        try {
            data.save("playerdata.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getXp(Player player) {
        return player.getTotalExperience();
    }

}
