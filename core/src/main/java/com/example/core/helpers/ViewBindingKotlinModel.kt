package com.example.core.helpers

import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.airbnb.epoxy.EpoxyModel
import com.example.core.R
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.util.concurrent.ConcurrentHashMap

abstract class ViewBindingKotlinModel<T : ViewBinding>(
    @LayoutRes private val layoutRes: Int
) : EpoxyModel<View>() {
    // Using reflection to get the static binding method.
    // Lazy so it's computed only once by instance, when the 1st ViewHolder is actually created.
    private val bindingMethod by lazy { getBindMethodFrom(this::class.java) }

    abstract fun T.bind()
    open fun T.unbind() {}

    @Suppress("UNCHECKED_CAST")
    override fun bind(view: View) {
        var binding = view.getTag(R.id.epoxy_viewbinding) as? T
        if (binding == null) {
            binding = bindingMethod.invoke(null, view) as T
            view.setTag(R.id.epoxy_viewbinding, binding)
        }
        binding.bind()
    }

    override fun unbind(view: View) {
        view.getBinding().unbind()
    }

    @Suppress("UNCHECKED_CAST")
    protected fun View.getBinding(): T {
        var binding = getTag(R.id.epoxy_viewbinding) as? T
        if (binding == null) {
            binding = bindingMethod.invoke(null, this) as T
            setTag(R.id.epoxy_viewbinding, binding)
        }
        return binding
    }

    override fun getDefaultLayout() = layoutRes
}

// Static cache of a method pointer for each type of item used.
private val sBindingMethodByClass = ConcurrentHashMap<Class<*>, Method>()

@Suppress("UNCHECKED_CAST")
@Synchronized
private fun getBindMethodFrom(javaClass: Class<*>): Method =
    sBindingMethodByClass.getOrPut(javaClass) {
        val actualTypeOfThis = getSuperclassParameterizedType(javaClass)
        val viewBindingClass = actualTypeOfThis.actualTypeArguments[0] as Class<ViewBinding>
        viewBindingClass.getDeclaredMethod("bind", View::class.java)
            ?: error("The binder class ${javaClass.canonicalName} should have a method bind(View)")
    }

private fun getSuperclassParameterizedType(klass: Class<*>): ParameterizedType {
    val genericSuperclass = klass.genericSuperclass
    return (genericSuperclass as? ParameterizedType)
        ?: getSuperclassParameterizedType(genericSuperclass as Class<*>)
}