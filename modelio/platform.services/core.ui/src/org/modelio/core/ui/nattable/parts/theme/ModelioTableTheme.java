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

package org.modelio.core.ui.nattable.parts.theme;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.style.BorderStyle;
import org.eclipse.nebula.widgets.nattable.style.HorizontalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.style.VerticalAlignmentEnum;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.modelio.ui.CoreColorRegistry;
import org.modelio.ui.UIColor;

/**
 * Modelio-specific theme for a NatTable.
 */
@objid ("1c6712d4-cf4b-48d0-b6a9-d6b2ea118b37")
public class ModelioTableTheme {
    @objid ("dc978171-0ddf-49e2-a109-8d9f6442d3ca")
    public final HorizontalAlignmentEnum cellHAlign;

    @objid ("6447c016-400a-478e-b35d-44ce69c13a95")
    public final VerticalAlignmentEnum cellVAlign;

    @objid ("9f89746e-267c-4619-af0a-ebcd8e3aadae")
    public final HorizontalAlignmentEnum horizontalColumnHeaderHAlign;

    @objid ("ed1df0af-1d97-40a3-b20f-778636089f60")
    public final VerticalAlignmentEnum horizontalColumnHeaderVAlign;

    @objid ("914dc991-9701-47cb-9e31-ac15f7122242")
    public HorizontalAlignmentEnum rowHeaderHAlign;

    @objid ("bf2a5cdf-b112-48aa-a64b-2aec51b44d30")
    public VerticalAlignmentEnum rowHeaderVAlign;

    @objid ("bb8dc0be-bddc-44da-bb26-08318942759f")
    public final HorizontalAlignmentEnum verticalColumnHeaderHAlign;

    @objid ("041de8c8-526b-46e1-9315-559e0abd9489")
    public final VerticalAlignmentEnum verticalColumnHeaderVAlign;

    @objid ("3c108f3e-80b0-47f1-88be-7c00069318c2")
    public static int DEFAULT_COLUMN_WIDTH = 40;

    @objid ("3ef14841-cce7-42b3-b2a1-4c1402d012d0")
    public static int DEFAULT_ROW_HEIGHT = 24+4;

    @objid ("e28e176a-9822-4347-83d7-b3ca75e4cce9")
    public final Color cellBackground;

// Normal (unselected) cell
    @objid ("d14d5a43-9d77-482b-9583-7e38b153de88")
    public final Font cellFont;

    @objid ("a913622b-4de9-4dd4-a5fe-44f17fd49946")
    public final Color cellForeGround;

    @objid ("10c2b8ce-22a7-4189-85ab-8f47238d8762")
    public final Color evenRowBackground;

// Normal (unselected) header
    @objid ("71a2f05f-d18a-4540-9170-935add701bcb")
    public final Color headerBackground;

    @objid ("e4051057-f4e5-4213-b925-4372c0991af1")
    public final Font headerFont;

    @objid ("1c54993c-d4b3-453e-a74c-e08668c71c8e")
    public final Color headerForeground;

    @objid ("9b69a1c9-6f5d-45c8-a5de-2871cab4f91e")
    public final Color headerGradientBackground;

    @objid ("eb7822f5-b302-4fa6-87ae-8f797150af4b")
    public final Color headerGradientForeground;

    @objid ("30a77a1b-ef08-4e3d-82e1-75e8a668e185")
    private static ModelioTableTheme instance = null;

// Alternate row background colors
    @objid ("f72a0713-e49f-498d-9a40-00c54968fbfe")
    public final Color oddRowBackground;

    @objid ("2859c1b0-411a-4531-9ec1-a2ecc459966b")
    public final Color selectedCellBackground;

    @objid ("9e601593-d360-411e-b095-84a32251071e")
    public final Font selectedCellFont;

    @objid ("cb5d5a57-9f6c-4860-885e-206b64ef306c")
    public final Color selectedCellForeground;

// Selected cell
    @objid ("a04bbba8-7066-4319-9418-5efd606baa32")
    public final Color selectedCellGridlineColor;

// Selected header
    @objid ("de62be25-2aae-4c3c-b5b1-b7b14441696f")
    public final Color selectedHeaderBackground;

    @objid ("a07b65c9-9616-410c-b56a-deb0961312af")
    public final Font selectedHeaderFont;

    @objid ("aedbaf30-917b-480d-829a-dffec42a3882")
    public final Color selectedHeaderForeground;

    @objid ("99f711f7-4d33-4b84-8ae4-06886b3c7a01")
    public final Color selectedHeaderGradientBackground;

    @objid ("59c8b40e-4013-47e0-a1f9-884605aeb026")
    public final Color selectedHeaderGradientForeground;

    @objid ("630d33e7-153d-4c4b-b9c8-fad8336b9989")
    public final Color selectedRowColumnBackground;

    @objid ("f224b489-117d-4206-890a-efd37fd042ee")
    public final Font selectedRowColumnFont;

    @objid ("9dd2bf8a-e85f-4714-991a-e8933e651b9d")
    public final Color selectedRowColumnForeground;

    @objid ("06d5ece0-3e90-4216-af8d-bc85f090b673")
    public final BorderStyle selectedCellGridBorderStyle;

    @objid ("371a6bc6-5477-490b-bd92-86f5f4445541")
    public static ModelioTableTheme getInstance() {
        return new ModelioTableTheme();
    }

    @objid ("d0812a1e-6d57-4527-9b75-03fa76da87d2")
    private ModelioTableTheme() {
        // Use platform theme values where possible
        Color systemSelectionBackground = Display.getCurrent().getSystemColor(SWT.COLOR_LIST_SELECTION);
        Color systemSelectionForeground = Display.getCurrent().getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT);
        Font defaultFont = Display.getCurrent().getSystemFont();
        
        // Normal (unselected) cell
        this.cellBackground = UIColor.WHITE;
        this.cellForeGround = UIColor.BLACK;
        this.cellFont = defaultFont;
        this.cellHAlign = HorizontalAlignmentEnum.LEFT;
        this.cellVAlign = VerticalAlignmentEnum.TOP;
        
        // Normal (unselected) header
        this.headerBackground = UIColor.TABLE_HEADER_BG;
        this.headerGradientBackground = this.headerBackground;
        this.headerGradientForeground = CoreColorRegistry.getDerivedColor(UIColor.WHITE, 1.5f);
        this.headerForeground = UIColor.TABLE_HEADER_FG;
        
        this.headerFont = defaultFont;
        
        this.verticalColumnHeaderHAlign = HorizontalAlignmentEnum.CENTER;
        this.verticalColumnHeaderVAlign = VerticalAlignmentEnum.MIDDLE;
        
        this.horizontalColumnHeaderHAlign = HorizontalAlignmentEnum.CENTER;
        this.horizontalColumnHeaderVAlign = VerticalAlignmentEnum.MIDDLE;
        
        this.rowHeaderHAlign = HorizontalAlignmentEnum.LEFT;
        this.rowHeaderVAlign = VerticalAlignmentEnum.TOP;
        
        // Alternate row background colors
        this.oddRowBackground = UIColor.TABLE_ODDROW_BG; // CoreColorRegistry.getColor(new
        // RGB(249, 249,
        // 255));
        this.evenRowBackground = UIColor.TABLE_EVENROW_BG; // GUIHelper.COLOR_WHITE;
        
        // Selected cell
        this.selectedCellGridlineColor = systemSelectionBackground;
        this.selectedCellGridBorderStyle = new BorderStyle(2, this.selectedCellGridlineColor, BorderStyle.LineStyleEnum.SOLID);
        
        this.selectedCellFont = defaultFont;
        this.selectedCellBackground = this.cellBackground; // systemSelectionBackground;
        this.selectedCellForeground = this.cellForeGround; // systemSelectionForeground;
        
        this.selectedRowColumnFont = defaultFont;
        this.selectedRowColumnBackground = systemSelectionBackground;
        this.selectedRowColumnForeground = systemSelectionForeground;
        
        // Selected header
        this.selectedHeaderFont = defaultFont;
        this.selectedHeaderBackground = systemSelectionBackground;
        this.selectedHeaderGradientBackground = this.selectedHeaderBackground;
        this.selectedHeaderGradientForeground = this.selectedHeaderBackground;
        
        this.selectedHeaderForeground = systemSelectionForeground;
    }

}
