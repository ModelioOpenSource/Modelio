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

package org.modelio.model.browser.view.content;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impact.ImpactProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.platform.model.ui.swt.labelprovider.LinkContainer;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default content provider with Infrastructure elements for the model browser.
 */
@objid ("0f3ddf9c-9ec6-4cff-a8c9-13819f740ad9")
public class InfrastructureContentProvider implements ITreeContentProvider {
    @objid ("d2060a35-4c7b-4617-9b0b-eb0fa2c46a4d")
    private Viewer currentViewer;

    @objid ("ce2fa07b-1fe6-44d9-a861-f01d8caf179e")
    private static InfrastructureVisitor visitor = new InfrastructureVisitor();

    @objid ("0d272496-35de-49fd-aab6-4f76a20e87de")
    @Override
    public void dispose() {
        // Nothing to do.
    }

    @objid ("15001a07-2845-4dc1-b76e-5e047a4b3bf2")
    @Override
    public Object[] getChildren(final Object parent) {
        // Special case: children of a fragment => the typed project(s)
        if (parent instanceof IProjectFragment) {
            return getFragmentRoots((IProjectFragment) parent).toArray();
        }
        
        // General case: MObject
        if (parent instanceof MObject) {
            return getChildren((MObject) parent).toArray();
        }
        
        // No children
        return Collections.EMPTY_LIST.toArray();
    }

    @objid ("b92e02aa-9c15-4056-b524-6ba453d9ed16")
    @Override
    public Object[] getElements(final Object parent) {
        // Nothing to return yet
        return new Object[0];
    }

    @objid ("87337da1-6690-46bb-b22e-28c77914c5c6")
    @Override
    public Object getParent(final Object child) {
        if (child instanceof MObject) {
            return getParent((MObject) child);
        }
        return null;
    }

    @objid ("14104417-3244-43b1-a373-607aa736dce1")
    @Override
    public boolean hasChildren(final Object parent) {
        if (parent instanceof IProjectFragment) {
            return hasChildren((IProjectFragment) parent);
        } else if (parent instanceof MObject) {
            return hasChildren((MObject) parent);
        } else {
            return false;
        }
    }

    @objid ("62547cfb-8263-4f9f-9f37-367f8c080782")
    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        this.currentViewer = viewer;
    }

    /**
     * Get children for a MObject
     * 
     * @param parent @return
     */
    @objid ("1c431ccf-5d0b-4e13-9518-6725f63cd2a9")
    private List<Object> getChildren(final MObject parent) {
        List<Object> mainChildren = InfrastructureContentProvider.visitor.getElements(parent);
        List<MObject> auxChildren = InfrastructureContentProvider.visitor.getLinks(parent);
        
        List<Object> ret = new ArrayList<>(mainChildren.size() + 2);
        ret.addAll(mainChildren);
        
        if (!auxChildren.isEmpty()) {
            ret.add(new LinkContainer(parent, auxChildren));
        }
        return ret;
    }

    /**
     * Has children? for a MObject
     * 
     * @param parent @return
     */
    @objid ("c28725f9-e27d-4ffb-b8c8-f65ce0608e91")
    private boolean hasChildren(final MObject parent) {
        if (parent instanceof ImpactModel) {
            // Assume ImpactModels always have A LOT of children
            return true;
        } else {
            return getChildren(parent).size() > 0;
        }
    }

    /**
     * Get children for a IProjectFragment
     */
    @objid ("1a571ae3-e52e-4bbc-b881-b0a52b291ff9")
    private List<Object> getFragmentRoots(IProjectFragment fragment) {
        List<Object> ret = new ArrayList<>();
        
        IRepository repository = fragment.getRepository();
        if (repository != null) {
            for (MObject root : fragment.getRoots()) {
                if (root.isValid() && root instanceof ModuleComponent) {
                    ret.add(root);
                } else if (root.isValid() && root instanceof ImpactProject) {
                    ret.add(root);
                }
            }
        }
        return ret;
    }

    /**
     * Has children? for a IProjectFragment
     */
    @objid ("eff879af-776e-431d-b452-f6e6805c7e95")
    private boolean hasChildren(IProjectFragment fragment) {
        return !getFragmentRoots(fragment).isEmpty();
    }

    /**
     * Get parent for a MObject
     * @param parent
     * @return
     */
    @objid ("aa24a64a-90da-46cf-ba49-05c31f982853")
    private Object getParent(final MObject child) {
        MObject element = child;
        MObject owner = element.getCompositionOwner();
        if (owner != null) {
            // look for right container
            if (InfrastructureContentProvider.visitor.getLinks(owner).contains(element)) {
                return new LinkContainer(owner, null);
            }
            return owner;
        } else if (element instanceof AbstractProject && element.isValid()) {
            // Composition root : return the project
            return GProject.getProject(element).getFragment(element);
        }
        return null;
    }

    /**
     * Visitor used to get tree node children.
     * <p>
     * <h3>Implementation note:</h3> If {@link #includeLinks} is <code>true</code>, the implementation should use {@link #addResults(List)} so that children tree nodes are linked to the container. <br/>
     * This should help the tree view not shrinking sometimes.
     * <p>
     * In the other case {@link #addResults(List)} should be called, to avoid creating adapters for nothing.
     */
    @objid ("b3fc0801-34cf-4898-aadb-79e38a72bb8d")
    private static class InfrastructureVisitor extends DefaultInfrastructureVisitor {
        /**
         * Show 'node' elements
         */
        @objid ("2127d4b5-a3e5-410f-bf34-f15e56924d1e")
        private boolean includeElements;

        /**
         * Show 'link' container and links
         */
        @objid ("ec904af7-e401-400c-a1eb-f0378fd8476a")
        private boolean includeLinks;

        /**
         * The computed children list
         */
        @objid ("c01dbcbd-3d26-4203-8f18-9677e4cba5ed")
        private List<Object> elements;

        @objid ("c1ab3be8-1e53-4b27-8446-79e8fd02fe40")
        private List<MObject> links;

        @objid ("9f93e994-15e2-4ab1-8f03-ea697319d76d")
        public InfrastructureVisitor() {
            // nothing to do
        }

        @objid ("1e3ce44f-ef91-40aa-b9d7-c241d5174e76")
        @Override
        public Object visitEnumeratedPropertyType(EnumeratedPropertyType theEnumeratedPropertyType) {
            if (this.includeElements) {
                // PropertyEnumerationLitteral
                addElementResults(theEnumeratedPropertyType.getLitteral());
            }
            return super.visitEnumeratedPropertyType(theEnumeratedPropertyType);
        }

        @objid ("c4fbe25d-35c5-4501-b1f3-eb9b86052bbd")
        @Override
        public Object visitImpactModel(ImpactModel theImpactModel) {
            // WARN : this nearly freezes Modelio with more than 5000 links
            // if (this.findLinksChildren) {
            // addResults(theImpactModel.getOwnedLinks());
            // }
            return super.visitImpactModel(theImpactModel);
        }

        @objid ("25f45bc3-18fb-4c2e-9a7a-ece23d1ca325")
        @Override
        public Object visitImpactProject(ImpactProject theImpactProject) {
            if (this.includeElements) {
                addElementResults(theImpactProject.getModel());
            }
            return super.visitImpactProject(theImpactProject);
        }

        @objid ("db8cbcc0-c102-408e-948c-552e3e970661")
        @Override
        public Object visitMatrixDefinition(MatrixDefinition theMatrixDefinition) {
            if (this.includeElements) {
                addElementResult(theMatrixDefinition.getLinesDefinition());
            
                addElementResult(theMatrixDefinition.getColumnsDefinition());
            
                addElementResult(theMatrixDefinition.getDepthDefinition());
            
                addElementResult(theMatrixDefinition.getValuesDefinition());
            }
            return super.visitMatrixDefinition(theMatrixDefinition);
        }

        @objid ("b6f6c803-34d7-4ddf-aeff-ad9b8735f7d6")
        @Override
        public Object visitMetaclassReference(MetaclassReference theMetaclassReference) {
            if (this.includeElements) {
                addElementResults(theMetaclassReference.getDefinedTagType());
            
                addElementResults(theMetaclassReference.getDefinedNoteType());
            
                addElementResults(theMetaclassReference.getDefinedResourceType());
            
                addElementResult(theMetaclassReference.getDefinedTable());
            }
            return super.visitMetaclassReference(theMetaclassReference);
        }

        @objid ("73a0d982-56ef-4f92-8497-79de6cabd934")
        @Override
        public Object visitModelElement(ModelElement theModelElement) {
            if (this.includeLinks) {
                addLinkResults(theModelElement.getDependsOnDependency());
            }
            if (this.includeElements) {
                addElementResults(theModelElement.getProduct());
            
                // Matrix
                addElementResults(theModelElement.getMatrix());
            }
            return super.visitModelElement(theModelElement);
        }

        @objid ("a285eba9-240d-43e8-80c1-c012cdcd3ba0")
        @Override
        public Object visitModuleComponent(ModuleComponent theModule) {
            if (this.includeElements) {
                addElementResults(theModule.getOwnedProfile());
            
                addElementResults(theModule.getModuleParameter());
            
                addElementResults(theModule.getDefinedPropertyType());
            
            }
            return super.visitModuleComponent(theModule);
        }

        @objid ("78a5d301-b2fe-4fee-a504-578ac63ea088")
        @Override
        public Object visitProfile(Profile theProfile) {
            if (this.includeElements) {
                addElementResults(theProfile.getOwnedReference());
            
                addElementResults(theProfile.getDefinedStereotype());
            
                addElementResults(theProfile.getDefinedType());
            }
            return super.visitProfile(theProfile);
        }

        @objid ("73dbd92c-5150-48e3-a4a0-bdad31cfd28e")
        @Override
        public Object visitPropertyTableDefinition(PropertyTableDefinition thePropertyTableDefinition) {
            if (this.includeElements) {
                addElementResults(thePropertyTableDefinition.getOwned());
            }
            return super.visitPropertyTableDefinition(thePropertyTableDefinition);
        }

        @objid ("2f478637-33cc-4bd8-be29-f044b9562974")
        @Override
        public Object visitStereotype(Stereotype theStereotype) {
            if (this.includeElements) {
                addElementResults(theStereotype.getDefinedTagType());
            
                addElementResults(theStereotype.getDefinedNoteType());
            
                addElementResults(theStereotype.getDefinedResourceType());
            
                addElementResult(theStereotype.getDefinedTable());
            }
            return super.visitStereotype(theStereotype);
        }

        /**
         * Avoid having a duplicated element in the result, but preserves order unlike a Set.
         * 
         * @param elt the element to add.
         */
        @objid ("eaa45a42-d9ef-49c6-9ff7-c66aef099e3e")
        private void addElementResult(Object elt) {
            if (elt != null && !this.elements.contains(elt)) {
                this.elements.add(elt);
            }
        }

        /**
         * Avoid having duplicated elements in the result, but preserves order unlike a Set.
         * 
         * @param elts the element to add.
         */
        @objid ("8ddb65b6-4ac0-47bc-a0bf-42bfaa496887")
        private void addElementResults(List<? extends MObject> elts) {
            if (!elts.isEmpty()) {
                // 'new ArrayList()' Shields against most concurrent modifications
                new ArrayList<>(elts).forEach(this::addElementResult);
            }
        }

        /**
         * Avoid having a duplicated element in the result, but preserves order unlike a Set.
         * 
         * @param elt the element to add.
         */
        @objid ("f8a12327-9202-468d-81d7-a102bebfc2ff")
        private void addLinkResult(MObject elt) {
            if (elt != null && !this.links.contains(elt)) {
                this.links.add(elt);
            }
        }

        /**
         * Avoid having duplicated elements in the result, but preserves order unlike a Set.
         * 
         * @param elts the element to add.
         */
        @objid ("f064358f-833d-4fed-8be6-cac71f968de0")
        private void addLinkResults(List<? extends MObject> elts) {
            if (!elts.isEmpty()) {
                // 'new ArrayList()' Shields against most concurrent modifications
                new ArrayList<>(elts).forEach(this::addLinkResult);
            }
        }

        /**
         * Get the non-links children to display into the given element.
         * 
         * @param parent the element where children will be looked for
         * @return The children to display when expanding the tree node.
         */
        @objid ("a853fee3-fb7b-4762-a4f1-c084d80db9dd")
        private List<Object> getElements(MObject parent) {
            this.includeElements = true;
            this.includeLinks = false;
            this.elements = new ArrayList<>();
            parent.accept(this);
            return this.elements;
        }

        /**
         * Get the link children to display into the given element.
         * 
         * @param parent the element where children will be looked for
         * @return The children to display when expanding the tree node.
         */
        @objid ("e262c9c9-39fa-42c8-a5f8-823313d743e5")
        private List<MObject> getLinks(MObject parent) {
            this.includeElements = false;
            this.includeLinks = true;
            this.links = new ArrayList<>();
            parent.accept(this);
            return this.links;
        }

    }

}
