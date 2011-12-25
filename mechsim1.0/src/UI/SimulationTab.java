package UI;

	import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;

import simulator.mainThread;
import simulator.mechanicalModel;

	public class SimulationTab implements Runnable{
		JPanel panel;
		boolean showingFrames;
		
		Thread t0 ;
		mainUIFrame frame;
		mainThread cycle;
		
		
		SimulationTab(){panel=new JPanel(
				showingFrames=false);}
		
		SimulationTab(mainUIFrame frame,mainThread cycle){
			this.cycle=cycle;
			this.frame=frame;
			panel=new JPanel();
			showingFrames=true;
			setNewSimulation();	
	 		}
	   
	    /**
	     * The main entry-point method. Creates the tab.
	     */		
		public void setPanel(JPanel panel){
			this.panel=panel;
			
		}
		
	    public Component draw() {	    	
	        return panel;
	    }
	    
	    void setNewSimulation(){
	    	showingFrames=true;
			//cycle = new mainThread(3,3,0.01);
			
			mechanicalModel model = cycle.getModel();
		    cycle.start();
		    
		    panel=new simulationPanel( model,this,cycle);
	    }
	    
		public void run() {
			System.out.println("Visualisation thread started");
			while (showingFrames){	
				panel.repaint();
				frame.repaint();
				try {
					//TODO maybe i should make it constant
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Visualisation thread stopped");
		}
		
		
		void disposeVisualisationThread(){
			showingFrames=false;
			try{
				cycle.StopSimulation();
			}
			catch(NullPointerException  d){
				System.out.println("problems?");
				
			}
		}
		public void PausePressed(){
			if(!cycle.getPauseCondition()){//if it is unpaused
				System.out.println("paused");
				synchronized (cycle) {
					cycle.setPauseCondition(true);
				}
			}
			else{
				synchronized (cycle) {
					cycle.setPauseCondition(false);
					cycle.notify();
				}
				System.out.println("unpaused");
			}
		}
	
	}


