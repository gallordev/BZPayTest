package com.gallordev.myapplication.ui.student

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.gallordev.myapplication.R
import com.gallordev.myapplication.databinding.LayoutStudentInfoBinding
import com.gallordev.myapplication.model.Student
import com.gallordev.myapplication.ui.common.DataBoundListAdapter

class StudentAdapter(
    private val editCallback: ((Student) -> Unit)? = null,
    private val deleteCallback: ((Student) -> Unit)? = null,
): DataBoundListAdapter<Student, LayoutStudentInfoBinding>(
    diffCallback = object : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Student, newItem: Student) =
            oldItem.name == newItem.name && oldItem.age == newItem.age
    }
) {

    override fun createBinding(parent: ViewGroup): LayoutStudentInfoBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_student_info,
            parent,
            false
        )
    }

    override fun bind(binding: LayoutStudentInfoBinding, item: Student) {
        with(binding) {
            student = item
            btnEdit.setOnClickListener { editCallback?.invoke(item) }
            btnDelete.setOnClickListener { deleteCallback?.invoke(item) }
        }
    }

}