package UI;




	import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


	public class ParamTab {
		JPanel panel;
		mainUIFrame frame;
		JTextField numberOfRows;
		ParamTab(mainUIFrame frame){
			this.frame=frame;
		}
	    public Component draw( ) {
	    	JPanel panel = new JPanel();
	    	JLabel info = new JLabel("Choose custum file to start simulation.");
			
			JButton EndSimulationButton = new JButton(" End Simulation");
			JButton PauseButton=new JButton("Pause Simulation");
			JButton LoadDataButton=new JButton("load data");
			JButton RestartButton=new JButton("Restart simulation");
			

			EndSimulationButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EndSimPressed();
				}
			});
			RestartButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					restartPressed();
				}
			});
			PauseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.PausePressed();
				}
			});
			LoadDataButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadDataPressed();
				}
			});
			panel.add(info);
			//panel.add(numberOfRows);
	     
	        panel.add(EndSimulationButton );
	        panel.add(PauseButton);
	        panel.add(LoadDataButton);
	        panel.add(RestartButton);
	        return panel;
	    }
	    
	    
	   
	    protected void EndSimPressed(){
	      	frame.EndSimPressed();
	    }
	    protected void restartPressed(){
	    	frame.restartPressed();
	    }
	    protected void loadDataPressed(){
	    	System.out.println("file opener opened");
	    	final JFileChooser filer = new JFileChooser();
	    	int result = filer.showOpenDialog(frame);

	    	// Determine which button was clicked to close the dialog
	    	switch (result) {
	    	  case JFileChooser.APPROVE_OPTION:
	    		  System.out.println("open Pressed");
	    		  frame.BeginSimPressed(filer.getSelectedFile());
	    	    // Approve (Open or Save) was clicked
	    	    break;
	    	  case JFileChooser.CANCEL_OPTION:
	    	    // Cancel or the close-dialog icon was clicked
	    	    break;
	    	  case JFileChooser.ERROR_OPTION:
	    	    // The selection process did not complete successfully
	    	    break;
	    	}
	    	
	    	
	    	
	    }
	    
	    

}
	
