/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.util;
import java.time.LocalDate;
import java.util.Random;

public final class Util {

    /**
     *
     * @param target date to check if is between start and end
     * @param start init range date
     * @param end end date range
     * @return if the target date is in date or not
     */
    public static boolean isInRange(final LocalDate target,
                                    final LocalDate start,
                                    final LocalDate end) {
        return ((target.isEqual(start) || target.isAfter(start))
               && (target.isEqual(end) || target.isBefore(end)));
    }

    public static double randomPrice() {
        return Math.abs(new Random().nextDouble());
    }

    public static int randomStock() {
        return Math.abs(new Random().nextInt());
    }


    /**
     * Hidden constructor
     */
    private Util() {

    }
}
