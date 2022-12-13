import java.util.*;

public class EvaluateAttack {

    private final CardType defenseCardType;
    private final Player player;
    private final QueenCollection returnQueenCollection;

    public EvaluateAttack(CardType defenseCardType, Player player, QueenCollection returnQueenCollection) {
        this.defenseCardType = defenseCardType;
        this.player = player;
        this.returnQueenCollection = returnQueenCollection;
    }
    public boolean play(Position targetQueen, int targetPlayerIdx) {
        if (targetPlayerIdx >= player.getGame().getPlayers().size()) return false;
        if (!player.getGame().getPlayers().get(targetPlayerIdx).getAwokenQueens().getQueens().containsKey(targetQueen)) return false;

        HandPosition defenseCardPosition = player.getGame().getPlayers().get(targetPlayerIdx).getHand().hasCardOfType(defenseCardType);
        if(defenseCardPosition != null) {
            ArrayList<HandPosition> positions = new ArrayList<>();
            positions.add(defenseCardPosition);
            player.getGame().getPlayers().get(targetPlayerIdx).getHand().pickCards(positions);
            player.getGame().getPlayers().get(targetPlayerIdx).getHand().removePickedCardsAndRedraw();
        }
        else {
            MoveQueen moveQueen = new MoveQueen(player, returnQueenCollection);
            moveQueen.play(targetQueen);
        }
        return true;
    }
}
