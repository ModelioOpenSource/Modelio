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

package org.modelio.diagram.elements.common.header;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.ToolbarRLayout;
import org.modelio.diagram.elements.core.figures.labelum.LabelumFigure;
import org.modelio.diagram.elements.core.figures.labelum.NativeTextLayouter;
import org.modelio.diagram.elements.core.figures.labelum.NoBreakTextLayouter;
import org.modelio.ui.CoreFontRegistry;

/**
 * A header figure is an horizontal stack set of:
 * <ul>
 * <li>an experimental optional 'left' figure</li>
 * <li>an optional 'left' icon</li>
 * <li>a label composed of a keyword, stereotype, main and tag part
 * <li>a 'right' icon area</li>
 * </ul>
 * </p>
 */
@objid ("7e9e819f-1dec-11e2-8cad-001ec947c8cc")
public class FlatHeaderFigure extends GradientFigure implements IHeaderFigure {
    @objid ("907b09d6-1e83-11e2-8cad-001ec947c8cc")
    private String keywordText;

    @objid ("907b09cc-1e83-11e2-8cad-001ec947c8cc")
    private String mainText;

    @objid ("907b09c7-1e83-11e2-8cad-001ec947c8cc")
    private String stereotypeText;

    @objid ("907b09d1-1e83-11e2-8cad-001ec947c8cc")
    private String tagText = "";

    @objid ("357fcf62-267e-41d4-82eb-ff64bd421b60")
    private Font stereotypeFont;

    @objid ("e86632c2-2e44-4019-9976-ebf476834ab5")
    private Font tagFont;

    @objid ("34ee2e46-7e98-4d76-bccf-8e1769b64a78")
    private Figure leftIconsContainer;

    /**
     * left label.
     */
    @objid ("654b918c-1e83-11e2-8cad-001ec947c8cc")
    private LabelumFigure leftLabel;

    /**
     * The main label
     */
    @objid ("7e9e81a5-1dec-11e2-8cad-001ec947c8cc")
    private LabelumFigure mainLabel;

    /**
     * The right icons container.
     */
    @objid ("65492f32-1e83-11e2-8cad-001ec947c8cc")
    private Figure rightIconsContainer;

    @objid ("9668a133-8962-4a3c-81c6-d0ef852b5d57")
    private LabelumFigure rightLabel;

    /**
     * Creates a ModelElementLabelFigure.
     */
    @objid ("7ea0e3b8-1dec-11e2-8cad-001ec947c8cc")
    public FlatHeaderFigure() {
        // The header figure is a horizontal toolbar layouted container.
        // There are plenty children ordered from left to right:
        //   - the left icons
        //   - the keyword
        //   - the left labels
        //   - the main label
        //   - the right labels
        //   - the right icons
        // Children are transparent without borders.
        
        BorderLayout borderLayout = new BorderLayout();
        setLayoutManager(borderLayout);
        
        // Left icons container
        // an horizontal toolbar layouted container, right aligned
        this.leftIconsContainer = new Figure();
        final ToolbarLayout leftIconAreaLayout = new ToolbarLayout(true);
        leftIconAreaLayout.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
        leftIconAreaLayout.setSpacing(1);
        this.leftIconsContainer.setLayoutManager(leftIconAreaLayout);
        this.leftIconsContainer.setOpaque(false);
        this.add(this.leftIconsContainer, BorderLayout.LEFT);
        
        Figure labelsArea = new Figure();
        
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setMajorSpacing(-1); // negative space between lines
        flowLayout.setMinorSpacing(2);
        flowLayout.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
        flowLayout.setObserveVisibility(true);
        labelsArea.setLayoutManager(flowLayout);
        this.add(labelsArea, BorderLayout.CENTER);
        
        // Left stereotypes label
        this.leftLabel = new LabelumFigure();
        this.leftLabel.setLabelAlignment(PositionConstants.LEFT);
        this.leftLabel.setIconAlignment(PositionConstants.LEFT);
        this.leftLabel.setTextMinorAlignment(PositionConstants.CENTER);
        labelsArea.add(this.leftLabel);
        
        
        // Fourth child: the main label area
        this.mainLabel = new LabelumFigure();
        this.mainLabel.setLabelAlignment(PositionConstants.LEFT);
        this.mainLabel.setIconAlignment(PositionConstants.LEFT);
        this.mainLabel.setTextMinorAlignment(PositionConstants.CENTER);
        //debug: this.mainLabel.setBorder(new LineBorder(Display.getCurrent().getSystemColor(SWT.COLOR_CYAN), 1));
        //GridData centerLayoutData = new GridData(SWT.BEGINNING, SWT.CENTER, false, true);
        labelsArea.add(this.mainLabel);
        
        
        // Right tags label
        this.rightLabel = new LabelumFigure();
        this.rightLabel.setLabelAlignment(PositionConstants.RIGHT);
        this.rightLabel.setIconAlignment(PositionConstants.LEFT);
        this.rightLabel.setTextMinorAlignment(PositionConstants.CENTER);
        //GridData rightLLayoutData = new GridData(SWT.BEGINNING, SWT.CENTER, false, true);
        labelsArea. add(this.rightLabel);
        
        // Right icons container
        // an horizontal toolbar layouted container, right aligned
        this.rightIconsContainer = new Figure();
        final ToolbarLayout righIconAreaLayout = new ToolbarRLayout();
        righIconAreaLayout.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
        righIconAreaLayout.setSpacing(1);
        this.rightIconsContainer.setLayoutManager(righIconAreaLayout);
        this.rightIconsContainer.setOpaque(false);
        
        //GridData rightLayoutData = new GridData(SWT.BEGINNING, SWT.CENTER, false, true);
        add(this.rightIconsContainer, BorderLayout.RIGHT);
    }

    @objid ("7ea0e3bb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void addNotify() {
        super.addNotify();
        setTextFont(getFont());
    }

    @objid ("7ea0e3be-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getLineColor() {
        return null;
    }

    @objid ("7ea0e3c3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getLineWidth() {
        return 0;
    }

    /**
     * Get the main label figure.
     * 
     * @return the main label figure.
     */
    @objid ("7ea0e3fe-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public LabelumFigure getMainLabelFigure() {
        return this.mainLabel;
    }

    @objid ("7ea0e3c8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getTextColor() {
        return this.mainLabel.getForegroundColor();
    }

    @objid ("7ea0e3cd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Font getTextFont() {
        return this.mainLabel.getFont();
    }

    /**
     * Tells whether the header will wrap if it does not fit horizontally.
     * 
     * @return whether the header will wrap if it does not fit horizontally.
     */
    @objid ("56cd637c-2110-48dd-b818-382c145a7f76")
    @Override
    public boolean isWrapped() {
        return getLabelsArea().getLayoutManager() instanceof FlowLayout;
    }

    /**
     * Set the right text.
     * 
     * @param value the right text.
     */
    @objid ("7ea0e3f2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setBottomLabel(String value) {
        if (value != null && value.equals(this.tagText))
            return;
        
        // The text should never be null
        if (value == null) {
            this.tagText = "";
        } else {
            this.tagText = value;
        }
        
        updateLabels();
    }

    @objid ("7ea3461a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setFont(final Font textFont) {
        super.setFont(textFont);
        updateDerivedFonts(textFont);
        
        if (this.leftLabel != null)
            this.leftLabel.setFont(this.stereotypeFont);
        
        this.mainLabel.setTextFont(textFont);
        
        if (this.rightLabel != null)
            this.rightLabel.setTextFont(this.tagFont);
    }

    /**
     * Set the keyword labels.
     * 
     * @param value the keyword label.
     */
    @objid ("7ea34616-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setKeywordLabel(String value) {
        if (value != null && value.equals(this.keywordText))
            return;
        
        // The text should never be null
        if (value == null) {
            this.keywordText = "";
        } else {
            this.keywordText = value;
        }
        
        updateLabels();
    }

    @objid ("45c5e817-da20-4548-89e9-43ca6e864157")
    @Override
    public void setLeftIcons(List<Image> icons) {
        // remove existing icons
        this.leftIconsContainer.removeAll();
        // add new image figures
        for (Image img : icons) {
            ImageFigure imgFigure = new ImageFigure(img);
            this.leftIconsContainer.add(imgFigure);
        }
    }

    @objid ("7ea0e3e0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineColor(Color lineColor) {
        // Nothing to do
    }

    @objid ("7ea0e3e4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineWidth(int lineWidth) {
        // Nothing to do
    }

    /**
     * Set the main label.
     * <p>
     * The main label is usually the element name with its signature.
     * 
     * @param s The new main label
     */
    @objid ("7ea0e3e8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setMainLabel(String s) {
        this.mainText = s;
        updateLabels();
    }

    /**
     * Set the right icons.
     * 
     * @param icons the right icons.
     */
    @objid ("7ea0e3ec-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setRightIcons(List<Image> icons) {
        // remove existing icons
        this.rightIconsContainer.removeAll();
        
        // add new image figures
        for (Image img : icons) {
            ImageFigure imgFigure = new ImageFigure(img);
            this.rightIconsContainer.add(imgFigure);
        }
    }

    /**
     * Set whether the main label is stroked through.
     * 
     * @param strikeThrough true to strike the label
     */
    @objid ("7ea34624-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setStrikeThrough(final boolean strikeThrough) {
        this.mainLabel.setStrikeThrough(strikeThrough);
    }

    @objid ("7ea0e3f6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextColor(Color textColor) {
        this.mainLabel.setTextColor(textColor);
    }

    @objid ("7ea0e3fa-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextFont(Font textFont) {
        //this.mainLabel.setFont(textFont);
        this.setFont(textFont);
    }

    /**
     * Set the stereotype labels.
     * 
     * @param value the stereotype label.
     */
    @objid ("7ea0e3dc-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTopLabel(String value) {
        if (value != null && value.equals(this.stereotypeText))
            return;
        
        // The text should never be null
        if (value == null) {
            this.stereotypeText = "";
        } else {
            this.stereotypeText = value;
        }
        
        updateLabels();
    }

    /**
     * Set whether the main label is underlined.
     * 
     * @param underline true to underline the main label
     */
    @objid ("7ea3461f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setUnderline(final boolean underline) {
        this.mainLabel.setUnderline(underline);
    }

    /**
     * Set whether the header will wrap on other lines if to small horizontally.
     * 
     * @param val <i>true</i> to wrap, <i>false</i> to truncate/shrink...
     * @return <i>true</i> only if the wrapping mode changed.
     */
    @objid ("ba6ef3f6-e199-448c-bcee-0b7b3d4c646d")
    @Override
    public boolean setWrapped(boolean val) {
        if (isWrapped() != val) {
            Figure labelsArea = getLabelsArea();
            if (val) {
                FlowLayout flowLayout = new FlowLayout();
                flowLayout.setMajorSpacing(-1); // negative space between lines
                flowLayout.setMinorSpacing(2);
                flowLayout.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
                flowLayout.setObserveVisibility(true);
        
                labelsArea.setLayoutManager(flowLayout);
        
                getMainLabelFigure().setTextLayouter(NativeTextLayouter.INSTANCE);
            } else {
                // no wrap
                ToolbarLayout layout = new ToolbarLayout(true);
                layout.setSpacing(2);
                layout.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
                labelsArea.setLayoutManager(layout);
        
                getMainLabelFigure().setTextLayouter(NoBreakTextLayouter.INSTANCE);
            }
            return true;
        }
        return false;
    }

    /**
     * @return the labels area.
     */
    @objid ("b92e33eb-1417-42b6-87ac-cad28b669578")
    private Figure getLabelsArea() {
        Figure labelsArea = (Figure) getChildren().get(1);
        return labelsArea;
    }

    @objid ("bc736a7d-e6df-415b-a6a6-75da7de12d12")
    private void updateDerivedFonts(Font baseFont) {
        if (this.mainLabel.getFont() == baseFont && this.tagFont != null && this.stereotypeFont != null)
            return;
        
        this.stereotypeFont = FigureUtilities2.getSmallerFont(baseFont);
        this.tagFont = CoreFontRegistry.getModifiedFont(this.stereotypeFont, SWT.ITALIC, 1);
    }

    /**
     * Update the displayed labels
     */
    @objid ("7ea34613-1dec-11e2-8cad-001ec947c8cc")
    private void updateLabels() {
        StringBuilder labelText = new StringBuilder();
        StringBuilder leftText = new StringBuilder();
        
        if (this.keywordText != null && !this.keywordText.isEmpty()) {
            leftText.append(this.keywordText);
            leftText.append(" ");
        }
        
        if (this.stereotypeText != null && !this.stereotypeText.isEmpty()) {
            leftText.append(this.stereotypeText);
            leftText.append(" ");
        }
        
        if (this.mainText != null && !this.mainText.isEmpty()) {
            labelText.append(this.mainText);
            labelText.append(" ");
        }
        
        //        if (this.tagText != null && !this.tagText.isEmpty()) {
        //            labelText.append(this.tagText);
        //        }
        
        this.leftLabel.setText(leftText.toString().trim());
        this.mainLabel.setText(labelText.toString().trim());
        this.rightLabel.setText(this.tagText.trim());
        
        //GridData centerLayoutData = (GridData) getLayoutManager().getConstraint(mainLabel);
        //centerLayoutData.widthHint = mainLabel.getPreferredSize(getSize().width, -1).width();
    }

}
