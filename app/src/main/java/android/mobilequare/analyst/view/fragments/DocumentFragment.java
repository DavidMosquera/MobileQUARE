package android.mobilequare.analyst.view.fragments; //$A
import android.mobilequare.analyst.R;//$A
import android.net.Uri;//$A
import android.os.Bundle;//$A
import android.view.LayoutInflater;//$A
import android.view.MotionEvent;//$A
import android.view.View;//$A
import android.view.ViewGroup;//$A
import android.widget.EditText;//$A
import android.widget.TextView;//$A
import androidx.activity.result.ActivityResultCallback;//$A
import androidx.activity.result.ActivityResultLauncher;//$A
import androidx.activity.result.contract.ActivityResultContracts;//$A
import androidx.fragment.app.Fragment;//$A
import com.itextpdf.text.pdf.PdfReader;//$A
import com.itextpdf.text.pdf.parser.PdfTextExtractor;//$A
import java.io.IOException;//$A
import java.net.MalformedURLException;//$A
public class DocumentFragment extends Fragment implements View.OnTouchListener {//$A
    private String path;//$A
    private String text = "";//$A
    private String title;//$A
    private EditText textView;//$A
    private TextView textViewError;//$A
    private ActivityResultLauncher<String> getDocument;//$A
    public DocumentFragment() {    }//$A
    public static DocumentFragment newInstance(String title) {//$A
        DocumentFragment fragment = new DocumentFragment();//$A
        fragment.setTitle(title);//$A
        return fragment;//$A
    }//$A
    public void setTitle(String title){
        this.title = title;
    }//$A
    @Override//$A
    public void onCreate(Bundle savedInstanceState) {//$A
        super.onCreate(savedInstanceState);//$A
        getDocument = registerForActivityResult(new ActivityResultContracts.GetContent(),//$A
                new ActivityResultCallback<Uri>() {//$A
                    @Override//$A
                    public void onActivityResult(Uri uri) {//$A
                        if(uri!=null){//$A
                            try {//$A
                                PdfReader reader = new PdfReader(getActivity().getContentResolver().openInputStream(uri));//$A
                                int n = reader.getNumberOfPages();//$A
                                String str = "";//$A
                                for (int i = 1; i <= n; i++) {//$A
                                    str += PdfTextExtractor.getTextFromPage(reader, i);//$A
                                }//$A
                                reader.close();//$A
                                path = uri.getPath().split("/")[uri.getPath().split("/").length-1];//$A
                                text = str;//$A
                                textView.setText(path);//$A
                            } catch (MalformedURLException e) {//$A
                                textViewError.setVisibility(View.VISIBLE);//$A
                                textViewError.setText("An error has occurred opening the selected discourse.");//$A
                            } catch (IOException e) {//$A
                                textViewError.setVisibility(View.VISIBLE);//$A
                                textViewError.setText("An error has occurred opening the selected discourse.");//$A
                            }//$A
                        }//$A
                    }//$A
                });//$A
    }//$A
    @Override//$A
    public View onCreateView(LayoutInflater inflater, ViewGroup container,//$A
                             Bundle savedInstanceState) {//$A
        View rootView = inflater.inflate(R.layout.fragment_document, container, false);//$A
        textView = rootView.findViewById(R.id.input_document_type_fragment);//$A
        textView.setHint("Select a PDF file containing a project discourse");//$A
        textViewError = rootView.findViewById(R.id.info_document_type_fragment);//$A
        textView.setClickable(true);//$A
        textView.setOnTouchListener(this);//$A
        ((TextView) rootView.findViewById(R.id.label_document_type_name_fragment)).setText(this.title);//$A
        return rootView;//$A
    }//$A
    public String getValue(){
        return this.text;
    }//$A
    @Override//$A
    public boolean onTouch(View v, MotionEvent event) {//$A
        if(MotionEvent.ACTION_UP == event.getAction()) getDocument.launch("application/pdf");//$A
        return false;//$A
    }//$A
    public void newInfo(String info) {//$A
        textViewError.setText(info);//$A
        textViewError.setVisibility(View.VISIBLE);//$A
    }//$A
}//$A