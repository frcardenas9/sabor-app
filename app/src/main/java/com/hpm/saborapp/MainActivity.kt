package com.hpm.saborapp

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout

class MainActivity :
    AppCompatActivity(),
    MenuFragment.OnMenuOptionSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            0,
            0
        )

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.menuContainer,
                    MenuFragment()
                )
                .replace(
                    R.id.contentContainer,
                    ContentFragment()
                )
                .commit()
        }
    }

    override fun onMenuOptionSelected(option: String) {

        val fragment = ContentFragment.newInstance(option)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.contentContainer,
                fragment
            )
            .commit()

        drawerLayout.closeDrawer(
            androidx.core.view.GravityCompat.START
        )
    }

}