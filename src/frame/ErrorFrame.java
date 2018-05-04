package frame;

import codes.RequestCode;

public class ErrorFrame extends Frame {
	
	private boolean isActive;
	
	public ErrorFrame() {
		super(RequestCode.ERROR);
	}

	@Override
	public int computeCrc() {
		return 0;
	}

	public void setActive(boolean active) {
		this.isActive = active;
	}



}
