package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Activity.AllPlaylistActivity;
import com.example.myapplication.Activity.DanhSachBaiHatActivity;
import com.example.myapplication.Adapter.PlaylistAdapter;
import com.example.myapplication.Model.Playlist;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIService;
import com.example.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Play_List extends Fragment {
    View view;
    ListView lvplaylist;
    TextView txttitleplaylist,txtviewxemthemplaylist;
    PlaylistAdapter playlistAdapter;
    ArrayList<Playlist> mangplaylist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_list,container,false);
        lvplaylist = view.findViewById(R.id.listviewplaylist);
        txttitleplaylist = view.findViewById(R.id.textviewtitleplaylist);
        txtviewxemthemplaylist = view.findViewById(R.id.textviewmoreplaylist);
        GetData();
        txtviewxemthemplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AllPlaylistActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.GetPlaylistCurrentDay();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                mangplaylist = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, mangplaylist);
                lvplaylist.setAdapter(playlistAdapter);
                setListViewHeightBasedOnChildren(lvplaylist);
                lvplaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                        Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                        intent.putExtra("itemplaylist",mangplaylist.get(position));
                        startActivity(intent);
                    }
                });
            }


            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // Dòng này là trước khi bạn gọi số đo nếu không bạn sẽ không được đo chiều cao. Listitem cần được vẽ trước để biết chiều cao.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
