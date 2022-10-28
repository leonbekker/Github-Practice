package leon.bekker.animationpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button finalize;
    RadioGroup radioG1, radioG2, radioG3;
    RadioButton[] rbtn1 = new RadioButton[3];
    RadioButton[] rbtn2 = new RadioButton[3];
    RadioButton[] rbtn3 = new RadioButton[3];
    TextView headline;
    private String id;
    Dialog popupDialog;
    AlertDialog.Builder adb_close;
    ImageButton popup_btn_close;
    LottieAnimationView lottieAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        finalize = (Button) findViewById(R.id.finalize);
        radioG1 = (RadioGroup) findViewById(R.id.radioG1);
        radioG2 = (RadioGroup) findViewById(R.id.radioG2);
        radioG3 = (RadioGroup) findViewById(R.id.radioG3);
        headline = (TextView) findViewById(R.id.headline);

        for (int i = 0; i < rbtn1.length; i++) {
            id = "q1_Option" + (i+1);
            rbtn1[i] = (RadioButton) findViewById(getResources().getIdentifier(id, "id", getPackageName()));
            rbtn1[i].setOnClickListener(this::onClick);
            id = "q2_Option" + (i+1);
            rbtn2[i] = (RadioButton) findViewById(getResources().getIdentifier(id, "id", getPackageName()));
            rbtn2[i].setOnClickListener(this::onClick);
            id = "q3_Option" + (i+1);
            rbtn3[i] = (RadioButton) findViewById(getResources().getIdentifier(id, "id", getPackageName()));
            rbtn3[i].setOnClickListener(this::onClick);
        }

        radioG1.setOnClickListener(this::onClick);
        radioG2.setOnClickListener(this::onClick);
        radioG3.setOnClickListener(this::onClick);
        finalize.setOnClickListener(this::onClick);

        // Alert Dialog
        adb_close = new AlertDialog.Builder(this);
        adb_close.setMessage("Are you sure you want to close the application?");
        adb_close.setTitle("BEFORE CLOSING THE APPLICATION");
        adb_close.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        adb_close.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //no action
            }
        });
        adb_close.setIcon(android.R.drawable.ic_lock_power_off);
        adb_close.setCancelable(false);
        //
    }

    private void onClick(View v) {
        boolean radioButton_pressed = false;
        if (v == finalize) {
            int selected1 = radioG1.getCheckedRadioButtonId();
            int selected2 = radioG2.getCheckedRadioButtonId();
            int selected3 = radioG3.getCheckedRadioButtonId();
            if (selected1 != -1 && selected2 != -1 && selected3 != -1) {
                Button btn1, btn2, btn3;
                btn1 = (RadioButton) findViewById(selected1);
                btn2 = (RadioButton) findViewById(selected2);
                btn3 = (RadioButton) findViewById(selected3);
                //  popup Dialog
                popupDialog = new Dialog(MainActivity.this);
                popupDialog.setContentView(R.layout.popup_dialog);
                popupDialog.getWindow().setBackgroundDrawable(getDrawable((R.drawable.data_dialog_background)));
                popupDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupDialog.setCancelable(false);
                popupDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                popupDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                popup_btn_close = (ImageButton) popupDialog.findViewById(R.id.dialog_button_close);
                lottieAnim = (LottieAnimationView) popupDialog.findViewById(R.id.animationView);
                popup_btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupDialog.dismiss();
                    }
                });
                //
                if (btn1.getText().toString().equals("מצרים") && btn2.getText().toString().equals("ירדן") && btn3.getText().toString().equals("לבנון")) {
                    Toast.makeText(this, "Well Done!", Toast.LENGTH_SHORT).show();
                    lottieAnim.setAnimation(R.raw.all_correct_anim);
                    popupDialog.show();
                }
                else {
                    Toast.makeText(this, "Try again.", Toast.LENGTH_SHORT).show();
                    lottieAnim.setAnimation(R.raw.try_again_anim);
                    popupDialog.show();
                }
            } else
                Toast.makeText(this, "Answer all questions first!", Toast.LENGTH_SHORT).show();
        }
        radioButton_pressed = false;
        for (int i = 0; i < rbtn1.length && !radioButton_pressed; i++) {
            if (v == rbtn1[i]){
                radioButton_pressed = true;
                //  popup Dialog
                popupDialog = new Dialog(MainActivity.this);
                popupDialog.setContentView(R.layout.popup_dialog);
                popupDialog.getWindow().setBackgroundDrawable(getDrawable((R.drawable.data_dialog_background)));
                popupDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupDialog.setCancelable(false);
                popupDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                popupDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                popup_btn_close = (ImageButton) popupDialog.findViewById(R.id.dialog_button_close);
                lottieAnim = (LottieAnimationView) popupDialog.findViewById(R.id.animationView);
                popup_btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupDialog.dismiss();
                    }
                });
                //
                if (rbtn1[i].getText().toString().equals("מצרים")) {
                    lottieAnim.setAnimation(R.raw.true_anim);
                } else {
                    lottieAnim.setAnimation(R.raw.false_anim);
                }
                popupDialog.show();
            }
        }
        for (int i = 0; i < rbtn2.length && !radioButton_pressed; i++) {
            if (v == rbtn2[i]){
                radioButton_pressed = true;
                //  popup Dialog
                popupDialog = new Dialog(MainActivity.this);
                popupDialog.setContentView(R.layout.popup_dialog);
                popupDialog.getWindow().setBackgroundDrawable(getDrawable((R.drawable.data_dialog_background)));
                popupDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupDialog.setCancelable(false);
                popupDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                popupDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                popup_btn_close = (ImageButton) popupDialog.findViewById(R.id.dialog_button_close);
                lottieAnim = (LottieAnimationView) popupDialog.findViewById(R.id.animationView);
                popup_btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupDialog.dismiss();
                    }
                });
                //
                if (rbtn2[i].getText().toString().equals("ירדן")) {
                    lottieAnim.setAnimation(R.raw.true_anim);
                } else {
                    lottieAnim.setAnimation(R.raw.false_anim);
                }
                popupDialog.show();
            }
        }
        for (int i = 0; i < rbtn3.length && !radioButton_pressed; i++) {
            if (v == rbtn3[i]){
                radioButton_pressed = true;
                //  popup Dialog
                popupDialog = new Dialog(MainActivity.this);
                popupDialog.setContentView(R.layout.popup_dialog);
                popupDialog.getWindow().setBackgroundDrawable(getDrawable((R.drawable.data_dialog_background)));
                popupDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupDialog.setCancelable(false);
                popupDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                popupDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                popup_btn_close = (ImageButton) popupDialog.findViewById(R.id.dialog_button_close);
                lottieAnim = (LottieAnimationView) popupDialog.findViewById(R.id.animationView);
                popup_btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupDialog.dismiss();
                    }
                });
                //
                if (rbtn3[i].getText().toString().equals("לבנון")) {
                    lottieAnim.setAnimation(R.raw.true_anim);
                } else {
                    lottieAnim.setAnimation(R.raw.false_anim);
                }
                popupDialog.show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.quit_application) {
            adb_close.show();
        }
        else if (id == R.id.reset_application) {
            radioG1.clearCheck();
            radioG2.clearCheck();
            radioG3.clearCheck();
        }
        return super.onOptionsItemSelected(item);
    }
}
