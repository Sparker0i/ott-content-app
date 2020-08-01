package me.sparker0i.ottcontent.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.sparker0i.ottcontent.R
import me.sparker0i.ottcontent.view.countrypicker.CountryPickerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_main, CountryPickerFragment())
            .commit()
    }
}