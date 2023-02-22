package id.fakhri_khairi.core.presentation

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.fakhri_khairi.core.R
import id.fakhri_khairi.core.databinding.ActivityCoreBinding
import id.fakhri_khairi.data.misc.Constants
import javax.inject.Inject

@AndroidEntryPoint
class CoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoreBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_core_fragment)
        val graphInflater = navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.core_nav_graph)

        val memberId = sharedPreferences.getString(Constants.MEMBER_ID,null)

        if (memberId.isNullOrEmpty()) {
            navGraph.startDestination = R.id.loginFragment
            navController.graph = navGraph
            return
        }

        val memberType = sharedPreferences.getString(Constants.MEMBER_TYPE, "")
        if (memberType == "member") {
            navGraph.startDestination  = R.id.memberDetailFragment
            navController.graph = navGraph
            return
        }

        navGraph.startDestination = R.id.memberListFragment
        navController.graph = navGraph
    }
}