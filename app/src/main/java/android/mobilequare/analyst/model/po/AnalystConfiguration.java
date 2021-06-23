package android.mobilequare.analyst.model.po;

public class AnalystConfiguration extends ClassConcept {
	//ATTRIBUTES 
	private boolean createsProjectUndoRedoNotification;
	private boolean createsProjectConfirmationMessage;
	private boolean establishesQuestionSetUndoRedoNotification;
	private boolean establishesQuestionSetConfirmationMessage;
	private boolean providesDiscourseUndoRedoNotification;
	private boolean providesDiscourseConfirmationMessage;
	private boolean asksQuestionUndoRedoNotification;
	private boolean asksQuestionConfirmationMessage;
	private boolean leftHandView;
	private double notificationTime;
	//CONSTRUCTOR
	public AnalystConfiguration() {
		this.createsProjectUndoRedoNotification = true;
		this.createsProjectConfirmationMessage = true;
		this.establishesQuestionSetUndoRedoNotification = true;
		this.establishesQuestionSetConfirmationMessage = true;
		this.providesDiscourseUndoRedoNotification = true;
		this.providesDiscourseConfirmationMessage = true;
		this.asksQuestionUndoRedoNotification = true;
		this.asksQuestionConfirmationMessage = true;
		this.leftHandView = false;
		this.notificationTime = 10;
	}
	//OPERATIONS
	public void setCreatesProjectUndoRedoNotification(boolean newCreatesProjectUndoRedoNotification) {
		this.createsProjectUndoRedoNotification = newCreatesProjectUndoRedoNotification;
	}
	public boolean getCreatesProjectUndoRedoNotification() {
		return this.createsProjectUndoRedoNotification;
	}
	public void setCreatesProjectConfirmationMessage(boolean newCreatesProjectConfirmationMessage) {
		this.createsProjectConfirmationMessage = newCreatesProjectConfirmationMessage;
	}
	public boolean getCreatesProjectConfirmationMessage() {
		return createsProjectConfirmationMessage;
	}
	public void setEstablishesQuestionSetUndoRedoNotification(boolean newEstablishesQuestionSetUndoRedoNotification) {
		this.establishesQuestionSetUndoRedoNotification = newEstablishesQuestionSetUndoRedoNotification;
	}
	public boolean getEstablishesQuestionSetUndoRedoNotification() {
		return this.establishesQuestionSetUndoRedoNotification;
	}
	public void setEstablishesQuestionSetConfirmationMessage(boolean newEstablishesQuestionSetConfirmationMessage) {
		this.establishesQuestionSetConfirmationMessage = newEstablishesQuestionSetConfirmationMessage;
	}
	public boolean getEstablishesQuestionSetConfirmationMessage() {
		return establishesQuestionSetConfirmationMessage;
	}
	public void setProvidesDiscourseUndoRedoNotification(boolean newProvidesDiscourseUndoRedoNotification) {
		this.providesDiscourseUndoRedoNotification = newProvidesDiscourseUndoRedoNotification;
	}
	public boolean getProvidesDiscourseUndoRedoNotification() {
		return this.providesDiscourseUndoRedoNotification;
	}
	public void setProvidesDiscourseConfirmationMessage(boolean newProvidesDiscourseConfirmationMessage) {
		this.providesDiscourseConfirmationMessage = newProvidesDiscourseConfirmationMessage;
	}
	public boolean getProvidesDiscourseConfirmationMessage() {
		return providesDiscourseConfirmationMessage;
	}
	public void setAsksQuestionUndoRedoNotification(boolean newAsksQuestionUndoRedoNotification) {
		this.asksQuestionUndoRedoNotification = newAsksQuestionUndoRedoNotification;
	}
	public boolean getAsksQuestionUndoRedoNotification() {
		return this.asksQuestionUndoRedoNotification;
	}
	public void setAsksQuestionConfirmationMessage(boolean newAsksQuestionConfirmationMessage) {
		this.asksQuestionConfirmationMessage = newAsksQuestionConfirmationMessage;
	}
	public boolean getAsksQuestionConfirmationMessage() {
		return asksQuestionConfirmationMessage;
	}
	public void setLeftHandView(boolean newLeftHandView) {
		this.leftHandView = newLeftHandView;
	}
	public boolean getLeftHandView() {
		return leftHandView;
	}
	public void setNotificationTime(double newNotificationTime) {
		this.notificationTime = newNotificationTime;
	}
	public double getNotificationTime() {
		return notificationTime;
	}
}