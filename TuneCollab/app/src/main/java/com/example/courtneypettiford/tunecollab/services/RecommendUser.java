package com.example.courtneypettiford.tunecollab.services;

import com.example.courtneypettiford.tunecollab.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by courtneypettiford on 4/20/18.
 */

public class RecommendUser {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private List<User> users = new ArrayList<>();
    private User currentUser;
    private List<User> recUsers = new ArrayList<>();
    private List<String> usersFirstNames = new ArrayList<>();
    private List<User> rolesInCommon = new LinkedList<>();
    private List<User> genresInCommon = new LinkedList<>();
    private List<User> influencesInCommon = new LinkedList<>();
    private List<User> topArtistsInCommon = new LinkedList<>();

    private static final Integer roleMinimum = 1;
    private static final Integer genreMinimum = 1;
    private static final Integer influencesMinimum = 1;
    private static final Integer artistsMinimum = 1;

    public RecommendUser() {

    }
    public List<User> determineUserCompatibility() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


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
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

        });

        return recUsers;
    }
}
