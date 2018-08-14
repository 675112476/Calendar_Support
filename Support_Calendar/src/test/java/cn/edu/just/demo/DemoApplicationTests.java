package cn.edu.just.demo;

import cn.edu.just.demo.dao.CalendarMapper;
import cn.edu.just.demo.dao.SchoolMapper;
import cn.edu.just.demo.dao.WorkerMapper;
import cn.edu.just.demo.model.Worker;
import cn.edu.just.demo.service.SchoolService;
import cn.edu.just.demo.service.impl.CalendarServiceImpl;
import cn.edu.just.demo.service.impl.CommonServiceImpl;
import cn.edu.just.demo.service.impl.OldstudentServiceImpl;
import cn.edu.just.demo.service.impl.SchoolServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    CommonServiceImpl commonService;
    @Autowired
    CalendarServiceImpl calendarService;
    @Autowired
    OldstudentServiceImpl oldstudentService;
    @Autowired
    CalendarMapper schoolMapper;
    @Test
    public void contextLoads() {

        //System.out.println(calendarService.get_calendar());
        //System.out.println(commonService.getDetails("2018-07-15"));
        //oldstudentService.getSchoolByDate("2018-09-02");
        //commonService.pre_Details("2018-08-29");
        System.out.println(schoolMapper.getsceneNameByDateAndSchool("2018-09-14","商校"));
    }

}
