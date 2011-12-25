package simulator;

import java.io.File;

public class mainThread extends Thread {

	boolean calculating;//info for stopping/starting thread
	boolean simulationPaused=false;//info for pausing/unpausing thread
	mechanicalModel model;
	double totalKineticalEnergy=0;
	double totalPotentialEnergy=0;
	Average averageKinEnergy;
	Average averagePotEnergy;
	
	

	
	public mainThread(File chosenFile){
		averageKinEnergy=new Average();
		averagePotEnergy=new Average();
		calculating=true;
		//creating new model
		model =new mechanicalModel(chosenFile,this);

	}
	public Average getKinEnergyAverage(){
		return averageKinEnergy;
	}
	public Average getPotEnergyAverage(){
		return averagePotEnergy;
	}

	public void StopSimulation(){
		calculating=false;
	}
	public mechanicalModel getModel(){return model;}
	
	public void run(){
		System.out.println("Simulation started");
		while(calculating){
			
			
			
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
			double totalKineticalEnergy=0;
			double totalPotentialEnergy=0;
			for(int i =1 ;i<model.getColumns()-1;i++){
				for(int j =1;j<model.getSeries()-1;j++){
					//get acceleration for every point
					model.getMassModel(j,i).updateKineticalEnergy();
					totalKineticalEnergy=totalKineticalEnergy+model.getMassModel(j,i).getKineticalEnergy();
					totalPotentialEnergy=totalPotentialEnergy+model.getPotentialEnergy(j,i);
					double systemAccelerationX=model.getxAcceleration(j, i);
					double systemAccelerationY=model.getyAcceleration(j, i);
					//set new acceleration in every point
					 model.getMassModel(j,i).setNewAcceleration(systemAccelerationX, systemAccelerationY);
				}
				
			}
			for(int i =1 ; i<model.getColumns()-1;i++){
				for(int j =1;j<model.getSeries()-1;j++){
					 model.getMassModel(j,i).updatePointCoordinates();
				}					
			}
			this.totalKineticalEnergy=totalKineticalEnergy;
			this.totalPotentialEnergy=totalPotentialEnergy;
			averageKinEnergy.insertNew(totalKineticalEnergy);
			averagePotEnergy.insertNew(totalPotentialEnergy);
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
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
	public double getTotalKineticalEnergy(){
		return totalKineticalEnergy;
	}
	public double getTotalPotentialEnergy(){
		return totalPotentialEnergy;
	}
	public double getAverageKineticalEnergy(){
		return averageKinEnergy.getAverage();
	}
	public double getAveragePotentialEnergy(){
		return averagePotEnergy.getAverage();
	}
	


	
}
