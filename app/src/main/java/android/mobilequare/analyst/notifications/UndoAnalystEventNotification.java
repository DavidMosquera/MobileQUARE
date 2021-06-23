package android.mobilequare.analyst.notifications;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.mobilequare.analyst.R;
import android.mobilequare.analyst.command.Command;
import android.mobilequare.analyst.controller.AnalystConfigurationController;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
public class UndoAnalystEventNotification implements NotificationInterface {
	private static UndoAnalystEventNotification undoAnalystEventNotification;
	private AnalystConfigurationController analystConfigurationController;
	private Command command;
	public static UndoAnalystEventNotification getInstance(Context context) {
		if (undoAnalystEventNotification == null) {
			undoAnalystEventNotification = new UndoAnalystEventNotification(context);
		}
		return undoAnalystEventNotification;
	}
	@Override
	public void create(String title, String text, Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			CharSequence name = "UNDO_CHANEL";
			String description = "UNDO_CHANEL_DESC";
			NotificationChannel channel = new NotificationChannel("UndoChanel", name,
					NotificationManager.IMPORTANCE_HIGH);
			channel.setDescription(description);
			NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);
			Intent undoIntent = new Intent(context, AnalystBroadcastReceiver.class);
			undoIntent.setAction("UNDO");
			Intent closeIntent = new Intent(context, AnalystBroadcastReceiver.class);
			closeIntent.setAction("UNDO-CLOSE");
			NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "UndoChanel")
					.setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(title).setContentText(text)
					.setAutoCancel(true)
					.addAction(R.drawable.ic_launcher_background, "UNDO",
							PendingIntent.getBroadcast(context, 0, undoIntent, 0))
					.addAction(R.drawable.ic_launcher_background, "CLOSE",
							PendingIntent.getBroadcast(context, 0, closeIntent, 0))
					.setTimeoutAfter(
							(long) analystConfigurationController.getAnalystConfiguration().getNotificationTime()
									* 1000)
					.setPriority(NotificationCompat.PRIORITY_HIGH);
			NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
			notificationManagerCompat.notify(1, builder.build());
		}
	}
	private UndoAnalystEventNotification(Context context) {
		analystConfigurationController = new AnalystConfigurationController(context);
	}
	public void setCommand(Command command) {
		this.command = command;
	}
	public Command getCommand() {
		return this.command;
	}
}
