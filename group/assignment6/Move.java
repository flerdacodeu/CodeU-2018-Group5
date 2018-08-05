/**
 * Represents the move of a car from a parking space to another parking space
 */
public class Move {
  private final Car car;
  private final ParkingSpace fromSpace;
  private final ParkingSpace toSpace;

  public Move(Car car, ParkingSpace fromSpace, ParkingSpace toSpace) {
	this.car = car;
	this.fromSpace = fromSpace;
	this.toSpace = toSpace;
  }

  public void getMoveInfo() {
	String info = "move car #" + car.getIdentifier() +
		" from space " + fromSpace.getIdentifier() + " to space " + toSpace.getIdentifier();
	System.out.println(info);
  }

  @Override
  public boolean equals(Object obj) {
	if (obj == null || this.getClass() != obj.getClass()) {
	  return false;
	}

	Move move = (Move) obj;
	return this.car.equals(move.car) && this.fromSpace.equals(move.fromSpace) && this.toSpace.equals(move.toSpace);
  }

  @Override
  public int hashCode() {
	int result = 17;
	result = 31 * result + car.hashCode();
	result = 31 * result + fromSpace.hashCode();
	result = 31 * result + toSpace.hashCode();
	return result;
  }
}
