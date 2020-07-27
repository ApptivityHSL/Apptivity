package com.example.apptivity;

import static com.example.apptivity.PersonalInformation.INPUT_NAME;
import static java.lang.String.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import java.util.ArrayList;

/**
 * The type Search 1.
 */
public class Search1 extends AppCompatActivity {
    /**
     * The constant alone.
     */
    protected static boolean alone;
    /**
     * The constant partner.
     */
    protected static boolean partner;
    /**
     * The constant family.
     */
    protected static boolean family;
    /**
     * The constant friends.
     */
    protected static boolean friends;
    /**
     * The constant budget.
     */
    protected static int budget;
    private SeekBar budgetBar;
    private TextView money;

    /**
     * The People array.
     */
    ArrayList<String> peopleArray = new ArrayList<>();

    private CheckBox inputAlone;
    private CheckBox inputPartner;
    private CheckBox inputFamily;
    private CheckBox inputFriend;
    private SeekBar inputBudget;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search1);
/**
        insertNameintoTextView();
 */
        seekbar();



        inputAlone =  findViewById(R.id.inputAlone);
        inputPartner =  findViewById(R.id.inputPartner);
        inputFamily =  findViewById(R.id.inputFamily);
        inputFriend =  findViewById(R.id.inputFriend);
        inputBudget =  findViewById(R.id.inputBudget);

        Button btSearch2 = findViewById(R.id.btSearch2);
        btSearch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                int value = inputBudget.getProgress();
                safeMoney(value);

                safePeople(peopleArray);

                alone = inputAlone.hasFocus();
                partner = inputPartner.hasFocus();
                family = inputFamily.hasFocus();
                friends = inputFriend.hasFocus();
                budget = 0;

                openSearch2();
            }

        });
    }

    /**
     * Open search 2.
     */
    public void openSearch2() {
        Intent intent = new Intent(this, Search2.class);
        startActivity(intent);
    }

    private void seekbar() {
        budgetBar = findViewById(R.id.inputBudget);
        money = findViewById(R.id.money);
        money.setText(format("%d€ / %d€", budgetBar.getProgress(), budgetBar.getMax()));


        budgetBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                        budget = progress;
                        money.setText(format("%d€ / %d€", progress, budgetBar.getMax()));
                    }

                    @Override
                    public void onStartTrackingTouch(final SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(final SeekBar seekBar) {
                        money.setText(format("%d€ / %d€", budget, budgetBar.getMax()));
                    }
                }
        );
    }

/**
    private void insertNameintoTextView() {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserIn", MODE_PRIVATE);
        String text = "Hallo " + mSharedPreferences.getString(INPUT_NAME, "") + "!";
        TextView greetings2 = findViewById(R.id.tvGreetings2);
        greetings2.setText(text);
    }
*/
    private void safePeople(final ArrayList<String> people) {
        SharedPreferences sharedPreferences = getSharedPreferences("people", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String Json = gson.toJson(people);
        editor.putString("people", Json);
        editor.apply();
    }

    private void safeMoney(final int i) {
        SharedPreferences sharedPreferences = getSharedPreferences("money", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("money", i);
        editor.apply();
    }

    /**
     * On checkbox clicked.
     *
     * @param view the view
     */
    public void onCheckboxClicked(final View view) {
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.inputAlone:
                if (checked) {
                    peopleArray.add((String) inputAlone.getText());
                } else {
                    peopleArray.remove(inputAlone.getText());
                }
                break;

            case R.id.inputPartner:
                if (checked) {
                    peopleArray.add((String) inputPartner.getText());
                } else {
                    peopleArray.remove(inputPartner.getText());
                }
                break;

            case R.id.inputFamily:
                if (checked) {
                    peopleArray.add((String) inputFamily.getText());
                } else {
                    peopleArray.remove(inputFamily.getText());
                }
                break;

            case R.id.inputFriend:
                if (checked) {
                    peopleArray.add((String) inputFriend.getText());
                } else {
                    peopleArray.remove(inputFriend.getText());
                }
                break;
            default:

        }



    }


}
