package com.idevelopers.giorgi.geopetrol.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Giorgi on 2/14/2017.
 */

public class PetrolDigital extends TextView {
    public PetrolDigital(Context context) {
        super(context);
        init();
    }

    public PetrolDigital(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PetrolDigital(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Typeface face= Typeface.createFromAsset(getContext().getAssets(), "fonts/digital.ttf");
        this.setTypeface(face);
    }
}
