package com.example.chatrt

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatrt.databinding.ActivityMainBinding
import com.example.chatrt.fragments.FragmentChats
import com.example.chatrt.fragments.FragmentPerfil
import com.example.chatrt.fragments.FragmentUsers
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        if(firebaseAuth.currentUser == null){
            goOptionsLogin()
        }

        //fragmento por default
        watchFragmentPerfil()

        binding.bottomNV.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_perfil -> {
                    watchFragmentPerfil()
                    true
                }

                R.id.item_usuarios -> {
                    watchFragmentUsers()
                    true
                }

                R.id.item_chats -> {
                    watchFragmentChats()
                    true
                }

                else -> {

                    false
                }
            }
        }

    }

    private fun goOptionsLogin() {
        startActivity(Intent(applicationContext, LoginOptionsActivity::class.java))
    }

    private fun watchFragmentPerfil(){
        binding.tvTitulo.text = "Perfil"
        val fragment = FragmentPerfil()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentFL.id, fragment, "Fragment Perfil")
        fragmentTransaction.commit()
    }
    private fun watchFragmentUsers(){
        binding.tvTitulo.text = "Usuarios"
        val fragment = FragmentUsers()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentFL.id, fragment, "Fragment Usuarios")
        fragmentTransaction.commit()
    }
    private fun watchFragmentChats(){
        binding.tvTitulo.text = "Chats"
        val fragment = FragmentChats()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentFL.id, fragment, "Fragment Chats")
        fragmentTransaction.commit()
    }
}