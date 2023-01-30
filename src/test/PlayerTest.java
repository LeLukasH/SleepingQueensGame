import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {
    @Mock
    private MoveQueen moveQueenMock;
    @Mock
    private EvaluateAttack evaluateAttackMock;
    @Mock
    private EvaluateNumberedCards evaluateNumberedCardsMock;
    @Mock
    private DrawingAndThrashPile drawingAndThrashPileMock;

    private Player player;

    private final int playerIndex = 0;

    @Before
    public void setUp(){
        moveQueenMock = mock(MoveQueen.class);
        evaluateAttackMock = mock(EvaluateAttack.class);
        evaluateNumberedCardsMock = mock(EvaluateNumberedCards.class);
        drawingAndThrashPileMock = mock(DrawingAndThrashPile.class);
        player = new Player(moveQueenMock, evaluateAttackMock, evaluateNumberedCardsMock, drawingAndThrashPileMock, playerIndex);
    }

    @Test
    public void testPlayWithEmptyCards() {
        List<Position> cards = new ArrayList<>();
        boolean result = player.play(cards);
        assertFalse(result);
    }

    @Test
    public void testPlayWithWrongPlay() {
        List<Position> cards = new ArrayList<>();
        cards.add(new HandPosition(0,0));
        boolean result = player.play(cards);
        assertFalse(result);
    }

    @Test
    public void testPlayWithKingCard() {
        List<Position> cards = new ArrayList<>();
        cards.add(new HandPosition(0, playerIndex));
        cards.add(new HandPosition(1, playerIndex));
        when(moveQueenMock.play(any())).thenReturn(true);
        boolean result = player.play(cards);
        assertTrue(result);
        verify(moveQueenMock).play(any());
    }

    @Test
    public void testPlayWithKnightCard() {
        List<Position> cards = new ArrayList<>();
        cards.add(new HandPosition(0, 1));
        cards.add(new AwokenQueenPosition(0, 0));
        when(evaluateAttackMock.play(any(), anyInt())).thenReturn(true);
        boolean result = player.play(cards);
        assertTrue(result);
        verify(evaluateAttackMock).play(any(), anyInt());
    }
}
