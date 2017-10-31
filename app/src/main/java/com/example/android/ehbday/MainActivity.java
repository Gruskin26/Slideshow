package com.example.android.ehbday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final Integer[] images = {R.drawable.wall1, R.drawable.wall3};
    private static final Integer[] messages = {R.string.m1, R.string.m2, R.string.m3};
    private static int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get my image view that makes up the background
        ImageView slide = (ImageView) findViewById(R.id.image);
        // Set the initial image to image 0
        counter = 0;
        slide.setImageResource(images[counter]);
        ImageButton prev = (ImageButton) findViewById(R.id.prev);
        prev.setVisibility(View.INVISIBLE);
    }

    /**
     * Advances to the next image and slide
     * @param view Required to make this an onClick method
     */
    public void next(View view) {
        boolean restarted;
        // Get the needed views
        ImageButton prev = (ImageButton) findViewById(R.id.prev);
        ImageButton nxt = (ImageButton) findViewById(R.id.next);
        ImageView slide = (ImageView) findViewById(R.id.image);
        // Increment the counter for the image
        // TODO set up text messages for each image. Can we caption an image view or do we need to
        // TODO use a separate text view?
        // Ensure that we can't go off the edge of the images array
        if (counter + 1 == images.length -1){
            nxt.setImageResource(android.R.drawable.ic_menu_revert);
        }
        if (counter + 1 > images.length - 1){
            counter = 0;
            restarted = true;
        }
        else {
            counter++;
            restarted = false;
        }
        // Set the image to the next one
        slide.setImageResource(images[counter]);
        if (counter > 0){
            prev.setVisibility(View.VISIBLE);
        }
        // Change the button image so that we can tell when we're going to restart
        if (restarted){
            nxt.setImageResource(android.R.drawable.ic_media_next);
            prev.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Backs up to the last slide
     * @param view Required to make this an onClick method
     */
    public void prev(View view){
        // Get the necessary resources
        ImageView slide = (ImageView) findViewById(R.id.image);
        ImageButton prev = (ImageButton) findViewById(R.id.prev);
        ImageButton nxt = (ImageButton) findViewById(R.id.next);
        // If we are at the first image, hide the back button from sight, and make it so that
        // you cannot go back
        if (counter - 1 == 0){
            counter = 0;
            prev.setVisibility(View.INVISIBLE);
        }
        // Otherwise decrement the counter
        else {counter--;}
        // If we're no longer on the last slide, change the revert button to be next
        // The reason for doing the math this way is to avoid re-drawing the image when it is
        // already the correct icon. It doesn't make a difference in such a small app, but it is
        // probably a good habit to get into.
        if (counter + 1 == images.length - 1){
            nxt.setImageResource(android.R.drawable.ic_media_next);
        }
        slide.setImageResource(images[counter]);
    }

}
