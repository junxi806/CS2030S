// @A0322584A
class Visitor {
  private static int count = 999;
  private final int id;
  private int ticketType;

  public Visitor(int ticketType) {
    this.id = ++count;
    this.ticketType = ticketType;
  }

  public String toString() {
    return String.format("V%d", id);
  }

  public int getTicketType() {
    return ticketType;
  }
}
