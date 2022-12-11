package damien.freeze;
import damien.freeze.command.completion.freeze_completion;
import damien.freeze.command.freeze;
import org.bukkit.plugin.java.JavaPlugin;


public final class Freeze extends JavaPlugin {



    @Override
    public void onEnable() {

        freeze freeze_instance = new freeze();

        getCommand("freeze").setExecutor(freeze_instance);
        getCommand("freeze").setTabCompleter(new freeze_completion());
        getServer().getPluginManager().registerEvents(freeze_instance, this);
    }

    @Override
    public void onDisable() {

    }






}
