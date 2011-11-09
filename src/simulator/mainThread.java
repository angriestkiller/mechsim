package simulator;

public class mainThread {
	int columns ,series;
	mechanicalModel model;
	public mainThread(int columns , int series,double dt){
		model =new mechanicalModel(columns ,series,dt);
		this.columns=columns;
		this.series=series;
	}
	
	public mechanicalModel getModel(){return model;}
	
	public void beginSimulation(){
		while(true){
			//loopstop conditions to be added
			//looppause conditions to be added
			try {
				Thread.sleep(2000);
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i =1 ; i<columns+1;i++){
				for(int j =1;j<series+1;j++){
					//get acceleration for every point
					double systemAccelerationX=model.getxAcceleration(i, j);
					double systemAccelerationY=model.getyAcceleration(i, j);
					//set new acceleration in every point
					 model.getMassModel(i,j).setNewAcceleration(systemAccelerationX, systemAccelerationY);
				}
				
			}
			for(int i =1 ; i<columns+1;i++){
				for(int j =1;j<series+1;j++){
					 //System.out.println(model.getMassModel(i,j).getxCoord());
					//apply point movement under acceleration defined in previous loop
					 //System.out.println(i + "  "+ j);
					 model.getMassModel(i,j).updatePointCoordinates();
				     
					
				}
				
			
			}
			//System.out.println(model.getxAcceleration(1,1)+" "+model.getyAcceleration(1,1));
			System.out.println(model.getMassModel(1,1).getxCoord());
			
			
		}
	}
	
}
