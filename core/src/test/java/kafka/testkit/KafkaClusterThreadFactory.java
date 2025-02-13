/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kafka.testkit;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class KafkaClusterThreadFactory implements ThreadFactory {
    private final String prefix;
    private final Set<Long> threadIds = ConcurrentHashMap.newKeySet();
    private final AtomicLong threadEpoch = new AtomicLong(0);

    KafkaClusterThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        String threadName = prefix + threadEpoch.addAndGet(1);
        Thread thread = new Thread(r, threadName);

        threadIds.add(thread.getId());
        return thread;
    }

    public Set<Long> getThreadIds() {
        return threadIds;
    }
}
