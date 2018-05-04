package frame;

import java.nio.ByteBuffer;
import java.util.zip.CRC32;

public class RemoteFrame extends Frame {
	
	private int dataLength;

	public RemoteFrame(short id) {
		super(id);
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public int getDataLength() {
		return dataLength;
	}

	@Override
	public int computeCrc() {
		ByteBuffer bf = ByteBuffer.allocate(9);
		bf.put((byte)1);
		bf.putInt(id);
		bf.putInt(dataLength);

		CRC32 crc = new CRC32();
		crc.update(bf);

		return (int)crc.getValue();
	}

}
