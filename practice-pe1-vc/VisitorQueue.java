// @A0322584A
class VisitorQueue extends Queue<Visitor> implements Comparable<VisitorQueue> {
  private final int id;

  public VisitorQueue(int id, int size) {
    super(size);
    this.id = id;
  }

  public int getTicketType() {
    return id;
  }

  @Override
  public boolean enq(Visitor v) {
    if (v.getTicketType() == id) {
      return super.enq(v);
    }
    return false;
  }

  @Override
  public int compareTo(VisitorQueue q) {
    if (super.isEmpty()) {
      return -1;
    }
    if (q.isEmpty()) {
      return 1;
    }
    int i = this.id - q.getTicketType();
    if (i != 0) {
      return i;
    }
    i = super.length() - q.length();
    if (i != 0) {
      return i;
    }
    return -1;
  }

  @Override
  public String toString() {
    return String.format("Q%d ", this.id) + super.toString();
  }
}
