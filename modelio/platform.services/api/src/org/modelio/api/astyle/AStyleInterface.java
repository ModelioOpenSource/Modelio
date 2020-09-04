/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.astyle;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.plugin.Api;

/**
 * AStyleInterface contains methods to call the Artistic Style formatter(AStylej library). Changing the class name requires changing Artistic Style.
 */
@objid ("005e482c-13d7-1f63-9ca6-001e4fea2d8b")
class AStyleInterface {
    /**
     * Call the AStyleMain function in Artistic Style.
     * 
     * @param textIn A string containing the source code to be formatted.
     * @param options A string of options to Artistic Style.
     * @return A String containing the formatted source from Artistic Style, or an empty string on error.
     */
    @objid ("005e575e-13d7-1f63-9ca6-001e4fea2d8b")
    public String formatSource(final String textIn, final String options) {
        // Return the allocated string
        // Memory space is allocated by OnAStyleMemAlloc, a callback function from AStyle
        String textOut;
        try {
            textOut = AStyleMain(textIn, options);
        } catch (UnsatisfiedLinkError e) {
            Api.LOG.warning("Cannot call function AStyleMain - " + e.getMessage());
            textOut = "";
        }
        return textOut;
    }

    /**
     * Call the AStyleGetVersion function in Artistic Style.
     * 
     * @return A String containing the formatted source from Artistic Style, or an empty string on error.
     */
    @objid ("005e8012-13d7-1f63-9ca6-001e4fea2d8b")
    public String getVersion() {
        String version;
        try {
            version = AStyleGetVersion();
        } catch (UnsatisfiedLinkError e) {
            Api.LOG.warning("Cannot call function GetVersion");
            version = "";
        }
        return version;
    }

    /**
     * Calls the AStyleMain function in Artistic Style.
     * 
     * @param textIn A string containing the source code to be formatted.
     * @param options A string of options to Artistic Style.
     * @return A String containing the formatted source from Artistic Style.
     */
    @objid ("005e9ffc-13d7-1f63-9ca6-001e4fea2d8b")
    public native String AStyleMain(final String textIn, final String options);

    /**
     * Calls the AStyleGetVersion function in Artistic Style.
     * 
     * @return A String containing the version number of Artistic Style.
     */
    @objid ("005ec068-13d7-1f63-9ca6-001e4fea2d8b")
    public native String AStyleGetVersion();

    /**
     * Default error handler used by Artistic Style.
     * 
     * @param id The error id.
     * @param str The error message.
     */
    @objid ("005ed788-13d7-1f63-9ca6-001e4fea2d8b")
    public void ErrorHandler(final int id, final String str) {
        Api.LOG.error(str);
    }


static {
        /**
         * This code is in charge of loading the native libraries required by hades.mda. The chosen loading mechanism is
         * based of the Bundle-NativeCode clause in the plugin manifest where loaded DLLs are declared along with their
         * supported platform and os.
         */
        try {
            System.loadLibrary("AStylej");
        } catch (UnsatisfiedLinkError x) {
            Api.LOG.warning("  - loading 'AStylej' failed, reason is " + x.getMessage());
        }
    }
}
