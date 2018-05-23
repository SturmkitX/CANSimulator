package frame;

import codes.RequestCode;

public class OverloadFrame extends Frame {

	public OverloadFrame() {
		super(RequestCode.OVERLOAD);
	}

	@Override
	public int computeCrc() {
		return 0;
	}

}
