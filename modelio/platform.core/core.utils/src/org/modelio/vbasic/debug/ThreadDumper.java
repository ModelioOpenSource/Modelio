/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.vbasic.debug;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Map.Entry;
import java.util.function.Consumer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Debugging utility class that can dump thread stack traces with java locks informations.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("92acb7f3-6b2c-46dd-9561-8a88546ad260")
public class ThreadDumper {
    @objid ("1f4e41a9-f518-41f4-9a6e-2e56a571a085")
    private final boolean synchronizerUsageSupported;

    @objid ("c1afe7b7-35ae-4cc3-b123-d459c974097d")
    private final boolean objectMonitorUsageSupported;

    @objid ("416eb805-fcf9-40c4-8a74-842a8eedd189")
    private final ThreadMXBean mbean;

    @objid ("fc70fcbc-d71c-4052-accf-0f9a107a93e3")
    private static ThreadDumper instance;

    /**
     * Constructor.
     * @deprecated use {@link #get()} singleton instead.
     */
    @objid ("f3f2f5fb-f4d4-43dd-a0ae-7e67773c89d3")
    @Deprecated
    public ThreadDumper() {
        this.mbean = ManagementFactory.getThreadMXBean();
        this.synchronizerUsageSupported = this.mbean.isSynchronizerUsageSupported();
        this.objectMonitorUsageSupported = this.mbean.isObjectMonitorUsageSupported();
    }

    /**
     * Dump all thread stack trace with java lock informations if asked for.
     * @param withLocks dump monitor and Lock informations
     * @return the found thread informations.
     */
    @objid ("14742561-71e9-4f5f-abc6-cb7a5a475755")
    public Result getAllThreads(boolean withLocks) {
        ThreadInfo[] threadInfos = this.mbean.dumpAllThreads(
                withLocks && this.objectMonitorUsageSupported,
                withLocks && this.synchronizerUsageSupported);
        return new Result(threadInfos);
    }

    /**
     * Look for dead locked threads and dump their stack trace with java lock informations.
     * <p>
     * If no dead locked thread is found dump all threads .
     * @return the found thread informations.
     */
    @objid ("0b09375d-3ceb-41e4-afab-cd6a3fcb3edc")
    public Result getDeadLocks() {
        long[] deadlockedThreadIds = this.synchronizerUsageSupported ? 
                this.mbean.findDeadlockedThreads() : null;
        
        ThreadInfo[] threadInfos;
        if (deadlockedThreadIds != null) {
            //System.err.println("Deadlock detected!");
            threadInfos = this.mbean.getThreadInfo(
                    deadlockedThreadIds, 
                    this.objectMonitorUsageSupported,
                    this.synchronizerUsageSupported);
        
        } else {
            threadInfos = this.mbean.dumpAllThreads(
                    this.objectMonitorUsageSupported,
                    this.synchronizerUsageSupported);
        }
        return new Result(threadInfos);
    }

    /**
     * Get the stack trace of all threads at the time of the call into a String.
     * <p>
     * The result contains no lock information and no thread state.
     * See {@link Thread#getAllStackTraces()} for more informations.
     * @return all threads stack trace.
     */
    @objid ("dd54edb8-f452-4f94-8b51-7426b99bb29e")
    public static String getThreadStackTraces() {
        StringBuilder s = new StringBuilder(16000);
        s.append("Thread dump:\n");
        for (Entry<Thread, StackTraceElement[]>  entry : Thread.getAllStackTraces().entrySet()) {
            s.append(entry.getKey().toString());
            s.append(":\n");
            for (StackTraceElement st : entry.getValue()) {
                s.append("   at ");
                s.append(st.toString());
                s.append("\n");
            }
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * @return the singleton instance.
     */
    @objid ("fe3f9450-5931-4056-b28e-d9a11a673c62")
    public static ThreadDumper get() {
        // Note : there is no synchronization because we don't mind 2 concurrent instances at a time.
        if (instance==null) {
            instance = new ThreadDumper();
        }
        return instance;
    }

    /**
     * Thread dump result.
     * 
     * @author cma
     */
    @objid ("732fc3df-88e0-4d0e-be69-b27bb1932077")
    public static class Result {
        @objid ("3ac5d0b6-92f2-4a56-be9a-3234dda9251c")
         final ThreadInfo[] threads;

        @objid ("4107ed27-e946-4cc4-9e66-3450590a6a7f")
        Result(ThreadInfo[] threads) {
            this.threads = threads;
        }

        /**
         * Dump line by line.
         * @param lineConsumer a consumer that will receive all text lines
         */
        @objid ("952a0cda-cd19-4cc6-959f-4cbf13499637")
        public void dump(Consumer<String> lineConsumer) {
            if (this.threads != null) {
                for (ThreadInfo threadInfo : this.threads) {
                    if (threadInfo != null) {
                        for(String s : threadInfo.toString().split("\n")) {
                            lineConsumer.accept(s);
                        }
                    }
                }
            }
        }

        /**
         * Add a suppressed exception for each thread
         * @param target the throwable to add suppressed exceptions to.
         * @return target for convenience
         */
        @objid ("5a8ccc2c-9b5e-44f3-b751-c6f3e65205ad")
        public <T extends Throwable> T addAsSupressed(T target) {
            if (this.threads != null) {
                for (ThreadInfo i : this.threads) {
                    Throwable t = new Throwable(i.toString());
                    t.setStackTrace(new StackTraceElement[0]);
                    target.addSuppressed(t);
                }
            }
            return target;
        }

        /**
         * Get the stack trace of all threads.
         * @return all threads stack trace.
         */
        @objid ("248b78bf-057e-48f0-a1f4-df06ea46e03c")
        @Override
        public String toString() {
            StringBuilder s = new StringBuilder(16000);
            s.append("Threads dump:\n");
            for (ThreadInfo i : this.threads) {
                s.append(i.toString());
                s.append("\n");
            }
            return s.toString();
        }

    }

}
