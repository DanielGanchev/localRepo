package solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CookiesProblem {

    Queue<Integer> queue = new PriorityQueue<>();
    public Integer solve(int k, int[] cookies) {
        for (int cookie : cookies) {
            queue.add(cookie);
        }

        int count = 0;
        while (queue.size() > 1 && queue.peek() < k) {
            int first = queue.poll();
            int second = queue.poll();

            int newCookie = first + 2 * second;
            queue.add(newCookie);
            count++;
        }

        if (queue.peek() < k) {
            return -1;
        }

        return count;
    }
}
