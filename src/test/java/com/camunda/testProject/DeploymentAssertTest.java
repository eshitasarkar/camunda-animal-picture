package com.camunda.testProject;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivateJobsResponse;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.response.DeploymentEvent;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.process.test.api.ZeebeTestEngine;
import io.camunda.zeebe.process.test.assertions.BpmnAssert;
import io.camunda.zeebe.process.test.assertions.DeploymentAssert;
import io.camunda.zeebe.process.test.assertions.JobAssert;
import io.camunda.zeebe.process.test.assertions.ProcessInstanceAssert;
import io.camunda.zeebe.process.test.extension.testcontainer.ZeebeProcessTest;
import io.camunda.zeebe.process.test.filters.RecordStream;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


@ZeebeProcessTest
public class DeploymentAssertTest {

    @Autowired
    private ZeebeTestEngine engine;
    @Autowired
    private ZeebeClient client;

    @Autowired
    private RecordStream recordStream;

    @Test
    public void testDeployBpmn() throws  Exception{
        DeploymentEvent event = client.newDeployCommand()
                .addResourceFromClasspath("animal-pictures-id.bpmn")
                .send()
                .join();
        DeploymentAssert assertions = BpmnAssert.assertThat(event);
    }
    @Test
    public void testStartProcessInstance() throws Exception {
        ProcessInstanceEvent event = client.newCreateInstanceCommand()
                .bpmnProcessId("animal-pictures-id")
                .latestVersion()
                .send()
                .join();
        ProcessInstanceAssert assertions = BpmnAssert.assertThat(event);
    }

    @Test
    public void testJobAssertions() throws Exception {
        ActivateJobsResponse response = client.newActivateJobsCommand()
                .jobType("find-selected-picture")
                .maxJobsToActivate(1)
                .send()
                .join();
        ActivatedJob activatedJob = response.getJobs().get(0);
        JobAssert assertions = BpmnAssert.assertThat(activatedJob);
    }


}
