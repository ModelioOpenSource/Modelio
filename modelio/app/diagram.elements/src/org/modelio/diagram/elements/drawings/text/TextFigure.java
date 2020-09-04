/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.elements.drawings.text;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.drawings.core.HAlign;

/**
 * Figure for {@link GmTextDrawing}.
 * <p>
 * Draws a multi-line text flow. The text alignment, font and color can be specified.
 */
@objid ("58a7e56e-493d-4f02-a37d-4b1769f2b10e")
public class TextFigure extends GradientFigure {
    @objid ("aad7c759-7e89-442a-96d8-a4bf7738e585")
    private FlowPage contents;

    @objid ("e91e0ef5-7614-436e-b3e5-43b3cbdbdc31")
    private TextFlow contentsText;

    @objid ("04f2b629-2353-4d13-ad57-9ed7b5ac04a4")
    public TextFigure() {
        this.contents = new FlowPage();
        this.contentsText = new TextFlow();
        this.contents.add(this.contentsText);
        // this.contents.setBorder(new MarginBorder(2));
        this.contents.setOpaque(false);
        this.contents.setHorizontalAligment(PositionConstants.LEFT);
        
        add(this.contents);
        setLayoutManager(new FlowLayout());
    }

    /**
     * Set the note text.
     * @param contents the note text.
     */
    @objid ("1eba2f53-d756-4b0b-8068-320b37cab86f")
    public void setContents(String contents) {
        this.contentsText.setText(contents);
    }

    /**
     * Set the text horizontal alignment.
     * @param align the text horizontal alignment.
     */
    @objid ("5af96411-fc97-406c-a73e-1377427ba276")
    public void setHorizontalAligment(HAlign align) {
        int ialign;
        switch (align) {
        case Center:
            ialign = PositionConstants.CENTER;
            break;
        case Left:
            ialign = PositionConstants.LEFT;
            break;
        case Right:
            ialign = PositionConstants.RIGHT;
            break;
        default:
            ialign = PositionConstants.CENTER;
            break;
        }
        
        if (this.contents.getHorizontalAligment() != ialign) {
            this.contents.setHorizontalAligment(ialign);
        }
    }

    @objid ("498e45af-c321-418b-a5d1-0e1cff56a7f4")
    @Override
    public void setTextColor(Color textColor) {
        this.contents.setForegroundColor(textColor);
        super.setTextColor(textColor);
    }

    @objid ("acbe7fe6-89f3-49ee-9480-c2809b2e06dd")
    @Override
    public void setTextFont(Font textFont) {
        this.contents.setFont(textFont);
        super.setTextFont(textFont);
    }

    @objid ("d7bdf996-db4d-46a2-b74e-6ba87c8d7b49")
    public String getContents() {
        return contentsText.getText();
    }

    @objid ("27efc7f2-ee1f-4358-a8eb-4757c8b4d80b")
    public int getHorizontalAligment() {
        return this.contents.getHorizontalAligment();
    }

}
