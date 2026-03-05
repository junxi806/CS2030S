// @A0322584A
class TourGuide {
   private final int id;
   
   public TourGuide(int id) {
     this.id = id;
   }

   @Override
   public String toString() {
     return String.format("TG%d", id);
   }
}
