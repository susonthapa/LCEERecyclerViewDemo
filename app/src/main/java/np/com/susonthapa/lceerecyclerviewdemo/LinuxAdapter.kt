package np.com.susonthapa.lceerecyclerviewdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import np.com.susonthapa.lceerecyclerviewdemo.databinding.LinuxItemBinding

/**
 * Created by suson on 6/26/20
 */
class LinuxAdapter (private val distro: List<LinuxModel>): RecyclerView.Adapter<LinuxItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinuxItemViewHolder {
        val binding = LinuxItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LinuxItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return distro.size
    }

    override fun onBindViewHolder(holder: LinuxItemViewHolder, position: Int) {
        holder.bind(distro[position])
    }

}

class LinuxItemViewHolder(private val binding: LinuxItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: LinuxModel) {
        binding.linuxImage.setImageResource(item.imageRes)
        binding.linuxName.text = item.name
    }
}