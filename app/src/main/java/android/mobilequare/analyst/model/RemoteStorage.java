package android.mobilequare.analyst.model;
public class RemoteStorage {
	private static RemoteStorage remoteStorage;
	public static RemoteStorage getInstance() {
		if (remoteStorage == null) {
			remoteStorage = new RemoteStorage();
		}
		return remoteStorage;
	}
	public RemoteStorage() {
		/*TODO TABLE CREATION**/
	}
}
