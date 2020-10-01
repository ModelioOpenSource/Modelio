/* 
 * Copyright 2013-2020 Modeliosoft
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;

/**
 * Service class to extract archive files.
 */
@objid ("0006e5d2-b816-1ffa-8e11-001ec947cd2a")
public class Unzipper {
    @objid ("c58ffe22-9c1b-45b4-a71d-bf90cf31b7a3")
    private String progressPrefix;

    @objid ("d5bdff8d-da45-4dee-9955-15c377f80094")
    private final String archiverName;

    @objid ("eedd82e3-a615-4a2b-be79-4862121c034c")
    private final String compressorName;

    @objid ("c1275c1a-f023-4d91-a0b1-b63675d69dea")
    private static final MessageFormat progressFormat = new MessageFormat("{0, number} / {1, number}");

    /**
     * Default c'tor using a ZIP archiver and no compressor.
     */
    @objid ("b8d4c55e-caf4-4234-8cfb-b6d705cf474c")
    public Unzipper() {
        this(ArchiveStreamFactory.ZIP, null);
    }

    /**
     * C'tor using a specific archiver and compressor.
     * 
     * @param archiverName the archive name, i.e. "ar", "arj", "zip", "tar", "jar", "cpio", "dump" or "7z".
     * @param compressorName the compressor name, i.e. "gz", "bzip2", "xz", "lzma", "pack200", "snappy-raw", "snappy-framed", "z", "lz4-block", "lz4-framed", "zstd", "deflate64" or "deflate". Might also be <code>null</code>.
     */
    @objid ("850684f0-765e-425b-a3d0-7a21dc47d4a0")
    public Unzipper(final String archiverName, final String compressorName) {
        this.archiverName = archiverName;
        this.compressorName = compressorName;
    }

    /**
     * Unzip the archive in the given folder.
     * 
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
        
        int cpt = 1;
        try (ArchiveInputStream ais = createInputStream(archive)) {
            ArchiveEntry entry;
            while ((entry = ais.getNextEntry()) != null) {
                if (subMonitor.isCanceled()) {
                    throw new InterruptedIOException();
                }
        
                if (entry.getName().equals("/")) {
                    // useless entry that made us doing a "rm -rf / "...
                    cpt++;
                    continue;
                }
        
                subMonitor.subTask(getProgressMonitorLabel(cpt, entriesNumber));
                cpt++;
        
                final Path target = folder.resolve(asRelativePath(entry.getName()));
        
                // directory
                if (entry.isDirectory()) {
                    Files.createDirectories(target);
                } else {
                    Files.createDirectories(target.getParent());
        
                    // Extract
                    try (OutputStream out = new FileOutputStream(target.toFile())) {
                        IOUtils.copy(ais, out);
                        out.close();
                    }
        
                    // Preserve permissions with TAR archiver only
                    if (entry instanceof TarArchiveEntry) {
                        PosixFileAttributeView attributeView = Files.getFileAttributeView(target, PosixFileAttributeView.class);
                        if (attributeView != null) {
                            attributeView.setPermissions(getPosixFilePermissions(((TarArchiveEntry)entry).getMode()));
                        }
                    }
                }
        
                // Preserve modification date
                try {
                    Files.setLastModifiedTime(target, FileTime.fromMillis(entry.getLastModifiedDate().getTime()));
                } catch (IOException e) {
                    Log.warning(FileUtils.getLocalizedMessage(e));
                    Log.trace(e);
                }
                subMonitor.worked(1);
            }
            ais.close();
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
            } catch (@SuppressWarnings ("unused") NoSuchFileException e2) {
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
        try (ArchiveInputStream ais = createInputStream(archive)) {
            while (ais.getNextEntry() != null) {
                count++;
                monitor.worked(1);
            }
            ais.close();
        }
        return count;
    }

    /**
     * Find entries in an archive that matches the given regular expression.
     * 
     * @param archive an archive.
     * @param regexp a regular expression.
     * @return entries matching the given pattern. Might be empty but never {@link NullPointerException}.
     * @throws java.io.IOException on I/O failure
     */
    @objid ("0059a268-b821-1ffa-8e11-001ec947cd2a")
    public ArchiveEntry[] findEntry(File archive, String regexp) throws IOException {
        List<ArchiveEntry> entries = new ArrayList<>();
        
        try (ArchiveInputStream ais = createInputStream(archive.toPath())){
            ArchiveEntry ze = null;
        
            Pattern p = Pattern.compile((regexp != null) ? regexp : ".*", Pattern.CASE_INSENSITIVE);
            while ((ze = ais.getNextEntry()) != null) {
                if (p.matcher(ze.getName()).matches()) {
                    entries.add(ze);
                }
            }
        }
        return entries.toArray(new ArchiveEntry[entries.size()]);
    }

    @objid ("3dc7c4bb-69b7-4f4a-9a8a-c72c2e40cfdc")
    private ArchiveInputStream createInputStream(final Path archive) throws IOException {
        try {
            final InputStream is = Files.newInputStream(archive);
            boolean ok = false;
            try {
                ArchiveInputStream archiveStream;
        
                // Note : "ok=true;return ..." are not factorized so that compiler sees 'is' resource
                // is correctly managed.
                if (this.archiverName == null) {
                    // Auto detect archive type
                    archiveStream = new ArchiveStreamFactory().createArchiveInputStream(is);
                    ok = true;
                    return archiveStream;
                } else if (this.compressorName != null) {
                    // archive and decoder specified
                    archiveStream = new ArchiveStreamFactory().createArchiveInputStream(this.archiverName,
                            new CompressorStreamFactory().createCompressorInputStream(this.compressorName, is));
                    ok = true;
                    return archiveStream;
                } else {
                    // Only archive type specified
                    archiveStream = new ArchiveStreamFactory().createArchiveInputStream(this.archiverName, is);
                    ok = true;
                    return archiveStream;
                }
            } finally {
                if (!ok) {
                    IOUtils.closeQuietly(is);
                }
            }
        } catch (ArchiveException | CompressorException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Computes the progress monitor label.
     * 
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
     * 
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
     * 
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

    /**
     * Convert integer mode to {@link PosixFilePermission} object.
     */
    @objid ("4b38d121-1faa-4de6-a095-245046443338")
    private static Set<PosixFilePermission> getPosixFilePermissions(int mode) {
        Set<PosixFilePermission> result = EnumSet.noneOf(PosixFilePermission.class);
        
        if ((mode & 0400) != 0) {
            result.add(PosixFilePermission.OWNER_READ);
        }
        if ((mode & 0200) != 0) {
            result.add(PosixFilePermission.OWNER_WRITE);
        }
        if ((mode & 0100) != 0) {
            result.add(PosixFilePermission.OWNER_EXECUTE);
        }
        if ((mode & 040) != 0) {
            result.add(PosixFilePermission.GROUP_READ);
        }
        if ((mode & 020) != 0) {
            result.add(PosixFilePermission.GROUP_WRITE);
        }
        if ((mode & 010) != 0) {
            result.add(PosixFilePermission.GROUP_EXECUTE);
        }
        if ((mode & 04) != 0) {
            result.add(PosixFilePermission.OTHERS_READ);
        }
        if ((mode & 02) != 0) {
            result.add(PosixFilePermission.OTHERS_WRITE);
        }
        if ((mode & 01) != 0) {
            result.add(PosixFilePermission.OTHERS_EXECUTE);
        }
        return result;
    }

}
