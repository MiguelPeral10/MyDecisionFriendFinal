package org.xoan.mydecisionfriend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class inicioAplicacion extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicioaplicacion);
        Button Nivel1 = (Button) findViewById(R.id.Nivel1);
        Button Nivel2 = (Button) findViewById(R.id.Nivel2);
        Button Nivel3 = (Button) findViewById(R.id.Nivel3);
        Button btnPerfil = (Button) findViewById(R.id.btn_perfil);
        Nivel1.setOnClickListener(this);
        Nivel2.setOnClickListener(this);
        Nivel3.setOnClickListener(this);
        btnPerfil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Nivel1:
                ejecutarRuleta(v);
                break;
            case R.id.Nivel2:
                ejecutarRuleta(v);
                break;
            case R.id.Nivel3:
                ejecutarRuleta(v);
                break;
            case R.id.btn_perfil:
                finish();
                Intent x = new Intent(this,Perfil.class);
                startActivity(x);
                break;
        }
    }
    public void ejecutarRuleta(View v){
        finish();
        Intent i = new Intent(this,ruletaDecision.class);
        startActivity(i);
    }
}