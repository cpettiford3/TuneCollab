package com.example.courtneypettiford.tunecollab.controllers;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.courtneypettiford.tunecollab.R;
import com.example.courtneypettiford.tunecollab.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.models.Album;
import kaaes.spotify.webapi.android.models.Artist;

public class RecommendUsersActivity extends Activity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private List<User> users = new ArrayList<>();
    private User currentUser;
    private List<User> recUsers = new ArrayList<>();
    private Map<User, Integer> rolesInCommon = new HashMap<>();
    private Map<User, Integer> genresInCommon = new HashMap<>();
    private Map<User, Integer> influencesInCommon = new HashMap<>();
    private Map<User, Integer> topArtistsInCommon = new HashMap<>();
    private Map<User, Integer> savedAlbumsInCommon = new HashMap<>();

    private Button back;
    private ListView recListView;
    private Gson g = new Gson();
    String artistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_users);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        back = findViewById(R.id.backFromRecommendedUsers);
        recListView = findViewById(R.id.recUsesListView);

        determineUserCompatibility();

//        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
//        recListView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecommendUsersActivity.this, MainActivity.class));
            }
        });
    }

    private void determineUserCompatibility() {

        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (mAuth.getCurrentUser() != null) {
                    for (DataSnapshot ds: dataSnapshot.getChildren()) {
                        User user = ds.getValue(User.class);
                        if (user != null) {
                            if (!mAuth.getCurrentUser().getUid().equals(user.getId())) {
                                users.add(user);
                            } else {
                                currentUser = user;
                            }
                        }
                    }
                }


                List rolesLookingFor = currentUser.getInterests();
                List genres = currentUser.getGenres();
                List influences = currentUser.getInfluences();
                List<Artist> topArtists = currentUser.getMostPlayedSpotifyArtists();
                List<Album> topAlbums = currentUser.getSavedAlbums();


                //first rounds of elimination --check to see if user role requirements that meets
                //the user's requested needs

                //determines roles in common between two users
                for (User user: users) {
                    List otherUserRoles = user.getRoles();
                    for (Object role: rolesLookingFor) {
                        for (Object otherRole: otherUserRoles) {
                            int i = 0;
                            if (role.equals(otherRole)) {
                                i++;
                                rolesInCommon.put(user, i);
                            }
                        }
                    }
                }

                //determines genres in common between two users
                int genreCount = 0;
                for (User user: users) {
                    List otherUserGenres = user.getGenres();
                    for (Object genre: genres) {
                        for (Object otherGenre: otherUserGenres) {
                            int i = 0;
                            if (genre.equals(otherGenre)) {
                                genreCount++;
                                genresInCommon.put(user, genreCount);
                            }
                        }
                    }
                }

                //determines influences in common between two users
                int influencesCount = 0;
                for (User user: users) {
                    List otherUserInfluences = user.getInfluences();
                    for (Object influence: influences) {
                        for (Object otherInfluence: otherUserInfluences) {
                            if (influence.equals(otherInfluence)) {
                                influencesCount++;
                                influencesInCommon.put(user, influencesCount);
                            }
                        }
                    }
                }


                int artistsInCommon = 0;
                for (User user: users) {
                    List<Artist> otherUsersArtists = user.getMostPlayedSpotifyArtists();
                    for (Artist a: topArtists) {
                        for (Artist otherArtists: otherUsersArtists) {
                            if (a.name.equals(otherArtists.name)) {
                                artistsInCommon++;
                                topArtistsInCommon.put(user, artistsInCommon);
                            }
                        }
                    }
                }

//                int albumsInCommon = 0;
//                for (User user: users) {
//                    List<Album> otherUsersAlbums = user.getSavedAlbums();
//                    for (Album album: topAlbums) {
//                        for (Album otherAlbums: otherUsersAlbums) {
//                            if (album.name.equals(otherAlbums.name)) {
//                                albumsInCommon++;
//                                savedAlbumsInCommon.put(user, albumsInCommon);
//                            }
//                        }
//                    }
//                }
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

}
