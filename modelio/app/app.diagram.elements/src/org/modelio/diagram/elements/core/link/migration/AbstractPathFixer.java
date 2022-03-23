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
package org.modelio.diagram.elements.core.link.migration;

import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.model.IPostLoadAction;

/**
 * Base post load action needed for {@link GmLink} migration.
 */
@objid ("05655b93-1d73-4230-8e19-276039c7a301")
abstract class AbstractPathFixer implements IPostLoadAction {
    @objid ("8e8b6e91-dca8-44ef-bfa4-eb6a7eb0a4a6")
    protected GmLink gmLink;

    @objid ("42367644-537c-4f20-ad52-ebd3e93af075")
    public  AbstractPathFixer(GmLink gmLink) {
        this.gmLink = gmLink;
    }

    /**
     * Update the layout data of the {@link #gmLink}.
     * @param newPathData a list of points.
     */
    @objid ("2d22cf5a-7fb9-4416-9a12-76a912494f00")
    protected final void applyLayoutData(List<Point> newPathData) {
        GmPath newGmPath = new GmPath(this.gmLink.getPath());
        newGmPath.setPathData(newPathData);
        this.gmLink.setLayoutData(newGmPath);
        
    }

    @objid ("3a30bc8d-7cba-4bd0-a08e-a9fc26aa7b9e")
    protected final boolean isContentDifferent(List<MPoint> newPointList, PointList oldPointList) {
        if (oldPointList.size() != newPointList.size()) {
            return true;
        }
        
        int[] newPoints = new int[newPointList.size() * 2];
        for (int i = 0; i < newPointList.size(); i++) {
            Point pt = newPointList.get(i);
            newPoints[2 * i] = pt.x;
            newPoints[2 * i + 1] = pt.y;
        }
        return !Arrays.equals(newPoints, oldPointList.toIntArray());
    }

}
