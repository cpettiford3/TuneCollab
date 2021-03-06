package com.example.courtneypettiford.tunecollab.controllers;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.courtneypettiford.tunecollab.R;
import com.example.courtneypettiford.tunecollab.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.models.Artist;

public class RecommendUsersActivity extends Activity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private User currentUser;
    private List<User> recUsers = new LinkedList<>();
    private List<User> rolesInCommon = new LinkedList<>();
    private List<User> genresInCommon = new LinkedList<>();
    private List<User> influencesInCommon = new LinkedList<>();
    private List<User> topArtistsInCommon = new LinkedList<>();
    private Map<User, Integer> savedAlbumsInCommon = new HashMap<>();

    private FirebaseUser user;
    private String userId;


    //with smaller data set and less complex algorithm, these were cutoffs I had for the
    //users to compare similarities
    private static final Integer roleMinimum = 1;
    private static final Integer genreMinimum = 2;
    private static final Integer influencesMinimum = 2;
    private static final Integer artistsMinimum = 2;

    private Button back;
    private ListView recListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_users);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        user = mAuth.getCurrentUser();
        userId = user.getUid();

        determineUserCompatibility();
    }

    private void determineUserCompatibility() {
        mDatabase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<User> users = new ArrayList<>();
                List<String> userNames = new ArrayList<>();

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


                //determines roles in common between two users
                int roleCount = 0;
                for (User user: users) {
                    List otherUserRoles = user.getRoles();
                    for (Object role: rolesLookingFor) {
                        for (Object otherRole: otherUserRoles) {
                            if (role.equals(otherRole)) {
                                roleCount++;
                                if (roleCount >= roleMinimum) {
                                    rolesInCommon.add(user);
                                }
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
                                if (genreCount >= genreMinimum) {
                                    genresInCommon.add(user);
                                }
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
                                if (influencesCount >= influencesMinimum) {
                                    influencesInCommon.add(user);
                                }
                            }
                        }
                    }
                }

                int artistsCount = 0;
                for (User user: users) {
                    List<Artist> otherUsersArtists = user.getMostPlayedSpotifyArtists();
                    for (Artist a: topArtists) {
                        for (Artist otherArtists: otherUsersArtists) {
                            if (a.name.equals(otherArtists.name)) {
                                artistsCount++;
                                if (artistsCount >= artistsMinimum) {
                                    topArtistsInCommon.add(user);
                                }
                            }
                        }
                    }
                }
                for (User u: users) {
                    if (rolesInCommon.contains(u)) {
                        recUsers.add(u);
                    } else if (genresInCommon.contains(u) && influencesInCommon.contains(u)) {
                        recUsers.add(u);
                    } else if (topArtistsInCommon.contains(u) && influences.contains(u)) {
                        recUsers.add(u);
                    }
                }

                for (User user: recUsers) {
                    userNames.add(user.getFirstName() + " " + user.getLastName());
                    userNames.add("Role(s): " + user.getRoles());
                    userNames.add("Genre(s): " + user.getGenres().toString());
                    userNames.add("Influences: " + user.getInfluences());

                }

                ListView listview = findViewById(R.id.list);
                ArrayAdapter arr = new ArrayAdapter<>(RecommendUsersActivity.this, android.R.layout.simple_list_item_1, userNames);
                listview.setAdapter(arr);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

}
