package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;


import simulator.mainThread;
import simulator.massModel;
import simulator.mechanicalModel;
import simulator.selectedPointInfo;


import javax.swing.JLabel;
import javax.swing.JPanel;

public class simulationPanel extends  JPanel implements KeyListener{

	mechanicalModel model;
	boolean PointSelected=false;
	selectedPointInfo selectedPoint;
	mainThread cycle;
	
	
	    simulationPanel( final mechanicalModel model,final SimulationTab simulationTab,mainThread cycle) {
	    	this.model=model;  
	    	this.cycle=cycle;
			addKeyListener(new KeyListener(){

				@Override
				public void keyTyped(KeyEvent a) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void keyPressed(KeyEvent a) {
				if(a.getKeyCode()==KeyEvent.VK_SPACE)
						simulationTab.PausePressed();
					
				}
				@Override
				public void keyReleased(KeyEvent a) {
					// TODO Auto-generated method stub
					
				}
				});
	    	addMouseMotionListener(new MouseMotionListener(){
	    		
				@Override
				public void mouseDragged(MouseEvent e) {
					
						
						if(!PointSelected){
							selectedPoint=model.getselectedPoint(e.getX()-100, e.getY()-100);
							if(selectedPoint.getSelected()==true){
								PointSelected=true;
							
							}
						}
						else{
							selectedPoint.getMass().setToDragged();
							selectedPoint.getMass().setNewCoordinatesByClicking(e.getX()-100,e.getY()-100);			
					}
				}	

				@Override
				public void mouseMoved(MouseEvent e) {
					
					
				}	    		
	    	});
	    		    		
	    	addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					
						
						if(!PointSelected){
							selectedPoint=model.getselectedPoint(e.getX(), e.getY());
							if(selectedPoint.getSelected()==true){
								PointSelected=true;
						}
					
						else{
							selectedPoint.getMass().setToDragged();
							selectedPoint.getMass().setNewCoordinatesByClicking(e.getX(),e.getY());
						}
					}

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					
					
						if(PointSelected){
							selectedPoint.getMass().setToUndragged();
							selectedPoint=null;
							PointSelected=false;
							model.getThread().getKinEnergyAverage().beginNewCount();
							model.getThread().getPotEnergyAverage().beginNewCount();
						}
					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				});
	    		
	    	}
	    	
	    
	    @Override
	  protected void paintComponent(Graphics g) {
	    	

	    	super.paintComponent(g);
	    	g.setColor(new Color(226,24,5));
	    	Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.drawString("Kinetical Energy is "+cycle.getTotalKineticalEnergy()+" J", 50, 50);
	        g2.drawString("Potential Energy is "+cycle.getTotalPotentialEnergy()+" J", 50, 60);
	        g2.drawString("Total Energy is "+(cycle.getTotalPotentialEnergy()+cycle.getTotalKineticalEnergy())+" J", 50, 70);
	        g2.drawString("Average Kinetical Energy is "+cycle.getAverageKineticalEnergy()+" J", 350, 50);
	        g2.drawString("Average Potential Energy is "+cycle.getAveragePotentialEnergy()+" J", 350, 60);
	        g2.drawString("Average Total Energy is "+(cycle.getAveragePotentialEnergy()+cycle.getAverageKineticalEnergy())+" J", 350, 70);
	        

	    	//add lines between dots
	    	for (int i =0;i<model.getSeries()-1;i++){
	    		for(int j=0;j<model.getColumns();j++){
	    			g2.draw(new Line2D.Double(model.getMassModel(i, j).getxCoord()+100, 
	    					model.getMassModel(i, j).getyCoord()+100, model.getMassModel(i+1, j).getxCoord()+100
	    					, model.getMassModel(i+1, j).getyCoord()+100));
	    		}}
	    	    	for (int i =0;i<model.getSeries();i++){
	    	    		for(int j=0;j<model.getColumns()-1;j++){
	    			
	    				g2.draw(new Line2D.Double(model.getMassModel(i, j).getxCoord()+100, 
	    					model.getMassModel(i, j).getyCoord()+100, model.getMassModel(i, 1+j).getxCoord()+100
	    					, model.getMassModel(i, j+1).getyCoord()+100));}
	    		
	    	}
	    	for (int i =0;i<model.getSeries();i++){
	    		for(int j=0;j<model.getColumns();j++){
	    			if( model.getMassModel(i, j).getStatus()==true)
	    				g.setColor(new Color(226,24,5));
	    			else
	    				g.setColor(new Color(26,224,5));
	    			
	    			g.fillOval( (int) model.getMassModel(i, j).getxCoord()-7+100, (int) model.getMassModel(i, j).getyCoord()-7+100, 15,15);			
			}			
		}

	            }

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
}


