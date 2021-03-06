package fr.skyost.hungergames.tasks;

import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.skyost.hungergames.HungerGames;
import fr.skyost.hungergames.HungerGames.Step;
import fr.skyost.hungergames.HungerGamesProfile;

public class PostExecuteFirst extends BukkitRunnable {
	
	@Override
	public void run() {
		HungerGames.currentStep = Step.SECOND_COUNTDOWN;
		//final String message = HungerGames.messages.message4.replace("/n/", String.valueOf(HungerGames.config.gameCountdownTime));
		Player player;
		for(final Entry<Player, HungerGamesProfile> entry : HungerGames.players.entrySet()) {
			player = entry.getKey();
			player.teleport(entry.getValue().getGeneratedLocation());
			player.getInventory().clear();
			player.getInventory().setChestplate(null);
			player.getInventory().setLeggings(null);
			player.getInventory().setBoots(null);
			player.getInventory().setHelmet(null);
			player.setGameMode(GameMode.SURVIVAL);
			player.setAllowFlight(false);
			player.setSneaking(HungerGames.config.gameAutoSneak);
			player.setHealth(player.getMaxHealth());
			player.setFoodLevel(20);
			player.getInventory().removeItem(HungerGames.kitSelector);
			player.sendMessage(ChatColor.DARK_PURPLE + "Get ready to battle!");
		}
//		HungerGames.tasks[0] = new Countdown(HungerGames.config.gameCountdownTime, HungerGames.config.gameCountdownExpBarLevel, HungerGames.config.gameCountdownMobBar, new PostExecuteSecond()).runTaskTimer(HungerGames.instance, 0, 20L).getTaskId();
//		HungerGames.tasks[1] = -1;
		HungerGames.tasks[0] = new Countdown(HungerGames.config.gameCountdownTime, HungerGames.config.gameCountdownExpBarLevel, HungerGames.config.gameCountdownMobBar, new StaredownPeriod(), true).runTaskTimer(HungerGames.instance, 0, 20L).getTaskId();
		HungerGames.tasks[1] = -1;
	}
	
}
