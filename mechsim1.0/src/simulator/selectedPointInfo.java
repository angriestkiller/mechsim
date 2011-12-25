package simulator;

public class selectedPointInfo {
	massModel mass;
	boolean selected;
	
	selectedPointInfo(massModel mass,boolean selected){
		this.mass=mass;
		this.selected=selected;
	}
	public boolean getSelected(){
		return selected;
	}
	public massModel getMass(){
		return mass;
	}
}
