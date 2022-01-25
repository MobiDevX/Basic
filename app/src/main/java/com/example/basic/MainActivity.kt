package com.example.basic

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.basic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var id_color = Color.BLUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener(View.OnClickListener {
            val sb: Snackbar = Snackbar.make(
                findViewById(R.id.coordinatorLayout),
                R.string.snackbar_text, Snackbar.LENGTH_LONG)
            val sbView = sb.view
            sbView.setBackgroundColor(id_color)
            sb.show()
        })
/*
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, R.string.snackbar_text, Snackbar.LENGTH_LONG)
                .show()
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
/*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        val sb: Snackbar = Snackbar.make(
            findViewById(R.id.coordinatorLayout),
            R.string.snackbar_text, Snackbar.LENGTH_LONG)
        val sbView: View = sb.view
        sbView.setBackgroundColor(id_color)
        return when (id) {
            R.id.green_item -> {
                // Green item was selected
                id_color = Color.GREEN
                sbView.setBackgroundColor(id_color)
                sb.show()
                true
            }
            R.id.yellow_item -> {
                // Yellow item was selected
                id_color = Color.YELLOW
                sbView.setBackgroundColor(id_color)
                sb.show()
                true
            }
            R.id.red_item -> {
                id_color = Color.RED
                sbView.setBackgroundColor(id_color)
                sb.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onPause() {
        super.onPause()
        val prefs = getSharedPreferences(localClassName, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt("idColor", id_color)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences(localClassName, MODE_PRIVATE)
        id_color = prefs.getInt("idColor", Color.BLACK)
    }
}