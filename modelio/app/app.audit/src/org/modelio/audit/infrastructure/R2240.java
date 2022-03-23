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
package org.modelio.audit.infrastructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AbstractControl;
import org.modelio.audit.engine.core.AbstractRule;
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditExecutionPlan.AuditTrigger;
import org.modelio.audit.engine.core.IControl;
import org.modelio.audit.engine.core.IDiagnosticCollector;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NSUseChecker checkCycles
 */
@objid ("a23a0243-ab80-421e-b87c-a392c9ae7c42")
public class R2240 extends AbstractInfrastructureRule {
    @objid ("724841d2-dfef-450a-a212-74b500181e97")
    private static final String RULEID = "R2240";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("d0955ae9-a305-4955-bb90-e003c763a83e")
    private CheckR2240 checkerInstance = null;

    @objid ("61c2843b-4f45-401b-86f8-5d6647a792af")
    @Override
    public String getRuleId() {
        return R2240.RULEID;
    }

    @objid ("7a1f36bd-3b43-4e8f-bf30-c00832d404d3")
    @Override
    public void autoRegister(InfrastructureAuditPlan plan) {
        plan.registerRule(Package.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Component.MQNAME, this, AuditTrigger.UPDATE);
        
        plan.registerRule(ElementImport.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        plan.registerRule(PackageImport.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        plan.registerRule(PackageMerge.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        plan.registerRule(Dependency.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
        // plan.registerRule(Usage.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("016ddd73-a500-4dd0-ab5f-e3814f3de07f")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("2a6fd50d-acf0-4132-99a4-0992a203bdc6")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d77e7137-c37a-4428-beb8-5bd5833a2263")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2240
     */
    @objid ("c4515e73-35f9-4a47-b8ac-b17f323d46d5")
    public  R2240() {
        this.checkerInstance = new CheckR2240(this);
    }

    @objid ("71494054-7f05-455c-84db-eee7b596aac7")
    private static class CheckR2240 extends AbstractControl {
        @objid ("282c4f88-eb4c-446c-82d5-2fb912e4ae95")
        public  CheckR2240(IRule rule) {
            super(rule);
        }

        @objid ("7d06a1d1-cf87-4aa5-afab-64c30ec7fd9c")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof NameSpace) {
                diagnostic.addEntry(checkR2240((NameSpace) element));
            } else if (element instanceof Dependency) {
                ModelElement me = ((Dependency) element).getImpacted();
                if (me != null && (me instanceof Package || me instanceof Component)) {
                    diagnostic.addEntry(checkR2240((NameSpace) me));
                }
            } else if (element instanceof ElementImport) {
                NameSpace ns = ((ElementImport) element).getImportingNameSpace();
                if (ns != null && (ns instanceof Package || ns instanceof Component)) {
                    diagnostic.addEntry(checkR2240(ns));
                }
            } else if (element instanceof PackageImport) {
                NameSpace ns = ((PackageImport) element).getImportingNameSpace();
                if (ns != null && (ns instanceof Package || ns instanceof Component)) {
                    diagnostic.addEntry(checkR2240(ns));
                }
            } else if (element instanceof PackageMerge) {
                Package pkg = ((PackageMerge) element).getReceivingPackage();
                if (pkg != null) {
                    diagnostic.addEntry(checkR2240(pkg));
                }
            }
            return diagnostic;
        }

        @objid ("5b53e612-ab39-40a9-9cee-d8c6b5674cb6")
        private IAuditEntry checkR2240(final NameSpace nameSpace) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    nameSpace,
                    null);
            
            if (checkDependencyCycle(nameSpace, new ArrayList<NameSpace>())) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(nameSpace);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("d409a903-7964-4c67-8a64-556df61d0a5c")
        private boolean checkDependencyCycle(final NameSpace tested, final List<NameSpace> analysedPath) {
            if (analysedPath.contains(tested)) {
                // Cycle detected in the current analyzed path
                return true;
            } else {
                // Add tested namespace to the analyzed path
                analysedPath.add(tested);
            }
            
            // Gathers all namespaces the tested element has a link to
            Set<NameSpace> targets = new HashSet<>();
            
            for (Dependency dep : tested.getDependsOnDependency()) {
                ModelElement me = dep.getDependsOn();
                if (me != null && (me instanceof Package || me instanceof Component)) {
                    targets.add((NameSpace) me);
                }
            }
            
            for (ElementImport ei : tested.getOwnedImport()) {
                NameSpace ns = ei.getImportedElement();
                if (ns != null && (ns instanceof Package || ns instanceof Component)) {
                    targets.add(ns);
                }
            }
            
            for (PackageImport pi : tested.getOwnedPackageImport()) {
                Package pkg = pi.getImportedPackage();
                if (pkg != null) {
                    targets.add(pkg);
                }
            }
            
            if (tested instanceof Package) {
                for (PackageMerge pm : ((Package) tested).getMerge()) {
                    Package pkg = pm.getMergedPackage();
                    if (pkg != null) {
                        targets.add(pkg);
                    }
                }
            }
            
            // Check for a cycle in each of these paths
            for (NameSpace ns : targets) {
                if (checkDependencyCycle(ns, analysedPath)) {
                    return true;
                }
            }
            
            // No cycle found, remove tested namespace from the analyzed path
            analysedPath.remove(tested);
            return false;
        }

    }

}
