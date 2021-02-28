package com.jivanpatil.interval;


import com.jivanpatil.exception.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Test class to validate the interval operations.
 * */
class IntervalsTest {
    Intervals intervals = new Intervals();

    @Test
    @DisplayName("Test to validate remove interval - when both input parameters are empty.")
    public void testRemoveInterval_BothIntervalEmpty(){
        List<Integer[]> result = intervals.removeIntervals(Collections.emptyList(),Collections.emptyList());
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test to validate remove interval - when first input parameter is empty.")
    public void testRemoveInterval_FirstIntervalEmpty(){
        List<Integer[]> integerIntervals = new ArrayList<>();
        integerIntervals.add(new Integer[]{10,100});
        List<Integer[]> result = intervals.removeIntervals(Collections.emptyList(),integerIntervals);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test to validate remove interval - when second input parameter is empty.")
    public void testRemoveInterval_SecondIntervalEmpty(){
        List<Integer[]> integerIntervals = new ArrayList<>();
        integerIntervals.add(new Integer[]{10,100});

        List<Integer[]> result = intervals.removeIntervals(integerIntervals,Collections.emptyList());
        Assertions.assertArrayEquals(integerIntervals.get(0),result.get(0));
    }

    @Test
    @DisplayName("TODO : Test to validate remove interval - when input parameter is null.")
    public void testMergeInterval_NullInterval(){

    }

    /**
     *    ---------------
     *         ------
     * */
    @Test
    @DisplayName("Test to validate remove intervals - simple use case")
    public void testRemoveInterval_UseCaseOne(){
        List<Integer[]> includeInterval = new ArrayList<>();
        includeInterval.add(new Integer[]{10,100});

        List<Integer[]> excludeInterval = new ArrayList<>();
        excludeInterval.add(new Integer[]{50,60});

        List<Integer[]> expectedResult = new ArrayList<>();
        expectedResult.add(new Integer[]{10,49});
        expectedResult.add(new Integer[]{61,100});

        List<Integer[]> actualResult = intervals.removeIntervals(includeInterval, excludeInterval);
        Assertions.assertArrayEquals(expectedResult.get(0),actualResult.get(0));
        Assertions.assertArrayEquals(expectedResult.get(1),actualResult.get(1));
        Assertions.assertEquals(expectedResult.size(),actualResult.size());
    }

    /**
     *    -------
     *       -------
     * */
    @Test
    @DisplayName("Test to validate remove intervals - use case two")
    public void testRemoveInterval_UseCaseTwo(){
        List<Integer[]> includeInterval = new ArrayList<>();
        includeInterval.add(new Integer[]{10,100});

        List<Integer[]> excludeInterval = new ArrayList<>();
        excludeInterval.add(new Integer[]{95,110});

        List<Integer[]> result = intervals.removeIntervals(includeInterval, excludeInterval);
        Assertions.assertArrayEquals(result.get(0),new Integer[]{10,94});

    }

    /**
     *     -------
     *  -------
     * */
    @Test
    @DisplayName("Test to validate remove intervals - use case three")
    public void testRemoveInterval_UseCaseThree(){
        List<Integer[]> includeInterval = new ArrayList<>();
        includeInterval.add(new Integer[]{10,100});

        List<Integer[]> excludeInterval = new ArrayList<>();
        excludeInterval.add(new Integer[]{5,50});

        List<Integer[]> result = intervals.removeIntervals(includeInterval, excludeInterval);
        Assertions.assertArrayEquals(new Integer[]{51,100},result.get(0));

    }

    @Test
    @DisplayName("Test to validate remove intervals - use case four")
    public void testRemoveInterval_UseCaseFour(){
        List<Integer[]> includeInterval = new ArrayList<>();
        includeInterval.add(new Integer[]{10,100});
        includeInterval.add(new Integer[]{200,300});
        includeInterval.add(new Integer[]{400,500});

        List<Integer[]> excludeInterval = new ArrayList<>();
        excludeInterval.add(new Integer[]{95,205});
        excludeInterval.add(new Integer[]{410,420});

        List<Integer[]> expectedResult = new ArrayList<>();
        expectedResult.add(new Integer[]{10,94});
        expectedResult.add(new Integer[]{206,300});
        expectedResult.add(new Integer[]{400,409});
        expectedResult.add(new Integer[]{421,500});

        List<Integer[]> actualResult = intervals.removeIntervals(includeInterval, excludeInterval);

        for(int index = 0; index < expectedResult.size();index++){
            Assertions.assertArrayEquals(expectedResult.get(index),actualResult.get(index));
        }
    }

    @Test
    @DisplayName("Test to validate remove intervals - use case five")
    public void testRemoveInterval_UseCaseFive(){
        List<Integer[]> includeInterval = new ArrayList<>();
        includeInterval.add(new Integer[]{10,100});
        includeInterval.add(new Integer[]{200,300});

        List<Integer[]> excludeInterval = new ArrayList<>();
        excludeInterval.add(new Integer[]{95,205});

        List<Integer[]> expectedResult = new ArrayList<>();
        expectedResult.add(new Integer[]{10,94});
        expectedResult.add(new Integer[]{206,300});

        List<Integer[]> actualResult = intervals.removeIntervals(includeInterval, excludeInterval);

        for(int index = 0; index < expectedResult.size();index++){
            Assertions.assertArrayEquals(expectedResult.get(index),actualResult.get(index));
        }
    }

    @Test
    @DisplayName("Test to validate remove intervals - use case six")
    public void testRemoveInterval_UseCaseSix(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{10,60});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{70,100});

        List<Integer[]> actualResult = intervals.removeIntervals(include,exclude);
        Assertions.assertArrayEquals(actualResult.get(0),include.get(0));

    }

    @Test
    @DisplayName("Test to validate remove intervals - both intervals are equal")
    public void testRemoveInterval_BothIntervalEqual(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{10,50});
        include.add(new Integer[]{100,200});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{10,50});
        exclude.add(new Integer[]{100,200});

        List<Integer[]> actualResult = intervals.removeIntervals(include,exclude);
        Assertions.assertTrue(actualResult.isEmpty());

    }

    @Test
    @DisplayName("Test to validate remove intervals - exclude begins first")
    public void testRemoveInterval_SecondBeginsFirst(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{10,50});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{1,25});

        List<Integer[]> result = intervals.removeIntervals(include,exclude);
        Assertions.assertArrayEquals(result.get(0),new Integer[]{26,50});

    }

    @Test
    @DisplayName("Test to validate remove intervals - exclude begins first")
    public void testRemoveInterval_SecondBeginsFirst_NonOverlapping(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{50,100});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{1,25});

        List<Integer[]> result = intervals.removeIntervals(include,exclude);
        Assertions.assertArrayEquals(result.get(0),include.get(0));

    }

    @Test
    @DisplayName("Test to validate remove intervals - one equal interval")
    public void testRemoveInterval_FirstEqualInterval(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{1,25});
        include.add(new Integer[]{50,100});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{50,100});

        List<Integer[]> result = intervals.removeIntervals(include,exclude);
        Assertions.assertArrayEquals(result.get(0),include.get(0));
        Assertions.assertEquals(result.size(),1);

    }

    @Test
    @DisplayName("Test to validate remove intervals - one equal interval")
    public void testRemoveInterval_SecondEqualInterval(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{1,25});
        include.add(new Integer[]{50,100});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{1,25});

        List<Integer[]> result = intervals.removeIntervals(include,exclude);
        Assertions.assertArrayEquals(result.get(0),include.get(0));
        Assertions.assertEquals(result.size(),1);

    }

    @Test
    @DisplayName("Test to validate remove intervals - one number remains")
    public void testRemoveInterval_OneNumberRemains(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{1,100});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{2,99});

        List<Integer[]> result = intervals.removeIntervals(include,exclude);
        Assertions.assertArrayEquals(result.get(0),new Integer[]{1,1});
        Assertions.assertArrayEquals(result.get(1),new Integer[]{100,100});
        Assertions.assertEquals(result.size(),2);

    }

    @Test
    @DisplayName("Test to validate remove intervals - interval beginning same.")
    public void testRemoveInterval_IntervalBeginningSame(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{1,100});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{1,10});

        List<Integer[]> result = intervals.removeIntervals(include,exclude);
        Assertions.assertArrayEquals(result.get(0),new Integer[]{11,100});
        Assertions.assertEquals(result.size(),1);

    }

    @Test
    public void five(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{20,40});
        include.add(new Integer[]{60,80});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{1,100});

        List<Integer[]> result = intervals.removeIntervals(include,exclude);
        Assertions.assertEquals(2, result.size());

    }

    @Test
    @DisplayName("Test remove intervals, all non-overlapping")
    public void testRemoveIntervals_NoneOverlappingIntervals_ExcludeBigger(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{20,40});
        include.add(new Integer[]{60,80});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{100,120});

        List<Integer[]> result = intervals.removeIntervals(include,exclude);
        Assertions.assertEquals(result.size(),2);

    }

    @Test
    @DisplayName("Test remove intervals, all non-overlapping")
    public void testRemoveIntervals_NoneOverlappingIntervals(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{20,40});
        include.add(new Integer[]{60,80});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{1,10});

        List<Integer[]> result = intervals.removeIntervals(include,exclude);
        Assertions.assertEquals(result.size(),2);

    }

    @Test()
    @DisplayName("Test to validate the invalid input")
    public void testRemoveInterval_InvalidInput(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{20,40});
        include.add(new Integer[]{60});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{1,10});
        Assertions.assertThrows(InvalidInputException.class,()->intervals.removeIntervals(include,exclude));

    }

    @Test
    @DisplayName("Merge overlapping intervals")
    public void testRemoveIntervals_NoneOverlapping_IncludeOverlapping(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{1,100});
        include.add(new Integer[]{25,50});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{200,300});

        List<Integer[]> result = intervals.removeIntervals(include,exclude);
        Assertions.assertEquals(1,result.size());
        Assertions.assertArrayEquals(new Integer[]{1,100},result.get(0));
    }

    @Test
    @DisplayName("Test to remove intervals - negative integers")
    public void testRemoveIntervals_NoneOverlapping_Negative(){
        List<Integer[]> include = new LinkedList<>();
        include.add(new Integer[]{-100,10});

        List<Integer[]> exclude = new ArrayList<>();
        exclude.add(new Integer[]{50,100});

        List<Integer[]> result = intervals.removeIntervals(include,exclude);
        Assertions.assertEquals(1,result.size());
        Assertions.assertArrayEquals(include.get(0),result.get(0));
    }

    @Test
    @DisplayName("Test to remove intervals - negative integers")
    public void testRemoveIntervals_Overlapping_Negative(){
        List<Integer[]> includeIntervals = new LinkedList<>();
        includeIntervals.add(new Integer[]{-100,100});

        List<Integer[]> excludeIntervals = new ArrayList<>();
        excludeIntervals.add(new Integer[]{50,100});

        List<Integer[]> expectedResult = new ArrayList<>();
        expectedResult.add(new Integer[]{-100,49});

        List<Integer[]> actualResult = intervals.removeIntervals(includeIntervals,excludeIntervals);
        Assertions.assertEquals(1, actualResult.size());
        Assertions.assertArrayEquals(expectedResult.get(0), actualResult.get(0));
    }

    @Test
    @DisplayName("Merge overlapping intervals")
    public void testMergeIntervals_Overlapping(){
        List<Integer[]> integerIntervals = new LinkedList<>();
        integerIntervals.add(new Integer[]{1,50});
        integerIntervals.add(new Integer[]{20,75});

        List<Integer[]> expectedResult = new ArrayList<>();
        expectedResult.add(new Integer[]{1,75});

        Integer[] actualResult = intervals.removeIntervals(
                integerIntervals.get(0),
                integerIntervals.get(1)
        );

        Assertions.assertArrayEquals(expectedResult.get(0), actualResult);
    }

    @Test
    @DisplayName("Merge overlapping intervals- change order")
    public void testMergeIntervals_Overlapping_OrderChange(){
        List<Integer[]> integerIntervals = new LinkedList<>();
        integerIntervals.add(new Integer[]{20,75});
        integerIntervals.add(new Integer[]{1,50});


        List<Integer[]> expectedResult = new ArrayList<>();
        expectedResult.add(new Integer[]{1,75});

        Integer[] actualResult = intervals.removeIntervals(
                integerIntervals.get(0),
                integerIntervals.get(1)
        );

        Assertions.assertArrayEquals(expectedResult.get(0), actualResult);
    }

    @Test
    @DisplayName("Merge overlapping intervals")
    public void testMergeIntervals_Overlapping_OneNumber(){
        List<Integer[]> integerIntervals = new LinkedList<>();
        integerIntervals.add(new Integer[]{1,50});
        integerIntervals.add(new Integer[]{50,100});

        List<Integer[]> expectedResult = new ArrayList<>();
        expectedResult.add(new Integer[]{1,100});

        Integer[] actualResult = intervals.removeIntervals(
                integerIntervals.get(0),
                integerIntervals.get(1)
        );

        Assertions.assertArrayEquals(expectedResult.get(0), actualResult);
    }

    @Test
    @DisplayName("Merge intervals - non overlapping")
    public void testMergeIntervals_NonOverlapping(){
        List<Integer[]> integerIntervals = new LinkedList<>();
        integerIntervals.add(new Integer[]{1,50});
        integerIntervals.add(new Integer[]{60,100});
        Integer[] actualResult = intervals.removeIntervals(
                integerIntervals.get(0),
                integerIntervals.get(1)
        );

        Assertions.assertNull(actualResult);
    }

    @Test
    @DisplayName("Merge intervals - non overlapping")
    public void testMergeIntervals_NonOverlapping_OrderChange(){
        List<Integer[]> integerIntervals = new LinkedList<>();
        integerIntervals.add(new Integer[]{60,100});
        integerIntervals.add(new Integer[]{1,50});
        Integer[] actualResult = intervals.removeIntervals(
                integerIntervals.get(0),
                integerIntervals.get(1)
        );

        Assertions.assertNull(actualResult);
    }

    @Test
    @DisplayName("Merge intervals - non overlapping")
    public void testMergeIntervals_NonOverlapping_CommonStart(){
        List<Integer[]> integerIntervals = new LinkedList<>();
        integerIntervals.add(new Integer[]{1,50});
        integerIntervals.add(new Integer[]{1,20});

        List<Integer[]> expectedResult = new ArrayList<>();
        expectedResult.add(new Integer[]{1,50});

        Integer[] actualResult = intervals.removeIntervals(
                integerIntervals.get(0),
                integerIntervals.get(1)
        );

        Assertions.assertArrayEquals(expectedResult.get(0), actualResult);
    }

    @Test
    @DisplayName("Test to validate input - invalid input provided")
    public void testIsInputValid(){
        List<Integer[]> integerIntervals = new LinkedList<>();
        integerIntervals.add(new Integer[]{60,100});
        integerIntervals.add(new Integer[]{1});

        Assertions.assertFalse(intervals.isInputValid(integerIntervals));
    }

    @Test
    @DisplayName("Test to validate input - valid input provided")
    public void testIsInputValid_ValidInput(){
        List<Integer[]> integerIntervals = new LinkedList<>();
        integerIntervals.add(new Integer[]{60,100});
        integerIntervals.add(new Integer[]{1,50});

        Assertions.assertTrue(intervals.isInputValid(integerIntervals));
    }

    @Test
    public void testMergeOverlappingIntervals(){
        List<Integer[]> integerIntervals = new LinkedList<>();
        integerIntervals.add(new Integer[]{25,75});
        integerIntervals.add(new Integer[]{1,50});
        integerIntervals.add(new Integer[]{100,150});

        List<Integer[]> expectedResult = new ArrayList<>();
        expectedResult.add(new Integer[]{1,75});
        expectedResult.add(new Integer[]{100,150});

        List<Integer[]> actualResult = intervals.removeIntervals(
                integerIntervals,
                List.of()
        );

        for(int index = 0; index < expectedResult.size();index++){
            Assertions.assertArrayEquals(expectedResult.get(index),actualResult.get(index));
        }
    }

    @Test
    public void testMergeOverlappingIntervals_AllOverLapping(){
        List<Integer[]> integerIntervals = new LinkedList<>();
        integerIntervals.add(new Integer[]{25,75});
        integerIntervals.add(new Integer[]{1,50});
        integerIntervals.add(new Integer[]{100,150});
        integerIntervals.add(new Integer[]{75,100});

        List<Integer[]> expectedResult = new ArrayList<>();
        expectedResult.add(new Integer[]{1,150});

        List<Integer[]> actualResult = intervals.removeIntervals(
                integerIntervals,
                List.of()
        );

        for(int index = 0; index < expectedResult.size();index++){
            Assertions.assertArrayEquals(expectedResult.get(index),actualResult.get(index));
        }
    }

}