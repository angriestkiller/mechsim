package simulator;

public class Average {
	long steps=0;
	double average;
	
	public void insertNew(double number){
		average=1.0*(average*steps+number)/(steps+1);
		steps++;
	}
	public double getAverage(){return average;}
	
	public void beginNewCount(){
		steps=0;
		average=0;
	}
}
