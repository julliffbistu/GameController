package controller.action.ui.penalty;

import common.Log;
import data.states.AdvancedData;
import data.PlayerInfo;
import data.values.GameStates;
import data.values.Penalties;

/**
 * @author Michel Bartsch
 * 
 * This action means that the illegal ball contact penalty has been selected.
 */
public class BallContact extends Penalty
{
    /**
     * Performs this action`s penalty on a selected player.
     * 
     * @param data      The current data to work on.
     * @param player    The player to penalise.
     * @param side      The side the player is playing on (0:left, 1:right).
     * @param number    The player`s number, beginning with 0!
     */
    @Override
    public void performOn(AdvancedData data, PlayerInfo player, int side, int number)
    {
        player.penalty = Penalties.SPL_ILLEGAL_BALL_CONTACT;
        handleRepeatedPenalty(data, player, side, number, GameStates.PLAYING);
        data.whenPenalized[side][number] = data.getTime();
        Log.state(data, "Illegal Ball Contact " + data.team[side].teamColor + " " + (number+1));
    }
    
    /**
     * Checks if this action is legal with the given data (model).
     * Illegal actions are not performed by the EventHandler.
     * 
     * @param data      The current data to check with.
     */
    @Override
    public boolean isLegal(AdvancedData data)
    {
        return (data.gameState == GameStates.PLAYING) || data.testmode;
    }
}