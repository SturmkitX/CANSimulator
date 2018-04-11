package frame;

import codes.RequestCode;

public class OverloadFrame extends Frame {
	
	private boolean isActive;
	
	public OverloadFrame() {
		super(RequestCode.OVERLOAD);
	}
	
	public void setActive(boolean active) {
		this.isActive = active;
	}

	@Override
	protected void computeMessage() {
		// TODO Auto-generated method stub
		
	}

}
