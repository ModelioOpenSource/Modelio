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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * The Docaposte graphic chart colors.
 */
@objid ("125ab706-2c2a-43f2-abc4-b8446f58e355")
public class DocaColors {
    // Action and interaction color
    @objid ("69f5c032-2db1-4254-840d-05495bdf866b")
    public static final Color DIGITAL_BLUE = new Color(Display.getCurrent(), 0x00, 0x00, 0xFF);

    // Typographic wrapping color
    @objid ("44f591a8-17f0-4dca-a54a-90f3ba8ac227")
    public static final Color ULTRAMARINE_BLUE = new Color(Display.getCurrent(), 0x00, 0x00, 0x8C);

    // Information color
    @objid ("960eff86-b382-4683-b204-7291fb6fd2da")
    public static final Color INFO_BLUE = new Color(Display.getCurrent(), 0x41, 0x7C, 0xFF);

    // Digital blue variations
    @objid ("18ff7c40-b5dc-438d-a776-06b9d8bef153")
    public static final Color DIGITAL_BLUE_05 = new Color(Display.getCurrent(), 0xF2, 0xF2, 0xFF);

    @objid ("78ec2fc5-c6e7-4781-b74e-4fd5096741d0")
    public static final Color DIGITAL_BLUE_10 = new Color(Display.getCurrent(), 0xE5, 0xE5, 0xFD);

    // Component state colors
    @objid ("5444a9bb-2705-47c3-9c0e-fff6fcc1b4cd")
    public static final Color ACTIVE_COLOR = new Color(Display.getCurrent(), 0x00, 0x00, 0xCC);

    @objid ("486047f5-a69b-42cc-b7f6-5c820db73d10")
    public static final Color FOCUSED_COLOR = new Color(Display.getCurrent(), 0x33, 0x63, 0xCC);

    @objid ("31c56b0e-b8bb-4933-b903-98fa53902679")
    public static final Color HOVER_COLOR = new Color(Display.getCurrent(), 0x01, 0x01, 0xE4);

    // Status color
    @objid ("4d55bd15-6f86-414d-8ed8-1ae65973ee43")
    public static final Color ERROR = new Color(Display.getCurrent(), 0xD0, 0x00, 0x00);

    @objid ("9af24a48-1bc9-4a43-919c-c57f7e3cf4f4")
    public static final Color WARNING = new Color(Display.getCurrent(), 0xF1, 0x8F, 0x01);

    @objid ("bcacb4dc-b191-4552-b996-0c59ac1bb42a")
    public static final Color INFO = new Color(Display.getCurrent(), 0x39, 0x70, 0xE6);

    // Black, white, grey and nuances
    @objid ("d4082fcb-699a-4b01-ac8a-5ac944c9c824")
    public static final Color BLACK = new Color(Display.getCurrent(), 0x00, 0x00, 0x00);

    @objid ("c4ed1ade-87ee-4319-bffb-cf39b2a53b06")
    public static final Color GREY = new Color(Display.getCurrent(), 0x80, 0x80, 0x80);

    @objid ("1486ee8b-8795-4f08-a005-29496c23f9e1")
    public static final Color GREY_10 = new Color(Display.getCurrent(), 0xEC, 0xEC, 0xEF);

    @objid ("63c33b15-bdb9-4385-a767-2fafe42e344a")
    public static final Color GREY_20 = new Color(Display.getCurrent(), 0xDA, 0xDB, 0xE0);

    @objid ("3aa56620-f056-49b6-aa53-05200d6fea6d")
    public static final Color GREY_30 = new Color(Display.getCurrent(), 0xC6, 0xC8, 0xD0);

    @objid ("4c5d6fa5-2439-47c6-a23e-a71833ad5424")
    public static final Color WHITE = new Color(Display.getCurrent(), 0xFF, 0xFF, 0xFF);

}
