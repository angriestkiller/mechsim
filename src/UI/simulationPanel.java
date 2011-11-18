package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;


import simulator.massModel;
import simulator.mechanicalModel;
import simulator.selectedPointInfo;

import javax.swing.JPanel;

public class simulationPanel extends  JPanel {

	mechanicalModel model;
	boolean PointSelected=false;
	selectedPointInfo selectedPoint;
	    simulationPanel( final mechanicalModel model) {
	    	this.model=model;  
	    	addMouseMotionListener(new MouseMotionListener(){

				@Override
				public void mouseDragged(MouseEvent e) {
					if(!PointSelected){
						selectedPoint=model.getselectedPoint(e.getX(), e.getY());
						if(selectedPoint.getSelected()==true){
							PointSelected=true;
							
						}
					}
					else{
						selectedPoint.getMass().setToDragged();
						selectedPoint.getMass().setNewCoordinatesByClicking(e.getX(),e.getY());
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
					}
					else{
						selectedPoint.getMass().setToDragged();
						selectedPoint.getMass().setNewCoordinatesByClicking(e.getX(),e.getY());
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
	        

	    	//add lines between dots
	    	for (int i =0;i<model.getColumns()+1;i++){
	    		for(int j=0;j<model.getSeries()+1;j++){
	    			g2.draw(new Line2D.Double(model.getMassModel(i, j).getxCoord(), 
	    					model.getMassModel(i, j).getyCoord(), model.getMassModel(i+1, j).getxCoord()
	    					, model.getMassModel(i+1, j).getyCoord()));
	    			if(j!=model.getSeries()){
	    				g2.draw(new Line2D.Double(model.getMassModel(i, j).getxCoord(), 
	    					model.getMassModel(i, j).getyCoord(), model.getMassModel(i, 1+j).getxCoord()
	    					, model.getMassModel(i, j+1).getyCoord()));}
	    			
	    		}
	    	}
	    	for (int i =0;i<model.getColumns()+1;i++){
	    		for(int j=0;j<model.getSeries()+1;j++){
	    			g.fillOval( (int) model.getMassModel(i, j).getxCoord()-7, (int) model.getMassModel(i, j).getyCoord()-7, 15,15);			
			}
			
		}


	            }
}


