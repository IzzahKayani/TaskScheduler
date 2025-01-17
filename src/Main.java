import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws FileNotFoundException
    {
        try {
            MinPQ<Task> pq = new MinPQ<>(1);
            PrintWriter writer = new PrintWriter("Output.txt");

            Scanner sc = new Scanner(new File("task1-input.txt"));
            sc.useDelimiter("\n");
            while (sc.hasNext()) {
                String[] tokens = sc.next().split(" ");
                Task task = new Task(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                pq.insert(task);
            }
            sc.close();

            double averageTime = 0;
            int length = pq.size();
            System.out.print("\nTask 1 Output:\nExecution order:[");
            writer.print("\nTask 1 Output:\nExecution order:[");
            while (!pq.isEmpty()) {
                averageTime += pq.min().getTime() * pq.size();
                writer.print(pq.min().getId());
                System.out.print(pq.delMin().getId());
                if (!pq.isEmpty()) {
                    System.out.print(",");
                    writer.print(",");
                }
            }

            System.out.println("]\nAverage completion time: " + averageTime / length);
            writer.println("]\nAverage completion time: " + averageTime / length);

            Scanner sc2 = new Scanner(new File("task2-input.txt"));
            sc2.useDelimiter("\n");
            Task[] tasks = new Task[100];
            int n = 0;
            while (sc2.hasNext()) {
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

            System.out.print("\nTask 2 Output:\nExecution order:[");
            writer.print("\nTask 2 Output:\nExecution order:[");
            while (n > 0) {
                for (int i = 0; i < length; i++) {
                    if (tasks[i].getPriority() == priority) {
                        pq.insert(tasks[i]);
                        n--;
                    }
                }
                while (!pq.isEmpty()) {
                    averageTime += pq.min().getTime() * size;
                    writer.print(pq.min().getId());
                    System.out.print(pq.delMin().getId());
                    if (n != 0 || !pq.isEmpty()) {
                        System.out.print(",");
                        writer.print(",");
                    }
                    size--;
                }
                priority++;
            }
            System.out.println("]");
            System.out.println("Average completion time: " + averageTime / length);
            writer.println("]\nAverage completion time: " + averageTime / length);

            Scanner sc3 = new Scanner(new File("task3-input.txt"));
            sc3.useDelimiter("\n");
            n = 0;

            tasks = new Task[100];
            while (sc3.hasNext()) {
                String[] tokens = sc3.next().split(" ");
                Task task = new Task(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), 1, Integer.parseInt(tokens[2]));
                tasks[n] = task;
                n++;
            }
            sc3.close();

            averageTime = 0;
            length = tasks.length;
            size = tasks.length;
            int timePassed = 0;

            System.out.print("\nTask 3 Output:\nExecution order:[");
            writer.print("\nTask 3 Output:\nExecution order:[");
            while (n > 0) {
                for (int i = 0; i < length; i++) {
                    if (tasks[i].getArrival() <= timePassed && tasks[i].getArrival() >= 0) {
                        pq.insert(tasks[i]);
                        tasks[i].setArrival(-1);
                    }
                }
                averageTime += pq.min().getTime() * size;
                timePassed += pq.min().getTime();
                writer.print(pq.min().getId());
                System.out.print(pq.delMin().getId());
                size--;
                n--;
                if (n != 0 || !pq.isEmpty())
                {
                    System.out.print(",");
                    writer.print(",");
                }
            }
            System.out.println("]");
            System.out.println("Average completion time: " + averageTime / length);
            writer.println("]\nAverage completion time: " + averageTime / length);
            writer.close();
        }
        catch(IOException e)
        {System.err.println("An error occurred: ");}
    }
}