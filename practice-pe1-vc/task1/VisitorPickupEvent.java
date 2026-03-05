// @A0322584A
class VisitorPickupEvent extends Event {
  private TourGuide tourGuide;
  private Visitor visitor;
  private VisitorCentre visitorCentre;

  public VisitorPickupEvent(double time, TourGuide tg, Visitor v, VisitorCentre vc) {
    super(time);
    this.tourGuide = tg;
    this.visitor = v;
    this.visitorCentre = vc;
  }

  @Override
  public String toString() {
    return String.format("%s %s picked up %s at %s",
        super.toString(), this.tourGuide.toString(), this.visitor.toString(),
        this.visitorCentre.toString());
  }

  @Override
  public Event[] simulate() {
    int t = this.visitor.getTicketType();
    if (t == 0) {
      return new Event[] {new VisitorAdmissionEvent(super.getTime() + 0.4, this.tourGuide,
          this.visitor, this.visitorCentre)};
    }
    return new Event[] {new VisitorAdmissionEvent(super.getTime() + 0.2, this.tourGuide,
          this.visitor, this.visitorCentre)};
  }
}
