package droid.zaeem.notifierx.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import droid.zaeem.notifierx.R;
import droid.zaeem.notifierx.cachememory.CacheModel;
import droid.zaeem.notifierx.database.DatabaseHandler;
import droid.zaeem.notifierx.helpers.Constants;


/**
 * Created by Zaeem on 5/11/2016.
 */
public class DetailedImportantMessageActivity extends AppCompatActivity {
    TextView textViewDetails,textViewTitle;
    int positon;
    Toolbar toolbar;
    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedmesage);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textViewDetails = (TextView) findViewById(R.id.inbox_detailed_message_tv);
        textViewDetails.setText(getIntent().getExtras().get(Constants.Keys.MESSAGE).toString());

        textViewTitle = (TextView) findViewById(R.id.inbox_detailed_message_title);
        textViewTitle.setText(getIntent().getExtras().get(Constants.Keys.MESSAGE_TITLE).toString());

        positon = getIntent().getIntExtra(Constants.Keys.POSITION, 0);
        databaseHandler= new DatabaseHandler(getApplicationContext());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detailed_important_message, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
            {
                finish();
                break;
            }
            case R.id.delete: {
                createAndShowAlertDialogDelete();
                break;

            }
            case R.id.star: {
                createAndShowAlertDialogStarred();
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void createAndShowAlertDialogDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Delete Message")
                .setMessage("Are you sure?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                 /*       ArrayList<String> messages = CacheModel.getStringList(Constants.Keys.INBOX_LIST);
                        messages.remove(positon);
                        CacheModel.putStringList(Constants.Keys.INBOX_LIST, messages);


                        ArrayList<String> messagesTitle = CacheModel.getStringList(Constants.Keys.INBOX_LIST_TITLE);
                        messagesTitle.remove(positon);
                        CacheModel.putStringList(Constants.Keys.INBOX_LIST_TITLE, messagesTitle);*/
                        databaseHandler.deleteMessage(databaseHandler.getMessage(++positon,"important"),"important");

                        finish();

                    }
                })
                .setNegativeButton("No", null)                        //Do nothing on no
                .show();
    }

    private void createAndShowAlertDialogStarred() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Star Message")
                .setMessage("Are you sure?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                     /*   ArrayList<String> messages = CacheModel.getStringList(Constants.Keys.INBOX_LIST);
                        String message = messages.get(positon);

                        ArrayList<String> messagesTitles = CacheModel.getStringList(Constants.Keys.INBOX_LIST_TITLE);
                        String messageTitle = messagesTitles.get(positon);


                        if (!CacheModel.getStringList(Constants.Keys.STARRED_LIST).isEmpty() && !CacheModel.getStringList(Constants.Keys.STARRED_LIST_TITLE).isEmpty() ) {
                            ArrayList starredList = new ArrayList();
                            starredList = CacheModel.getStringList(Constants.Keys.STARRED_LIST);
                            starredList.add(message);
                            CacheModel.putStringList(Constants.Keys.STARRED_LIST, starredList);

                            ArrayList starredListTitle = new ArrayList();
                            starredListTitle = CacheModel.getStringList(Constants.Keys.STARRED_LIST_TITLE);
                            starredListTitle.add(messageTitle);
                            CacheModel.putStringList(Constants.Keys.STARRED_LIST_TITLE, starredListTitle);

                        }
                        if (CacheModel.getStringList(Constants.Keys.STARRED_LIST).isEmpty() && CacheModel.getStringList(Constants.Keys.STARRED_LIST_TITLE).isEmpty()) {
                            ArrayList starredList = new ArrayList();
                            starredList.add(message);
                            CacheModel.putStringList(Constants.Keys.STARRED_LIST, starredList);

                            ArrayList starredListTitle = new ArrayList();
                            starredListTitle.add(messageTitle);
                            CacheModel.putStringList(Constants.Keys.STARRED_LIST_TITLE, starredListTitle);

                        }*/
                        databaseHandler.addMessage(databaseHandler.getMessage(++positon,"important"),"starred");
                        finish();

                    }
                })
                .setNegativeButton("No", null)                        //Do nothing on no
                .show();
    }
}
