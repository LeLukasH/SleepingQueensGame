import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class AwokenQueensTest {

    @Test
    public void addQueen() {
        AwokenQueens awakenedQueens = new AwokenQueens(1);
        Queen queen = new Queen(10);
        awakenedQueens.addQueen(queen);
        Assert.assertEquals(1, awakenedQueens.getQueens().size());
    }

    @Test
    public void removeQueen() {
        AwokenQueens awakenedQueens = new AwokenQueens(1);
        Queen queen1 = new Queen(15);
        Queen queen2 = new Queen(10);
        awakenedQueens.addQueen(queen1);
        awakenedQueens.addQueen(queen2);
        awakenedQueens.removeQueen(new AwokenQueenPosition(0,1));
        Assert.assertEquals(1, awakenedQueens.getQueens().size());
        Assert.assertTrue(awakenedQueens.getQueens().containsKey(new AwokenQueenPosition(0,1)));
    }

    @Test
    public void removeQueenReturnsRemovedQueen() {
        AwokenQueens awakenedQueens = new AwokenQueens(1);
        Queen queen1 = new Queen(10);
        awakenedQueens.addQueen(queen1);
        Optional<Queen> removedQueen = awakenedQueens.removeQueen(new AwokenQueenPosition(0,1));
        Assert.assertTrue(removedQueen.isPresent());
        Assert.assertEquals(queen1, removedQueen.get());
    }
}