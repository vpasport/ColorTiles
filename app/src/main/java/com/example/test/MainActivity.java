package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int darkColor;
    int brightColor;
    View[][] tiles = new View[4][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources r = getResources();
        darkColor = r.getColor(R.color.dark);
        brightColor = r.getColor(R.color.bright);

        tiles[0][0] = findViewById( R.id.t00 );
        tiles[0][1] = findViewById( R.id.t01 );
        tiles[0][2] = findViewById( R.id.t02 );
        tiles[0][3] = findViewById( R.id.t03 );

        tiles[1][0] = findViewById( R.id.t10 );
        tiles[1][1] = findViewById( R.id.t11 );
        tiles[1][2] = findViewById( R.id.t12 );
        tiles[1][3] = findViewById( R.id.t13 );

        tiles[2][0] = findViewById( R.id.t20 );
        tiles[2][1] = findViewById( R.id.t21 );
        tiles[2][2] = findViewById( R.id.t22 );
        tiles[2][3] = findViewById( R.id.t23 );

        tiles[3][0] = findViewById( R.id.t30 );
        tiles[3][1] = findViewById( R.id.t31 );
        tiles[3][2] = findViewById( R.id.t32 );
        tiles[3][3] = findViewById( R.id.t33 );

        fill();
    }

    public void changeColor(View v) {
        ColorDrawable d = (ColorDrawable) v.getBackground();
        if (d.getColor() == brightColor) {
            v.setBackgroundColor(darkColor);
        } else {
            v.setBackgroundColor(brightColor);
        }
    }

    public void fill(){
        double rand;
        for ( int i = 0; i < 4; i++ ){
            for ( int j = 0; j < 4; j++ ){
                rand = Math.random();
                if ( rand < 0.5 ){
                    changeColor( tiles[i][j] );
                }
            }
        }
    }

    public void onClick(View v) {
        String tag = v.getTag().toString();
        String[] coords = tag.split( " " );
        int x = Integer.parseInt( coords[0] ), y = Integer.parseInt( coords[1] );

        changeColor(v);
        for (int i = 0; i < 4; i++) {
            changeColor(tiles[x][i]);
            changeColor(tiles[i][y]);
        }

        int brightCount = 0, darkCount = 0;

        for ( int i = 0; i < 4; i++ ){
            for ( int j = 0; j < 4; j++ ){
                ColorDrawable d = (ColorDrawable) tiles[i][j].getBackground();
                if ( d.getColor() == darkColor ) darkCount++;
                else brightCount++;
            }
        }

        if( brightCount == 0 || darkCount == 0 ){
            Toast.makeText( this, "You win!", Toast.LENGTH_LONG ).show();
            fill();
        }
    }
}
