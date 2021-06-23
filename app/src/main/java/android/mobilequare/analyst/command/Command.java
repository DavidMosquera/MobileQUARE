package android.mobilequare.analyst.command;
public interface Command {
	void undo() throws Exception;
	void redo() throws Exception;
}
