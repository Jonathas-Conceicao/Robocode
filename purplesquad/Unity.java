package purplesquad;

import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

import purplesquad.Point;
import purplesquad.RobotColors;

public class Unity extends TeamRobot {
	private static int WALK_AMOUNT = 100;

	Random gerador = new Random();
	private double posicaoChasi;
	private int posicaoSorteada = 0;
	private double novaPosicao;
	private double posicaoX;
	private double posicaoY;
	private double posicaoMyArm;
	private double posicaoMyRadar;
	private double gunTurnAmt;
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

		this.targeting = true;
		Point enemyP = this.getEnemyPoint(e);
		this.broadcastEnemyPoint(enemyP);

		this.turnGunToPoint(enemyP);
		smartFire(e.getDistance());

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

	public void onHitByBullet(HitByBulletEvent e) {
		// Tomei bala
		posicaoChasi = getHeading();
		double anguloTiroInimigo;
		anguloTiroInimigo = e.getBearing();
		int flagQuadrante = 0; 
		// em que quadrante estou
		if (posicaoChasi > 0 && posicaoChasi < 90) {
			// Primeiro Quadrante
			flagQuadrante = 1;		
		}else if (posicaoChasi > 90 && posicaoChasi < 180) {
			// Quarto quadrante
			flagQuadrante = 4;
		}else if (posicaoChasi > 180 && posicaoChasi < 270) {
			// Terceiro quadrante
			flagQuadrante = 3;
		}else if (posicaoChasi > 270) {
			// Segundo quadrante
			flagQuadrante = 2;
		}

		// Em que angulo esta o inimigo (quadrantes divididos em X)
		if (anguloTiroInimigo < 45 && anguloTiroInimigo > 315) {
			// X cima
			if (flagQuadrante == 4) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 0) {
					turnRight(90);
				}
			}else if (flagQuadrante == 3) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 0) {
					turnRight(180);
				}else if (posicaoChasi == 90) {
					turnRight(90);
				}
			}else if (flagQuadrante == 2) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 90 || posicaoChasi == 0) {
					turnRight(180);
				}
			}else if (flagQuadrante == 1) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 0) {
					turnLeft(180);
				}else if (posicaoChasi == 90 ) {
					turnRight(90);
				}else if (posicaoChasi == 270) {
					turnLeft(90);
				}
			}

		}else if (anguloTiroInimigo > 45 && anguloTiroInimigo < 135) {
			// X direita
			if (flagQuadrante == 4) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 90) {
					turnRight(180);
				}else if (posicaoChasi == 180) {
					turnLeft(90);
				}
			}else if (flagQuadrante == 3) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 90) {
					turnRight(180);
				}else if (posicaoChasi == 270) {
					turnRight(90);
				}
			}else if (flagQuadrante == 2) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 90) {
					turnRight(180);
				}else if (posicaoChasi == 270) {
					turnRight(90);
				}
			}else if (flagQuadrante == 1) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 0) {
					turnLeft(90);
				}else if (posicaoChasi == 180) {
					turnRight(90);
				}else if (posicaoChasi == 90) {
					turnRight(180);
				}
			}

		}else if (anguloTiroInimigo > 135 && anguloTiroInimigo < 225) {
			// X baixo
			if (flagQuadrante == 4) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 270) {
					turnRight(180);
				}else if (posicaoChasi == 180) {
					turnRight(90);
				}
			}else if (flagQuadrante == 3) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 90) {
					turnRight(180);
				}
			}else if (flagQuadrante == 2) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 180 || posicaoChasi == 90) {
					turnRight(180);
				}
			}else if (flagQuadrante == 1) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 90) {
					turnLeft(90);
				}else if (posicaoChasi == 270) {
					turnRight(90);
				}else if (posicaoChasi == 270) {
					turnRight(180);
				}
			}

		}else if (anguloTiroInimigo > 225 && anguloTiroInimigo < 315) {
			// X esquerda
			if (flagQuadrante == 4) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 270) {
					turnRight(90);
				}
			}else if (flagQuadrante == 3) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 0 || posicaoChasi == 180) {
					turnRight(90);
					// Talvez mexer
				}
			}else if (flagQuadrante == 2) {
				posicaoChasi = getHeading();
				if (posicaoChasi == 180) {
					turnRight(90);
				}else if (posicaoChasi == 0) {
					turnLeft(90);
				}else if (posicaoChasi == 270) {
					turnRight(180);
				}
			}
		}else if (flagQuadrante == 1) {
			posicaoChasi = getHeading();
			if (posicaoChasi == 270) {
				turnRight(180);
			}else if (posicaoChasi == 0 || posicaoChasi == 90 || posicaoChasi == 180) {
				turnRight(90);
			}
		}
	}

	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		//Bati na parede Negao
		posicaoChasi = getHeading(); // Atualizo a posicao do chassi
		posicaoSorteada = gerador.nextInt(2); // esquerda ou direita
		if (posicaoSorteada == 0) { // esquerda
				novaPosicao = 90;
				turnRight(novaPosicao); 
		}else{ // Direita
				novaPosicao = 90;
				turnLeft(novaPosicao);
		}
	}

	public void onHitRobot(HitRobotEvent e) {
		back(40000);
		if (isTeammate(e.getName())) {
			return;
		}
		double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() +
																									 getHeading() - getGunHeading());
		turnGunRight(turnGunAmt);
		fire(3);
	}

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
		posicaoX = getX();
		posicaoY = getY();
		int flagQuadrante = 0; 
		// em que quadrante estou
		if (posicaoX > 6 && posicaoY > 5) {
			// Primeiro Quadrante
			flagQuadrante = 1;		
		}else if (posicaoX < 6 && posicaoY > 5) {
			// Quarto quadrante
			flagQuadrante = 4;
		}else if (posicaoX < 6 && posicaoY < 5) {
			// Terceiro quadrante
			flagQuadrante = 3;
		}else if (posicaoX > 6 && posicaoY < 5) {
			// Segundo quadrante
			flagQuadrante = 2;
		}

		posicaoChasi = getHeading();
		posicaoMyArm = getGunHeading();
		posicaoMyRadar = getRadarHeading();

		flagQuadrante =1;
		if (flagQuadrante == 1) {
			posicaoSorteada = 0; //gerador.nextInt(3);	
			if (posicaoSorteada == 0) {
				// Vou para CIMA
				novaPosicao = posicaoChasi; // Quanto que falta para ele ajustar para poder andar
				// Vira o corpo
				turnLeft(novaPosicao);
				// Corpo ajustado so andar
				setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
				gunTurnAmt = 10; // Initialize gunTurn to 10
				int count=0;
				while(true){
					ahead(40000);
					turnGunRight(180);
					turnRadarRight(180);

				}
			}else{
				// Vou para DIREITA
				if (posicaoChasi < 90) {
					novaPosicao = 90 - posicaoChasi;
					turnRight(novaPosicao); // VIra o corpo positivamente
				}else{
					novaPosicao = posicaoChasi - 90;
					turnLeft(novaPosicao);	// Vira o corpo negativamente
				}

				while(true){			// Corpo ajustado so andar
					ahead(300);
				}
			}
		}else if (flagQuadrante == 2) {
			posicaoSorteada = gerador.nextInt(3);	
			if (posicaoSorteada == 0) {
				// Vou para CIMA
				novaPosicao = posicaoChasi; // Quanto que falta para ele ajustar para poder andar
				// Vira o corpo
				turnLeft(novaPosicao);
				// Corpo ajustado so andar

				while(true){
					ahead(300);
				}
			}else{
				// Vou para ESQUERDA
				if (posicaoChasi < 270) {
					novaPosicao = 270 - posicaoChasi;
					turnLeft(novaPosicao);
				}else{
					novaPosicao = posicaoChasi - 270;
					turnRight(novaPosicao);
				}

				while(true){			// Corpo ajustado so andar
					ahead(300);
				}
			}
		}else if (flagQuadrante == 3) {
			posicaoSorteada = gerador.nextInt(3);
			if (posicaoSorteada == 0) {
				// Vou para ESQUERDA
				if (posicaoChasi < 270) {
					novaPosicao = 270 - posicaoChasi;
					turnRight(novaPosicao);
				}else{
					novaPosicao = posicaoChasi - 270;
					turnLeft(novaPosicao);
				}

				while(true){			// Corpo ajustado so andar
					ahead(300);
				}	
			}else{
				// Vou para BAIXO
				if (posicaoChasi < 180) {
					novaPosicao = 180 - posicaoChasi;
					turnLeft(novaPosicao);
				}else{
					novaPosicao = posicaoChasi - 180;
					turnRight(novaPosicao);
				}


				while(true){			// Corpo ajustado so andar
					ahead(300);
				}	
			}
		}else if (flagQuadrante == 4) {
			posicaoSorteada = gerador.nextInt(3);
			if (posicaoSorteada == 0) {
				// Vou para BAIXO
				if (posicaoChasi < 180) {
					novaPosicao = 180 - posicaoChasi;
					turnLeft(novaPosicao);
				}else{
					novaPosicao = posicaoChasi - 180;
					turnRight(novaPosicao);
				}

				while(true){			// Corpo ajustado so andar
					ahead(300);
				}	
			}else{
				// Vou para DIREITA
				if (posicaoChasi < 90) {
					novaPosicao = 90 - posicaoChasi;
					turnRight(novaPosicao); // VIra o corpo positivamente
				}else{
					novaPosicao = posicaoChasi - 90;
					turnLeft(novaPosicao);	// Vira o corpo negativamente
				}

				while(true){			// Corpo ajustado so andar
					ahead(300);
				}	
			}
		}
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
		// Strong fire for the win
		fire(3);
	}

}
