package com.example.yourapp.bluetooth

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView

class BluetoothReceiver(private val showDevice: TextView) : BroadcastReceiver() {

    var selectedDevice: BluetoothDevice? = null
    var deviceName: String? = null
    var deviceAddress: String? = null

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        Log.d("taggg", "$action")

        selectedDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
        selectedDevice?.let { device ->
            deviceName = device.name
            deviceAddress = device.address
            showDevice.text = "配對裝置: $deviceName\n位址: $deviceAddress"

            try {
                device.javaClass.getMethod("createBond").invoke(device)
            } catch (e: Exception) {
                Log.e("CreateBondError", e.message ?: "Error during bond")
            }
        }
    }
}
