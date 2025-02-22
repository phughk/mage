package mage.watchers.common;

import mage.constants.WatcherScope;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.players.Player;
import mage.watchers.Watcher;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
 * Counts amount of life lost current or last turn by players.
 * This watcher is automatically started in gameImpl.init for each game
 *
 * @author LevelX2
 */
public class PlayerLostLifeWatcher extends Watcher {

    private final Map<UUID, Integer> amountOfLifeLostThisTurn = new HashMap<>();
    private final Map<UUID, Integer> amountOfLifeLostLastTurn = new HashMap<>();

    /**
     * Game default watcher
     */
    public PlayerLostLifeWatcher() {
        super(WatcherScope.GAME);
    }

    @Override
    public void watch(GameEvent event, Game game) {
        if (event.getType() == GameEvent.EventType.LOST_LIFE) {
            UUID playerId = event.getPlayerId();
            if (playerId != null) {
                Integer amount = amountOfLifeLostThisTurn.get(playerId);
                if (amount == null) {
                    amount = event.getAmount();
                } else {
                    amount = amount + event.getAmount();
                }
                amountOfLifeLostThisTurn.put(playerId, amount);
            }
        }
    }

    public int getLifeLost(UUID playerId) {
        return amountOfLifeLostThisTurn.getOrDefault(playerId, 0);
    }

    public int getAllOppLifeLost(UUID playerId, Game game) {
        int amount = 0;
        for (UUID opponentId : this.amountOfLifeLostThisTurn.keySet()) {
            Player opponent = game.getPlayer(opponentId);
            if (opponent != null && opponent.hasOpponent(playerId, game)) {
                amount += this.amountOfLifeLostThisTurn.getOrDefault(opponentId, 0);
            }
        }
        return amount;
    }

    public int getNumberOfOpponentsWhoLostLife(UUID playerId, Game game) {
        int numPlayersLostLife = 0;
        for (UUID opponentId : this.amountOfLifeLostThisTurn.keySet()) {
            Player opponent = game.getPlayer(opponentId);
            if (opponent != null && opponent.hasOpponent(playerId, game)) {
                if (this.amountOfLifeLostThisTurn.getOrDefault(opponentId, 0) > 0) {
                    numPlayersLostLife++;
                }
            }
        }
        return numPlayersLostLife;
    }

    public int getLifeLostLastTurn(UUID playerId) {
        return amountOfLifeLostLastTurn.getOrDefault(playerId, 0);
    }

    @Override
    public void reset() {
        super.reset();
        amountOfLifeLostLastTurn.clear();
        amountOfLifeLostLastTurn.putAll(amountOfLifeLostThisTurn);
        amountOfLifeLostThisTurn.clear();
    }
}
