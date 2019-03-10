package ru.bgbrakhi.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    List<Profile> profiles = new ArrayList<>();

    public Profiles() {
        for (int i = 0; i < 10; i++) {
            profiles.add(new Profile(new Address("city" + i, "street" + i, i + 1, i + 1)));
        }
    }

    public List<Address> collect(List<Profile> profiles) {
        profiles.sort(new Comparator<Profile>() {
            @Override
            public int compare(Profile p1, Profile p2) {
                return p1.getAddress().city.compareTo(p2.getAddress().city);
            }
        });
        return profiles.
                stream().map(profile -> profile.getAddress()).collect(Collectors.toList()).
                stream().distinct().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Profiles profiles = new Profiles();
        List<Address> addresses = profiles.collect(profiles.profiles);
    }
}
