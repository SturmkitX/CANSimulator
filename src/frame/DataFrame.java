package frame;

import java.nio.ByteBuffer;
import java.util.zip.CRC32;

public class DataFrame extends Frame {
	
	private int dataLength;
	private byte[] data;	// data can be anything, provided it fits in dataLength bytes

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

	@Override
	public int computeCrc() {
		ByteBuffer bf = ByteBuffer.allocate(17);
		bf.put((byte)0);
		bf.putInt(id);
		bf.putInt(dataLength);
		bf.put(data);

		CRC32 crc = new CRC32();
		crc.update(bf);

		return (int)crc.getValue();
	}

}
