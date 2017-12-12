package com.hhly.lyygame.presentation.view.gamehall.category;

/**
 * Created by ${HELY} on 17/2/25.
 * 邮箱：heli.lixiong@gmail.com
 */

public enum GameCategory {

    HOT(-1),//热门游戏
    QUA(-1),//精品游戏
    ONLY(8022),//独家
    REC(8007),//推荐
    OFFLINE(8009),//单机
    ONLINE(8008),//网游
    ROLE(8005),//角色扮演
    SHOOT(8006),//射击游戏
    ARPG(8002),//即时战斗
    ADVE(8012),//冒险游戏
    SLG(8001),//策略游戏
    CHESS(8003),//旗类游戏
    RAC(8013),//竞速游戏
    ACTION(8014),//动作游戏
    SIM(8015),//模拟经营
    LVG(8016),//养成游戏
    FLY(8017),//飞行游戏
    SPORT(8004),//体育游戏
    FIGHT(8018),//格斗游戏
    PUZ(8019),//益智游戏
    FILLER(8020),//对战游戏
    OTHER(8021);//其他

    private int value = 0;

    private GameCategory(int value){
        this.value = value;
    }

    public int value(){
        return value;
    }

}
