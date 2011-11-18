package UI;




	import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


	public class ParamTab {
		JPanel panel;
		mainUIFrame frame;
		JTextField numberOfRows;
		ParamTab(mainUIFrame frame){
			this.frame=frame;
		}
	    public Component draw( ) {
	    	JPanel panel = new JPanel();
			JButton BeginSimulationButton = new JButton(" Begin Simulation");
			JButton EndSimulationButton = new JButton(" End Simulation");
			JButton PauseButton=new JButton("Pause Simulation");
			numberOfRows=new JTextField(5);
			BeginSimulationButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BeginSimPressed();
				}
			});
			EndSimulationButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EndSimPressed();
				}
			});
			PauseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.PausePressed();
				}
			});
			panel.add(numberOfRows);
	        panel.add(BeginSimulationButton );
	        panel.add(EndSimulationButton );
	        panel.add(PauseButton);
	        return panel;
	    }
	    
	    
	    protected void BeginSimPressed(){
	    	try{
	    		int number =1+Integer.parseInt(numberOfRows.getText());
	    		frame.BeginSimPressed(number);
	    	}
	    	catch(Exception couldnotReadline){
	    		
	    	}
	    }
	    protected void EndSimPressed(){
	      	frame.EndSimPressed();
	    }
	    

}
