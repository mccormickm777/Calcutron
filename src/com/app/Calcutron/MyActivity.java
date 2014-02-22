package com.app.Calcutron;

import java.util.ArrayList;

import com.app.Calcutron.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
	private TextView historyTextView;
	private TextView answerTextView;
	private boolean equalsPressed;
	private float prevAnswer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	    historyTextView = (TextView) findViewById(R.id.tHistory);
	    answerTextView = (TextView) findViewById(R.id.tAnswer);
	    equalsPressed = true;
	    prevAnswer = 0;
    }
    
    public void one(View view) {
    	clearHistoryAfterEquals();
    	historyTextView.setText(historyTextView.getText() + "1");
    }
    public void two(View view) {
    	clearHistoryAfterEquals();
    	historyTextView.setText(historyTextView.getText() + "2");
    }
    public void three(View view) {
    	clearHistoryAfterEquals();
    	historyTextView.setText(historyTextView.getText() + "3");
    }
    public void four(View view) {
    	clearHistoryAfterEquals();
    	historyTextView.setText(historyTextView.getText() + "4");
    }
    public void five(View view) {
    	clearHistoryAfterEquals();
    	historyTextView.setText(historyTextView.getText() + "5");
    }
    public void six(View view) {
    	clearHistoryAfterEquals();
    	historyTextView.setText(historyTextView.getText() + "6");
    }
    public void seven(View view) {
    	clearHistoryAfterEquals();
    	historyTextView.setText(historyTextView.getText() + "7");
    }
    public void eight(View view) {
    	clearHistoryAfterEquals();
    	historyTextView.setText(historyTextView.getText() + "8");
    }
    public void nine(View view) {
    	clearHistoryAfterEquals();
    	historyTextView.setText(historyTextView.getText() + "9");
    }
    public void zero(View view) {
    	clearHistoryAfterEquals();
    	historyTextView.setText(historyTextView.getText() + "0");
    }
    public void period(View view) {
    	clearHistoryAfterEquals();
    	historyTextView.setText(historyTextView.getText() + ".");
    }
    public void plus(View view) {
    	clearHistoryAfterEquals();
    	recallPrevAnswer();
    	historyTextView.setText(historyTextView.getText() + "+");
    }
    public void subtract(View view) {
    	clearHistoryAfterEquals();
    	recallPrevAnswer();
    	historyTextView.setText(historyTextView.getText() + "-");
    }
    public void multiply(View view) {
    	clearHistoryAfterEquals();
    	recallPrevAnswer();
    	historyTextView.setText(historyTextView.getText() + "*");
    }
    public void divide(View view) {
    	clearHistoryAfterEquals();
    	recallPrevAnswer();
    	historyTextView.setText(historyTextView.getText() + "/");
    }
    public void clear(View view) {
    	historyTextView.setText("");
    }
    public void clearHistoryAfterEquals() {
    	if (equalsPressed)
    		historyTextView.setText("");
    	equalsPressed = false;
    }
    public void recallPrevAnswer() {
    	if (historyTextView.getText().equals("")) {
    		historyTextView.setText("ans");
    	}
    }
    public void backspace(View view) {
    	clearHistoryAfterEquals();
    	int length = historyTextView.getText().length();
    	if (length > 0) {
        	if (length == 1)
        	{
        		historyTextView.setText("");
        	}
        	else {
            	historyTextView.setText(historyTextView.getText().subSequence(0, length-1));
        	}
    	}
    }
    
    public void equals(View view) {
    	if (!equalsPressed) {
	    	CharSequence history = historyTextView.getText();
	    	ArrayList<Character> operatorAL = new ArrayList<Character>();
	    	ArrayList<Float> numberAL = new ArrayList<Float>();
	    	StringBuilder temp = new StringBuilder("");
	    	StringBuilder prev = new StringBuilder("");
	    	int tempPeriodCount = 0;
	    	CharSequence errorText = "";
	    	//answerTextView.setText("Got Here");
	    	for (int i = 0; i < history.length(); i++)
	    	{
	    		char character = history.charAt(i);
	    		if (i == history.length() - 1) {
	    			if (isOperator(character)) {
	    				errorText = "Error: Ends in Operator";
	    				break;
	    			} else if (character == '.') {
	    				errorText = "Error: Ends in Period";
	    				break;
	    			}
	    		}
	    		if (isOperator(character)) {
	    			if (prev.length() == 0) {
	    				//historyTextView.setText("ans" + character);
	    				//errorText = "Error: Starts with Operator";
	    				//break;
	    			}
	    			else if (isOperator(prev.charAt(0))) {
	    				errorText = "Error: Adjacent Operators";
	    				break;
	    			}
	    			else {
	        			prev.delete(0, prev.length());
	        			prev.append(character);
	        			operatorAL.add(character);
	        			//System.out.println(Float.parseFloat(temp.toString()));
	        			if (temp.toString().equals("ans")) {
	        				numberAL.add(prevAnswer);
	        			} else {
		        			numberAL.add(Float.parseFloat(temp.toString()));
	        			}
	        			tempPeriodCount = 0;
	        			temp.delete(0, temp.length());
	    			}
	    		}
	    		else {
	    			if (character == '.')
	    			{
	    				tempPeriodCount++;
		    			if (tempPeriodCount > 1) {
		    				errorText = "Error: Double Period";
		    				break;
		    			}
	    			}
	    			temp.append(character);
	    			if (prev.length() != 0) {
	    				prev.delete(0, prev.length());
	    			}
        			prev.append(character);
        			if (i == history.length() - 1) {
	        			if (temp.equals("ans")) {
	        				numberAL.add(prevAnswer);
	        			} else {
		        			numberAL.add(Float.parseFloat(temp.toString()));
	        			}
        			}
	    		}
	    	}
	    	
	    	if (errorText.length() == 0) {
	        	float total = numberAL.get(0);
	        	for (int i = 0; i < operatorAL.size(); i++) {
	        		char nextOperator = operatorAL.get(i);
	        		float nextNumber = numberAL.get(i + 1);
	        		if (nextOperator == '+') {
	        			total = total + nextNumber;
	        		} else if (nextOperator == '-') {
	        			total = total - nextNumber;
	        		} else if (nextOperator == '*') {
	         			total = total * nextNumber;
	         		} else if (nextOperator == '/') {
	         			if (nextNumber == 0) {
	         				answerTextView.setText("Error: Divide by Zero");
	         				break;
	         			}
	        			total = total / nextNumber;
	        		}
	        	}
	        	prevAnswer = total;
	        	answerTextView.setText(Float.valueOf(total).toString());
	    	} else {
	    		answerTextView.setText(errorText);
	    	}
	
	    	equalsPressed = true;
    	}
    }
    
    public boolean isOperator(char character) {
    	if (character == '+' || character == '-'|| character == '*'|| character == '/')
    		return true;
    	else
    		return false;
    }
}
