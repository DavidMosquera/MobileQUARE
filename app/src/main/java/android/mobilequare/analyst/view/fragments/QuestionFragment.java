package android.mobilequare.analyst.view.fragments;//$A
import android.mobilequare.analyst.R;//$A
import android.os.Bundle;//$A
import android.view.LayoutInflater;//$A
import android.view.View;//$A
import android.view.ViewGroup;//$A
import android.widget.LinearLayout;//$A
import android.widget.TextView;//$A
import androidx.fragment.app.Fragment;//$A
import java.util.List;//$A
public class QuestionFragment extends Fragment {//$A
    private List<ConceptFragment> conceptFragmentList;//$A
    private String title;//$A
    private String answer = "";//$A
    public QuestionFragment() {    }//$A
    public static QuestionFragment newInstance(List<ConceptFragment> conceptFragmentList, String title) {//$A
        QuestionFragment fragment = new QuestionFragment();//$A
        fragment.setConceptFragmentList(conceptFragmentList);//$A
        fragment.setTitle(title);//$A
        return fragment;//$A
    }//$A
    public static QuestionFragment newInstance(String title, String answer) {//$A
        QuestionFragment fragment = new QuestionFragment();//$A
        fragment.setAnswer(answer.compareTo("")==0 ? "None" : answer);//$A
        fragment.setTitle(title);//$A
        return fragment;//$A
    }//$A
    @Override//$A
    public void onCreate(Bundle savedInstanceState) {//$A
        super.onCreate(savedInstanceState);//$A
    }//$A
    @Override//$A
    public View onCreateView(LayoutInflater inflater, ViewGroup container,//$A
                             Bundle savedInstanceState) {//$A
        View rootView =  inflater.inflate(R.layout.fragment_question, container, false);//$A
        if(this.answer.compareTo("")!=0){//$A
            rootView.findViewById(R.id.none_answer_text_view).setVisibility(View.VISIBLE);//$A
            ((TextView)rootView.findViewById(R.id.none_answer_text_view)).setText(this.answer);//$A
        }else{//$A
            if(this.conceptFragmentList != null) {//$A
                if (this.conceptFragmentList.size() == 0)//$A
                    rootView.findViewById(R.id.none_answer_text_view).setVisibility(View.VISIBLE);//$A
                else {//$A
                    LinearLayout linearLayout = rootView.findViewById(R.id.concept_function_fragment_linear_layout);//$A
                    for (ConceptFragment conceptFragment : this.conceptFragmentList) {//$A
                        getChildFragmentManager().beginTransaction().add(linearLayout.getId(), conceptFragment).commit();//$A
                    }//$A
                }//$A
            }//$A
        }//$A
        TextView textView = rootView.findViewById(R.id.question_title_view);//$A
        textView.setText(this.title);//$A
        return rootView;//$A
    }//$A
    public void setConceptFragmentList(List<ConceptFragment> conceptFragmentList){//$A
        this.conceptFragmentList = conceptFragmentList;//$A
    }//$A
    public void setTitle(String title){//$A
        this.title = title;//$A
    }//$A
    public void setAnswer(String answer){//$A
        this.answer = answer;//$A
    }//$A
}//$A