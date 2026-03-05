// @A0322584A
class VisitorCentre extends Seq<VisitorQueue> {
  public static final int EXPRESS = 2;
  public static final int REGULAR = 1;
  public static final int WALKIN = 0;
  private int capacity;
  private int maxCapacity;

  public VisitorCentre(int NC, int C) {
    super(3);
    for (int i = 0; i < 3; i++) {
      set(i, new VisitorQueue(i, NC));
    }
    this.capacity = C;
    this.maxCapacity = C;
  }

  public int getCapacity() {
    return this.maxCapacity;
  }

  public boolean isEmpty() {
    for (int i = 0; i < 3; i++) {
      VisitorQueue q = super.get(i);
      if (!q.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  public VisitorCentre welcome(Visitor v) {
    int ticketType = v.getTicketType();
    VisitorQueue visitorQueue = super.get(ticketType);
    visitorQueue.enq(v);
    return this;
  }

  public Visitor admitToAttraction() throws AttractionFullException {
    try {
      for (int i = 2; i >= 0; i--) {
        if (capacity == 0) {
         throw new AttractionFullException(this.maxCapacity);
        }
        VisitorQueue visitorQueue = super.get(i);
        Visitor v = visitorQueue.deq();
        if (v != null) {
          this.capacity--;
          return v;
        }
      }
      return null;
    }
    finally {}
  }

  @Override
  public String toString() {
    return String.format("VC: [ 0:%s, 1:%s, 2:%s ]",
        super.get(0).toString(), super.get(1).toString(), super.get(2).toString());
  }
}
