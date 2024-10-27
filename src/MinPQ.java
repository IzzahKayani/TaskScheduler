import java.util.Comparator;

public class Schedule<Key extends Comparable<Key>>
{
    private Key[] pq;
    private int n;
    private int time;
    private int id;
    private Comparator<Key> comparator;

    public Schedule()
    {
        pq = (Key[]) new Object[1];
        n = 0;
    }

    public void job(int idNum, int time)
    {
        id = idNum;
        this.time = time;
    }

    public int getTime()
    {return time;}

    public boolean isEmpty()
    {return n == 0;}

    private boolean less(int i,int j)
    {return pq[i].compareTo(pq[j]) < 0;}

    private void exch(int i,int j)
    {Key temp = pq[i]; pq[i] = pq[j]; pq[j] = temp;}

    public int size()
    {return n;}

    public void insert(Key x)
    {
        if (n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = x;
        swim(n);
    }

    private void resize(int capacity)
    {
        Key[] temp = (Key[]) new Object[capacity];
        for(int i = 1; i <= n; i++)
        {temp[i] = pq[i];}

        pq = temp;
    }

    public Key delMin()
    {
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;
        return min;
    }

    private void swim(int k)
    {
        while(k > 1 && less(k/2, k))
        {
            exch(k, k/2);
            k = k/2;
        }
    }
    private void sink(int k)
    {
        while (2*k <= n)
        {
            int j = 2*k;
            if(j < n && less(j,j + 1))
            {j++;}

            if(!less(k, j))
            {break;}

            exch(k, j);
            k = j;

        }
    }
}
