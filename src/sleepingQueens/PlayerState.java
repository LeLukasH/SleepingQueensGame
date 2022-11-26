package sleepingQueens;

import java.util.*;

public class PlayerState {
    private Map<Integer, Optional<Card>> cards;
    private Map<Integer, Queen> awokenQueens;
}
