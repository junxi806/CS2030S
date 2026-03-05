// @A0322584A
class TourGuideArrivalEvent extends Event {
  TourGuide tourGuide;
  VisitorCentre visitorCentre;

  public TourGuideArrivalEvent(double time, VisitorCentre vc, TourGuide tg) {
    super(time);
    this.visitorCentre = vc;
    this.tourGuide = tg;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(" %s arrived at %s",
        this.tourGuide.toString(), visitorCentre.toString());
  }

  @Override
  public Event[] simulate() {
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
