package frame;

import java.io.Serializable;

public abstract class Frame implements Comparable<Frame>, Serializable {

	public static final short MAX_AVAILABLE_ID = 2047;		// maximum that can be stored on 11 bits (Short.MAX_VALUE & 0x7FF)

	/*
	All the fields of a Frame will be represented using ints and Object for variable sizes
	When the message will be computed as a byte array for CRC check, the actual specification sizes will be used
	 */

	protected int id;
	
	protected byte[] message;
	
	public Frame() {
		this.message = null;
		this.id = MAX_AVAILABLE_ID;
	}

	public Frame(int id) {
		this.message = null;
		this.id = id;
	}
	
	protected abstract void computeMessage();
	
	public byte[] getMessage() {
		return message;
	}

	public int getId() {
		return id;
	}

	public int compareTo(Frame o) {
		return (id - o.getId());
	}
}
