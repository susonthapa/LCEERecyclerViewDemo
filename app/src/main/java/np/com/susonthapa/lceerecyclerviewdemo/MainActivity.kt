package np.com.susonthapa.lceerecyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import np.com.susonthapa.lceerecyclerviewdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val distro = ArrayList<LinuxModel>()
        distro.addAll(getData())
        val adapter = LinuxAdapter(distro)
        binding.linuxRecyclerView.recyclerView.adapter = adapter
        // attach the listener for the buttons
        binding.toggleView.addOnButtonCheckedListener { group, checkedId, isChecked ->
            when (checkedId) {
                // not the best implementation for LCEE
                // you should have mutually exclusive LCEE states, the below is just for demonstration
                R.id.loading_button -> {
                    distro.clear()
                    adapter.notifyDataSetChanged()

                    binding.linuxRecyclerView.showLoadingView()
                }

                R.id.content_button -> {
                    distro.clear()
                    distro.addAll(getData())
                    adapter.notifyDataSetChanged()

                    binding.linuxRecyclerView.hideAllViews()
                }

                R.id.empty_button -> {
                    distro.clear()
                    adapter.notifyDataSetChanged()

                    binding.linuxRecyclerView.showEmptyView()
                }

                R.id.error_button -> {
                    distro.clear()
                    adapter.notifyDataSetChanged()

                    binding.linuxRecyclerView.showErrorView()
                }

            }
        }

    }


    private fun getData(): List<LinuxModel> {
        return listOf(
            LinuxModel(R.drawable.ic_arch, "Arch Linux"),
            LinuxModel(R.drawable.ic_debian, "Debian Linux"),
            LinuxModel(R.drawable.ic_gentoo, "Gentoo Linux"),
            LinuxModel(R.drawable.ic_kali, "Kali Linux"),
            LinuxModel(R.drawable.ic_mint, "Mint Linux"),
            LinuxModel(R.drawable.ic_red_hat, "Red Hat Linux"),
            LinuxModel(R.drawable.ic_ubuntu, "Ubuntu Linux")
        )
    }
}