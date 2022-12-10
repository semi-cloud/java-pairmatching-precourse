package pairmatching.utils;

import java.util.function.Function;
import java.util.function.Supplier;

public class InputUtils {
    public static <T> T read(Supplier<T> input) {
        try {
            return input.get();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return read(input);
        }
    }

    public static <T, R> R read(Function<T, R> object, Supplier<T> input) {
        try {
            return object.apply(input.get());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return read(object, input);
        }
    }
}
