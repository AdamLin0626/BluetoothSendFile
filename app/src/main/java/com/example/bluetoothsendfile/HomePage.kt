package com.example.bluetoothsendfile


import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.yourapp.bluetooth.BluetoothManager
import com.example.yourapp.bluetooth.BluetoothReceiver

class HomePage : AppCompatActivity() {

    private lateinit var showDevice: TextView
    private lateinit var MessageBox: EditText
    private lateinit var receiver: BluetoothReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)

        showDevice = findViewById(R.id.ShowMessage)
        MessageBox = findViewById(R.id.MessageBox)

        // 廣播接收器模組化後建構
        receiver = BluetoothReceiver(showDevice)
        val filter = IntentFilter("android.bluetooth.devicepicker.action.DEVICE_SELECTED")
        registerReceiver(receiver, filter)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun PairDevice(view: View) {
        if (!BluetoothManager.isBluetoothEnabled()) {
            BluetoothManager.requestEnableBluetooth(this)
        } else {
            BluetoothManager.openDevicePicker(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}