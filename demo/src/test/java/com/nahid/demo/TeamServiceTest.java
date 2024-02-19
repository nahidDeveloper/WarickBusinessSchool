package com.nahid.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.*;


import com.nahid.demo.team.Team;
import com.nahid.demo.team.TeamRepository;
import com.nahid.demo.team.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    private List<Team> mockTeams;

    @Before
    public void setUp() {
        mockTeams = new ArrayList<>();
        mockTeams.add(new Team(1, "Team1"));
        mockTeams.add(new Team(2, "Team2"));
    }

    @Test
    public void testGetAllTeams() {
        when(teamRepository.findAll()).thenReturn(mockTeams);

        List<Team> teams = teamService.getAllTeams();

        assertEquals(mockTeams.size(), teams.size());

    }


}
