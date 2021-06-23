package android.mobilequare.analyst;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.mobilequare.analyst.R;
import android.mobilequare.analyst.view.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class AnalystMenuView extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_analyst_menu);
		setTitle("MobileQUARE: Analyst Menu");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.role_menu_right_layout, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.role_menu_configuration :
				configuration();
				return true;
			case R.id.role_menu_help :
				help();
				return true;
			default :
				return super.onOptionsItemSelected(item);
		}
	}
	public void analystCreatesProjectTransition(View view) {
		startActivity(new Intent(this, CreatesProjectView.class));
	}
	public void analystEstablishesQuestionSetTransition(View view) {
		startActivity(new Intent(this, EstablishesQuestionSetView.class));
	}
	public void analystProvidesDiscourseTransition(View view) {
		startActivity(new Intent(this, ProvidesDiscourseView.class));
	}
	public void analystAsksQuestionTransition(View view) {
		startActivity(new Intent(this, AsksQuestionView.class));
	}

	public void help() {
		startActivity(new Intent(this, AnalystHelpView.class));
	}
	public void configuration() {
		startActivity(new Intent(this, AnalystConfigurationView.class));
	}
}
