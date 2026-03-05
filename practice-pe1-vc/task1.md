# Practical Assessment Practice - Visitor Centre Simulation
CS2030S Programming Methodology II

---

## Preliminary

Ensure that the following files are present in your home directory:

- `AttractionFullEvent.java`
- `AttractionFullException.java`
- `Event.java`
- `PE1.java`
- `Queue.java`
- `Seq.java`
- `Simulation.java`
- `Simulator.java`
- `TourGuide.java`
- `TourGuideArrivalEvent.java`
- `Visitor.java`
- `VisitorAdmissionEvent.java`
- `VisitorArrivalEvent.java`
- `VisitorCentre.java`
- `VisitorCentreSimulation.java`
- `VisitorPickupEvent.java`
- `VisitorQueue.java`

Ensure that the following directories are also present:

- `inputs`  
- `outputs`  
- `pristine`

Additionally, make sure that the following files are in your home directory:

- `task1.md` - unencrypted question for Task 1  
- `submit.sh` - submission script  
- `test.sh`  - script to run all test cases for Task 1
- `test.jar` - test jar file for Task 1

**You may add new files as needed**

---

## Provided Classes

The following files have been provided for you.  DO NOT CHANGE THESE FILES.  

+---------------+----------------------------------------------------------+
| File/Class    | Description                                              |
+---------------+----------------------------------------------------------+
| Seq<T>        | Generic sequence data structure with methods to set,     |
|               | get, and find the max and min elements.                  |
| Queue<T>      | Generic queue data structure with methods enq(T),        |
|               | deq(), isEmpty(), and size().                            |
| Event         | Represents a simulation event. Has a time field and an   |
|               | simulate() method to perform the event.                  |
| Simulator     | Manages the simulation. Maintains a priority queue of    |
|               | events and run() to execute them in time order.          |
| Simulation    | Abstract simulation class. Provides init() to set up     |
|               | and run() to execute using a Simulator.                  |
+---------------+----------------------------------------------------------+

We have also provided the following exception class: `AttractionFullException`

---

## Background

In this question, we will simulate a visitor centre for a tourist attraction. 
Visitors arrive with tickets and queue at the visitor centre. Tour guides arrive 
to pick up visitors and escort them into the attraction. The tourist attraction 
has a maximum capacity and can only admit a fixed number of visitors. Once the 
attraction is full, no more visitors can be admitted and the visitors that have 
arrived wait in the queues at the visitor centre.

---

## 1. (2 marks) `TourGuide`

Write a class named `TourGuide`, which represents a tour guide.

Each `TourGuide` has a field `id` of type `int`, which cannot be changed once 
initialized.

The constructor takes an `int` parameter representing the `id`.

`TourGuide` overrides `toString()`.

### Specifications

+----------------+-----------------------------------------------------------+
| Method Name    | toString                                                  |
+----------------+-----------------------------------------------------------+
| Description    | Returns a string in the form `"TG<id>"` (e.g., `"TG123"`) |
| Parameter      | none                                                      |
| Return         | The string representation of the tour guide as above.     |
+----------------+-----------------------------------------------------------+

### Sample output

```text
jshell> TourGuide g = new TourGuide(123)
g ==> TG123
```

### Testing

Run the test cases for Question 1 by running the following command.

```shell
java -cp test.jar:. Test1
```

---

## 2. (4 marks) `Visitor`

Write a class named `Visitor`, which represents a visitor going to the
tourist attraction.

Every visitor is identified by a unique id of type `int`. The id is
assigned at creation time and cannot be changed once initialized.

The first visitor ever created has id **1000**, then **1001**, and so on.

Each visitor also holds a ticket type represented by an `int`, initialised
via the constructor.

Write the methods below:

+----------------+-----------------------------------------------------------+
| Method Name    | toString                                                  |
+----------------+-----------------------------------------------------------+
| Description    | Returns a string in the form `"V<id>"` (e.g., `"V1000"`)  |
| Parameter      | none                                                      |
| Return         | The string representation of the visitor as above.        |
+----------------+-----------------------------------------------------------+

+----------------+-----------------------------------------------------------+
| Method Name    | getTicketType                                             |
+----------------+-----------------------------------------------------------+
| Description    | Returns the visitor's ticket type.                        |
| Parameter      | none                                                      |
| Return         | The visitor's ticket type.                                | 
+----------------+-----------------------------------------------------------+

### Sample output

```text
jshell> Visitor v1 = new Visitor(0)
v1 ==> V1000
jshell> Visitor v2 = new Visitor(1)
v2 ==> V1001
jshell> v1.getTicketType()
$.. ==> 0
jshell> v2.getTicketType()
$.. ==> 1
```

### Testing

Run the test cases for Question 2 by running the following command.

```shell
java -cp test.jar:. Test2
```

---

## 3. (8 marks) `VisitorQueue`

You have been provided with the implementation of `Queue<T>`.

Write a class named `VisitorQueue` that extends `Queue<T>` and allows
comparison between two objects of type `VisitorQueue`. Every visitor queue
has a field named `id` of type `int` that denotes the type of tickets held
by visitors in the queue (each queue is entered only by visitors with the
same type of tickets). The ticket type of a queue is initialised via the
constructor and cannot be updated once initialized. The type of the ticket
of the queue and the sizeof the queue are passed as parameters to the
constructor of `VisitorQueue`.

Write `VisitorQueue` so that it overrides the following methods:

+----------------+-----------------------------------------------------------+
| Method Name    | enq                                                       |
+----------------+-----------------------------------------------------------+
| Description    | Enqueue a visitor only if the visitor's ticket type       |
|                | matches the ticket type of the queue.                     |
| Parameter      | Visitor v - the visitor to be enqueued.                   |
| Return         | `true` if the visitor is successfully enqueued; `false`   |
|                | otherwise.                                                |
+----------------+-----------------------------------------------------------+

+----------------+-----------------------------------------------------------+
| Method Name    | compareTo                                                 |  
+----------------+-----------------------------------------------------------+
| Description    | Compares this queue with another queue using the ordering |
|                | rules below.                                              |
| Parameter      | VisitorQueue q - the queue to compare with.               |
| Return         | A negative integer if this queue is "less than" the       |
|                | argument q, 0 if they are equal, and a positive integer   |
|                | if this queue is "greater than" the argument q according  |
|                | to the ordering rules below otherwise.                    |
+----------------+-----------------------------------------------------------+

+----------------+-----------------------------------------------------------+
| Method Name    | toString                                                  |
+----------------+-----------------------------------------------------------+
| Description    | Returns a string representation of the queue in the form  |
|                | `Q<ticketType> [ <visitor1> <visitor2> ... ]` where       |
|                | `ticketType` is the ticket type of the queue and          |
|                | `<visitor1>`, `<visitor2>`, ... are the visitors in the   |
|                | queue.                                                    |
| Parameter      | none                                                      |
| Return         | The string representation of the queue as above.          |
+----------------+-----------------------------------------------------------+

### Ordering rules for `compareTo`

- An empty queue is always considered the smallest (last in ordering).

– If both queues are empty, the current queue is considered "less than" the
  specified queue.

– When both queues are non-empty, they are first compared by their ticket
  type.

– The queue with the smaller (lower) ticket type is considered "less than"
  the other queue.

– If both queues have the same ticket type, they are then compared by the
  number of visitors.

– The queue with fewer visitors is considered "less than" the other queue.

– If both queues have the same ticket type and the same number of visitors,
  the current queue is considered "less than" the argument queue by default.

### Sample output

```text
jshell> 
..> VisitorQueue vq1 = new VisitorQueue(0, 2)
vq1 ==> Q0 [ ]
jshell> VisitorQueue vq2 = new VisitorQueue(1, 1)
vq2 ==> Q1 [ ]
jshell> VisitorQueue vq3 = new VisitorQueue(0, 1)
vq3 ==> Q0 [ ]
jshell> 
..> vq1.enq(new Visitor(0))
$.. ==> true
jshell> vq1.enq(new Visitor(1))
$.. ==> false
jshell> vq1.enq(new Visitor(0))
$.. ==> true
jshell> vq1.toString()
$.. ==> "Q0 [ V1000 V1002 ]"
jshell> 
..> vq2.compareTo(vq1)
$.. ==> -1
jshell> vq2.enq(new Visitor(1))
$.. ==> true
jshell> vq2.toString()
$.. ==> "Q1 [ V1003 ]"
jshell> vq2.compareTo(vq1)
$.. ==> 1
jshell> 
..> vq3.compareTo(vq1)
$.. ==> -1
jshell> vq3.enq(new Visitor(0))
$.. ==> true
jshell> vq3.toString()
$.. ==> "Q0 [ V1004 ]"
jshell> vq3.compareTo(vq1)
$.. ==> -1
jshell> vq3.compareTo(vq2)
$.. ==> -1
```

### Testing

Run the test cases for Question 3 by running the following command.

```shell
java -cp test.jar:. Test3
```

---

## 4. (10 marks) `VisitorCentre`

You have been provided with the implementation of `Seq<T extends
Comparable<T>>`.

The implementation also provides max and min methods to find the maximum
and minimum elements in the sequence respectively. You have also been
provided with a class `AttractionFullException`. Please skim through
both implementations before you answer this question.

Write a class named `VisitorCentre` that simulates a visitor centre at the
tourist attraction. It maintains a sequence of visitor queues for each
of the following ticket types: `express`, `regular`, and `walk-ins`. It
also maintains the following constant public fields of type int to denote
the ticket types: `EXPRESS` equals 2, `REGULAR` equals 1, and `WALKIN`
equals 0.

`VisitorCentre` is initialised with an `int` queue size `NC` and the `int`
maximum visitors allowed into the attraction, `C`. It has three visitor
queues, one for every ticket type.  Every visitor queue can hold a maximum
of `NC` number of visitors.

Write `VisitorCentre` so that it implements the following methods:

+----------------+-----------------------------------------------------------+
| Method Name    | welcome                                                   |
+----------------+-----------------------------------------------------------+
| Description    | Takes a visitor and enqueues into the appropriate queue.  |
|                | If the queue is full, do nothing.                         |
| Parameter      | Visitor v - the visitor to be welcomed.                   |
| Return         | `this` VisitorCentre after welcoming the visitor.         |
+----------------+-----------------------------------------------------------+

+----------------+-----------------------------------------------------------+
| Method Name    | admitToAttraction                                         |
+----------------+-----------------------------------------------------------+
| Description    | Takes no argument and removes one visitor for admission   |
|                | using priority: EXPRESS, then REGULAR, then WALKIN.       |
|                | Throws `AttractionFullException` with message `Attraction |
|                | capacity of CAPACITY reached` when attraction capacity is |
|                | reached.                                                  |
| Parameter      | none                                                      |
| Return         | The visitor admitted into the attraction.                 |
+----------------+-----------------------------------------------------------+

+----------------+-----------------------------------------------------------+
| Method Name    | toString                                                  |
+----------------+-----------------------------------------------------------+
| Description    | Returns a string representation in the following (example)|
|                | format.                                                   |
|                | `VC: [ 0:Q0 [ V1000], 1:Q1 [ V1001 ] ]`.                  |
| Parameter      | none                                                      |
| Return         | The string representation of the visitor centre as above. |
+----------------+-----------------------------------------------------------+

### Sample output

```text
jshell> 
..> System.out.println("EXPRESS: " + VisitorCentre.EXPRESS)
EXPRESS: 2
jshell> System.out.println("REGULAR: " + VisitorCentre.REGULAR)
REGULAR: 1
jshell> System.out.println("WALKIN: " + VisitorCentre.WALKIN)
WALKIN: 0
jshell> 
..> VisitorCentre vc = new VisitorCentre(2, 3)
vc ==> VC: [ 0:Q0 [ ], 1:Q1 [ ], 2:Q2 [ ] ]
jshell> vc.admitToAttraction()
$.. ==> null
jshell> 
..> vc.welcome(new Visitor(0))
$.. ==> VC: [ 0:Q0 [ V1000 ], 1:Q1 [ ], 2:Q2 [ ] ]
jshell> vc.welcome(new Visitor(2))
$.. ==> VC: [ 0:Q0 [ V1000 ], 1:Q1 [ ], 2:Q2 [ V1001 ] ]
jshell> 
..> vc.admitToAttraction()
$.. ==> V1001
jshell> 
..> vc.welcome(new Visitor(0))
$.. ==> VC: [ 0:Q0 [ V1000 V1002 ], 1:Q1 [ ], 2:Q2 [ ] ]
jshell> vc.welcome(new Visitor(0))
$.. ==> VC: [ 0:Q0 [ V1000 V1002 ], 1:Q1 [ ], 2:Q2 [ ] ]
jshell> vc.welcome(new Visitor(1))
$.. ==> VC: [ 0:Q0 [ V1000 V1002 ], 1:Q1 [ V1004 ], 2:Q2 [ ] ]
jshell> 
..> vc.admitToAttraction()
$.. ==> V1004
jshell> vc.admitToAttraction()
$.. ==> V1000
jshell> vc.admitToAttraction()
jdk.jshell.EvalException: Attraction capacity of 3 reached.
	at VisitorCentre.admitToAttraction(#8:35)
	at .(#22:1)
```

### Testing

Run the test cases for Question 4 by running the following command.

```shell
java -cp test.jar:. Test4
```

---

## 6. (16 marks) `Simulation`

Now we are ready to implement our simulation. We have provided
you with the class `Event`, `Simulator`, `Simulation`, and
`VisitorCentreSimulation`.  The first three classes are the same as what
we provided in Exercises 1 - 3.

To initialize the `VisitorCentreSimulation`, it reads the following
standard input:

- Two positive integers `N_q` and `C` that correspond to the size of any
  queue in the visitor centre (equals to the number of queues), and the
  capacity of the tourist attraction.  - Two positive integers `N_a` and
  `N_v` that correspond to the number of admins and the number of visitors.

- The next `N_a` lines contain two values each, representing arrival
  time and admin id.  - The next `N_v` lines contain two values each,
  representing the arrival time, and ticket type of the visitor.

You can assume that the input is correctly formatted and always follows
the specified restrictions.

The code to read these inputs has been given to you in
`VisitorCentreSimulation`.  The code to initialize the events has also
been given but has been commented out.  A good place to start is to
uncomment the two lines and write the necessary classes to make the
simulation run properly.

After initializing the simulator with the events specified in the input
files above, the simulation runs as follows:

- Visitors arrive at the specified timings at the visitor centre and
  are placed into the appropriate queue.  If a queue reaches its maximum
  capacity, the visitor is silently turned away.

- Tour guides arrive at the specified timings at the visitor centre to
  pick up visitors and admit them into the attraction.

- Upon arrival, a tour guide waits 0.1 units of time before it picks up a
  visitor.  The visitor to pick up is selected via the `admitToAttraction`
  method.

- Time between picking up a visitor and admitting them into the
  attraction depends on the type of ticket s/he holds, a WALKIN ticket
  takes 0.4 units of time, while another ticket takes 0.2 units of time.

- After a visitor is admitted, the tour guide waits 0.1 units of time
  before picking up the next visitor.

- If the tourist attraction reaches full capacity, all visitors and
  tour guides wait in the visitor center and the simulation stalls.

- If there is no visitor left to pick up at the visitor center, the
  tour guide departs the visitor centre.

### Events to print

- `TourGuideArrivalEvent` - arrival of a tour guide
- `VisitorArrivalEvent` - arrival of a visitor
- `VisitorPickupEvent` - a tour guide picks up a visitor
- `AttractionFullEvent` - attraction capacity reached
- `VisitorAdmissionEvent` - a visitor admitted into the attraction

### Sample output

```text
1.000 V1000 arrived at VC: [ 0:Q0 [ ], 1:Q1 [ ], 2:Q2 [ ] ]
2.000 TG123 arrived at VC: [ 0:Q0 [ V1000 ], 1:Q1 [ ], 2:Q2 [ ] ]
2.100 TG123 picked up V1000 at VC: [ 0:Q0 [ ], 1:Q1 [ ], 2:Q2 [ ] ]
2.200 V1001 arrived at VC: [ 0:Q0 [ ], 1:Q1 [ ], 2:Q2 [ ] ]
2.300 V1002 arrived at VC: [ 0:Q0 [ ], 1:Q1 [ V1001 ], 2:Q2 [ ] ]
2.400 V1003 arrived at VC: [ 0:Q0 [ ], 1:Q1 [ V1001 ], 2:Q2 [ V1002 ] ]
2.500 V1000 admitted VC: [ 0:Q0 [ ], 1:Q1 [ V1001 V1003 ], 2:Q2 [ V1002 ] ]
2.600 TG123 picked up V1002 at VC: [ 0:Q0 [ ], 1:Q1 [ V1001 V1003 ], 2:Q2 [ ] ]
2.700 V1004 arrived at VC: [ 0:Q0 [ ], 1:Q1 [ V1001 V1003 ], 2:Q2 [ ] ]
2.800 V1002 admitted VC: [ 0:Q0 [ ], 1:Q1 [ V1001 V1003 ], 2:Q2 [ ] ]
2.900 Attraction capacity of 2 reached.
```

### In the example above, 

- Lines 1, 4, 5, 6, and 9 are output from the arrival events of the
  visitors. Each line prints a visitor, followed by the string " arrived at
  ";, followed by the visitor centre.

- Line 2 corresponds to the arrival events of the tour guide. It prints
  the tour guide, followed by the string " arrived at ", followed by the
  visitor centre.

- Lines 3 and 8 correspond to a tour guide picking up a visitor from
  the queue. It prints the tour guide, followed by " picked up ", followed
  by the visitor, followed by " at " and then the visitor centre.

- Lines 7 and 10 correspond to the entry of visitors from the visitor
  centre to the attraction.  Each line prints the visitor, followed by
  a string " admitted ", followed by the visitor centre.

- Line 11 corresponds congestion event when a tourist attraction cannot
  admit any more visitors.  The error message of the exception is printed
  on the output.

- Please note that at time 2.700, `V1004` was silently dropped since
  the visitor queue for the ticket type is full.

## Testing

You can test individual test cases for the simulation using the following
command.

```shell
java PE1 < inputs/PE1.X.task1.in
```

You can run all test cases for the simulation using the following command.

```shell
bash test.sh task1
```

---

## Submit

Submit your Task 1 by running the following command.

```shell
bash submit.sh task1
```

---

# END OF PAPER
