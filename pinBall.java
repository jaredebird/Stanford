import acm.graphics.*;
/** Separation between bricks */


public class pinBall extends GOval {
	
	private static final int maxV = 8;
	private static final int maxChangeV = 3;
	
	public pinBall(double width, double height) {
		super(width, height);
		vX = 0;
		vY=0;		
		// TODO Auto-generated constructor stub
	}
	public double getLeft() {
		return this.getX();
	}
	public double getRight() {
		return this.getX()+this.getWidth();
	}
	public double getTop() {
		return this.getY();
	}
	public double getBottom() {
		return this.getY() + this.getHeight();
	}
	public double getCenterX() {
		return this.getX() + this.getWidth()/2;
	}
	public double getCenterY() {
		return this.getY() + this.getHeight()/2;
	}
	public boolean rightBoundry(double rightBorder) {
		if (this.getRight() >= rightBorder) { 
			return true;
		}
		else {
			return false;
		}
	}
	public void hitPaddle(double paddleLeft, double paddleRight){
		double paddleCenter = (paddleLeft + paddleRight)/2;
		double paddleWidth = paddleRight - paddleLeft;
		double vChange = maxChangeV * ((this.getCenterX() - paddleCenter)/(paddleWidth/2.0));
		vX = median (-1 * maxV, maxV, vX + vChange);
		vY = -vY;
	}
	private double median (double a, double b, double c){
		return Math.max(Math.min(a,b), Math.min(Math.max(a,b),c));
	}
	
	public void moveBall(){
		this.move(vX, vY);
	}
	public void setVx(double xVel){
		vX = (int) xVel;
	}
	public double getVx(){
		return vX;
	}
	public double getVy(){
		return vY;
	}
	public void setVy(double yVel){
		vY = yVel;
	}
	public void setVelocity(double xVel, double yVel) {
		vX = xVel;
		vY = yVel;
	}
	public void setRound(int round){
		currentRound = round;
	}
	public int getRound(){
		return currentRound;
	}
	public void reverseX() {
		vX = -vX;
	}
	public void reverseY(){
		vY = -vY;
	}
	private  double vX, vY;
	private int currentRound;
	
}