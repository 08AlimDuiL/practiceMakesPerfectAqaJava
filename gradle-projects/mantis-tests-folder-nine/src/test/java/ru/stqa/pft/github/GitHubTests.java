package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {
    /*
        private static final String TOKEN_CLASSIC = "IDEA не дает использовать токен";

        @Test
        public void testCommits() throws IOException {
            Github github = new RtGithub(TOKEN_CLASSIC);
            RepoCommits commits = github.repos().get(new Coordinates.Simple("08AlimDuiL", "practiceMakesPerfectAqaJava")).commits();
            for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
                System.out.println(new RepoCommit.Smart(commit).message());
            }
        }
    */
    @Test
    public void testCommits() throws IOException {
        String token = System.getenv("GITHUB_TOKEN");
        if (token == null) {
            throw new IllegalStateException("GITHUB_TOKEN environment variable is not set");
        }

        Github github = new RtGithub(token);
        RepoCommits commits = github.repos().get(new Coordinates.Simple("08AlimDuiL", "practiceMakesPerfectAqaJava")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}