package fr.skyost.hungergames.tasks;

import java.util.Map.Entry;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.skyost.hungergames.HungerGames;
import fr.skyost.hungergames.HungerGames.Step;
import fr.skyost.hungergames.HungerGamesProfile;

public class StaredownPeriod extends BukkitRunnable {
	
	@Override
	public void run() {
		HungerGames.currentStep = Step.SECOND_COUNTDOWN;
		//final String message = HungerGames.messages.message4.replace("/n/", String.valueOf(HungerGames.config.gameCountdownTime));
		Player player;
		for(final Entry<Player, HungerGamesProfile> entry : HungerGames.players.entrySet()) {
		player = entry.getKey();
		//player.teleport(entry.getValue().getGeneratedLocation());
		player.setGameMode(GameMode.SURVIVAL);
		player.setAllowFlight(false);
		player.setSneaking(HungerGames.config.gameAutoSneak);
		player.setHealth(player.getMaxHealth());
		player.setFoodLevel(20);
		player.setExhaustion(0);
		player.setSaturation(20.0f);
		player.getInventory().removeItem(HungerGames.kitSelector);
		player.sendMessage("Go! Starting Invincibility Period...");
		}
		HungerGames.tasks[0] = new Countdown(HungerGames.config.gameCountdownTime, HungerGames.config.gameCountdownExpBarLevel, HungerGames.config.gameCountdownMobBar, new PostExecuteSecond()).runTaskTimer(HungerGames.instance, 0, 10L).getTaskId();
		HungerGames.tasks[1] = -1;
		}
	
}
