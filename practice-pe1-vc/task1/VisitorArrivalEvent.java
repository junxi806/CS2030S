// @A0322584A
class VisitorArrivalEvent extends Event {
  private VisitorCentre visitorCentre;
  private Visitor visitor;

  public VisitorArrivalEvent(double time, VisitorCentre vc, Visitor v) {
    super(time);
    this.visitorCentre = vc;
    this.visitor = v;
  }

  @Override
  public String toString() {
    return String.format("%s %s arrived at %s",
        super.toString(), visitor.toString(), visitorCentre.toString());
  }

  @Override
  public Event[] simulate() {
    visitorCentre.welcome(this.visitor);
    return new Event[] {};
  }
}
