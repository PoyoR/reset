package com.credicel.ineqreset

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.credicel.ineqreset.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        verificarAdmin()

        val deviceAdmin = ComponentName(this, AdminReceiver::class.java)
        val admin = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager

        binding.button.setOnClickListener {

            // Launch the activity to have the user enable our admin.
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
                putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, deviceAdmin)
            }
            startActivity(intent)
        }


        binding.button2.setOnClickListener {

            admin.wipeData(0)
        }
    }

    private fun verificarAdmin(){
        val deviceAdmin = ComponentName(this, AdminReceiver::class.java)
        val admin = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager

        if (admin.isAdminActive(deviceAdmin)) {

            binding.textView.text = "Seleccione el botón siguiente para restablecer el equipo ( esta acción borrará todos los datos del equipo y no se podrá revertir)"

            binding.button2.visibility = View.VISIBLE
            binding.button.visibility = View.GONE
            binding.tvHabilitado.visibility = View.VISIBLE
            binding.tvNoHabilitado.visibility = View.GONE
        }else{

            binding.textView.text = "Para porder resetear el equipo debe habilitar el permiso de administración "
            binding.button2.visibility = View.GONE
            binding.button.visibility = View.VISIBLE
            binding.tvHabilitado.visibility = View.GONE
            binding.tvNoHabilitado.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        verificarAdmin()
    }

}