package com.demo.tdd;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

public class TicTacToeSpec {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private TicTacToe ticTacToe;

    @Before
    public final void before(){
        ticTacToe = new TicTacToe();
    }

    @Test
    public void whenXOutsideBoardThenRuntimeException(){
        exception.expect(RuntimeException.class);
        ticTacToe.play(5,2);
    }
    @Test
    public void whenYOutsideBoardThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 5);
    }
    @Test
    public void whenOccupiedThenRuntimeException(){
        ticTacToe.play(2,1);
        exception.expect(RuntimeException.class);
        ticTacToe.play(2,1);
    }
    @Test
    public void givenFirstTurnWhenNextPlayerThenX(){
        assertEquals('X', ticTacToe.nextPlayer());
    }
    @Test
    public void givenLastTurnWasXorOWhenNextPlayerThenOorX(){
        ticTacToe.play(1,1);
        assertEquals('O',ticTacToe.nextPlayer());
        ticTacToe.play(2,1);
        assertEquals('X',ticTacToe.nextPlayer());
    }
    @Test
    public void whenPlayThenNoWinner(){
        String actual = ticTacToe.play(1,1);
        assertEquals("No winner", actual);
    }
    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 1); // X
        ticTacToe.play(2, 2); // O
        String actual = ticTacToe.play(3, 1); // X
        assertEquals("X is the winner", actual);
    }
    @Test
    public void whenPlayAndWholeVerticalLineThenWinner() { //测试垂直方向是否获胜
        ticTacToe.play(2, 1); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(3, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 2); // X
        String actual = ticTacToe.play(1, 3); // O
        assertEquals("O is the winner", actual);
    }
    @Test
    public void whenPlayAndTopBottomDiagonalLineThenWinner() { //测试对角线方向是否获胜
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(1, 3); // O
        String actual = ticTacToe.play(3, 3); // X
        assertEquals("X is the winner", actual);
    }
    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner() { //测试另一条对角线
        ticTacToe.play(1, 3); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(1, 2); // O
        String actual = ticTacToe.play(3, 1); // X
        assertEquals("X is the winner", actual);
    }
    @Test
    public void whenAllBoxesAreFilledThenDraw() { //处理平局
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        String actual = ticTacToe.play(3, 2);
        assertEquals("The result is draw", actual);
    }
}
