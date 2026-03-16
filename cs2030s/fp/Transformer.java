/**
 * The Transformer interface that can transform a type T
 * to type U.
 * CS2030S Exercise 4
 * AY25/26 Semester 2
 *
 * @author Gan Jun Xi (12B)
 */
package cs2030s.fp;

public interface Transformer<T, U> {
  U transform(T t);
}
