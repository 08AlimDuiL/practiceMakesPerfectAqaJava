package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests {

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
    }

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue()
                .withSubject("Test issue")
                .withDescription("New test issue");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

    private Set<Issue> getIssues() throws IOException {

        String json = Request.get("http://demo.bugify.com/api/issues.json")
                .addHeader("Authorization", "Bearer LSGjeU4yP1X493ud1hNniA==")
                .execute()
                .returnContent()
                .asString();

        String jsonAssured = RestAssured
                .get("http://demo.bugify.com/api/issues.json")
                .asString();
        JsonElement parsed = JsonParser.parseString(jsonAssured);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        return new Gson()
                .fromJson(issues, new TypeToken<Set<Issue>>() {
                }.getType());
    }

    private int createIssue(Issue newIssue) throws IOException {

        String json = Request.post("http://demo.bugify.com/api/issues.json")
                .addHeader("Authorization", "Bearer LSGjeU4yP1X493ud1hNniA==")
                .bodyForm(Form.form()
                        .add("subject", newIssue.getSubject())
                        .add("description", newIssue.getDescription())
                        .build())
                .execute()
                .returnContent().asString();

        String jsonAssured = RestAssured
                .given()
                .param("subject", newIssue.getSubject())
                .param("description", newIssue.getDescription())
                .post("http://demo.bugify.com/api/issues.json")
                .asString();
        JsonElement parsed = JsonParser.parseString(jsonAssured);

        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}