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
package com.sun.star.lib.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.editors.richnote.libreoffice.plugin.LibreOfficeEditors;

/**
 * This class can be used as a class path builder for application classes which use UNO.
 * 
 * <p>The Loader class detects a UNO installation on the system and returns the
 * UNO jar files to add to the search path of a customized class loader.</p>
 */
@objid ("80309af2-6066-4f50-8102-49fe3f3eeab0")
public final class Loader {
    @objid ("22190d39-5d66-4901-b8b9-ba629c3798d3")
    private static List<URL> m_urls = null;

    /**
     * do not instantiate
     */
    @objid ("e407d35d-7860-48e6-89df-11f6e4d12e28")
    private  Loader() {
        
    }

    /**
     * Gets the customized class path with the UNO jar files.
     * <p>
     * Returns <code>null</code> if LibreOffice or OpenOffice is not installed.
     * @return the customized class loader
     * @throws IOException if the LibreOffice/OpenOffice installation is broken or incomplete.
     */
    @objid ("c879fc5b-0d0b-42ee-9f5b-960932bcaa0b")
    public static synchronized List<URL> getUnoClassPath() throws IOException {
        final String CLASSESDIR = "classes";
        final String JUHJAR = "juh.jar";
        
        if ( m_urls == null ) { 
        
            // get the urls from which to load classes and resources
            // from the class path
            ArrayList<URL> urls = new ArrayList<>();
            String classpath = null;
            try {
                classpath = System.getProperty( "java.class.path" );
            } catch ( SecurityException e ) {
                // don't add the class path entries to the list of class
                // loader URLs
                LibreOfficeEditors.LOG.warning("com.sun.star.lib.loader.Loader::" +
                        "getUnoClassPath(): cannot get system property " +
                        "java.class.path: ");
                LibreOfficeEditors.LOG.warning(e);
            }
            
            if ( classpath != null ) {
                addUrls(urls, classpath, File.pathSeparator);
            }
        
            // get the urls from which to load classes and resources       
            // from the UNO installation
            String path = InstallationFinder.getPath();        
            if ( path != null ) {            
                boolean found = false;
                File fClassesDir = new File( path, CLASSESDIR );
                File fJuh = new File( fClassesDir, JUHJAR );
                if ( fJuh.exists() ) {
                    try {
                        URL[] clurls = new URL[]{fJuh.toURI().toURL()};
                        try (URLClassLoader cl = new URLClassLoader( clurls , ClassLoader.getSystemClassLoader())) {
                            Class<?> c = cl.loadClass("com.sun.star.comp.helper.UnoInfo" );
                            Method m = c.getMethod( "getJars", (Class[]) null );
                            URL[] jarurls = (URL[]) m.invoke(null, (Object[]) null );
                            for ( int i = 0; i < jarurls.length; i++ ) {
                                urls.add( jarurls[i] );
                                found = true;
                            }
                        }
                    } catch ( MalformedURLException e ) {
                        // don't add the UNO jar files to the list of class
                        // loader URLs
                        LibreOfficeEditors.LOG.warning(e);
                    } catch ( ClassNotFoundException e ) {
                        // don't add the UNO jar files to the list of class
                        // loader URLs
                        LibreOfficeEditors.LOG.warning(e);
                    } catch ( NoSuchMethodException e ) {
                        // don't add the UNO jar files to the list of class
                        // loader URLs
                        LibreOfficeEditors.LOG.warning(e);
                    } catch ( IllegalAccessException e ) {
                        // don't add the UNO jar files to the list of class
                        // loader URLs
                        LibreOfficeEditors.LOG.warning(e);
                    } catch ( InvocationTargetException e ) {
                        // don't add the UNO jar files to the list of class
                        // loader URLs
                        LibreOfficeEditors.LOG.warning(e);
                    }
                } 
                if (!found){
                    callUnoinfo(path, urls);
                }
            } else {
                LibreOfficeEditors.LOG.warning("No LibreOffice/OpenOffice installation found.");
                return null;
            }
            
            // Check URLs
            checkUrls(urls);
            m_urls = urls;
        
        }
        return m_urls;
    }

    @objid ("a723d21c-fef0-4f56-ae30-b0a786accaf9")
    private static void addUrls(final List<URL> urls, final String data, final String delimiter) {
        StringTokenizer tokens = new StringTokenizer( data, delimiter );
        while ( tokens.hasMoreTokens() ) {
            final String nextToken = tokens.nextToken();
            try {
                urls.add( new File( nextToken ).toURI().toURL() );
            } catch ( MalformedURLException e ) {
                // don't add this class path entry to the list of class loader
                // URLs
                LibreOfficeEditors.LOG.warning( "com.sun.star.lib.loader.Loader::" +
                        "addUrls(): bad '"+ nextToken+"' pathname: "+ e );
            }
        }
        
    }

    @objid ("6839a4df-5bac-4b72-921a-893e3dc2cb23")
    @SuppressWarnings("resource")
    private static void callUnoinfo(final String path, final List<URL> urls) throws IOException {
        Process p;
        try {
            p = Runtime.getRuntime().exec(new String[] { new File(path, "unoinfo").getPath(), "java" });
        } catch (IOException e) {
            //System.err.println("com.sun.star.lib.loader.Loader::callUnoinfo: exec unoinfo: " + e);
            throw e;
        }
        
        new Drain(p.getErrorStream()).start();
        int code;
        byte[] buf = new byte[1000];
        int n = 0;
        try {
            InputStream s = p.getInputStream();
            code = s.read();
            for (;;) {
                if (n == buf.length) {
                    if (n > Integer.MAX_VALUE / 2) {
                        throw new IOException("too much unoinfo output");
                    }
                    byte[] buf2 = new byte[2 * n];
                    for (int i = 0; i < n; ++i) {
                        buf2[i] = buf[i];
                    }
                    buf = buf2;
                }
                int k = s.read(buf, n, buf.length - n);
                if (k == -1) {
                    break;
                }
                n += k;
            }
        } catch (IOException e) {
            throw new IOException("com.sun.star.lib.loader.Loader::callUnoinfo: reading" +
                                          " unoinfo output: " + e, e);
        }
        
        int ev;
        try {
            ev = p.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LibreOfficeEditors.LOG.error("com.sun.star.lib.loader.Loader::callUnoinfo: waiting for" +
                                       " unoinfo: " + e);
            return;
        }
        if (ev != 0) {
            throw new IOException("com.sun.star.lib.loader.Loader::callUnoinfo: unoinfo"
                                          + " exit value " + n);
        
        }
        
        String s;
        if (code == '0') {
            s = new String(buf);
        } else if (code == '1') {
            try {
                s = new String(buf, "UTF-16LE");
            } catch (UnsupportedEncodingException e) {
                throw new IOException("com.sun.star.lib.loader.Loader::callUnoinfo():" +
                                              " transforming unoinfo output: " + e);
            }
        } else {
            throw new IOException("Bad unoinfo output code:"+code);
        }
        
        addUrls(urls, s, "\0");
        
    }

    /**
     * Check all URLS are valid and throws {@link IOException} if an invalid URL is found.
     * @param vec the URLS to validate
     * @throws IOException if an invalid URL is found.
     */
    @objid ("52a2d502-d4ed-4302-bd50-5084e2bdbd82")
    private static void checkUrls(final List<URL> vec) throws IOException {
        for (URL url : vec) {
            if (url.getProtocol().equals("file")) {
        
                try {
                    File f = new File(url.toURI());
                    if (! f.exists()) {
        
                        String msg;
                        if (f.getPath().contains("unoil.jar")) {
                            //msg = "The "+f.getCanonicalPath()+" file is missing.\nYou probably miss the 'libreoffice-java-common' debian package.";
                            msg = LibreOfficeEditors.I18N.getMessage("unoilJarMissing", f.getCanonicalPath());
                        } else {
                            //msg = "The "+f.getCanonicalPath()+" file is missing.";
                            msg = LibreOfficeEditors.I18N.getMessage("classpathFileMissing", f.getCanonicalPath());
                        }
                        throw new FileNotFoundException(msg);
                    }
                } catch (URISyntaxException e) {
                    throw new IOException(e);
                }
            }
        
        }
        
    }

    @objid ("8489c3c5-3b81-47e8-987d-029eca38e0fe")
    private static final class Drain extends Thread {
        @objid ("e7e1883f-3415-49ef-8586-57e99a491d2a")
        private final InputStream stream;

        @objid ("4a6b9513-ade1-4486-8cfd-903c091d2200")
        public  Drain(final InputStream stream) {
            super("unoinfo stderr drain");
            this.stream = stream;
            
        }

        @objid ("bed607d6-6f1c-4503-a5a1-1cccf48ce506")
        @Override
        public void run() {
            try {
                while (this.stream.read() != -1) {/* noop */}
            } catch (IOException e) { /* ignored */ }
            
        }

    }

}
