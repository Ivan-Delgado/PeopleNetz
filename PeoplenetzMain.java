import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
public class PeoplenetzMain extends JFrame{
	Commands com= new Commands();
	Maps map;
	StartScreen screen;
	Battle battle;
	Intro intro;
	Pause pause;
	String CurrentFrame="start";
	boolean inStart=false;
    public PeoplenetzMain() throws IOException{
    	setTitle("PeopleNetz");
    	setSize(500,500);
    	setVisible(true);
    	setResizable(false);
    	addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent a) {
				com.SetCurrentCommand(KeyEvent.getKeyText(a.getKeyCode()));
			}
			@Override
			public void keyReleased(KeyEvent a) {
				com.SetCurrentCommand(null);
			}
			@Override
			public void keyTyped(KeyEvent a) {
				//empty
			}
    	});
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void startScreen(String temp) {
    	if(temp.equals("start")) {
    		screen=new StartScreen();
            add(screen);
    	}
    	if(temp.equals("main")) {
    		map=new Maps();
            add(map);
    	}
    	if(temp.equals("intro")) {
    		intro=new Intro();
            add(intro);
    	}
    	if(temp.equals("battle")) {
    		battle=new Battle();
            add(battle);
    	}
    }
    private void setStart(boolean temp) {
    	inStart=temp;
    }
    private void endScreen(String temp) throws NullPointerException{
    	if(temp.equals("start")) {
    		remove(screen);
    		screen.dispose();
    	}
    	if(temp.equals("main")) {
    		remove(map);
    		map.dispose();
    	}
    	if(temp.equals("intro")) {
    		remove(intro);
    		intro.dispose();
    	}
    	if(temp.equals("battle")) {
    		remove(battle);
    		battle.dispose();
    	}
    }
    public void start() {
    	startScreen("start");
    	while(inStart) {
    		repaint();
    	}
    	endScreen("start");
    	CurrentFrame="main";
    	GetShit shit=new GetShit();
    	if(shit.check()) {
        	while(true) {
        		map.update();
        		repaint();
        	}
    	}else {
    		return;
    	}
    }
    public void setState(String temp) {
    	if(temp.equalsIgnoreCase("start")) {
    		setStart(true);
    	}else {
    		CurrentFrame=temp;   
    	}
    }
    public static void main(String[]args) throws IOException {
    	PeoplenetzMain peep=new PeoplenetzMain();
    	peep.start();
    }
}
