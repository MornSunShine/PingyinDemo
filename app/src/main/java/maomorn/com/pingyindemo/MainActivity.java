package maomorn.com.pingyindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.input)
    EditText input;
    @BindView(R.id.content)
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListeners();

    }

    private void initListeners() {

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString=input.getText().toString();
//                HanyuPinyinOutputFormat format=new HanyuPinyinOutputFormat();
//                format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//                format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//                format.setVCharType(HanyuPinyinVCharType.WITH_V);
                String output=PinyinUtils.getPingYin(inputString);
//                String output="";
//                try{
//                    output+=PinyinHelper.toHanYuPinyinString(inputString,format,"",true);
//                }catch (BadHanyuPinyinOutputFormatCombination e){
//                    e.printStackTrace();
//                }
                content.setText(output);
                Log.d("TAG",output);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = input.getText().toString();
                if(1==inputString.length()&&java.lang.Character.toString(
                        inputString.charAt(0)).matches("[\\u4E00-\\u9FA5]+")){
                    String output=PinyinUtils.getMorePinyin(inputString.charAt(0));
                    content.setText(output);
                    Log.d("TAG",output);
                }else{
                    Toast.makeText(MainActivity.this,"请确保有且只有一个汉语字符！！！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = input.getText().toString();
                if(1==inputString.length()&&java.lang.Character.toString(
                        inputString.charAt(0)).matches("[\\u4E00-\\u9FA5]+")){
                    String output=PinyinUtils.getWithTone(inputString.charAt(0));
                    content.setText(output);
                    Log.d("TAG",output);
                }else{
                    Toast.makeText(MainActivity.this,"请确保有且只有一个汉语字符！！！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
