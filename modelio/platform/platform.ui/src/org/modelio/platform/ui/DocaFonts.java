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
package org.modelio.platform.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

/**
 * The DocaFonts fonts are pre-allocated resources for Modelio that can be used without having to deal with their lifecycle (they are available once Modelio starts and only freed when Modelio stops).<br/>
 * These fonts follow the DocaPoste 'Pollux' UI recommendation.</br>
 * They can be used for documents-like UI but they are not intended to be used for UI controls for which  'system' like fonts are recommended  see  {@link UiFont}.
 * 
 * <p>Note that the used Barlow and Montserrat fonts might not be installed on the system in which case fallback fonts will be determined by the system itself with unpredictable results.</p>
 */
@objid ("27034bae-28b2-4ee8-ae16-faa262bb4e9f")
public class DocaFonts {
    @objid ("02066d0b-8045-48e5-936e-c8c6931aa8db")
    public static Font H1 = new Font(Display.getCurrent(), new FontData("Montserrat", 36, SWT.BOLD));

    @objid ("f670ee4f-f261-49ce-b9cb-17053753aab4")
    public static Font H2 = new Font(Display.getCurrent(), new FontData("Montserrat", 32, SWT.BOLD));

    @objid ("f2c2c87d-6788-4a3d-af0c-8dbfbf3beb6c")
    public static Font H3 = new Font(Display.getCurrent(), new FontData("Montserrat", 22, SWT.BOLD));

    @objid ("72560759-f63a-481f-abfc-cdf4cbf45bf6")
    public static Font H4 = new Font(Display.getCurrent(), new FontData("Montserrat", 16, SWT.BOLD));

    @objid ("e5e9c5f0-0820-409f-9ccd-4c15b5f3d2b3")
    public static Font H5 = new Font(Display.getCurrent(), new FontData("Montserrat", 14, SWT.BOLD));

    @objid ("ebb7e8ed-c5d1-42ef-a8fa-15b0b4befc69")
    public static Font SUBTITLE_LARGE = new Font(Display.getCurrent(), new FontData("Barlow", 18, SWT.NORMAL));

    @objid ("24f28dc4-7937-4a6b-9a23-062e2ca7c4d7")
    public static Font SUBTITLE_SMALL = new Font(Display.getCurrent(), new FontData("Barlow", 14, SWT.NORMAL));

    @objid ("c38f3b97-6efd-4348-b3e4-fe2cd01f4680")
    public static Font BODY_LARGE = new Font(Display.getCurrent(), new FontData("Barlow", 16, SWT.NORMAL));

    @objid ("e7ac0722-01b3-46a0-8ce0-9aaff992583f")
    public static Font BODY_NORMAL = new Font(Display.getCurrent(), new FontData("Barlow", 14, SWT.NORMAL));

    @objid ("eff87d16-5cb2-49b1-a953-14c61e2eb176")
    public static Font BODY_SMALL = new Font(Display.getCurrent(), new FontData("Barlow", 12, SWT.NORMAL));

    @objid ("86c2b0df-6ec3-4614-891a-d9a26088f89c")
    public static Font CAPTION = new Font(Display.getCurrent(), new FontData("Barlow", 12, SWT.ITALIC));

}
