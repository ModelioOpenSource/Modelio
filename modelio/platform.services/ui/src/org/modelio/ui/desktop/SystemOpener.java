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

package org.modelio.ui.desktop;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Open an URL using the system configured editor.
 * <p>
 * {@link Desktop} class from AWT is buggy on some platforms:
 * it seems it crashes the VM on Linux if GTK 2 is absent.
 * <p>
 * Use {@link SystemOpener} as a replacement.
 * 
 * @since 4.0
 */
@objid ("842e1c31-8cb0-4634-9193-d872e67be8d1")
public class SystemOpener {
    /**
     * Open the file with the system configured editor.
     * 
     * @param file the file or directory to open
     * @throws java.io.IOException If an I/O error occurs
     */
    @objid ("4531a85b-9648-4652-b178-024ffece83f1")
    public static void open(Path file) throws IOException {
        if (!Files.exists(file)) {
            throw new NoSuchFileException(file.toString());
        }
        
        String currentOS = System.getProperty("os.name").toLowerCase();
        if (currentOS.contains("nix") || currentOS.contains("nux")) { // Linux flavours
            xdgOpen(file.toString());
        } else if (currentOS.contains("mac")) { // Mac OS
            macOpen(file.toString());
        } else {
            desktopOpen(file.toFile());
        }
    }

    /**
     * Open the system default browser on the given URI.
     * 
     * @param uri the URI to browse
     * @throws java.io.IOException If an I/O error occurs
     */
    @objid ("f932aa53-8032-4026-ae02-108b33ab061e")
    public static void browse(URI uri) throws IOException {
        String currentOS = System.getProperty("os.name").toLowerCase();
        
        if (currentOS.contains("nix") || currentOS.contains("nux")) { // Linux flavours
            xdgOpen(uri.toString());
        } else if (currentOS.contains("mac")) { // Mac OS
            macOpen(uri.toString());
        } else {
            desktopBrowse(uri);
        }
    }

    /**
     * Launches the mail composing window of the user default mail client,
     * filling the message fields specified by a mailto: URI.
     * <p>
     * A mailto: URI can specify message fields including "to", "cc", "subject", "body", etc.
     * See The mailto URL scheme (RFC 2368) for the mailto: URI specification details.
     * @see Desktop#mail(URI)
     * 
     * @param mailUri the specified mailto: URI
     * @throws java.io.IOException If an I/O error occurs
     */
    @objid ("251a7ea5-da91-4290-9bf5-9aa891a471a2")
    public static void mailto(URI mailUri) throws IOException {
        String currentOS = System.getProperty("os.name").toLowerCase();
        if (currentOS.contains("nix") || currentOS.contains("nux")) { // Linux flavours
            xdgOpen(mailUri.toString());
        } else if (currentOS.contains("mac")) { // Mac OS
            macOpen(mailUri.toString());
        } else {
            desktopMailto(mailUri);
        }
    }

    @objid ("9f7f6081-c614-4792-99b0-e6ff1ce502ca")
    private static void xdgOpen(String toOpen) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(new String[]{"xdg-open" , toOpen});
    }

    @objid ("e316441e-31da-4ac1-a840-04b0ea801f0d")
    private static void macOpen(String toOpen) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(new String[]{"open", toOpen});
    }

    @objid ("d1ed3c4c-dacb-444f-a14b-d9dacfecfda9")
    private static void desktopOpen(File file) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.OPEN)) {
            desktop.open(file);
        }
    }

    @objid ("2baa8794-07d7-48ed-9df2-c686802ef4f8")
    private static void desktopBrowse(URI uri) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(uri);
            }
        }
    }

    @objid ("dac97154-d430-40d8-ae23-ef2c61dc105e")
    private static void desktopMailto(URI mailUri) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.MAIL)) {
                desktop.mail(mailUri);
            }
        }
    }

}
