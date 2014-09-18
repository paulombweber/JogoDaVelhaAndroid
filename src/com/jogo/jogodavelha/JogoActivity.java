package com.jogo.jogodavelha;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class JogoActivity extends Activity {

	private Button[][] mCampos = new Button[3][3];
	private boolean EhX;
	private byte count = 0;
	private boolean partidaFinalizada = false;
	private String vencedor = "";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        EhX = true;
        
        mCampos[0][0] = (Button)findViewById(R.id.btn01);
        mCampos[0][1] = (Button)findViewById(R.id.btn02);
        mCampos[0][2] = (Button)findViewById(R.id.btn03);
        mCampos[1][0] = (Button)findViewById(R.id.btn04);
        mCampos[1][1] = (Button)findViewById(R.id.btn05);
        mCampos[1][2] = (Button)findViewById(R.id.btn06);
        mCampos[2][0] = (Button)findViewById(R.id.btn07);
        mCampos[2][1] = (Button)findViewById(R.id.btn08);
        mCampos[2][2] = (Button)findViewById(R.id.btn09);        
        
		OnClickListener onClickListener = new OnClickListener() {

			public void onClick(View v) {
				if ( ((Button) v).getText().equals("")){
					if(partidaFinalizada){
						if (vencedor.isEmpty()){
							Toast.makeText(JogoActivity.this,"Houve um empate, Inicie uma nova Partida", Toast.LENGTH_LONG).show();
						}else{
							Toast.makeText(JogoActivity.this,"O vencedor foi jogador: " + vencedor + ", Inicie uma nova Partida", Toast.LENGTH_LONG).show();
						}
					}else{
						count++;
						EhX = !EhX;
						if (EhX)
						  ((Button) v).setText("X");
						else
						  ((Button) v).setText("O");	
						
						if (encontrouGanhador()){
							vencedor = ((Button) v).getText().toString();
							Toast.makeText(JogoActivity.this,"O vencedor foi jogador: " + vencedor + ", Inicie uma nova Partida", Toast.LENGTH_LONG).show();
							partidaFinalizada = true;
						} else {
							if(count == 9){
								partidaFinalizada = true;
								Toast.makeText(JogoActivity.this,"Houve um empate, Inicie uma nova Partida", Toast.LENGTH_LONG).show();
							}
						}
					}
				}else{
					Toast.makeText(JogoActivity.this,"Esse campo já foi utilizado em uma jogada.", Toast.LENGTH_LONG).show();
				}
			}
		};

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				mCampos[i][j].setOnClickListener(onClickListener);
			}
		}
        
    }	
    
    private boolean jogadaVencedora(Button button, Button button2,
			Button button3) {
    	String opcao = button.getText().toString();
		return !opcao.isEmpty() && opcao.equals(button2.getText().toString()) && opcao.equals(button3.getText().toString());
	}
    
    private boolean encontrouGanhador(){
    	boolean achouGanhador = false;
    	int a = 0;
    	while(!achouGanhador && a < 3){
    		if (jogadaVencedora(mCampos[a][0], mCampos[a][1], mCampos[a][2])){
    			achouGanhador = true;
    			mCampos[a][0].setBackgroundResource(R.drawable.buttonwinner);
    			mCampos[a][1].setBackgroundResource(R.drawable.buttonwinner);
    			mCampos[a][2].setBackgroundResource(R.drawable.buttonwinner);
    		}else{
    			if (jogadaVencedora(mCampos[0][a], mCampos[1][a], mCampos[2][a])){
    				achouGanhador = true;
    				mCampos[0][a].setBackgroundResource(R.drawable.buttonwinner);
    				mCampos[1][a].setBackgroundResource(R.drawable.buttonwinner);
    				mCampos[2][a].setBackgroundResource(R.drawable.buttonwinner);
    			}
    		}
    		a++;
    	}
    	if (!achouGanhador && jogadaVencedora(mCampos[0][0], mCampos[1][1], mCampos[2][2])){
			achouGanhador = true;
			mCampos[0][0].setBackgroundResource(R.drawable.buttonwinner);
			mCampos[1][1].setBackgroundResource(R.drawable.buttonwinner);
			mCampos[2][2].setBackgroundResource(R.drawable.buttonwinner);
		}
    	if (!achouGanhador && jogadaVencedora(mCampos[0][2], mCampos[1][1], mCampos[2][0])){
    		achouGanhador = true;
    		mCampos[0][2].setBackgroundResource(R.drawable.buttonwinner);
    		mCampos[1][1].setBackgroundResource(R.drawable.buttonwinner);
    		mCampos[2][0].setBackgroundResource(R.drawable.buttonwinner);
    	}   	
    	return achouGanhador;
    }
    
	private void onComecaNovaPartida(){
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				mCampos[i][j].setText("");
				mCampos[i][j].setBackgroundResource(R.drawable.button);
			}
		}   
		vencedor = "";
		partidaFinalizada = false;
		count = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
		return true;	
    }
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.novaPartida){
			onComecaNovaPartida();
		}
		return super.onOptionsItemSelected(item);
	}

}
