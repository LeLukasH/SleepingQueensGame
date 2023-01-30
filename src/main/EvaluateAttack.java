import java.util.*;

public class EvaluateAttack {

    private CardType defenseCardType;
    private final Map<Integer, Player> players;
    private final MoveQueen moveQueen;

    public EvaluateAttack(Map<Integer, Player> players, MoveQueen moveQueen) {
        this.defenseCardType = CardType.NUMBER;
        this.players = players;
        this.moveQueen = moveQueen;
    }
    public boolean play(Position targetQueen, int targetPlayerIdx) {
        if (targetPlayerIdx >= players.size()) return false;
        if (!players.get(targetPlayerIdx).getAwokenQueens().getQueens().containsKey(targetQueen)) return false;

        HandPosition defenseCardPosition = players.get(targetPlayerIdx).getHand().hasCardOfType(defenseCardType);
        if(defenseCardPosition != null) {
            ArrayList<HandPosition> positions = new ArrayList<>();
            positions.add(defenseCardPosition);
            players.get(targetPlayerIdx).getHand().pickCards(positions);
            players.get(targetPlayerIdx).getHand().removePickedCardsAndRedraw();
        }
        else {
            moveQueen.play(targetQueen);
        }
        return true;
    }
    public void setDefenseCardType(CardType cardType) {
        this.defenseCardType = cardType;
    }
}
