/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.gproject.gproject.lock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.ILockInfo;
import org.modelio.gproject.gproject.GProjectLockedException;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.files.CloseOnFail;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;

/**
 * Used to prevent a project from being opened by many Modelio instances at once.
 * <p>
 * There is only one instance of ProjectLock per project directory, because Linux JVM releases the lock if any related or not
 * Channel or InputStream towards the locked file is closed.
 * <p>
 * <h3>Notes</h3>
 * From "https://www.securecoding.cert.org/confluence/display/java/FIO00-J.+Do+not+operate+on+files+in+shared+directories" :
 * <p>
 * Linux implements both mandatory locks and advisory locks. Advisory locks are not enforced by the operating system,
 * which diminishes their value from a security perspective.
 * Unfortunately, the mandatory file lock in Linux is generally impractical because<ul>
 * <li> mandatory locking is supported only by certain network file systems.
 * <li> file systems must be mounted with support for mandatory
 * locking, which is disabled by default.
 * <li> locking relies on the group ID bit, which can be turned off by another process (thereby
 * defeating the lock).
 * <li> <b>the lock is implicitly dropped if the holding process closes any descriptor of the file. </b>
 * </ul>
 */
@objid ("d1972049-fe6a-4be9-a3fe-e80275ae8bec")
public class ProjectLock {
    @objid ("7e49cb68-e341-4a92-b785-d3121c1f3a15")
    private String projectName;

    @objid ("2b70f8e4-7fb4-4fd6-8a40-b6091c383626")
    private FileChannel channel;

    @objid ("8056d9e4-f4fb-42d0-872d-019ea8261060")
    private FileLock lock;

    @objid ("029ada60-1b83-4dda-b698-e8e0013f63bd")
    private final Path lockFile;

    @objid ("1bdfd6ea-4b89-4fc9-8984-4b7483fd755f")
    private final Path lockInfoFile;

    /**
     * To allow only one instance for a given project
     */
    @objid ("8d15560b-568f-4698-a10d-b5fa4fdb0b52")
    private static final Map<Path, WeakReference<ProjectLock>> instances = new HashMap<>();

    /**
     * Initialize the lock.
     * <p>
     * Call {@link #lock()} to acquire the lock.
     * 
     * @param directory the lock directory.
     * @param projectName the project name.
     */
    @objid ("fe4c5ce8-659e-4f77-b296-82424bebbd85")
    private ProjectLock(Path directory, String projectName) {
        this.projectName = projectName;
        this.lockFile = directory.resolve("lock.dat");
        this.lockInfoFile = directory.resolve("lock.info");
    }

    /**
     * Release the lock.
     * 
     * @throws java.io.IOException If an I/O error occurs
     */
    @objid ("a6f9da52-06de-496f-bdc4-c27c0291c903")
    public synchronized void close() throws IOException {
        if (this.lock != null)  {
            IOException err = null;
        
            try {
                this.lock.release();
            } catch (IOException e) {
                err = e;
            }
        
            if (this.channel != null) {
                try {
                    this.channel.close();
                    if (Files.isRegularFile(this.lockFile)) {
                        Files.delete(this.lockFile);
                    }
              
                    this.channel = null;
                } catch (IOException e) {
                    if (err != null) {
                        err.addSuppressed(e);
                    } else {
                        err = e;
                    }
                }
            }
        
            try {
                if (Files.isRegularFile(this.lockInfoFile)) {
                    Files.delete(this.lockInfoFile);
                }
            } catch (IOException e) {
                if (err != null) {
                    err.addSuppressed(e);
                } else {
                    Log.warning(e);
                }
            }
        
            if (err == null) {
                this.lock = null;
            } else {
                throw err;
            }
        }
    }

    /**
     * Acquires the lock.
     * 
     * @throws org.modelio.gproject.gproject.GProjectLockedException if the project is already open somewhere else or this lock instance is already acquired.
     * @throws java.io.IOException in case of I/O failure
     */
    @objid ("709d2133-0d55-4318-a2c8-c06f58ceccd0")
    public synchronized void lock() throws GProjectLockedException, IOException {
        if (this.lock != null || this.channel != null) {
            String msg = CoreProject.I18N.getMessage("ProjectLock.sameVm", this.projectName);
            throw new GProjectLockedException(msg, new IllegalStateException(msg));
        }
        
        // Ensure directory exists
        Files.createDirectories(this.lockFile.getParent());
        
        // Open or create the file
        this.channel = FileChannel.open(this.lockFile, StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        
        try (CloseOnFail shield = new CloseOnFail(this.channel)){
            this.lock = this.channel.tryLock(0, Long.MAX_VALUE, false);
            if (this.lock == null) {
                // Project locked, throw exception
                throw createLockException();
            } else {
                // Project not yet locked.
                // Build a LockInfo
                String jvmIdentifier = getJvmId();
                String user = System.getProperty("user.name");
                LockInfo lockInfo = new LockInfo(true, user, getHostName(), jvmIdentifier, Calendar.getInstance().getTime());
        
                // Write the lock info
                try (OutputStream out = Files.newOutputStream(this.lockInfoFile)) { 
                    lockInfo.toProperties().store(out, "Project lock informations");
                }
        
                // keep the channel open on success to keep the lock
                shield.success();
            }
        } catch (GProjectLockedException e) {
            // prevent GProjectLockedException to be caught as IOException
            throw e;
        } catch (OverlappingFileLockException e) {
            throw new GProjectLockedException(CoreProject.I18N.getMessage("ProjectLock.sameVm", this.projectName), e);
        } catch (IOException e) {
            throw new IOException(CoreProject.I18N.getMessage("ProjectLock.failure", this.projectName, FileUtils.getLocalizedMessage(e)), e);
        }
    }

    /**
     * Test whether the project is locked
     * 
     * @return a lock information if the project is locked else <i>null</i>.
     * @throws java.io.IOException in case of I/O error.
     */
    @objid ("ffa48c7f-6b33-4cbe-a7f6-1f0d3651c268")
    public synchronized ILockInfo test() throws IOException {
        if (this.lock != null) {
            // Locked in this VM
            return getLockInfo();
        }
        
        try (FileChannel c = FileChannel.open(this.lockFile, StandardOpenOption.READ, StandardOpenOption.WRITE);
                FileLock l = c.tryLock(0, Long.MAX_VALUE, false);){
        
            if (l == null) {
                return getLockInfo();
            }
        
            return null;
        } catch (NoSuchFileException | FileNotFoundException e) {
            // No lock
            return null;
        } catch (OverlappingFileLockException e) {
            // Locked in same VM
            return getLockInfo();
        }
    }

    @objid ("3d0b7117-836b-450d-aaa8-d76d907ced65")
    private String getHostName() {
        String hostname = "Unknown host";
        
        try {
            InetAddress addr = InetAddress.getLocalHost();
            //hostname = addr.getHostName();
            return addr.getCanonicalHostName();
        } catch (UnknownHostException ex) {
            Log.trace(ex);
        }
        return hostname;
    }

    @objid ("6025a0fc-f8af-4085-9df5-b355e8b157c0")
    private String getJvmId() {
        // found on https://svn.apache.org/repos/asf/chukwa/tags/chukwa-0.4.0/src/java/org/apache/hadoop/chukwa/util/PidFile.java
        // see http://stackoverflow.com/questions/35842/how-can-a-java-program-get-its-own-process-id
        // get a string like "26574@MachineName"
        String jvmId = ManagementFactory.getRuntimeMXBean().getName();
        
        //String[] items = pidLong.split("@");
        //String processId = items[0];
        return jvmId;
    }

    @objid ("da981f60-b696-45b8-97bb-71025f2cde36")
    private ILockInfo getLockInfo() throws IOException {
        Properties props = new Properties();
        
        try (InputStream is = Files.newInputStream(this.lockInfoFile);) {
            props.load(is);
        } catch (FileNotFoundException | NoSuchFileException e) {
            return null;
        }
        return new LockInfo(props, getJvmId());
    }

    @objid ("eede210e-0b96-4f22-b4f2-5708b7fd9853")
    private GProjectLockedException createLockException() {
        try {
            
            ILockInfo info = getLockInfo();
            
            String msg;
            if (info.isSelf()) {
                msg = CoreProject.I18N.getMessage("ProjectLock.sameVm", this.projectName);
            } else {
                msg = CoreProject.I18N.getMessage("ProjectLock.otherVm", this.projectName, 
                        info.getJvmIdentifier(),
                        info.getHostName(), 
                        info.getOwner(), 
                        info.getDate());
            }
            
            return new GProjectLockedException(msg, info);
        } catch (IOException e) {
            return new GProjectLockedException(FileUtils.getLocalizedMessage(e), e);
        }
    }

    /**
     * Get a project lock for a project directory.
     * <p>
     * Call {@link #lock()} then to acquire the lock.
     * 
     * @param directory the lock directory.
     * @param projectName the project name.
     * @return the matching project lock
     * @throws java.io.IOException if the path cannot be resolved
     */
    @objid ("8a2e5430-e8fa-4830-a0cc-26e806dc0947")
    public static ProjectLock get(Path directory, String projectName) throws IOException {
        Files.createDirectories(directory);
        
        Path realDir = directory.toRealPath();
                
        synchronized (instances) {
            WeakReference<ProjectLock> ref = instances.get(realDir);
            ProjectLock instance = ref != null ? ref.get() : null;
            
            if (instance == null) {
                instance = new ProjectLock(realDir, projectName);
                instances.put(realDir, new WeakReference<>(instance));
            }
            
            return instance;
        }
    }

    @objid ("bad37bb5-a194-4dbd-8664-56a1bd1e692f")
    @Override
    protected void finalize() throws Throwable {
        synchronized(instances) {
            try {
                close();
            } catch (Exception e) {
                Log.trace("Failed closing "+this.lockFile+" lock on finalization:"+e.toString());
                Log.trace(e);
            } finally {
                instances.remove(this.lockFile.getParent());
            }
        }
        
        super.finalize();
    }

}
