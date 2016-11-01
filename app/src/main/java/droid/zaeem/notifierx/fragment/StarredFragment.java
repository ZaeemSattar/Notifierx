package droid.zaeem.notifierx.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import droid.zaeem.notifierx.R;
import droid.zaeem.notifierx.activity.DetailedInboxMessageActivity;
import droid.zaeem.notifierx.activity.DetailedStarredMessageActivity;
import droid.zaeem.notifierx.adapter.MessagesAdapter;
import droid.zaeem.notifierx.cachememory.CacheModel;
import droid.zaeem.notifierx.database.DatabaseHandler;
import droid.zaeem.notifierx.database.Message;
import droid.zaeem.notifierx.decorations.DividerItemDecoration;
import droid.zaeem.notifierx.helpers.Constants;
import droid.zaeem.notifierx.listeners.RecyclerItemClickListener;

/**
 * Created by Droid on 7/15/2016.
 */
public class StarredFragment extends BaseFragment {

    MessagesAdapter messagesAdapter;
    RecyclerView recyclerView;
    DatabaseHandler databaseHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inbox, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        // Inflate the layout for this fragment
   /*     if (!CacheModel.getStringList(Constants.Keys.STARRED_LIST).isEmpty() && !CacheModel.getStringList(Constants.Keys.STARRED_LIST_TITLE).isEmpty()) {
            setUpRecyclerview();
        }*/
        databaseHandler= new DatabaseHandler(getContext());
        if(databaseHandler.getAllMessages("starred").size()!=0)
        {
            setUpRecyclerview();

        }
        return rootView;
    }

    public void setUpRecyclerview() {
       /* final ArrayList<String> strings= CacheModel.getStringList(Constants.Keys.INBOX_LIST);
        final ArrayList<String> stringsTitles= CacheModel.getStringList(Constants.Keys.INBOX_LIST_TITLE);*/
        final List<Message> messages= databaseHandler.getAllMessages("starred");
        messagesAdapter = new MessagesAdapter(messages);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        recyclerView.setAdapter(messagesAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent= new Intent(getActivity(), DetailedStarredMessageActivity.class);
                        intent.putExtra(Constants.Keys.MESSAGE,messages.get(position).getBody());
                        intent.putExtra(Constants.Keys.MESSAGE_TITLE,messages.get(position).getTitle());
                        intent.putExtra(Constants.Keys.POSITION,position);
                        getActivity().startActivity(intent);

                    }
                })
        );
        messagesAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResume() {
        setUpRecyclerview();
        super.onResume();
    }
}
