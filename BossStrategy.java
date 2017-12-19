import java.util.ArrayList;

public interface BossStrategy {

	public void useMovement(Boss boss);
	public void useWeapon(Boss boss, ArrayList<Projectile> shots);
	public void changeStrategy(Boss boss);
	
}
