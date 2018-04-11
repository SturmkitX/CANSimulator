package frame;

public class RemoteFrame extends Frame {
	
	private int dataLength;
	private int crc;

	public RemoteFrame(short id) {
		super(id);
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
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
