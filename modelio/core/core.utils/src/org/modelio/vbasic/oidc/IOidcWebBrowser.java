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
package org.modelio.vbasic.oidc;

import java.net.URI;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Interface that represents a web browser component that displays the login page.
 * @author cmarin
 * @since 5.2
 */
@objid ("1fd37883-b63f-4a18-9522-1787f9154f3d")
public interface IOidcWebBrowser {
    /**
     * Request the browser to display the given URI.
     * <p>
     * The caller expects this method to return as soon as possible and to open the browser asynchronously.
     * <p>
     * The browser should call the given runnable if it detects the user closed the browser or cancelled the authentication.
     * @param reqUri the URL to load in the browser.
     * @param browserClosedListener a cancel listener
     */
    @objid ("d7b7d56a-970b-4988-aff7-22ba12d307a1")
    void browse(URI reqUri, Runnable browserClosedListener);

    /**
     * Request the browser to close.
     * <p>
     * Called by the authentication flow at the end.
     */
    @objid ("3e55a892-beb2-4107-a753-294e862746dc")
    void closeBrowser();
}

