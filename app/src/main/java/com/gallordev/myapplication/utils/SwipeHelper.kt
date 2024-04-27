package com.gallordev.myapplication.utils

import android.content.Context
import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gallordev.myapplication.ui.student.StudentAdapter

object SwipeHelper {

    fun setItemTouchHelper(context: Context, recyclerView: RecyclerView, size: Int) {

        ItemTouchHelper(object : ItemTouchHelper.Callback() {

            private val limitScrollX = dipToPx(context)
            private var currentScrollX = 0
            private var currentScrollXWhenInActive = 0
            private var initXWhenInActive = 0f
            private var firstInActive = false
            var leftSwipeChecker = false

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

            override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
                return Integer.MAX_VALUE.toFloat()
            }

            override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
                return Integer.MAX_VALUE.toFloat()
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    if (viewHolder.itemView.scrollX == 0) {
                        leftSwipeChecker = true
                    }

                    leftSwipeChecker = leftSwipeChecker && dX < 0

                    if (leftSwipeChecker) {
                        recoverSwipedItem(viewHolder, recyclerView)
                        if (viewHolder.itemView.scrollX != 0) {
                            leftSwipeChecker = false
                        }
                    }

                    if (dX == 0f) {
                        currentScrollX = viewHolder.itemView.scrollX
                        firstInActive = true
                    }

                    if (isCurrentlyActive) {
                        var scrollOffset = currentScrollX + (-dX).toInt()
                        if (scrollOffset > limitScrollX) {
                            scrollOffset = limitScrollX
                        } else if (scrollOffset < 0) {
                            scrollOffset = 0
                        }
                        viewHolder.itemView.scrollTo(scrollOffset, 0)
                    } else {
                        if (firstInActive) {
                            firstInActive = false
                            currentScrollXWhenInActive = viewHolder.itemView.scrollX
                            initXWhenInActive = dX
                        }

                        if (viewHolder.itemView.scrollX < limitScrollX) {
                            viewHolder.itemView.scrollTo(
                                (currentScrollXWhenInActive * dX / initXWhenInActive).toInt(),
                                0
                            )
                        }
                    }
                }
            }

            private fun recoverSwipedItem(
                viewHolder: RecyclerView.ViewHolder,
                recyclerView: RecyclerView
            ) {

                for (i in size downTo 0) {
                    val itemView = recyclerView.findViewHolderForAdapterPosition(i)?.itemView

                    if (i != viewHolder.adapterPosition) {

                        itemView?.let {
                            if (it.scrollX > 0) {
                                recoverItemAnim(itemView)
                            }
                        }
                    }

                    itemView?.setOnClickListener {
                        recoverItemAnim(itemView)
                    }
                }
            }

            private fun recoverItemAnim(itemView: View?) {
                itemView?.scrollTo(0, 0)
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)

                if (viewHolder.itemView.scrollX > limitScrollX) {
                    viewHolder.itemView.scrollTo(limitScrollX, 0)
                } else if (viewHolder.itemView.scrollX < 0) {
                    viewHolder.itemView.scrollTo(0, 0)
                }
            }

        }).apply {
            attachToRecyclerView(recyclerView)
        }
    }

    private fun dipToPx(context: Context): Int {
        return (100f * context.resources.displayMetrics.density).toInt()
    }

}
