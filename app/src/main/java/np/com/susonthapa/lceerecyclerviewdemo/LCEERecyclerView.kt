package np.com.susonthapa.lceerecyclerviewdemo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import np.com.susonthapa.lceerecyclerviewdemo.databinding.LceRecyclerLayoutBinding
import np.com.susonthapa.lceerecyclerviewdemo.databinding.RecyclerEmptyLayoutBinding
import np.com.susonthapa.lceerecyclerviewdemo.databinding.RecyclerErrorLayoutBinding
import np.com.susonthapa.lceerecyclerviewdemo.databinding.RecyclerLoadingLayoutBinding

/**
 * Created by suson on 6/17/20
 * Custom recycler view with integrated error, empty and loading view
 */
class LCEERecyclerView constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)


    private val binding: LceRecyclerLayoutBinding =
        LceRecyclerLayoutBinding.inflate(LayoutInflater.from(context), this)

    private val errorBinding: RecyclerErrorLayoutBinding
    private val emptyBinding: RecyclerEmptyLayoutBinding
    private val loadingBinding: RecyclerLoadingLayoutBinding

    // expose the recycler view
    val recyclerView: RecyclerView
        get() = binding.customRecyclerView

    var errorText: String = ""
        set(value) {
            field = value
            errorBinding.errorMsgText.text = value
        }

    var emptyText: String = ""
        set(value) {
            field = value
            emptyBinding.emptyMessage.text = value
        }

    @DrawableRes
    var errorIcon = 0
        set(value) {
            field = value
            errorBinding.errorImage.setImageResource(value)
        }

    @DrawableRes
    var emptyIcon = 0
        set(value) {
            field = value
            emptyBinding.emptyImage.setImageResource(value)
        }

    init {

        // inflate the layout
        errorBinding = binding.customErrorView
        emptyBinding = binding.customEmptyView
        loadingBinding = binding.customOverlayView

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LCEERecyclerView,
            0,
            0
        ).apply {
            try {
                errorText = getString(R.styleable.LCEERecyclerView_errorText) ?: "Something went wrong"
                emptyText =
                    getString(R.styleable.LCEERecyclerView_emptyText) ?: "Nothing to show"
                errorIcon = getResourceId(
                    R.styleable.LCEERecyclerView_errorIcon,
                    R.drawable.ic_error_loading
                )
                emptyIcon =
                    getResourceId(R.styleable.LCEERecyclerView_emptyIcon, R.drawable.ic_empty_image)
            } finally {
                recycle()
            }
        }
    }

    fun showEmptyView(msg: String? = null) {
        emptyText = msg ?: emptyText
        loadingBinding.root.visibility = View.GONE
        errorBinding.root.visibility = View.GONE

        emptyBinding.root.visibility = View.VISIBLE
    }

    fun showErrorView(msg: String? = null) {
        errorText = msg ?: errorText
        loadingBinding.root.visibility = View.GONE
        emptyBinding.root.visibility = View.GONE

        errorBinding.root.visibility = View.VISIBLE
    }

    fun showLoadingView() {
        emptyBinding.root.visibility = View.GONE
        errorBinding.root.visibility = View.GONE

        loadingBinding.root.visibility = View.VISIBLE
    }

    fun hideAllViews() {
        loadingBinding.root.visibility = View.GONE
        errorBinding.root.visibility = View.GONE
        emptyBinding.root.visibility = View.GONE
    }

    fun setOnRetryClickListener(callback: () -> Unit) {
        errorBinding.retryButton.setOnClickListener {
            callback()
        }
    }
}