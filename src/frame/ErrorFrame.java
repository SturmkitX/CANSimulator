package frame;

import codes.RequestCode;

public class ErrorFrame extends Frame {
	
	private boolean isActive;
	
	public ErrorFrame() {
		super(RequestCode.ERROR);
	}
	
	public void setActive(boolean active) {
		this.isActive = active;
	}

	@Override
	protected void computeMessage() {
		// TODO Auto-generated method stub
		
	}

}
