package purplesquad;

import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.*;
import java.io.IOException;

import purplesquad.Point;
import purplesquad.RobotColors;

public class Unity extends TeamRobot {
	private static int WALK_AMOUNT = 100;
	private boolean movingForward;
	private boolean targeting;

	public void run() {
		out.println("We are UNITY.");
		this.setColors();

		while (true) { this.movementAI(); }
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		if (isTeammate(e.getName())) {
			return;
		}

		stop();
		this.targeting = true;
		Point enemyP = this.getEnemyPoint(e);
		this.broadcastEnemyPoint(enemyP);

		setAhead(100);
		this.turnGunToPoint(enemyP);
		smartFire(e.getDistance());
		back(100);
		scan();

		this.targeting = false;
		resume();
	}

	public void onMessageReceived(MessageEvent e) {
		if (e.getMessage() instanceof Point) {
			if (!this.targeting) {
				this.turnGunToPoint((Point) e.getMessage());
			}
		}
	}

	public void onHitByBullet(HitByBulletEvent e) { reverseDirection(); }
	public void onHitWall(HitWallEvent e) { reverseDirection(); }
	public void onHitRobot(HitRobotEvent e) { reverseDirection(); }

	private Point getEnemyPoint(ScannedRobotEvent e) {
		double enemyBearing = this.getHeading() + e.getBearing();
		double enemyX = getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
		double enemyY = getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));
		return new Point(enemyX, enemyY);
	}

	private void broadcastEnemyPoint(Point p) {
		try {
			broadcastMessage(p);
		} catch (IOException ex) {
			out.println("Unable to send order: ");
			ex.printStackTrace(out);
		}
	}

	private void turnGunToPoint(Point p) {
		double dx = p.getX() - this.getX();
		double dy = p.getY() - this.getY();
		// Calculate angle to target
		double theta = Math.toDegrees(Math.atan2(dx, dy));

		// Turn gun to target
		turnGunRight(normalRelativeAngleDegrees(theta - getGunHeading()));
	}
	
	private void movementAI() {
		setTurnRadarRight(10000);
		movingForward = true;
		setAhead(WALK_AMOUNT);
		setTurnRight(20);
		waitFor(new TurnCompleteCondition(this));
	}

	private void setColors() {
		RobotColors c = new RobotColors();
		setBodyColor(c.bodyColor);
		setGunColor(c.gunColor);
		setRadarColor(c.radarColor);
		setScanColor(c.scanColor);
		setBulletColor(c.bulletColor);
	}

	private void smartFire(double robotDistance) {
		if (robotDistance > 200 || getEnergy() < 15) {
			fire(1);
		} else if (robotDistance > 50) {
			fire(2);
		} else {
			fire(3);
		}
	}

	private void reverseDirection() {
		if (movingForward) {
			setBack(WALK_AMOUNT);
			movingForward = false;
		} else {
			setAhead(WALK_AMOUNT);
			movingForward = true;
		}
	}
}
