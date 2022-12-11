package damien.freeze.command.completion;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import java.util.ArrayList;
import java.util.List;

public class freeze_completion implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 2) {

            List<String> list = new ArrayList<>();
            list.add("on");
            list.add("off");
            return list;

        }


        return null;
    }
}

