/**
 * A conditional statement that returns either true of false.
 * CS2030S Exercise 4
 * AY25/26 Semester 2
 *
 * @author Gan Jun Xi (12B)
 */
package cs2030s.fp;

public interface BooleanCondition<T> {
  boolean test(T t);
}
