package com.example.lafyuu.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lafyuu.R
import com.example.lafyuu.adapter.BasketAdapter
import com.example.lafyuu.databinding.FragmentBasketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {
    val viewModel by viewModels<BasketViewModel>()
    private lateinit var basketAdapter: BasketAdapter
    lateinit var binding:FragmentBasketBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentBasketBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        basketAdapter = BasketAdapter(
            onClickItems = { item ->
                viewModel.deleteBasket(item.id)
            },
            onQuantityChanged = {
                val totalPrice = basketAdapter.calculateTotalPrice()
                binding.textViewTotalPrice.text = "${totalPrice} AZN"
            }
        )

        binding.rvbasket.adapter=basketAdapter
        binding.rvbasket.layoutManager=LinearLayoutManager(context)
        observeData()

        var code= mapOf(
            "Discount10" to 0.10,
            "Discount20" to 0.20,
            "VIP30" to 0.30
        )

        binding.myButton.setOnClickListener {
            val enteredcode=binding.editTextText2.text.toString().trim()
            val totalString=binding.textViewTotalPrice.text.toString().trim()
            val total = totalString.replace("AZN", "").trim().toDoubleOrNull() ?: 0.0

            if (binding.editTextText2.text.isNotEmpty()){
                if (enteredcode in code){
                    val discount=code[enteredcode]?:0.0
                    viewModel.updateTotalPrice(total,discount)
                }else{
                    Toast.makeText(context,"Yanlis kod",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(context,"Kupon kodu daxil edin",Toast.LENGTH_LONG).show()
            }
        }





    }

    fun observeData(){
        viewModel.allBaskets().observe(viewLifecycleOwner){
            basketAdapter.updataList(it)
            val totalPrice = basketAdapter.calculateTotalPrice()
            binding.textViewTotalPrice.text = "${totalPrice} AZN "
            binding.textViewCount.text = "${basketAdapter.itemCount}"
        }

        viewModel.totalPriceLiveData.observe(viewLifecycleOwner){discountprice->
         binding.textViewEndirim.text="${discountprice}AZN"


        }
    }


}
