package top.test.boot.task.task;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AsyncTaskTest {

    @Resource
    private AsyncTask asyncTask;

    @Test
    void testSyncTask() throws InterruptedException {
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
    }

}