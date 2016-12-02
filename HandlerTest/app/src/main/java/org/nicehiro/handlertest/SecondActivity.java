package org.nicehiro.handlertest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hiro on 16-12-2.
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btn10).setOnClickListener(this);
        findViewById(R.id.btn11).setOnClickListener(this);
        findViewById(R.id.btn12).setOnClickListener(this);
        findViewById(R.id.btn13).setOnClickListener(this);
        findViewById(R.id.btn14).setOnClickListener(this);
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public void makeToast(final String msg) {
        SecondActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SecondActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onButton1Click() {
        String msg = "按钮点击：" + (isMainThread() ? "是" : "不是") + "主线程";
        SecondActivity.this.makeToast(msg);

        final Handler handler = new Handler();
        final Runnable handleTask = new Runnable() {
            @Override
            public void run() {
                String msg = "handle.post 执行：" + (isMainThread() ? "是" : "不是") + "主线程";
                SecondActivity.this.makeToast(msg);
            }
        };

        Runnable task = new Runnable() {
            @Override
            public void run() {
                String msg = "线程执行：" + (isMainThread() ? "是" : "不是") + "主线程";
                SecondActivity.this.makeToast(msg);

                handler.post(handleTask);
            }
        };

        final Thread thread = new Thread(task);
        thread.start();
    }

    public void onButton2Click() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SecondActivity.this.makeToast("postDelayed");
            }
        }, 1000);
    }

    public void onButton3Click() {
        final Handler handler = new Handler();
        long time = SystemClock.uptimeMillis() + 2000;
        handler.postAtTime(new Runnable() {
            @Override
            public void run() {
                SecondActivity.this.makeToast("postAtTime");
            }
        }, time);
    }

    public void onButton4Click() {
        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.postAtFrontOfQueue(new Runnable() {
                    @Override
                    public void run() {
                        String msg = "postAtFrontOfQueue";
                        SecondActivity.this.makeToast(msg);
                    }
                });
            }
        });
        thread.start();
    }

    public void onButton5Click() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String toast = "handleMessage 未知";
                switch (msg.what) {
                    case 0:
                        toast = "handleMessage\nsendMessage msg0 参数=" + msg.obj;
                        break;
                }
                SecondActivity.this.makeToast(toast);
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg0 = Message.obtain(handler, 0, "参数0");
                handler.sendMessage(msg0);
            }
        });
        thread.start();
    }

    public void onButton6Click() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String toast = "handleMessage 未知";
                switch (msg.what) {
                    case 0:
                        toast = "handleMessage\nsendMessageDelayed msg0 参数=" + msg.obj;
                        break;
                }
                SecondActivity.this.makeToast(toast);
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg0 = Message.obtain(handler, 0, "参数0");
                handler.sendMessageDelayed(msg0, 1000);
            }
        });
        thread.start();
    }

    public void onButton7Click() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String toast = "handleMessage 未知";
                switch (msg.what) {
                    case 0:
                        toast = "handleMessage\nsendMessageAtTime msg0 参数=" + msg.obj;
                        break;
                }
                SecondActivity.this.makeToast(toast);
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg0 = Message.obtain(handler, 0, "参数0");
                handler.sendMessageAtTime(msg0, SystemClock.uptimeMillis()+2000);
            }
        });
        thread.start();
    }

    public void onButton8Click() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String toast = "handleMessage 未知";
                switch (msg.what) {
                    case 0:
                        toast = "handleMessage\nsendMessageAtFontOfQueue msg0 参数=" + msg.obj;
                        break;
                }
                SecondActivity.this.makeToast(toast);
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg0 = Message.obtain(handler, 0, "参数0");
                handler.sendMessageAtFrontOfQueue(msg0);
            }
        });
        thread.start();
    }

    // 错误方法示范
    public void onButton9Click() {
        final Runnable postTask = new Runnable() {
            @Override
            public void run() {
                String msg = "post: " + (isMainThread() ? "是" : "不是") + "主线程";
                SecondActivity.this.makeToast(msg);
            }
        };

        Runnable threadTask = new Runnable() {
            @Override
            public void run() {
                final Handler handler = new Handler();
                handler.post(postTask);
            }
        };
        Thread thread = new Thread(threadTask);
        thread.start();
    }

    public void onButton10Click() {
        final Runnable postTask = new Runnable() {
            @Override
            public void run() {
                String msg = "post: " + (isMainThread() ? "是" : "不是") + "主线程";
                SecondActivity.this.makeToast(msg);
            }
        };

        final HandlerThread outerThread = new HandlerThread("HandlerThread");
        outerThread.start();

        Runnable threadTask = new Runnable() {
            @Override
            public void run() {
                final Handler handler = new Handler(outerThread.getLooper());
                handler.post(postTask);
            }
        };
        Thread thread = new Thread(threadTask);
        thread.start();
    }

    public void onButton11Click() {
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                String msg = "IdleHandler running";
                SecondActivity.this.makeToast(msg);
                return false;
            }
        });
    }

    public void onButton12Click() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SecondActivity.this.makeToast("postDelayed");
            }
        };
        handler.postDelayed(runnable, 1000);

        for (int i=0; i<10000; i++) {
            //TODO
        }

        handler.removeCallbacks(runnable);
    }

    public void onButton13Click() {
        final HandlerThread outerThread = new HandlerThread("HandlerThread");
        outerThread.start();

        final Handler handler = new Handler(outerThread.getLooper());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SecondActivity.this.makeToast("postDelayed 消息");
                    }
                }, 1000);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        outerThread.quit();
                        String msg = "HandlerThread Loop 退出";
                        SecondActivity.this.makeToast(msg);
                    }
                });
            }
        });
        thread.start();
    }

    public void onButton14Click() {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                onButton1Click();
                break;
            case R.id.btn2:
                onButton2Click();
                break;
            case R.id.btn3:
                onButton3Click();
                break;
            case R.id.btn4:
                onButton4Click();
                break;
            case R.id.btn5:
                onButton5Click();
                break;
            case R.id.btn6:
                onButton6Click();
                break;
            case R.id.btn7:
                onButton7Click();
                break;
            case R.id.btn8:
                onButton8Click();
                break;
            case R.id.btn9:
                onButton9Click();
                break;
            case R.id.btn10:
                onButton10Click();
                break;
            case R.id.btn11:
                onButton11Click();
                break;
            case R.id.btn12:
                onButton12Click();
                break;
            case R.id.btn13:
                onButton13Click();
                break;
            case R.id.btn14:
                onButton14Click();
                break;
        }
    }
}
