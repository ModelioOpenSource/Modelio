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
package org.modelio.metamodel.impl.mmextensions.standard.migration.from_36;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.spi.mm.IMofSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;

/**
 * Move all BpmnLane.PartitionElement containing BPMN elements to BpmnLane.BpmnPartitionElementRef .
 */
@objid ("21adc66c-0c7e-4ec7-ab50-56d59610214d")
public class BpmnLanePartitionMigrator {
    @objid ("7e64f587-5884-4965-a164-65c66dbf79ed")
    private final MClass bpmnLaneMC;

    @objid ("8fa498f8-05b6-4bbd-8ed5-fec36130ee96")
    private final MClass bpmnBaseElementMc;

    @objid ("08fef386-6597-40c5-ba56-b9e9ddb3487c")
    private final MClass umlModelElementMc;

    @objid ("7a87db4d-9ba9-4d6e-9e21-6c60fe4525b1")
    private final MClass dependencyMc;

    @objid ("b403b211-a9c3-4d4e-bc99-4f0d02c94c8c")
    private final IMofSession mofSession;

    /**
     * Move all BpmnLane.PartitionElement containing BPMN elements to BpmnLane.BpmnPartitionElementRef .
     * @param mofSession the MOF session
     * @param monitor a progress monitor
     */
    @objid ("590904ba-f5ae-41d5-ad52-e18a97f3cc9b")
    public void run(IModelioProgress monitor) {
        @SuppressWarnings ("resource")
        PrintWriter logger = this.mofSession.getReport().getLogger();
        
        logger.println("  Fixing BPMN Lanes PartitionElement...");
        
        SubProgress mon = SubProgress.convert(monitor, 5);
        
        final Collection<MofSmObjectImpl> existingLanes = this.mofSession.findByClass(this.bpmnLaneMC, false);
        mon.worked(1);
        mon.setWorkRemaining(existingLanes.size() + 1);
        
        for (MofSmObjectImpl lane : existingLanes) {
            MofSmObjectImpl partition = lane.getSingleDep("PartitionElement");
            if (partition==null) {
                continue;
            } else if (this.bpmnBaseElementMc.isInstance(partition)) {
                logger.format("    Move %s.PartitionElement = %s to BpmnPartitionElementRef.%n", lane, partition);
        
                lane.getDep("PartitionElement").clear();
                lane.getDep("BpmnPartitionElementRef").add(partition);
            } else if (! this.umlModelElementMc.isInstance(partition)) {
                // replace by a Dependency
                logger.format("    Replace %s.PartitionElement = %s by a Dependency.%n", lane, partition);
        
                MofSmObjectImpl dep = this.mofSession.createObject(this.dependencyMc);
                dep.getDep("DependsOn").add(partition);
                dep.getDep("Impacted").add(lane);
                dep.setName("PartitionElement");
        
                lane.getDep("PartitionElement").clear();
            }
        
            mon.worked(1);
        }
        logger.println("  Fixed BPMN Lanes PartitionElement.");
        
    }

    @objid ("a369938c-0072-4162-b9a8-324d1faaaf53")
    public  BpmnLanePartitionMigrator(IMofSession mofSession) {
        this.mofSession = mofSession;
        MofMetamodel metamodel = mofSession.getMetamodel();
        this.bpmnLaneMC = requireMClass(metamodel, "Standard.BpmnLane");
        this.bpmnBaseElementMc= requireMClass(metamodel, "Standard.BpmnBaseElement");
        this.umlModelElementMc= requireMClass(metamodel, "Standard.UmlModelElement");
        this.dependencyMc= requireMClass(metamodel, Dependency.MQNAME);
        
    }

    @objid ("83dd5029-262e-4ab9-9a71-687a8dc3e7c5")
    @SuppressWarnings ("unchecked")
    private static <T extends SmClass> T requireMClass(MofMetamodel metamodel, String name) {
        return (T) Objects.requireNonNull(metamodel.getMClass(name), name);
    }

}
