package com.gil.school.api.model.Validators;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    // Custom validation annotation
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = WebsiteURLValidator.class)
    public @interface ValidWebsiteURL {
        String message() default "Invalid website URL";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {}; // Here you define payload()
    }

    // Custom validator class
    public static class WebsiteURLValidator implements ConstraintValidator<ValidWebsiteURL, String> {
        private static final String URL_REGEX = "^(https?|ftp)://[^\s/$.?#].[^\s]*$";

        @Override
        public void initialize(ValidWebsiteURL constraintAnnotation) {}

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null) {
                return true; // null values are considered valid
            }

            Pattern pattern = Pattern.compile(URL_REGEX);
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
    }


    // Location




    @Target({ElementType.FIELD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = ValidLocationValidator.class)
    public @interface ValidLocation {
        String message() default "Invalid location coordinates";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }


    public static class ValidLocationValidator implements ConstraintValidator<ValidLocation, String> {

        @Override
        public void initialize(ValidLocation constraintAnnotation) {
        }

        @Override
        public boolean isValid(String location_school, ConstraintValidatorContext context) {
            String location = location_school;

            if (isValidCoordinates(location)) {
                return true;
            } else {
                return false;
            }
        }

        private boolean isValidCoordinates(String location) {
            // Split the combined location string into latitude and longitude
            if( location!=null){
            String[] coordinates = location.split(",");

            if (coordinates.length != 2) {
                return false; // Invalid format, not exactly two coordinates
            }

            try {
                double latitude = Double.parseDouble(coordinates[0]);
                double longitude = Double.parseDouble(coordinates[1]);

                // Perform reverse geocoding using Nominatim API
                String nominatimApiUrl = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + latitude + "&lon=" + longitude;
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(nominatimApiUrl);
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    String responseBody = EntityUtils.toString(entity);
                    JSONObject jsonObject = new JSONObject(responseBody);

                    if (jsonObject.has("display_name")) {
                        return true; // The location is valid and exists in the real world
                    }
                }
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }}

            return false; // The location is not valid or could not be verified
        }
    }
}
