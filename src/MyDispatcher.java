/* Implement this class. */

import java.util.List;

public class MyDispatcher extends Dispatcher {

    public MyDispatcher(SchedulingAlgorithm algorithm, List<Host> hosts) {
        super(algorithm, hosts);
    }

    int lastHost = 0;

    @Override
    public void addTask(Task task) {
        switch (algorithm) {
            case ROUND_ROBIN:

                hosts.get((lastHost + 1) % hosts.size()).addTask(task);
                lastHost++;

                break;
            case SHORTEST_QUEUE:

                int min = Integer.MAX_VALUE;
                int minIndex = 0;
                for (int i = 0; i < hosts.size(); i++) {
                    if (hosts.get(i).getQueueSize() < min) {
                        min = hosts.get(i).getQueueSize();
                        minIndex = i;
                    }
                }
                hosts.get(minIndex).addTask(task);

                break;
            case SIZE_INTERVAL_TASK_ASSIGNMENT:

                hosts.get(task.getType().ordinal()).addTask(task);

                break;
            case LEAST_WORK_LEFT:

                long minWork = Long.MAX_VALUE;
                int minWorkIndex = 0;
                for (int i = 0; i < hosts.size(); i++) {
                    if (hosts.get(i).getWorkLeft() < minWork) {
                        minWork = hosts.get(i).getWorkLeft();
                        minWorkIndex = i;
                    }
                }
                hosts.get(minWorkIndex).addTask(task);

                break;
            default:
                break;
        }
    }
}