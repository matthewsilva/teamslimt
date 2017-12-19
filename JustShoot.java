import java.util.ArrayList;

public class JustShoot implements BossStrategy {

	private int burstCounter;

	private ShootingStrategy shotType;
		
		public JustShoot() {
			burstCounter = 0;
			shotType = new DoubleShot();
		}
		
		@Override
		public void useMovement(Boss boss) {
			boss.setYVelocity(0);
			if(burstCounter > 40)
				boss.setYVelocity(0);
			else if(burstCounter > 30)
				boss.setYVelocity(-8);
			else if(burstCounter > 20)
				boss.setYVelocity(0);
			else if(burstCounter > 10)
				boss.setYVelocity(8);
			
		}

		@Override
		public void useWeapon(Boss boss, ArrayList<Projectile> shots) {
			if(burstCounter < 50) {
				shots.addAll(shotType.rightShots(boss));
				burstCounter++;
			}
		}

		@Override
		public void changeStrategy(Boss boss) {
			//System.out.println("burstCounter = " + burstCounter);
			if (burstCounter >= 50) {
				boss.setStrategy(new MoveNoShoot());
				//System.out.println("Strategy now MoveNoShoot");
			}
			
		}

}
