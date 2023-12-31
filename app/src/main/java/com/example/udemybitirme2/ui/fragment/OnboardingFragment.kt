package com.example.udemybitirme2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.udemybitirme2.MainActivity
import com.example.udemybitirme2.R
import com.example.udemybitirme2.data.onboarding.OnBoardingItem
import com.example.udemybitirme2.util.gecisYap
import com.example.udemybitirme2.databinding.FragmentOnboardingBinding
import com.example.udemybitirme2.ui.adapter.OnboardingScreenAdapter

class OnboardingFragment : Fragment() {
    private lateinit var binding : FragmentOnboardingBinding
    private  val onboadringItemsAdapter: OnboardingScreenAdapter by lazy { OnboardingScreenAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_onboarding,container,false)
        binding.onBoardingFragmentNesnesi=this
        setOnBoardingItems()
        setupIndicators()
        setCurrentIndicator(0)

        return binding.root
    }

    fun setOnBoardingItems() {
        onboadringItemsAdapter.submitList(listOf(
            OnBoardingItem(
                onboaringImage = R.raw.onboarding1,
                title = "Order Now" ,
                description = "Your food at your door before it gets cold. Order now"
            ),
            OnBoardingItem(
                onboaringImage = R.raw.onboarding2,
                title = "Add Favorites" ,
                description = "Add or remove the dishes you like and order often to your favorites at any time"
            ),
            OnBoardingItem(
                onboaringImage = R.raw.onboarding3,
                title = "Settings" ,
                description = "Set your theme to your liking and choose from two language preferences"
            )
        ))


        binding.onboardingViewPager.adapter=onboadringItemsAdapter
        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (binding.onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(onboadringItemsAdapter.itemCount)
        val layoutParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(requireContext())
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                binding.indicatorsContainer.addView(it)
            }
        }

    }

    private fun setCurrentIndicator(position : Int) {
        val childCount = binding.indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = binding.indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_active_background
                    )
                )
            }else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }

    fun getStartedAndSkip(it:View){
        (requireActivity() as MainActivity).setToolbarVisibility(true) // Toolbar'ı görünür yap
        Navigation.gecisYap(it,R.id.anasayfaGecis)

    }

}