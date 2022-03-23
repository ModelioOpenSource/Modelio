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
package org.modelio.metamodel.mmextensions.standard.facilities;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Composition initializer service.
 * <p>
 * Use it to move an element under another one.
 */
@objid ("01f40498-0000-6d48-0000-000000000000")
public class CompositionInitializer extends DefaultModelVisitor {
    @objid ("ac678916-3718-4d25-8cd0-18514c3e3c73")
    private MExpert mExpert;

    @objid ("0015334e-f750-1090-8d81-001ec947cd2a")
    protected final SmObjectImpl parent;

    @objid ("00152bce-f750-1090-8d81-001ec947cd2a")
    protected SmDependency smDep;

    /**
     * Instantiate a composition initializer.
     * @param parent the new composition parent where elements will be initialized.
     */
    @objid ("01f40498-0000-7682-0000-000000000000")
    public  CompositionInitializer(final SmObjectImpl parent) {
        this.parent = parent;
    }

    /**
     * Attach the given element to the parent element.
     * @param obj the element to attach
     * @param dep optional, the metamodel dependency to use to attach the element.
     * @return <code>true</code> if the object was successfully attached, <code>false</code> if it
     * is impossible to attach the element to the parent.
     */
    @objid ("01f40498-0000-778a-0000-000000000000")
    public boolean execute(final SmObjectImpl obj, final SmDependency dep) {
        if (obj == null) {
            return false;
        }
        
        if (dep != null && this.parent != null) {
            SmDependency effectiveDep;
            if (this.parent.getMClass().hasBase(dep.getSource()) && obj.getMClass().hasBase(dep.getTarget())) {
                effectiveDep = dep;
            } else {
                effectiveDep = (SmDependency) getExpert().getDefaultCompositionDep(this.parent, obj);
                if (effectiveDep != null) {
                    effectiveDep = effectiveDep.getSymetric();
                }
            }
        
            // Try a generic approach
            List<MObject> mGet = obj.mGet(effectiveDep);
            if (mGet != null) {
                return mGet.add(parent);
            }
        }
        
        Object ret = obj.accept(this);
        return ret != null ? (boolean) ret : false;
    }

    @objid ("01f40498-0000-7302-0000-000000000000")
    @Override
    public Object visitActivityNode(ActivityNode theActivityNode) {
        if (this.parent instanceof Activity) {
            theActivityNode.setOwner((Activity) this.parent);
            return true;
        } else if (this.parent instanceof StructuredActivityNode) {
            theActivityNode.setOwnerNode((StructuredActivityNode) this.parent);
            return true;
        } else if (this.parent instanceof Clause) {
            theActivityNode.setOwnerClause((Clause) this.parent);
            return true;
        } else if (this.parent instanceof ActivityPartition) {
            theActivityNode.setOwnerPartition((ActivityPartition) this.parent);
            return true;
        } else {
            return visitUmlModelElement(theActivityNode);
        }
        
    }

    @objid ("01f40498-0000-7312-0000-000000000000")
    @Override
    public Object visitActivityPartition(ActivityPartition theActivityPartition) {
        if (this.parent instanceof ActivityPartition) {
            theActivityPartition.setSuperPartition((ActivityPartition) this.parent);
            return true;
        } else {
            return visitActivityGroup(theActivityPartition);
        }
        
    }

    @objid ("01f40498-0000-6f22-0000-000000000000")
    @Override
    public Object visitAssociationEnd(AssociationEnd theAssociationEnd) {
        if (this.parent instanceof Classifier) {
            theAssociationEnd.setSource((Classifier) this.parent, true);
            return true;
        } else if (this.parent instanceof AssociationEnd) {
            theAssociationEnd.setOpposite((AssociationEnd) this.parent);
            return true;
        }
        return false;
    }

    @objid ("01f40498-0000-6e72-0000-000000000000")
    @Override
    public Object visitConstraint(Constraint theConstraint) {
        if (this.parent instanceof ModelElement) {
            theConstraint.getConstrainedElement().add((UmlModelElement) this.parent);
            return true;
        } else {
            return visitUmlModelElement(theConstraint);
        }
        
    }

    @objid ("01f40498-0000-6eba-0000-000000000000")
    @Override
    public Object visitModelTree(ModelTree theModelTree) {
        if (this.parent instanceof TemplateParameter) {
            // TemplateParameter case must be before ModelTree because it is a ModelTree
            theModelTree.setOwnerTemplateParameter((TemplateParameter) this.parent);
            return true;
        } else if (this.parent instanceof ModelTree) {
            theModelTree.setOwner((ModelTree) this.parent);
            return true;
        } else if (this.parent instanceof Project) {
            // If parent is the project, add to the root package
            Project project = (Project) this.parent;
            List<Package> rootPackage = project.getModel();
            if (!rootPackage.isEmpty()) {
                theModelTree.setOwner(rootPackage.get(0));
                return true;
            }
        } else {
            return visitUmlModelElement(theModelTree);
        }
        return false;
    }

    @objid ("01f40498-0000-701a-0000-000000000000")
    @Override
    public Object visitPackage(Package thePackage) {
        // TemplateParameter case must be before ModelTree because it is a ModelTree
        if (this.parent instanceof TemplateParameter) {
            thePackage.setOwnerTemplateParameter((TemplateParameter) this.parent);
            return true;
        } else if (this.parent instanceof ModelTree) {
            thePackage.setOwner((ModelTree) this.parent);
            return true;
        } else if (this.parent instanceof Project) {
            // If parent is the project, add as root package
            Project project = (Project) this.parent;
            project.getModel().add(thePackage);
            return true;
        } else {
            return visitModelTree(thePackage);
        }
        
    }

    @objid ("01f40498-0000-7032-0000-000000000000")
    @Override
    public Object visitParameter(Parameter theParameter) {
        if (this.parent instanceof Operation) {
            if (this.smDep == null) {
                this.smDep = (SmDependency) getExpert().getDefaultCompositionDep(this.parent, theParameter);
            }
            if ("Returned".equals(this.smDep.getName()) || "Return".equals(this.smDep.getName())) {
                theParameter.setReturned((Operation) this.parent);
                return true;
            } else {
                theParameter.setComposed((Operation) this.parent);
                return true;
            }
        }
        return false;
    }

    @objid ("52baa235-a4de-11dd-81f5-001ec947ccaf")
    @Override
    public Object visitRegion(Region theRegion) {
        if (this.parent instanceof State) {
            theRegion.setParent((State) this.parent);
            return true;
        } else if (this.parent instanceof StateMachine) {
            // When appending to the state machine, the region is the top region.
            StateMachine machine = (StateMachine) this.parent;
            Region topState = machine.getTop();
            if (topState == null) {
                theRegion.setRepresented((StateMachine) this.parent);
                return true;
            }
        }
        return false;
    }

    @objid ("01f40498-0000-751a-0000-000000000000")
    @Override
    public Object visitStateVertex(StateVertex theStateVertex) {
        if (this.parent instanceof Region) {
            theStateVertex.setParent((Region) this.parent);
            return true;
        }
        return false;
    }

    @objid ("c6732d83-3a67-4910-8af3-f9e3820199ea")
    private MExpert getExpert() {
        if (this.mExpert == null) {
            this.mExpert = this.parent.getMClass().getMetamodel().getMExpert();
        }
        return this.mExpert;
    }

}
