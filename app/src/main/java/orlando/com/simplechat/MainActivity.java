package orlando.com.simplechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    EditText entrada;
    TextView historial;
    Button btn;
    Cesar cesar;

    private Socket mSocket;

    {
        try {
            mSocket = IO.socket("http://172.18.16.197:3000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSocket.on("chat message", nuevoMensaje);
        mSocket.connect();
        cesar = new Cesar();
        entrada = (EditText) findViewById(R.id.historial);
        historial = (TextView) findViewById(R.id.mensaje);
        btn = (Button) findViewById(R.id.enviar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentoEnvio();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        mSocket.off("new message", nuevoMensaje);
    }

    private void intentoEnvio() {
        String mensaje = entrada.getText().toString();
        if (TextUtils.isEmpty(mensaje)) return;
        entrada.setText("");

        int seed = cesar.getSeed(-15, 15);
        String key = cesar.encrypt(mensaje, seed);

        mSocket.emit("seed", seed); // manda la semilla de cifrado para descifrar el mensaje
        mSocket.emit("chat message", key);//manda el mensaje cifrado

    }

    private void agregarMensaje(String username, String mensaje) {
        historial.append(mensaje+"\n");
    }

    private Emitter.Listener nuevoMensaje = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String username="";
                    String mensaje = (String) args[0];

                    agregarMensaje(username, mensaje);
                }
            });
        }
    };


}
