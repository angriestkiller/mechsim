package UI;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


//import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;



/**
 * Graphical user interface of the simulator
 */



public class mainUIFrame extends JFrame {

  private ParamTab paramTab ;
  private SimulationTab sumulationTab;


  /**
   * Constructs mechsim GUI.
   */
 public mainUIFrame(){

    // Create singleton instances of the tab classes
	 paramTab=new ParamTab();
	 sumulationTab= new SimulationTab();
 

    setTitle("MechSim");

    // set L&F to the nice Windows style
   // try {
      //  UIManager.setLookAndFeel(new WindowsLookAndFeel());
   // } catch (UnsupportedLookAndFeelException e1) {
      
  //  }

    drawWidgets();

    // size & location
    int width = 600;
    int height = 400;
    setSize(width, height);


    addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            
            
            System.exit(0);
        }
    });
  }

  private void drawWidgets() {
    JTabbedPane tabbedPane = new JTabbedPane();

    tabbedPane.add("Parameters", paramTab.draw());
    tabbedPane.add("Simulation", sumulationTab.draw());

    getContentPane().add(tabbedPane);
  }
  public void setFrameVisible(){
	  setVisible(true);
  }

}

