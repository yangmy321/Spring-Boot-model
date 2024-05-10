package top.test.boot.task.job;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import jakarta.annotation.Resource;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import top.test.boot.task.entity.CronTriggers;
import top.test.boot.task.service.DataBaseService;

import java.util.Date;
import java.util.List;

/**
 * @author 34746
 */
public class QuartzSimpleTask extends QuartzJobBean {
    @Resource
    DataBaseService dataBaseService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<CronTriggers> all = dataBaseService.selAllData();//读取数据

        // 创建Excel写入器
        ExcelWriter writer = ExcelUtil.getWriter("D:\\ideaCode\\spring-boot-learning\\spring-boot-task\\src\\main\\resources\\output.xlsx");


        writer.autoSizeColumnAll();  // 自适应单元格大小

        // 写入表头
        writer.addHeaderAlias("schedName", "SCHED_NAME");
        writer.addHeaderAlias("triggerName", "TRIGGER_NAME");
        writer.addHeaderAlias("triggerGroup", "TRIGGER_GROUP");
        writer.addHeaderAlias("cronExpression", "CRON_EXPRESSION");
        writer.addHeaderAlias("timeZoneId", "TIME_ZONE_ID");

        // 写入数据
        writer.write(all);

        // 关闭写入器
        writer.close();

        System.out.println("quartz从数据库表中查询最新的数据，通过 excel 生成报表到本地：" + new Date());
    }

}