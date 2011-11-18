package simulator;

public class mainThread extends Thread {
	int columns ;
	int series;
	boolean calculating;//info for stopping/starting thread
	boolean simulationPaused=false;//info for pausing/unpausing thread
	mechanicalModel model;
	public mainThread(int columns , int series,double dt){
		calculating=true;
		model =new mechanicalModel(columns ,series,dt);
		this.columns=columns;
		this.series=series;
	}
	public void StopSimulation(){
		calculating=false;
	}
	public mechanicalModel getModel(){return model;}
	
	public void run(){
		System.out.println("Simulation started");
		while(calculating){
			
			//looppause conditions to be added
			
			synchronized (this) {
                while (simulationPaused) {
                    try {
                        wait();
                    } catch (Exception e) {
                    }
                }
                	
			}
			try {
				Thread.sleep(2);
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i =1 ; i<model.getColumns();i++){
				for(int j =1;j<model.getSeries();j++){
					//get acceleration for every point
					
					double systemAccelerationX=model.getxAcceleration(i, j);
					double systemAccelerationY=model.getyAcceleration(i, j);
					//set new acceleration in every point
					 model.getMassModel(i,j).setNewAcceleration(systemAccelerationX, systemAccelerationY);
				}
				
			}
			for(int i =1 ; i<columns+1;i++){
				for(int j =1;j<series+1;j++){
					 model.getMassModel(i,j).updatePointCoordinates();
				}					
			}
		}
		System.out.println("Simulation stopped");
	}
	
	
	public boolean getPauseCondition(){
		return simulationPaused;
	}
	public void setPauseCondition(boolean a){
		 simulationPaused=a;
	}
	
	public void pauseButtonPressed(){
		if(!simulationPaused){
			System.out.println("paused");
			simulationPaused=true;
		}
		else{
			simulationPaused=false;
			notify();
			System.out.println("unpaused");
		}
	}


	
}
