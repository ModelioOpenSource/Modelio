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

/**
 *
 */
package org.modelio.editors.richnote.libreoffice.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.lib.loader.InstallationFinder;
import com.sun.star.lib.loader.Loader;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.modelio.editors.richnote.libreoffice.editor.IEditedDocumentViewer;
import org.modelio.editors.richnote.libreoffice.preferences.PreferenceConstants;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * Loader that will find LibreOffice installation and create a class loader that can load OOoBean.
 * @author cmarin
 */
@objid ("f28f20b3-95ef-496d-b780-659455efce1c")
public class LibreOfficeLoader {
    @objid ("6b8ee323-76d2-48ea-af14-2500daab569b")
    private static final String LINUX_DOCUMENT_VIEWER_CLASS = "org.modelio.editors.richnote.libreoffice.runtime.editor.LibreOfficeSwtLinuxDocumentViewer";

    @objid ("4c96d34c-9ce0-4d81-891b-d71ba381cfa0")
    private static final String WIN_DOCUMENT_VIEWER_CLASS = "org.modelio.editors.richnote.libreoffice.runtime.editor.LibreOfficeSwtWinDocumentViewer";

    @objid ("15758c17-8179-477b-b098-d221f4401b6a")
    private static ClassLoader oooClassLoader = null;

    /**
     * Get the LibreOffice class loader.
     * <p>
     * This loader will find LibreOffice installation and can load OOoBean.
     * Returns <code>null</code> if LibreOffice or OpenOffice is not installed.
     * 
     * @return the LibreOffice class loader or <code>null</code>.
     * @throws java.io.IOException if the LibreOffice/OpenOffice installation is broken or incomplete.
     */
    @objid ("faeac460-cd9e-41af-a5bd-15982db897fa")
    public static ClassLoader getClassLoader() throws IOException {
        if (LibreOfficeLoader.oooClassLoader == null) {
            createClassLoader();
        }
        return LibreOfficeLoader.oooClassLoader;
    }

    @objid ("b241c7c8-ab81-4db6-a4a1-e5d6b1347f8b")
    private static void createClassLoader() throws IOException {
        findInstallPathFromPreferences();
        
        // Get LibreOffice class loader
        List<URL> ooClassPath = Loader.getUnoClassPath();
        
        if (ooClassPath != null) {
        
            // Look for:
            // - officebean.jar an put in in officebeanJarUrl
            // - soffice.exe and put its directory in UNO_PATH env variable
            URL programUrl = null;
            for (URL url : ooClassPath) {
                if (url.getProtocol().equals("file")) {
                    File dir;
                    try {
                        // dir = new File(new URI("file", url.getFile(),null )).getParentFile();
                        dir = new File(new URI(url.toString())).getParentFile();
                        // System.out.println("debug: *"+dir+" classpath.");
        
                        if (InstallationFinder.isOooPathValid(dir)) {
                            // System.out.println("debug: **"+f+" found.");
                            // Set UNO_PATH env path to soffice.exe executable path.
                            final File programDir = new File(dir, "program");
                            System.setProperty("UNO_PATH", programDir.getAbsolutePath());
                            programUrl = programDir.toURI().toURL();
                        }
        
                    } catch (URISyntaxException e) {
                        LibreOfficeEditors.LOG.warning(e);
                    } catch (MalformedURLException e) {
                        LibreOfficeEditors.LOG.warning(e);
                    }
        
                }
            }
        
            if (programUrl == null) {
                throw getFileNotFoundIn("OpenOffice installation directory not found in:\n", ooClassPath);
            } else {
                // Add the 'runtime/bin' plugin directory to class path
                Bundle bundle = FrameworkUtil.getBundle(LibreOfficeLoader.class);
                URL secondBinUrl = FileLocator.find(bundle, new Path("runtime/bin"), null);
        
                if (secondBinUrl == null) {
                    throw new UnsatisfiedLinkError("Cannot find runtime/bin directory in '" + bundle.getSymbolicName() + "' bundle.");
                }
        
                ArrayList<URL> urls = new ArrayList<>();
        
                // Add the 'runtime/bin' and program dir plugin directory to class path
                urls.add(secondBinUrl);
                urls.add(programUrl);
        
                // Add officebean.dll directory
                if (needOfficeBeanLib()) {
                    URL officeBeanLibUrl = lookForOfficeBeanLib(ooClassPath);
                    urls.add(officeBeanLibUrl);
                }
        
                // Add officebean.jar
                if (needOfficeBeanJar()) {
                    URL officebeanJarUrl = lookForOfficeBeanJar(ooClassPath);
                    if (officebeanJarUrl == null) {
                        throw getFileNotFoundIn("officebean.jar not found in class path:\n", ooClassPath);
                    } else {
                        urls.add(officebeanJarUrl);
                    }
                }
        
                // Add UNO jars
                urls.addAll(ooClassPath);
        
                // Build our composite class loader
                final ClassLoader pluginClassLoader = LibreOfficeLoader.class.getClassLoader();
        
                LibreOfficeLoader.oooClassLoader = new LibreOfficeInternalClassLoader(urls.toArray(new URL[urls.size()]), pluginClassLoader);
        
                installPreferenceListener();
            }
        }
    }

    @objid ("e372b30f-d6dc-40bd-b3cd-bc572254e0dc")
    private static boolean needOfficeBeanJar() {
        // return isWindows();
        return false;
    }

    @objid ("79cb7b80-22fd-4e19-9aab-b9d1d27a3a05")
    private static boolean needOfficeBeanLib() {
        // return isWindows();
        return false;
    }

    /**
     * @return <code>true</code> if the OS is Windows, else <code>false</code>.
     */
    @objid ("f26d4c07-4e48-4adb-9ad1-fdcd252fdc5e")
    public static boolean isWindows() {
        return (System.getProperty("os.name").toLowerCase().startsWith("win"));
    }

    /**
     * Look for 'officebean.so' or 'officebeanlo.so' native library.
     * <p>
     * On Ubuntu it is located in '/usr/lib/libreoffice/basis-link/program'.
     * <p>
     * The URLS for Ubuntu usually look like:
     * <ul>
     * <li>file:/home/cma/mtools/sirius/rcptarget/eclipse/plugins/org.eclipse.equinox.launcher_1.2.0.v20110502.jar,
     * <li>file:/usr/lib/libreoffice/program/../basis-link/ure-link/share/java/ridl.jar,
     * <li>file:/usr/lib/libreoffice/program/../basis-link/ure-link/share/java/jurt.jar,
     * <li>file:/usr/lib/libreoffice/program/../basis-link/ure-link/share/java/juh.jar,
     * <li>file:/usr/lib/libreoffice/program/../basis-link/program/classes/unoil.jar,
     * <li>file:/usr/lib/libreoffice/program/
     * </ul>
     * 
     * @param urls The directory URLS to search
     * @return the found library path.
     * @throws java.io.FileNotFoundException if the library was not found.
     */
    @objid ("5776e13d-2f57-4ff1-b9b0-783323aa5654")
    private static URL lookForOfficeBeanLib(final List<URL> urls) throws FileNotFoundException {
        URL ret = tryLookForLibFile("officebean", urls);
        if (ret == null) {
            ret = tryLookForLibFile("officebeanlo", urls);
        }
        
        if (ret != null) {
            return ret;
        } else {
            throw getFileNotFoundIn("officebean native library not found in the class path:\n", urls);
        }
    }

    /**
     * Look for 'officebean.jar' java library.
     * <p>
     * On Ubuntu it is located in '/usr/share/libreoffice/basis3.4/program/classes'.
     * <p>
     * The URLS for Ubuntu usually look like:
     * <ul>
     * <li>file:/home/cma/mtools/sirius/rcptarget/eclipse/plugins/org.eclipse.equinox.launcher_1.2.0.v20110502.jar,
     * <li>file:/usr/lib/libreoffice/program/../basis-link/ure-link/share/java/ridl.jar,
     * <li>file:/usr/lib/libreoffice/program/../basis-link/ure-link/share/java/jurt.jar,
     * <li>file:/usr/lib/libreoffice/program/../basis-link/ure-link/share/java/juh.jar,
     * <li>file:/usr/lib/libreoffice/program/../basis-link/program/classes/unoil.jar,
     * <li>file:/usr/lib/libreoffice/program/
     * </ul>
     * 
     * @param ooClassPath The directory URLS to search
     * @return the found library path or NULL.
     */
    @objid ("8d6cac31-93ee-453e-9cb1-64805b415b74")
    private static URL lookForOfficeBeanJar(final List<URL> ooClassPath) {
        final String libFileName = "officebean.jar";
        
        for (URL url : ooClassPath) {
            if (url.getProtocol().equals("file")) {
                try {
                    // File dir = new File(new URI("file", url.getFile(),null )).getParentFile();
                    File dir = new File(new URI(url.toString()));
                    if (!dir.isDirectory()) {
                        dir = dir.getParentFile();
                    }
        
                    while (dir != null && dir.isDirectory()) {
                        File f = new File(dir, libFileName);
                        if (f.isFile()) {
                            return f.toURI().toURL();
                        }
        
                        dir = dir.getParentFile();
                    }
                } catch (URISyntaxException e) {
                    LibreOfficeEditors.LOG.warning(e);
                } catch (MalformedURLException e) {
                    LibreOfficeEditors.LOG.warning(e);
                }
            }
        }
        return null;
    }

    /**
     * Install a preference listener that resets the class loader when the OOO
     * program path is modified.
     */
    @objid ("57bf3617-4fc7-42b4-a108-fc3c497c6911")
    private static void installPreferenceListener() {
        LibreOfficeEditors.PREFERENCES.addPropertyChangeListener(new IPropertyChangeListener() {
        
            @SuppressWarnings ("synthetic-access")
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                if (event.getProperty().equals(PreferenceConstants.P_OOOPATH)) {
                    final String newProgramPath = (String) event.getNewValue();
                    if (InstallationFinder.isProgramPathValid(new File(newProgramPath))) {
                        LibreOfficeLoader.oooClassLoader = null;
                        LibreOfficeEditors.PREFERENCES.removePropertyChangeListener(this);
                    } else {
                        LibreOfficeEditors.LOG.warning("Program path changed to invalid path: " + newProgramPath);
                    }
                }
            }
        });
    }

    /**
     * Look for a specified installation directory in the preferences.
     * <p>
     * If a valid installation directory, set the {@link InstallationFinder#SYSPROP_NAME}
     * system property.
     */
    @objid ("75a17129-84c7-4ed6-aa3c-94c11049c8fe")
    private static void findInstallPathFromPreferences() {
        String installPath = LibreOfficeEditors.PREFERENCES.getString(PreferenceConstants.P_OOOPATH);
        if (installPath != null && InstallationFinder.isProgramPathValid(new File(installPath))) {
            System.setProperty(InstallationFinder.SYSPROP_NAME, installPath);
        }
    }

    @objid ("7042d344-614e-4624-9553-f5e45c23cbc7")
    private static FileNotFoundException getFileNotFoundIn(final String msg, final List<URL> ooClassPath) {
        StringBuilder s = new StringBuilder();
        s.append(msg);
        for (URL url : ooClassPath) {
            s.append(" - ");
            s.append(url.toString());
            s.append("\n");
        }
        return new FileNotFoundException(s.toString());
    }

    /**
     * Look for the given native library in the given directories.
     * <p>
     * The library will be searched in the directories given by the URLs, and the parent
     * directories recursively.
     * <p>
     * On Ubuntu libraries are located in '/usr/lib/libreoffice/basis-link/program'.<br>
     * The URLS for Ubuntu usually look like:
     * <ul>
     * <li>file:/home/cma/mtools/sirius/rcptarget/eclipse/plugins/org.eclipse.equinox.launcher_1.2.0.v20110502.jar,
     * <li>file:/usr/lib/libreoffice/program/../basis-link/ure-link/share/java/ridl.jar,
     * <li>file:/usr/lib/libreoffice/program/../basis-link/ure-link/share/java/jurt.jar,
     * <li>file:/usr/lib/libreoffice/program/../basis-link/ure-link/share/java/juh.jar,
     * <li>file:/usr/lib/libreoffice/program/../basis-link/program/classes/unoil.jar,
     * <li>file:/usr/lib/libreoffice/program/
     * </ul>
     * 
     * @param urls The directory URLS to search
     * @return the found library path or <code>null</code>.
     */
    @objid ("20ab7c34-2e8c-4c7a-b7de-52b1307d6c22")
    private static URL tryLookForLibFile(final String libName, final List<URL> urls) {
        final String libFileName = System.mapLibraryName(libName);
        
        for (URL url : urls) {
            if (url.getProtocol().equals("file")) {
                try {
                    // File dir = new File(new URI("file", url.getFile(),null )).getParentFile();
                    File dir = new File(new URI(url.toString()));
                    if (!dir.isDirectory()) {
                        dir = dir.getParentFile();
                    }
        
                    while (dir != null && dir.isDirectory()) {
                        File f = new File(dir, libFileName);
                        if (f.isFile()) {
                            return dir.toURI().toURL();
                        }
        
                        dir = dir.getParentFile();
                    }
                } catch (URISyntaxException e) {
                    LibreOfficeEditors.LOG.warning(e);
                } catch (MalformedURLException e) {
                    LibreOfficeEditors.LOG.warning(e);
                }
            }
        }
        return null;
    }

    /**
     * Get the {@link IEditedDocumentViewer} implementation class in the
     * OpenOffice class loader space.
     * 
     * @return the {@link IEditedDocumentViewer} implementation class.
     * @throws java.io.IOException if the LibreOffice/OpenOffice installation is broken or incomplete.
     */
    @objid ("dbb71ee1-8846-43f8-ac00-5cc80294677a")
    @SuppressWarnings ("unchecked")
    public static Class<? extends IEditedDocumentViewer> getDocumentViewerClass() throws IOException {
        ClassLoader officeClassLoader = LibreOfficeLoader.getClassLoader();
        
        String className;
        if (LibreOfficeLoader.isWindows()) {
            className = LibreOfficeLoader.WIN_DOCUMENT_VIEWER_CLASS;
        } else {
            className = LibreOfficeLoader.LINUX_DOCUMENT_VIEWER_CLASS;
        }
        
        Class<?> cl;
        try {
            cl = officeClassLoader.loadClass(className);
        
        } catch (ClassNotFoundException e) {
            throw new IOException("'" + className + "' not found.", e);
        }
        
        if (!IEditedDocumentViewer.class.isAssignableFrom(cl)) {
            throw new IOException("'" + className + "' class does not implement IEditedDocumentViewer.");
        }
        return (Class<? extends IEditedDocumentViewer>) cl;
    }

    /**
     * Class loader that looks for a class :
     * <ol>
     * <li>in the system class loader
     * <li>in the URL class path
     * <li>in the fallback class loader
     * </ol>
     * <p>
     * Works around Eclipse building bug that includes wrongly the 'runtime/bin' directory
     * to the plugin class path.
     * @author cma
     * @since 3.6
     */
    @objid ("be3ce948-e4a6-4acd-9edb-32054d6aa5ad")
    private static final class LibreOfficeInternalClassLoader extends URLClassLoader {
        @objid ("91a9ffd5-a2cb-43d6-bb79-d28edd5c4269")
        private final ClassLoader fallBackClassLoader;

        @objid ("01acd189-67c1-4a97-a8f7-9fd2242b2d94")
        private LibreOfficeInternalClassLoader(URL[] urls, ClassLoader fallbackClassLoader) {
            super(urls);
            this.fallBackClassLoader = fallbackClassLoader;
        }

        @objid ("ce7073b4-4d04-4c10-bc48-40b358fedb3e")
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                // Look in priority in the URL class path
                return super.findClass(name);
            } catch (ClassNotFoundException e) {
                try {
                    return this.fallBackClassLoader.loadClass(name);
                } catch (ClassNotFoundException e2) {
                    e.addSuppressed(e2);
                    throw e;
                }
            }
        }

        @objid ("25e74062-0f31-4a76-a74b-de6ffd76e7ed")
        @Override
        public URL findResource(String name) {
            // Look in priority in the URL class path
            URL ret = super.findResource(name);
            if (ret != null) {
                return ret;
            }
            return this.fallBackClassLoader.getResource(name);
        }

    }

}
