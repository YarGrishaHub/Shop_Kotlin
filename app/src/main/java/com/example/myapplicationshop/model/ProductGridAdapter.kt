import Product
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationshop.DetailActivity
import com.example.myapplicationshop.R

class ProductGridAdapter (
    private val context: android.content.Context,
    private val products: List<Product>
) : RecyclerView.Adapter<ProductGridAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById<ImageView>(R.id.ivProductImage)
        val name: TextView = view.findViewById<TextView>(R.id.tvProductName)
        val price: TextView = view.findViewById<TextView>(R.id.tvProductPrice)
        val button: TextView = view.findViewById<Button>(R.id.btnDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_grid, parent, false)
        return VH(view)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val product = products[position]

        holder.image.setImageResource(product.ImageRes)
        holder.name.text = product.name
        holder.price.text = "${product.price} $"

        holder.button.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("name", product.name)
                putExtra("price", product.price)
                putExtra("ImageRes", product.ImageRes)
                putExtra("description", product.description)
            }
            context.startActivity(intent)
        }

    }





}