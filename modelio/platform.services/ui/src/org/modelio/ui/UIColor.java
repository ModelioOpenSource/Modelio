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

package org.modelio.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * The UIColor values are pre-allocated resources for Modelio that can be used without having to deal with their lifecycle (they are available once Modelio starts and only freed when Modelio stops).<br/>
 * There are three categories of UIColor pre-allocated colors
 * <ul>
 * <li><b>'swt' colors</b>: they are the colors defined by the SWT constants.<br/>
 * The main benefit of using these colors is that several of them are based on the OS/platform theme (where applicable). For this reason these values should always be the first choice. The 'swt'colors are named 'SWT_XXXXX' in UIColor.</li>
 * <li><b>'functional-colors'</b>: named from their intended context and usage specific in Modelio.<br/>
 * The 'functional-colors' name has the form: XXX_YYY_ZZ where:
 * <ul>
 * <li>XXX stands for the SWT widget or category of widget the color applies to.
 * <li>YYY stands for the functional context of the color
 * <li>ZZ is either BG for background, FG for foreground
 * </ul>
 * Example: TEXT_WRITABLE_BG => background color of a writable SWT Text widget</li>
 * <li><b>'color-named' colors</b>: named from the color they represent, for example BLACK, WHITE and so on. The proposed values and names are those from the CGA 16 colors standard palette, plus the ORANGE.
 * </ul>
 * <b>Modelio developers should use these colors in the priority order of their categories, ie 'swt colors' first, then 'functional colors' and lastly 'named colors'.</b>
 */
@objid ("8d550e35-c068-11e1-8c0a-002564c97630")
public interface UIColor {
    @objid ("2e35436e-6f5a-460a-9df1-fe4842fcb1e8")
    public static final Color BLACK = new Color(Display.getCurrent(), 0x00, 0x00, 0x00);

    @objid ("b1445870-13bc-4746-9b19-0db67ed6e2a1")
    public static final Color BLUE = new Color(Display.getCurrent(), 0x00, 0x00, 0xAA);

    @objid ("318f6022-a5b7-4360-82ed-cb17e0a62321")
    public static final Color BROWN = new Color(Display.getCurrent(), 0xAA, 0x55, 0x00);

    @objid ("c03d7400-b85d-4202-8ea3-e6241aa6162e")
    public static final Color CYAN = new Color(Display.getCurrent(), 0x00, 0xAA, 0xAA);

    @objid ("37ebf48a-dd64-4d93-829d-c60034641773")
    public static final Color GRAY = new Color(Display.getCurrent(), 0x55, 0x55, 0x55);

    @objid ("cf7f3292-a985-4764-921a-a3f6f349a5bd")
    public static final Color GREEN = new Color(Display.getCurrent(), 0x00, 0XAA, 0x00);

    @objid ("c907e25a-3eef-4b1f-a917-0e784961f903")
    public static final Color LIGHTBLUE = new Color(Display.getCurrent(), 0x55, 0x55, 0xFF);

    @objid ("4093c1a0-b381-4563-b3c9-7d1003f39fdc")
    public static final Color LIGHTCYAN = new Color(Display.getCurrent(), 0x55, 0xFF, 0xFF);

    @objid ("83def3c6-1d00-4b2e-8729-4e0cb7ef6756")
    public static final Color LIGHTGRAY = new Color(Display.getCurrent(), 0xAA, 0xAA, 0xAA);

    @objid ("cb9ecb4b-c153-481b-b7d1-7c370102e8e8")
    public static final Color LIGHTGREEN = new Color(Display.getCurrent(), 0x55, 0xFF, 0x55);

    @objid ("2ee58c0c-9888-4a94-9ef6-dfc6f5461def")
    public static final Color LIGHTMAGENTA = new Color(Display.getCurrent(), 0xFF, 0x55, 0xFF);

    @objid ("a5d79027-5701-4c25-9b5c-128c34b12d8e")
    public static final Color LIGHTRED = new Color(Display.getCurrent(), 0xFF, 0x55, 0x55);

    @objid ("82a2ddcd-3fa6-4026-980d-f467d665466c")
    public static final Color MAGENTA = new Color(Display.getCurrent(), 0xAA, 0x00, 0xAA);

    @objid ("862134df-7759-4869-909f-0ab3e57e5b9b")
    public static final Color ORANGE = new Color(Display.getCurrent(), 0xFF, 0xC4, 0x00);

    @objid ("45d153e0-6f81-4c5f-a5f1-342229ea1206")
    public static final Color POSTIT_YELLOW = new Color(Display.getCurrent(), 255, 255, 210);

    @objid ("538ae918-32e9-4ea7-af41-f2499091f8eb")
    public static final Color RED = new Color(Display.getCurrent(), 0xAA, 0x00, 0x00);

    @objid ("5de595a3-f382-4a17-aae6-39bdc39cb822")
    public static final Color WHITE = new Color(Display.getCurrent(), 0xFF, 0xFF, 0xFF);

    @objid ("5451e40a-00d6-4e47-bbe6-682beacfcc82")
    public static final Color YELLOW = new Color(Display.getCurrent(), 0xFF, 0xFF, 0x55);

    @objid ("e33f4b7d-4dbc-479d-b8c3-d2c3491e4f60")
    public static final Color SWT_INFO_BACKGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_INFO_BACKGROUND);

    @objid ("0a44d04f-4b7e-4875-bb6c-b913c7db40ee")
    public static final Color SWT_INFO_FOREGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_INFO_FOREGROUND);

    @objid ("f1dec3b2-ce6d-47d4-9a6a-9e78d835180e")
    public static final Color SWT_LINK_FOREGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_LINK_FOREGROUND);

    @objid ("f3358c00-b652-4319-8d8d-463b49a56d33")
    public static final Color SWT_LIST_BACKGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_LIST_BACKGROUND);

    @objid ("72e44865-99f6-47ef-9de2-843b4e5e55ef")
    public static final Color SWT_LIST_FOREGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_LIST_FOREGROUND);

    @objid ("b4a76e7e-abf2-43a2-bf5c-e916b218104f")
    public static final Color SWT_LIST_SELECTION = Display.getCurrent().getSystemColor(SWT.COLOR_LIST_SELECTION);

    @objid ("3782fe2c-316d-4238-8858-d346b195ce78")
    public static final Color SWT_LIST_SELECTION_TEXT = Display.getCurrent().getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT);

    @objid ("54f91d15-a54c-4a55-8217-d74fd956ad81")
    public static final Color SWT_TITLE_BACKGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_BACKGROUND);

    @objid ("668bb1e6-750b-404a-bfa6-f4bec4d6e0d0")
    public static final Color SWT_TITLE_BACKGROUND_GRADIENT = Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT);

    @objid ("dc8231a2-4e68-453b-a765-cff7958524a6")
    public static final Color SWT_TITLE_FOREGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_FOREGROUND);

    @objid ("ca5c8737-26fc-4d0b-ac30-e5f4eea16758")
    public static final Color SWT_TITLE_INACTIVE_BACKGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND);

    @objid ("917933ae-dc39-4b85-b5e9-bc6ba20886d8")
    public static final Color SWT_TITLE_INACTIVE_BACKGROUND_GRADIENT = Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT);

    @objid ("5d4ca26d-0c6e-4389-bed5-790b48acecd6")
    public static final Color SWT_TITLE_INACTIVE_FOREGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND);

    @objid ("66fadca1-5d46-44e9-a442-bb08cba0bdb2")
    public static final Color SWT_TRANSPARENT = Display.getCurrent().getSystemColor(SWT.COLOR_TRANSPARENT);

    @objid ("6d4b838a-e744-49b4-97f3-971df96245d8")
    public static final Color SWT_WIDGET_BACKGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);

    @objid ("01eae3f3-3e32-4a01-a90b-8cb523729fd1")
    public static final Color SWT_WIDGET_BORDER = Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BORDER);

    @objid ("4a6bdf42-af64-44e2-b8cb-40d6abcd09cf")
    public static final Color SWT_WIDGET_DARK_SHADOW = Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW);

    @objid ("e81b752b-cbe2-46de-8188-c14e5c285a3a")
    public static final Color SWT_WIDGET_FOREGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);

    @objid ("4b783d0e-7ab1-45fd-b99f-bbd973953613")
    public static final Color SWT_WIDGET_HIGHLIGHT_SHADOW = Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW);

    @objid ("8be90dda-0f8e-4851-a409-43458753b9e6")
    public static final Color SWT_WIDGET_LIGHT_SHADOW = Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW);

    @objid ("bfee04b4-fd0f-4a43-b4e3-be296b204a97")
    public static final Color SWT_WIDGET_NORMAL_SHADOW = Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW);

    @objid ("df4da4c9-a066-4ec9-bf54-73893e08d57c")
    public static final Color EDITOR_COMMENT_FG = new Color(Display.getCurrent(), 0, 128, 0);

    @objid ("c2ff6735-b87f-49fc-a07e-7b636174b03c")
    public static final Color EDITOR_KEYWORD_FG = new Color(Display.getCurrent(), 120, 0, 164);

    @objid ("ace73310-bf48-4487-82c1-4e74bdc91b12")
    public static final Color EDITOR_MDDTAG_FG = new Color(Display.getCurrent(), 160, 160, 160);

    /**
     * Code Editor highlighting colors. Should not be used for SWT widgets...
     */
    @objid ("123d2e37-2c63-4cc4-af7a-cf85f4789f8b")
    public static final Color EDITOR_ROTEXT_FG = new Color(Display.getCurrent(), 0, 0, 0);

    @objid ("bf6db22f-205f-499b-ad05-89a5259f835a")
    public static final Color EDITOR_RWTEXT_FG = new Color(Display.getCurrent(), 0, 0, 160);

    /**
     * Hyperlink color.
     */
    @objid ("23a9519e-77b8-4067-931f-572b5492edca")
    public static final Color HYPERLINK_FG = new Color(Display.getCurrent(), 0, 0, 128);

    /**
     * Label
     */
    @objid ("97613193-2647-4278-a53a-64c35b101ce0")
    public static final Color LABEL_TIP_FG = new Color(Display.getCurrent(), 113, 111, 100);

    /**
     * Model elements whose metaclass is currently disabled by the metamodel mask, font color is light blue #C0C0FF.
     */
    @objid ("87387729-9ece-45e8-afe3-42ce8d1b75e1")
    public static final Color MASKED_METACLASS_FG = new Color(Display.getCurrent(), 192, 192, 255);

    /**
     * Modifiable model elements font color is black #000000.
     */
    @objid ("4979aa89-d4f5-4562-9b8e-384c2f7f7813")
    public static final Color MODIFIABLE_ELEMENT_FG = UIColor.BLACK;

    @objid ("9e2e1a1e-ae30-45dc-9322-0af01ba15c0e")
    public static final Color NONMODIFIABLE_ELEMENT_FG = new Color(Display.getCurrent(), 96, 96, 96);

    /**
     * Ramc model elements font or line color is modified yellow #3d5c99
     */
    @objid ("9341e66c-ff3e-4b63-a10e-4512a0d6f8d9")
    public static final Color RAMC_ELEMENT_FG = new Color(Display.getCurrent(), 61, 92, 153);

    /**
     * Incomplete model elements font color is light red #FF8080.
     */
    @objid ("91966fc8-fe0b-42ff-bede-fe285f88f3eb")
    public static final Color SHELL_ELEMENT_FG = new Color(Display.getCurrent(), 255, 128, 128);

    /**
     * Table even/odd row colors
     */
    @objid ("5b2dbf7e-3a56-44f3-9b1f-18ea5a439ef7")
    public static final Color TABLE_EVENROW_BG = new Color(Display.getCurrent(), 245, 245, 250);

    @objid ("49f5619d-a70a-4f3c-a3df-2faa1b846f4d")
    public static final Color TABLE_HEADER_BG = new Color(Display.getCurrent(), 220, 220, 220);

    @objid ("0c454eac-998f-4981-82ef-6457bf1268fe")
    public static final Color TABLE_HEADER_FG = new Color(Display.getCurrent(), 64, 64, 64);

    @objid ("437f9b75-ad58-40f6-a707-0e1ac8bbc4a6")
    public static final Color TABLE_ODDROW_BG = new Color(Display.getCurrent(), 255, 255, 255);

    @objid ("bc506949-94e5-4fdc-9fa6-841665df3a81")
    public static final Color TEXT_READONLY_BG = new Color(Display.getCurrent(), 232, 232, 232);

    @objid ("51059b62-563a-4a3a-96f2-260a7b06c2af")
    public static final Color TEXT_READONLY_FG = new Color(Display.getCurrent(), 111, 111, 111);

    /**
     * Text Field colors
     */
    @objid ("357a9357-bbad-442b-a301-ebe0e5eb7a7b")
    public static final Color TEXT_WRITABLE_BG = new Color(Display.getCurrent(), 255, 255, 255);

    @objid ("9ceb6828-03e2-4f88-a087-119785cf46e2")
    public static final Color TEXT_WRITABLE_FG = UIColor.BLACK;

    /**
     * Model elements whose metaclass is missing or unknown, font color is light red #FF8080.
     */
    @objid ("bbecf573-3b14-435d-8db1-d869ed1dbab0")
    public static final Color UNKNOWN_METACLASS_FG = new Color(Display.getCurrent(), 255, 128, 128);

}
