import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AsyncSort {

    /**
     * @author hanulful
     *
     * RECOMMEND NOT TO USE THIS FUNCTION
     * This function is trash and it sucks
     * This function takes a long time
     * This function sorts the array and print elements line by line
     * This function could cause overhead problem when List is big
     * This function does NOT GUARANTEE sort when the size is big
     *
     * @param nums only Integer array
     */
    public static void integerSort(List<Integer> nums) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(nums.size());
        CompletableFuture<Void> sortCompletableFuture = null;
        for (Integer num : nums) {
            sortCompletableFuture = CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(num * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " : " + num);
            }, executorService);
        }

        sortCompletableFuture.get();
        executorService.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> nums = Stream.generate(() -> (int) (Math.random() * 100))
                .limit(10)
                .collect(Collectors.toList());

        System.out.println(nums);
        integerSort(nums);
    }
}
