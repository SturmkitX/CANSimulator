package frame;

import codes.RequestCode;

public class OverloadFrame extends Frame {
	
	private boolean isActive;
	
	public OverloadFrame() {
		super(RequestCode.OVERLOAD);
	}

	@Override
	public int computeCrc() {
		return 0;
	}

	public void setActive(boolean active) {
		this.isActive = active;
	}

}
