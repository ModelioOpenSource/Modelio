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
package org.modelio.metamodel.impl.mmextensions.standard.migration.from_bpmn_36;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;

/**
 * Utility to delete LaneSets that have no Lane.
 */
@objid ("6b823f00-52b2-4e02-938a-836d6cef7dc1")
class LaneSetCleaner {
    /**
     * Clean up empty LaneSets.
     * @param process a BPMN Process
     * @param logger the logger
     */
    @objid ("dba59487-adfa-4a2d-a567-c7ea2abecbfc")
    public static void deleteProcessEmptyLaneSets(MofSmObjectImpl process, PrintWriter logger) {
        List<MofSmObjectImpl> origRootPools = new ArrayList<>(process.getDep("LaneSet"));
        for (MofSmObjectImpl origPool : origRootPools) {
            // There should be only one lane in pools.
            List<MofSmObjectImpl> poolLanes = origPool.getDep("Lane");
            if (poolLanes.size() > 1) {
                logger.format("  WARNING: The %s pool contains %d lanes instead of one: %s.\n", origPool, poolLanes.size(), poolLanes);
            }
        
            cleanupLaneSet(logger, origPool, process);
        }
        
    }

    @objid ("4e11a529-554d-4e43-ae17-57acce17e994")
    private static void deleteLaneEmptyLaneSets(MofSmObjectImpl lane, PrintWriter logger) {
        List<MofSmObjectImpl> childLaneSets = lane.getDep("ChildLaneSet");
        for (MofSmObjectImpl laneSet : new ArrayList<>(childLaneSets)) {
            cleanupLaneSet(logger, laneSet, lane);
        }
        
    }

    @objid ("60e8bccf-ec16-4fcc-8134-ab7f139d32b3")
    private static void cleanupLaneSet(PrintWriter logger, MofSmObjectImpl laneSet, MofSmObjectImpl laneSetOwner) {
        final List<MofSmObjectImpl> childLanes = laneSet.getDep("Lane");
        if (childLanes.isEmpty()) {
            // Empty LaneSet, delete it.
            logger.format("   The %s LaneSet in %s contains no lane, delete it.\n", laneSet, laneSetOwner);
            laneSet.delete();
        }
        
        for (MofSmObjectImpl childLane : childLanes) {
            deleteLaneEmptyLaneSets(childLane, logger);
        }
        
    }

}
