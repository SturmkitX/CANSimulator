package frame;

public abstract class Frame implements Comparable<Frame> {

	public static final short MAX_AVAILABLE_ID = 2047;		// maximum that can be stored on 11 bits (Short.MAX_VALUE & 0x7FF)

	protected short id;
	
	protected byte[] message;
	
	public Frame() {
		this.message = null;
		this.id = MAX_AVAILABLE_ID;
	}

	public Frame(short id) {
		this.message = null;
		this.id = id;
	}
	
	protected abstract void computeMessage();
	
	public byte[] getMessage() {
		return message;
	}

	public short getId() {
		return id;
	}

	public int compareTo(Frame o) {
		return (id - o.getId());
	}
}
