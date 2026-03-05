// @A0322584A
class VisitorAdmissionEvent extends Event {
  private TourGuide tourGuide;
  private Visitor visitor;
  private VisitorCentre visitorCentre;

  public VisitorAdmissionEvent(double time, TourGuide tg, Visitor v, VisitorCentre vc) {
    super(time);
    this.tourGuide = tg;
    this.visitor = v;
    this.visitorCentre = vc;
  }

  @Override
  public String toString() {
    return String.format("%s %s admitted %s",
        super.toString(), visitor.toString(), visitorCentre.toString());
  }

  @Override
  public Event[] simulate() {
    if (visitorCentre.isEmpty()) {
      return new Event[] {};
    }
    try {
      Visitor v = visitorCentre.admitToAttraction();
      if (v != null) {
        return new Event[] {new VisitorPickupEvent(super.getTime() + 0.1, this.tourGuide, v, this.visitorCentre)};
      }
      return new Event[] {};
    }
    catch (AttractionFullException e) {
      return new Event[] {new AttractionFullEvent(super.getTime(), visitorCentre.getCapacity())};
    }
  }
}
