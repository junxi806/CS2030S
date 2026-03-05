import java.util.Scanner;

/**
 * This class implements a RT System simulation.
 *
 * @author Ashish Dandekar
 * @version CS2030S AY23/24 Semester 2
 */
class VisitorCentreSimulation extends Simulation {
  // TODO: Uncomment this line to get started
  private VisitorCentre vc;
  /**
   * The list of ticket events to populate
   * the simulation with.
   */
  public Event[] initEvents;

  /**
   * Constructor for a Visitor Centre simulation.
   *
   */
  public VisitorCentreSimulation(Scanner sc) {
    int queueSize = sc.nextInt();
    int capacity = sc.nextInt();

    // TODO: Uncomment this line to get started
    this.vc = new VisitorCentre(queueSize, capacity);

    int numGuides = sc.nextInt();
    int numVisitors = sc.nextInt();
    initEvents = new Event[numGuides + numVisitors];

    int id = 0;

    for (int i = 0; i < numGuides; i++) {
      double arrivalTime = sc.nextDouble();
      int guideId = sc.nextInt();
      // TODO: uncomment these two lines to get started
      TourGuide guide = new TourGuide(guideId);
      initEvents[id] = new TourGuideArrivalEvent(arrivalTime, vc, guide);
      id += 1;
    }

    for (int i = 0; i < numVisitors; i++) {
      double arrivalTime = sc.nextDouble();
      int ticketType = sc.nextInt();
      // TODO: uncomment these two lines to get started
      Visitor visitor = new Visitor(ticketType);
      initEvents[id] = new VisitorArrivalEvent(arrivalTime, vc, visitor);
      id += 1;
    }
  }

  /**
   * Retrieve an array of events to populate the
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
