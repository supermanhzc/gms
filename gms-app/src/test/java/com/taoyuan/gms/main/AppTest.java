package com.taoyuan.gms.main;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.GmsApplication;
import com.taoyuan.gms.core.adminmanage.service.IGameSettingService;
import com.taoyuan.gms.core.adminmanage.service.ILevelSettingService;
import com.taoyuan.gms.model.entity.admin.web.GameSettingEntity;
import com.taoyuan.gms.model.entity.admin.web.LevelSettingEntity;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GmsApplication.class)
@WebAppConfiguration
@Slf4j
public class AppTest extends TestCase {

    @Autowired
    private IGameSettingService gameSettingService;

    @Autowired
    private ILevelSettingService levelSettingService;

    public AppTest(){}

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
//    public AppTest(String testName) {
//        super(testName);
//    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }


    @org.junit.Test
    public void testUpdateGameSetting() {
//        List<GameSettingEntity> games = new ArrayList<>();
//        for (int i = 1; i < 10; i++) {
//            GameSettingEntity entity = new GameSettingEntity();
//            entity.setGameName("游戏" + i);
//            games.add(entity);
//        }
//        gameSettingService.saveOrUpdateBatch(games);
    }

    @org.junit.Test
    public void testGteGameSetting() {
//        Map<String, Object> map = gameSettingService.getMap(new QueryWrapper<GameSettingEntity>());
//        Assert.assertNotNull(map);
//        log.info(String.valueOf(map));
    }

    @org.junit.Test
    public void testLevelSetting(){
        List<LevelSettingEntity> levelSettingEntities = new ArrayList<LevelSettingEntity>();
        for(int i =0;i<8;i++){
            String name = "vip" +i;
            LevelSettingEntity entity = new LevelSettingEntity();
            entity.setId(i);
            entity.setName(name);
            entity.setExperience(1000*(i+1));
            entity.setDrawCondition(100);
            entity.setDrawTimes(10);
            entity.setRechargeCommission(2);
            entity.setRelieveGoldenCoin(50);
            entity.setCashPrizeDiscount(100+i);
            levelSettingEntities.add(entity);
        }
        levelSettingService.saveOrUpdateBatch(levelSettingEntities);
    }
}
