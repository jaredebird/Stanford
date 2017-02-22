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

import com.sun.glass.ui.Screen;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 1000;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 200;
	private static final int PADDLE_HEIGHT = 30;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 1;
	
	private static final int NBRICKS = NBRICKS_PER_ROW * NBRICK_ROWS;

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
	
/** Vertical position of ball start */
	private static final int BALL_START_Y = 500;
	
	/** Vertical Velocity*/
	private static final int Y_VELOCITY = 4;

	/** Pause Time */
	private static final int PAUSE = 6;
	
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		setupBoard();
		runGame();

	}

private void runGame() {
	// TODO Auto-generated method stub
int i = 0;
	while (!gameOver()){
		i++;
		label.setLabel(Integer.toString(i) + " - " + ball.getBricks());
		while (ball.isVisible() && !gameOver()){
			ball.moveBall();
			checkCollision();
			pause(4);
			label.setLabel(Integer.toString(i) + " - " + ball.getBricks());
//			label.setLabel(String.valueOf(ball.getBottom()) + " - " + HEIGHT);
			if (ball.getBottom() > HEIGHT) {
				ball.setVisible(false);
			}
		}
	}
}

private boolean gameOver() {
	// TODO Auto-generated method stub
	if (ball.getRound() < NTURNS && ball.getBricks() < NBRICKS) {
		return false;
	}else{
		return true;
	}
}

private void setWindowSize() {
	setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);	
}

	private void setupBoard() {
	
		setWindowSize();
		createBall();
		createBricks();
		createPaddle();
		label = new GLabel("Start");
		label.setFont("Times New Roman-36");
		add(label, 50,50);
		addMouseListeners();
	}
	private void createBall(){
		ball = new pinBall(BALL_RADIUS*2,BALL_RADIUS*2);
		ball.setVisible(false);
//		ball.setBounds(getWidth()/2-BALL_RADIUS, BALL_START_Y);
		ball.setBounds(getWidth()/2-BALL_RADIUS, BALL_START_Y, BALL_RADIUS*2,BALL_RADIUS*2);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		add(ball);
		ball.setRound(currentRound);
	}
	private void createPaddle() {
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		add(paddle, getWidth()/2, HEIGHT-PADDLE_Y_OFFSET);
		
		
	}

	public void mouseMoved(MouseEvent e) {
			paddle.setLocation(median(e.getX()-paddle.getWidth()/2, WIDTH - PADDLE_WIDTH, 0 ), paddle.getY());
	}
	public void mouseClicked(MouseEvent e){
		
		activateBall();

		
	}
	private double median (double a, double b, double c){
		return Math.max(Math.min(a,b), Math.min(Math.max(a,b),c));
	}

	private void checkCollision() {
		checkPaddle();
		checkRightWall();
		checkLeftWall();
		checkTopWall();
		checkBrick();
	}

	private void checkBrick() {
		checkBrickTop();
		checkBrickBottom();
		checkBrickRight();
		checkBrickLeft();
	}

	private void checkBrickLeft() {
		GObject collobj = getElementAt(ball.getLeft(), ball.getCenterY());
		if (collobj != null && collobj.getHeight() == BRICK_HEIGHT && collobj.getWidth() == BRICK_WIDTH){
			if (ball.getVx() < 0) {
				ball.reverseY();
			}
			remove(collobj);
			collobj = null;
			ball.hitBrick();
		}
	}

	private void checkBrickRight() {
		GObject collobj = getElementAt(ball.getRight(), ball.getCenterY());
		if (collobj != null && collobj.getHeight() == BRICK_HEIGHT && collobj.getWidth() == BRICK_WIDTH){
			if (ball.getVx() > 0) {
				ball.reverseY();
			}
			remove(collobj);
			collobj = null;
			ball.hitBrick();
		}
	}

	private void checkBrickBottom() {
		GObject collobj = getElementAt(ball.getCenterX(), ball.getBottom());
		if (collobj != null && collobj.getHeight() == BRICK_HEIGHT && collobj.getWidth() == BRICK_WIDTH){
			if (ball.getVy() > 0) {
				ball.reverseY();
			}
			remove(collobj);
			collobj = null;
			ball.hitBrick();
		}
	}

	private void checkBrickTop() {
		GObject collobj = getElementAt(ball.getCenterX(), ball.getTop());
		if (collobj != null && collobj.getHeight() == BRICK_HEIGHT && collobj.getWidth() == BRICK_WIDTH){
			if (ball.getVy() < 0) {
				ball.reverseY();
			}
			remove(collobj);
			collobj = null;
			ball.hitBrick();
		}
	}

	private void checkPaddle() {
		GObject collobj = getElementAt(ball.getCenterX(), ball.getBottom());
		if (collobj == paddle){
			if (ball.getVy() > 0) {
				ball.hitPaddle(collobj.getX(), collobj.getX()+collobj.getWidth());
			}
		}
	}
	private void checkRightWall(){
		if (ball.getRight() > WIDTH){
			if (ball.getVx() > 0){ ball.reverseX();}
		}		
	}
	private void checkLeftWall(){
		if (ball.getLeft() <= 0){
			if (ball.getVx() < 0 ){ball.reverseX();}
		}		
	}
	private void checkTopWall(){
		if (ball.getTop() <= 0){
			if (ball.getVy() < 0 ){ ball.reverseY();}	
		}
	}	
		

	private void startBall() {
		

	}

	private void activateBall() {
		if (!ball.isVisible() && currentRound < NTURNS){
			ball.setVisible(true);
			ball.setLocation(getWidth()/2-BALL_RADIUS, BALL_START_Y);
			currentRound ++;
			ball.setRound(currentRound);
			vx = rgen.nextDouble(1.0, 3.0);
			if (rgen.nextBoolean(0.5)) vx = -vx;
				ball.setVelocity(vx, Y_VELOCITY);
				
		} else if (currentRound == NTURNS) {
			label.setLabel("Game Over!");
		}
		
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
	private pinBall ball;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private GLabel label;
	private int currentRound;
	private double vx, vy;
}
