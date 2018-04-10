package frame;

public class ErrorFrame extends Frame {
	
	private boolean isActive;
	
	public ErrorFrame() {
		super(ERROR_FRAME_ID);
	}
	
	public void setActive(boolean active) {
		this.isActive = active;
	}

	@Override
	protected void computeMessage() {
		// TODO Auto-generated method stub
		
	}

}
