package frame;

import java.io.Serializable;

public abstract class Frame implements Comparable<Frame>, Serializable {

	public static final short MAX_AVAILABLE_ID = 2047;		// maximum that can be stored on 11 bits (Short.MAX_VALUE & 0x7FF)

	protected int id;
	protected int crc;

	public Frame() {
		this.id = MAX_AVAILABLE_ID;
	}

	public Frame(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int compareTo(Frame o) {
		return (id - o.getId());
	}

	public abstract int computeCrc();

	public int getCrc() {
		return crc;
	}

	public void setCrc(int crc) {
		this.crc = crc;
	}
}
