


## Assignments

1. Setup [IntellijIDEA Ultimate]  (https://www.jetbrains.com/idea/)
2. Setup [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
3. Setup  [Apache Maven](https://maven.apache.org/)
4. Read about new features from [Java 8 to 17](https://openjdk.java.net/projects/jdk/)


# Exam questions
1. <b>Describe 5 new features in Java since version 7</b>
    - Switch Expressions -> https://openjdk.java.net/jeps/325
    - Pattern Matching for instanceof -> https://openjdk.java.net/jeps/375

2. <b>What is local type inference (Java 10)</b> -> https://openjdk.java.net/jeps/286
    - The inference process, substantially, just gives the variable the type of its initializer expression
    - The initializer has no target type (because we haven't inferred it yet). Poly expressions that require such a type, like lambdas, method references, and array initializers, will trigger an error.
    - If the initializer has the null type, an error occurs—like a variable without an initializer, this variable is probably intended to be initialized later, and we don't know what type will be wanted.

            var list = new ArrayList<String>();  // infers ArrayList<String>
            var stream = list.stream();          // infers Stream<String>


3. <b>Describe Java records in (Java 15)</b> -> https://openjdk.java.net/jeps/384
    - Records are classes that act as transparent carriers for immutable data. Records can be thought of as nominal tuples.
    - Reduce boilerplate code -> automatically implements methods ToString(), hashCode(), equals()
    - Geter will be also automatically generated
    - They are kind of like dataclases in python
    - A record is implicitly final, and cannot be abstract
    - A record does not have an extends clause. The superclass of a record is always java.lang.Record
    - The implicitly declared fields corresponding to the record components of a record class are final and moreover are not modifiable via reflection (doing so will throw IllegalAccessException)
    - Any explicit declarations of a member that would otherwise be automatically derived must match the type of the automatically derived member exactly

            record Point(int x, int y) { } 

            // This is eqvivalent to record decleration above
            class Point {
                private final int x;
                private final int y;

                Point(int x, int y) { 
                    this.x = x;
                    this.y = y;
                }

                int x() { return x; }
                int y() { return y; }

                public boolean equals(Object o) { 
                    if (!(o instanceof Point)) return false;
                    Point other = (Point) o;
                    return other.x == x && other.y = y;
                }

                public int hashCode() {
                    return Objects.hash(x, y);
                }

                public String toString() { 
                    return String.format("Point[x=%d, y=%d]", x, y);
                }
            }


3. <b>Describe Java sealed classes introduced (Java 17)</b> -> https://openjdk.java.net/jeps/360, https://openjdk.java.net/jeps/409
    - A sealed class or interface can be extended or implemented only by those classes and interfaces permitted to do so.

            package com.example.geometry;

            public abstract sealed class Shape
                permits Circle, Rectangle, Square, WeirdShape { ... }

            public final class Circle extends Shape { ... }

            public sealed class Rectangle extends Shape 
                permits TransparentRectangle, FilledRectangle { ... }
            public final class TransparentRectangle extends Rectangle { ... }
            public final class FilledRectangle extends Rectangle { ... }

            public final class Square extends Shape { ... }

            public non-sealed class WeirdShape extends Shape { ... }

    - Support future directions in pattern matching by providing a foundation for the exhaustive analysis of patterns.

            Shape rotate(Shape shape, double angle) {
                return switch (shape) {   // pattern matching switch
                    case Circle c    -> c; 
                    case Rectangle r -> shape.rotate(angle);
                    case Square s    -> shape.rotate(angle);
                    // no default needed!
                }
            }

4. <b>Difference between Java and JVM</b>

    | JAVA  | JVM |
    | ------------- | ------------- |
    | Java is programming language  | JVM is the heart of java programming language. |
    | Content Cell  | When we run a program, JVM is responsible to converting Byte code to the machine specific code.  |
    || JVM is also platform dependent and provides core java functions like memory management, garbage collection, security etc.|
    || JVM is customizable and we can use java options to customize it, for example allocating minimum and maximum memory to JVM.|
    || JVM is called virtual because it provides a interface that does not depend on the underlying operating system and machine hardware. |
    || This independence from hardware and operating system is what makes java program write-once run-anywhere |
  

5. <b>How does a HashTable works?</b> -> https://www.youtube.com/watch?v=KyUTuwz_b7Q
