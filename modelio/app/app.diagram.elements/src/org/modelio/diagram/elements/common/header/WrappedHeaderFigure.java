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
package org.modelio.diagram.elements.common.header;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.ToolbarRLayout;
import org.modelio.diagram.elements.core.figures.labelum.LabelumFigure;
import org.modelio.platform.ui.CoreFontRegistry;

/**
 * A wrapped header figure is a stack set of:
 * <ul>
 * <li>an icon area</li>
 * <li>a 'top' labels area, containing a vertical stack of labels</li>
 * <li>main label</li>
 * <li>a 'bottom' labels area, containing a vertical stack of labels</li>
 * </ul>
 * <p>
 * <br>
 * All labels wraps to a new line. The top and bottom labels are displayed using a font derived from the main label font (reduced
 * size).
 * </p>
 */
@objid ("7e75f9af-1dec-11e2-8cad-001ec947c8cc")
public class WrappedHeaderFigure extends GradientFigure implements IHeaderFigure {
    @objid ("66b5f6fb-1e83-11e2-8cad-001ec947c8cc")
    private Figure topArea;

    @objid ("66b13249-1e83-11e2-8cad-001ec947c8cc")
    private Figure rightArea;

    @objid ("0ba8b524-6f4d-4ae0-8dab-2df39f8d8c12")
    private LabelumFigure bottomLabel;

    @objid ("66b13248-1e83-11e2-8cad-001ec947c8cc")
    private Figure leftArea;

    @objid ("7e785bcb-1dec-11e2-8cad-001ec947c8cc")
    private LabelumFigure mainLabel;

    @objid ("66b1324a-1e83-11e2-8cad-001ec947c8cc")
    private LabelumFigure keywordLabel;

    @objid ("66b394a1-1e83-11e2-8cad-001ec947c8cc")
    private LabelumFigure topLabels;

    @objid ("7e785bcf-1dec-11e2-8cad-001ec947c8cc")
    private Font stereotypeFont = null;

    @objid ("7e785bd0-1dec-11e2-8cad-001ec947c8cc")
    private Font tagFont = null;

    /**
     * Constructor.
     */
    @objid ("7e7abe1a-1dec-11e2-8cad-001ec947c8cc")
    public  WrappedHeaderFigure() {
        // The header figure is a 'BorderLayout' container.
        // Children layout:
        // - TOP : topArea - figure with 2 LabelumFigure (keyword and stereotypes labels)
        // - RIGHT : rightArea - Figure with tool bar layout (stereotypes icons)
        // - BOTTOM: bottomArea - LabelumFigure ( tagged values)
        // - LEFT : leftArea - Figure with tool bar layout (metaclass icon)
        // - CENTER: contentsArea (main label)
        // Children are transparent without borders
        
        setLayoutManager(new BorderLayout());
        // TRACE: container.setBorder(new LineBorder(ColorConstants.orange, 2));
        
        // -- LEFT Area --
        this.leftArea = createLeftFigures();
        this.add(this.leftArea, BorderLayout.LEFT);
        
        // -- RIGHT Area --
        this.rightArea = createRightFigures();
        this.add(this.rightArea, BorderLayout.RIGHT);
        
        // -- TOP Area --
        
        this.topArea = createTopFigures();
        this.add(this.topArea, BorderLayout.TOP);
        
        // -- BOTTOM area --
        // Lazily added in setBottomLabel(). Do nothing
        
        // -- CENTER Area --
        // a Labelum
        this.mainLabel = createCenterFigures();
        
    }

    /**
     * Set the icons displayed on the upper left corner.
     * @param icons The left icons
     */
    @objid ("7e7abe1d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLeftIcons(List<Image> icons) {
        // remove existing labels
        this.leftArea.removeAll();
        // add new image figures
        for (Image img : icons) {
            ImageFigure imgFigure = new ImageFigure(img);
            this.leftArea.add(imgFigure);
        }
        
        this.leftArea.setVisible(!icons.isEmpty());
        
    }

    /**
     * Set the icons displayed on the upper right corner.
     * @param icons The right icons
     */
    @objid ("7e7abe23-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setRightIcons(List<Image> icons) {
        // remove existing labels
        this.rightArea.removeAll();
        // add new image figures
        for (Image img : icons) {
            ImageFigure imgFigure = new ImageFigure(img);
            this.rightArea.add(imgFigure);
        }
        
        this.rightArea.setVisible(!icons.isEmpty());
        
    }

    /**
     * Set the keyword label.
     * @param text the keyword label.
     */
    @objid ("7e7abe29-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setKeywordLabel(String text) {
        if (text == null || text.isEmpty()) {
            this.keywordLabel.setVisible(false);
        } else {
            this.keywordLabel.setText(text);
            this.keywordLabel.setVisible(true);
        }
        
    }

    /**
     * Set the labels displayed on top of the main label.
     * @param text the top labels.
     */
    @objid ("7e7abe2d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTopLabel(String text) {
        this.topLabels.setVisible(text != null && !text.isEmpty());
        this.topLabels.setText(text);
        
    }

    /**
     * Set the main label.
     * @param s the main label.
     */
    @objid ("7e7abe33-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setMainLabel(String s) {
        this.mainLabel.setText(s);
        
        if (s.isEmpty()) {
            if (this.mainLabel.getParent() == this) {
                remove(this.mainLabel);
            }
        } else if (this.mainLabel.getParent() == null) {
            this.add(this.mainLabel, BorderLayout.CENTER);
        }
        
        revalidate();
        
    }

    /**
     * Set the labels displayed below the main label.
     * @param bottomLabels the bottom labels.
     */
    @objid ("7e7abe37-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setBottomLabel(String bottomLabels) {
        if (bottomLabels == null || bottomLabels.isEmpty()) {
            if (this.bottomLabel != null) {
                remove(this.bottomLabel);
                this.bottomLabel = null;
            }
        } else {
            if (this.bottomLabel == null) {
                this.bottomLabel = new LabelumFigure(bottomLabels);
        
                this.bottomLabel.setFont(this.tagFont);
                this.bottomLabel.setTextColor(this.mainLabel.getTextColor());
                this.bottomLabel.setLabelAlignment(PositionConstants.CENTER);
                // TRACE: this.bottomLabelsArea.setBorder(new LineBorder(ColorConstants.blue, 1));
        
                this.add(this.bottomLabel, BorderLayout.BOTTOM);
            } else {
                this.bottomLabel.setText(bottomLabels);
            }
        }
        
    }

    // @objid ("7e7abe44-1dec-11e2-8cad-001ec947c8cc")
    // @Override
    // public Color getTextColor() {
    // return this.mainLabel.getForegroundColor();
    // }
    // @objid ("7e7abe49-1dec-11e2-8cad-001ec947c8cc")
    // @Override
    // public Font getTextFont() {
    // return this.mainLabel.getFont();
    // }
    @objid ("7e7abe4e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextColor(Color textColor) {
        this.topLabels.setTextColor(textColor);
        if (this.keywordLabel != null) {
            this.keywordLabel.setForegroundColor(textColor);
        }
        this.mainLabel.setTextColor(textColor);
        if (this.bottomLabel != null) {
            this.bottomLabel.setTextColor(textColor);
        }
        super.setTextColor(textColor);
        
    }

    @objid ("7e7abe52-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextFont(Font textFont) {
        updateDerivedFonts(textFont);
        
        this.topLabels.setTextFont(this.stereotypeFont);
        if (this.keywordLabel != null) {
            this.keywordLabel.setFont(this.stereotypeFont);
        }
        this.mainLabel.setTextFont(textFont);
        if (this.bottomLabel != null) {
            this.bottomLabel.setTextFont(this.tagFont);
        }
        super.setTextFont(textFont);
        
    }

    // @objid ("7e7abe59-1dec-11e2-8cad-001ec947c8cc")
    // @Override
    // public Color getLineColor() {
    // // return null;
    // return super.getLineColor();
    // }
    // @objid ("7e7abe5e-1dec-11e2-8cad-001ec947c8cc")
    // @Override
    // public int getLineWidth() {
    // return super.getLineWidth(); // WAS return 0;
    // }
    // @objid ("7e7abe63-1dec-11e2-8cad-001ec947c8cc")
    // @Override
    // public void setLineColor(Color lineColor) {
    //
    // super.setLineColor(lineColor);
    // }
    //
    // @objid ("7e7d2071-1dec-11e2-8cad-001ec947c8cc")
    // @Override
    // public void setLineWidth(int lineWidth) {
    //
    // super.setLineWidth(lineWidth);
    // }
    @objid ("7e7d2079-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Dimension getMinimumSize(int wHint, int hHint) {
        // Always ignore the vertical hint!
        return super.getMinimumSize(wHint, -1);
    }

    /**
     * Set whether the main label is underlined.
     * @param underline true to underline the main label
     */
    @objid ("7e7d2082-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setUnderline(final boolean underline) {
        this.mainLabel.setUnderline(underline);
    }

    /**
     * Set whether the main label is stroked through.
     * @param strikeThrough true to strike the label
     */
    @objid ("7e7d2087-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setStrikeThrough(final boolean strikeThrough) {
        this.mainLabel.setStrikeThrough(strikeThrough);
    }

    /**
     * Get the main label figure.
     * @return the main label figure.
     */
    @objid ("7e7abe3d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public LabelumFigure getMainLabelFigure() {
        return this.mainLabel;
    }

    @objid ("7e7abe56-1dec-11e2-8cad-001ec947c8cc")
    private void updateDerivedFonts(Font baseFont) {
        if (this.mainLabel.getFont() == baseFont && this.tagFont != null && this.stereotypeFont != null) {
            return;
        }
        
        this.stereotypeFont = FigureUtilities2.getSmallerFont(baseFont);
        this.tagFont = CoreFontRegistry.getModifiedFont(this.stereotypeFont, SWT.ITALIC, 1);
        
    }

    @objid ("aae63863-c1fa-497f-8254-c7ede775788e")
    protected LabelumFigure createCenterFigures() {
        LabelumFigure label = new LabelumFigure();
        label.setLabelAlignment(PositionConstants.CENTER);
        
        label.setBorder(new MarginBorder(2, 0, 3, 0));
        
        this.add(label, BorderLayout.CENTER);
        return label;
    }

    /**
     * A figure holding two texts: keyword and stereotypes
     */
    @objid ("ed7ac99f-993e-4c96-8279-3e2247e6445b")
    protected Figure createTopFigures() {
        Figure topFigure = new Figure();
        topFigure.setLayoutManager(new BorderLayout());
        
        // first row : Keyword label
        this.keywordLabel = new LabelumFigure("");
        this.keywordLabel.setLabelAlignment(PositionConstants.CENTER);
        this.keywordLabel.setVisible(false);
        topFigure.add(this.keywordLabel, BorderLayout.TOP);
        
        // second row: top labels
        this.topLabels = new LabelumFigure();
        this.topLabels.setLabelAlignment(PositionConstants.CENTER);
        this.topLabels.setVisible(false);
        // TRACE: this.topLabelsArea.setBorder(new LineBorder(ColorConstants.green, 1));
        topFigure.add(this.topLabels, BorderLayout.BOTTOM);
        return topFigure;
    }

    /**
     * An horizontal tool bar layouted container, center aligned
     */
    @objid ("4aa4308a-f09c-4ce2-9ff0-58f28e9bc061")
    protected Figure createRightFigures() {
        Figure rightFigure = new Figure();
        ToolbarLayout tbLayout = new ToolbarRLayout();
        tbLayout.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
        tbLayout.setSpacing(1);
        rightFigure.setLayoutManager(tbLayout);
        rightFigure.setOpaque(false);
        rightFigure.setBorder(new MarginBorder(new Insets(0, 1, 0, 1)));
        // TRACE: rightIconsContainer.setBorder(new LineBorder(1));
        return rightFigure;
    }

    /**
     * an horizontal tool bar layouted container, center aligned
     */
    @objid ("3507a4fb-44ec-43eb-85bf-4b6b7db34ed9")
    protected Figure createLeftFigures() {
        Figure leftFigure = new Figure();
        ToolbarLayout tbLayout = new ToolbarLayout(true);
        tbLayout.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
        tbLayout.setSpacing(1);
        leftFigure.setLayoutManager(tbLayout);
        leftFigure.setOpaque(false);
        leftFigure.setBorder(new MarginBorder(new Insets(0, 1, 0, 1)));
        // TRACE: leftIconsContainer.setBorder(new LineBorder(1));
        return leftFigure;
    }

    /**
     * TODO fix doc
     * Sets the horizontal alignment of the main label block. Valid values are:
     * <UL>
     * <LI>{@link PositionConstants#NONE NONE} - (default) Alignment is
     * inherited from parent. If a parent is not found then LEFT is used.</LI>
     * <LI>{@link PositionConstants#LEFT} - Alignment is with leading edge</LI>
     * <LI>{@link PositionConstants#RIGHT} - Alignment is with trailing edge</LI>
     * <LI>{@link PositionConstants#CENTER}</LI>
     * <LI>{@link PositionConstants#ALWAYS_LEFT} - Left, irrespective of
     * orientation</LI>
     * <LI>{@link PositionConstants#ALWAYS_RIGHT} - Right, irrespective of
     * orientation</LI>
     * </UL>
     * @param value the alignment
     */
    @objid ("0f194a3d-86e7-492a-9811-d1928c2bf940")
    public void setMainLabelAlignement(int value) {
        this.mainLabel.setLabelAlignment(value);
    }

    /**
     * TODO fix doc
     * Returns the effective horizontal alignment of the main label block.
     * This method will never return {@link PositionConstants#NONE}. If the value is none, it will return the
     * inherited alignment. If no alignment was inherited, it will return the
     * default alignment ({@link PositionConstants#LEFT}).
     * @return the effective alignment
     */
    @objid ("7c2231e4-9c9b-4dd6-8bfb-b6bf1cf05d9f")
    public int getMainLabelAlignement() {
        return this.mainLabel.getLabelAlignment();
    }

    /**
     * Wrapping change not supported, do nothing.
     */
    @objid ("e8a6603e-eceb-4d64-a5b8-00bcd79b0c62")
    @Override
    public boolean setWrapped(boolean val) {
        return false;
    }

    @objid ("99123013-14f2-4350-a149-8c19fadbb362")
    @Override
    public boolean isWrapped() {
        return true;
    }

    @objid ("23be3bda-da1e-4cfe-9fec-89504cb1ecb7")
    @Override
    protected void layout() {
        // Standard behavior
        super.layout();
        
    }

}
