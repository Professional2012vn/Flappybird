/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;
import java.util.Scanner;
import java.sql.*;
import java.sql.Statement;
import java.io.*;

/**
 *
 * @author Sang
 */
public class Flappybird extends GameScreen{
    
    private BufferedImage bird,gameover, getready, batdau;
    private Animation bird_anim;
    
    
    
    public static float g = 0.1f;
    
    private Bird birds;
    private Ground ground;
    private ChimneyGroup chimneyGroup;
    private listScore score;
    private String s;
    private Insertname name;
    
    
    
    
    public int Point = 0;
    
    private int BEGIN_SCREEN = 0;
    private int GAMEPLAY_SCREEN =1;
    private int PAUSE_SCREEN=3;
    private int GAME_OVER=2;
    
    private int CurrentScreen = BEGIN_SCREEN;
    
    
    

    public Flappybird(){
    
    super(800,600);
    
        
        try {
            bird = ImageIO.read(new File("Assets/bird_sprite.png"));
            
        } catch (IOException ex) {}
    
        bird_anim = new Animation(70);
        AFrameOnImage f;
        f = new AFrameOnImage(0,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(120,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60,0,60,60);
        bird_anim.AddFrame(f);
        
        birds = new Bird(350,250,50,50);
        
        ground = new Ground();
        
        score = new listScore();
        
        name = new Insertname();
        name.setVisible(true);
        chimneyGroup = new ChimneyGroup();
        
        
        BeginGame();
        
        //System.out.print("Insert your name : "); //Lenh in ra man hinh
        //Scanner nhap = new Scanner(System.in);
        //s = nhap.nextLine(); //nhap chuoi
        
    }
    
    public static void main(String[] args) {
        
        new Flappybird();
        
        
    }
    
    
    
    private void resetGame(){
            
    birds.setPos(350, 250);
    birds.setVt(0);
    birds.setLive(true);
    Point =0;
    chimneyGroup.resetChimneys();
        
    }

    @Override
    public void GAME_UPDATE(long deltaTime) {
        if(CurrentScreen == BEGIN_SCREEN){
            resetGame();
        } else if(CurrentScreen ==GAMEPLAY_SCREEN){
            if(birds.getLive())
            bird_anim.Update_Me(deltaTime);
            birds.update(deltaTime);
            ground.Update();
            
            chimneyGroup.update();
            
            for(int i = 0;i<ChimneyGroup.SIZE;i++){
                if(birds.getRect().intersects(chimneyGroup.getChimney(i).getRect())){
                    if(birds.getLive()) birds.bupSound.play();
                    birds.setLive(false);
                
                    
                }
                    
            }
            
            for(int i = 0;i<ChimneyGroup.SIZE;i++){
                if(birds.getPosX()> chimneyGroup.getChimney(i).getPosX()&&!chimneyGroup.getChimney(i).getIsBehindBird()&&i%2==0){
                    Point ++;
                    birds.getMoneySound.play();
                    chimneyGroup.getChimney(i).setIsBehindBird(true);
                }
            }
            
            if(birds.getPosY()+birds.getH()>ground.getYGround()){
            CurrentScreen = GAME_OVER;
            }
        } else if(CurrentScreen == PAUSE_SCREEN){
        
        } else{
            
            s = name.getname();
            score.saveScore(s, Point);
        }
        
        
    
    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
        
        
        g2.setColor(Color.decode("#b8daef"));
        g2.fillRect(0, 0, MASTER_WIDTH, MASTER_HEIGHT);
        
        chimneyGroup.paint(g2);
        
        ground.Paint(g2);
        
        
        
        
        if(birds.getIsFlying())
            bird_anim.PaintAnims( (int)birds.getPosX(),(int)birds.getPosY(), bird, g2, 0, -1);  
        else
            bird_anim.PaintAnims( (int)birds.getPosX(),(int)birds.getPosY(), bird, g2, 0, 0);
        
        
        if(CurrentScreen == BEGIN_SCREEN){
            try {
                batdau =ImageIO.read(new File("Assets/Flappybird.png"));
                g2.drawImage(batdau, 150, 150,null);
                
                //  g2.setColor(Color.red);
                //  g2.drawString("moi ban an phim de bat dau lai", 200, 300); 
            } catch (IOException ex) {
            }
            //g2.setColor(Color.red);
            //g2.drawString("moi ban an phim de bat dau", 200, 300);
        }
        if(CurrentScreen == GAME_OVER){
            try {
                gameover =ImageIO.read(new File("Assets/GameOver.png"));
                g2.drawImage(gameover, 150, 175,null);
                getready =ImageIO.read(new File("Assets/GetReady.png"));
                g2.drawImage(getready, 150, 275,null);
                
                 
                 
            } catch (IOException ex) {
            }
            
            
            
            score.showScore(g2);
            
        }
        if(CurrentScreen == PAUSE_SCREEN){
            g2.setColor(Color.RED);
            g2.setFont(new Font("arial",Font.BOLD,20));
            g2.drawString("Press ENTER to Continue", 300, 320);
        }
        g2.setColor(Color.RED);
        g2.setFont(new Font("arial",Font.BOLD,20));
        g2.drawString("Your score: "+Point, 20, 50);
    }

    @Override
    public void KEY_ACTION(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            
            if(CurrentScreen == BEGIN_SCREEN){
                CurrentScreen = GAMEPLAY_SCREEN;
            }else if(CurrentScreen == GAMEPLAY_SCREEN){
                if(birds.getLive())
                birds.fly();
                
            } else if(CurrentScreen == PAUSE_SCREEN){
                CurrentScreen = GAMEPLAY_SCREEN;
            } else if(CurrentScreen == GAME_OVER){
                CurrentScreen = BEGIN_SCREEN;
            }
           
        }
        
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            
            if(CurrentScreen == GAMEPLAY_SCREEN){
                CurrentScreen = PAUSE_SCREEN;
            } else if(CurrentScreen == PAUSE_SCREEN){
                CurrentScreen = GAMEPLAY_SCREEN;
            }
        }
    
    }

    
}
