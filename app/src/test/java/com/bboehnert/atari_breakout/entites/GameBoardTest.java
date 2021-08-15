package com.bboehnert.atari_breakout.entites;

import com.bboehnert.atari_breakout.AudioListener;
import com.bboehnert.atari_breakout.DrawListener;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Class for testing the Game board
 */

public class GameBoardTest {

    @Mock
    private DrawListener drawListener;

    @Mock
    private AudioListener audioListener;

    @InjectMocks
    private GameBoard gameBoard;
    private final double delta = 1e-5;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        gameBoard = new GameBoard();
        gameBoard.setWidth(800);
        gameBoard.setHeight(640);

        gameBoard.setAudioPlayer(audioListener);
        gameBoard.setDrawer(drawListener);
        gameBoard.initComponents();
    }

    /**
     * Test for starting the game
     */
    @Test
    public void startGameTest() {
        Assert.assertEquals(800, gameBoard.getWidth(), delta);
        Assert.assertEquals(640, gameBoard.getHeight(), delta);

        Assert.assertFalse(gameBoard.isGameStarted());
        Assert.assertNotNull(gameBoard.getBall());
        Assert.assertNotNull(gameBoard.getPaddle());
        Assert.assertNotNull(gameBoard.getBricks());
        gameBoard.restartGame();
        Assert.assertTrue(gameBoard.isGameStarted());
    }

    /**
     * Test for the paddle movement
     */
    @Test
    public void paddleGameBoardTest() {
        gameBoard.movePaddle(-10);
        Assert.assertEquals(0, gameBoard.getPaddle().getX(), delta);
        gameBoard.movePaddle(gameBoard.getWidth() - 10);
        Assert.assertEquals(gameBoard.getWidth() - gameBoard.getPaddle().getWidth(),
                gameBoard.getPaddle().getX(), delta);
    }

    /**
     * Test all actions of the game
     */
    @Test
    public void getCurrentActionTest() {
        Assert.assertNull(gameBoard.getCurrentAction());
        gameBoard.restartGame();

        // Reflected By paddle
        gameBoard.getBall().x = gameBoard.getPaddle().x;
        gameBoard.getBall().y = gameBoard.getPaddle().y;
        Assert.assertNotNull(gameBoard.getCurrentAction());
        Assert.assertEquals(GameBoard.GameAction.Y_Reflection, gameBoard.getCurrentAction());

        // Reflected By Left Boundary
        gameBoard.getBall().y = -1;
        Assert.assertNotNull(gameBoard.getCurrentAction());
        Assert.assertEquals(GameBoard.GameAction.Y_Reflection, gameBoard.getCurrentAction());

        // Reflected By Left Boundary
        gameBoard.getBall().x = -1;
        Assert.assertNotNull(gameBoard.getCurrentAction());
        Assert.assertEquals(GameBoard.GameAction.X_Reflection, gameBoard.getCurrentAction());

        // Reflected By Right Boundary
        gameBoard.getBall().x = gameBoard.getWidth() - gameBoard.getBall().width + 1;
        Assert.assertNotNull(gameBoard.getCurrentAction());
        Assert.assertEquals(GameBoard.GameAction.X_Reflection, gameBoard.getCurrentAction());

        // GameOver Down Boundary
        gameBoard.getBall().x = 0;
        gameBoard.getBall().y = gameBoard.getHeight() - gameBoard.getBall().width + 1;
        Assert.assertNotNull(gameBoard.getCurrentAction());
        Assert.assertEquals(GameBoard.GameAction.GameLose, gameBoard.getCurrentAction());

        // Game win
        for (Brick brick : gameBoard.getBricks()) {
            brick = null;
        }

        Assert.assertNotNull(gameBoard.getCurrentAction());
        Assert.assertEquals(GameBoard.GameAction.GameLose, gameBoard.getCurrentAction());
    }

    /**
     * Execute reflection actions
     */
    @Test
    public void processActionTest() {
        float ballX = gameBoard.getBall().getX();
        gameBoard.processAction(null);
        Assert.assertEquals(ballX, gameBoard.getBall().getX(), delta);

        gameBoard.restartGame();
        gameBoard.processAction(null);
        Assert.assertNotEquals(ballX, gameBoard.getBall().getX(), delta);

        // X-Reflection
        ballX = gameBoard.getBall().getX();
        Assert.assertEquals(ballX, gameBoard.getBall().getX(), delta);
        gameBoard.processAction(GameBoard.GameAction.X_Reflection);
        Assert.assertNotEquals(ballX, gameBoard.getBall().getX(), delta);

        // Y-Reflection
        float ballY = gameBoard.getBall().getY();
        Assert.assertEquals(ballY, gameBoard.getBall().getY(), delta);
        gameBoard.processAction(GameBoard.GameAction.Y_Reflection);
        Assert.assertNotEquals(ballY, gameBoard.getBall().getY(), delta);
    }

    /**
     * Execute game win action
     */
    @Test
    public void gameWinTest() {
        gameBoard.restartGame();

        // GameWin
        Assert.assertTrue(this.gameBoard.isGameStarted());
        gameBoard.processAction(GameBoard.GameAction.GameWin);
        Assert.assertFalse(this.gameBoard.isGameStarted());

    }

    /**
     * Execute game lose action
     */
    @Test
    public void gameLoseTest() {
        gameBoard.restartGame();

        // GameWin
        Assert.assertTrue(this.gameBoard.isGameStarted());
        gameBoard.processAction(GameBoard.GameAction.GameLose);
        Assert.assertFalse(this.gameBoard.isGameStarted());
    }
}