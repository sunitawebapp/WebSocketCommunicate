package com.example.websocketcommunicate


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import dev.gustavoavila.websocketclient.WebSocketClient
import java.io.BufferedReader
import java.io.PrintWriter
import java.net.URI
import java.net.URISyntaxException

var output: PrintWriter? = null
var input: BufferedReader? = null
var SERVER_IP = "wss://socket.porchpirateboxer.com"
val SERVER_PORT = 3133

lateinit var mWebSocketClient: WebSocketClient

class MainActivity : AppCompatActivity() {

    lateinit var thread1 : Thread
    lateinit var tvIP: TextView
    lateinit var tvPort:TextView
  companion object{
      lateinit  var tvMessages: TextView
  }
    lateinit var etMessage: EditText
    lateinit var btnSend: Button

    var message: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvIP = findViewById(R.id.tvIP)
        tvPort = findViewById(R.id.tvPort)
        tvMessages = findViewById(R.id.tvMessages)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)
        //wss://socket.porchpirateboxer.com:3133?key=6371fad6cbb7ccf4ffe9d120
       // connectdemoapi("wss://demo.piesocket.com/v3/channel_1?api_key=VCXCEuvhGcBDP7XhiJJUDvR1e1D3eiVjgZ9VRiaV&notify_self")

        connect("wss://socket.porchpirateboxer.com:3133?deviceKey=ABABDSDSDD123")

    }


    @Throws(Exception::class)
    private fun connectdemoapi(websocketEndPointUrl: String)  {
      //  var websocketEndPointUrl = websocketEndPointUrl
        val uri: URI
        try {

         //   Log.i(TAG, " WSURL: $websocketEndPointUrl")
            uri = URI(websocketEndPointUrl)
        } catch (e: URISyntaxException) {
          //  Log.e(TAG, e.getMessage())
            return
        }
      var  mWebSocketClient = object : WebSocketClient(uri) {


          override fun onOpen() {
              Log.i("Websocket", "Opened")
          }

          override fun onTextReceived(message: String?) {
              Log.i("Websocket", "message"+message)
          }

          override fun onBinaryReceived(data: ByteArray?) {
              Log.i("Websocket", "data"+data)
          }

          override fun onPingReceived(data: ByteArray?) {
              Log.i("Websocket", "data"+data)
          }

          override fun onPongReceived(data: ByteArray?) {
              Log.i("Websocket", "data"+data)
          }

          override fun onException(e: java.lang.Exception?) {
              Log.i("Websocket", "e"+e)
          }

          override fun onCloseReceived() {
              Log.i("Websocket", "close")
          }
      }
        mWebSocketClient.send("")
        mWebSocketClient.connect()
    }


    @Throws(Exception::class)
    private fun connect(websocketEndPointUrl: String)  {
        //  var websocketEndPointUrl = websocketEndPointUrl
        val uri: URI
        try {

            //   Log.i(TAG, " WSURL: $websocketEndPointUrl")
            uri = URI(websocketEndPointUrl)
        } catch (e: URISyntaxException) {
            //  Log.e(TAG, e.getMessage())
            return
        }
          mWebSocketClient = object : WebSocketClient(uri) {


            override fun onOpen() {
                Log.i("Websocket", "Opened")
                val gson = Gson()
             var sendMsgReq= SendMsgReq(
                 type="arrival",
                subType= "triggered",
                detail= "parcel-arrived",
                timestamp="ABABDSDSDD123",
                deviceKey="ABABDSDSDD123",
                photoPath= "//some-location"
             )
                val msg = gson.toJson(sendMsgReq)
               // var jsonObject= JSONObject(msg)
                mWebSocketClient!!.send(msg)

            }

            override fun onTextReceived(message: String?) {
                Log.i("Websocket", "message"+message)
            }

            override fun onBinaryReceived(data: ByteArray?) {
                Log.i("Websocket", "data"+data)
            }

            override fun onPingReceived(data: ByteArray?) {
                Log.i("Websocket", "data"+data)
            }

            override fun onPongReceived(data: ByteArray?) {
                Log.i("Websocket", "data"+data)
            }

            override fun onException(e: java.lang.Exception?) {
                Log.i("Websocket", "e"+e)
            }

            override fun onCloseReceived() {
                Log.i("Websocket", "close")
            }
        }
        mWebSocketClient.connect()
    }
}

