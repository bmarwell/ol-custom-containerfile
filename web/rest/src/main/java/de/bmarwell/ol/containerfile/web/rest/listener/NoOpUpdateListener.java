/*
 * Copyright (C) 2024.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bmarwell.ol.containerfile.web.rest.listener;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import jakarta.inject.Singleton;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
@Singleton
public class NoOpUpdateListener implements ServletContextListener {

    private Logger log = LoggerFactory.getLogger(NoOpUpdateListener.class);

    @Resource
    private ManagedScheduledExecutorService executor;

    public NoOpUpdateListener() {
        // cdi
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.executor.scheduleAtFixedRate(() -> greet(), 5L, 20L, TimeUnit.SECONDS);
    }

    private void greet() {
        log.info("Hello from " + this.getClass().getCanonicalName());
    }

    public void setExecutor(ManagedScheduledExecutorService executor) {
        this.executor = executor;
    }
}
