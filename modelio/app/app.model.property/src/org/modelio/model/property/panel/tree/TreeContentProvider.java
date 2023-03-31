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
package org.modelio.model.property.panel.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException;

/**
 * Content provider for the annotation view.
 */
@objid ("8fac8b30-c068-11e1-8c0a-002564c97630")
public class TreeContentProvider implements ITreeContentProvider {
    @objid ("52b8f9d4-86aa-4848-8db5-d524a6aa3fc6")
    private boolean displayHiddenTags = false;

    @objid ("33c2aabd-297b-4230-b767-f3700ab8c06c")
    private ModelElement annotedElement;

    @objid ("8fac8b32-c068-11e1-8c0a-002564c97630")
    @Override
    public Object[] getElements(Object object) {
        if (object == null) {
            return Collections.EMPTY_LIST.toArray();
        }
        List<Object> elements = new ArrayList<>();
        if (object instanceof Element) {
            // first child is the element itself
            elements.add(((Element) object).getMClass());
        }
        
        // for ModelElement add stereotypes and modules
        if (object instanceof ModelElement) {
            // then comes the modules
            elements.addAll(getContributingModules((ModelElement) object));
        }
        return elements.toArray();
    }

    @objid ("8faeec49-c068-11e1-8c0a-002564c97630")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

    @objid ("8faeec4c-c068-11e1-8c0a-002564c97630")
    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        // Nothing to do
        this.annotedElement = (ModelElement) (newInput instanceof ModelElement ? newInput : null);
        
    }

    @objid ("8faeec55-c068-11e1-8c0a-002564c97630")
    @Override
    public Object[] getChildren(Object parent) {
        if (parent instanceof ModuleComponent && this.annotedElement != null) {
            List<Stereotype> ret = new ArrayList<>();
            for (Stereotype stereotype : this.annotedElement.getExtension()) {
                if (parent.equals(stereotype.getModule())) {
                    ret.add(stereotype);
                }
            }
            return ret.toArray();
        }
        return Collections.EMPTY_LIST.toArray();
    }

    @objid ("8faeec5d-c068-11e1-8c0a-002564c97630")
    @Override
    public Object getParent(Object child) {
        return null;
    }

    @objid ("8faeec63-c068-11e1-8c0a-002564c97630")
    @Override
    public boolean hasChildren(Object parent) {
        if (parent instanceof ModuleComponent && this.annotedElement != null) {
            return getChildren(parent).length > 0;
        } else if (parent instanceof Stereotype) {
            return getChildren(parent).length > 0;
        }
        return false;
    }

    /**
     * Returns the list of the modules that 'contribute' to the model element annotations (ie tagged values based on metaclass reference instead of stereotype).<br/>
     * A module is a 'contributor' when:
     * <ul>
     * <li>it defines at least one tagtype applicable on the model element</li>
     * <li>it is started</li>
     * </ul>
     * @return a collection of Modules
     */
    @objid ("8faeec69-c068-11e1-8c0a-002564c97630")
    private Collection<ModuleComponent> getContributingModules(final ModelElement element) {
        Set<ModuleComponent> modules = new TreeSet<>(new Comparator<ModuleComponent>() {
            @Override
            public int compare(ModuleComponent o1, ModuleComponent o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        
        if (element.isValid()) {
            for (TagType tagType : findTagTypes(CoreSession.getSession(element), element.getMClass())) {
                MetaclassReference ownerReference = tagType.getOwnerReference();
                if (ownerReference != null && displayTagType(tagType)) {
                    modules.add(ownerReference.getOwnerProfile().getOwnerModule());
                }
            }
        
            for (PropertyTableDefinition tableDefinition : findPropertyTableDefinitions(CoreSession.getSession(element), element.getMClass())) {
                MetaclassReference ownerReference = tableDefinition.getOwnerReference();
                if (ownerReference != null) {
                    modules.add(ownerReference.getOwnerProfile().getOwnerModule());
                }
            }
        
            for (Stereotype stereotype : element.getExtension()) {
                final ModuleComponent module = stereotype.getModule();
                if (module != null) {
                    modules.add(module);
                }
            }
        }
        return modules;
    }

    @objid ("44a42f81-5bb3-445d-b07c-b62345961fa6")
    protected boolean displayTagType(TagType tagType) {
        return this.displayHiddenTags || !tagType.isIsHidden();
    }

    @objid ("a0e9b073-9554-45c6-9b6c-47185d233745")
    void setShowHiddenMdaElements(boolean show) {
        this.displayHiddenTags = show;
    }

    @objid ("419cf289-ec4b-4f14-9b1d-383eb3d06365")
    private MClass getBaseClass(ICoreSession session, String baseName) throws MetaclassNotFoundException {
        MClass smBase = session.getMetamodel().getMClass(baseName);
        
        if (smBase == null) {
            throw new MetaclassNotFoundException(baseName);
        }
        return smBase;
    }

    /**
     * Get tag types defined on metaclass references.
     */
    @objid ("93b5289b-d7a0-4c57-b10b-2401ca8a80de")
    private List<TagType> findTagTypes(ICoreSession session, MClass metaclass) {
        IModel model = session.getModel();
        List<TagType> ret = new ArrayList<>();
        for (ModuleComponent module : model.findByClass(ModuleComponent.class)) {
            for (Profile profile : module.getOwnedProfile()) {
                for (MetaclassReference reference : profile.getOwnedReference()) {
                    try {
                        MClass referenceClass = getBaseClass(session, reference.getReferencedClassName());
                        if (metaclass.hasBase(referenceClass)) {
                            for (TagType tagType : reference.getDefinedTagType()) {
                                ret.add(tagType);
                            }
                        }
                    } catch (@SuppressWarnings ("unused") MetaclassNotFoundException e) {
                        // Ignore bad metaclass
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Get property tabless defined on metaclass references.
     */
    @objid ("80b5a5e9-ecdd-401f-999a-fbc86d51681f")
    private List<PropertyTableDefinition> findPropertyTableDefinitions(CoreSession session, MClass metaclass) {
        IModel model = session.getModel();
        List<PropertyTableDefinition> ret = new ArrayList<>();
        for (ModuleComponent module : model.findByClass(ModuleComponent.class)) {
            for (Profile profile : module.getOwnedProfile()) {
                for (MetaclassReference reference : profile.getOwnedReference()) {
                    try {
                        MClass referenceClass = getBaseClass(session, reference.getReferencedClassName());
                        if (metaclass.hasBase(referenceClass)) {
                            PropertyTableDefinition table = reference.getDefinedTable();
                            if (table != null) {
                                ret.add(table);
                            }
                        }
                    } catch (@SuppressWarnings ("unused") MetaclassNotFoundException e) {
                        // Ignore bad metaclass
                    }
                }
            }
        }
        return ret;
    }

}
