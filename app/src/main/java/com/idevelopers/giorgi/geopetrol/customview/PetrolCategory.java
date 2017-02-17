package com.idevelopers.giorgi.geopetrol.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Giorgi on 2/14/2017.
 */

public class PetrolCategory extends TextView {

    public PetrolCategory(Context context) {
        super(context);
        init();
    }

    public PetrolCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PetrolCategory(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Typeface face= Typeface.createFromAsset(getContext().getAssets(), "fonts/gugeshashvili_5_mthavruli.ttf");
        this.setTypeface(face);
    }

}
