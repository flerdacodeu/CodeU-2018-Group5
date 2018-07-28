/**
 * Assumptions:
 * - the unique identifier of a parking space is a non-zero positive integer
 * - for an empty parking space the car attribute is null
 */
public class ParkingSpace {

  private final int identifier;

  public ParkingSpace(int identifier) {
    assert identifier > 0;
    this.identifier = identifier;
  }

  public int getIdentifier() {
    return identifier;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }

    ParkingSpace parkingSpace = (ParkingSpace) obj;
    return this.identifier == parkingSpace.identifier;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + identifier;
    return result;
  }
}
