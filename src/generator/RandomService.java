package generator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class RandomService {
    public static List<String> randomSymbolResults = new CopyOnWriteArrayList<>();

    public static String getRandomSymbol() {
        List<Thread> threads = new LinkedList<>();
        for (Integer i = 0; i < 10; i++) {
            OurThread t = new OurThread(i.toString());
            threads.add(t);
        }
        for(char i = 97; i <= 122; i++){
            threads.add(new OurThread(Character.toString(i)));
        }
        for (char i = 65; i <= 90; i++) {
            threads.add(new OurThread(Character.toString(i)));
        }

        threads.add(new OurThread("_"));

        for (Thread t : threads) {
            t.start();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = RandomService.randomSymbolResults.get(new Random().nextInt(threads.size() - 1));
        RandomService.randomSymbolResults.clear();
        return result;
    }
}
