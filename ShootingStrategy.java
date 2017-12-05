import java.util.ArrayList;

public interface ShootingStrategy {

	public ArrayList<Projectile> upShots(Player player);
	public ArrayList<Projectile> downShots(Player player);
	public ArrayList<Projectile> leftShots(Player player);
	public ArrayList<Projectile> rightShots(Player player);
	
}