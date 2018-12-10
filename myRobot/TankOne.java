package myRobot;
import robocode.*;
import java.awt.Color;
import java.util.Random;
import robocode.Robot;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import robocode.ScannedRobotEvent;


// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * LupsTanque - a robot by (your name here)
 */
public class TankOne extends Robot
{
	/**
	 * run: LupsTanque's default behavior
	 */
	Random gerador = new Random();
	public double posicaoChasi;
	public int posicaoSorteada = 0;//gerador.nextInt(4);	// Sorteio para direcao que irei
	public double novaPosicao;
	public double posicaoX;
	public double posicaoY;
	public double posicaoMyArm;
	public double posicaoMyRadar;
	public double gunTurnAmt;

	public void run() {
					setAdjustRadarForGunTurn(true);
		
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar
		setBodyColor(Color.blue);
		setGunColor(Color.black);
		setRadarColor(Color.red);

		// Verifico o quadrante do nascimento
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
				// setAdjustGunForRobotTurn(true);
				// setAdjustRadarForRobotTurn(true);
				// ajusta arma
				// if (posicaoMyArm > 180) {
				// 	posicaoMyArm = posicaoMyArm - 180;
				// 	turnGunRight(posicaoMyArm);
				// }else{
				// 	posicaoMyArm = 180 - posicaoMyArm;
				// 	turnGunLeft(posicaoMyArm);
				// }
				// // ajusta Radar
				// if (posicaoMyRadar > 180) {
				// 	posicaoMyRadar = posicaoMyRadar - 180;
				// 	turnRadarRight(posicaoMyRadar);
				// }else{
				// 	posicaoMyRadar = 180 - posicaoMyRadar;
				// 	turnRadarLeft(posicaoMyRadar);
				// }
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
				
				// ajusta arma
				// if (posicaoMyArm > 270) {
				// 	posicaoMyArm = posicaoMyArm - 270;
				// 	turnGunRight(posicaoMyArm);
				// }else{
				// 	posicaoMyArm = 270 - posicaoMyArm;
				// 	turnGunLeft(posicaoMyArm);
				// }
				// // ajusta Radar
				// if (posicaoMyRadar > 270) {
				// 	posicaoMyRadar = posicaoMyRadar - 270;
				// 	turnRadarRight(posicaoMyRadar);
				// }else{
				// 	posicaoMyRadar = 270 - posicaoMyRadar;
				// 	turnRadarLeft(posicaoMyRadar);
				// }

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
				
				// ajusta arma
				// if (posicaoMyArm > 180) {
				// 	posicaoMyArm = posicaoMyArm - 180;
				// 	turnGunRight(posicaoMyArm);
				// }else{
				// 	posicaoMyArm = 180 - posicaoMyArm;
				// 	turnGunLeft(posicaoMyArm);
				// }
				// // ajusta Radar
				// if (posicaoMyRadar > 180) {
				// 	posicaoMyRadar = posicaoMyRadar - 180;
				// 	turnRadarRight(posicaoMyRadar);
				// }else{
				// 	posicaoMyRadar = 180 - posicaoMyRadar;
				// 	turnRadarLeft(posicaoMyRadar);
				// }

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
				
				// ajusta arma
				// if (posicaoMyArm > 90) {
				// 	posicaoMyArm = posicaoMyArm - 90;
				// 	turnGunRight(posicaoMyArm);
				// }else{
				// 	posicaoMyArm = 90 - posicaoMyArm;
				// 	turnGunLeft(posicaoMyArm);
				// }
				// // ajusta Radar
				// if (posicaoMyRadar > 90) {
				// 	posicaoMyRadar = posicaoMyRadar - 90;
				// 	turnRadarRight(posicaoMyRadar);
				// }else{
				// 	posicaoMyRadar = 90 - posicaoMyRadar;
				// 	turnRadarLeft(posicaoMyRadar);
				// }

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

				// ajusta arma
				// if (posicaoMyArm > 90) {
				// 	posicaoMyArm = posicaoMyArm - 90;
				// 	turnGunRight(posicaoMyArm);
				// }else{
				// 	posicaoMyArm = 90 - posicaoMyArm;
				// 	turnGunLeft(posicaoMyArm);
				// }
				// // ajusta Radar
				// if (posicaoMyRadar > 90) {
				// 	posicaoMyRadar = posicaoMyRadar - 90;
				// 	turnRadarRight(posicaoMyRadar);
				// }else{
				// 	posicaoMyRadar = 90 - posicaoMyRadar;
				// 	turnRadarLeft(posicaoMyRadar);
				// }

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


				// turnGunLeft(posicaoMyArm);
				// turnRadarLeft(posicaoMyRadar);

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

				// turnGunLeft(posicaoMyArm);
				// turnRadarLeft(posicaoMyRadar);

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

				// ajusta arma
				// if (posicaoMyArm > 270) {
				// 	posicaoMyArm = posicaoMyArm - 270;
				// 	turnGunRight(posicaoMyArm);
				// }else{
				// 	posicaoMyArm = 270 - posicaoMyArm;
				// 	turnGunLeft(posicaoMyArm);
				// }
				// ajusta Radar
				// if (posicaoMyRadar > 270) {
				// 	posicaoMyRadar = posicaoMyRadar - 270;
				// 	turnRadarRight(posicaoMyRadar);
				// }else{
				// 	posicaoMyRadar = 270 - posicaoMyRadar;
				// 	turnRadarLeft(posicaoMyRadar);
				// }


				while(true){			// Corpo ajustado so andar
					ahead(300);
				}	
			}
		}
		//REMOVI CODIGO DAQUI
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		double anguloDoInimigo;
		anguloDoInimigo = e.getBearing();
		double anguloArma = getGunHeading();
		double robotDistance = e.getDistance();
		if (e.getDistance() < 50 && getEnergy() > 50) {
			turnGunRight(anguloDoInimigo);
			fire(2);
		} // otherwise, fire 1.
		else {
			turnGunRight(anguloDoInimigo);
			fire(1);
		}
		// Call scan again, before we turn the gun
		scan();
	}
	

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
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
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
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
		// // primeira batida nao precisa pois ta ajustado
		// 	//ajusto arma e Radar
		// 	double posicaoParede = e.getBearing();
		// 	if (posicaoParede == 0 ) {
		// 		turnRadarRight(180);
		// 		turnGunRight(180);	
		// 	}else if (posicaoParede == 90 ) {
		// 		turnRadarLeft(180);
		// 		turnGunLeft(180);
		// 	}else if (posicaoParede == 180 ){
		// 		turnRadarLeft(180);
		// 		turnGunLeft(180);
		// 	}else if (posicaoParede == 270) {
		// 		turnRadarRight(180);
		// 		turnGunRight(180);
		// 	}

	}	

	public void onHitRobot(HitRobotEvent e) {
		double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() +
		getHeading() - getGunHeading());
		turnGunRight(turnGunAmt);
		fire(1);
		back(40000);
	}
}
