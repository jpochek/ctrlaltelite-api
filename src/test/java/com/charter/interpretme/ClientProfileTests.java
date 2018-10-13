package com.charter.interpretme;

import com.charter.interpretme.repository.ClientProfileRepository;
import com.charter.interpretme.rest.entity.ClientProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientProfileTests {
    @Mock
    private ClientProfileRepository clientProfileRepository;

    @InjectMocks
    private ClientProfileController controller;

    @Test
    public void findAllReturnsValid() {
        when(clientProfileRepository.findAll()).thenReturn(Arrays.asList(buildProfile()));
        List<ClientProfile> profiles = controller.getAllClients();

        assertFalse(CollectionUtils.isEmpty(profiles));
        assertThat(profiles.size(), is(1));

    }

    @Test
    public void findByIdReturnsValid() {
        ClientProfile profile = buildProfile();

        when(clientProfileRepository.findOne("1")).thenReturn(profile);

        ClientProfile response = controller.getClientById("1");
        assertThat(response, is(notNullValue()));
        assertThat(response, is(profile));

    }

    @Test
    public void findByInvalidIdReturnsNull() {
        assertThat(controller.getClientById("2"), is(nullValue()));
    }

    @Test
    public void saveNewReturnsValid() {
        ClientProfile profile = buildProfile();
        when(clientProfileRepository.save(Matchers.any(ClientProfile.class))).thenReturn(profile);
        ClientProfile response = controller.addClientProfile(profile);
        assertThat(response, is(notNullValue()));
        assertThat(response, is(profile));
    }

    @Test
    public void saveExistingReturnsValid() {
        ClientProfile profile = buildProfile();
        profile.setAge(21);
        when(clientProfileRepository.save(Matchers.any(ClientProfile.class))).thenReturn(profile);
        ClientProfile response = controller.editClientProfile("1", profile);
        assertThat(response, is(notNullValue()));
        assertThat(response, is(profile));
    }

    @Test
    public void deleteExisting() {
        doNothing().when(clientProfileRepository).delete("1");
        controller.deleteProfile("1");
        verify(clientProfileRepository).delete("1");
    }

    public ClientProfile buildProfile() {
        return new ClientProfile("1", "testClient", "Test", "Client",
                "English,German,Italian,Portuguese", "123 Anywhere Ave.", "",
                "St. Louis", "MO", "12345", "cantbebothered@test.com",
                "123-456-7890", 0.0, "", 50, "Male", false);
    }
}
