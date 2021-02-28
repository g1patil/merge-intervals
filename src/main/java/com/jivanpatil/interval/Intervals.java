package com.jivanpatil.interval;

import com.jivanpatil.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author jivanpatil
 * Class to perform interval operations.
 * */
public class Intervals {

    private static final int START_INDEX = 0;
    private static final int END_INDEX = 1;
    private static final String INVALID_INPUT_ERROR = "Invalid input provided";

    /**
     * Removes the intervals from the given list of intervals. This is the core driver function which
     * traverses over the include intervals and removes the exclude from it. Function keeps adding new intervals to existing
     * list as we proceed.
     * @param includeIntervals List of integer intervals. From this interval, other one will be removed.
     * @param excludeIntervals List of intervals which are needs to be removed.
     * @return List of intervals, after removing the excludeIntervals intervals from includeIntervals.
     * If the resultant list has the overlapping intervals , then combines/merges them.
     * */
    public List<Integer[]> mergeIntervals(List<Integer[]> includeIntervals, List<Integer[]> excludeIntervals){
        int includeFlag = START_INDEX;
        int excludeFlag = START_INDEX;

        if(!isInputValid(includeIntervals) || !isInputValid(excludeIntervals)){
            throw new InvalidInputException(INVALID_INPUT_ERROR);
        }

        /* sort the input parameters */
        if( !includeIntervals.isEmpty()) { includeIntervals.sort(Comparator.comparingInt(o -> o[START_INDEX])); }
        if( !excludeIntervals.isEmpty()) { excludeIntervals.sort(Comparator.comparingInt(o -> o[START_INDEX])); }

        mergeOverlappingIntervals(includeIntervals);

        while ( !includeIntervals.isEmpty() && !excludeIntervals.isEmpty() && includeFlag < includeIntervals.size()){
            List<Integer[]> newIntervals = removeOneInterval( includeIntervals.get(includeFlag), excludeIntervals.get(excludeFlag));

            if( canIntervalBeRemoved(includeIntervals.get(includeFlag), excludeIntervals.get(excludeFlag)) || newIntervals.isEmpty()){
                excludeIntervals.remove(excludeFlag);
            }


            /* Remove previous interval and add new ones at same position*/
            includeIntervals.remove(includeFlag);
            includeIntervals.addAll(includeFlag, newIntervals);

            if(!newIntervals.isEmpty()){includeFlag++;}
        }
        return includeIntervals;
    }

    /**
     * Checks whether the second interval can be further used for exclusion operation or not.
     * Ex.------------         -------------
     *      -----         --------
     * second interval has no loose ends after exclusion. If there is loose end then it can be used by
     * successive or next interval for exclusion operation.
     * @param intervalOne first integer interval
     * @param intervalTwo second integer interval
     * @return true , if first interval completely covers the second interval
     * or first interval contains the start of the second interval
     * false , if second interval starts before first one
     * */
    public boolean canIntervalBeRemoved(final Integer[] intervalOne, final Integer[] intervalTwo){
        int oneStart = intervalOne[START_INDEX], oneEnd = intervalOne[END_INDEX];
        int twoStart = intervalTwo[START_INDEX], twoEnd = intervalTwo[END_INDEX];

        return (oneStart < twoStart && oneEnd > twoEnd) ||
                (oneStart < twoEnd && twoEnd < oneEnd);

    }

    /**
     * Merges the two intervals if they intersect or overlap.
     * @param firstInterval first integer interval
     * @param secondInterval second integer interval
     * @return combined interval of first and second , if two intersect/overlap, NULL otherwise
     * */
    public Integer[] mergeIntervals(final Integer[] firstInterval, final Integer[] secondInterval){
        int firstStart = firstInterval[START_INDEX], firstEnd = firstInterval[END_INDEX];
        int secondStart = secondInterval[START_INDEX],secondEnd = secondInterval[END_INDEX];

        if( doIntervalIntersect(firstInterval,secondInterval) ){
            return new Integer[]{Math.min(firstStart,secondStart) , Math.max(firstEnd,secondEnd)};
        }
        return null;
    }

    /**
     * Given two intervals, includeInterval and excludeInterval, builds the new intervals from them.
     * Removes the intervals excludeInterval from includeInterval and builds new set of intervals
     * @param includeInterval the intervals from which the excludeInterval with be removed.
     * @param excludeInterval the intervals which are needs to be removed
     * @return includeInterval interval with excludeInterval interval removed from it.
     * */
    private List<Integer[]> removeOneInterval(final Integer[] includeInterval, final Integer[] excludeInterval){
        List<Integer[]> result = new ArrayList<>();

        int includeStart = includeInterval[START_INDEX], includeEnd = includeInterval[END_INDEX];
        int excludeStart = excludeInterval[START_INDEX],excludeEnd = excludeInterval[END_INDEX];

        if(!doIntervalIntersect(includeInterval, excludeInterval)){result.add(includeInterval);return result;}

        if(includeStart == excludeStart && includeEnd == excludeEnd){return List.of();}

        if( includeStart <= excludeStart){

            if(includeStart != excludeStart){
                Integer[] first = {includeStart,excludeStart-1};
                result.add(first);
            }

            if( excludeEnd < includeEnd){
                Integer[] second = {excludeEnd+1,includeEnd};
                result.add(second);
            }
            return result;
        }

        if (excludeStart < includeStart){
            Integer[] first = {excludeEnd+1,includeEnd};
            result.add(first);
        }

        return result;
    }

    /**
     * Merges the overlapping intervals in the given list of intervals.
     * @param intervalList list of integer intervals
     * */
    private void mergeOverlappingIntervals(List<Integer[]> intervalList){
        int flagIndex = START_INDEX;

        while ( flagIndex < intervalList.size()-1 ){
            Integer[] mergedInterval = mergeIntervals(intervalList.get(flagIndex),intervalList.get(flagIndex+1));
            if(mergedInterval != null){
                intervalList.remove(flagIndex);
                intervalList.remove(flagIndex);
                intervalList.add(flagIndex, mergedInterval);
            } else {
                flagIndex++;
            }
        }
    }

    /**
     * Validates if the provided input is valid or not. Each interval should have beginning and ending integer.
     * @param interval array of integers
     * @return true, if there is at least one interval with only one element in it.
     * */
    public boolean isInputValid(final List<Integer[]> interval){
        return ! ( interval.stream().filter(i -> i.length == 1).count() >= 1 );
    }

    /**
     * Checks if the provided two intervals are completely non-intersecting or non-overlapping
     * @param intervalOne integer interval one
     * @param intervalTwo integer interval
     * @return true, if two interval intersect , false otherwise
     * */
    public boolean doIntervalIntersect(final Integer[] intervalOne, final Integer[] intervalTwo){
        return !(intervalOne[END_INDEX] <  intervalTwo[START_INDEX]  ||
                intervalTwo[END_INDEX] < intervalOne[START_INDEX] );
    }

}



