package org.alexv.vet_manager_api.commons.utils;

import java.util.Arrays;

public class NameUtils {
    public static String[] splitFullName(String fullName) {
        String[] nameParts = fullName.split(" ");
        if (nameParts.length > 2) {
            String lastName = String.join(" ", Arrays.copyOfRange(nameParts, 1, nameParts.length));
            return new String[]{nameParts[0], lastName};
        } else {
            return nameParts;
        }
    }
}
