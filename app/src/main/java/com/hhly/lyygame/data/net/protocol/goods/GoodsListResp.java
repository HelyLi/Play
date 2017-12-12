package com.hhly.lyygame.data.net.protocol.goods;

import com.hhly.lyygame.data.model.GoodsInfo;
import com.hhly.lyygame.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by ${HELY} on 17/2/5.
 * 邮箱：heli.lixiong@gmail.com
 */

public class GoodsListResp extends BaseResp {

    /**
     * msg : null
     * goods : [{"id":1101,"name":"天猫1000积分兑换券","type":1,"picUrl":"http://public.13322.com/5445f82f.jpg","needScoreId":2,"needScore":1,"total":100,"enable":0,"price":1000,"country":0,"position":2,"createTime":1483948543000,"updateTime":null,"platform":4},{"id":1103,"name":"侏罗纪公园电影票","type":1,"picUrl":"http://public.13322.com/328b3984.jpg","needScoreId":2,"needScore":1,"total":99,"enable":0,"price":35,"country":0,"position":4,"createTime":1483953654000,"updateTime":null,"platform":4},{"id":1105,"name":"扫地机器人","type":1,"picUrl":"http://public.13322.com/2956b5d8.jpg","needScoreId":2,"needScore":1,"total":91,"enable":0,"price":1000,"country":0,"position":6,"createTime":1483954535000,"updateTime":null,"platform":4},{"id":1002,"name":"联想(Lenovo)拯救者 ISK15.6英寸游戏本","type":1,"picUrl":"http://public.13322.com/5c1fd6b.jpg","needScoreId":3,"needScore":4949100,"total":99,"enable":0,"price":5499,"country":0,"position":1,"createTime":1481021001000,"updateTime":1482114353000,"platform":4},{"id":1035,"name":"iPad Air 2 金色 128G WLAN版","type":1,"picUrl":"http://public.13322.com/5e0d46bf.jpg","needScoreId":3,"needScore":3319200,"total":399,"enable":0,"price":3688,"country":0,"position":2,"createTime":1481270132000,"updateTime":1482114489000,"platform":4},{"id":1036,"name":"京东卡500元","type":1,"picUrl":"http://public.13322.com/1ea73074.jpg","needScoreId":3,"needScore":30,"total":699,"enable":0,"price":500,"country":0,"position":3,"createTime":1481270256000,"updateTime":1482225210000,"platform":4},{"id":1020,"name":"狼蛛F2008混光跑马无冲104键游戏键盘","type":1,"picUrl":"http://public.13322.com/2f295527.jpg","needScoreId":3,"needScore":197100,"total":699,"enable":0,"price":219,"country":0,"position":4,"createTime":1481192219000,"updateTime":1482114587000,"platform":4},{"id":1022,"name":"10 Q币","type":1,"picUrl":"http://public.13322.com/24b14c0e.jpg","needScoreId":3,"needScore":9000,"total":6666,"enable":0,"price":10,"country":0,"position":6,"createTime":1481192429000,"updateTime":1482114694000,"platform":4},{"id":1041,"name":"奥斯卡金像奖","type":1,"picUrl":"http://public.13322.com/775b06c0.jpg","needScoreId":3,"needScore":5,"total":10000,"enable":0,"price":8888,"country":0,"position":7,"createTime":1481600757000,"updateTime":1482459857000,"platform":4},{"id":1040,"name":"兰博基尼","type":1,"picUrl":"http://public.13322.com/791fea79.jpg","needScoreId":3,"needScore":1,"total":1000,"enable":0,"price":2000000,"country":0,"position":9,"createTime":1481535283000,"updateTime":null,"platform":4},{"id":1024,"name":"滴滴滴战靴","type":1,"picUrl":"http://public.13322.com/33b13e71.jpg","needScoreId":3,"needScore":10,"total":93,"enable":0,"price":500,"country":0,"position":10,"createTime":1481197314000,"updateTime":1481879948000,"platform":4}]
     * total : 11
     */

    private int total;
    private List<GoodsInfo> goods;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<GoodsInfo> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsInfo> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "GoodsListResp{" +
                "total=" + total +
                ", goods=" + goods +
                '}';
    }
}
