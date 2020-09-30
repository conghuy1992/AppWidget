package com.conghuy.nativewidget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataProvider extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new DataRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    class DataRemoteViewsFactory implements RemoteViewsFactory {

        List<String> myListView = new ArrayList<>();
        Context mContext = null;

        public DataRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;
        }

        @Override
        public void onCreate() {
            initData();
        }

        @Override
        public void onDataSetChanged() {
            initData();
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return myListView.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews view = new RemoteViews(mContext.getPackageName(),
                    R.layout.grid_item);
            view.setTextViewText(R.id.date, myListView.get(position));
            return view;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        private void initData() {
            myListView.clear();
            for (int i = 1; i <= 42; i++) {
                myListView.add(String.valueOf(new Random().nextInt(100)));
            }

        }
    }
}