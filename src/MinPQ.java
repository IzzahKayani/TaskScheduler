import java.util.Comparator;

public class MinPQ<Key extends Comparable<Key>>
{
    private Key[] pq;
    private int n;

    public MinPQ(int capacity)
    {
        pq = (Key[]) new Comparable[capacity + 1];
        n = 0;
    }

    public Key min()
    {return pq[1];}

    public int size()
    {return n;}

    public boolean isEmpty()
    {return n == 0;}

    private boolean greater(int i,int j)
    {return pq[i].compareTo(pq[j]) > 0;}

    private void exch(int i,int j)
    {Key temp = pq[i]; pq[i] = pq[j]; pq[j] = temp;}

    public void insert(Key x)
    {
        if (n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = x;
        swim(n);
    }

    private void resize(int capacity)
    {
        Key[] temp = (Key[]) new Comparable[capacity];
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
        while(k > 1 && greater(k/2, k))
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
            if(j < n && greater(j,j + 1))
            {j++;}

            if(!greater(k, j))
            {break;}

            exch(k, j);
            k = j;

        }
    }
}
