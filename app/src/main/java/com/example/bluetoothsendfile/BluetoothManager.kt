
package com.example.yourapp.bluetooth

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.widget.Toast

object BluetoothManager {
    val adapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    fun isBluetoothEnabled(): Boolean {
        return adapter?.isEnabled == true
    }

    fun requestEnableBluetooth(context: Context) {
        Toast.makeText(context, "先開權限後再點擊按鈕", Toast.LENGTH_SHORT).show()
        val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
        context.startActivity(intent)
    }

    fun openDevicePicker(context: Context) {
        val intent = Intent("android.bluetooth.devicepicker.action.LAUNCH")
        context.startActivity(intent)
    }
}
