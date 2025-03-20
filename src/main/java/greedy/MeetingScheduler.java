package greedy

  /*
Problem Statement: There is one meeting room in a firm. You are given two arrays, start and end each of size N.For an index ‘i’, start[i] denotes the starting time of the ith meeting while end[i]  will denote the ending time of the ith meeting. Find the maximum number of meetings that can be accommodated if only one meeting can happen in the room at a  particular time. Print the order in which these meetings will be performed.

Example:

Input:  N = 6,  start[] = {1,3,0,5,8,5}, end[] =  {2,4,5,7,9,9}

Output: 1 2 4 5

  */
  
import java.util.*;

class MeetingSlot {
    int start, end, pos;

    MeetingSlot(int start, int end, int pos) {
        this.start = start;
        this.end = end;
        this.pos = pos;
    }
}

public class MeetingScheduler {
    public static void maxMeetings(int[] start, int[] end) {
        int n = start.length;
        List<MeetingSlot> meetings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            meetings.add(new MeetingSlot(start[i], end[i], i + 1));
        }

        // Sorting based on end time, and position in case of ties
        meetings.sort(Comparator.comparingInt((MeetingSlot m) -> m.end)
                .thenComparingInt(m -> m.pos));

        List<Integer> schedule = new ArrayList<>();
        int lastEndTime = meetings.get(0).end;
        schedule.add(meetings.get(0).pos);

        for (int i = 1; i < n; i++) {
            if (meetings.get(i).start > lastEndTime) {
                lastEndTime = meetings.get(i).end;
                schedule.add(meetings.get(i).pos);
            }
        }

        System.out.println("Meeting order: " + schedule);
    }

    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 5, 7, 9, 9};
        maxMeetings(start, end);
    }
}
