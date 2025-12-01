package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.hc.client5.http.fluent.Executor;
import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.testng.annotations.Test;
import org.apache.hc.client5.http.fluent.Request;


import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests {

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
/* 2015
        String json = getExecutor()
                .execute(Request.get("http://demo.bugify.com/api/issues.json"))
                .returnContent()
                .asString();
        JsonElement parsed = new JsonParser().parse(json); //deprecated метод

 */
        String json = Request.get("http://demo.bugify.com/api/issues.json")
                .addHeader("Authorization", "Bearer LSGjeU4yP1X493ud1hNniA==")
                .execute()
                .returnContent()
                .asString();

        JsonElement parsed = com.google.gson.JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        return new Gson()
                .fromJson(issues, new TypeToken<Set<Issue>>() {
                }.getType());
    }

    /* 2015
    private Executor getExecutor() {

        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
    }
*/

    private int createIssue(Issue newIssue) throws IOException {
      /* 2015
        String json = getExecutor()
                .execute(Request
                        .post("http://demo.bugify.com/api/issues.json")
                        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                                new BasicNameValuePair("description", newIssue.getDescription())))
        JsonElement parsed = new JsonParser().parse(json);
*/

        String json = Request.post("http://demo.bugify.com/api/issues.json")
                .addHeader("Authorization", "Bearer LSGjeU4yP1X493ud1hNniA==")
                .bodyForm(Form.form()
                        .add("subject", newIssue.getSubject())
                        .add("description", newIssue.getDescription())
                        .build())
                .execute()
                .returnContent().asString();

        JsonElement parsed = com.google.gson.JsonParser.parseString(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}