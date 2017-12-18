public interface BossStrategy 
{

	public void useMovement(Boss boss);
	public void useWeapon(Boss boss);
	public void changeStrategy(Boss boss);
	
}