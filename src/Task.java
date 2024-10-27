;
public class Task implements Comparable<Task> {
    private int id;
    private int time;
    private int priority;
    private int arrival;
    public Task(int jobId, int processTime)
    {
        id = jobId;
        time = processTime;
    }

    public Task(int jobId, int processTime, int priorityLevel)
    {
        id = jobId;
        time = processTime;
        priority = priorityLevel;
    }

    public Task(int jobId, int processTime, int priorityLevel, int arrivalTime)
    {
        id = jobId;
        time = processTime;
        priority = priorityLevel;
        arrival = arrivalTime;
    }

    public int getId()
        {return id;}

    public int getTime()
        {return time;}

    public int getPriority()
        {return priority;}

    public int getArrival()
        {return arrival;}

    public void setArrival(int arrivalTime)
    {
        arrival = arrivalTime;
    }
    public int compareTo(Task o)
    {
        int result = 0;

        if(this.time < o.time)
        {result = -1;}
        else if(this.time > o.time)
        {result = 1;}

        return result;
    }
}
