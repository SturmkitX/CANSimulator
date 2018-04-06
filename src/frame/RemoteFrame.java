package frame;

public class RemoteFrame extends Frame {
	
	private short id;
	private byte dataLength;
	private short crc;
	private byte ack;
	
	public RemoteFrame(short id) {
		super();
		this.id = id;
	}

	@Override
	protected void computeMessage() {
		// TODO Auto-generated method stub
		
	}

}
