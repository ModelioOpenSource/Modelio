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

package org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E282:
 * <ul>
 * <li>desc = A set of Dependencies should not create a cycle.</li>
 * <li>what = Dépendency ''{0}'' makes a cycle with previously existing Dependencies.</li>
 * </ul>
 */
@objid ("0070b4f8-e20e-1f69-b3fb-001ec947cd2a")
public class E282Checker implements IChecker {
    @objid ("0038e2ee-625d-1f6b-b3fb-001ec947cd2a")
    private static final String ERRORID = "E282";

    @objid ("00001b6c-e473-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        Dependency currentdep = (Dependency) object;
        
        List<Object> cycle = detectCycle(currentdep);
        
        if (cycle != null) {
            // Post the audit entry
            report.addEntry(new ModelError(ERRORID, (MObject) cycle.get(0), cycle));
        }
    }

    /**
     * end verifyDependencyCycle
     */
    @objid ("00001d38-e473-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=*, metaclass=Dependency, feature=Impacted
        plan.registerChecker(this, smMetamodel.getMClass(Dependency.class), TriggerType.Update, "Impacted");
        
        // trigger=*, metaclass=Dependency, feature=DependsOn
        plan.registerChecker(this, smMetamodel.getMClass(Dependency.class), TriggerType.Update, "DependsOn");
        
        // trigger=create, metaclass=Dependency, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(Dependency.class), TriggerType.Create, null);
    }

    @objid ("00392eb6-625d-1f6b-b3fb-001ec947cd2a")
    private List<Object> detectCycle(final Dependency dep) {
        List<Object> collector = new ArrayList<>();
        return detectCycle(dep, collector);
    }

    @objid ("00395e9a-625d-1f6b-b3fb-001ec947cd2a")
    private List<Object> detectCycle(final Dependency currentDep, final List<Object> collector) {
        collector.add(currentDep);
        
        // Look for dependencies as source or destination of the current
        // dependency
        ModelElement source = currentDep.getImpacted();
        ModelElement dest = currentDep.getDependsOn();
        
        if (source != null && ((MObject) source).isValid() && source instanceof Dependency) {
        
            // Search the source in the collector of dependencies. If found we
            // have a cycle !
            if (collector.contains(source)) {
                // There is a cycle here 
                collector.add(source);
                return collector;
            } else {
                // There is no cycle up to this one, recursive call of detectCycle
                return detectCycle((Dependency) source, collector);
            }
        }
        
        if (dest != null && ((MObject) dest).isValid() && dest instanceof Dependency) {
            // Search the dest in the collector of dependencies. If found we have a cycle !
            if (collector.contains(dest)) {
                // There is a cycle here 
                collector.add(dest);
                return collector;
        
            } else {
                // There is no cycle up to this one, recursive call of verifyDependencyCycle
                return detectCycle((Dependency) dest, collector);
            }
        }
        return null;
    }

}
