package simulator;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import IO.FileReaders;


public class mechanicalModel {
	//this is a main simulator class
	// stores info about mass position , speed and acceleration
	int series;
	int columns;
	double dt;
	int k=1;//Guck's koef
	int  l=100;// tasakaalus vedru pikkus
	// i and j are indexes of mass dots
	mainThread thread;
	massModel MassDot;
	List<List<massModel>> pointArray ;
	springArray verticalSprings;
	springArray horisontalSprings;

	
	public mechanicalModel(File chosenFile,mainThread thread){
		 this.thread=thread;
		int startD;//starting distance
		//columns should show moving participles 
		FileReaders reader=new FileReaders(chosenFile);
		
		if(reader.readNextLine().equals(new String("accuracy"))){
			this.dt=Double.parseDouble(reader.readNextLine());
			System.out.println("accuracy ok");
		    String wtf =reader.readNextLine();
		    System.out.println(wtf);
		    	
			if(wtf.equals(new String("starting distance"))){
				
				startD=Integer.parseInt(reader.readNextLine());	
				System.out.println("starting distance ok");
		
				if(reader.readNextLine().equals(new String("balanced lenght"))){
					this.l=Integer.parseInt(reader.readNextLine());
					System.out.println("balanced lenght ok");
			
			
					if(reader.readNextLine().equals(new String("masses"))){
						if(createMassArray(reader, startD)){
							System.out.println("masses  ok");
							verticalSprings=new springArray(reader,new String("horisontal springs"));
							horisontalSprings=new springArray(reader,new String("end"));
						}
					}
					}
			}
			
			System.out.println("load succesful");
		}
	}

	protected boolean createMassArray(FileReaders reader,int startD){
		pointArray = new ArrayList<List<massModel>>();
		int rows=0;
		String nextline=reader.readNextLine();
		while(!nextline.equals(new String("vertical springs"))){
			try{
			
			String[] parts =nextline.split(" ");
			columns= parts.length;
			List firstarray = new ArrayList<massModel>();
			for( int j =0 ; j< parts.length ; j++){
				
				if(parts[j].equals(new String("p"))||parts[j].equals(new String("n"))){
					firstarray.add(new massModel(startD*(j) ,startD*rows,dt,false,0));
				}
				else{
					firstarray.add(new massModel(startD*(j) ,startD*rows,dt,true,StringToInt(parts[j])));
				}
				
			}
			pointArray.add(firstarray);
			rows++;
			
			nextline=reader.readNextLine();
			}
			catch(Exception ex){
				System.out.println("could not load file correctly on line "+ rows);
				JOptionPane.showMessageDialog( null, "couldn't load file correctly." +
						"Check for mistakes in a file and try again");
				
				
				return false;
		}
			series=rows;

		}
		System.out.println("series are "+getSeries());
		System.out.println("columns are "+getColumns());
		return true;
	}
	protected int StringToInt(String a){
		
		try{
			//System.out.println(Integer.parseInt(a));	
			return Integer.parseInt(a);
		}
		catch(Exception ex){
			System.out.println("could not change string to int");
			return 0;
		}
	}

	public double getCoordX(int i , int j){ return pointArray.get(i).get(j).getxCoord();}
	public double getCoordY(int i , int j){ return pointArray.get(i).get(j).getyCoord();}
	public massModel getMassModel(int i , int j){ return pointArray.get(i).get(j);}
	
	//the most important two methods for the simulator
	//calculates force superpositions for one specific mass point (one for x , second for y)
	
	public double getPotentialEnergy(int i ,int j){//returns half of potential energy of springs surrounding the mass
		double PotEnergy=0;
		PotEnergy=PotEnergy+getEnergy(i,j,i,j-1);
		PotEnergy=PotEnergy+getEnergy(i,j,i+1,j);
		PotEnergy=PotEnergy+getEnergy(i,j,i-1,j);
		PotEnergy=PotEnergy+getEnergy(i,j,i,j+1);
		return PotEnergy;
	}
	double getEnergy(int i1 ,int j1,int i2 , int j2){//returns half of pot energy of free spring,if one of the spring ends is unchangeble
		if(pointArray.get(i2).get(j2).changable==false)//then all pot energy is returned
			return 0.5*k(i1,j1,i2,j2)*Math.pow(getLenght( i1 ,  j1, i2 ,  j2)-l,2);
		else
			return 0.25*k(i1,j1,i2,j2)*Math.pow(getLenght( i1 ,  j1, i2 ,  j2)-l,2);
	}

	public double getxAcceleration(int i , int j){
		return ( pointArray.get(i).get(j-1).getxCoord()-pointArray.get(i).get(j).getxCoord())*
				k(i,j,i,j-1)*
				getForceModule(i,j,i,j-1)/getLenght(i,j,i,j-1)+
				(pointArray.get(i+1).get(j).getxCoord()-pointArray.get(i).get(j).getxCoord())*
				k(i,j,i+1,j)*
				getForceModule(i,j,i+1,j)/getLenght(i,j,i+1,j)+
				(pointArray.get(i-1).get(j).getxCoord()-pointArray.get(i).get(j).getxCoord())*
				k(i,j,i-1,j)*
				getForceModule(i,j,i-1,j)/getLenght(i,j,i-1,j)+
				(pointArray.get(i).get(j+1).getxCoord()-pointArray.get(i).get(j).getxCoord())*
				k(i,j,i,j+1)*
				getForceModule(i,j,i,j+1)/getLenght(i,j,i,j+1);
				//-dissipation
		
	}
	//	ax=k*(verh-seredina )*sila/vsa dlina + k*(pravo - seredina )*sila / vsa dlina +k* (levo-seredina)*sila/vsa dlina+k*(niz - seredina)...
		
//	}
	//public double getyAcceleration(int i , int j){
		//ay=k*((verh-seredina)*sila/dlina+(pravo-seredina)*sila/dlina+(levo-seredina)*sila/dlina+(niz-seredina)...
//	}
	

	
	public double getyAcceleration(int i , int j){//returns force x vector
		return  (pointArray.get(i).get(j-1).getyCoord()-pointArray.get(i).get(j).getyCoord())
				*k(i,j,i,j-1)
				*getForceModule(i,j,i,j-1)/getLenght(i,j,i,j-1)+
				(pointArray.get(i+1).get(j).getyCoord()-pointArray.get(i).get(j).getyCoord())
				*k(i,j,i+1,j)
				*getForceModule(i,j,i+1,j)/getLenght(i,j,i+1,j)+
				(pointArray.get(i-1).get(j).getyCoord()-pointArray.get(i).get(j).getyCoord())*
				k(i,j,i-1,j)*
				getForceModule(i,j,i-1,j)/getLenght(i,j,i-1,j)+
				(pointArray.get(i).get(j+1).getyCoord()-pointArray.get(i).get(j).getyCoord())
				*k(i,j,i,j+1)
				*getForceModule(i,j,i,j+1)/getLenght(i,j,i,j+1);
				//-dissipation
		
	}
	
	 double getLenght(int i , int j,int i1 , int j1){//returns distance between 2 points
		return Math.sqrt(Math.pow(pointArray.get(i).get(j).getxCoord()-pointArray.get(i1).get(j1).getxCoord(),2)+
				Math.pow(pointArray.get(i).get(j).getyCoord()-pointArray.get(i1).get(j1).getyCoord(),2));
		
	}
	double getForceModule(int i , int j,int i1 , int j1){
		return (getLenght(i,j,i1,j1)-l)*k;
		
	}
	public int getSeries(){return series;}
	



	public int getColumns() {
		return columns;
	}
	public selectedPointInfo getselectedPoint(int x , int y){
		massModel selectedPoint=null;
		boolean selected=false;
		for (int i =1;i<getSeries();i++){
    		for(int j=1;j<getColumns();j++){
    			if(x<(getMassModel(i, j).getxCoord()+20 ) &&
    					x>(getMassModel(i, j).getxCoord()-20)&&
    					y<(getMassModel(i, j).getyCoord()+20)&&
    					y>(getMassModel(i, j).getyCoord()-20)&&
    					selected==false){
    						selectedPoint =getMassModel(i, j);
    						selected=true;
    						
    			}
    		}
		
	}
		return new selectedPointInfo(selectedPoint,selected);
	}
	public int k(int i1,int j1,int i2,int j2 ){//returns guck constant
		
		if(j1==j2){
			if(i1>i2){
				try{
					return verticalSprings.getValue(i2,j1-1);}
				catch(Exception ex ){
					System.out.println("couldn't load vertical spring "+i1+" "+j1+" "+i2+" "+j2);
					return 0;
				}
			}
			
			if(i2>i1){
				try{
					return verticalSprings.getValue(i1,j1-1);
				}
				catch(Exception ex ){
					System.out.println("couldn't load vertical spring "+i1+" "+j1+" "+i2+" "+j2);
					return 0;
				}
			}
			else
				return 0;
		}
		if(i1==i2){
			if(j1>j2){
				try{
					return  horisontalSprings.getValue(i1-1,j2);}
				catch(Exception ex ){
					System.out.println("couldn't load horizontal spring "+i1+" "+j1+" "+i2+" "+j2);
					return 0;
				}
				}
				
			if(j1<j2){
				try{
					return  horisontalSprings.getValue(i1-1,j1);
				}
				catch(Exception ex ){
					System.out.println("couldn't load horizontal spring "+i1+" "+j1+" "+i2+" "+j2);
					return 0;
				}
			}
			else 
				return 0;
		}
		else 
			return 0;
		}


		
	
	
	public mainThread getThread(){
		return thread;
	}
}

