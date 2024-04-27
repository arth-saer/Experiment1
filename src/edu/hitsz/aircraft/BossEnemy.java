package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.BossBullet;
import edu.hitsz.prop.*;
import edu.hitsz.propfactory.*;
import edu.hitsz.trajectory.Annular;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends EnemyAircraft{

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = 20;
        this.power = 75;
        this.trajectory = new Annular();
    }
    @Override
    public int getSCORE() {
        return 200;
    }
    @Override
    public List<BaseProp> dropProp(){
        int i, j;
        List<BaseProp> res = new LinkedList<>();

        PropFactory propFactory;
        for(j = -1; j < 2; j++)
        {
            i = (int)(Math.random()*100);
            if(i%6==0){
                propFactory = new AddHpPropFactory();
                res.add(propFactory.createProp(this.getLocationX() + 40 * j,this.getLocationY(),0,3));
            }
            else if(i%6==1){
                propFactory = new BombPropFactory();
                res.add(propFactory.createProp(this.getLocationX() + 40 * j,this.getLocationY(),0,3));
            }
            else if(i%6==2) {
                propFactory = new FirePropFactory();
                res.add(propFactory.createProp(this.getLocationX() + 40 * j,this.getLocationY(),0,3));
            }
            else if(i%6==3) {
                propFactory = new FirePlusPropFactory();
                res.add(propFactory.createProp(this.getLocationX() + 40 * j,this.getLocationY(),0,3));
            }
        }

        return res;
    }
    @Override
    public List<BaseBullet> shoot() {
        return this.getBullets(this, BossBullet.class);
    }

}
