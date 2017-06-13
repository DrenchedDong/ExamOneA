package dongting.bwei.com.examonea;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private final static String TAG = "MainActivity";
    private RoundProgressBar01 roundProgressBar_01 = null;
    private int TIME_TICKER = 0;
    private int time = 0;
    private int progress = 0;
    private final int UPDATE_UI_01 = 1;
    private final int UPDATE_UI_02 = 2;
    private final int UPDATE_UI_03 = 3;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_UI_01:
                    Log.i(TAG, "UPDATE_UI_01");
                    progress++;
                    if (progress > 100) {
                        progress = 0;
                    }
                    handler.sendEmptyMessageDelayed(UPDATE_UI_01, 100);
                    updateUIRoundProgressBar_01(progress);
                    break;
                case UPDATE_UI_02:
                    progress -= 1;
                    if (progress > 0) {
                        handler.sendEmptyMessageDelayed(UPDATE_UI_02, 100);
                        updateUIRoundProgressBar_01(progress);
                    }
                    if (progress==0){
                        progress++;
                        if (progress > 100) {
                            progress = 0;
                        }
                        handler.sendEmptyMessageDelayed(UPDATE_UI_01, 100);
                        updateUIRoundProgressBar_01(progress);
                    }
                    break;
                case UPDATE_UI_03:

                    break;

            }
        }
    };
    private boolean   b = false;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        handler.sendEmptyMessageDelayed(UPDATE_UI_01, 100);
        Button bt1 = (Button) findViewById(R.id.bt1);

        //循环播放
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeMessages(UPDATE_UI_01);
                handler.sendEmptyMessageDelayed(UPDATE_UI_02, 100);
            }
        });
//暂停

        Button bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b ==false){
                    handler.removeMessages(UPDATE_UI_01);
                    handler.removeMessages(UPDATE_UI_02);
                    b=true;
                }else {
                    init();
                    handler.sendEmptyMessageDelayed(UPDATE_UI_01, 100);
                    b=false;
                }

            }

        });

    }

    private void init() {
        // TODO Auto-generated method stub
        roundProgressBar_01 = (RoundProgressBar01) findViewById(R.id.roundProgressBar_01);
        roundProgressBar_01.setStyle(TIME_TICKER);
        roundProgressBar_01.setProgress(1);
        progress = 1;

    }

    private void updateUIRoundProgressBar_01(int progress) {
        roundProgressBar_01.setProgress(progress);

    }
}