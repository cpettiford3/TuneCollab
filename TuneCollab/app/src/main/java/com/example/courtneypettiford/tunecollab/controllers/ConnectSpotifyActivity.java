package com.example.courtneypettiford.tunecollab.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.courtneypettiford.tunecollab.R;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyCallback;
import kaaes.spotify.webapi.android.SpotifyError;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistSimple;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.SavedAlbum;
import kaaes.spotify.webapi.android.models.Track;
import retrofit.client.Response;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ConnectSpotifyActivity extends Activity {

    private static final String CLIENT_ID = "a0e3f05ecef04be3861c7c4cde343b12";
    private static final String REDIRECT_URI = "http://www.tunecollab.com/callback/";

    private static final int REQUEST_CODE = 1337;

    private String accessToken;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    String userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI).setShowDialog(true);


        builder.setScopes(new String[]{"user-read-private", "streaming", "playlist-read-private",
        "user-library-read", "user-top-read"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userId = user.getUid();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    accessToken = response.getAccessToken();
                    getListOfCurrentUsersPlaylists();
                    break;

                // Auth flow returned an error
                case ERROR:
                    Toast.makeText(getApplicationContext(), response.getError(), Toast.LENGTH_SHORT).show();
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
    }


    //organize into separate methods?
    private void getListOfCurrentUsersPlaylists() {
        SpotifyApi api = new SpotifyApi();

        api.setAccessToken(accessToken);

        SpotifyService spotify = api.getService();

        spotify.getTopArtists(new SpotifyCallback<Pager<Artist>>() {
            @Override
            public void success(Pager<Artist> artist, retrofit.client.Response response) {
                Log.d("Success", artist.items.toString());
                mDatabase.child(userId).child("mostPlayedSpotifyArtists").setValue(artist.items);
            }

            @Override
            public void failure(SpotifyError error) {
                Log.d("Failure", error.toString());
            }
        });

        spotify.getMySavedAlbums(new SpotifyCallback<Pager<SavedAlbum>>() {
            @Override
            public void success(Pager<SavedAlbum> savedAlbum, retrofit.client.Response response) {
                Log.d("Saved album success", savedAlbum.toString());
                mDatabase.child(userId).child("savedAlbums").setValue(savedAlbum.items);
            }

            @Override
            public void failure(SpotifyError error) {
                Log.d("Saved album failure", error.toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
