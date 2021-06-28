package android.mobilequare.analyst.view.fragments; //$A
import android.app.AlertDialog; //$A
import android.content.DialogInterface; //$A
import android.mobilequare.analyst.R; //$A
import android.mobilequare.analyst.controller.AsksQuestionController;
import android.mobilequare.analyst.model.po.Actor;//$A
import android.mobilequare.analyst.model.po.Concept;//$A
import android.mobilequare.analyst.model.po.Function;//$A
import android.mobilequare.analyst.model.po.Project;//$A
import android.mobilequare.analyst.model.po.QuestionSet;//$A
import android.mobilequare.analyst.view.AsksQuestionView;//$A
import android.os.Bundle;//$A
import android.view.LayoutInflater;//$A
import android.view.View;//$A
import android.view.ViewGroup;//$A
import android.widget.Button;//$A
import android.widget.ImageView;//$A
import android.widget.TextView;//$A
import androidx.fragment.app.Fragment;//$A
import java.util.List;//$A
public class ConceptFragment extends Fragment {//$A
    private Concept concept;//$A
    private Function function;//$A
    private Actor actor;//$A
    private android.mobilequare.analyst.model.po.Object object;//$A
    private int type;//$A
    private Project project;//$A
    private AlertDialog askDialog;//$A
    private int selected;//$A
    private AsksQuestionController asksQuestionController; //$A
    private QuestionSet questionSet;//$A
    private String questionTitle; //$A
    public ConceptFragment() {//$A
    }//$A
    public static ConceptFragment newInstance(Project project, QuestionSet questionSet, AsksQuestionController asksQuestionController){//$A
        ConceptFragment fragment = new ConceptFragment();//$A
        fragment.setType(0);//$A
        fragment.setAsksQuestionController(asksQuestionController); //$A
        fragment.setProject(project);//$A
        fragment.setQuestionSet(questionSet); //$A
        return fragment;//$A
    }//$A
    public static ConceptFragment newInstance(Function function, Actor actor , android.mobilequare.analyst.model.po.Object object, Project project, AsksQuestionController asksQuestionController, QuestionSet questionSet){//$A
        ConceptFragment fragment = new ConceptFragment();//$A
        fragment.setFunction(function);//$A
        fragment.setAsksQuestionController(asksQuestionController); //$A
        fragment.setType(1);//$A
        fragment.setProject(project);//$A
        fragment.setActor(actor);//$A
        fragment.setObject(object);//$A
        fragment.setQuestionSet(questionSet); //$A
        return fragment;//$A
    }//$A
    public static ConceptFragment newInstance(Function function, Project project, AsksQuestionController asksQuestionController, QuestionSet questionSet){//$A
        ConceptFragment fragment = new ConceptFragment();//$A
        fragment.setFunction(function);//$A
        fragment.setType(1);//$A
        fragment.setAsksQuestionController(asksQuestionController); //$A
        fragment.setProject(project);//$A
        fragment.setQuestionSet(questionSet); //$A
        return fragment;//$A
    }//$A
    public static ConceptFragment newInstance(Concept concept, Project project, AsksQuestionController asksQuestionController, QuestionSet questionSet) {//$A
        ConceptFragment fragment = new ConceptFragment();//$A
        fragment.setConcept(concept);//$A
        fragment.setType(2);//$A
        fragment.setAsksQuestionController(asksQuestionController); //$A
        fragment.setProject(project);//$A
        fragment.setQuestionSet(questionSet); //$A
        return fragment;//$A
    }//$A
    @Override//$A
    public void onCreate(Bundle savedInstanceState) {//$A
        super.onCreate(savedInstanceState);//$A
    }//$A
    @Override//$A
    public View onCreateView(LayoutInflater inflater, ViewGroup container,//$A
                             Bundle savedInstanceState) {//$A
        View rootView = inflater.inflate(R.layout.fragment_concept, container, false);//$A
        ImageView imageView = rootView.findViewById(R.id.image_concept_function);//$A
        this.selected = -1;//$A
        ((Button)rootView.findViewById(R.id.ask_button)).setOnClickListener(new View.OnClickListener() {//$A
            @Override//$A
            public void onClick(View v) {//$A
                ask(v);//$A
            }//$A
        });//$A
        if(this.type == 1){//$A
            imageView.setImageResource(R.drawable.ic_software);//$A
            ((TextView)rootView.findViewById(R.id.concept_name_text_view)).setText(function.getActionVerb() + " " + object.getName());//$A
        }else if (this.type == 2){//$A
            imageView.setImageResource(R.drawable.ic_concepto);//$A
            ((TextView)rootView.findViewById(R.id.concept_name_text_view)).setText(concept.getName()); //$A
        }else{//$A
            imageView.setImageResource(R.drawable.ic_configuration);//$A
            ((TextView)rootView.findViewById(R.id.concept_name_text_view)).setText(project.getName()); //$A
        }//$A
        return rootView;//$A
    }//$A
    public void setObject(android.mobilequare.analyst.model.po.Object object){this.object = object;}//$A
    public void setActor(Actor actor){this.actor = actor;}//$A
    public void setConcept(Concept concept){//$A
        this.concept = concept;//$A
    }//$A
    public void setFunction(Function function){//$A
        this.function = function;//$A
    }//$A
    public void setType(int type){//$A
        this.type = type;//$A
    }//$A
    public void setQuestionSet (QuestionSet questionSet){this.questionSet = questionSet;}//$A
    public void setProject(Project project){//$A
        this.project = project;//$A
    }//$A
    public void setAsksQuestionController(AsksQuestionController asksQuestionController){ this.asksQuestionController = asksQuestionController; } //$A
    public void askConfirm(){//$A
        askDialog.cancel();//$A
        String questionType = "";//$A
        boolean actor = false; //$A
        if(this.selected != -1) {//$A
            if (this.type == 0){ if (this.selected == 0) questionType = "Which are the project's discourse concepts?"; else questionType = "Which are the project's discourse actors?";}//$A
            else if(this.type == 1){if (this.selected == 0){ actor = true; questionType = "Which are the X's attributes?";} else if (this.selected == 1){ questionType = "Which are the X's attributes?";} else{ questionType = "Which are the X's functions?";}}//$A
            else{if(this.selected == 0) questionType = "Which are the X's attributes?"; else questionType = "Which are the X's functions?";}//$A
            if(this.type == 1){ if (actor) asksQuestionController.asksQuestion(((AsksQuestionView) getActivity()), questionSet, questionType, this.actor, questionTitle); else asksQuestionController.asksQuestion(((AsksQuestionView) getActivity()), questionSet, questionType, this.object, questionTitle); }else{ asksQuestionController.asksQuestion(((AsksQuestionView) getActivity()), questionSet, questionType, this.concept, questionTitle); } //$A
        }//$A
    }//$A
    public void askCancel(){//$A
        askDialog.cancel();//$A
    }//$A
    public void ask(View view){//$A
        CharSequence[] items;//$A
        if(this.type == 0) {//$A
            items = new CharSequence[]{"Which are the project's discourse concepts?", "Which are the project's discourse actors?"};//$A
        }else if(this.type == 1) {//$A
            items = new CharSequence[]{"Which are the "+this.actor.getName()+"'s attributes?", "Which are the "+this.object.getName()+"'s attributes?", "Which are the "+this.object.getName()+"'s functions?"};//$A
        }else{//$A
            items = new CharSequence[]{"Which are the "+this.concept.getName()+"'s attributes?", "Which are the "+this.concept.getName()+"'s functions?"};//$A
        }//$A
        askDialog = new AlertDialog.Builder(getContext()).setTitle("What do you want to ask?")//$A
        .setSingleChoiceItems(items, -1,new DialogInterface.OnClickListener() {//$A
            @Override//$A
            public void onClick(DialogInterface dialog, int which) {//$A
                selected = which;//$A
                questionTitle = items[which].toString();//$A
            }//$A
        }).setPositiveButton("Ask", new DialogInterface.OnClickListener() {//$A
            @Override//$A
            public void onClick(DialogInterface dialog, int id) {
                askConfirm();
            }//$A
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {//$A
            @Override//$A
            public void onClick(DialogInterface dialog, int id) {//$A
                askCancel();//$A
            }//$A
        }).create();//$A
        askDialog.show();//$A
    }//$A
}//$A