package com.bboehnert.atari_breakout.entites;

import com.bboehnert.atari_breakout.mvp.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

/**
 * Class for testing the Game board
 */

public class GameBoardTest {

    @Mock
    private Model.DrawListener drawListener;

    @Mock
    private Model.AudioListener audioListener;

    @InjectMocks
    private GameBoard board;
    private final double delta = 1e-5;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        board = new GameBoard();
        board.setWidth(800);
        board.setHeight(640);

        board.initComponents(drawListener);
    }

    /**
     * Test for starting the game
     */
    @Test
    public void startGameTest() {
        Assert.assertEquals(800, board.getWidth(), delta);
        Assert.assertEquals(640, board.getHeight(), delta);

        Assert.assertFalse(board.isGameStarted());
        Assert.assertNotNull(board.getBall());
        Assert.assertNotNull(board.getPaddle());
        Assert.assertNotNull(board.getBricks());
        board.restartGame();
        Assert.assertTrue(board.isGameStarted());
    }

    /**
     * Test for the paddle movement
     */
    @Test
    public void paddleGameBoardTest() {
        board.movePaddle(-10, drawListener);
        Assert.assertEquals(0, board.getPaddle().getX(), delta);
        board.movePaddle(board.getWidth() - 10, drawListener);
        Assert.assertEquals(board.getWidth() - board.getPaddle().getWidth(),
                board.getPaddle().getX(), delta);
    }

    /**
     * Execute game win action
     */
    @Test
    public void gameWinTest() {
        board.restartGame();

        // GameWin
        Assert.assertTrue(this.board.isGameStarted());
        Assert.assertEquals(GameBoard.GameState.Undecided, this.board.getState());

        Arrays.fill(board.getBricks(), null);

        Assert.assertEquals(GameBoard.GameState.Undecided, this.board.getState());
        board.doGameAction(drawListener, audioListener);
        Assert.assertEquals(GameBoard.GameState.Won, this.board.getState());

        board.restartGame();
        Assert.assertEquals(GameBoard.GameState.Undecided, this.board.getState());
    }

    /**
     * Execute game lose action
     */
    @Test
    public void gameLoseTest() {
        board.restartGame();

        // GameWin
        Assert.assertTrue(this.board.isGameStarted());
        Assert.assertEquals(GameBoard.GameState.Undecided, this.board.getState());

        board.getBall().y = board.getHeight() - board.getBall().getHeight() + 1;
        Assert.assertEquals(GameBoard.GameState.Undecided, this.board.getState());
        board.doGameAction(drawListener, audioListener);
        Assert.assertEquals(GameBoard.GameState.Lost, this.board.getState());
    }
}