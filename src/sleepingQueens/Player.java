package sleepingQueens;

import java.util.*;

public class Player {

    public Game game;
    public int playerIndex;
    public Hand hand;
    public AwokenQueens awokenQueens;

    public Player(Game game, int playerIndex) {
        this.game = game;
        hand = new Hand(this);
        this.playerIndex = playerIndex;
        awokenQueens = new AwokenQueens(this);
    }

    public void play(List<Position> cards) {
        if (cards.isEmpty()) {
            System.out.println("You have to play...");
            return;
        }
        if (!(cards.get(0) instanceof HandPosition)) {
            System.out.println("Wrong play...");
            return;
        }

        List<HandPosition> playingCards = new ArrayList<>();
        if (cards.size() == 1) {
            playingCards.add((HandPosition) cards.get(0));
        }
        else if (cards.size() == 2) {
            Optional<Card> firstCard = getPlayerState().cards.get(cards.get(0).getCardIndex());
            if (firstCard.isEmpty()) {
                System.out.println("You do not have this card...");
                return;
            }
            switch(firstCard.get().type) {
                case KING:
                    Position targetQueen = cards.get(1);
                    MoveQueen moveQueen = new MoveQueen(this, awokenQueens);
                    if (!moveQueen.play(targetQueen)) {
                        return;
                    }
                    playingCards.add((HandPosition) cards.get(0));
                    return;
                case KNIGHT:
                    targetQueen = cards.get(1);
                    if (!(targetQueen instanceof AwokenQueenPosition)) return;
                    EvaluateAttack evaluateAttack = new EvaluateAttack(CardType.DRAGON, this, awokenQueens);
                    if (!evaluateAttack.play(targetQueen, ((AwokenQueenPosition) targetQueen).getPlayerIndex())) {
                        return;
                    }
                    playingCards.add((HandPosition) cards.get(0));
                    break;
                case SLEEPING_POTION:
                    targetQueen = cards.get(1);
                    if (!(targetQueen instanceof AwokenQueenPosition)) return;
                    evaluateAttack = new EvaluateAttack(CardType.MAGIC_WAND, this, game.sleepingQueens);
                    if (!evaluateAttack.play(targetQueen, ((AwokenQueenPosition) targetQueen).getPlayerIndex())) {
                        return;
                    }
                    playingCards.add((HandPosition) cards.get(0));
                    break;
                default:
                    if (!(cards.get(1) instanceof HandPosition)) {
                        System.out.println("Wrong cards...");
                        return;
                    }
                    Optional<Card> secondCard = getPlayerState().cards.get(cards.get(1).getCardIndex());
                    if (secondCard.isEmpty()) {
                        System.out.println("You do not have this card...");
                        return;
                    }
                    if (firstCard.get().type != CardType.NUMBER || secondCard.get().type != CardType.NUMBER) {
                        System.out.println("Wrong cards...");
                        return;
                    }
                    else {
                        EvaluateNumberedCards evaluateNumberedCards = new EvaluateNumberedCards(this);
                        if (!evaluateNumberedCards.play(List.of(firstCard.get(), secondCard.get()))) {
                            return;
                        }
                        playingCards.add((HandPosition) cards.get(0));
                        playingCards.add((HandPosition) cards.get(1));
                    }
            }
        }
        else {
            ArrayList<Card> evaluateCards = new ArrayList<>();
            for (Position position : cards) {
                if (!(position instanceof HandPosition)) {
                    System.out.println("Wrong play...");
                    return;
                }
                if (((HandPosition) position).getPlayerIndex() != playerIndex) {
                    System.out.println("Those are not your cards...");
                    return;
                }
                Optional<Card> card = getPlayerState().cards.get(cards.get(0).getCardIndex());
                if (card.isEmpty()) {
                    System.out.println("You do not have this card...");
                    return;
                }
                evaluateCards.add(card.get());
                playingCards.add((HandPosition) position);
            }
            for (Card card : evaluateCards) {
                if (card.type != CardType.NUMBER) {
                    System.out.println("Can not evaluate cards...");
                    return;
                }
            }
            EvaluateNumberedCards evaluateNumberedCards = new EvaluateNumberedCards();
            if (!evaluateNumberedCards.play(evaluateCards)) {
                System.out.println("Bad number combination...");
                return;
            }
        }
        hand.pickCards(playingCards);
        hand.removePickedCardsAndRedraw();

    }
    public PlayerState getPlayerState() {
        PlayerState playerState = new PlayerState();

        Map<Integer, Optional<Card>> cards = new HashMap<>();
        List<Card> handCards = hand.getCards();
        for (int i = 0; i < 5; i++) {
            if (i < handCards.size()) {
                cards.put(i, Optional.ofNullable(handCards.get(i)));
            }
            else {
                cards.put(i, Optional.empty());
            }
        }
        playerState.cards = cards;

        Map<Integer, Queen> queens = new HashMap<>();
        for (Map.Entry<Position, Queen> entry : awokenQueens.getQueens().entrySet()) {
            queens.put(entry.getKey().getCardIndex(), entry.getValue());
        }
        playerState.awokenQueens = queens;

        return playerState;
    }
}
