/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.awt.Rectangle;
import java.io.File;
import pkg2dgamesframework.Objects;
import pkg2dgamesframework.SoundPlayer;

/**
 *
 * @author Sang
 */
public class Bird extends Objects{
    
    private float vt = 0;
    private boolean isFlying = false;
    
    private Rectangle rect;
    
    private boolean isLive = true;
    
    public SoundPlayer flapSound, bupSound, getMoneySound;
    
    public Bird(int x, int y, int w, int h){
        super(x,y,w,h);
        rect = new Rectangle(x,y,w,h);
    }
    
    public void setLive(boolean b){
        isLive = b;
        
        
        
        bupSound = new SoundPlayer(new File("Assets/bup.wav"));
        flapSound = new SoundPlayer(new File("Assets/flap.wav"));
        getMoneySound = new SoundPlayer(new File("Assets/getmoney.wav"));
                
    }
    
    public boolean getLive(){
        return isLive;
    }
    
    public Rectangle getRect(){
        return rect;
    }
    
    public void setVt( float vt){
    this.vt = vt;
    }
    
    public void update(long deltaTime){
    
        vt+= Flappybird.g;
        this.setPosY(this.getPosY()+vt);
        this.rect.setLocation((int)this.getPosX(),(int)this.getPosY());
        if(vt<0)
            isFlying = true  ;
        else
            isFlying = false;
        
    }
    
    public void fly(){
    vt = -3;
    flapSound.play();
    
    }
    
    public boolean getIsFlying(){
        return isFlying;
    }
    
}
