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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This class provides functionality for reading string values from the
 * Windows Registry. It requires the native library unowinreg.dll.
 */
@objid ("92ba4fad-0f7b-413b-9782-43d7d00f2b6b")
final class WinRegKey {
    @objid ("8b893a17-c35e-4d38-be8b-97d5d4deba9f")
    private String m_rootKeyName;

    @objid ("9ec61ffd-352b-493f-a14b-2c7df5087e64")
    private String m_subKeyName;

    /**
     * native methods to access the windows registry
     */
    @objid ("4d89c69a-c583-4c90-be15-5b7466060a0d")
    private static native boolean winreg_RegOpenClassesRoot(final long[] hkresult);

    @objid ("95158f4f-ccfa-40be-b38f-1692a1622ca2")
    private static native boolean winreg_RegOpenCurrentConfig(final long[] hkresult);

    @objid ("5e91b2bc-9dce-4d1a-898e-6adfdbda82bf")
    private static native boolean winreg_RegOpenCurrentUser(final long[] hkresult);

    @objid ("3d47219d-288c-4b93-9d9e-aa5c6bd9f2e1")
    private static native boolean winreg_RegOpenLocalMachine(final long[] hkresult);

    @objid ("956048ff-9595-4a18-baa0-f5d07673b4c6")
    private static native boolean winreg_RegOpenUsers(final long[] hkresult);

    @objid ("76610fd7-de6a-4576-82b2-9a043b17b3bc")
    private static native boolean winreg_RegOpenKeyEx(final long parent, final String name, final long[] hkresult);

    @objid ("acca1a89-501a-4446-bc16-e111f313be35")
    private static native boolean winreg_RegCloseKey(final long hkey);

    @objid ("734ada9e-d366-490d-9740-896254417c7d")
    private static native boolean winreg_RegQueryValueEx(final long hkey, final String value, final long[] type, final byte[] data, final long[] size);

    @objid ("de24c9e6-978e-4ccb-8532-25f850db7c12")
    private static native boolean winreg_RegQueryInfoKey(final long hkey, final long[] subkeys, final long[] maxSubkeyLen, final long[] values, final long[] maxValueNameLen, final long[] maxValueLen, final long[] secDescriptor);

    /**
     * Constructs a <code>WinRegKey</code>.
     * @param rootKeyName The root key name.
     * @param subKeyName The key path.
     */
    @objid ("e2dd20dd-d65e-42cb-8025-fd93d8782a7f")
    public  WinRegKey(final String rootKeyName, final String subKeyName) {
        this.m_rootKeyName = rootKeyName;
        this.m_subKeyName = subKeyName;
        
    }

    /**
     * Reads a string value for the specified value name.
     * @param valueName the value name to read
     * @return the value
     * @throws WinRegKeyException in case of failure.
     */
    @objid ("d7f42f22-1b50-41d6-bc65-d4d329afdb6c")
    public String getStringValue(final String valueName) throws WinRegKeyException {
        byte[] data = getValue( valueName );
        // remove terminating null character
        return new String( data, 0, data.length - 1 );
    }

    /**
     * Reads a value for the specified value name.
     */
    @objid ("a3e5177b-22e3-4ad3-bbe0-c33399ded35d")
    private byte[] getValue(final String valueName) throws WinRegKeyException {
        byte[] result = null;
        long[] hkey = {0};
        
        // open the specified registry key
        boolean bRet = false;
        long[] hroot = {0};
        if ( this.m_rootKeyName.equals( "HKEY_CLASSES_ROOT" ) ) {
            bRet = winreg_RegOpenClassesRoot( hroot );
        } else if ( this.m_rootKeyName.equals( "HKEY_CURRENT_CONFIG" ) ) {
            bRet = winreg_RegOpenCurrentConfig( hroot );
        } else if ( this.m_rootKeyName.equals( "HKEY_CURRENT_USER" ) ) {
            bRet = winreg_RegOpenCurrentUser( hroot );
        } else if ( this.m_rootKeyName.equals( "HKEY_LOCAL_MACHINE" ) ) {
            bRet = winreg_RegOpenLocalMachine( hroot );
        } else if ( this.m_rootKeyName.equals( "HKEY_USERS" ) ) {
            bRet = winreg_RegOpenUsers( hroot );
        } else {
            throw new WinRegKeyException( "unknown root registry key!");
        }
        if ( !bRet ) {
            throw new WinRegKeyException( "opening root registry key " +
                "failed!" );
        }
        if ( !winreg_RegOpenKeyEx( hroot[0], this.m_subKeyName, hkey ) ) {
            if ( !winreg_RegCloseKey( hroot[0] ) ) {
                throw new WinRegKeyException( "opening registry key and " +
                    "releasing root registry key handle failed!" );
            }            
            throw new WinRegKeyException( "opening registry key failed!" );
        }
            
        // get the size of the longest data component among the key's values
        long[] subkeys = {0};
        long[] maxSubkeyLen = {0};
        long[] values = {0};
        long[] maxValueNameLen = {0};
        long[] maxValueLen = {0};
        long[] secDescriptor = {0};
        if ( !winreg_RegQueryInfoKey( hkey[0], subkeys, maxSubkeyLen,
                 values, maxValueNameLen, maxValueLen, secDescriptor ) ) {
            if ( !winreg_RegCloseKey( hkey[0] ) ||
                 !winreg_RegCloseKey( hroot[0] ) ) {
                throw new WinRegKeyException( "retrieving information about " +
                    "the registry key and releasing registry key handles " +
                    "failed!" );
            }                
            throw new WinRegKeyException( "retrieving information about " +
                "the registry key failed!" );
        }
        
        // get the data for the specified value name
        byte[] buffer = new byte[ (int) maxValueLen[0] ];        
        long[] size = new long[1];
        size[0] = buffer.length;
        long[] type = new long[1];
        type[0] = 0;
        if ( !winreg_RegQueryValueEx( hkey[0], valueName, type, buffer,
                 size ) ) {
            if ( !winreg_RegCloseKey( hkey[0] ) ||
                 !winreg_RegCloseKey( hroot[0] ) ) {
                throw new WinRegKeyException( "retrieving data for the " +
                    "specified value name and releasing registry key handles " +
                    "failed!" );
            }
            throw new WinRegKeyException( "retrieving data for the " +
                "specified value name failed!" );
        }
        
        // release registry key handles
        if ( !winreg_RegCloseKey( hkey[0] ) ||
             !winreg_RegCloseKey( hroot[0] ) ) {
            throw new WinRegKeyException( "releasing registry key handles " +
                "failed!" );
        }
        
        result = new byte[ (int) size[0] ];
        System.arraycopy( buffer, 0, result, 0, (int)size[0] );
        return result;
    }

static {        
            try {
                ClassLoader cl = WinRegKey.class.getClassLoader();            
                InputStream is = cl.getResourceAsStream( "win/unowinreg.dll" );
                if ( is != null ) {                
                    // generate a temporary name for lib file and write to temp
                    // location
                    BufferedInputStream istream = new BufferedInputStream( is );
                    File libfile = File.createTempFile( "unowinreg", ".dll" );
                    libfile.deleteOnExit(); // ensure deletion
                    BufferedOutputStream ostream = new BufferedOutputStream(
                        new FileOutputStream( libfile ) );
                    int bsize = 2048; int n = 0;
                    byte[] buffer = new byte[bsize];
                    while ( ( n = istream.read( buffer, 0, bsize ) ) != -1 ) {
                        ostream.write( buffer, 0, n );
                    }        
                    istream.close();
                    ostream.close();            
                    // load library
                    System.load( libfile.getPath() );
                } else {
                    // If the library cannot be found as a class loader resource,
                    // try the global System.loadLibrary(). The JVM will look for
                    // it in the java.library.path.            
                    System.loadLibrary( "unowinreg" );
                }            
            } catch ( java.lang.Exception e ) {
                System.err.println( "com.sun.star.lib.loader.WinRegKey: " +
                    "loading of native library failed!" + e );
            }
        }
    
}
