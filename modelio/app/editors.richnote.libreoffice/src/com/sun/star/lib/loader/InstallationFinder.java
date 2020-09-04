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

package com.sun.star.lib.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This class finds a UNO installation on the system.
 * 
 * <p>A UNO installation can be specified by the user by either setting the
 * com.sun.star.lib.loader.unopath system property or by setting the
 * UNO_PATH environment variable to the program directory of a UNO
 * installation.
 * Note, that Java 1.3.1 and Java 1.4 don't support environment variables
 * (System.getenv() throws java.lang.Error) and therefore setting the UNO_PATH
 * enviroment variable won't work with those Java versions.
 * If no UNO installation is specified by the user, the default installation
 * on the system will be returned.</p>
 * 
 * <p>On the Windows platform the default installation is read from the Windows
 * Registry.</p>
 * 
 * <p>On the Unix/Linux platforms the default installation is found from the
 * PATH environment variable. Note, that for Java 1.3.1 and Java 1.4 the
 * default installation is found by using the 'which' command, because
 * environment variables are not supported with those Java versions.
 * Both methods require that the 'soffice' executable or a symbolic
 * link is in one of the directories listed in the PATH environment variable.
 * For older versions than OOo 2.0 the above described methods may fail.
 * In this case the default installation is taken from the .sversionrc file in
 * the user's home directory. Note, that the .sversionrc file will be omitted
 * for OOo 2.0</p>
 */
@objid ("2a1674a0-2c52-49f6-bcfd-5fa262129582")
public final class InstallationFinder {
    /**
     * Java system property where a UNO installation can be specified.
     */
    @objid ("75d4a987-8e29-4cee-ad1e-8c388f134522")
    public static final String SYSPROP_NAME = "com.sun.star.lib.loader.unopath";

    @objid ("4190b94b-8e5a-49c1-bafc-fa3869c12c70")
    private static final String ENVVAR_NAME = "UNO_PATH";

    @objid ("e658e8c6-3b2a-4896-98f5-976ae8f9b3e7")
    private static final String SOFFICE = "soffice"; // Unix/Linux only

    @objid ("1e1e28ed-db83-417c-bb9e-816cd1b201a0")
    private InstallationFinder() {
    }

    /**
     * do not instantiate
     * Gets the path of a UNO installation.
     * @return the installation path or <code>null</code>, if no installation
     * was specified or found, or if an error occurred
     */
    @objid ("111118c5-c3b9-4129-aa8b-5ae25430f910")
    public static String getPath() {
        String path = null;
        
        // get the installation path from the Java system property
        // com.sun.star.lib.loader.unopath
        // (all platforms)
        path = getPathFromProperty( SYSPROP_NAME );
        if ( path == null ) {
            // get the installation path from the UNO_PATH environment variable
            // (all platforms, not working for Java 1.3.1 and Java 1.4)
            path = getPathFromEnvVar( ENVVAR_NAME );
            if ( path == null ) {
                if ( isWindows() ) {
                    // get the installation path from the Windows Registry
                    // (Windows platform only)
                    path = getPathFromWindowsRegistry();
                } else {
                    // get the installation path from the PATH environment
                    // variable (Unix/Linux platforms only, not working for
                    // Java 1.3.1 and Java 1.4)
                    path = getPathFromPathEnvVar();
                    if ( path == null ) {
                        // get the installation path from the 'which'
                        // command (Unix/Linux platforms only)
                        path = getPathFromWhich();
                        if ( path == null ) {                   
                            // get the installation path from the
                            // .sversionrc file (Unix/Linux platforms only,
                            // for older versions than OOo 2.0)
                            path = getPathFromSVersionFile();
                        }
                    }
                }
            }
        
        }
        return path;
    }

    /**
     * Gets the installation path from a Java system property.
     * 
     * <p>This method is called on all platforms.
     * The Java system property can be passed into the application by using
     * the -D flag, e.g.
     * java -D<property name>=<installation path> -jar application.jar.</p>
     * @return the installation path or <code>null</code>, if no installation
     * was specified in the Java system property or if an error occurred
     */
    @objid ("dde08c47-29d9-4c39-b396-77d5ee528221")
    private static String getPathFromProperty(final String prop) {
        String path = null;
        
        try {
            path = System.getProperty( prop );
        } catch ( SecurityException e ) {
            // if a SecurityException was thrown, return <code>null</code>
        }
        return path;
    }

    /**
     * Gets the installation path from an environment variable.
     * 
     * <p>This method is called on all platforms.
     * Note, that in Java 1.3.1 and Java 1.4 System.getenv() throws
     * java.lang.Error and therefore this method returns null for those
     * Java versions.</p>
     * @return the installation path or <code>null</code>, if no installation
     * was specified in the environment variable or if an error occurred
     */
    @objid ("6f31427e-6465-4f65-bba8-c0004e8f6d55")
    private static String getPathFromEnvVar(final String var) {
        String path = null;
        
        try {
            path = System.getenv( var );            
        } catch ( SecurityException e ) {
            // if a SecurityException was thrown, return <code>null</code>
        } catch ( java.lang.Error err ) {
            // System.getenv() throws java.lang.Error in Java 1.3.1 and 
            // Java 1.4
        }
        return path;
    }

    /**
     * Gets the installation path from the Windows Registry.
     * 
     * <p>This method is called on the Windows platform only.</p>
     * @return the installation path or <code>null</code>, if no installation
     * was found or if an error occurred
     */
    @objid ("9ce91e6c-a811-4262-bb63-743f4900db08")
    private static String getPathFromWindowsRegistry() {
        final String LIBREOKEYNAME = "Software\\LibreOffice\\UNO\\InstallPath";
        final String OOOKEYNAME = "Software\\OpenOffice.org\\UNO\\InstallPath";
        
        String ret = getPathFromWindowsRegistry(OOOKEYNAME);
        if (ret != null && !ret.isEmpty())
            return ret;
        else
            return getPathFromWindowsRegistry(LIBREOKEYNAME);
    }

    /**
     * Gets the installation path from the PATH environment variable.
     * 
     * <p>This method is called on Unix/Linux platforms only.
     * An installation is found, if the executable 'soffice' or a symbolic link
     * is in one of the directories listed in the PATH environment variable.
     * Note, that in Java 1.3.1 and Java 1.4 System.getenv() throws
     * java.lang.Error and therefore this method returns null for those
     * Java versions.</p>
     * @return the installation path or <code>null</code>, if no installation
     * was found or if an error occurred
     */
    @objid ("1b83f188-d562-437e-82b4-41a63908b1a8")
    private static String getPathFromPathEnvVar() {
        final String PATH_ENVVAR_NAME = "PATH";
        
        String path = null;
        String str = null;
        
        try {
            str = System.getenv( PATH_ENVVAR_NAME );
        } catch ( SecurityException e ) {
            // if a SecurityException was thrown, return <code>null</code>
            return null;
        } catch ( java.lang.Error err ) {
            // System.getenv() throws java.lang.Error in Java 1.3.1 and 
            // Java 1.4
            return null;
        }
        
        if ( str != null ) {
            StringTokenizer tokens = new StringTokenizer(
                str, File.pathSeparator );
            while ( tokens.hasMoreTokens() ) {
                File file = new File( tokens.nextToken(), SOFFICE );
                try {
                    if ( file.exists() ) {
                        try {                   
                            // resolve symlink
                            path = file.getCanonicalFile().getParent();
                            if ( path != null )
                                break;
                        } catch ( IOException e ) {
                            // if an I/O exception is thrown, ignore this
                            // path entry and try the next one
                            System.err.println( "com.sun.star.lib.loader." +
                                "InstallationFinder::getPathFromEnvVar: " +
                                "bad path: " + e );
                        }  
                    }
                } catch ( SecurityException e ) {
                    // if a SecurityException was thrown, ignore this path
                    // entry and try the next one
                }
            }
        }
        return path;
    }

    /**
     * Gets the installation path from the 'which' command on Unix/Linux
     * platforms.
     * 
     * <p>This method is called on Unix/Linux platforms only.
     * An installation is found, if the executable 'soffice' or a symbolic link
     * is in one of the directories listed in the PATH environment variable.</p>
     * @return the installation path or <code>null</code>, if no installation
     * was found or if an error occurred
     */
    @objid ("cb48db3b-a9bd-4ae3-9950-14ff9ac5b98b")
    private static String getPathFromWhich() {
        final String WHICH = "which";
        
        String path = null;
        
        // start the which process
        String[] cmdArray = new String[2];
        cmdArray[0] = WHICH;
        cmdArray[1] = SOFFICE;
        Process proc = null;
        Runtime rt = Runtime.getRuntime();
        try {
            proc = rt.exec( cmdArray );
        } catch ( SecurityException e ) {
            return null;
        } catch ( IOException e ) {
            // if an I/O exception is thrown, return <code>null</null>
            System.err.println( "com.sun.star.lib.loader." +
                "InstallationFinder::getPathFromWhich: " +
                "which command failed: " + e );
            return null;
        }
            
        // empty standard error stream in a seperate thread
        StreamGobbler gobbler = new StreamGobbler( proc.getErrorStream() );
        gobbler.start();
            
        // read the which output from standard input stream
        try (BufferedReader br = new BufferedReader(new InputStreamReader( proc.getInputStream()))) {
            String line = null;        
            while ( ( line = br.readLine() ) != null ) {
                if ( path == null ) {
                    // get the path from the which output
                    int index = line.lastIndexOf( SOFFICE );
                    if ( index != -1 ) {
                        int end = index + SOFFICE.length();
                        for ( int i = 0; i <= index; i++ ) {
                            File file = new File( line.substring( i, end ) );
                            try {
                                if ( file.exists() ) {
                                    // resolve symlink
                                    path = file.getCanonicalFile().getParent();
                                    if ( path != null )
                                        break;
                                }
                            } catch ( SecurityException e ) {
                                return null;
                            }
                        }
                    }
                }
            }
        } catch ( IOException e ) {
            // if an I/O exception is thrown, return <code>null</null>
            System.err.println( "com.sun.star.lib.loader." +
                                "InstallationFinder::getPathFromWhich: " +
                                "reading which command output failed: " + e );
            return null;
        }        
        
        try {
            // wait until the which process has terminated
            proc.waitFor();
        } catch ( InterruptedException e ) {
            // the current thread was interrupted by another thread,
            // kill the which process
            proc.destroy();            
            // set the interrupted status
            Thread.currentThread().interrupt();                
        }
        return path;
    }

    /**
     * Gets the installation path from the .sverionrc file in the user's home
     * directory.
     * 
     * <p>This method is called on Unix/Linux platforms only.
     * The .sversionrc file is written during setup and will be omitted for
     * OOo 2.0.</p>
     * @return the installation path or <code>null</code>, if no installation
     * was found or if an error occurred
     */
    @objid ("1e947757-21cb-4d8d-87f4-10e5262fc9e4")
    private static String getPathFromSVersionFile() {
        final String SVERSION = ".sversionrc"; // Unix/Linux only
        final String VERSIONS = "[Versions]";
        
        String path = null;
        
        try {        
            File fSVersion = new File(
                System.getProperty( "user.home" ) ,SVERSION );
            if ( fSVersion.exists() ) {
                Vector<String> lines = new Vector<>();
                try (BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream( fSVersion ), "UTF-8"))) {
                    String line = null;                
                    while ( ( line = br.readLine() ) != null &&
                            ( line.equals( VERSIONS ) ) != true ) {
                        // read lines until [Versions] is found
                    }
                    while ( ( line = br.readLine() ) != null &&
                            line.length() != 0 ) {
                        if ( !line.startsWith( ";" ) )
                            lines.add( line );
                    }
                } catch ( IOException e ) {
                    // if an I/O exception is thrown, try to analyze the lines
                    // read so far
                    System.err.println( "com.sun.star.lib.loader." +
                        "InstallationFinder::getPathFromSVersionFile: " +
                        "reading .sversionrc file failed: " + e );
                }            
                for ( int i = lines.size() - 1; i >= 0; i-- ) {
                    StringTokenizer tokens = new StringTokenizer(
                        lines.elementAt( i ), "=" );
                    if ( tokens.countTokens() != 2 )
                        continue;         
                    
                    @SuppressWarnings("unused")
                    String key = tokens.nextToken();
                    String url = tokens.nextToken();
                    path = getCanonicalPathFromFileURL( url );                
                    if ( path != null )
                        break;                
                }            
            }            
        } catch ( SecurityException e ) {
            return null;
        }
        return path;
    }

    /**
     * Translates an OOo-internal absolute file URL reference (encoded using
     * UTF-8) into a Java canonical pathname.
     * @param oooUrl any URL reference; any fragment part is ignored
     * @return if the given URL is a valid absolute, local (that is, the host
     * part is empty or equal to "localhost", ignoring case) file URL, it is
     * converted into an absolute canonical pathname; otherwise,
     * <code>null</code> is returned
     */
    @objid ("835981a1-d0d3-4b79-a985-0ffaa8899774")
    private static String getCanonicalPathFromFileURL(final String oooUrl) {
        String prefix = "file://";
        if (oooUrl.length() < prefix.length()
            || !oooUrl.substring(0, prefix.length()).toLowerCase().equals(
                prefix))
        {
            return null;
        }        
        StringBuffer buf = new StringBuffer(prefix);
        int n = oooUrl.indexOf('/', prefix.length());
        if (n < 0) {
            n = oooUrl.length();
        }
        String host = oooUrl.substring(prefix.length(), n);
        if (host.length() != 0 && !host.toLowerCase().equals("localhost")) {
            return null;
        }
        buf.append(host);
        if (n == oooUrl.length()) {
            buf.append('/');
        } else {
        loop:
            while (n < oooUrl.length()) {
                buf.append('/');
                ++n;
                int n2 = oooUrl.indexOf('/', n);
                if (n2 < 0) {
                    n2 = oooUrl.length();
                }
                while (n < n2) {
                    char c = oooUrl.charAt(n);
                    switch (c) {
                    case '%':
                        byte[] bytes = new byte[(n2 - n) / 3];
                        int len = 0;
                        while (oooUrl.length() - n > 2
                               && oooUrl.charAt(n) == '%')
                        {
                            int d1 = Character.digit(oooUrl.charAt(n + 1), 16);
                            int d2 = Character.digit(oooUrl.charAt(n + 2), 16);
                            if (d1 < 0 || d2 < 0) {
                                break;
                            }
                            int d = 16 * d1 + d2;
                            if (d == '/') {
                                return null;
                            }
                            bytes[len++] = (byte) d;
                            n += 3;
                        }
                        String s;
                        try {
                            s = new String(bytes, 0, len, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            return null;
                        }
                        buf.append(s);
                        break;
        
                    case '#':
                        break loop;
        
                    default:
                        buf.append(c);
                        ++n;
                        break;
                    }
                }
            }
        }
        URL url;
        try {
            url = new URL(buf.toString());
        } catch (MalformedURLException e) {
            return null;
        }
        String path = url.getFile();
        String fragment = url.getRef();
        if (fragment != null) {
            path += '#' + fragment;
        }
        String ret = null;
        File file = new File( path, SOFFICE );
        try {
            if ( file.isAbsolute() && file.exists() ) {
                try {
                    // resolve symlink
                    ret = file.getCanonicalFile().getParent();
                } catch ( IOException e ) {
                    return null;
                }     
            }
        } catch ( SecurityException e ) {
            return null;
        }
        return ret;
    }

    /**
     * Gets the installation path from a Windows Registry key path.
     * 
     * <p>This method is called on the Windows platform only.</p>
     * @param subKeyName The key path where the installation path is stored.
     * @return the installation path or <code>null</code>, if no installation
     * was found or if an error occurred
     */
    @objid ("965e7103-9304-4967-9ebd-d8840a5e1ee6")
    private static String getPathFromWindowsRegistry2(final String subKeyName) {
        String path = null;
        
        try {
            // read the key's default value from HKEY_CURRENT_USER
            WinRegKey key = new WinRegKey( "HKEY_CURRENT_USER", subKeyName );
            path = key.getStringValue( "" ); // default            
        } catch ( WinRegKeyException e ) {
            try {
                // read the key's default value from HKEY_LOCAL_MACHINE
                WinRegKey key = new WinRegKey( "HKEY_LOCAL_MACHINE",
                                               subKeyName );
                path = key.getStringValue( "" ); // default                
            } catch ( WinRegKeyException we ) {
                /*System.err.println( "com.sun.star.lib.loader." +
                    "InstallationFinder::getPathFromWindowsRegistry: " +
                    "reading key from Windows Registry failed: " + we );*/
            } 
        } catch (UnsatisfiedLinkError ex) {         
            // java.lang.UnsatisfiedLinkError: C:/temp/unowinreg*****.dll: Can't load IA 32-bit .dll on a AMD 64-bit platform
            // Occurs on windows 64bits because unowinreg.dll is a 32 bits dll.
            System.err.println( "com.sun.star.lib.loader." +
                    "InstallationFinder::getPathFromWindowsRegistry: " +
                    "reading key from Windows Registry failed: " + ex );
        } catch (NoClassDefFoundError ex){
            // java.lang.NoClassDefFoundError: Could not initialize class com.sun.star.lib.loader.WinRegKey
            // Occurs on windows 64bits because 'WinRegKey' loading failed, see upper.
            // Ignore error
        }
        return path;
    }

    /**
     * Tells whether the given directory is a valid OpenOffice
     * or LibreOffice installation directory.
     * @param installDir A directory.
     * @return true for a valid installation directory, false in all other cases.
     */
    @objid ("de84b62b-207c-4874-b0f4-a4dfb929c7ed")
    public static boolean isOooPathValid(final File installDir) {
        return isProgramPathValid(new File(installDir,"program"));
    }

    /**
     * Tells whether the given directory is a valid OpenOffice
     * or LibreOffice program directory.
     * <p>
     * The directory is valid if it contains the OpenOffice executable.
     * @param programDir A directory.
     * @return true for a valid program directory, false in all other cases.
     */
    @objid ("966c60dc-1b2f-44b9-8a0b-17f9d2cdc23a")
    public static boolean isProgramPathValid(final File programDir) {
        File f ;
        if (isWindows()) {
            f = new File(programDir, "soffice.exe");
        } else {
            f = new File(programDir, "soffice");
        }
        return (f.isFile());
    }

    /**
     * @return <code>true</code> if the OS is Windows, else <code>false</code>.
     */
    @objid ("e9b185bf-2c36-4e41-a562-9e8cab630a21")
    private static boolean isWindows() {
        return (System.getProperty("os.name").toLowerCase().startsWith("win")) ;
    }

    /**
     * Gets the installation path from a Windows Registry key path.
     * 
     * <p>This method is called on the Windows platform only.</p>
     * @param subKeyName The key path where the installation path is stored.
     * @return the installation path or <code>null</code>, if no installation
     * was found or if an error occurred
     */
    @objid ("07184be9-2b9d-48d2-a356-a27e34773195")
    private static String getPathFromWindowsRegistry(final String subKeyName) {
        String path;
        try {
            path = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, subKeyName, "");
        } catch (IllegalAccessException | InvocationTargetException e) {
            path = null;
        }
        
        if (path == null) {
            try {
                path = WinRegistry.readString(WinRegistry.HKEY_LOCAL_MACHINE, subKeyName, "");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return path;
    }

    /**
     * This class is used for emptying any stream which is passed into it in
     * a separate thread.
     */
    @objid ("54a39742-6b06-44d0-84ae-e09abb93a4d9")
    private static final class StreamGobbler extends Thread {
        @objid ("7f632318-71ed-496a-8bc3-bb579890583d")
         InputStream m_istream;

        @objid ("5843ba61-7ba5-4da7-bed2-88606b6521a6")
        StreamGobbler(final InputStream istream) {
            this.m_istream = istream;
        }

        @objid ("6b9008f2-a173-40b3-9d1c-51836bd04fc8")
        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader( this.m_istream ) )) {
                // read from input stream
                while ( br.readLine() != null ) {
                    // don't handle line content
                }
                br.close();
            } catch ( IOException e ) {
                // stop reading from input stream
            }
        }

    }

}
