package com.project.mvvmbasemodel.base;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class GenericPageScrollAdapter<T, V extends ViewDataBinding, SV extends ViewDataBinding> extends GenericAdapter<T, V> {

    private static final int PAGE_SCROLL_VIEW = 3;
    private boolean isLoadingAdded = false;
    private int currentPage;
    private boolean isLoading;
    private boolean isLastPage;


    public GenericPageScrollAdapter(Context _context) {
        super(_context);
    }

    public abstract int getScrollLayoutId();

    public abstract void onBindScrollView(SV binding, T item, int position);

    protected abstract void doOnPageScroll(int nextPageNo);

    @Override
    public int getItemViewType(int position) {
        return (position == getListItems().size() - 1 && isLoadingAdded) ? PAGE_SCROLL_VIEW : ITEM_VIEW;
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == PAGE_SCROLL_VIEW) {
            return new GenericViewHolder<SV>(DataBindingUtil.inflate(getInflater(), getScrollLayoutId(), parent, false));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {

        if (getItemViewType(position) == PAGE_SCROLL_VIEW) {
            onBindScrollView((SV) holder.mBinding, getListItems().get(position), position);
            return;
        }
        super.onBindViewHolder(holder, position);
    }

    public void add(T item) {
        getListItems().add(item);
        notifyItemInserted(getListItems().size() - 1);
    }

    public void addData(List<T> items) {
        for (T item : items) {
            add(item);
        }
    }

    public void remove(T item) {
        int position = getListItems().indexOf(item);
        if (position > -1) {
            getListItems().remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public void clearAll() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
        resetCurrentPage();
    }

    protected abstract T getDefaultScrollItem();

    public void showPageScrollView() {
        isLoadingAdded = true;
        add(getDefaultScrollItem());
    }

    public void hidePageScrollView() {
        isLoadingAdded = false;

        int position = getListItems().size() - 1;
        T item = getItem(position);

        if (item != null) {
            getListItems().remove(position);
            notifyItemRemoved(position);
        }
    }


    public void resetCurrentPage() {
        this.currentPage = 1;
    }


    public void setPageScrollListener(RecyclerView recyclerView) {

        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        resetCurrentPage();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {

                        isLoading = true;
                        currentPage += 1;
                        doOnPageScroll(currentPage);

                    }
                }
            }
        });
    }

    public void updateScrollPages(int totalPage, int currentPage, boolean isNextPage) {
        this.currentPage = currentPage;
        isLoading = false;
        if (isNextPage) {

            if (currentPage != totalPage)
                togglePageLoader(true);
            else
                isLastPage = true;

        }

        if (!isNextPage && currentPage < totalPage) {
            isLastPage = false;
            togglePageLoader(true);
        } else {
            isLastPage = currentPage == totalPage;

        }
    }

    public void togglePageLoader(boolean status) {

        if (status) {
            showPageScrollView();
        } else {
            hidePageScrollView();
        }

    }

    public int getCurrentPage() {
        return currentPage;
    }
}
