package frame;

public class DataFrame extends Frame {
	
	private short id;
	private byte dataLength;
	private long data;
	private short crc;
	private byte ack;
	
	public DataFrame(short id) {
		super();
		this.id = id;
	}
	
	public void setDataLength(byte dataLength) {
		this.dataLength = dataLength;
	}
	
	public void setData(long data) {
		this.data = data;
	}

	@Override
	protected void computeMessage() {
		// TODO Auto-generated method stub
		
	}

}