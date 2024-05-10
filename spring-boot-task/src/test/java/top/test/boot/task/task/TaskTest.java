package top.test.boot.task.task;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskTest {

    @Resource
    private SyncTask syncTask;

    @Test
    void testSyncTask() throws InterruptedException {
        syncTask.doTaskOne();
        syncTask.doTaskTwo();
        syncTask.doTaskThree();
    }
}