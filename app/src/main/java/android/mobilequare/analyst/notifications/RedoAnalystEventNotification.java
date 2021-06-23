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
public class RedoAnalystEventNotification implements NotificationInterface {
	private static RedoAnalystEventNotification redoAnalystEventNotification;
	private AnalystConfigurationController analystConfigurationController;
	private Command command;
	public static RedoAnalystEventNotification getInstance(Context context) {
		if (redoAnalystEventNotification == null) {
			redoAnalystEventNotification = new RedoAnalystEventNotification(context);
		}
		return redoAnalystEventNotification;
	}
	@Override
	public void create(String title, String text, Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			CharSequence name = "REDO_CHANEL";
			String description = "REDO_CHANEL_DESC";
			NotificationChannel channel = new NotificationChannel("RedoChanel", name,
					NotificationManager.IMPORTANCE_HIGH);
			channel.setDescription(description);
			NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);
			Intent undoIntent = new Intent(context, AnalystBroadcastReceiver.class);
			undoIntent.setAction("REDO");
			Intent closeIntent = new Intent(context, AnalystBroadcastReceiver.class);
			closeIntent.setAction("REDO-CLOSE");
			NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "RedoChanel")
					.setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(title).setContentText(text)
					.setAutoCancel(true)
					.addAction(R.drawable.ic_launcher_background, "REDO",
							PendingIntent.getBroadcast(context, 0, undoIntent, 0))
					.addAction(R.drawable.ic_launcher_background, "CLOSE",
							PendingIntent.getBroadcast(context, 0, closeIntent, 0))
					.setTimeoutAfter(
							(long) analystConfigurationController.getAnalystConfiguration().getNotificationTime()
									* 1000)
					.setPriority(NotificationCompat.PRIORITY_HIGH);
			NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
			notificationManagerCompat.notify(2, builder.build());
		}
	}
	private RedoAnalystEventNotification(Context context) {
		analystConfigurationController = new AnalystConfigurationController(context);
	}
	public void setCommand(Command command) {
		this.command = command;
	}
	public Command getCommand() {
		return this.command;
	}
}