package com.example.thomas.rolediceapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;


public class RollDiceActivity extends ActionBarActivity {
    
    private AttackInstance attackInstance;
    private EditText attackEditText;
    private EditText defenceEditText;
    private Random random;
    private TextView outputTextArea;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_dice);
        attackEditText = (EditText)findViewById(R.id.attackPlayers);
        defenceEditText = (EditText)findViewById(R.id.defencePlayers);
        random = new Random();
        outputTextArea = (TextView)findViewById(R.id.OutputTextArea);
        scrollView = (ScrollView)findViewById(R.id.OutputScrollArea);
    }
    
    public void Cancel(View view){
        attackInstance = null;
        attackEditText.setEnabled(true);
        attackEditText.setText("12");
        defenceEditText.setEnabled(true);
        defenceEditText.setText("5");
        outputTextArea.setText("");
    }

    public void RunOnce(View view){
        if ( attackInstance == null){
            InitiateAttack(Integer.parseInt(defenceEditText.getText().toString()),Integer.parseInt(attackEditText.getText().toString()));
        }
        if (attackInstance.CanAttack()){
            attackInstance.Roll();
        }
    }

    public void RunAll(View view){
        if ( attackInstance == null){
            InitiateAttack(Integer.parseInt(defenceEditText.getText().toString()),Integer.parseInt(attackEditText.getText().toString()));
        }
//        while (attackInstance.CanAttack()){
//            attackInstance.Roll();
//        }
    }

    private void InitiateAttack(int defencePlayers, int attackPlayers){
        attackInstance = new AttackInstance(defencePlayers,attackPlayers,this);
        attackEditText.setEnabled(false);
        defenceEditText.setEnabled(false);
    }

    public int NextDiceRoll(){
        return random.nextInt(5) + 1;
    }

    public void PrintToActivity(String message){
        outputTextArea.append(message + "\n");
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_roll_dice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
