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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.MessageFormat;
import java.util.Scanner;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.plugin.CoreUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;

/**
 * This class consists exclusively of static methods that operate on files,
 * directories, or other types of files.
 * <p>
 * This class uses {@link java.nio.file.Files} that give better diagnostics on
 * failure than {@link java.io.File}.
 * <p>
 * <b>Note:</b> Most of the methods may throw a {@link FileSystemException}.
 * Its {@linkplain FileSystemException#getMessage() getMessage()} method usually does not return a user friendly message.
 * Use {@linkplain #getLocalizedMessage(FileSystemException)} to get a user friendly error message.
 */
@objid ("e9ac6715-8541-11e1-afeb-001ec947ccaf")
public final class FileUtils {
    @objid ("b4918bb3-da09-47b4-aa6f-0375044d5797")
    private static int cpt = 1;

    @objid ("c5c93ec5-3884-488f-907e-168c251c54aa")
    private static int numberFile = 0;

    @objid ("ddb6427e-10ba-455d-97ef-3948df9c7b85")
    private static final MessageFormat progressFormat = new MessageFormat("{0, number} / {1, number}");

    @objid ("4b9e84f7-e253-4de9-bb59-0f69ab07ad28")
    private static String progressPrefix;

    /**
     * Compute the size of all files stored in a directory tree.
     * @throws IOException if an I/O error occurs. call {@linkplain IOException#getMessage()} to get the error cause.
     * @throws FileSystemException if a file system exception occurs. <b>Note:</b> {@linkplain FileSystemException#getMessage() getMessage()}
     * usually does not return a user friendly message. Call
     * {@linkplain FileUtils#getLocalizedMessage(FileSystemException)} to get a user friendly error message.
     * @param path a directory path.
     * @return the size of the directory.
     */
    @objid ("0028e5a6-b977-1ffa-8e11-001ec947cd2a")
    public static long computeSize(Path path) throws IOException, FileSystemException {
        DirectorySizeVisitor dsv = new DirectorySizeVisitor();
        dsv.compute(path);
        return dsv.totalSize;
    }

    /**
     * Copy recursively the given directory to (not into) the destination directory.
     * <p>
     * The destination directory does not have to exist.
     * @throws IOException in case of failure
     * @throws FileSystemException if a file system exception occurs.
     * <b>Note:</b> {@linkplain FileSystemException#getMessage() getMessage()} usually does not return a user friendly message.
     * Call {@linkplain FileUtils#getLocalizedMessage(FileSystemException)} to get a user friendly error message.
     * @param from the directory to copy
     * @param toDir the directory copy path
     */
    @objid ("fabda4a4-d023-11e1-bf59-001ec947ccaf")
    public static void copyDirectoryTo(Path from, Path toDir) throws IOException, FileSystemException {
        CopyDirVisitor copyVisitor = new CopyDirVisitor(from, toDir, StandardCopyOption.REPLACE_EXISTING);
        Files.walkFileTree(from, copyVisitor);
        
    }

    /**
     * @param from path to copy
     * @param toDir path destination
     * @param monitor the monitor
     */
    @objid ("b9aa5d1e-0843-4082-8eb9-1527403e5bb7")
    public static void copyDirectoryTo(Path from, Path toDir, IModelioProgress monitor) throws IOException, FileSystemException {
        int nbreFile = getNumberFile(from);
        CopyDirVisitorWithMonitor copyVisitorWithMonitor = new CopyDirVisitorWithMonitor(from, toDir, monitor,nbreFile, StandardCopyOption.REPLACE_EXISTING);
        Files.walkFileTree(from, copyVisitorWithMonitor);
        
    }

    @objid ("3c3c055a-8fd1-44f4-90ef-f63d3cec80eb")
    private static int getNumberFile(Path fromPath) throws IOException {
        Files.walkFileTree(fromPath, new SimpleFileVisitor<Path>() {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
          numberFile++;
          return FileVisitResult.CONTINUE;
        }});
        return numberFile;
    }

    /**
     * @param entriesNumber number of file to copy
     * @param currentCount current count of file to copy
     * @return progess bar monitoring label when doing the copy
     */
    @objid ("cd5c6e35-fff8-4740-8a0a-6a7d488bec1b")
    private static String getProgressMonitorLabel(int currentCount, int numberFile) {
        StringBuffer s = new StringBuffer();
        if (progressPrefix != null && !progressPrefix.isEmpty()) {
            if (progressPrefix.length() > 80) {
                // append first 80 chars to leave room
                s.append(progressPrefix.substring(0, 80));
                s.append("...");
            } else {
                s.append(progressPrefix);
            }
            s.append(" ");
        }
        progressFormat.format(new Object[]{currentCount, numberFile}, s, null);
        return s.toString();
    }

    /**
     * Creates a directory by creating all nonexistent parent directories first. Unlike the {@link Files#createDirectory
     * createDirectory} method, an exception is not thrown if the directory could not be created because it already exists.
     * 
     * <p>
     * If this method fails, then it may do so after creating some, but not all, of the parent directories.
     * @throws IOException if an I/O error occurs. call {@linkplain IOException#getMessage()} to get the error cause.
     * @throws FileSystemException if a file system exception occurs. <b>Note:</b> {@linkplain FileSystemException#getMessage()} usually does not
     * return a user friendly message. Call {@linkplain #getLocalizedMessage(FileSystemException)} to get a user
     * friendly error message.
     * @throws SecurityException in the case of the default provider, and a security manager is installed, the
     * {@link SecurityManager#checkWrite(String) checkWrite} method is invoked prior to attempting to create a directory
     * and its {@link SecurityManager#checkRead(String) checkRead} is invoked for each parent directory that is checked.
     * If {@code dir} is not an absolute path then its {@link Path#toAbsolutePath toAbsolutePath} may need to be invoked
     * to get its absolute path. This may invoke the security manager's
     * {@link SecurityManager#checkPropertyAccess(String) checkPropertyAccess} method to check access to the system
     * property {@code user.dir}
     * @throws FileAlreadyExistsException if {@code dir} exists but is not a directory <i>(optional specific exception)</i>
     * @throws InvalidPathException - if a Path object cannot be constructed from the abstract path (see FileSystem.getPath)
     * @param dir the directory to create
     */
    @objid ("e9ac671b-8541-11e1-afeb-001ec947ccaf")
    public static void createDir(final File dir) throws IOException, FileSystemException, SecurityException, FileAlreadyExistsException, InvalidPathException {
        Files.createDirectories(dir.toPath());
    }

    /**
     * Decode a string from a file name encoded with {@link #encodeFileName(String, StringBuilder)}.
     * <p>
     * FIXME Limitation : this method does not support non ASCII characters !!
     * @param fileName a file name with/without extension
     * @param sb the string builder to use to build the decoded string. The decoded file name will be appended to the buffer.
     * @return the string builder for convenience.
     */
    @objid ("36066993-f543-4873-9a07-9c8c382ed607")
    public static StringBuilder decodeFileName(String fileName, StringBuilder sb) {
        final char escape = '%';
        
        int len  = fileName.length();
        sb.ensureCapacity(len);
        
        for (int i=0; i<len; ++i) {
            char ch = fileName.charAt(i);
            if ( ch == escape) {
                ch = (char) Integer.parseInt(fileName.substring(i+1, i+3), 16);
                i += 2;
            }
            sb.append(ch);
        }
        return sb;
    }

    /**
     * Deletes the given file or directory recursively.
     * <p>
     * If the path denotes a directory, tries to delete it with all its content.
     * @throws IOException if an I/O error occurs. call {@linkplain IOException#getMessage()} to
     * get the error cause.
     * @throws FileSystemException if a file system exception occurs.
     * <b>Note:</b> {@linkplain FileSystemException#getMessage()} usually does not return a user friendly message.
     * Call {@linkplain #getLocalizedMessage(FileSystemException)} to get a user friendly error message.
     * @throws SecurityException In the case of the default provider, and a security manager
     * is installed, the {@link SecurityManager#checkDelete(String)}
     * method is invoked to check delete access to the file
     * @throws DirectoryNotEmptyException if the file is a directory and could not otherwise be deleted
     * because the directory is not empty <i>(optional specific
     * exception)</i>
     * @throws NoSuchFileException if the file does not exist <i>(optional specific
     * exception)</i>
     * @param path the path to the file or directory to delete
     */
    @objid ("00974b68-bdb5-1ffa-8e11-001ec947cd2a")
    public static void delete(final Path path) throws IOException, FileSystemException, SecurityException, DirectoryNotEmptyException, NoSuchFileException {
        if (Files.isDirectory(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        
                    Files.delete(file);
        
                    return FileVisitResult.CONTINUE;
                }
        
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (exc != null) {
                        throw exc;
                    }
        
                    Files.delete(dir);
        
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            Files.deleteIfExists(path);
        }
        
    }

    /**
     * Deletes the given file or directory recursively.
     * <p>
     * If the path denotes a directory, tries to delete it with all its content.
     * @throws IOException if an I/O error occurs. call {@linkplain IOException#getMessage()} to get the error cause.
     * @throws FileSystemException if a file system exception occurs. <b>Note:</b> {@linkplain FileSystemException#getMessage()} usually does not
     * return a user friendly message. Call {@linkplain #getLocalizedMessage(FileSystemException)} to get a user
     * friendly error message.
     * @throws SecurityException In the case of the default provider, and a security manager is installed, the
     * {@link SecurityManager#checkDelete(String)} method is invoked to check delete access to the file
     * @throws DirectoryNotEmptyException if the file is a directory and could not otherwise be deleted because the directory is not empty <i>(optional
     * specific exception)</i>
     * @throws NoSuchFileException if the file does not exist <i>(optional specific exception)</i>
     * @param path the path to the file or directory to delete
     */
    @objid ("e9ac6713-8541-11e1-afeb-001ec947ccaf")
    public static void delete(final String path) throws IOException, FileSystemException, SecurityException, DirectoryNotEmptyException, NoSuchFileException {
        delete(Paths.get(path));
    }

    /**
     * Deletes the given file or directory recursively.
     * <p>
     * If the path denotes a directory, tries to delete it with all its content.
     * @throws IOException if an I/O error occurs. call {@linkplain IOException#getMessage()} to
     * get the error cause.
     * @throws FileSystemException if a file system exception occurs.
     * <b>Note:</b> {@linkplain FileSystemException#getMessage()} usually does not return a user friendly message.
     * Call {@linkplain #getLocalizedMessage(FileSystemException)} to get a user friendly error message.
     * @param file the file or directory to delete.
     */
    @objid ("e9ac671a-8541-11e1-afeb-001ec947ccaf")
    public static void delete(final File file) throws IOException, FileSystemException {
        delete(file.toPath());
    }

    /**
     * Encode any string to a legal file name.
     * <p>
     * Illegal characters are encoded by '%' followed by the hex value of the character.
     * <p>
     * FIXME Limitation : this method does not support supplementary code points (UTF > 16).
     * @see #decodeFileName(String, StringBuilder) to decode the file name
     * @param s a string to encode
     * @param sb the string builder to use to build the encoded string. The encoded file name will be appended to the buffer.
     * @return the string builder for convenience.
     * @author http://stackoverflow.com/questions/1184176/how-can-i-safely-encode-a-string-in-java-to-use-as-a-filename
     */
    @objid ("f97fca82-9762-4d2c-a75c-f2bbd52b6764")
    public static StringBuilder encodeFileName(String s, StringBuilder sb) {
        final char escape = '%';
        
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch < ' ' || ch >= 0x7F
                || ch == '/' || ch=='\\' // add other illegal chars
                || (ch == '.' && i == 0) // we don't want to collide with "." or ".."!
                || ch == escape) {
                sb.append(escape);
                if (ch < 0x10) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(ch));
            } else {
                sb.append(ch);
            }
        }
        return sb;
    }

    /**
     * Make best effort to compute a user friendly message from the given {@link IOException}.
     * @param e the exception.
     * @return a message, won't be null.
     */
    @objid ("edc68ee7-9054-433f-9f60-04d3b68f6911")
    public static String getLocalizedMessage(IOException e) {
        try {
            if (e instanceof org.apache.http.client.ClientProtocolException) {
                return getApacheErrorMessage(e);
            }
        } catch (@SuppressWarnings ("unused") NoClassDefFoundError ignored) {
            // ignore "NoClassDefFoundError: org.apache.http.client.ClientProtocolException not found"
        }
        
        if (e instanceof javax.net.ssl.SSLProtocolException) {
            // better message for "javax.net.ssl.SSLProtocolException: handshake alert:  unrecognized_name"
            if (e.getMessage().equals("handshake alert:  unrecognized_name")) {
                return CoreUtils.I18N.getString("javax.net.ssl.SSLProtocolException_handshake_alert_unrecognized_name");
            }
        }
        
        if (e instanceof java.io.SyncFailedException && e.getMessage().equals("sync failed")) {
            String pattern = CoreUtils.I18N.getString("$"+e.getClass().getName());
            return MessageFormat.format(pattern, e.getMessage());
        }
        
        if (e instanceof FileSystemException) {
            return getFileSystemExceptionLocalizedMessage((FileSystemException) e) ;
        }
        
        // Handles AsynchronousCloseException, java.net.UnknownHostException ...
        String key = "$"+e.getClass().getName();
        if (CoreUtils.I18N.containsKey(key)) {
            String pattern = CoreUtils.I18N.getString(key);
            return MessageFormat.format(pattern, e.getMessage());
        }
        
        if (e.getLocalizedMessage() != null) {
            return e.getLocalizedMessage();
        } else {
            return e.toString();
        }
        
    }

    /**
     * Moves a path to another.
     * <p>
     * Avoids {@link DirectoryNotEmptyException} thrown by the standard {@link Files#move(Path, Path, java.nio.file.CopyOption...)} method
     * when moving a directory tree by copying each file and then deleting the directory tree itself.
     * </p>
     * @throws IOException in case of failure
     * @param source the path to the file to move
     * @param target the path to the target file (may be associated with a different provider to the source path)
     */
    @objid ("84716b03-7e4a-40a1-9c50-4c764587aad0")
    public static void move(Path source, Path target) throws IOException {
        copyDirectoryTo(source, target);
        delete(source);
        
    }

    /**
     * Read the whole content of an input stream and returns it as a string.
     * <p>
     * To be used for small files.
     * @throws IOException if an I/O error occurs.
     * <b>Note:</b> {@linkplain FileSystemException#getMessage()} usually does not return a user friendly message.
     * Call {@linkplain #getLocalizedMessage(IOException)} to get a user friendly error message.
     * @param is an input stream
     * @param charset The encoding type used to convert bytes from the stream into characters.
     * @return the read string
     */
    @objid ("40ad1e4c-87b4-4514-9d05-50754dc3eb15")
    public static String readWhole(InputStream is, String charset) throws IOException {
        // see http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
        try (Scanner s = new java.util.Scanner(is, charset)){
            s.useDelimiter("\\A");
            String ret =  s.hasNext() ? s.next() : "";
            if (s.ioException() != null) {
                throw s.ioException();
            }
            return ret;
        }
        
    }

    /**
     * Read the whole content of a file and returns it as a string.
     * <p>
     * To be used for small files.
     * @throws IOException if an I/O error occurs. call {@linkplain IOException#getMessage()} to
     * get the error cause.
     * @throws FileSystemException if a file system exception occurs.
     * <b>Note:</b> {@linkplain FileSystemException#getMessage()} usually does not return a user friendly message.
     * Call {@linkplain #getLocalizedMessage(FileSystemException)} to get a user friendly error message.
     * @param file a file path.
     * @param charset The encoding type used to convert bytes from the stream into characters.
     * @return the read string
     */
    @objid ("f6e65269-ea70-406d-aa88-687082d8f3fd")
    public static String readWhole(Path file, String charset) throws IOException, FileSystemException {
        try (InputStream is = new BufferedInputStream(Files.newInputStream(file))) {
            return readWhole(is, charset);
        }
        
    }

    /**
     * No instance.
     */
    @objid ("d4576141-cc12-11e1-a564-001ec947ccaf")
    private  FileUtils() {
        
    }

    /**
     * Handles Apache ClientProtocolException exceptions that have no message
     * and each cause having a part of the message:
     * <pre>
     * org.apache.http.client.ClientProtocolException: null
     * at org.apache.http.impl.client.InternalHttpClient.doExecute(InternalHttpClient.java:188)
     * at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:82)
     * 
     * Caused by: org.apache.http.HttpException: Cannot convert host to URI: http://www.m&lt;odeliosoft.com
     * at org.apache.http.impl.conn.SystemDefaultRoutePlanner.determineProxy(SystemDefaultRoutePlanner.java:77)
     * at org.apache.http.impl.conn.DefaultRoutePlanner.determineRoute(DefaultRoutePlanner.java:73)
     * 
     * Caused by: java.net.URISyntaxException: Illegal character in authority at index 7: http://www.m&lt;odeliosoft.com
     * at java.net.URI$Parser.fail(URI.java:2848)
     * at java.net.URI$Parser.parseAuthority(URI.java:3186)
     * </pre>
     * 
     * Returns in this case :
     * 
     * <pre>Cannot convert host to URI: http://www.m&lt;odeliosoft.com
     * Illegal character in authority at index 7: http://www.m&lt;odeliosoft.com</pre>
     * @param e the Apache exception
     */
    @objid ("9cd5ba5d-6fef-4fb3-ad00-fff9da570869")
    private static String getApacheErrorMessage(IOException e) {
        if (e.getMessage() != null) {
            return e.getMessage(); // message filled, return it.
        } else {
            /*
             * Handles Apache exceptions where ClientProtocolException has no message
             * and each cause has a part of the message like below:
             *
             * org.apache.http.client.ClientProtocolException: null
             *   at org.apache.http.impl.client.InternalHttpClient.doExecute(InternalHttpClient.java:188)
             *   at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:82)
             *
             * Caused by: org.apache.http.HttpException: Cannot convert host to URI: http://www.m<odeliosoft.com
             *   at org.apache.http.impl.conn.SystemDefaultRoutePlanner.determineProxy(SystemDefaultRoutePlanner.java:77)
             *   at org.apache.http.impl.conn.DefaultRoutePlanner.determineRoute(DefaultRoutePlanner.java:73)
             *
             * Caused by: java.net.URISyntaxException: Illegal character in authority at index 7: http://www.m<odeliosoft.com
             *   at java.net.URI$Parser.fail(URI.java:2848)
             *   at java.net.URI$Parser.parseAuthority(URI.java:3186)
             */
            StringBuilder sb = new StringBuilder();
            for (Throwable t = e.getCause(); t!=null; t = t.getCause()) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(t.getMessage());
            }
            return sb.toString();
        
        }
        
    }

    /**
     * Compute and return a user friendly message from the given FileSystemException.
     * @param e the exception.
     * @return a message, won't be null.
     */
    @objid ("d454fef0-cc12-11e1-a564-001ec947ccaf")
    private static String getFileSystemExceptionLocalizedMessage(FileSystemException e) {
        String pattern = CoreUtils.I18N.getString("$"+e.getClass().getName());
        return MessageFormat.format(pattern, e.getFile(), e.getOtherFile(), e.getReason());
    }

    @objid ("3da67873-3a39-4b90-a43a-dc5da227874d")
    public static String getProgressPrefix() {
        return progressPrefix;
    }

    @objid ("3623db28-db98-49c1-b96d-4a6d3dfd81de")
    public static void setProgressPrefix(String progressPrefix) {
        FileUtils.progressPrefix = progressPrefix;
    }

    /**
     * Visitor that copies a directory to another path.
     */
    @objid ("581a2c43-d059-491b-9c34-1237b7066616")
    public static class CopyDirVisitor extends SimpleFileVisitor<Path> {
        @objid ("e99aa572-4449-4fb5-b2b9-045676bd5e69")
        private StandardCopyOption copyOption = StandardCopyOption.REPLACE_EXISTING;

        @objid ("ed302f2f-6e5a-4319-8756-19969b6242af")
        private Path fromPath;

        @objid ("a6953e9e-9000-4c48-a69f-7b4a76c3882a")
        private Path toPath;

        @objid ("aba7abf6-8815-47a5-8da9-f2d8540e5c64")
        public  CopyDirVisitor(Path fromPath, Path toPath, StandardCopyOption copyOption) {
            this.fromPath = fromPath;
            this.toPath = toPath;
            this.copyOption = copyOption;
            
        }

        @objid ("b71dd485-fb86-40ae-bf42-d9af6b659f82")
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if (dir.equals(this.toPath)) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            Path targetPath = this.toPath.resolve(this.fromPath.relativize(dir));
            if(!Files.exists(targetPath)){
                Files.createDirectory(targetPath);
            }
            return FileVisitResult.CONTINUE;
        }

        @objid ("10cbaed4-bc0f-46b3-a7fa-6c8d766db269")
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Files.copy(file, this.toPath.resolve(this.fromPath.relativize(file)), this.copyOption);
            return FileVisitResult.CONTINUE;
        }

    }

    /**
     * Directory walker visitor that computes the size of a directory.
     * <p>
     * Instantiate one, call {@link #compute(Path)} and then get the value of the
     * counter attributes.
     */
    @objid ("0029072a-b977-1ffa-8e11-001ec947cd2a")
    private static class DirectorySizeVisitor extends SimpleFileVisitor<Path> {
        /**
         * Total size of the directory.
         */
        @objid ("002920b6-b977-1ffa-8e11-001ec947cd2a")
        long totalSize = 0;

        /**
         * Total number of files.
         */
        @objid ("00293ca4-b977-1ffa-8e11-001ec947cd2a")
        long nFiles = 0;

        /**
         * Total number of directories.
         */
        @objid ("00294514-b977-1ffa-8e11-001ec947cd2a")
        long nDirectory = 0;

        @objid ("00294bfe-b977-1ffa-8e11-001ec947cd2a")
        public void compute(Path path) throws IOException {
            this.totalSize = 0;
            this.nFiles = 0;
            this.nDirectory = 0;
            
            Files.walkFileTree(path, this);
            
        }

        @objid ("00295fae-b977-1ffa-8e11-001ec947cd2a")
        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
                this.nDirectory++;
            }
            if (Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)) {
                this.nFiles++;
                this.totalSize += attrs.size();
            }
            return FileVisitResult.CONTINUE;
        }

        @objid ("d37455dc-c9bf-11e1-8052-001ec947ccaf")
        public  DirectorySizeVisitor() {
            
        }

    }

    @objid ("833a4e34-b378-4d60-89d9-f5220f64cacf")
    public static class CopyDirVisitorWithMonitor extends SimpleFileVisitor<Path> {
        @objid ("966d8e3b-45ea-4b52-aba8-f8e24b3a56c6")
        private StandardCopyOption copyOption = StandardCopyOption.REPLACE_EXISTING;

        @objid ("4f3ce6f5-12f3-435f-a4f8-e726742bd6ec")
        private Path fromPath;

        @objid ("16ac12bd-990a-444c-96ab-33ebb1993895")
        private Path toPath;

        @objid ("b8d27459-19c1-4488-99cd-c282c6825a30")
        private int numberFile = 0;

        @objid ("d39b7ff3-0159-4d10-8673-99fb53827d49")
        private IModelioProgress monitor;

        @objid ("93a2af74-0f75-4b53-9c4a-5b5c11d8483a")
        public  CopyDirVisitorWithMonitor(Path fromPath, Path toPath, IModelioProgress monitor, int numberFile, StandardCopyOption copyOption) {
            this.fromPath = fromPath;
            this.toPath = toPath;
            this.copyOption = copyOption;
            this.monitor = monitor;
            this.numberFile = numberFile;
            
        }

        @objid ("205e8a3b-6a63-4283-94c0-c626d5ec2a23")
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if (dir.equals(this.toPath)) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            Path targetPath = this.toPath.resolve(this.fromPath.relativize(dir));
            if(!Files.exists(targetPath)){
                Files.createDirectory(targetPath);
            }
            return FileVisitResult.CONTINUE;
        }

        @objid ("ad9df1b8-d75c-46be-be62-c63063dd4a93")
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            SubProgress subMonitor = SubProgress.convert(this.monitor, this.numberFile);
            subMonitor.setWorkRemaining(this.numberFile);
            Files.copy(file, this.toPath.resolve(this.fromPath.relativize(file)), this.copyOption);
            subMonitor.subTask(getProgressMonitorLabel(cpt,this.numberFile));
            subMonitor.setWorkRemaining(this.numberFile);
            cpt++;
            subMonitor.worked(5);
            return FileVisitResult.CONTINUE;
        }

    }

}
