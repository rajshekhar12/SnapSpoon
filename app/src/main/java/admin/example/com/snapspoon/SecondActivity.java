package admin.example.com.snapspoon;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = null;
    @Bind(R.id.videoView)
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);


        //specify the location of media file
        String url = intent.getStringExtra("url");

        mediaPlayer = new MediaPlayer();
        videoView.setVideoURI(Uri.parse(url));
        videoView.setMediaController(new MediaController(this));

        videoView.start();

    }
}
