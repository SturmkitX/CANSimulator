package frame;

public class OverloadFrame extends Frame {
	
	private boolean isActive;
	
	public OverloadFrame() {
		super(OVERLOAD_FRAME_ID);
	}
	
	public void setActive(boolean active) {
		this.isActive = active;
	}

	@Override
	protected void computeMessage() {
		// TODO Auto-generated method stub
		
	}

}
