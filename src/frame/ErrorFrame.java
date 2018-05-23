package frame;

import codes.RequestCode;

public class ErrorFrame extends Frame {

	public ErrorFrame() {
		super(RequestCode.ERROR);
	}

	@Override
	public int computeCrc() {
		return 0;
	}


}
