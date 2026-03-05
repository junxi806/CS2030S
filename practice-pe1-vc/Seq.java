public class Seq<T extends Comparable<T>> {
  private T[] array;

  @SuppressWarnings("unchecked")
  public Seq(int size) {
    @SuppressWarnings("rawtypes")
    Comparable[] arr = new Comparable[size];
    this.array = (T[]) arr;
  }

  public void set(int index, T item) {
    if (index < this.array.length) {
      this.array[index] = item;
    }
  }

  public T get(int index) {
    return this.array[index];
  }

  public int size() {
    return this.array.length;
  }

  public T min() {
    T minimum = null;

    for (int i = 0; i < this.array.length; i++) {
      if (this.array[i].compareTo(minimum) < -1) {
        minimum = this.array[i];
      }
    }
    return minimum;
  }

  public T max() {
    T maximum = null;

    for (int i = 0; i < this.array.length; i++) {
      if (this.array[i].compareTo(maximum) > -1) {
        maximum = this.array[i];
      }
    }
    return maximum;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < this.array.length; i++) {
      s.append(i + ":" + this.array[i]);
      if (i != this.array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
