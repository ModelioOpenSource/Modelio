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

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.MessageFormat;
import java.text.Normalizer;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.plugin.CoreUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;

/**
 * Service class to compress one or more directories into one .zip file.
 * <p>
 * One instance is configured to produce one unique zip archive.
 * The zip archive is build by calling one
 * of the compress methods. Each time a compress method is called the
 * complete archive contents are replaced.
 */
@objid ("c98a8cd5-a5a3-11e1-aa98-001ec947ccaf")
public class Zipper {
    @objid ("c98a8cd7-a5a3-11e1-aa98-001ec947ccaf")
    private static final int BUFFER = 2048;

    @objid ("c98cef10-a5a3-11e1-aa98-001ec947ccaf")
    private boolean aborted = false;

    @objid ("13194160-7498-4003-bb34-7b444e29b17a")
    private int nTotal;

    @objid ("c98a8cd9-a5a3-11e1-aa98-001ec947ccaf")
    private Path archive;

    @objid ("c98cef11-a5a3-11e1-aa98-001ec947ccaf")
     ZipOutputStream out;

    /**
     * Matches non ASCII characters
     */
    @objid ("c0b4b550-ecf9-45fc-8ed4-7068f7b564f6")
    private static final Pattern nonAsciiChar = Pattern.compile("[^\\p{ASCII}]");

    @objid ("6cee9ba4-6881-4630-996b-32cd45118bc7")
    private final MessageFormat progressLabel;

    /**
     * Construct a Zipper instance.
     * <p>
     * Note that the instance is configured to
     * produce one unique zip archive. The zip archive is build by calling one
     * of the compress methods. Each time a compress method is called the
     * complete archive contents are replaced.
     * @param archive the full pathname of the archive to be produced by this
     * zipper.
     */
    @objid ("c98cef12-a5a3-11e1-aa98-001ec947ccaf")
    public Zipper(final Path archive) {
        this.archive = archive;
        this.progressLabel = new MessageFormat(CoreUtils.I18N.getString("Zipper.progressLabel"));
    }

    /**
     * Compress the directory or file given by 'path'.
     * @param path the pathname of the directory or file to compress
     * @param monitor an IModelioProgress object to report compression progression. Can be null.
     * @param title the title displayed for the compression task. Requires a 'monitor' instance. Can be null.
     * @throws java.io.IOException on I/O error
     */
    @objid ("c98cef24-a5a3-11e1-aa98-001ec947ccaf")
    public void compress(final Path path, final IModelioProgress monitor, final String title) throws IOException {
        compress(Collections.singletonList(path), null, null, monitor, title);
    }

    /**
     * Compress the directory or file given by 'path'.
     * @param path the pathname of the directory or file to compress
     * @param skipDirectoryMatchers matchers to skip directories and their content
     * @param skipFileMatchers matchers to skip files
     * @param monitor an IModelioProgress object to report compression progression. Can be null.
     * @param title the title displayed for the compression task. Requires a 'monitor' instance. Can be null.
     * @throws java.io.IOException on I/O error
     */
    @objid ("c98cef2a-a5a3-11e1-aa98-001ec947ccaf")
    public void compress(final Path path, final List<PathMatcher> skipDirectoryMatchers, final List<PathMatcher> skipFileMatchers, final IModelioProgress monitor, final String title) throws IOException {
        compress(Collections.singletonList(path), skipDirectoryMatchers, skipFileMatchers, monitor, title);
    }

    /**
     * Compress the contents of the directories list 'paths'.
     * @param pathes a list of directories or files (full paths)
     * @param skipDirectoryMatchers matchers to skip directories and their content
     * @param skipFileMatchers matchers to skip files
     * @param initialMonitor an IModelioProgress object to report compression progression. Can be null.
     * @param title the title displayed for the compression task. Requires a 'monitor' instance. Can be null.
     * @throws java.io.IOException on I/O error
     */
    @objid ("ce7a5c79-78ab-4448-88b4-c7f98eaae65d")
    public void compress(final Iterable<Path> pathes, final List<PathMatcher> skipDirectoryMatchers, final List<PathMatcher> skipFileMatchers, IModelioProgress initialMonitor, final String title) throws IOException {
        SubProgress monitor = SubProgress.convert(initialMonitor, getATaskName(title), 5);
        this.nTotal = countEntries(monitor.newChild(1), pathes);
        monitor.setWorkRemaining(this.nTotal);
        
        this.aborted = false;
        
        try (ZipOutputStream zipOutput = openZip(this.archive)) {
        
            for (Path path : pathes) {
                compressContent(path, path.getFileName().toString(), skipDirectoryMatchers, skipFileMatchers, monitor);
            }
        } finally {
            if (this.aborted) {
                Files.deleteIfExists(this.archive);
            }
        }
    }

    /**
     * Compress the directory content or file given by 'path'.
     * @param path the pathname of the directory or file to compress
     * @param monitor an IModelioProgress object to report compression progression. Can be null.
     * @param title the title displayed for the compression task. Requires a 'monitor' instance. Can be null.
     * @throws java.io.IOException on I/O error
     */
    @objid ("c98cef16-a5a3-11e1-aa98-001ec947ccaf")
    public void compressContent(final Path path, final IModelioProgress monitor, final String title) throws IOException {
        compressContent(Collections.singletonList(path), monitor, title);
    }

    /**
     * Compress the contents of the directories list 'pathes' in the same zip directory.
     * @param pathes a list of directories or files (full pathes)
     * @param progressMonitor an IModelioProgress object to report compression progression. Can be null.
     * @param title the title displayed for the compression task. Requires a 'monitor' instance. Can be null.
     * @throws java.io.IOException on I/O error
     */
    @objid ("c98cef1c-a5a3-11e1-aa98-001ec947ccaf")
    public void compressContent(final Iterable<Path> pathes, IModelioProgress progressMonitor, final String title) throws IOException {
        SubProgress monitor = SubProgress.convert(progressMonitor, getATaskName(title), 5);
        this.nTotal = countEntries(monitor.newChild(1), pathes);
        monitor.setWorkRemaining(this.nTotal);
        this.aborted = false;
        
        try (ZipOutputStream zipOutput = openZip(this.archive)) {
        
            for (Path path : pathes) {
                compressContent(path, null, null, null, monitor);
            }
        } finally {
            if (this.aborted) {
                Files.deleteIfExists(this.archive);
            }
        }
    }

    @objid ("c98cef4c-a5a3-11e1-aa98-001ec947ccaf")
    public Path getArchive() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.archive;
    }

    @objid ("c98cef50-a5a3-11e1-aa98-001ec947ccaf")
    public boolean isAborted() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.aborted;
    }

    @objid ("c98cef42-a5a3-11e1-aa98-001ec947ccaf")
    protected ZipOutputStream openZip(final Path archivePath) throws IOException {
        OutputStream dest = Files.newOutputStream(archivePath);
        CheckedOutputStream checksum = new CheckedOutputStream(dest, new Adler32());
        BufferedOutputStream buff = new BufferedOutputStream(checksum);
        this.out = new ZipOutputStream(buff);
        this.out.setMethod(ZipOutputStream.DEFLATED);
        this.out.setLevel(Deflater.BEST_COMPRESSION);
        return this.out;
    }

    /**
     * {@linkplain Normalizer Normalize} to {@link Normalizer.Form#NFC} and remove all non ASCII characters from the given string.
     * @param s the string to clean
     * @return a cleaned string
     */
    @objid ("c98cef47-a5a3-11e1-aa98-001ec947ccaf")
    protected static String unAccent(final String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFC);
        return nonAsciiChar.matcher(temp).replaceAll("");
    }

    /**
     * Compress the contents of the directory or file given by 'path'.
     * 
     * The path directory itself will not be part of the compressed file.
     * Only the files and directories that it contains will be compressed.
     * @param title the title displayed for the compression task. Requires a 'monitor' instance. Can be null.
     * @param srcPath the pathname of the directory or file to compress
     * @param intoDir the destination directory inside the zip file.
     * @param skipDirectoryMatchers the list of PathMatchers to use to skip some directories to compress.
     * @param skipFileMatchers the list of PathMatchers to use to skip some files to compress.
     * @param monitor an initialized IProgressMonitor object to report compression progression. .
     */
    @objid ("c98cef39-a5a3-11e1-aa98-001ec947ccaf")
    private void compressContent(final Path srcPath, final String intoDir, final List<PathMatcher> skipDirectoryMatchers, final List<PathMatcher> skipFileMatchers, final IModelioProgress monitor) throws IOException {
        FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
            int count = 0;
            @Override
            public FileVisitResult visitFile(Path file,
                    BasicFileAttributes attrs) throws IOException {
                if (monitor.isCanceled()) {
                    return FileVisitResult.TERMINATE;
                }
                if (skipFileMatchers != null && !skipFileMatchers.isEmpty()) {
                    for (PathMatcher skipFileMatcher : skipFileMatchers) {
                        if (skipFileMatcher.matches(file.toAbsolutePath())) {
                            return FileVisitResult.CONTINUE;
                        }
                    }
                }
                compressFile(Zipper.this.out, file, monitor);
                monitor.worked(1);
                
                if (++this.count % 17 == 0) {
                    monitor.subTask( computeProgressLabel(this.count, Zipper.this.nTotal));
                }
        
                return FileVisitResult.CONTINUE;
            }
        
            @Override
            public FileVisitResult preVisitDirectory(Path dir,
                    BasicFileAttributes attrs) throws IOException {
                if (monitor.isCanceled()) {
                    return FileVisitResult.TERMINATE;
                }
        
                if (skipDirectoryMatchers != null && !skipDirectoryMatchers.isEmpty()) {
                    for (PathMatcher skipDirectoryMatcher : skipDirectoryMatchers) {
                        if (skipDirectoryMatcher.matches(dir.toAbsolutePath())) {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                    }
                }
        
                // Avoid adding entry for the initial directory:
                // it causes infinite recursion in Unzipper.
                if (! dir.equals(srcPath)) {
                    addDirectoryEntry( dir);
                }
        
                return FileVisitResult.CONTINUE;
            }
        
            private void addDirectoryEntry( Path dir) throws IOException {
                // Compute directory path in the zip archive
                String zipPath = computeZipPath(dir);
        
                // set as directory
                zipPath = zipPath + "/";
        
                // Create zip entry
                ZipEntry entry = new ZipEntry(unAccent(zipPath));
                entry.setTime(Files.getLastModifiedTime(dir).toMillis());
                //java8:entry.setLastModifiedTime(Files.getLastModifiedTime(dir));
        
                Zipper.this.out.putNextEntry(entry);
            }
        
            /**
             * Compute directory path in the zip archive
             * @param dir the directory path
             * @return the final path in the zip file
             */
            private String computeZipPath(Path dir) {
                Path relativePath = srcPath.relativize(dir);
                if (relativePath == null) {
                    relativePath = srcPath;
                }
        
                String zipPath = relativePath.toString().replace("\\", "/");
                if (intoDir != null) {
                    zipPath = intoDir + "/" + zipPath ;
                }
        
                return zipPath;
            }
        
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                    throws IOException {
                monitor.worked(1);
        
                if (exc==null && monitor.isCanceled()) {
                    return FileVisitResult.TERMINATE;
                }
        
                return super.postVisitDirectory(dir, exc);
            }
        
            private void compressFile(ZipOutputStream zipOutput, Path f, IModelioProgress mon) throws IOException {
                //mon.subTask(f.toString());
        
                // Compute directory path in the zip archive
                String zipPath = computeZipPath(f);
        
                // Create zip entry
                ZipEntry entry = new ZipEntry(unAccent(zipPath));
                entry.setTime(Files.getLastModifiedTime(f).toMillis());
                //java 8: entry.setLastModifiedTime(Files.getLastModifiedTime(f));
                zipOutput.putNextEntry(entry);
        
                // write file data in the zip stream
                Files.copy(f, zipOutput);
        
                // Close the current entry
                zipOutput.closeEntry();
            }
        };
        
        Files.walkFileTree(srcPath, visitor );
        
        if (monitor.isCanceled()) {
            this.aborted = true;
        }
    }

    @objid ("c98cef32-a5a3-11e1-aa98-001ec947ccaf")
    private int countEntries(IModelioProgress monitor, final Iterable<Path> pathes) throws IOException {
        final int[] ret = new int[] {0};
        
        for (Path path : pathes) {
            FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file,
                        BasicFileAttributes attrs) throws IOException {
                    ret[0]++;
                    
                    if (ret[0] % 17 == 0) {
                        monitor.subTask(computeProgressLabel(0, ret[0]));
                    }
        
                    return super.visitFile(file, attrs);
                }
        
        
                @Override
                public FileVisitResult preVisitDirectory(Path dir,
                        BasicFileAttributes attrs) throws IOException {
                    ret[0]++;
        
                    return super.preVisitDirectory(dir, attrs);
                }
        
            };
        
            Files.walkFileTree(path, visitor );
        }
        return ret [0];
    }

    @objid ("37f3eb22-3d56-4f2a-8fb3-595abe6b46a0")
    protected String getATaskName(final String title) {
        if (title == null) {
            return MessageFormat.format(CoreUtils.I18N.getString("Zipper.progressTitle"),  this.archive.getFileName());
        } else {
            return title;
        }
    }

    @objid ("0adde6a3-e1fe-4a48-8307-b7f68b67395b")
    private String computeProgressLabel(int count, int total) {
        return this.progressLabel.format(new Object[] {this.archive.getFileName(), count, total}, new StringBuffer(), null).toString();
    }

}
