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
        if (targetPlayerIdx >= player.game.players.size()) return false;
        if (!player.game.players.get(targetPlayerIdx).awokenQueens.getQueens().containsKey(targetQueen)) return false;

        HandPosition defenseCardPosition = player.game.players.get(targetPlayerIdx).hand.hasCardOfType(defenseCardType);
        if(defenseCardPosition != null) {
            ArrayList<HandPosition> positions = new ArrayList<>();
            positions.add(defenseCardPosition);
            player.game.players.get(targetPlayerIdx).hand.pickCards(positions);
            player.game.players.get(targetPlayerIdx).hand.removePickedCardsAndRedraw();
        }
        else {
            MoveQueen moveQueen = new MoveQueen(player, returnQueenCollection);
            moveQueen.play(targetQueen);
        }
        return true;
    }
}
