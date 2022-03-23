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
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.labelum.LabelumFigure;

/**
 * Interface for a ModelElement header figure.
 */
@objid ("822ee215-e368-4fcf-97ea-20223175ebd6")
public interface IHeaderFigure extends IPenOptionsSupport, IFigure {
    /**
     * Set the icons displayed on the upper left corner.
     * @param icons The left icons
     */
    @objid ("f16108c9-a1b6-4741-bb2a-473ee6f3ee04")
    void setLeftIcons(List<Image> icons);

    /**
     * Set the icons displayed on the upper right corner.
     * @param icons The right icons
     */
    @objid ("e14c483e-3399-44a5-b5e4-4dd2a3926a0e")
    void setRightIcons(List<Image> icons);

    /**
     * Set the keyword label.
     * @param text the keyword label.
     */
    @objid ("c649d0c6-f816-4710-9554-9483eaf86af1")
    void setKeywordLabel(String text);

    /**
     * Set the labels displayed on top of the main label.
     * @param topLabel the top labels.
     */
    @objid ("074fd410-53f2-4818-ba0e-f11fec59803d")
    void setTopLabel(String topLabel);

    /**
     * Set the main label.
     * @param s the main label.
     */
    @objid ("2280ac7a-3765-4c1f-962a-432c30efa1cf")
    void setMainLabel(String s);

    /**
     * Set the label displayed below the main label.
     * @param bottomLabel the bottom label.
     */
    @objid ("4a903737-4af3-4497-b8be-8960bff9f749")
    void setBottomLabel(String bottomLabel);

    /**
     * Set whether the main label is underlined.
     * @param underline true to underline the main label
     */
    @objid ("fdd95fe1-cf08-444e-a582-49bd7fe0c13f")
    void setUnderline(boolean underline);

    /**
     * Set whether the main label is stroked through.
     * @param strikeThrough true to strike the label
     */
    @objid ("d2527b91-a1fe-4915-aaac-6b194df16e54")
    void setStrikeThrough(boolean strikeThrough);

    /**
     * @return the main label figure.
     */
    @objid ("a3d9dfbe-b016-4ce9-b208-034b94a70049")
    LabelumFigure getMainLabelFigure();

    /**
     * Set whether the header will wrap on other lines if to small horizontally.
     * @param val <i>true</i> to wrap, <i>false</i> to truncate/shrink...
     * @return <i>true</i> only if the wrapping mode changed.
     */
    @objid ("ac442f83-a18e-4774-8488-a7ce59f76c5f")
    boolean setWrapped(boolean val);

    /**
     * Tells whether the header will wrap if it does not fit horizontally.
     * @return whether the header will wrap if it does not fit horizontally.
     */
    @objid ("9c86659a-23f5-49ae-bccb-dd336c85bd6b")
    boolean isWrapped();

}
