package com.learn.advisor;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;

@Slf4j
public class SampleAdvisor implements CallAdvisor {

    @Override @NonNull
    public ChatClientResponse adviseCall(@NonNull ChatClientRequest chatClientRequest,
                                         @NonNull CallAdvisorChain callAdvisorChain) {
        log.info(chatClientRequest.prompt().getUserMessages().toString());
        return callAdvisorChain.nextCall(chatClientRequest);
    }

    @Override @NonNull
    public String getName() {
        return SampleAdvisor.class.getSimpleName();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
