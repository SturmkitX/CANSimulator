package frame;

public abstract class Frame {
	
	protected byte[] message;
	
	public Frame() {
		this.message = null;
	}
	
	protected abstract void computeMessage();
	
	public byte[] getMessage() {
		return message;
	}
}
