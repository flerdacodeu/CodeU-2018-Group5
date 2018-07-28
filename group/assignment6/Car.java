/**
 * Assumption: the unique identifier of a car is a non-zero positive integer
 */
public class Car {

  private final int identifier;
  private final int destinationSlot;

  public Car(int identifier) {
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

    Car car = (Car) obj;
    return this.identifier == car.identifier;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + identifier;
    return result;
  }
}
