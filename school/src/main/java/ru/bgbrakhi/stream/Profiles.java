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
        List<Address> result = profiles.
                stream().map(profile -> profile.getAddress()).collect(Collectors.toList()).
                stream().distinct().collect(Collectors.toList());
        result.sort(new Comparator<Address>() {
                    @Override
                    public int compare(Address o1, Address o2) {
                        return o1.city.compareTo(o2.city);
                    }
                });
        return result;
    }

    public static void main(String[] args) {
        Profiles profiles = new Profiles();
        List<Address> addresses = profiles.collect(profiles.profiles);
    }
}
