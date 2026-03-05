// @A0322584A
class AttractionFullEvent extends Event {
  private int capacity;

  public AttractionFullEvent(double time, int capacity) {
    super(time);
    this.capacity = capacity;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(" Attraction capacity of %d reached.", this.capacity);
  }

  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
