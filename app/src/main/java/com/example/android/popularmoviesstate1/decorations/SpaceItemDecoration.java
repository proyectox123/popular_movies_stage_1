package com.example.android.popularmoviesstate1.decorations;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

//In order to add padding in recycler's item, it's necessary to create a custom class to modify
//item's offsets
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = validateItemTop(view, parent);
        outRect.right = space;
        outRect.bottom = space;
        outRect.left = space;
    }

    private int validateItemTop(@NonNull View view, @NonNull RecyclerView parent){
        return parent.getChildLayoutPosition(view) == 0 ? space : 0;
    }
}