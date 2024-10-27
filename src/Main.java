import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws FileNotFoundException
    {
        MinPQ<Task> pq = new MinPQ<>(1);

        Scanner sc = new Scanner(new File("task1-input.txt"));
        sc.useDelimiter("\n");
        while(sc.hasNext())
        {
            String[] tokens = sc.next().split(" ");
            Task task = new Task(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
            pq.insert(task);
        }
        sc.close();

        double averageTime = 0;
        int length = pq.size();
        System.out.print("Execution order:[");
        while(!pq.isEmpty())
        {
            averageTime += pq.min().getTime()*pq.size();
            System.out.print(pq.delMin().getId());
            if(!pq.isEmpty())
            {System.out.print(",");}
        }
        System.out.println("]");

        System.out.println("Average completion time: " + averageTime / length);

        Scanner sc2 = new Scanner(new File("task2-input.txt"));
        sc2.useDelimiter("\n");
        Task[] tasks = new Task[100];
        int n = 0;
        while(sc2.hasNext())
        {
            String[] tokens = sc2.next().split(" ");
            Task task = new Task(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
            tasks[n] = task;
            n++;
        }
        sc2.close();

        averageTime = 0;
        length = tasks.length;
        int size = tasks.length;
        int priority = 1;

        System.out.print("\nExecution order:[");
        while(n > 0)
        {
            for(int i = 0; i < length; i++)
            {
                if(tasks[i].getPriority() == priority)
                {pq.insert(tasks[i]); n--;}
            }
            while(!pq.isEmpty())
            {
                averageTime += pq.min().getTime() * size;
                System.out.print(pq.delMin().getId());
                if (n != 0 || !pq.isEmpty())
                {System.out.print(",");}
                size--;
            }
            priority++;
        }
        System.out.println("]");
        System.out.println("Average completion time: " + averageTime / length);

        Scanner sc3 = new Scanner(new File("task3-input.txt"));
        sc3.useDelimiter("\n");
        n = 0;

        tasks = new Task[100];
        while(sc3.hasNext())
        {
            String[] tokens = sc3.next().split(" ");
            Task task = new Task(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),1,Integer.parseInt(tokens[2]));
            tasks[n] = task;
            n++;
        }
        sc3.close();

        averageTime = 0;
        length = tasks.length;
        size = tasks.length;
        int timePassed = 0;

        System.out.print("\nExecution order:[");
        while(n > 0)
        {
            for(int i = 0; i < length; i++)
            {
                if(tasks[i].getArrival() <= timePassed && tasks[i].getArrival()>= 0)
                {
                    pq.insert(tasks[i]);
                    tasks[i].setArrival(-1);
                }
            }
                averageTime += pq.min().getTime() * size;
                timePassed += pq.min().getTime();
                System.out.print(pq.delMin().getId());
                size--;
                n--;
                if (n != 0 || !pq.isEmpty())
                {System.out.print(",");}
        }
        System.out.println("]");
        System.out.println("Average completion time: " + averageTime / length);
    }
}