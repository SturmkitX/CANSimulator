package frame;

public class DataFrame extends Frame {
	
	private int dataLength;
	private byte[] data;	// data can be anything, provided it fits in dataLength bytes
	private int crc;

	public DataFrame(int id) {
		super(id);
	}
	
	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}
	
	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] getData() {
		return data;
	}

	public int getDataLength() {
		return dataLength;
	}

	public int getCrc() {
		return crc;
	}

	@Override
	protected void computeMessage() {
		// TODO Auto-generated method stub
		
	}

}
