/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 1200;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 8;

/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;


/** Height of a brick */
	private static final int BRICK_HEIGHT = 16;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 20;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 140;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		setupBoard();
		runGame();
	}

private void runGame() {
	// TODO Auto-generated method stub
	
}

private void setWindowSize() {
	setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);	
}

	private void setupBoard() {
	
		setWindowSize();
		pause(30);
		createBricks();
		createPaddle();
		addMouseListeners();
	}

	private void createPaddle() {
		 paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		add(paddle, getWidth()/2, getHeight()-PADDLE_Y_OFFSET);
		
		
	}

	public void mouseMoved(MouseEvent e) {

			paddle.setLocation(e.getX()-paddle.getWidth()/2, paddle.getY());


	}


	private void createBricks() {
		
		int leftOffset = findLeftStart();
		
		int topOffset = BRICK_Y_OFFSET;
		Color[] rowColors = new Color[NBRICK_ROWS];
			rowColors = createColorArray();
			for (int i=0; i<NBRICK_ROWS; i++){
				createRow(rowColors[i], leftOffset, topOffset);
				topOffset += (BRICK_HEIGHT + BRICK_SEP);
			}

	}

	private int findLeftStart() {
		int totalBrickWidth = BRICK_WIDTH*NBRICKS_PER_ROW;
		int totalBrickSpacing = BRICK_SEP * (NBRICKS_PER_ROW - 1);
		
		return (WIDTH-(totalBrickWidth + totalBrickSpacing))/2;
	}

	private void createRow(Color rowColor, int leftOffset, int topOffset) {

		for (int i=0;i<NBRICKS_PER_ROW; i++){
			GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			brick.setFilled(true);
			brick.setColor(rowColor);
			add(brick, leftOffset, topOffset);

			leftOffset += (BRICK_WIDTH + BRICK_SEP);
		}
	}

	private Color[] createColorArray() {
	// TODO Auto-generated method stub
	Color[] rowColors = new Color[NBRICK_ROWS];
	
	for (int i=0;i<NBRICK_ROWS; i++){
		switch (i/2){
		case 0:
			rowColors[i]= Color.RED;
			break;
		case 1:
			rowColors[i]= Color.ORANGE;
			break;
		case 2:
			rowColors[i]= Color.YELLOW;
			break;	
		case 3:
			rowColors[i]= Color.GREEN;
			break;
		default:
			rowColors[i]= Color.CYAN;
			break;
		}
	
	}

	return rowColors;
}
	private GRect paddle;
}
