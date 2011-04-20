package name.shamansir.mvplayout.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Card implements IsSerializable {
	
	private int id;

	protected Card() {
		this(-1);
	}
	
	protected Card(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
