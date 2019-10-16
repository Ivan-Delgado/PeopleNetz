import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
public class PeoplenetzMain {
	JFrame frame=new JFrame("Peoplenetz");
	Commands com= new Commands();
	Maps map;
	StartScreen screen;
	String CurrentFrame="start";
	boolean inStart=false;
    public PeoplenetzMain() {
    	frame.setSize(500,500);
    	frame.setVisible(true);
    	frame.setResizable(false);
    	frame.addKeyListener(new KeyListener() {
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
    }
    private void startScreen() {
    	screen=new StartScreen();
        frame.add(screen);
    }
    public void setStart(boolean temp) {
    	inStart=temp;
    }
    private void endScreen() {
    	frame.remove(screen);
    }
    public void start() {
    	startScreen();
    	while(inStart) {
    		frame.repaint();
    	}
    	endScreen();
    	GetShit shit=new GetShit();
    	if(shit.check()) {
        	while(true) {
        		map.update();
        		frame.repaint();
        	}
    	}else {
    		return;
    	}
    }
    public void setState(String temp) {
    	
    }
    public static void main(String[]args) {
    	PeoplenetzMain peep=new PeoplenetzMain();
    	peep.start();
    }
}
