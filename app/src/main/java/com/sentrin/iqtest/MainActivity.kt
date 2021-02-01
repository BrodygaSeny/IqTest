package com.sentrin.iqtest

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sentrin.iqtest.MyApplication.Companion.mainData
import com.sentrin.iqtest.data.getDatabase
import kotlinx.android.synthetic.main.toast.*
import kotlinx.android.synthetic.main.toast.view.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var viewModel:MainActivityViewModel


    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //main data of App
        mainData.contextMainActivity = this
        mainData.database = getDatabase(this)
        mainData.viewModelMainActivity = viewModel


        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppThemeLight);
        }

        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //todo
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.firstStartFragment, R.id.nav_question, R.id.answerFragment, R.id.aboutApp), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }


        // actions //
        //show prompts
        viewModel.showPromptStairs.observe(this, Observer {
            if (it){
                scope.launch{
                    showPromptFirstSteps()
                }
                viewModel.showPromptStairs.value = false
            }
        })

        viewModel.showPromptThinkAbout.observe(this, Observer {
            if (it){
                scope.launch{
                    showPromptThinkAbout()
                }
                viewModel.showPromptThinkAbout.value = false
            }
        })



    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    private suspend fun showPromptFirstSteps() {
        withContext(Dispatchers.Main) {
            delay(3000)

            val customLayout = layoutInflater.inflate(R.layout.toast, toast_contsraint)

            val toast = Toast(mainData.contextMainActivity)
            toast.duration = Toast.LENGTH_LONG
            customLayout.text.text = resources.getString(R.string.prompt_stairs)
            toast.view = customLayout
            toast.show()
        }
    }

    private suspend fun showPromptThinkAbout() {
        withContext(Dispatchers.Main) {
            delay(2500)

            val customLayout = layoutInflater.inflate(R.layout.toast, toast_contsraint)

            val toast = Toast(mainData.contextMainActivity)
            toast.duration = Toast.LENGTH_LONG
            customLayout.text.text = resources.getString(R.string.prompt_think_about)
            toast.view = customLayout
            toast.show()

        }
    }



}