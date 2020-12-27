package resti.example.appmemori.ui.resep

import android.content.Context
import android.content.Intent
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import resti.example.appmemori.R
import resti.example.appmemori.data.model.Resep
import resti.example.appmemori.databinding.ItemResepBinding
import resti.example.appmemori.ui.base.BaseAdapter

class ResepAdaptor(val context: Context) : BaseAdapter<Resep>(R.layout.item_resep) {
    override fun onBind(binding: ViewDataBinding?, data: Resep) {
        val mBinding = binding as ItemResepBinding
        Glide.with(context).load(data.thumb).into(mBinding.itemThumb)
    }

    override fun onClick(binding: ViewDataBinding?, data: Resep) {
        val intent = Intent(context, ResepActivity::class.java)
        intent.putExtra(ResepActivity.OPEN_RESEP, data)
        context.startActivity(intent)
    }
}