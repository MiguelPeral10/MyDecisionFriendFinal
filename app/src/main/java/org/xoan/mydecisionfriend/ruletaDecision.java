package org.xoan.mydecisionfriend;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class ruletaDecision extends Activity implements Animation.AnimationListener, View.OnClickListener {
    boolean btnRotacion = true;
    long lngGrados = 0;
    int nDecisiones = 8;
    SharedPreferences sp;
    ImageButton b_girarRuleta;
    Button sugPlan;
    ImageView imgRuleta,imgEleccion;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ruletadecision);
        this.sp = PreferenceManager.getDefaultSharedPreferences(this);
        this.nDecisiones = this.sp.getInt("nDecisiones",8);
        b_girarRuleta = (ImageButton) findViewById(R.id.girarRuleta);
        sugPlan = (Button) findViewById(R.id.sugerirPlan);
        imgRuleta = (ImageView) findViewById(R.id.imgRuleta);
        imgEleccion = (ImageView) findViewById(R.id.imgEleccion);
        b_girarRuleta.setOnClickListener(this);
        sugPlan.setOnClickListener(this);
    }


    @Override
    public void onAnimationStart(Animation animation) {
        this.btnRotacion = false;
        b_girarRuleta.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Toast mensaje = Toast.makeText(this, " " + String.valueOf((int)(((double)this.nDecisiones)
                - Math.floor( ((double) this.lngGrados) / (360.0d / ((double) this.nDecisiones))))) + " ", Toast.LENGTH_LONG);
        mensaje.setGravity(49,0,0);
        mensaje.show();
        this.btnRotacion = true;

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.girarRuleta:
                if(this.btnRotacion){
                    int aleatorio = new Random().nextInt(360) + 3600;
                    RotateAnimation rt = new RotateAnimation((float)this.lngGrados,(float)(this.lngGrados + ((long)aleatorio)),1,0.5f,1,0.5f);
                    this.lngGrados = (this.lngGrados + ((long)aleatorio) % 360);
                    rt.setDuration((long)aleatorio);
                    rt.setFillAfter(true);
                    rt.setInterpolator(new DecelerateInterpolator());
                    rt.setAnimationListener(this);
                    imgRuleta.setAnimation(rt);
                    imgRuleta.startAnimation(rt);
                }
                break;
            case R.id.sugerirPlan:
                ejecutarNuevoPlan(v);
                break;
        }

    }

    private void ejecutarNuevoPlan(View v) {
        Intent i = new Intent(this,sugerirPlan.class);
        startActivity(i);
    }


}
