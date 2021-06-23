package android.mobilequare.analyst.notifications;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationManagerCompat;
public class AnalystBroadcastReceiver extends BroadcastReceiver {
	public AnalystBroadcastReceiver() {
		super();
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		switch (intent.getAction()) {
			case "UNDO" :
				try {
					UndoAnalystEventNotification.getInstance(context).getCommand().undo();
				} catch (Exception e) {
					e.printStackTrace();
				}
				RedoAnalystEventNotification.getInstance(context)
						.setCommand(UndoAnalystEventNotification.getInstance(context).getCommand());
				RedoAnalystEventNotification.getInstance(context).create("Shop", "Action successfully undid", context);
				NotificationManagerCompat.from(context).cancel(1);
				break;
			case "UNDO-CLOSE" :
				NotificationManagerCompat.from(context).cancel(1);
				break;
			case "REDO" :
				try {
					RedoAnalystEventNotification.getInstance(context).getCommand().redo();
				} catch (Exception e) {
					e.printStackTrace();
				}
				UndoAnalystEventNotification.getInstance(context)
						.setCommand(RedoAnalystEventNotification.getInstance(context).getCommand());
				UndoAnalystEventNotification.getInstance(context).create("Shop", "Action successfully redid", context);
				NotificationManagerCompat.from(context).cancel(2);
				break;
			case "REDO-CLOSE" :
				NotificationManagerCompat.from(context).cancel(2);
				break;
		}
	}
}