/**
 * CS2030S Exercise 5
 * AY25/26 Semester 2
 *
 * @author Gan Jun Xi (12B)
 */
package cs2030s.fp;

import java.util.NoSuchElementException;

public abstract class Maybe<T> {
  private static final None n = new None();

  protected abstract T get();
  public abstract Maybe<T> filter(BooleanCondition<? super T> condition);
  public abstract <R> Maybe<R> map(Transformer<? super T, ? extends R> t);

  public static <T> Maybe<T> of(T obj) {
    if (obj == null) {
      return none();
    }
    return some(obj);
  }

  // none is always empty
  @SuppressWarnings("unchecked")
  public static <T> Maybe<T> none() {
    return (Maybe<T>) n;
  }

  public static <T> Maybe<T> some(T t) {
    return (Maybe<T>) new Some<T> (t);
  }

  private static class None extends Maybe<Object> {

    private None() {}

    @Override 
    public Maybe<Object> filter(BooleanCondition<? super Object> condition) {
    return super.none();
    }

    @Override
    public <R> Maybe<R> map(Transformer<? super Object, ? extends R> t) {
      return super.none();
    }

    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }

    @Override
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof None) {
        return true;
      }
      return false;
    }
  }

  private static final class Some<T> extends Maybe<T> {
    private final T some;

    private Some(T t) {
      this.some = t;
    }

    @Override 
    public Maybe<T> filter(BooleanCondition<? super T> condition) {
      if (this.some != null) {
        if (!condition.test(this.some)) {
          return super.none();
        }
      }
      return this;
    }

    @Override
    public <R> Maybe<R> map(Transformer<? super T, ? extends R> transformer) throws NullPointerException{
      try {
        R res = transformer.transform(this.some);
        return super.some(res);
      }
      catch (NullPointerException e) {
        throw new NullPointerException();
      }
    }

    @Override
    protected T get() {
      return this.some;
    }

    @Override
    public String toString() {
      return "[" + some + "]";
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Some<?>) {
        Some<?> s = (Some<?>) obj;
        if (this.some == null) {
          if (s.get() == null) {
            return true;
          }
          return false;
        }
        if (this.some.equals(s.get())) {
          return true;
        }
        return false;
      }
      return false;
    }
  }
}
