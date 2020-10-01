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

package com.sun.star.comp.beans;

import java.lang.reflect.Field;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.lang.SystemDependent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * OS Detection service class.
 * @author cmarin
 */
@objid ("b9b53cb5-da43-4d8e-a527-b095fe6cd612")
public class OSDetect {
    /**
     * Operating System Name system property.
     */
    @objid ("7ee605c9-5efd-4517-a0be-2d491c7605fc")
    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();

    /**
     * Tells whether the OS is a Linux
     * 
     * @return <code>true</code> if the OS is Linux.
     */
    @objid ("7cac20e4-5fd6-42c1-b298-60bc3143b11b")
    public static boolean isLinux() {
        return isNix() ;
    }

    /**
     * Don't instantiate.
     */
    @objid ("2f9d665f-fef9-43ba-b7cd-c1952b3d58c5")
    private OSDetect() {
    }

    /**
     * Test whether the runtime operating system is a Windows variant.
     * 
     * @return true if the runtime OS is Windows
     */
    @objid ("f147a91d-b9c8-4095-920b-b9cdca218fe3")
    public static boolean isWindows() {
        return OS_NAME.indexOf("win") != -1;
    }

    /**
     * Test whether the runtime operating system is "unix-like".
     * 
     * @return true if the runtime OS is unix-like, Linux, Unix, FreeBSD etc
     */
    @objid ("385174d0-7535-46d6-a608-5b7ce6958fa1")
    public static boolean isNix() {
        return OS_NAME.indexOf("nux") != -1 || OS_NAME.indexOf("nix") != -1 || OS_NAME.indexOf("freebsd") != -1;
    }

    /**
     * Test whether the runtime operating system is a Mac variant.
     * 
     * @return true if the runtime OS is Mac
     */
    @objid ("cf60fdea-5d98-42b0-ae89-1529321f478c")
    public static boolean isMac() {
        return OS_NAME.indexOf("mac") != -1;
    }

    /**
     * Retrieves a platform dependent system window identifier.
     * 
     * @return The system window identifier.
     */
    @objid ("1826119c-ba98-41be-8989-55e3361dfa2a")
    public static long getNativeWindow(Composite c) {
        try {
            Field _viewField;
            Field _idField;
            if (OSDetect.isMac()) {
                _viewField = Control.class.getDeclaredField("view");
                Object view = _viewField.get(c);
                Class<?> idClass = Class.forName("org.eclipse.swt.internal.cocoa.id");
                _idField = idClass.getDeclaredField("id");
                return _idField.getLong(view);
        
            } else if (OSDetect.isNix()) {
                _idField = Composite.class.getDeclaredField("embeddedHandle");
                return _idField.getLong(c);
            } else {
                _idField = Control.class.getDeclaredField("handle");
                return _idField.getLong(c);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a platform dependent system window type.
     * 
     * @return The system window type.
     */
    @objid ("92ef48cd-3906-4910-b2a0-c23b87fd3c24")
    public static short getNativeWindowSystemType() {
        if (OSDetect.isMac()) {
            return SystemDependent.SYSTEM_MAC;
        } else if (OSDetect.isWindows()) {
            return SystemDependent.SYSTEM_WIN32;
        } else {
            return SystemDependent.SYSTEM_XWINDOW;
        }
    }

}
