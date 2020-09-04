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

package org.modelio.vbasic.files;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;

/**
 * Service to extract an archive.
 */
@objid ("0006e5d2-b816-1ffa-8e11-001ec947cd2a")
public class Unzipper {
    @objid ("c58ffe22-9c1b-45b4-a71d-bf90cf31b7a3")
    private String progressPrefix;

    @objid ("c1275c1a-f023-4d91-a0b1-b63675d69dea")
    private static final MessageFormat progressFormat = new MessageFormat("{0, number} / {1, number}");

    /**
     * Unzip the archive in the given folder.
     * @param archive the archive to extract
     * @param folder the target folder
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility
     * to call done() on the given monitor. Accepts <i>null</i>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @throws java.io.IOException on I/O failure
     * @throws java.io.InterruptedIOException if the user cancelled the operation in the progress dialog.
     */
    @objid ("00590c36-b821-1ffa-8e11-001ec947cd2a")
    public void unzip(final Path archive, final Path folder, IModelioProgress monitor) throws IOException, InterruptedIOException {
        SubProgress subMonitor = SubProgress.convert(monitor, 100000);
        
        int entriesNumber = countEntries(archive, subMonitor.newChild(10000));
        
        // Recalibrate the monitor
        subMonitor.setWorkRemaining(entriesNumber);
        
        int cpt = 0;
        try (ZipInputStream zis = createZipInputStream(archive)) {
            ZipEntry ze = null;
            while ((ze = zis.getNextEntry()) != null) {
                
                if (subMonitor.isCanceled()) {
                    throw new InterruptedIOException();
                }
        
                if (ze.getName().equals("/")) {
                    // useless entry that made us doing a "rm -rf / " ...
                    continue;
                }
                
                final Path f = folder.resolve(asRelativePath(ze.getName()));
        
                if (Files.exists(f)) {
                    FileUtils.delete(f);
                }
        
                // directory
                if (ze.isDirectory()) {
                    Files.createDirectories(f);
                } else {
                    subMonitor.subTask(getProgressMonitorLabel(cpt, entriesNumber));
                    Files.createDirectories(f.getParent());
                    extractFile(zis, f);
                }
                
                //f.toFile().setLastModified(ze.getTime());
                try {
                    Files.setLastModifiedTime(f, FileTime.fromMillis(ze.getTime()));
                } catch (IOException e) {
                    Log.warning(FileUtils.getLocalizedMessage(e));
                    Log.trace(e);
                }
                subMonitor.worked(1);
                cpt++;
            }
        }
    }

    /**
     * Unzip one file of the archive in the given folder.
     * @param archive the archive to extract
     * @param entry the archive entry to extract
     * @param folder the target folder
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility
     * to call done() on the given monitor. Accepts <i>null</i>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @throws java.io.IOException on I/O failure
     */
    @objid ("0059db34-b821-1ffa-8e11-001ec947cd2a")
    public void unzip(final File archive, final String entry, final File folder, IModelioProgress monitor) throws IOException {
        SubProgress subMonitor = SubProgress.convert(monitor, 1);
        
        try (ZipFile zipFile = new ZipFile(archive.getCanonicalFile())) {
        
            ZipEntry ze = zipFile.getEntry(entry);
        
            if (ze != null) {
                final Path f = folder.toPath().resolve(ze.getName());
        
                
                if (Files.exists(f)) {
                    FileUtils.delete(f);
                }
        
                // directory
                if (ze.isDirectory()) {
                    Files.createDirectories(f);
                } else {
                    Files.createDirectories(f.getParent());
                    try (InputStream zis = zipFile.getInputStream(ze)) {
                        extractFile(zis, f);
                    }
                    subMonitor.worked(1);
                }
                
                //f.setLastModified(ze.getTime());
                try {
                    Files.setLastModifiedTime(f, FileTime.fromMillis(ze.getTime()));
                } catch (IOException e) {
                    Log.warning(FileUtils.getLocalizedMessage(e));
                    Log.trace(e);
                }
            }
        }
    }

    /**
     * Read file contents from the Zip and write the file
     */
    @objid ("00587988-b821-1ffa-8e11-001ec947cd2a")
    private void extractFile(InputStream zis, Path f) throws IOException {
        try {
            Files.copy(zis, f, StandardCopyOption.REPLACE_EXISTING);
        } catch (final IOException e) {
            try {
                Files.delete(f);
            } catch (NoSuchFileException e2) {
                // ignore
            } catch (IOException e2) {
                e.addSuppressed(e2);
            }
            throw e;
        }
    }

    @objid ("0058e62a-b821-1ffa-8e11-001ec947cd2a")
    private int countEntries(Path archive, IModelioProgress monitor) throws IOException {
        int count = 0;
        try (ZipInputStream zis = createZipInputStream(archive)) {
            while (zis.getNextEntry() != null) {
                count++;
                monitor.worked(1);
            }
            zis.close();
        }
        return count;
    }

    @objid ("0059a268-b821-1ffa-8e11-001ec947cd2a")
    public ZipEntry[] findEntry(File archive, String regexp) throws IOException {
        List<ZipEntry> entries = new ArrayList<>();
        
        try (ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(archive.getCanonicalFile())))) {
            ZipEntry ze = null;
        
            Pattern p = Pattern.compile((regexp != null) ? regexp : ".*", Pattern.CASE_INSENSITIVE);
            while ((ze = zis.getNextEntry()) != null) {
                if (p.matcher(ze.getName()).matches()) {
                    entries.add(ze);
                }
            }
        
        }
        return entries.toArray(new ZipEntry[entries.size()]);
    }

    @objid ("3dc7c4bb-69b7-4f4a-9a8a-c72c2e40cfdc")
    private ZipInputStream createZipInputStream(final Path archive) throws IOException {
        return new ZipInputStream(new BufferedInputStream(Files.newInputStream(archive)));
    }

    /**
     * Computes the progress monitor label.
     * @param currentCount the count of extracted files
     * @param entriesNumber the total number of entries in the archive.
     * @return the progress monitor label to display.
     */
    @objid ("69d77935-0723-4f8a-9091-6204de73246a")
    protected String getProgressMonitorLabel(int currentCount, int entriesNumber) {
        StringBuffer s = new StringBuffer();
        
        if (this.progressPrefix != null && !this.progressPrefix.isEmpty()) {
            if (this.progressPrefix.length() > 80) {
                // append first 80 chars to leave room
                s.append(this.progressPrefix.substring(0, 80));
                s.append("...");
            } else {
                s.append(this.progressPrefix);
            }
            s.append(" ");
        }
        
        progressFormat.format(new Object[]{currentCount, entriesNumber}, s, null);
        return s.toString();
    }

    /**
     * Set the progress monitor sub task label prefix that will be displayed just before the counters.
     * @param labelPrefix the monitor label prefix
     * @return this zipper to chain calls.
     */
    @objid ("d326e1ee-ac2a-465a-aafe-d8986fe157c8")
    public Unzipper setProgressLabelPrefix(String labelPrefix) {
        this.progressPrefix = labelPrefix;
        return this;
    }

    /**
     * Remove all '/' and '\' on the beginning to ensure the path is a relative one.
     * @param name a zip entry name
     * @return an normalized name.
     */
    @objid ("82496532-4e8e-4374-82a2-44b4c13b1951")
    private String asRelativePath(String name) {
        if (name.isEmpty()) {
            return name;
        }
        
        char c = name.charAt(0);
        if (c != '/' && c != '\\') {
            return name;
        }
        
        int i = 0;
        int l = name.length();
        while (c == '/' || c == '\\') {
            i++;
            if (i >= l) {
                return "";
            }
            
            c = name.charAt(i);
        }
        return name.substring(i);
    }

}
