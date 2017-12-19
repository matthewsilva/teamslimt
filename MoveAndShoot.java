import java.util.ArrayList;

public class MoveAndShoot implements BossStrategy {

private int timesPassed;
private int burstCounter;

private ShootingStrategy shotType;
	
	public MoveAndShoot() {
		timesPassed = 0;
		burstCounter = 0;
		shotType = new singleShot();
	}
	
	@Override
	public void useMovement(Boss boss) {
		// TODO Auto-generated method stub
		if(boss.getYPos() >= 600) {
			boss.moveTo(boss.getXPos(),590);
			boss.setYVelocity(-6.0);
			timesPassed++;
			burstCounter = 0;
		}
		if(boss.getYPos() >= 300 && boss.getYPos() <= 275) {
			burstCounter = 0;
		}
		if(boss.getYPos() <= 100) {
			boss.moveTo(boss.getXPos(), 110);
			boss.setYVelocity(6.0);
			timesPassed++;
			burstCounter = 0;
		}
	}

	@Override
	public void useWeapon(Boss boss, ArrayList<Projectile> shots) {
		if(burstCounter < 10) {
			shots.addAll(shotType.rightShots(boss));
			burstCounter++;
		}
	}

	@Override
	public void changeStrategy(Boss boss) {
		//System.out.println("timesPassed = " + timesPassed);
		if (timesPassed > 6) {
			boss.setStrategy(new JustShoot());
			//System.out.println("Changed to JustShoot");
		}
	}

}
