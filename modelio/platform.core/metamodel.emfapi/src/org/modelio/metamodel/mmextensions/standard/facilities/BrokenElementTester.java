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

package org.modelio.metamodel.mmextensions.standard.facilities;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.ElementRealization;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.metamodel.visitors.IDefaultInfrastructureVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Service class that tells whether an element is broken.
 * <p>
 * Usually an element is broken if one of its mandatory dependencies is <i>null</i>.
 */
@objid ("022aa484-0d79-11de-bb0e-001ec947ccaf")
public class BrokenElementTester {
    @objid ("45125935-0d7a-11de-bb0e-001ec947ccaf")
    public boolean isBroken(MObject anObject) {
        Object ret = anObject.accept(new BrokenVisitor());
        return ret != null ? (Boolean) ret : false;
    }

    @objid ("2f67e7c2-0d79-11de-bb0e-001ec947ccaf")
    private static class BrokenVisitor extends DefaultModelVisitor implements IDefaultInfrastructureVisitor {
        @objid ("25405e24-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitActivityEdge(ActivityEdge theElement) {
            if (theElement.getTarget() == null || theElement.getSource() == null) {
                return true;
            } else {
                return visitModelElement(theElement);
            }
        }

        @objid ("2542c06e-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitAssociationEnd(AssociationEnd currentRole) {
            AssociationEnd oppositeRole = currentRole.getOpposite();
            if (oppositeRole == null) {
                return true;
            }
            
            Classifier currentSource = currentRole.getSource();
            Classifier currentTarget = currentRole.getTarget();
            boolean currentNavigability = currentRole.isNavigable();
            
            Classifier oppositeSource = oppositeRole.getSource();
            Classifier oppositeTarget = oppositeRole.getTarget();
            boolean oppositeNavigability = oppositeRole.isNavigable();
            
            boolean ok = false;
            if (currentNavigability && !oppositeNavigability) { // THISSIDE
                // only current source and target must be filled
                ok = currentSource != null && currentTarget != null && oppositeSource == null && oppositeTarget == null;
            } else if (!currentNavigability && oppositeNavigability) { //OTHERSIDE:
                // only opposite source and target must be filled
                ok = currentSource == null && currentTarget == null && oppositeSource != null && oppositeTarget != null;
            } else if (currentNavigability && oppositeNavigability) { // BOTHSIDES:
                // current source must be equals to opposite target as well as current target and opposite source
                ok = currentSource != null && currentTarget != null && currentSource.equals(oppositeTarget) && currentTarget.equals(oppositeSource);
            } else if (!currentNavigability && !oppositeNavigability) { 
                // both sources must be filled, but no target
                ok = currentSource != null && currentTarget == null && oppositeSource != null && oppositeTarget == null;
            }
            
            if (ok) {
                return visitFeature(currentRole);
            } else {
                return true;
            }
        }

        @objid ("2542c06f-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitBinding(Binding theElement) {
            // Currently no audit rules forbid Binding with no source and no destination so disable the check.
            //
            // if ((theElement.getRepresentedFeature()== null) ||
            // (theElement.getRole()==0 && theElement.getConnectorRole()==0 && theElement.getConnectorEndRole()==0))
            // brokenDetected (theElement);
            // else
            return false;
        }

        @objid ("2542c072-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitDependency(Dependency theElement) {
            if (theElement.getImpacted() == null) {
                return true;
            } else {
                return false;
            }
        }

        @objid ("2542c073-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitElementImport(ElementImport theElement) {
            if (theElement.getImportedElement() == null) {
                return true;
            } else {
                return visitModelElement(theElement);
            }
        }

        @objid ("2542c074-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitElementRealization(ElementRealization theElement) {
            return visitAbstraction(theElement);
        }

        @objid ("38dc0cba-12f7-11de-831d-001ec947ccaf")
        @Override
        public Object visitGeneralization(Generalization theElement) {
            if (theElement.getSuperType() == null) {
                return true;
            } else {
                return visitModelElement(theElement);
            }
        }

        @objid ("2542c075-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitInterfaceRealization(InterfaceRealization theElement) {
            if (theElement.getImplemented() == null) {
                return true;
            } else {
                return visitModelElement(theElement);
            }
        }

        @objid ("2542c077-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitLinkEnd(LinkEnd currentRole) {
            LinkEnd oppositeRole = currentRole.getOpposite();
            if (oppositeRole == null) {
                return true;
            }
            
            Instance currentSource = currentRole.getSource();
            Instance currentTarget = currentRole.getTarget();
            boolean currentNavigability = currentRole.isNavigable();
            
            Instance oppositeSource = oppositeRole.getSource();
            Instance oppositeTarget = oppositeRole.getTarget();
            boolean oppositeNavigability = oppositeRole.isNavigable();
            
            boolean ok = false;
            if (currentNavigability && !oppositeNavigability) { // THISSIDE
                // only current source and target must be filled
                ok = currentSource != null && currentTarget != null && oppositeSource == null && oppositeTarget == null;
            } else if (!currentNavigability && oppositeNavigability) { //OTHERSIDE:
                // only opposite source and target must be filled
                ok = currentSource == null && currentTarget == null && oppositeSource != null && oppositeTarget != null;
            } else if (currentNavigability && oppositeNavigability) { // BOTHSIDES:
                // current source must be equals to opposite target as well as current target and opposite source
                ok = currentSource != null && currentTarget != null && currentSource.equals(oppositeTarget) && currentTarget.equals(oppositeSource);
            } else if (!currentNavigability && !oppositeNavigability) { 
                // both sources must be filled, but no target
                ok = currentSource != null && currentTarget == null && oppositeSource != null && oppositeTarget == null;
            }
            
            if (ok) {
                return visitModelElement(currentRole);
            } else {
                return true;
            }
        }

        @objid ("8a70b8e3-1303-11de-9ac2-001ec947ccaf")
        @Override
        public Object visitManifestation(Manifestation theElement) {
            if (theElement.getUtilizedElement() == null) {
                return true;
            } else {
                return visitModelElement(theElement);
            }
        }

        @objid ("e9ab8783-4b90-11de-884f-001ec947ccaf")
        @Override
        public Object visitMetaclassReference(MetaclassReference theElement) {
            if (theElement.getReferencedClassName().isEmpty()) {
                return true;
            } else {
                return visitElement(theElement);
            }
        }

        @objid ("2542c07a-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitNote(Note theElement) {
            if (theElement.getModel() == null) {
                return true;
            } else {
                return visitModelElement(theElement);
            }
        }

        @objid ("2542c07b-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitPackageImport(PackageImport theElement) {
            if (theElement.getImportedPackage() == null) {
                return true;
            } else {
                return visitModelElement(theElement);
            }
        }

        @objid ("2542c07c-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitPackageMerge(PackageMerge theElement) {
            if (theElement.getMergedPackage() == null) {
                return true;
            } else {
                return visitModelElement(theElement);
            }
        }

        @objid ("12033970-9de9-11de-b490-001ec947ccaf")
        @Override
        public Object visitTypedPropertyTable(TypedPropertyTable theElement) {
            if (theElement.getType() == null) {
                return true;
            } else {
                return visitElement(theElement);
            }
        }

        @objid ("2542c07d-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitRaisedException(RaisedException theElement) {
            if (theElement.getThrownType() == null) {
                return true;
            } else {
                return visitModelElement(theElement);
            }
        }

        @objid ("2542c07e-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitRequiredInterface(RequiredInterface theElement) {
            if (theElement.getRequiredElement() == null) {
                return true;
            } else {
                return visitModelElement(theElement);
            }
        }

        @objid ("1f3f4fb2-4b91-11de-884f-001ec947ccaf")
        @Override
        public Object visitStereotype(Stereotype theElement) {
            if (theElement.getBaseClassName().isEmpty()) {
                return true;
            } else {
                return visitModelElement(theElement);
            }
        }

        @objid ("2542c07f-0d7a-11de-bb0e-001ec947ccaf")
        @Override
        public Object visitTaggedValue(TaggedValue theElement) {
            if (theElement.getDefinition() == null) {
                return true;
            } else {
                return visitModelElement(theElement);
            }
        }

        @objid ("38dc0cbb-12f7-11de-831d-001ec947ccaf")
        @Override
        public Object visitUsage(Usage theElement) {
            if (theElement.getDependsOn() == null) {
                return true;
            } else {
                return visitDependency(theElement);
            }
        }

        @objid ("002a6a16-f750-1090-8d81-001ec947cd2a")
        @Override
        public Object visitElement(Element obj) {
            return false;
        }

        @objid ("2c62d8f9-1e0a-4e98-8872-7a7a3a409530")
        @Override
        public Object visitUmlModelElement(UmlModelElement obj) {
            return visitModelElement(obj);
        }

        @objid ("8e489c71-899c-4122-beeb-c50063009f50")
        @Override
        public Object visitModelElement(ModelElement obj) {
            return false;
        }

        @objid ("3a2a06b5-8bfe-419a-91a8-ea7ce70c0d4a")
        public BrokenVisitor() {
            super();
            this.infrastructureVisitor = this;
        }

    }

}
