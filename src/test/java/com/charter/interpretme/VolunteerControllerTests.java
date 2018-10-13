package com.charter.interpretme;

import com.charter.interpretme.repository.VolunteerProfileRepository;
import com.charter.interpretme.rest.entity.VolunteerProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VolunteerControllerTests {
    @Mock
    private VolunteerProfileRepository volunteerProfileRepository;

    @InjectMocks
    private VolunteerProfileController controller;

    @Test
    public void findAllReturnsValidList() {
        when(volunteerProfileRepository.findAll()).thenReturn(Arrays.asList(buildProfile()));

        List<VolunteerProfile> profiles = controller.getVolunteerProfiles();
        assertFalse(CollectionUtils.isEmpty(profiles));
        assertThat(profiles.size(), is(1));

        VolunteerProfile profile = profiles.get(0);
        assertThat(profile.getUsername(), is("test1"));
    }

    @Test
    public void findOneReturnsValid() {
        VolunteerProfile profile = buildProfile();
        profile.setId("123");

        when(volunteerProfileRepository.findOne("123")).thenReturn(profile);
        VolunteerProfile foundProfile = controller.findById("123");
        assertThat(foundProfile, is(profile));
    }

    @Test
    public void findOneInvalidIdReturnsEmpty() {
        assertThat(controller.findById("456"), is(nullValue()));
    }

    @Test
    public void testSaveReturnsValid() {
        VolunteerProfile profile = buildProfile();
        profile.setEmailAddress("nobody@idontcare.com");
        profile.setLanguages("Spanish");

        when(volunteerProfileRepository.save(profile)).thenReturn(profile);

        VolunteerProfile savedProfile = controller.saveVolunteer(profile);
        assertThat(savedProfile, is(notNullValue()));
        assertThat(savedProfile.getUsername(), is("test1"));
        assertThat(savedProfile.getLanguages(), is("Spanish"));
    }

    @Test
    public void testDeleteReturnsValid() {
        doNothing().when(volunteerProfileRepository).delete("1");
        controller.deleteProfile("1");
        verify(volunteerProfileRepository).delete("1");
    }

    @Test
    public void testEditReturnsValid() {
        VolunteerProfile profile = buildProfile();
        profile.setAverageRating(5.0);

        when(volunteerProfileRepository.save(any(VolunteerProfile.class))).thenReturn(profile);
        VolunteerProfile response = controller.editVolunteer("1", profile);
        assertThat(response, is(notNullValue()));
        assertThat(response, is(profile));
    }

    private VolunteerProfile buildProfile() {
        return new VolunteerProfile("1","test1", "Test", "User", "German, Italian",
                "123 Nowhere St.", "", "Charlotte", "NC", "28277",
                "here@there.com", "704-555-1212", 0.0, "", 33,
                "Male", false);
    }
}
