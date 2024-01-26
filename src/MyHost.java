/* Implement this class. */

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class MyHost extends Host {

    PriorityBlockingQueue<Task> queue;
    public MyHost() {
        queue = new PriorityBlockingQueue(1, Comparator.comparing(Task::getPriority).reversed().thenComparing(Task::getStart));
    }

    Task currentTask = null;
    boolean isRunning = true;
    long lastTime = 0;
    long currentTime = 0;

    @Override
    public void run() {
        while (isRunning) {
            if (currentTask == null) {
                if (!queue.isEmpty())
                    currentTask = queue.poll();
            } else {
                lastTime = System.currentTimeMillis();

                while (currentTask.getLeft() > 0) {
                    if (currentTask.isPreemptible() && !queue.isEmpty()) {
                        Task nextTask = queue.poll();
                        if (nextTask.getPriority() > currentTask.getPriority()) {
                            queue.add(currentTask);
                            currentTask = nextTask;
                        } else {
                            queue.add(nextTask);
                        }
                    }

                    currentTime = System.currentTimeMillis();

                    currentTask.setLeft(currentTask.getLeft() - (currentTime - lastTime));

                    lastTime = currentTime;
                }

                if (currentTask != null) {
                    currentTask.finish();
                    currentTask = null;
                }

            }

        }
    }


    @Override
    public void addTask(Task task) {
        queue.add(task);
    }

    @Override
    public int getQueueSize() {
        return queue.size() + (currentTask == null ? 0 : 1);
    }

    @Override
    public long getWorkLeft() {
        long workLeft = 0;
        for (Task task : queue) {
            workLeft += task.getLeft();
        }

        if (currentTask != null) {
            workLeft += currentTask.getLeft();
        }

        return workLeft;
    }

    @Override
    public void shutdown() {
        isRunning = false;
    }
}
