package com.test;

//import com.lindseyvarner.engine.players;
import com.lindseyvarner.engine.players.Transition;
import org.junit.Test;

import static com.lindseyvarner.engine.BLACK;
import static com.lindseyvarner.engine.WHITE;
import static org.junit.jupiter.api.Assertions.*;
import com.lindseyvarner.engine.Alliance;
import com.lindseyvarner.engine.board.*;
import com.lindseyvarner.engine.board.Board.Builder;
import com.lindseyvarner.engine.board.Move;
import com.lindseyvarner.engine.pieces.*;
//import com.lindseyvarner.engine.player.ai.BoardEvaluator;
//import com.lindseyvarner.engine.player.ai.StandardBoardEvaluator;
import org.junit.Test;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class KingSafetyTest {

    @Test
    public void test1() {
        final Builder builder = new Builder();

        builder.setPiece(new King(4, BLACK, false));
        builder.setPiece(new Pawn(12, BLACK));

        builder.setPiece(new Pawn(52, WHITE));
        builder.setPiece(new King(60, WHITE, false));
        builder.setMoveMaker(WHITE);

        final Board board = builder.build();

        //assertEquals(KingSafetyAnalyzer.get().calculateKingTropism(board.whitePlayer()).tropismScore(), 40);
    }

}
