import java.util.ArrayList;

public class MoveNoShoot implements BossStrategy {

	private int timesPassed;
	
	public MoveNoShoot() {
		timesPassed = 0;
	}
	@Override
	public void useMovement(Boss boss) {
		if(boss.getYPos() >= 800) {
			boss.setYVelocity(-3.0);
			timesPassed++;
		}
		else if(boss.getYPos() <= 100) {
			boss.setYVelocity(3.0);
			timesPassed++;
		}
		else
			boss.setYVelocity(-3);
		// TODO Auto-generated method stub

	}

	@Override
	public void useWeapon(Boss boss, ArrayList<Projectile> shots) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeStrategy(Boss boss) {
		// TODO Auto-generated method stub
		//System.out.println("timesPassed = " + timesPassed);
		if(timesPassed > 6) {
			boss.setStrategy(new MoveAndShoot());
			//System.out.println("Changed to MoveAndShoot");
		}
	}

}
