package UI;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import simulator.mainThread;


//import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;


/**
 * Graphical user interface of the simulator
 */

public class mainUIFrame extends JFrame {

  boolean SimulationRunning=false;
  private ParamTab paramTab ;
  private SimulationTab simulationTab;
  JTabbedPane tabbedPane;
  Thread t1;//Thread that changes frames

  /**
   * Constructs mechsim GUI.
   */
 public mainUIFrame(){

    // Create singleton instances of the tab classes
	 paramTab=new ParamTab(this);
	 simulationTab= new SimulationTab();
	 setTitle("MechSim");
     drawWidgets();

    // size 
    int width = 1000;
    int height = 700;
    setSize(width, height);


    addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            
            
            System.exit(0);
        }
    });
    setVisible(true);
  }
 
 public SimulationTab getSimuTab(){
	 return simulationTab;
 }


  private void drawWidgets() {
    tabbedPane = new JTabbedPane();

    tabbedPane.add("Parameters", paramTab.draw());
    tabbedPane.add("Simulation", simulationTab.draw());

    getContentPane().add(tabbedPane);
  }
  public void setFrameVisible(){
	  setVisible(true);
  }
  void callVisualisationThread(){
	  t1=new Thread(simulationTab);
	  t1.start();
	  
  }
  public void StopVisualisationThread(){
	  simulationTab.disposeVisualisationThread();

  }
  void BeginSimPressed(int number){
	  if (!SimulationRunning){
		  tabbedPane.remove(tabbedPane.getComponent(1));
		  mainThread cycle = new mainThread(number,number,0.01);
		  simulationTab= new SimulationTab(this,cycle);
		  callVisualisationThread();
		  tabbedPane.add("Simulation", simulationTab.draw());
		  SimulationRunning=true;
	  }
  }
  void EndSimPressed(){
	  if(SimulationRunning){
		  StopVisualisationThread();
		  tabbedPane.remove(tabbedPane.getComponent(1));
		  simulationTab= new SimulationTab();
		  tabbedPane.add("Simulation", simulationTab.draw());
		  SimulationRunning=false;
	  }
  }
  void PausePressed(){
	  simulationTab.PausePressed();
  }

}

