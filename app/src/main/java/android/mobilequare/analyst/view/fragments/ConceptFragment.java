package android.mobilequare.analyst.view.fragments; //$A
import android.app.AlertDialog; //$A
import android.content.DialogInterface; //$A
import android.mobilequare.analyst.R; //$A
import android.mobilequare.analyst.model.po.Actor;//$A
import android.mobilequare.analyst.model.po.Concept;//$A
import android.mobilequare.analyst.model.po.Function;//$A
import android.os.Bundle;//$A
import android.view.LayoutInflater;//$A
import android.view.View;//$A
import android.view.ViewGroup;//$A
import android.widget.Button;//$A
import android.widget.ImageView;//$A
import android.widget.TextView;//$A
import androidx.fragment.app.Fragment;//$A
public class ConceptFragment extends Fragment {//$A
    private Concept concept;//$A
    private Function function;//$A
    private Actor actor;//$A
    private android.mobilequare.analyst.model.po.Object object;//$A
    private int type;//$A
    private String project;//$A
    private AlertDialog askDialog;//$A
    private int selected;//$A
    public ConceptFragment() {//$A
    }//$A
    public static ConceptFragment newInstance(String project){//$A
        ConceptFragment fragment = new ConceptFragment();//$A
        fragment.setType(0);//$A
        fragment.setProject(project);//$A
        return fragment;//$A
    }//$A
    public static ConceptFragment newInstance(Function function, Actor actor , android.mobilequare.analyst.model.po.Object object, String project){//$A
        ConceptFragment fragment = new ConceptFragment();//$A
        fragment.setFunction(function);//$A
        fragment.setType(1);//$A
        fragment.setProject(project);//$A
        fragment.setActor(actor);//$A
        fragment.setObject(object);//$A
        return fragment;//$A
    }//$A
    public static ConceptFragment newInstance(Function function, String project){//$A
        ConceptFragment fragment = new ConceptFragment();//$A
        fragment.setFunction(function);//$A
        fragment.setType(1);//$A
        fragment.setProject(project);//$A
        return fragment;//$A
    }//$A
    public static ConceptFragment newInstance(Concept concept, String project) {//$A
        ConceptFragment fragment = new ConceptFragment();//$A
        fragment.setConcept(concept);//$A
        fragment.setType(2);//$A
        fragment.setProject(project);//$A
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
            ((TextView)rootView.findViewById(R.id.concept_name_text_view)).setText(project); //$A
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
    public void setProject(String project){//$A
        this.project = project;//$A
    }//$A
    public void askConfirm(){//$A
        askDialog.cancel();//$A
        String questionType = "";//$A
        if(this.selected != -1) {//$A
            if (this.type == 0){ if (this.selected == 0) questionType = "Which are the project's discourse concepts?"; else questionType = "Which are the project's discourse actors?";}//$A
            else if(this.type == 1){if (this.selected == 0 || this.selected == 1) questionType = "Which are the X's attributes?"; else questionType = "Which are the X's functions?";}//$A
            else{if(this.selected == 0) questionType = "Which are the X's attributes?"; else questionType = "Which are the X's functions?";}//$A
        }//$A
        System.out.println(questionType);//$A
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