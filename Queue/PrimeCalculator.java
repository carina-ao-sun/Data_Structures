public class PrimeCalculator<E> extends ArrayQueue<E>{

    public ArrayQueue<Integer> primes = new ArrayQueue<>();

// Method
    public void primesTo(int n){
    if (n<2) throw new IllegalStateException("Error: Input must be a number greater than or equal to 2.");
    ArrayQueue<Integer> numbers = new ArrayQueue<>(n-1);

    for (int i = 2; i <= n; i++) {
        numbers.enqueue(i);
    }
    

    //remove all elements of numbers that are divisible by p;
    while (!numbers.isEmpty()){
        int p = numbers.dequeue();
        primes.enqueue(p);

        int size = numbers.size();
        for (int j = 0; j < size; j++) {
            int current = numbers.dequeue();
            if (current % p != 0) {
                numbers.enqueue(current);
            }
        }
    }

    System.out.println("Printing primes up to " + n + ": ");
    while (!primes.isEmpty()) {
        System.out.print(primes.dequeue());
        if (!primes.isEmpty()) {
            System.out.print(", ");
        }
        }
        System.out.println(".");
    }



}

