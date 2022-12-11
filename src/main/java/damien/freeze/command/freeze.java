package damien.freeze.command;
import damien.freeze.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class freeze implements CommandExecutor, Listener {

    public ArrayList<Player> frozen_players = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 2)
            {
                Player target = Freeze.getPlugin(Freeze.class).getServer().getPlayer(args[0]);



                switch (args[1].toLowerCase()) {
                    case "on":

                        target.sendTitle("","§3You has been freezed by " + player.getName());
                        target.setFreezeTicks(120);
                        target.playSound(target, Sound.ENTITY_PLAYER_HURT_FREEZE, 50, 1);
                        frozen_players.add(target);
                        target.setWalkSpeed(0);
                        target.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 200));

                        player.sendMessage("§2Player successfully frozen");
                        Bukkit. getScheduler(). runTaskLater(Freeze.getPlugin(Freeze.class), () -> {
                            target.resetTitle();

                        }, 40L);
                        break;
                    case "off":
                        try {
                            if(player.getWalkSpeed() == 0)
                            {
                                frozen_players.remove(target);
                                target.setWalkSpeed(0.2f);
                                target.removePotionEffect(PotionEffectType.JUMP);
                                target.playSound(target, Sound.AMBIENT_UNDERWATER_EXIT, 50, 1);
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 50, 1);
                                player.sendMessage("§2Player successfully unfrozen");
                            } else {
                                player.sendMessage("§cThis player isn't frozen. Use §6/freeze " + target.getName() + " on §cto freeze this player");
                            }

                        }
                        catch(Exception e) {
                            player.sendMessage("§cThis player isn't frozen. Use §6/freeze " + target.getName() + " on §cto freeze this player");
                        }
                        break;

                }











            } else {
                player.sendMessage("§cError with argument");
            }

        }




        return true;
    }


    @EventHandler
    public void movement(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Double xTo = event.getTo().getX();
        Double xFrom = event.getFrom().getX();
        Double yTo = event.getTo().getY();
        Double yFrom = event.getFrom().getY();
        Double zTo = event.getTo().getZ();
        Double zFrom = event.getFrom().getZ();
        if(event.getTo().locToBlock(xTo) != event.getFrom().locToBlock(xFrom) || event.getTo().locToBlock(zTo) != event.getFrom().locToBlock(zFrom) || event.getTo().locToBlock(yTo) != event.getFrom().locToBlock(yFrom)) {
            if(frozen_players.contains(player)) {
                player.teleport(event.getFrom());
            }
        }
    }


}
